package me.aristhena.client.module.modules.combat.velocity;

import me.aristhena.client.module.*;
import me.aristhena.event.events.*;
import net.minecraft.network.play.server.*;

public class NCP extends VelocityMode
{
    public NCP(final String name, final boolean value, final Module module) {
        super(name, value, module);
    }
    
    @Override
    public boolean onPacketReceiveEvent(final PacketReceiveEvent event) {
        if (super.onPacketReceiveEvent(event) && event.getPacket() instanceof S12PacketEntityVelocity) {
            event.setCancelled(true);
        }
        return true;
    }
}
