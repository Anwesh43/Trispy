package com.anwesome.game.trispy.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import com.anwesome.game.trispy.OverActivity;
import com.anwesome.game.trispy.gameobjects.MenuBall;
import com.anwesome.game.trispy.runners.GameRunner;

/**
 * Created by anweshmishra on 22/01/17.
 */
public class GameView extends SurfaceView{
    private Thread gameThread;
    private GameRunner runner;
    public GameView(final Context context) {
        super(context);
        runner=new GameRunner(getHolder());
        runner.setNavigationHandler(new MenuBall.NavigationHandler() {
            @Override
            public void handleNavigation() {
                Intent intent = new Intent(context, OverActivity.class);
                context.startActivity(intent);
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
            runner.handleTap();
        }
        return true;
    }
}
