/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.event.events;

import me.aristhena.event.Event;

public class UpdateEvent
extends Event {
    public Event.State state;
    public float yaw;
    public float pitch;
    private double y;
    private boolean onground;
    private boolean alwaysSend;
    public byte type;

    public UpdateEvent() {
        this.state = Event.State.POST;
    }

    public UpdateEvent(double y2, float yaw, float pitch, boolean ground) {
        this.state = Event.State.PRE;
        this.yaw = yaw;
        this.pitch = pitch;
        this.y = y2;
        this.onground = ground;
        this.type = 0;
    }

    public void type() {
        this.type = (byte)2;
    }

    public Event.State getState() {
        return this.state;
    }

    public float getYaw() {
        return this.yaw;
    }

    public float getPitch() {
        return this.pitch;
    }

    public double getY() {
        return this.y;
    }

    public boolean isOnground() {
        return this.onground;
    }

    public boolean shouldAlwaysSend() {
        return this.alwaysSend;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public void setY(double y2) {
        this.y = y2;
    }

    public void setGround(boolean ground) {
        this.onground = ground;
    }

    public void setAlwaysSend(boolean alwaysSend) {
        this.alwaysSend = alwaysSend;
    }
}

