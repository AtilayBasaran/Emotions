package com.example.emotions;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLOutput;

public class GameOver extends AppCompatActivity {

    TextView tvPoints,PersonalBest;
    SharedPreferences sharedPreferences;
    EditText name;
    Button button1 , button2;
    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        int points = getIntent().getExtras().getInt("points");
        name = (EditText) findViewById(R.id.name);
        button1 = (Button) findViewById(R.id.save);
        button2 = (Button) findViewById(R.id.show);
        mDatabaseHelper = new DatabaseHelper(this);
        tvPoints = (TextView) findViewById(R.id.tvPoints);
        PersonalBest = (TextView) findViewById(R.id.PersonalBest);
        tvPoints.setText("" + points);
        sharedPreferences = getSharedPreferences("pref",0);
        int pointsSP = sharedPreferences.getInt("pointsSP",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(points>pointsSP){
            pointsSP = points;
            editor.putInt("pointsSP", pointsSP);
            editor.commit();
        }
        PersonalBest.setText("" + pointsSP);
    }
    public void restart(View view){
        Intent intent = new Intent(GameOver.this,GamePage.class);
        startActivity(intent);
        finish();
    }
    public void AddData(String newEntry,String p){
        boolean insertData = mDatabaseHelper.addData(newEntry,p);

        if(insertData){
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.data),Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.data2),Toast.LENGTH_SHORT).show();
        }
    }
    public void exit(View view){
        finish();
    }

    public void save(View view) {
        String newEntry = name.getText().toString();
        String pointDb = tvPoints.getText().toString();
        if(name.length() != 0){
            AddData(newEntry,pointDb);
            name.setText("");
            tvPoints.setText("");
        }else{
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.data3),Toast.LENGTH_SHORT).show();
        }
    }

    public void show(View view) {
        Intent intent = new Intent(GameOver.this,ScorePage.class);
        startActivity(intent);
    }
}
