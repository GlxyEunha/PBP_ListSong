package com.example.listsong;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView shevaListView;
    ArrayList<String> arrayList;

    ArrayAdapter shevaAdapter;
    MediaPlayer shevaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shevaListView = findViewById(R.id.shevaListView);
        arrayList = new ArrayList<String>();
        Field[] fields = R.raw.class.getFields();
        for (int i = 0 ; i < fields.length ; i++){
            arrayList.add(fields[i].getName());
        }

        shevaAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        shevaListView.setAdapter(shevaAdapter);

        shevaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(shevaPlayer != null) {
                    shevaPlayer.release();
                }

                int resId = getResources().getIdentifier(arrayList.get(i),"raw",getPackageName());

                shevaPlayer = MediaPlayer.create(MainActivity.this,resId);
                shevaPlayer.start();

            }
        });
    }
}