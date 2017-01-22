package com.anwesome.game.trispy.utils;

import com.anwesome.game.trispy.GameConstants;
import com.anwesome.game.trispy.gameobjects.Ring;

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
    private static int getRandomIndex(Map<Integer,Ring> ringMap) {
        Random random = new Random();
        int index = random.nextInt(GameConstants.colors.length);
        if(ringMap.containsKey(index)) {
            return getRandomIndex(ringMap);
        }
        return index;
    }
}
