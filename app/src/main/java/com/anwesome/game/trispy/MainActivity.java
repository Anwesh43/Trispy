package com.anwesome.game.trispy;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import com.anwesome.game.trispy.gameobjects.MenuBall;
import com.anwesome.game.trispy.views.GameView;
import com.anwesome.game.trispy.views.InteractiveView;

public class MainActivity extends InteractiveActivity {
    public void createMenus(InteractiveView interactiveView) {
        MenuBall playBall = MenuBall.newInstance(BitmapFactory.decodeResource(getResources(),R.drawable.play),12, Color.parseColor("#b71c1c"));
        playBall.setX(5*w/6);
        playBall.setY(h/2);
        playBall.setNavigationHandler(new MenuBall.NavigationHandler() {
            @Override
            public void handleNavigation() {
                Intent intent = new Intent(MainActivity.this,GameAcivity.class);
                startActivity(intent);
            }
        });
        interactiveView.setMenuBalls(playBall);
    }
}
