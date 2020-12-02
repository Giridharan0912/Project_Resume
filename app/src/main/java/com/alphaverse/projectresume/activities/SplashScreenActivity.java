package com.alphaverse.projectresume.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.alphaverse.projectresume.R;
import com.alphaverse.projectresume.activities.profilelist.ProfileListActivity;

public class SplashScreenActivity extends AppCompatActivity {
    ImageView splashScreen;
    TextView appName;
    Animation side,bottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        splashScreen=findViewById(R.id.app_icon);
        appName=findViewById(R.id.app_name);
        side= AnimationUtils.loadAnimation(this,R.anim.side_anim);
        bottom= AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        splashScreen.setAnimation(side);
        appName.setAnimation(bottom);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
               startActivity(new Intent(SplashScreenActivity.this, ProfileListActivity.class));
               finish();
            }
        }, 3500);
    }

}