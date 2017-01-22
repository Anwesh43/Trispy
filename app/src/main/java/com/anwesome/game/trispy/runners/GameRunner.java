package com.anwesome.game.trispy.runners;

import android.graphics.*;
import android.view.*;
import com.anwesome.game.trispy.GameConstants;
import com.anwesome.game.trispy.gameobjects.Ring;
import com.anwesome.game.trispy.gameobjects.RotatingLine;
import com.anwesome.game.trispy.utils.GameCreateUtil;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 22/01/17.
 */
public class GameRunner implements Runnable{
    private boolean isRunning = false;
    private int time = 0;
    private int level = 1;
    private int w,h;
    private ConcurrentLinkedQueue<Ring> rings = new ConcurrentLinkedQueue<>();
    private RotatingLine rotatingLine;
    private SurfaceHolder surfaceHolder;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public void run() {
        while(isRunning) {
            if(surfaceHolder.getSurface().isValid()) {
                Canvas canvas = surfaceHolder.lockCanvas();
                if(time == 0){
                    w = canvas.getWidth();
                    h = canvas.getHeight();
                    rings = GameCreateUtil.createRings(level);
                    rotatingLine = RotatingLine.newInstance();
                }
                canvas.drawColor(Color.WHITE);
                canvas.drawColor(Color.parseColor("#99263238"));
                rotatingLine.draw(canvas,paint);
                for(Ring ring:rings) {
                    ring.draw(canvas,paint);
                    if(rotatingLine.getRot() == ring.getDeg()) {
                        rotatingLine.setSpeed(0);
                    }
                }
                surfaceHolder.unlockCanvasAndPost(canvas);
                time++;
            }
            try {

                Thread.sleep(GameConstants.GAME_DELAY);
            }
            catch(Exception ex) {

            }
        }
    }
    public void pause() {
        isRunning = false;
    }
    public void resume() {
        isRunning = true;
    }
    public GameRunner(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }
    public void handleTap() {
        rotatingLine.handleTap();
    }
}
