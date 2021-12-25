package me.aristhena.event.events;

import inquisitor.eventapi.events.Event;

public class EventRender
implements Event {
    private float partialTicks;

    public EventRender(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return this.partialTicks;
    }
}

