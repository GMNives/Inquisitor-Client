package me.aristhena.client.module.modules.combat;

import me.aristhena.client.module.Module;
import me.aristhena.client.module.Module.Mod;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.PacketSendEvent;
import me.aristhena.utils.ClientUtils;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C0BPacketEntityAction;

@Mod
public class ExtraKnockBack extends Module
{
    @EventTarget
    public void onLatest(final PacketSendEvent event) {
        if (event.getPacket() instanceof C02PacketUseEntity) {
            final C02PacketUseEntity packet = (C02PacketUseEntity)event.getPacket();
            if (packet.getAction() == C02PacketUseEntity.Action.ATTACK && ClientUtils.player().isSwingInProgress) {
                ClientUtils.mc().thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(ClientUtils.mc().thePlayer, C0BPacketEntityAction.Action.START_SPRINTING));
                ClientUtils.mc().thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(ClientUtils.mc().thePlayer, C0BPacketEntityAction.Action.STOP_SPRINTING));
                ClientUtils.mc().thePlayer.sendQueue.addToSendQueue(new C0BPacketEntityAction(ClientUtils.mc().thePlayer, C0BPacketEntityAction.Action.START_SPRINTING));
            }
        }
    }
}
