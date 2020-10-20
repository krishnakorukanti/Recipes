package com.crish.recipes.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.crish.recipes.R;

public class SplashMainActivity extends AppCompatActivity {
private ImageView imageView;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_main);
        imageView = findViewById(R.id.imageSplash);
        imageView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                |View.SYSTEM_UI_FLAG_FULLSCREEN
                |View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        handler = new Handler();
    }
    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),SplashActivity.class));
                finish();
            }
        },2000);
    }
}