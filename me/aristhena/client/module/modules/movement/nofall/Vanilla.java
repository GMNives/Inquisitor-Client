package me.aristhena.client.module.modules.movement.nofall;

import net.minecraft.network.play.client.*;
import me.aristhena.client.module.*;
import me.aristhena.event.events.*;
import me.aristhena.utils.*;
import net.minecraft.network.*;

public class Vanilla extends NoFallMode
{
    public Vanilla(final String name, final boolean value, final Module module) {
        super(name, value, module);
    }
    
    @Override
    public boolean onUpdate(final UpdateEvent event) {
        if (super.onUpdate(event) && ClientUtils.mc().thePlayer.fallDistance >= 2.0f) {
            ClientUtils.mc().thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
        }
        return true;
    }
}
