package com.anwesome.game.trispy;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.anwesome.game.trispy.utils.GameNavigationalHandler;
import com.anwesome.game.trispy.views.SplashView;

/**
 * Created by anweshmishra on 05/02/17.
 */
public class SplashActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashView splashView = new SplashView(this);
        splashView.setNavigationalHandler(new GameNavigationalHandler() {
            @Override
            public Intent getNavigationalIntent() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                return intent;
            }

            @Override
            public void handleNavigation(Intent intent) {
                startActivity(intent);
            }
        });
        setContentView(splashView);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
    public void onBackPressed() {

    }
}
