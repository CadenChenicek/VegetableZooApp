package com.example.vegetablezooapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class GamePlayActivity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 60000;

    private TextView countdownText;
    private TextView char1, char2, char3, char4;
    private final TextView space1 = null;
    private final TextView space2 = null;
    private final TextView space3 = null;
    private final TextView space4 = null;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = START_TIME_IN_MILLIS;
    private boolean timeRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        countdownText = findViewById(R.id.countdownText);
        startTimer();

        char1 = (TextView) findViewById(R.id.letter1);
        char2 = (TextView) findViewById(R.id.letter2);
        char3 = (TextView) findViewById(R.id.letter3);
        char4 = (TextView) findViewById(R.id.letter4);

        space1 = (TextView) findViewById(R.id.letterSpace1);
        space2 = (TextView) findViewById(R.id.letterSpace2);
        space3 = (TextView) findViewById(R.id.letterSpace3);
        space4 = (TextView) findViewById(R.id.letterSpace4);


        char1.setOnLongClickListener(longclickListener);
        char2.setOnLongClickListener(longclickListener);
        char3.setOnLongClickListener(longclickListener);
        char4.setOnLongClickListener(longclickListener);

        space1.setOnDragListener(dragListener);
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

    View.OnLongClickListener longclickListener = new View.OnLongClickListener(){
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("","");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, myShadowBuilder, v, 0);
            return true;
        }
    };

    View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            switch (dragEvent){
                case DragEvent.ACTION_DRAG_ENTERED:
                    final View view = (View) event.getLocalState();

                    if (view.getId() == R.id.letter1){
                        space1.setText(char1.getText());
                    }
                    else if (view.getId() == R.id.letter2){
                        space1.setText(char1.getText());
                    }
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    break;
            }

            return true;
        }
    };


}
