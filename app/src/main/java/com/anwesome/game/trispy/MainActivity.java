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

public class MainActivity extends AppCompatActivity {
    private InteractiveView gameView;
    private int w ,h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new InteractiveView(this);
        initDimensions();
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
        gameView.setMenuBalls(playBall);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(gameView);

    }
    public void initDimensions() {
        DisplayManager displayManager = (DisplayManager)getSystemService(Context.DISPLAY_SERVICE);
        Point size = new Point();
        Display display = displayManager.getDisplay(0);
        if(display!=null) {
            display.getRealSize(size);
            w = size.x;
            h = size.y;
        }
    }
    public void onPause() {
        super.onPause();
    }
    public void onResume() {
        super.onResume();
    }
}
