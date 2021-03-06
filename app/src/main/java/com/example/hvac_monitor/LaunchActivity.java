package com.example.hvac_monitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class LaunchActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;

    //Hooks
    View top_logo;
    TextView title_hvac, title_my, team_num;

    //Animations
    Animation topAnimation, bottomAnimation, middleAnimation, middleAnimation2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_launch);

        //Animations
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        middleAnimation = AnimationUtils.loadAnimation(this, R.anim.middle_animation);
        middleAnimation2 = AnimationUtils.loadAnimation(this, R.anim.middle_animation2);

        //Hooks
        top_logo = findViewById(R.id.logo);
        title_my = findViewById(R.id.myTitle);
        title_hvac = findViewById(R.id.hvacTitle);
        team_num = findViewById(R.id.team);

        //Set Animations
        top_logo.setAnimation(topAnimation);
        title_my.setAnimation(middleAnimation);
        title_hvac.setAnimation(middleAnimation2);
        team_num.setAnimation(bottomAnimation);


        //Splash Screen

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent( LaunchActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
