package com.anwesome.game.trispy.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.util.Log;

import com.anwesome.game.trispy.GameConstants;
import com.anwesome.game.trispy.R;

/**
 * Created by anweshmishra on 26/01/17.
 */
public class SoundStateHandler {
    private MediaPlayer mediaPlayer;
    private int seekTime = 0;
    private boolean isPlaying = false;
    private Bitmap soundBitmap,muteBitmap;
    private boolean sourceLoaded = false;
    public Bitmap getMuteBitmap() {
        return muteBitmap;
    }

    public void setMuteBitmap(Bitmap muteBitmap) {
        this.muteBitmap = muteBitmap;
    }

    public Bitmap getSoundBitmap() {
        return soundBitmap;
    }

    public void setSoundBitmap(Bitmap soundBitmap) {
        this.soundBitmap = soundBitmap;
    }

    public SoundStateHandler(Context context) {
        soundBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sound);
        muteBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.mute_black);
        mediaPlayer = new MediaPlayer();
        try {
            AssetFileDescriptor fd = context.getAssets().openFd(GameConstants.BACKGROUND_SOUND_FILE);
            mediaPlayer.setDataSource(fd.getFileDescriptor());

            sourceLoaded = true;
        }
        catch (Exception ex) {
            Log.d("exception",ex.toString());
        }
    }
    public void start() {
        if(!isPlaying) {
            try {
                mediaPlayer.prepare();
                mediaPlayer.start();
                mediaPlayer.setVolume(0, GameConstants.BACKGROUND_SOUND_VOLUME);
                mediaPlayer.seekTo(seekTime);
                mediaPlayer.setLooping(true);
                isPlaying = true;
            }
            catch (Exception ex) {

            }

        }

    }
    public void pause() {
        if(isPlaying && mediaPlayer.isPlaying()) {
            seekTime = mediaPlayer.getCurrentPosition();
            mediaPlayer.stop();
            isPlaying = false;
        }
    }
    public boolean isPlaying() {
        return isPlaying;
    }
}
