package com.anwesome.game.trispy.utils;

import android.content.Intent;

/**
 * Created by anweshmishra on 25/01/17.
 */
public interface GameNavigationalHandler {
    Intent getNavigationalIntent();
    void handleNavigation(Intent intent);
}
