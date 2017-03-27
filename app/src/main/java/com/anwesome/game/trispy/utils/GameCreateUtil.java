package com.anwesome.game.trispy.utils;

import com.anwesome.game.trispy.GameConstants;
import com.anwesome.game.trispy.gameobjects.MovingBall;
import com.anwesome.game.trispy.gameobjects.Ring;
import com.anwesome.game.trispy.gameobjects.RotatingLine;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 22/01/17.
 */
public class GameCreateUtil {
    public static ConcurrentLinkedQueue<Ring> createRings() {
        int numberOfBalls = 4;
        ConcurrentLinkedQueue<Ring> rings = new ConcurrentLinkedQueue<>();
        int gap = 360/numberOfBalls,startDeg =0;
        Map<Integer,Ring> ringMap = new HashMap<>();
        for(int i=0;i<numberOfBalls;i++) {
            int colorIndex = getRandomIndex(ringMap);
            Ring ring = Ring.newInstance(startDeg,GameConstants.colors[colorIndex]);
            rings.add(ring);
            ringMap.put(colorIndex,ring);
            startDeg+=gap;
        }
        return rings;
    }
    public static MovingBall createBall(float deg,int canvasWidth) {
        Random random = new Random();
        int colorIndex = random.nextInt(GameConstants.colors.length);
        int color = GameConstants.colors[colorIndex];
        MovingBall movingBall = MovingBall.newInstance(0,deg,color);
        movingBall.setEdge((int)(2*canvasWidth/GameConstants.RING_RADIUS_SCALE+canvasWidth/GameConstants.LINE_SCALE));
        return movingBall;
    }
    private static int getRandomIndex(Map<Integer,Ring> ringMap) {
        Random random = new Random();
        int index = random.nextInt(GameConstants.colors.length);
        if(ringMap.containsKey(index)) {
            return getRandomIndex(ringMap);
        }
        return index;
    }
    public static void createMovingBallForLevel(int time, int level, int w,RotatingLine rotatingLine,ConcurrentLinkedQueue<MovingBall> balls) {
        if((time)%(90-Math.min(level,4)*10) == 0) {
            Random random = new Random();
            int rotIndex = random.nextInt(4);
            float rot = rotatingLine.getRot()+rotIndex*90;
            rot%=360;
            balls.add(createBall(rot,w));

        }
    }
}
