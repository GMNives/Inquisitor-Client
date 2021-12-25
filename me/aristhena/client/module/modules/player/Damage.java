package me.aristhena.client.module.modules.player;

import me.aristhena.client.module.Module;
import me.aristhena.client.module.Module.Mod;
import me.aristhena.client.option.Option;
import me.aristhena.utils.ClientUtils;
import net.minecraft.network.play.client.C03PacketPlayer;

@Mod(displayName = "Damage")
public class Damage extends Module
{
    @Option.Op(increment = 0.5, min = 0.5)
    private double damage;
    
    public Damage() {
        this.damage = 0.5;
    }
    
    @Override
    public void enable() {
        if (ClientUtils.player() != null) {
            for (int i = 0; i < 80.0 + 40.0 * (this.damage - 0.5); ++i) {
                ClientUtils.player().sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(ClientUtils.player().posX, ClientUtils.player().posY + 0.049, ClientUtils.player().posZ, false));
                ClientUtils.player().sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(ClientUtils.player().posX, ClientUtils.player().posY, ClientUtils.player().posZ, false));
            }
            ClientUtils.player().sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(ClientUtils.player().posX, ClientUtils.player().posY, ClientUtils.player().posZ, true));
        }
    }
}
