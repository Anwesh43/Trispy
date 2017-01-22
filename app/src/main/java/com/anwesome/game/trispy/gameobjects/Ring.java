package com.anwesome.game.trispy.gameobjects;

import android.graphics.*;

import com.anwesome.game.trispy.GameConstants;

/**
 * Created by anweshmishra on 22/01/17.
 */
public class Ring {
    private float deg;
    private int color;
    private Ring(float deg,int color) {
        this.deg = deg;
        this.color = color;
    }
    public static Ring newInstance(float deg,int color){
        return new Ring(deg,color);
    }
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(GameConstants.STROKE_SIZE);
        float x= canvas.getWidth()/2,y = canvas.getHeight()/2;
        float r = canvas.getWidth()/ GameConstants.RING_RADIUS_SCALE;
        float rx = r+canvas.getWidth()/GameConstants.LINE_SCALE+r;
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(deg);
        canvas.drawCircle(rx,0,r,paint);
        canvas.restore();
    }
    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public float getDeg() {
        return deg;
    }
    public void setDeg(float deg) {
        this.deg = deg;
    }
    public int hashCode() {
        return (int)deg+color;
    }
}
