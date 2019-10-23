package com.example.vegetablezooapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
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

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.Random;

public class GamePlayActivity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 60000;
    public static final String VEGETABLE = "veg";
    private String veg;

    private TextView countdownText;
    private TextView char1, char2, char3, char4;
    private TextView space1, space2, space3, space4;
    private Character[] vegLetterArray = new Character[4];

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = START_TIME_IN_MILLIS;
    private boolean timeRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        Intent intent = getIntent();
        veg = intent.getStringExtra(VEGETABLE);
        TextView test = (TextView) findViewById(R.id.test1);
        test.setText(veg);

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

        assignLetters();

        char1.setOnLongClickListener(longclickListener);
        char2.setOnLongClickListener(longclickListener);
        char3.setOnLongClickListener(longclickListener);
        char4.setOnLongClickListener(longclickListener);

        space1.setOnDragListener(dragListener1);
        space2.setOnDragListener(dragListener2);
        space3.setOnDragListener(dragListener3);
        space4.setOnDragListener(dragListener4);
    }

    public void assignLetters(){
        int len = VEGETABLE.length();
        for (int i = 0; i <= len; i++){
            vegLetterArray[i] = veg.charAt(i);

        }
            //char1.setText(vegLetterArray[0].toString());
        RandomizeArray(vegLetterArray);
        char1.setText(vegLetterArray[0].toString());
        char2.setText(vegLetterArray[1].toString());
        char3.setText(vegLetterArray[2].toString());
        char4.setText(vegLetterArray[3].toString());

        /*
        int random = new Random().nextInt(5) + 1;

        if (random == 1){
            char1.setText(vegLetterArray[0].toString());

            int random2 = new Random().nextInt(4) + 1;
            if (random2 == 1){
                char2.setText(vegLetterArray[1].toString());

                int random3 = new Random().nextInt(3) + 1;
                if (random3 == 1){
                    char3.setText(vegLetterArray[2].toString());
                    char4.setText(vegLetterArray[3].toString());
                }
                else if (random3 == 2){
                    char4.setText(vegLetterArray[2].toString());
                    char3.setText(vegLetterArray[3].toString());
                }
            }

            else if (random2 == 2){
                char3.setText(vegLetterArray[1].toString());

                int random3 = new Random().nextInt(3) + 1;
                if (random3 == 1){
                    char2.setText(vegLetterArray[2].toString());
                    char4.setText(vegLetterArray[3].toString());
                }
                else if (random3 == 2){
                    char4.setText(vegLetterArray[2].toString());
                    char2.setText(vegLetterArray[3].toString());
                }
            }

            else if (random2 == 3){
                char4.setText(vegLetterArray[1].toString());

                int random3 = new Random().nextInt(3) + 1;
                if (random3 == 1){
                    char2.setText(vegLetterArray[2].toString());
                    char3.setText(vegLetterArray[3].toString());
                }
                else if (random3 == 2){
                    char3.setText(vegLetterArray[2].toString());
                    char2.setText(vegLetterArray[3].toString());
                }
            }

        }
        else if (random == 2){
            char2.setText(vegLetterArray[0].toString());

            int random2 = new Random().nextInt(4) + 1;
            if (random2 == 1){
                char1.setText(vegLetterArray[1].toString());

                int random3 = new Random().nextInt(3) + 1;
                if (random3 == 1){
                    char3.setText(vegLetterArray[2].toString());
                    char4.setText(vegLetterArray[3].toString());
                }
                else if (random3 == 2){
                    char4.setText(vegLetterArray[2].toString());
                    char3.setText(vegLetterArray[3].toString());
                }
            }
            else if (random2 == 2){
                char3.setText(vegLetterArray[1].toString());

                int random3 = new Random().nextInt(3) + 1;
                if (random3 == 1){
                    char1.setText(vegLetterArray[2].toString());
                    char4.setText(vegLetterArray[3].toString());
                }
                else if (random3 == 2){
                    char4.setText(vegLetterArray[2].toString());
                    char1.setText(vegLetterArray[3].toString());
                }
            }
            else if (random2 == 3){
                char4.setText(vegLetterArray[1].toString());

                int random3 = new Random().nextInt(3) + 1;
                if (random3 == 1){
                    char1.setText(vegLetterArray[2].toString());
                    char3.setText(vegLetterArray[3].toString());
                }
                else if (random3 == 2){
                    char3.setText(vegLetterArray[2].toString());
                    char1.setText(vegLetterArray[3].toString());
                }
            }

        }
        else if (random == 3){
            char3.setText(vegLetterArray[0].toString());

            int random2 = new Random().nextInt(4) + 1;
            if (random2 == 1){
                char1.setText(vegLetterArray[1].toString());

                int random3 = new Random().nextInt(3) + 1;
                if (random3 == 1){
                    char2.setText(vegLetterArray[2].toString());
                    char4.setText(vegLetterArray[3].toString());
                }
                else if (random3 == 2){
                    char4.setText(vegLetterArray[2].toString());
                    char3.setText(vegLetterArray[3].toString());
                }
            }
            else if (random2 == 2){
                char2.setText(vegLetterArray[1].toString());

                int random3 = new Random().nextInt(3) + 1;
                if (random3 == 1){
                    char1.setText(vegLetterArray[2].toString());
                    char4.setText(vegLetterArray[3].toString());
                }
                else if (random3 == 2){
                    char4.setText(vegLetterArray[2].toString());
                    char1.setText(vegLetterArray[3].toString());
                }
            }
            else if (random2 == 3){
                char4.setText(vegLetterArray[1].toString());

                int random3 = new Random().nextInt(3) + 1;
                if (random3 == 1){
                    char1.setText(vegLetterArray[2].toString());
                    char2.setText(vegLetterArray[3].toString());
                }
                else if (random3 == 2){
                    char2.setText(vegLetterArray[2].toString());
                    char1.setText(vegLetterArray[3].toString());
                }
            }
        }
        else if (random == 4){
            char4.setText(vegLetterArray[0].toString());

            int random2 = new Random().nextInt(4) + 1;
            if (random2 == 1){
                char1.setText(vegLetterArray[1].toString());

                int random3 = new Random().nextInt(3) + 1;
                if (random3 == 1){
                    char2.setText(vegLetterArray[2].toString());
                    char3.setText(vegLetterArray[3].toString());
                }
                else if (random3 == 2){
                    char3.setText(vegLetterArray[2].toString());
                    char2.setText(vegLetterArray[3].toString());
                }
            }
            else if (random2 == 2){
                char2.setText(vegLetterArray[1].toString());

                int random3 = new Random().nextInt(3) + 1;
                if (random3 == 1){
                    char1.setText(vegLetterArray[2].toString());
                    char3.setText(vegLetterArray[3].toString());
                }
                else if (random3 == 2){
                    char3.setText(vegLetterArray[2].toString());
                    char1.setText(vegLetterArray[3].toString());
                }
            }
            else if (random2 == 3){
                char3.setText(vegLetterArray[1].toString());

                int random3 = new Random().nextInt(3) + 1;
                if (random3 == 1){
                    char1.setText(vegLetterArray[2].toString());
                    char2.setText(vegLetterArray[3].toString());
                }
                else if (random3 == 2){
                    char2.setText(vegLetterArray[2].toString());
                    char1.setText(vegLetterArray[3].toString());
                }
            }

        }

         */



    }

    public static Character[] RandomizeArray(Character[] array){
        Random rgen = new Random();  // Random number generator

        for (int i=0; i<array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            Character temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        return array;
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

    View.OnLongClickListener longclickListener = new View.OnLongClickListener(){
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("","");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, myShadowBuilder, v, 0);
            return true;
        }
    };

    View.OnDragListener dragListener1 = new View.OnDragListener() {
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

    View.OnDragListener dragListener2 = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();

            switch (dragEvent){
                case DragEvent.ACTION_DRAG_ENTERED:
                    final View view = (View) event.getLocalState();

                    if (view.getId() == R.id.letter1){
                        space2.setText(char1.getText());
                    }
                    else if (view.getId() == R.id.letter2){
                        space2.setText(char2.getText());
                    }
                    else if (view.getId() == R.id.letter3){
                        space2.setText(char3.getText());
                    }
                    else if (view.getId() == R.id.letter4){
                        space2.setText(char4.getText());
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
    View.OnDragListener dragListener3 = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();

            switch (dragEvent){
                case DragEvent.ACTION_DRAG_ENTERED:
                    final View view = (View) event.getLocalState();

                    if (view.getId() == R.id.letter1){
                        space3.setText(char1.getText());
                    }
                    else if (view.getId() == R.id.letter2){
                        space3.setText(char2.getText());
                    }
                    else if (view.getId() == R.id.letter3){
                        space3.setText(char3.getText());
                    }
                    else if (view.getId() == R.id.letter4){
                        space3.setText(char4.getText());
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
    View.OnDragListener dragListener4 = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();

            switch (dragEvent){
                case DragEvent.ACTION_DRAG_ENTERED:
                    final View view = (View) event.getLocalState();

                    if (view.getId() == R.id.letter1){
                        space4.setText(char1.getText());
                    }
                    else if (view.getId() == R.id.letter2){
                        space4.setText(char2.getText());
                    }
                    else if (view.getId() == R.id.letter3){
                        space4.setText(char3.getText());
                    }
                    else if (view.getId() == R.id.letter4){
                        space4.setText(char4.getText());
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

