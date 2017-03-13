package com.anwesome.game.trispy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;

import com.anwesome.game.trispy.gameobjects.MenuBall;
import com.anwesome.game.trispy.views.InteractiveView;

/**
 * Created by anweshmishra on 23/01/17.
 */
public class OverActivity extends InteractiveActivity {
    protected void createMenus(InteractiveView interactiveView) {
        int gap = w/3;
        int score = getScoreFromGameActivity();
        SharedPreferences sharedPreferences = getSharedPreferences(GameConstants.SCORE_PREF,0);
        int highScore = sharedPreferences.getInt(GameConstants.HIGH_SCORE_KEY,0);
        MenuBall restart = MenuBall.newInstance(BitmapFactory.decodeResource(getResources(),R.drawable.restart),12,GameConstants.MENU_BALL_COLOR);
        restart.setX(w/2+gap);
        restart.setY(h/2);
        restart.setDeg(0);
        restart.setNavigationHandler(new MenuBall.NavigationHandler() {
            @Override
            public void handleNavigation() {
                Intent intent = new Intent(OverActivity.this,GameAcivity.class);
                startActivity(intent);
            }
        });
        MenuBall home = MenuBall.newInstance(BitmapFactory.decodeResource(getResources(),R.drawable.home),12,GameConstants.MENU_BALL_COLOR);
        home.setX(w/2-gap);
        home.setY(h/2);
        home.setDeg(180);
        home.setNavigationHandler(new MenuBall.NavigationHandler() {
            @Override
            public void handleNavigation() {
                Intent intent = new Intent(OverActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        interactiveView.setMenuBalls(restart,home);
        interactiveView.addHeader("Score:"+score);
        interactiveView.addHeader("Highest Score:"+highScore);
    }
    private int getScoreFromGameActivity() {
        Intent intent = getIntent();
        if(getIntent()!=null && getIntent().getExtras()!=null) {
            return getIntent().getExtras().getInt(GameConstants.SCORE_KEY,0);
        }
        return 0;
    }
}
