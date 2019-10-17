package com.example.vegetablezooapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.Locale;

public class GamePlayActivity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 60000;

    private TextView countdownText;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = START_TIME_IN_MILLIS;
    private boolean timeRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        countdownText = findViewById(R.id.countdownText);
        startTimer();


    }

    private void startTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeRunning = false;
            }
        }.start();

        timeRunning = true;
    }

    private void updateCountDownText(){
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);

        countdownText.setText(timeLeftFormatted);
    }


}
