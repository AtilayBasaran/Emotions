package com.example.emotions;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class SettingsPage extends AppCompatActivity {

    private Button lan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_page);
        lan = (Button)findViewById(R.id.language);

    }

    public void change(View view) {
        showChangeLanguageDialog();
    }

    private void showChangeLanguageDialog() {
        final String [] listLan = {getResources().getString(R.string.lan1),getResources().getString(R.string.lan2)};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(SettingsPage.this);
        mBuilder.setTitle(getResources().getString(R.string.language));
        mBuilder.setSingleChoiceItems(listLan, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(i == 0){
                    setLocale("en");
                    recreate();
                }
                else if(i == 1){
                    setLocale("tr");
                    recreate();
                }
                dialog.dismiss();
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }
    private void setLocale(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();

    }

    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Setings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang","");
        setLocale(language);
    }

    public void backMenu(View view) {
        Intent intent = new Intent(SettingsPage.this,
                MainActivity.class);
        startActivity(intent);
    }
}
