package com.example.emotions;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class GamePage extends AppCompatActivity {

    TextView timer;
    TextView result;
    ImageView showImg;
    HashMap<String,Integer> map = new HashMap<>();
    ArrayList<String> emoteList = new ArrayList<String>();
    ArrayList<String> emoteListButton = new ArrayList<String>();
    int index;
    Button b1,b2,b3,b4,b5,b6;
    TextView tvPoint;
    int points;
    CountDownTimer countDownTimer;
    long millis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_page);
        timer = findViewById(R.id.timer);
        result = findViewById(R.id.result);
        showImg = findViewById(R.id.showImg);
        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);
        b3 = findViewById(R.id.btn3);
        b4 = findViewById(R.id.btn4);
        b5 = findViewById(R.id.btn5);
        b6 = findViewById(R.id.btn6);
        tvPoint = findViewById(R.id.point);
        index = 0;
        emoteList.add(getResources().getString(R.string.happy));
        emoteList.add(getResources().getString(R.string.sad));
        emoteList.add(getResources().getString(R.string.anxious));
        emoteList.add(getResources().getString(R.string.cheerful));
        emoteList.add(getResources().getString(R.string.fearful));
        emoteList.add(getResources().getString(R.string.excited));
        emoteList.add(getResources().getString(R.string.happy));
        emoteList.add(getResources().getString(R.string.sad));
        emoteList.add(getResources().getString(R.string.anxious));
        emoteList.add(getResources().getString(R.string.cheerful));
        emoteList.add(getResources().getString(R.string.fearful));
        emoteList.add(getResources().getString(R.string.excited));
        emoteList.add(getResources().getString(R.string.happy));
        emoteList.add(getResources().getString(R.string.anxious));
        emoteList.add(getResources().getString(R.string.excited));

        emoteListButton.add(getResources().getString(R.string.happy));
        emoteListButton.add(getResources().getString(R.string.sad));
        emoteListButton.add(getResources().getString(R.string.anxious));
        emoteListButton.add(getResources().getString(R.string.cheerful));
        emoteListButton.add(getResources().getString(R.string.fearful));
        emoteListButton.add(getResources().getString(R.string.excited));

        map.put(emoteList.get(0),R.drawable.happy);
        map.put(emoteList.get(1),R.drawable.sad);
        map.put(emoteList.get(2),R.drawable.anxious);
        map.put(emoteList.get(3),R.drawable.cheerful);
        map.put(emoteList.get(4),R.drawable.fearful);
        map.put(emoteList.get(5),R.drawable.excited);
        Collections.shuffle( emoteList);
        millis = 20000;
        points = 0;
        startGame();

    }

    private void startGame() {
        millis = 20000;
        timer.setText("" + (millis/1000) + "s");
        tvPoint.setText(points + " / " + emoteList.size());
        generateQuestion(index);
        countDownTimer = new CountDownTimer(millis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText("" + (millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                index++;
                if(index>emoteList.size()-1){
                    showImg.setVisibility(View.GONE);
                    b1.setVisibility(View.GONE);
                    b2.setVisibility(View.GONE);
                    b3.setVisibility(View.GONE);
                    b4.setVisibility(View.GONE);
                    b5.setVisibility(View.GONE);
                    b6.setVisibility(View.GONE);
                    Intent intent = new Intent(GamePage.this,GameOver.class);
                    startActivity(intent);
                    finish();
                }else{
                    startGame();
                }
            }
        }.start();
    }

    private void generateQuestion(int index) {
        ArrayList<String> emoteListTemp = (ArrayList<String>)emoteListButton.clone();
        Collections.shuffle(emoteListTemp);
        ArrayList<String> newList = new ArrayList<>();
        newList.add(emoteListTemp.get(0));
        newList.add(emoteListTemp.get(1));
        newList.add(emoteListTemp.get(2));
        newList.add(emoteListTemp.get(3));
        newList.add(emoteListTemp.get(4));
        newList.add((emoteListTemp.get(5)));
        Collections.shuffle(newList);
        b1.setText(newList.get(0));
        b2.setText(newList.get(1));
        b3.setText(newList.get(2));
        b4.setText(newList.get(3));
        b5.setText(newList.get(4));
        b6.setText(newList.get(5));
        showImg.setImageResource(map.get((emoteList.get(index))));

    }
    public void nextQuestion(View view){
        countDownTimer.cancel();
        index++;
        if(index > emoteList.size() -1){
            showImg.setVisibility(View.GONE);
            b1.setVisibility(View.GONE);
            b2.setVisibility(View.GONE);
            b3.setVisibility(View.GONE);
            b4.setVisibility(View.GONE);
            b5.setVisibility(View.GONE);
            b6.setVisibility(View.GONE);
            Intent intent = new Intent(GamePage.this,GameOver.class);
            intent.putExtra("points",points);
            startActivity(intent);
            finish();
        }else{
            startGame();
        }
    }
    public void answerSelected(View view){
        countDownTimer.cancel();
        String answer = ((Button) view).getText().toString().trim();
        String correctAnswer = emoteList.get(index);
        if(answer.equals(correctAnswer)){
            points++;
            tvPoint.setText(points + " / " + emoteList.size());
            result.setText(getResources().getString(R.string.correct));
            nextQuestion(view);
        }else {
            result.setText(getResources().getString(R.string.wrong));
            nextQuestion(view);
        }
    }
}
