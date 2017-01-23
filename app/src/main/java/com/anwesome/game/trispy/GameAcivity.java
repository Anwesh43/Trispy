package com.anwesome.game.trispy;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.anwesome.game.trispy.views.GameView;

/**
 * Created by anweshmishra on 22/01/17.
 */
public class GameAcivity extends AppCompatActivity{
    private GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(gameView);
    }
    public void onPause() {
        super.onPause();
        gameView.pause();
    }
    public void onResume() {
        super.onResume();
        gameView.resume();
    }
}

