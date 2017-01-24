package com.anwesome.game.trispy.utils;

/**
 * Created by anweshmishra on 23/01/17.
 */
public class GameStateHandler {
    private int score = 0;
    private boolean isRunning = true,showScore = false,shouldShowOver = false,navigateTgGameOverMenu = false;
    private GameStateHandler() {

    }
    public boolean isShowScore() {
        return showScore;
    }
    public int getScore() {
        return score;
    }
    public static GameStateHandler newInstance() {
        return new GameStateHandler();
    }
    public void addScore() {
        score++;
        if(!showScore) {
            showScore = true;
        }
    }
    public void stopShowingScore() {
        if(showScore) {
            showScore = false;
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
    public void showOver() {
        if(!shouldShowOver) {
            shouldShowOver = true;
        }
    }
    public boolean isShouldShowOver() {
        return shouldShowOver;
    }
    public void startNavigating() {
        if(!navigateTgGameOverMenu) {
            navigateTgGameOverMenu = true;
        }
    }
    public boolean shouldNavigate() {
        return navigateTgGameOverMenu;
    }
}
