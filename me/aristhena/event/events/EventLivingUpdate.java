package me.aristhena.event.events;

import inquisitor.eventapi.events.Event;
import net.minecraft.entity.Entity;

public class EventLivingUpdate implements Event{
    private Entity entity;
    public EventLivingUpdate(Entity entity) {
        super();
        this.entity = entity;
    }
    
    public Entity getEntity() {
        return this.entity;
    }
}
