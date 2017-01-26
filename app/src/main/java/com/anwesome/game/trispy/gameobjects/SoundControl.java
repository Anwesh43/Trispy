package com.anwesome.game.trispy.gameobjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.anwesome.game.trispy.utils.SoundStateHandler;

/**
 * Created by anweshmishra on 26/01/17.
 */
public class SoundControl {
    private SoundStateHandler soundStateHandler;
    private float x,y,w=64,h=64;
    private int rendered = 0;

    public SoundControl(SoundStateHandler soundStateHandler) {
        this.soundStateHandler = soundStateHandler;
    }
    public void draw(Canvas canvas, Paint paint) {
        if(rendered == 0){
            x = canvas.getWidth()*19/20;
            y = canvas.getHeight()/20;
        }
        if(soundStateHandler!=null) {
            if(soundStateHandler.isPlaying()) {
                Bitmap soundBitmap = soundStateHandler.getSoundBitmap();
                w = soundBitmap.getWidth();
                h = soundBitmap.getHeight();
                canvas.drawBitmap(soundBitmap,x-w/2,y-h/2,paint);
            }
            else {
                Bitmap muteBitmap = soundStateHandler.getMuteBitmap();
                w = muteBitmap.getWidth();
                h = muteBitmap.getHeight();
                canvas.drawBitmap(muteBitmap,x-w/2,y-h/2,paint);
            }
        }
        rendered++;
    }
    private void toggleSoundState() {
        if(soundStateHandler!=null && soundStateHandler.isPlaying()) {
            soundStateHandler.pause();
        }
        else if(soundStateHandler!=null && !soundStateHandler.isPlaying()){
            soundStateHandler.start();
        }
    }
    public boolean containsTouch(float x,float y) {
        boolean contains = x>=this.x -w/2 && x<=this.x+w/2 && y>=this.y-h/2 && y<=this.y+h/2;
        if(contains) {
            toggleSoundState();
        }
        return contains;
    }
}
