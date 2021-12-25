package me.aristhena.client.module.modules.movement;

import java.util.Arrays;
import java.util.List;

import me.aristhena.client.module.Module;
import me.aristhena.client.module.Module.Mod;
import me.aristhena.event.Event;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.UpdateEvent;
import me.aristhena.utils.ClientUtils;
import me.aristhena.utils.Timer;
import net.minecraft.block.Block;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

@Mod(displayName = "ScaffoldWalk")
public class Scaffold extends Module
{
    private List<Block> invalid;
    private Timer timer;
    private BlockData blockData;
    boolean placing;
    
    public Scaffold() {
        this.invalid = Arrays.asList(Blocks.air, Blocks.water, Blocks.fire, Blocks.flowing_water, Blocks.lava, Blocks.flowing_lava);
        this.timer = new Timer();
    }
    
    public void onEnable() {
        super.enable();
    }
    
    public void onDisable() {
        super.disable();
    }
    
    @EventTarget
    public void onUpdate(final UpdateEvent event) {
        if (event.getState() == Event.State.PRE) {
            this.blockData = null;
            if (!ClientUtils.player().isSneaking()) {
                final BlockPos blockBelow1 = new BlockPos(ClientUtils.player().posX, ClientUtils.player().posY - 1.0, ClientUtils.player().posZ);
                if (ClientUtils.world().getBlockState(blockBelow1).getBlock() == Blocks.air) {
                    this.blockData = this.getBlockData(blockBelow1, this.invalid);
                    if (this.blockData != null) {
                        final float yaw = this.aimAtLocation(this.blockData.position.getX(), this.blockData.position.getY(), this.blockData.position.getZ(), this.blockData.face)[0];
                        final float pitch = this.aimAtLocation(this.blockData.position.getX(), this.blockData.position.getY(), this.blockData.position.getZ(), this.blockData.face)[1];
                        event.setYaw(yaw);
                        event.setPitch(pitch);
                    }
                }
            }
        }
        if (event.getState() == Event.State.POST && this.blockData != null && this.timer.delay(75.0f)) {
            ClientUtils.mc().rightClickDelayTimer = 0;
            final int heldItem = ClientUtils.player().inventory.currentItem;
            for (int i = 0; i < 9; ++i) {
                if (ClientUtils.player().inventory.getStackInSlot(i) != null && ClientUtils.player().inventory.getStackInSlot(i).getItem() instanceof ItemBlock) {
                    ClientUtils.player().inventory.currentItem = i;
                    break;
                }
            }
            if (ClientUtils.playerController().func_178890_a(ClientUtils.player(), ClientUtils.world(), ClientUtils.player().getHeldItem(), this.blockData.position, this.blockData.face, new Vec3(this.blockData.position.getX(), this.blockData.position.getY(), this.blockData.position.getZ()))) {
                ClientUtils.packet(new C0APacketAnimation());
            }
            ClientUtils.player().inventory.currentItem = heldItem;
        }
    }
    
    private float[] aimAtLocation(final double x, final double y, final double z, final EnumFacing facing) {
        final EntitySnowball temp = new EntitySnowball(ClientUtils.world());
        temp.posX = x + 0.5;
        temp.posY = y - 2.7035252353;
        temp.posZ = z + 0.5;
        final EntitySnowball entitySnowball5;
        final EntitySnowball entitySnowball10;
        final EntitySnowball entitySnowball4 = entitySnowball10 = (entitySnowball5 = temp);
        entitySnowball10.posX += facing.getDirectionVec().getX() * 0.25;
        final EntitySnowball entitySnowball7;
        final EntitySnowball entitySnowball11;
        final EntitySnowball entitySnowball6 = entitySnowball11 = (entitySnowball7 = temp);
        entitySnowball11.posY += facing.getDirectionVec().getY() * 0.25;
        final EntitySnowball entitySnowball9;
        final EntitySnowball entitySnowball12;
        final EntitySnowball entitySnowball8 = entitySnowball12 = (entitySnowball9 = temp);
        entitySnowball12.posZ += facing.getDirectionVec().getZ() * 0.25;
        return this.aimAtLocation(temp.posX, temp.posY, temp.posZ);
    }
    
    private float[] aimAtLocation(final double positionX, final double positionY, final double positionZ) {
        final double x = positionX - ClientUtils.player().posX;
        final double y = positionY - ClientUtils.player().posY;
        final double z = positionZ - ClientUtils.player().posZ;
        final double distance = MathHelper.sqrt_double(x * x + z * z);
        return new float[] { (float)(Math.atan2(z, x) * 180.0 / 3.141592653589793) - 90.0f, (float)(-(Math.atan2(y, distance) * 180.0 / 3.141592653589793)) };
    }
    
    private BlockData getBlockData(final BlockPos pos, final List list) {
        return list.contains(ClientUtils.world().getBlockState(pos.add(0, -1, 0)).getBlock()) ? (list.contains(ClientUtils.world().getBlockState(pos.add(-1, 0, 0)).getBlock()) ? (list.contains(ClientUtils.world().getBlockState(pos.add(1, 0, 0)).getBlock()) ? (list.contains(ClientUtils.world().getBlockState(pos.add(0, 0, -1)).getBlock()) ? (list.contains(ClientUtils.world().getBlockState(pos.add(0, 0, 1)).getBlock()) ? null : new BlockData(pos.add(0, 0, 1), EnumFacing.NORTH)) : new BlockData(pos.add(0, 0, -1), EnumFacing.SOUTH)) : new BlockData(pos.add(1, 0, 0), EnumFacing.WEST)) : new BlockData(pos.add(-1, 0, 0), EnumFacing.EAST)) : new BlockData(pos.add(0, -1, 0), EnumFacing.UP);
    }
    
    private class BlockData
    {
        public BlockPos position;
        public EnumFacing face;
        
        public BlockData(final BlockPos position, final EnumFacing face) {
            this.position = position;
            this.face = face;
        }
    }
}
