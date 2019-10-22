package com.example.vegetablezooapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

public class GamePlayActivity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 60000;

    private TextView countdownText;
    private ImageView char1, char2, char3, char4;
    private ImageView space1, space2, space3, space4;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = START_TIME_IN_MILLIS;
    private boolean timeRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        countdownText = findViewById(R.id.countdownText);
        startTimer();

        char1 = (ImageView) findViewById(R.id.letter1);
        char2 = (ImageView) findViewById(R.id.letter2);
        char3 = (ImageView) findViewById(R.id.letter3);
        char4 = (ImageView) findViewById(R.id.letter4);

        space1 = (ImageView) findViewById(R.id.letterSpace1);
        space2 = (ImageView) findViewById(R.id.letterSpace2);
        space3 = (ImageView) findViewById(R.id.letterSpace3);
        space4 = (ImageView) findViewById(R.id.letterSpace4);

        findViewById(R.id.letter1).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.letter2).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.letter3).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.letter4).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.letterSpace1).setOnDragListener(new MyDragListener());
        findViewById(R.id.letterSpace2).setOnDragListener(new MyDragListener());
        findViewById(R.id.letterSpace3).setOnDragListener(new MyDragListener());
        findViewById(R.id.letterSpace4).setOnDragListener(new MyDragListener());

        /*
        char1.setOnLongClickListener(longclickListener);
        char2.setOnLongClickListener(longclickListener);
        char3.setOnLongClickListener(longclickListener);
        char4.setOnLongClickListener(longclickListener);

        space1.setOnDragListener(dragListener);
        space2.setOnDragListener(dragListener);
        space3.setOnDragListener(dragListener);
        space4.setOnDragListener(dragListener);

         */
    }

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    class MyDragListener implements View.OnDragListener {
        Drawable enterShape = getResources().getDrawable(
                R.drawable.shape_droptarget);
        Drawable normalShape = getResources().getDrawable(R.drawable.shape);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundDrawable(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) v;
                    container.addView(view);
                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundDrawable(normalShape);
                default:
                    break;
            }
            return true;
        }
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

    /*
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
                        space1.setText(char2.getText());
                    }
                    else if (view.getId() == R.id.letter3){
                        space1.setText(char3.getText());
                    }
                    else if (view.getId() == R.id.letter4){
                        space1.setText(char4.getText());
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

     */

    // Assign the touch listener to your view which you want to move


    // This defines your touch listener


}

