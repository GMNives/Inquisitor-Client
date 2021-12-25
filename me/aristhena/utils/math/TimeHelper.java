/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.utils.math;

public class TimeHelper {
    private long prevMS = 0L;
    private long lastMS;

    public boolean delay(float milliSec) {
        return (float)(this.getTime() - this.prevMS) >= milliSec;
    }

    public void reset() {
        this.prevMS = this.getTime();
    }

    public boolean hasReached(long milliseconds) {
        return this.getCurrentMS() - this.lastMS >= milliseconds;
    }

    public long getTime() {
        return System.nanoTime() / 1000000L;
    }

    public long getDifference() {
        return this.getTime() - this.prevMS;
    }

    public void setDifference(long difference) {
        this.prevMS = this.getTime() - difference;
    }

    public boolean isDelayComplete(long delay) {
        return System.currentTimeMillis() - this.lastMS >= delay;
    }

    public void setLastMS(long lastMS) {
        this.lastMS = lastMS;
    }

    public void setLastMS() {
        this.lastMS = System.currentTimeMillis();
    }

    public int convertToMS(int perSecond) {
        return 1000 / perSecond;
    }

    public long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }
}

