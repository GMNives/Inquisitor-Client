// 
// Decompiled by Procyon v0.5.30
// 

package me.aristhena.client.module.modules.misc;

import net.minecraft.util.AxisAlignedBB;
import me.aristhena.client.module.Module;
import me.aristhena.client.module.Module.Mod;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.BoundingBoxEvent;
import net.minecraft.block.BlockCactus;

@Mod(displayName = "Anti Cactus")
public class AntiCactus extends Module
{
    @EventTarget
    private void onBoundingBox(final BoundingBoxEvent event) {
        if (event.getBlock() instanceof BlockCactus) {
            event.setBoundingBox(new AxisAlignedBB(event.getBlockPos().getX(), event.getBlockPos().getY(), event.getBlockPos().getZ(), event.getBlockPos().getX() + 1, event.getBoundingBox().maxY, event.getBlockPos().getZ() + 1));
        }
    }
}
