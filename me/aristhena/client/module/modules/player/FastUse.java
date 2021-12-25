/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.client.module.modules.player;

import me.aristhena.client.module.Module;
import me.aristhena.client.option.Option;
import me.aristhena.event.Event;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.UpdateEvent;
import me.aristhena.utils.ClientUtils;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

@Module.Mod(displayName="FastUse")
public class FastUse
extends Module {
    @Option.Op(min=1.0, max=50.0, increment=2.0)
    private static double speed = 17.0;

    @EventTarget
    private void onUpdate(UpdateEvent event) {
        if (event.getState() == Event.State.PRE && ClientUtils.player().getItemInUseDuration() == 16 && !(ClientUtils.player().getItemInUse().getItem() instanceof ItemBow) && !(ClientUtils.player().getItemInUse().getItem() instanceof ItemSword)) {
            int i2 = 0;
            while ((double)i2 < speed) {
                ClientUtils.packet(new C03PacketPlayer(true));
                ++i2;
            }
            ClientUtils.packet(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
        }
    }
}

