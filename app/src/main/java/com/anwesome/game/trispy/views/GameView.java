package com.anwesome.game.trispy.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import com.anwesome.game.trispy.GameConstants;
import com.anwesome.game.trispy.OverActivity;
import com.anwesome.game.trispy.gameobjects.MenuBall;
import com.anwesome.game.trispy.runners.GameRunner;
import com.anwesome.game.trispy.utils.GameNavigationalHandler;
import com.anwesome.game.trispy.utils.SoundStateHandler;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by anweshmishra on 22/01/17.
 */
public class GameView extends SurfaceView{
    private Thread gameThread;
    private GameRunner runner;
    private InterstitialAd interstitialAd;
    public GameView(final Context context) {
        super(context);
        final SharedPreferences sharedPreferences = context.getSharedPreferences(GameConstants.SCORE_PREF,0);
        runner=new GameRunner(new SoundStateHandler(context),getHolder(),sharedPreferences);
        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId(GameConstants.AD_UNIT_ID);

        final int gamePlayed = sharedPreferences.getInt(GameConstants.GAME_PLAYED_KEY,0);
        if(gamePlayed%3 == 0) {
            final AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).addTestDevice("F1597CF42020B020DF5642D2953DAC89").build();
            interstitialAd.loadAd(adRequest);
        }
        runner.setNavigationHandler(new GameNavigationalHandler() {
            @Override
            public void handleNavigation(final Intent intent) {
                if(gamePlayed%3 ==0) {
                    ((Activity)context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(interstitialAd.isLoaded()) {
                                interstitialAd.setAdListener(new AdListener() {
                                    @Override
                                    public void onAdClosed() {
                                        super.onAdClosed();
                                        context.startActivity(intent);
                                    }
                                });
                                interstitialAd.show();
                            }
                        }
                    });
                }
                else {
                    context.startActivity(intent);
                }
            }
            @Override
            public Intent getNavigationalIntent() {
                return new Intent(context,OverActivity.class);
            }
        });
        updateGamePlayedInPref(sharedPreferences,gamePlayed);
        gameThread = new Thread(runner);
        gameThread.start();
    }
    public void pause() {
        runner.pause();
        while(true) {
            try{
                gameThread.join();
                break;
            }
            catch (Exception ex) {

            }
        }
    }
    public void resume() {
        runner.resume();
        gameThread = new Thread(runner);
        gameThread.start();
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            runner.handleTap(event.getX(),event.getY());
        }
        return true;
    }
    public void updateGamePlayedInPref(SharedPreferences sharedPreferences,int gamePlayed) {
        if(sharedPreferences!=null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(GameConstants.GAME_PLAYED_KEY,gamePlayed+1);
            editor.commit();
        }
    }
}
