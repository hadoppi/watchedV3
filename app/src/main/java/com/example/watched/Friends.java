package com.example.watched;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Friends extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        final String [] shows = getResources().getStringArray(R.array.friends);
        ListView list;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listview_layout, shows){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view;
                //If View doesn't exist create a new view
                if (convertView == null) {
                    // Create the Layout
                    LayoutInflater inflater = getLayoutInflater();
                    view = inflater.inflate(R.layout.listview_layout, parent, false);
                } else {
                    view = convertView;
                }
                TextView textView1 = (TextView) view.findViewById(R.id.listview_show);
                textView1.setText(shows[position]);

                return view;
            }
        };
        list = (ListView) findViewById(R.id.main_listview);
        list.setAdapter(adapter);

        //ListeView handler
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(Friends.this, "You have selected: " + shows[position], Toast.LENGTH_LONG).show();
            }
        });
    }
}