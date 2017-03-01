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
    private Typeface typeface;
    private GameNavigationalHandler navigationalHandler;
    public SplashView(Context context) {
        super(context);
        rotatingLine = RotatingLine.newInstance();
        rotatingLine.setSpeed(30);
        rotatingLine.setAlpha(0);
        typeface = Typeface.createFromAsset(context.getAssets(),"Otto.ttf");
    }
    public void onDraw(Canvas canvas) {
        paint.setTypeface(typeface);
        canvas.drawColor(Color.parseColor(GameConstants.SPLASH_BACK_COLOR));
        rotatingLine.draw(canvas,paint,GameConstants.ROTATING_LINE_COLOR,0);
        splashText.render(canvas,paint,canvas.getWidth()/2,canvas.getHeight()/7);
        if(time>=3 && time < 7 ) {
            rotatingLine.incrementAlpha();
        }
        else if(time>=7 && time<11){
            splashText.animate();
        }
        else if(time>=16 && navigationalHandler!=null) {
            Intent intent = navigationalHandler.getNavigationalIntent();
            navigationalHandler.handleNavigation(intent);
        }
        time++;
        if(time < 17) {
            try {
                Thread.sleep(200);
                invalidate();
            } catch (Exception ex) {

            }
        }
    }
    public void setNavigationalHandler(GameNavigationalHandler gameNavigationalHandler) {
        this.navigationalHandler = gameNavigationalHandler;
    }


}
