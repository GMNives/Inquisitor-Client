/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.client.module.modules.auto;

import java.util.Random;
import me.aristhena.client.module.Module;
import me.aristhena.client.option.Option;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.UpdateEvent;
import me.aristhena.utils.ClientUtils;
import net.minecraft.item.ItemBow;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

@Module.Mod
public class AutoBow
extends Module {
    @Option.Op
    public boolean vanilla;
    Random random = new Random();

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        if (ClientUtils.mc().thePlayer.getItemInUse().getItem() instanceof ItemBow && ClientUtils.mc().thePlayer.getItemInUseDuration() == 16) {
            if (this.vanilla) {
                for (int index = 0; index < 20 + this.random.nextInt(1642); ++index) {
                    ClientUtils.mc().thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C06PacketPlayerPosLook(ClientUtils.mc().thePlayer.posX, ClientUtils.mc().thePlayer.posY + 1.0E-9, ClientUtils.mc().thePlayer.posZ, ClientUtils.mc().thePlayer.rotationYaw, ClientUtils.mc().thePlayer.rotationPitch, true));
                }
            } else {
                for (int i = 0; i < 5; ++i) {
                    ClientUtils.mc().thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
                }
                ClientUtils.mc().thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
                ClientUtils.mc().thePlayer.stopUsingItem();
            }
        }
    }
}

