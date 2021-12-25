package me.aristhena.utils.timer;

public class TimeHelper
{
    private long lastMS;
    private long prevTime;
    
    public long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }
    
    public long getLastMS() {
        return this.lastMS;
    }
    
    public boolean hasPassed(final double milli) {
        return this.getTime() - this.prevTime >= milli;
    }
    
    public boolean hasReached(final long milliseconds) {
        return this.getCurrentMS() - this.lastMS >= milliseconds;
    }
    
    public long getTime() {
        return this.getCurrentMS() - this.lastMS;
    }
    
    public void reset() {
        this.lastMS = this.getCurrentMS();
    }
    
    public void setLastMS(final long currentMS) {
        this.lastMS = currentMS;
    }
}
