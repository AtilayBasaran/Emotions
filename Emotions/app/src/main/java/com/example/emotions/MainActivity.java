package com.example.emotions;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void startGame(View view) {
        Intent intent = new Intent(MainActivity.this,
                GamePage.class);
        startActivity(intent);
    };

    public void settings(View view) {
        Intent intent = new Intent(MainActivity.this,
                SettingsPage.class);
        startActivity(intent);
    };

    public void scoreTable(View view) {
        Intent intent = new Intent(MainActivity.this,
                ScorePage.class);
        startActivity(intent);
    };

    public void information(View view) {
        Intent intent = new Intent(MainActivity.this,
                InformationPage.class);
        startActivity(intent);
    };
}