package com.example.vegetablezooapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class TitleSplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN_INT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_splash_screen);
        new Handler().postDelayed(new Runnable(){

            public void run(){
                Intent homeIntent = new Intent(TitleSplashScreen.this, MainMenu.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_SCREEN_INT);
    }
}
