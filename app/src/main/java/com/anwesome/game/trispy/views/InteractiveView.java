package com.anwesome.game.trispy.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.anwesome.game.trispy.GameConstants;
import com.anwesome.game.trispy.gameobjects.MenuBall;
import com.anwesome.game.trispy.gameobjects.RotatingLine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 23/01/17.
 */
public class InteractiveView extends View{
    private List<MenuBall> menuBalls = new ArrayList<>();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RotatingLine rotatingLine = RotatingLine.newInstance(1,12,6);
    public void setMenuBalls(MenuBall... menuBalls) {
        for(MenuBall ball:menuBalls) {
            this.menuBalls.add(ball);
        }
    }
    public InteractiveView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        canvas.drawColor(GameConstants.BACK_COLOR);
        rotatingLine.move();
        rotatingLine.draw(canvas,paint);
        for(MenuBall ball:menuBalls) {
            ball.draw(canvas,paint);
            if(rotatingLine.getRot() == ball.getDeg()) {
                if(ball.getNavigationHandler()!=null) {
                    ball.getNavigationHandler().handleNavigation();
                }
                rotatingLine.setSpeed(0);
                rotatingLine.setRot(270);
            }
        }


        try {
            Thread.sleep(100);
            invalidate();
        }
        catch (Exception ex) {

        }
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            for(MenuBall ball:menuBalls) {
                if(ball.containsTouch(event.getX(),event.getY())) {
                    rotatingLine.setSpeed(15);
                    break;
                }
            }
        }
        return true;
    }
}
