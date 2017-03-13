package com.anwesome.game.trispy.gameobjects;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.anwesome.game.trispy.GameConstants;

/**
 * Created by anweshmishra on 22/01/17.
 */
public class MovingBall {
    private float deg,x,rotSpeed = 0;
    private int color;
    private int edge=0,rendered = 0;
    private final float xSpeed = 6;
    private MovingBall(float x,float deg,int color) {
        this.deg = deg;
        this.x = x;
        this.color = color;
    }
    public static MovingBall newInstance(float x,float deg,int color) {
        return new MovingBall(x,deg,color);
    }
    public void draw(Canvas canvas, Paint paint) {

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        int pivotx = canvas.getWidth()/2,pivoty = canvas.getHeight()/2;
        canvas.save();
        canvas.translate(pivotx,pivoty);
        canvas.rotate(deg);
        canvas.drawCircle(x,0,canvas.getWidth()/GameConstants.RING_RADIUS_SCALE,paint);
        canvas.restore();
    }
    public void move() {
        x+=xSpeed;
        deg+=rotSpeed;
        deg%=360;
    }
    public int getColor() {
        return color;
    }
    public float getDeg() {
        return deg;
    }
    public float getX() {
        return x;
    }
    public void setEdge(int edge) {
        this.edge = edge;
    }
    public boolean isAtEdge() {
        return x>edge;
    }
    public int hashCode() {
        return (int)deg+(int)x+color;
    }
    public void setRotSpeed(float rotSpeed) {
        this.rotSpeed = rotSpeed;
    }
}
