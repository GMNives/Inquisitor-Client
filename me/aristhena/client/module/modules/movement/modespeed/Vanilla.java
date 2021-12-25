// 
// Decompiled by Procyon v0.5.30
// 

package me.aristhena.client.module.modules.movement.modespeed;

import me.aristhena.client.module.Module;
import me.aristhena.client.module.modules.movement.Speed;
import me.aristhena.event.events.MoveEvent;
import me.aristhena.utils.ClientUtils;

public class Vanilla extends SpeedMode
{
    public Vanilla(final String name, final boolean value, final Module module) {
        super(name, value, module);
    }
    
    @Override
    public boolean onMove(final MoveEvent event) {
        if (super.onMove(event) && ClientUtils.player().onGround) {
            ClientUtils.setMoveSpeed(event, ((Speed)this.getModule()).speed);
        }
        return true;
    }
}
