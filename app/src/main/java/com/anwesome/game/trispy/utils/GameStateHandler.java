package com.anwesome.game.trispy.utils;

/**
 * Created by anweshmishra on 23/01/17.
 */
public class GameStateHandler {
    private int score = 0;
    private boolean isRunning = true;
    private GameStateHandler() {

    }
    public int getScore() {
        return score;
    }
    public static GameStateHandler newInstance() {
        return new GameStateHandler();
    }
    public void addScore() {
        score++;
    }
    public void end() {
        if(!isRunning) {
            isRunning = true;
        }
    }
    public void pause() {
        if(isRunning) {
            isRunning = false;
        }
    }
    public boolean shouldRender() {
        return isRunning;
    }
    public void resume() {
        if(!isRunning) {
            isRunning = true;
        }
    }
}
