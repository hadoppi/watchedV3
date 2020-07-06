package com.example.watched;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.watched.DAO.MovieDao;
import com.example.watched.database.Movie;

import java.io.File;
import java.util.concurrent.Executors;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase   {
    private static final String TAG = "AppDatabaseOppi";
    private static final String DATABASE_NAME = "db_library_oppi";

    private final MutableLiveData<Boolean> isDatabaseCreated = new MutableLiveData<>();

    private static AppDatabase INSTANCE;
    private static final Object LOCK = new Object();

    public abstract MovieDao movieDao();


    public synchronized static AppDatabase getAppDatabase(Context context)
    {
        if(INSTANCE == null)
        {
            synchronized (LOCK)
            {
                if(INSTANCE == null)
                {
                    INSTANCE = buildDatabase(context.getApplicationContext());
                    INSTANCE.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return INSTANCE;
    }

    private static AppDatabase buildDatabase(final Context appContext)
    {
        Log.i(TAG, "Database will be initialized.");
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback()
                {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db)
                    {
                        super.onCreate(db);
                        Executors.newSingleThreadExecutor().execute(() -> {
                            AppDatabase database = AppDatabase.getAppDatabase(appContext);
                            DatabaseInitializer.populateDatabase(database);
                            // notify that the database was created and it's ready to be used
                            database.setDatabaseCreated();
                        });
                    }
                }).build();
    }
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            Log.i(TAG, "Database initialized.");
            setDatabaseCreated();
        }
    }
    private void setDatabaseCreated(){
        isDatabaseCreated.postValue(true);
    }
    public LiveData<Boolean> getDatabaseCreated() {
        return isDatabaseCreated;
    }
    public static void initializeDemoData(final AppDatabase database)
    {
        Executors.newSingleThreadExecutor().execute(() ->
        {
            database.runInTransaction(() -> {
                Log.i(TAG, "Wipe database.");
                database.movieDao().deleteAll();
                database.clearAllTables();

                DatabaseInitializer.populateDatabase(database);
            });
        });
    }
}