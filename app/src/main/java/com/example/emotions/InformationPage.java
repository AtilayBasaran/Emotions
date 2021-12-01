package com.example.emotions;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class InformationPage extends AppCompatActivity {
    RecyclerView r1;
    String s1[],s2[];
    int imageResource[] = {R.drawable.emote_happy,R.drawable.emote_sad,R.drawable.emote_cheerful,R.drawable.emote_anxious,R.drawable.emote_fearful,R.drawable.emote_excited};
    myOwnAdapter ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_page);
        r1 = (RecyclerView) findViewById(R.id.re);

        s1 = getResources().getStringArray(R.array.emote_name);
        s2 = getResources().getStringArray(R.array.description);
        ad = new myOwnAdapter(this,s1,s2,imageResource);

        r1.setAdapter(ad);
        r1.setLayoutManager(new LinearLayoutManager(this));
    }
}

