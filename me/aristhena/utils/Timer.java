// 
// Decompiled by Procyon v0.5.30
// 

package me.aristhena.utils;

public class Timer
{
    public static float timerSpeed;
    private long prevMS = 0L;
	protected long lastMS = -1L;
	private long previousTime;
    private long currentMS = 0L;
    
    public Timer() {
        this.prevMS = 0L;
    }
    
    public boolean delay(final float milliSec) {
        return this.getTime() - this.prevMS >= milliSec;
    }
    
    public void reset() {
        this.prevMS = this.getTime();
    }
    
    public long getTime() {
        return System.nanoTime() / 1000000L;
    }
    
    public long getDifference() {
        return this.getTime() - this.prevMS;
    }
    
    public void setDifference(final long difference) {
        this.prevMS = this.getTime() - difference;
    }

    public boolean isDelayCompleteParadox(float delay) {
        return (float)(System.currentTimeMillis() - this.lastMS) >= delay;
    }

    public boolean hasReached(long milliseconds) {
        return this.getCurrentMS() - this.lastMS >= milliseconds;
    }

    public boolean hasReached(float milliseconds) {
        return (float)(this.getCurrentMS() - this.lastMS) >= milliseconds;
    }

    public boolean hasReached(Float milliseconds) {
        return (float)(this.getCurrentMS() - this.lastMS) >= milliseconds.floatValue();
    }

    public long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }

	public void setLastMS(long currentMS) {
        this.lastMS = currentMS;
    }
}
