package com.anwesome.game.trispy.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

import com.anwesome.game.trispy.GameConstants;
import com.anwesome.game.trispy.utils.InstructionController;

/**
 * Created by anweshmishra on 27/02/17.
 */
public class InstructionView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private InstructionController controller = new InstructionController();
    private Activity activity;
    private Typeface typeface;
    public InstructionView(Context context) {
        super(context);
        this.activity = (Activity)context;
        typeface = Typeface.createFromAsset(context.getAssets(),"cucumber.ttf");
    }

    public void onDraw(Canvas canvas) {
        paint.setTypeface(typeface);
        canvas.drawColor(GameConstants.BACK_COLOR);
        controller.drawInstruction(canvas,paint);
        if(!controller.isStop()) {
            try {
                Thread.sleep(GameConstants.GAME_DELAY);
                invalidate();
            } catch (Exception ex) {
            }
        }

    }

    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            controller.handleTap(activity);
        }
        return true;
    }
}
