package me.aristhena.utils.timer;

public class TimerUtil
{
    private long prevMS;
    private long currentMs;
    private long lastMs;
    private long previousTime;
    
    public TimerUtil() {
        this.currentMs = 0L;
        this.lastMs = -1L;
        this.prevMS = 0L;
    }
    
    public boolean hasDelay(final double d) {
        return this.getTime() - this.prevMS >= 1000.0 / d;
    }
    
    public void reset() {
        this.prevMS = this.getTime();
    }
    
    public long getTime() {
        return System.nanoTime() / 1000000L;
    }
    
    public boolean a(final long milliseconds) {
        return this.getCurrentMS() - this.prevMS >= milliseconds;
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
    
    public long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }
    
    public long getLastMS() {
        return this.lastMs;
    }
    
    public static long getCurrentTime() {
        return System.nanoTime() / 1000000L;
    }
    
    public boolean hasTimePassedM(final long MS) {
        return this.currentMs >= this.lastMs + MS;
    }
    
    public boolean hasReached(final long milliseconds) {
        return this.getCurrentMS() - this.lastMs >= milliseconds;
    }
    
    public boolean check(final float milliseconds) {
        return getCurrentTime() - this.previousTime >= milliseconds;
    }
    
    public boolean hasPassed(final double milli) {
        return this.getTime() - this.previousTime >= milli;
    }
    
    public boolean isDelayComplete(final long delay) {
        return System.currentTimeMillis() - this.lastMs >= delay;
    }
    
    public int convertToMS(final int perSecond) {
        return 1000 / perSecond;
    }
}
