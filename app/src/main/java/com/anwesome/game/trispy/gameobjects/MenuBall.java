package com.anwesome.game.trispy.gameobjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.anwesome.game.trispy.GameConstants;

/**
 * Created by anweshmishra on 23/01/17.
 */
public class MenuBall {
    private Bitmap icon;
    private int radiusScale= GameConstants.RING_RADIUS_SCALE;
    private int color = Color.WHITE;
    private int x,y,deg,radius = 10,time = 0;
    private NavigationHandler navigationHandler;
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    private MenuBall(Bitmap icon, int radiusScale, int color) {
        this.icon = icon;
        this.radiusScale = radiusScale;
        this.color = color;
    }
    public static MenuBall newInstance(Bitmap icon,int radiusScale,int color) {
        return new MenuBall(icon,radiusScale,color);
    }
    public void draw(Canvas canvas, Paint paint) {
        if(time == 0) {
            radius = canvas.getWidth()/radiusScale;
            int iconW = radius,iconH = radius;
            icon = Bitmap.createScaledBitmap(icon,iconW,iconH,true);
        }
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(GameConstants.STROKE_SIZE);
        canvas.drawCircle(x,y,radius,paint);
        canvas.drawBitmap(icon,x-radius/2,y-radius/2,paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);

        time++;
    }
    public boolean containsTouch(float x,float y) {
        return x>=this.x -radius && x<=this.x+radius && y>=this.y-radius && y<=this.y+radius;
    }

    public NavigationHandler getNavigationHandler() {
        return navigationHandler;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getRadiusScale() {
        return radiusScale;
    }

    public void setRadiusScale(int radiusScale) {
        this.radiusScale = radiusScale;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public int hashCode() {
        return color+x+y+deg;

    }
    public void setNavigationHandler(NavigationHandler navigationHandler) {
        this.navigationHandler = navigationHandler;
    }
    public interface  NavigationHandler {
        void handleNavigation();
    }
}
