package com.anwesome.game.trispy.gameobjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 05/02/17.
 */
public class SplashText {
    private float scale=0;private int alpha=0;
    private String text;
    private SplashText(String text) {
        this.text = text;
    }
    public static SplashText newInstance(String text) {
        return new SplashText(text);
    }
    public void render(Canvas canvas, Paint paint,float x,float y) {
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(120);
        paint.setColor(Color.WHITE);
        paint.setAlpha(alpha);
        canvas.save();
        canvas.translate(x,y);
        canvas.drawText(text,-paint.measureText(text)/2,0,paint);
        canvas.restore();
    }
    public void animate() {
        if(alpha<255) {
            alpha+=55;
        }
    }
}
