package com.anwesome.game.trispy;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.anwesome.game.trispy.views.InstructionView;

/**
 * Created by anweshmishra on 27/02/17.
 */
public class InstructionActivity extends Activity {
    private InstructionView instructionView;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        instructionView = new InstructionView(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(instructionView);
    }
}
