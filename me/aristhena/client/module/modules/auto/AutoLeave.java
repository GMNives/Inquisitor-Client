package me.aristhena.client.module.modules.auto;

import me.aristhena.client.module.*;
import me.aristhena.client.option.*;
import me.aristhena.event.events.*;
import me.aristhena.utils.*;
import net.minecraft.potion.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import me.aristhena.event.*;
import net.minecraft.world.*;
import net.minecraft.util.*;
import net.minecraft.block.*;

@Module.Mod
public class AutoLeave extends Module
{
    private double packets;
    private double potionPackets;
    @Option.Op(name = "Health", min = 0.0, max = 20.0, increment = 0.5)
    private double health;
    private boolean potion;
    
    public AutoLeave() {
        this.packets = 6000.0;
        this.potionPackets = 0.4;
        this.health = 7.0;
        this.potion = false;
    }
    
    @EventTarget
    private void onUpdate(final UpdateEvent event) {
        if (this.potion) {
            if (ClientUtils.player().getActivePotionEffect(Potion.regeneration) != null && ClientUtils.player().getActivePotionEffect(Potion.regeneration).getDuration() > 0 && ClientUtils.player().getHealth() <= this.health * 2.0 && (ClientUtils.player().isCollidedVertically || this.isInsideBlock()) && event.getState().equals(Event.State.POST)) {
                for (int i = 0; i < this.potionPackets; ++i) {
                    ClientUtils.player().getActivePotionEffect(Potion.regeneration).deincrementDuration();
                    ClientUtils.packet(new C03PacketPlayer(true));
                }
            }
        }
        else if (ClientUtils.player().getHealth() <= this.health * 2.0 && (ClientUtils.player().isCollidedVertically || this.isInsideBlock()) && event.getState().equals(Event.State.POST)) {
            for (int i = 0; i < this.packets; ++i) {
                ClientUtils.packet(new C03PacketPlayer(true));
            }
        }
    }
    
    private boolean isInsideBlock() {
        for (int x = MathHelper.floor_double(ClientUtils.player().boundingBox.minX); x < MathHelper.floor_double(ClientUtils.player().boundingBox.maxX) + 1; ++x) {
            for (int y = MathHelper.floor_double(ClientUtils.player().boundingBox.minY); y < MathHelper.floor_double(ClientUtils.player().boundingBox.maxY) + 1; ++y) {
                for (int z = MathHelper.floor_double(ClientUtils.player().boundingBox.minZ); z < MathHelper.floor_double(ClientUtils.player().boundingBox.maxZ) + 1; ++z) {
                    final Block block = ClientUtils.world().getBlockState(new BlockPos(x, y, z)).getBlock();
                    if (block != null && !(block instanceof BlockAir)) {
                        AxisAlignedBB boundingBox = block.getCollisionBoundingBox(ClientUtils.world(), new BlockPos(x, y, z), ClientUtils.world().getBlockState(new BlockPos(x, y, z)));
                        if (block instanceof BlockHopper) {
                            boundingBox = new AxisAlignedBB(x, y, z, x + 1, y + 1, z + 1);
                        }
                        if (boundingBox != null && ClientUtils.player().boundingBox.intersectsWith(boundingBox)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
