package com.anwesome.game.trispy.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.anwesome.game.trispy.GameAcivity;
import com.anwesome.game.trispy.GameConstants;
import com.anwesome.game.trispy.gameobjects.MovingBall;
import com.anwesome.game.trispy.gameobjects.Ring;
import com.anwesome.game.trispy.gameobjects.RotatingLine;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 27/02/17.
 */
public class InstructionController {
    private int time = 0,ntime = 0;
    private boolean isStop = false;
    private InstructionModeStore store = new InstructionModeStore();
    private int w;
    private RotatingLine rotatingLine;
    private MovingBall movingBall;
    private ConcurrentLinkedQueue<Ring> rings = new ConcurrentLinkedQueue<>();
    private String[] instructions = {"Tap Anywhere To Rotate The Wheel","Good!","The Ball Color Must Match The Ring color","Excellent!","Tap Anywhere to start the game"};
    public void drawInstruction(Canvas canvas, Paint paint) {
        if(time == 0) {
            int w = canvas.getWidth(),h = canvas.getHeight();
            rotatingLine = RotatingLine.newInstance();
            rings = GameCreateUtil.createRings();
            int color = GameConstants.colors[1];
            if(rings.size() >= 2) {
                int index = 0;
                for(Ring ring:rings) {
                    if(index == 1) {
                        color = ring.getColor();
                        break;
                    }
                    index++;
                }
            }
            movingBall = MovingBall.newInstance(0,90,color);
            movingBall.setEdge(2*w/GameConstants.RING_RADIUS_SCALE+w/GameConstants.LINE_SCALE);
        }
        int deg = 0,color = GameConstants.ROTATING_LINE_COLOR;
        if(store.getMode() == 2) {
            deg = (int)movingBall.getDeg();
            color = movingBall.getColor();
        }
        rotatingLine.draw(canvas,paint, color,deg);

        for(Ring ring:rings) {
            ring.draw(canvas,paint);
        }
        w = canvas.getWidth();
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(canvas.getHeight()/20);
        paint.setColor(Color.WHITE);
        drawText(canvas,paint);
        if(store.getMode() == 1) {
            rotatingLine.move();
            if (rotatingLine.getRot() %360  == 0) {
                store.setMode(2);
                rotatingLine.setRot(90);
            }
        }
       else  if(store.getMode() == 2) {
            movingBall.draw(canvas,paint);
            movingBall.move();
            if(movingBall.isAtEdge()) {
                store.setMode(3);
            }
        }
        else if(store.getMode() == 3) {
            ntime++;
            if(ntime == 10) {
                ntime = 0;
                store.setMode(store.getMode()+1);
            }
        }
        else if(store.getMode() == 4) {
            ntime++;
            if(ntime == 5) {
                isStop = true;
            }
        }
        time++;
    }
    public boolean isStop() {
        return isStop;
    }
    private void drawText(Canvas canvas,Paint paint) {
        String msg = "";
        float widthLimit = 7*canvas.getWidth()/10,x=canvas.getWidth()/2;
        String tokens[] = instructions[store.getMode()].split(" ");
        float y = canvas.getHeight()/20;
        for(String token:tokens) {
            if(paint.measureText(msg+" "+token)>widthLimit) {
                canvas.drawText(msg,x-paint.measureText(msg)/2,y,paint);
                msg = token;
                y+=paint.getTextSize();
            }
            else {
                msg = msg+" "+token;
            }
        }
        canvas.drawText(msg,x-paint.measureText(msg)/2,y,paint);

    }
    public void handleTap(Activity activity) {
        if(store.getMode() == 0) {
            rotatingLine.handleTap();
            store.setMode(1);
        }
        if(store.getMode() == instructions.length-1) {
            Intent intent = new Intent(activity,GameAcivity.class);
            activity.startActivity(intent);
        }
    }
}
