package me.aristhena.client.module.modules.combat.velocity;

import me.aristhena.client.module.*;
import me.aristhena.event.events.*;
import me.aristhena.utils.*;

public class AAC extends VelocityMode
{
    public AAC(final String name, final boolean value, final Module module) {
        super(name, value, module);
    }
    
    @Override
    public boolean onUpdate(final UpdateEvent event) {
        if (super.onUpdate(event) && !ClientUtils.player().onGround && ClientUtils.player().hurtTime != 0) {
            double yaw = ClientUtils.mc().thePlayer.rotationYawHead;
            yaw = Math.toRadians(yaw);
            final double motionX = -Math.sin(yaw) * 0.08;
            final double motionZ = Math.cos(yaw) * 0.08;
            ClientUtils.mc().thePlayer.motionX = motionX;
            ClientUtils.mc().thePlayer.motionZ = motionZ;
        }
        return true;
    }
}
