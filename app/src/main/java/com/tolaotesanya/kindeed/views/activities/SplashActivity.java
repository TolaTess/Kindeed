package com.tolaotesanya.kindeed.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.tolaotesanya.kindeed.R;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 2000;

    Animation animation;
    TextView app_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        animation = AnimationUtils.loadAnimation(this, R.anim.splash_anim);
        app_title = findViewById(R.id.app_name);

        app_title.setAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(splashIntent);
            }
        }, SPLASH_SCREEN);

    }
}
