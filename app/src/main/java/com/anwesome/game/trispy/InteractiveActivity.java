package com.anwesome.game.trispy;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.WindowManager;

import com.anwesome.game.trispy.views.InteractiveView;

/**
 * Created by anweshmishra on 24/01/17.
 */
public class InteractiveActivity extends AppCompatActivity{
    protected int w,h;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDimensions();
        createInteractiveView();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    private void initDimensions() {
        DisplayManager displayManager = (DisplayManager)getSystemService(Context.DISPLAY_SERVICE);
        Display display = displayManager.getDisplay(0);
        Point size = new Point();
        display.getRealSize(size);
        if(display!=null) {
            w = size.x;
            h = size.y;
        }
    }
    protected void createMenus(InteractiveView interactiveView){

    }
    private void createInteractiveView() {
        InteractiveView interactiveView = new InteractiveView(this);
        createMenus(interactiveView);
        setContentView(interactiveView);
    }
    public void onBackPressed() {

    }
    public void onPause() {
        super.onPause();
    }
    public void onResume() {
        super.onResume();
    }
}
