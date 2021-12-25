package me.aristhena.client.module.modules.combat.criticals;

import me.aristhena.client.module.*;
import me.aristhena.client.module.modules.combat.Criticals;
import me.aristhena.event.events.*;
import me.aristhena.utils.*;
import net.minecraft.network.play.client.*;

public class Jump extends CriticalsMode
{
    public Jump(final String name, final boolean value, final Module module) {
        super(name, value, module);
    }
    
    @Override
    public boolean onPacketSend(final PacketSendEvent event) {
        if (Criticals.canCrit() && event.getPacket() instanceof C02PacketUseEntity) {
            final C02PacketUseEntity packet = (C02PacketUseEntity)event.getPacket();
            if (packet.getAction() == C02PacketUseEntity.Action.ATTACK) {
                ClientUtils.player().jump();
            }
        }
        return true;
    }
}
