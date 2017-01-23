package com.anwesome.game.trispy.utils;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 23/01/17.
 */
public class GameStateIndicator {
    private GameStateHandler gameStateHandler;
    private GameStateIndicator(GameStateHandler gameStateHandler) {
        this.gameStateHandler = gameStateHandler;
    }
    public static GameStateIndicator newInstance(GameStateHandler gameStateHandler) {
        return new GameStateIndicator(gameStateHandler);
    }
}
