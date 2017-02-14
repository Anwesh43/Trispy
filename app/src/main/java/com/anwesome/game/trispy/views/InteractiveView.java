package com.anwesome.game.trispy.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.preference.PreferenceActivity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;

import com.anwesome.game.trispy.GameConstants;
import com.anwesome.game.trispy.gameobjects.MenuBall;
import com.anwesome.game.trispy.gameobjects.RotatingLine;
import com.anwesome.game.trispy.gameobjects.SoundControl;
import com.anwesome.game.trispy.utils.SoundStateHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 23/01/17.
 */
public class InteractiveView extends View{
    private List<String> headers = new ArrayList<>();
    private List<MenuBall> menuBalls = new ArrayList<>();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private MenuBall selectedBall = null;
    private boolean isAnimating = false;
    protected SoundStateHandler soundStateHandler;
    protected SoundControl soundControl;
    private RotatingLine rotatingLine = RotatingLine.newInstance(1,12,6);
    public void setMenuBalls(MenuBall... menuBalls) {
        for(MenuBall ball:menuBalls) {
            this.menuBalls.add(ball);
        }
    }
    public InteractiveView(Context context) {
        super(context);
        soundStateHandler = new SoundStateHandler(context);
        soundControl = new SoundControl(soundStateHandler);
    }
    public void pause() {
        if(soundStateHandler!=null) {
            soundStateHandler.pause();
        }
    }
    public void resume() {
        if(soundStateHandler!=null) {
            soundStateHandler.start();
        }
    }
    public void onDraw(Canvas canvas) {
        canvas.drawColor(GameConstants.BACK_COLOR);
        drawSoundControl(canvas,paint);
        int y = canvas.getHeight()/8,y_gap = canvas.getHeight()/20;
        paint.setColor(Color.WHITE);
        paint.setTextSize(canvas.getHeight()/20);
        for(String header:headers) {
            canvas.drawText(header,canvas.getWidth()/2-paint.measureText(header)/2,y,paint);
            y+=y_gap;
        }
        rotatingLine.move();
        rotatingLine.draw(canvas,paint,GameConstants.ROTATING_LINE_COLOR,0);
        for(MenuBall menuBall:menuBalls) {
            menuBall.draw(canvas,paint);
        }
        if(selectedBall!=null && rotatingLine.getRot() == selectedBall.getDeg()) {
            rotatingLine.setSpeed(0);
            if(selectedBall.getNavigationHandler()!=null) {
                selectedBall.getNavigationHandler().handleNavigation();
                isAnimating = false;
            }
        }
        if(isAnimating) {
            try {
                Thread.sleep(100);
                invalidate();
            } catch (Exception ex) {

            }
        }
    }
    protected void drawSoundControl(Canvas canvas,Paint paint) {
        if(soundControl!=null) {
            soundControl.draw(canvas,paint);
        }
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            if(!(soundControl!=null && soundControl.containsTouch(event.getX(),event.getY()))) {
                for(MenuBall ball:menuBalls) {
                    if(ball.containsTouch(event.getX(),event.getY())) {
                        float finalDeg = ball.getDeg();
                        rotatingLine.setSpeed((finalDeg-(rotatingLine.getRot()-360))/6);
                        selectedBall = ball;
                        isAnimating = true;
                        postInvalidate();
                        break;
                    }
                }
            }
        }
        return true;
    }
    public void addHeader(String header) {
        this.headers.add(header);
    }

}
