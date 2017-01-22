package com.anwesome.game.trispy.gameobjects;
import android.graphics.*;

import com.anwesome.game.trispy.GameConstants;

public class RotatingLine {
    private float rot = 0,maxSpeed = 30,speed = 0;
    private RotatingLine() {

    }
    public static RotatingLine newInstance() {
        return new RotatingLine();
    }
    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
    public float getMaxSpeed() {
        return maxSpeed;
    }
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    public float getSpeed() {
        return speed;
    }
    public float getRot() {
        return rot;
    }
    public void setRot(int rot) {
        this.rot = rot;
    }
    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(GameConstants.ROTATING_LINE_COLOR);
        paint.setStrokeWidth(GameConstants.STROKE_SIZE);
        int x = canvas.getWidth()/2,y = canvas.getHeight()/2,r = canvas.getWidth()/GameConstants.RING_RADIUS_SCALE,x1 = r+canvas.getWidth()/GameConstants.LINE_SCALE;
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(rot);
        canvas.drawCircle(0,0,r,paint);
        canvas.drawLine(r,0,x1,0,paint);
        canvas.restore();
        rot+=speed;
        rot%=360;
    }
    public void handleTap(){
        if(speed == 0) {
            speed = maxSpeed;
        }
    }
    public int hashCode() {
        return (int)rot+(int)speed+(int)maxSpeed;
    }
}
