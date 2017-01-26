package com.anwesome.game.trispy.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import com.anwesome.game.trispy.GameConstants;
import com.anwesome.game.trispy.OverActivity;
import com.anwesome.game.trispy.gameobjects.MenuBall;
import com.anwesome.game.trispy.runners.GameRunner;
import com.anwesome.game.trispy.utils.GameNavigationalHandler;
import com.anwesome.game.trispy.utils.SoundStateHandler;

/**
 * Created by anweshmishra on 22/01/17.
 */
public class GameView extends SurfaceView{
    private Thread gameThread;
    private GameRunner runner;
    public GameView(final Context context) {
        super(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences(GameConstants.SCORE_PREF,0);
        runner=new GameRunner(new SoundStateHandler(context),getHolder(),sharedPreferences);
        runner.setNavigationHandler(new GameNavigationalHandler() {
            @Override
            public void handleNavigation(Intent intent) {
                context.startActivity(intent);
            }
            @Override
            public Intent getNavigationalIntent() {
                return new Intent(context,OverActivity.class);
            }
        });
        gameThread = new Thread(runner);
        gameThread.start();
    }
    public void pause() {
        runner.pause();
        while(true) {
            try{
                gameThread.join();
                break;
            }
            catch (Exception ex) {

            }
        }
    }
    public void resume() {
        runner.resume();
        gameThread = new Thread(runner);
        gameThread.start();
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            runner.handleTap(event.getX(),event.getY());
        }
        return true;
    }
}
