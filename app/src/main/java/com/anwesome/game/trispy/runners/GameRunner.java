package com.anwesome.game.trispy.runners;

import android.graphics.*;
import android.view.*;
import com.anwesome.game.trispy.GameConstants;
import com.anwesome.game.trispy.gameobjects.MovingBall;
import com.anwesome.game.trispy.gameobjects.Ring;
import com.anwesome.game.trispy.gameobjects.RotatingLine;
import com.anwesome.game.trispy.utils.GameCreateUtil;
import com.anwesome.game.trispy.utils.GameStateHandler;
import com.anwesome.game.trispy.utils.GameStateIndicator;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 22/01/17.
 */
public class GameRunner implements Runnable{
    private boolean isRunning = false;
    private int time = 0;
    private int level = 1;
    private int w,h;
    private int currentRingColor = 0;
    private ConcurrentLinkedQueue<Ring> rings = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<MovingBall> balls = new ConcurrentLinkedQueue<>();
    private RotatingLine rotatingLine;
    private SurfaceHolder surfaceHolder;
    private MovingBall currentBall;
    private GameStateHandler gameStateHandler;
    private GameStateIndicator gameStateIndicator;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public void run() {
        while(isRunning) {
            if(surfaceHolder.getSurface().isValid()) {
                Canvas canvas = surfaceHolder.lockCanvas();
                if(time == 0){
                    w = canvas.getWidth();
                    h = canvas.getHeight();
                    rings = GameCreateUtil.createRings();
                    rotatingLine = RotatingLine.newInstance();
                    currentBall = GameCreateUtil.createBall(0,w);
                    gameStateHandler = GameStateHandler.newInstance();
                    gameStateIndicator = GameStateIndicator.newInstance(canvas,gameStateHandler);
                    balls.add(currentBall);
                }
                canvas.drawColor(GameConstants.BACK_COLOR);
                rotatingLine.draw(canvas,paint);
                rotatingLine.move();
                for(MovingBall movingBall:balls) {
                    movingBall.setRotSpeed(rotatingLine.getSpeed());
                    movingBall.draw(canvas,paint);
                    movingBall.move();
                }
                for(Ring ring:rings) {
                    ring.draw(canvas,paint);
                    if(rotatingLine.getRot() == ring.getDeg()) {
                        rotatingLine.setSpeed(0);
                        if(currentBall!=null) {
                            float currentBallDeg = currentBall.getDeg();
                            int ringColorIndexForCurrentBall = (int) (currentBallDeg / 90);
                            currentRingColor = checkForRingColor(ringColorIndexForCurrentBall);
                        }
                    }
                }
                if(currentBall!=null && currentBall.isAtEdge()) {
                    if (currentRingColor == currentBall.getColor()) {
                        balls.remove(currentBall);
                        gameStateHandler.addScore();
                        if(gameStateHandler.getScore()%5 == 0) {
                            level++;
                        }
                        //Code to set first ball of the collection as currentBall
                        if(balls.size()>0) {
                            setFirstBallAsCurrent();
                        }
                        else {
                            currentBall = null;
                        }
                    }

                    else {
                        gameStateHandler.showOver();
                        isRunning = false;
                    }
                }
                currentRingColor = 0;
                gameStateIndicator.drawScore(paint);
                gameStateIndicator.drawOver(paint);
                surfaceHolder.unlockCanvasAndPost(canvas);
                if(balls.size()>0 && currentBall == null) {
                    setFirstBallAsCurrent();
                }
                time++;
                GameCreateUtil.createMovingBallForLevel(time,level,w,rotatingLine,balls);
            }
            try {
                Thread.sleep(GameConstants.GAME_DELAY);
            }
            catch(Exception ex) {

            }
        }
    }
    private void setFirstBallAsCurrent() {
        for(MovingBall ball:balls) {
            currentBall = ball;
            break;
        }
    }
    private int checkForRingColor(int index) {
        int i =0;
        for(Ring ring:rings) {
            if(i == index) {
                return ring.getColor();
            }
            i++;
        }
        return 0;
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
