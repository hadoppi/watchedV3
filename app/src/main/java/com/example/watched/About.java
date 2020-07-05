package com.example.watched;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


    }
    private void setupActionBar() {

        getActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
