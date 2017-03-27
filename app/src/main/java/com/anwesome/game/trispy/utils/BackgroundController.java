package com.anwesome.game.trispy.utils;

import android.app.KeyguardManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.anwesome.game.trispy.GameConstants;

/**
 * Created by anweshmishra on 27/03/17.
 */
public class BackgroundController {
    private int index = 0;
    public void fetchNextColor() {
        index++;
        index%=GameConstants.GAME_BACK_COLORS.length;
    }
    private BackgroundController() {
    }
    public static BackgroundController getInstance() {
        return  new BackgroundController();
    }
    public void drawBackground(Canvas canvas, Paint paint) {
        canvas.drawColor(Color.parseColor(GameConstants.GAME_BACK_COLORS[index]));
    }
}
