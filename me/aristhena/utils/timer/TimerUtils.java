package me.aristhena.utils.timer;

import java.util.*;

public class TimerUtils
{
    private static long prevMS;
    private long currentMs;
    private long lastMs;
    
    public TimerUtils() {
        this.currentMs = 0L;
        this.lastMs = -1L;
        TimerUtils.prevMS = 0L;
    }
    
    public static boolean hD(final double d) {
        return getTime() - TimerUtils.prevMS >= 1000.0 / d;
    }
    
    public static boolean hasRandomDelay(final double d, final Random rand) {
        return getTime() - TimerUtils.prevMS >= 1000.0 / d;
    }
    
    public static void rt() {
        TimerUtils.prevMS = getTime();
    }
    
    public static long getTime() {
        return System.nanoTime() / 1000000L;
    }
    
    public void updateMs() {
        this.currentMs = System.currentTimeMillis();
    }
    
    public void setLastMs() {
        this.lastMs = System.currentTimeMillis();
    }
    
    public boolean hasPassed(final long Ms) {
        return this.currentMs > this.lastMs + Ms;
    }
    
    public static boolean hasReached(final long milliseconds) {
        return getTime() - TimerUtils.prevMS >= 1000.0f / milliseconds;
    }
}
