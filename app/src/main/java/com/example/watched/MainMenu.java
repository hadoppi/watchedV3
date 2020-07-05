package com.example.watched;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    Button buttonDiscover;
    Button buttonMessages;
    Button buttonFriends;
    Button buttonTimer;
    Button buttonMyList;
    Button buttonConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        buttonDiscover = (Button) findViewById(R.id.circle_button2);
        buttonDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiscover();
            }
        });
        buttonConfiguration = (Button) findViewById(R.id.circle_button5);
        buttonConfiguration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConfiguration();
            }
        });
        buttonMessages = (Button) findViewById(R.id.circle_button6);
        buttonMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMessages();
            }
        });
        buttonMyList = (Button) findViewById(R.id.circle_button);
        buttonMyList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyList();
            }
        });
        buttonFriends = (Button) findViewById(R.id.circle_button7);
        buttonFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFriends();
            }
        });
        buttonTimer = (Button) findViewById(R.id.circle_button4);
        buttonTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimer();
            }
        });

    }
    public void openDiscover(){
        Intent intent = new Intent(this, Discover.class);
        startActivity(intent);
    }
    public void openMessages(){
        Intent intent = new Intent(this, Messages.class);
        startActivity(intent);
    }
      public void openFriends(){
        Intent intent = new Intent(this, Friends.class);
        startActivity(intent);
    }
      public void openTimer(){
        Intent intent = new Intent(this, Timer.class);
        startActivity(intent);
    }
      public void openMyList(){
        Intent intent = new Intent(this, MyList.class);
        startActivity(intent);
    }
      public void openConfiguration(){
        Intent intent = new Intent(this, Configuration.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){
            case R.id.action_about:
                Intent intent = new Intent(this, About.class);
                MainMenu.this.startActivity(intent);
                break;
        }

        return true;
    }


}
