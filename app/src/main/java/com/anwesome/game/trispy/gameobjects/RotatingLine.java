package com.anwesome.game.trispy.gameobjects;
import android.graphics.*;

import com.anwesome.game.trispy.GameConstants;

public class RotatingLine {
    private float rot = 270,maxSpeed = GameConstants.ROTATING_SPEED,speed = 0;
    private int lines = 4,radiusScale = GameConstants.RING_RADIUS_SCALE,lineScale = GameConstants.LINE_SCALE;
    private RotatingLine() {

    }
    private RotatingLine(int lines,int radiusScale,int lineScale) {
        this.lines = lines;
        this.radiusScale = radiusScale;
        this.lineScale = lineScale;
    }
    public static RotatingLine newInstance(int... arguments) {
        if(arguments.length == 3) {
            return new RotatingLine(arguments[0],arguments[1],arguments[2]);
        }
        return new RotatingLine();
    }

    public int getRadiusScale() {
        return radiusScale;
    }

    public void setRadiusScale(int radiusScale) {
        this.radiusScale = radiusScale;
    }

    public int getLineScale() {
        return lineScale;
    }

    public void setLineScale(int lineScale) {
        this.lineScale = lineScale;
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
    public void draw(Canvas canvas, Paint paint,int color,int deg) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        paint.setStrokeWidth(GameConstants.STROKE_SIZE);
        int x = canvas.getWidth()/2,y = canvas.getHeight()/2,r = canvas.getWidth()/radiusScale,x1 = r+canvas.getWidth()/lineScale;
        canvas.save();
        canvas.translate(x,y);
        canvas.rotate(rot);
        paint.setColor(color);
        canvas.drawCircle(0,0,r,paint);
        for(int i=0;i<lines;i++) {
            canvas.save();
            canvas.rotate(90*i);
            if(deg == (90*i+rot)%360) {
                paint.setColor(color);
            }
            else {
                paint.setColor(GameConstants.ROTATING_LINE_COLOR);
            }
            canvas.drawLine(r, 0, x1, 0, paint);
            canvas.restore();
        }
        canvas.restore();
    }
    public void move() {
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
