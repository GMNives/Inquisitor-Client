/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.client.module.modules.player;

import me.aristhena.client.module.Module;
import me.aristhena.client.option.Option;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.TickEvent;
import me.aristhena.utils.ClientUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.network.play.client.C03PacketPlayer;

@Module.Mod(displayName="Fast Pickup")
public class FastPickup
extends Module {
    @Option.Op(min=10.0, max=1000.0, increment=10.0)
    private static double packets = 200.0;

    public Entity getItems(double range) {
        Entity tempEntity = null;
        double dist = range;
        ClientUtils.mc();
        for (Object i2 : Minecraft.theWorld.loadedEntityList) {
            double curDist;
            Entity entity = (Entity)i2;
            if (!ClientUtils.mc().thePlayer.onGround || !ClientUtils.mc().thePlayer.isCollidedVertically || !(entity instanceof EntityItem) || !((curDist = (double)ClientUtils.mc().thePlayer.getDistanceToEntity(entity)) <= dist)) continue;
            dist = curDist;
            tempEntity = entity;
        }
        return tempEntity;
    }

    @EventTarget
    public void onPreTick(TickEvent e2) {
        Entity items;
        if (this.isEnabled() && (items = this.getItems(1.0)) != null) {
            int i2 = 0;
            while ((double)i2 < packets) {
                ClientUtils.mc().thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer());
                ++i2;
            }
        }
    }
}

