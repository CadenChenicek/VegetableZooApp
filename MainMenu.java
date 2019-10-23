package com.example.vegetablezooapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.util.Random;

public class MainMenu extends AppCompatActivity {
    private static String veg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        /*
        Beet
        Leek
        Kale
        Corn
        Peas
         */
        String beet = "BEET";
        String leek = "LEEK";
        String kale = "KALE";
        String corn = "CORN";
        String peas = "PEAS";

        int random = new Random().nextInt(6) + 1;

        if (random == 1){
            veg = beet;
        }
        else if (random == 2){
            veg = leek;
        }
        else if (random == 3){
            veg = kale;
        }
        else if (random == 4){
            veg = corn;
        }
        else {
            veg = peas;
        }

    }

    public void clickPlay(View v) {
        Intent intent = new Intent(MainMenu.this, GamePlayActivity.class);
        intent.putExtra(GamePlayActivity.VEGETABLE, veg);
        startActivity(intent);
    }
}
