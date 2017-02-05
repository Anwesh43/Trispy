package com.anwesome.game.trispy.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.view.View;
import com.anwesome.game.trispy.GameConstants;
import com.anwesome.game.trispy.gameobjects.RotatingLine;
import com.anwesome.game.trispy.gameobjects.SplashText;
import com.anwesome.game.trispy.utils.GameNavigationalHandler;

/**
 * Created by anweshmishra on 05/02/17.
 */
public class SplashView extends View {
    private int time = 0;
    private RotatingLine rotatingLine;
    private SplashText splashText = SplashText.newInstance("TRIPSY");
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private GameNavigationalHandler navigationalHandler;
    public SplashView(Context context) {
        super(context);
        rotatingLine = RotatingLine.newInstance(4,80,40);
        rotatingLine.setSpeed(30);
    }
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.parseColor(GameConstants.SPLASH_BACK_COLOR));
        rotatingLine.draw(canvas,paint,GameConstants.ROTATING_LINE_COLOR,0);
        splashText.render(canvas,paint,canvas.getWidth()/2,canvas.getHeight()/5);
        if(time < 6 ) {
            rotatingLine.move();
            rotatingLine.setLineScale(rotatingLine.getLineScale()-5);
            rotatingLine.setRadiusScale(rotatingLine.getRadiusScale()-10);
        }
        else if(time>=6 && time<17){
            splashText.animate();
        }
        else if(navigationalHandler!=null) {
            Intent intent = navigationalHandler.getNavigationalIntent();
            navigationalHandler.handleNavigation(intent);
        }
        time++;
        if(time < 18) {
            try {
                Thread.sleep(100);
                invalidate();
            } catch (Exception ex) {

            }
        }
    }
    public void setNavigationalHandler(GameNavigationalHandler gameNavigationalHandler) {
        this.navigationalHandler = gameNavigationalHandler;
    }


}
