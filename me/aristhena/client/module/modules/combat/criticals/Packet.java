package me.aristhena.client.module.modules.combat.criticals;

import me.aristhena.client.module.*;
import me.aristhena.client.module.modules.combat.Criticals;
import me.aristhena.event.events.*;
import me.aristhena.utils.*;
import net.minecraft.network.play.client.*;

public class Packet extends CriticalsMode
{
    public Packet(final String name, final boolean value, final Module module) {
        super(name, value, module);
    }
    
    public static void packet_crits() {
        ClientUtils.packet(new C03PacketPlayer.C04PacketPlayerPosition(ClientUtils.player().posX, ClientUtils.player().posY + 0.05, ClientUtils.player().posZ, false));
        ClientUtils.packet(new C03PacketPlayer.C04PacketPlayerPosition(ClientUtils.player().posX, ClientUtils.player().posY, ClientUtils.player().posZ, false));
        ClientUtils.packet(new C03PacketPlayer.C04PacketPlayerPosition(ClientUtils.player().posX, ClientUtils.player().posY + 0.012511, ClientUtils.player().posZ, false));
        ClientUtils.packet(new C03PacketPlayer.C04PacketPlayerPosition(ClientUtils.player().posX, ClientUtils.player().posY, ClientUtils.player().posZ, false));
    }
    
    @Override
    public boolean onPacketSend(final PacketSendEvent event) {
        if (Criticals.canCrit() && event.getPacket() instanceof C02PacketUseEntity) {
            final C02PacketUseEntity packet = (C02PacketUseEntity)event.getPacket();
            if (packet.getAction() == C02PacketUseEntity.Action.ATTACK) {
                packet_crits();
            }
        }
        return true;
    }
}
