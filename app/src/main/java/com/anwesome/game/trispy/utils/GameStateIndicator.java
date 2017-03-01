package com.anwesome.game.trispy.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.anwesome.game.trispy.GameConstants;

/**
 * Created by anweshmishra on 23/01/17.
 */
public class GameStateIndicator {
    private GameStateHandler gameStateHandler;
    private Canvas canvas;
    private int showScoreTime = 0,maxScore=6,showOver=0,maxOver = 10;
    private GameStateIndicator(Canvas canvas,GameStateHandler gameStateHandler) {
        this.gameStateHandler = gameStateHandler;
        this.canvas = canvas;
    }
    public static GameStateIndicator newInstance(Canvas canvas,GameStateHandler gameStateHandler) {
        return new GameStateIndicator(canvas,gameStateHandler);
    }
    public void drawScore(Paint paint){
        if(gameStateHandler.isShowScore()) {
            String text = "" + gameStateHandler.getScore();
            paint.setTextSize(canvas.getWidth() / 8);
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawText(text, canvas.getWidth() / 2 - paint.measureText(text) / 2, canvas.getHeight() / 8 - paint.getTextSize() / 2, paint);
            showScoreTime++;
            if(showScoreTime == maxScore) {
                gameStateHandler.stopShowingScore();
                showScoreTime = 0;
            }
        }
    }
    public void drawOver(Paint paint) {
        if(gameStateHandler.isShouldShowOver()) {
            paint.setTextSize(canvas.getWidth() / 9);
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawText(GameConstants.GAME_OVER_TEXT,canvas.getWidth()/2-paint.measureText(GameConstants.GAME_OVER_TEXT)/2,canvas.getHeight()/2-paint.getTextSize()/2,paint);
            showOver++;
            if(showOver == maxOver) {
                gameStateHandler.startNavigating();
            }
        }
    }
}
