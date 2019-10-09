package com.example.vegetablezooapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN_INT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable(){

            public void run(){
                Intent homeIntent = new Intent(MainActivity.this, TitleSplashScreen.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_SCREEN_INT);
    }
}
