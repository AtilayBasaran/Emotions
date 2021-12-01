package com.example.emotions;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ScorePage extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    ListView mListView;
    String TAG = "ListData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_page);
        mListView = findViewById(R.id.list);
        mDatabaseHelper = new DatabaseHelper(this);
        
        showList();
    }

    private void showList() {
        Log.d(TAG,"showList: Displaying data in the ListView");

        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(getFullRecord(data.getString(1),data.getString(2)));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ScorePage.this,listData.get(position) + " " + getResources().getString(R.string.hasP),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getFullRecord(String name , String record){
        return name + "  " + record;
    }
}
