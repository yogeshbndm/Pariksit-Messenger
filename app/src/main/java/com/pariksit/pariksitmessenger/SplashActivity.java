package com.pariksit.pariksitmessenger;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    Animation topAnimation,bottomAnimation;
    private static int splashTimeOut = 2000;
    ImageView logo;
    TextView appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

//        topAnimation = AnimationUtils.loadAnimation(SplashActivity.this,R.anim.top_animation);
//        bottomAnimation = AnimationUtils.loadAnimation(SplashActivity.this,R.anim.bottom_animation);


        logo = findViewById(R.id.logo);
//        appName = findViewById(R.id.appName);

//        logo.setAnimation(topAnimation);
//        appName.setAnimation(bottomAnimation);
       // appName = findViewById(R.id.appName);

        logo.setAnimation(topAnimation);
       // appName.setAnimation(bottomAnimation);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },splashTimeOut);
    }
}