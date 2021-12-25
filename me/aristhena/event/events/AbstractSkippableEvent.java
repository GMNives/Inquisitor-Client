/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.event.events;

import me.aristhena.event.IEvent;

public abstract class AbstractSkippableEvent
implements IEvent {
    private /* synthetic */ boolean isSkipped;

    protected /* synthetic */ AbstractSkippableEvent() {
    }

    public /* synthetic */ boolean getCancelledState() {
        return this.isSkipped;
    }

    public /* synthetic */ void setSkip(boolean isCancelled) {
        this.isSkipped = isCancelled;
    }
}

