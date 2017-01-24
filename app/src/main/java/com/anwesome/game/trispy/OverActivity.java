package com.anwesome.game.trispy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
        MenuBall restart = MenuBall.newInstance(BitmapFactory.decodeResource(getResources(),R.drawable.restart),12,Color.parseColor("#b71c1c"));
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
        MenuBall home = MenuBall.newInstance(BitmapFactory.decodeResource(getResources(),R.drawable.home),12,Color.parseColor("#b71c1c"));
        home.setX(w/2-gap);
        home.setY(h/2);
        home.setDeg(0);
        home.setNavigationHandler(new MenuBall.NavigationHandler() {
            @Override
            public void handleNavigation() {
                Intent intent = new Intent(OverActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        interactiveView.setMenuBalls(restart,home);
    }
}
