package me.aristhena.utils;

import net.minecraft.entity.player.*;
import net.minecraft.world.pathfinder.*;
import java.util.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.pathfinding.*;
import net.minecraft.client.entity.*;

public class PathFind
{
    public EntityPlayer pos;
    public PathFinder pathFinder;
    private float yaw;
    public static float fakeYaw;
    public static float fakePitch;
    
    public PathFind(final String name) {
        this.pathFinder = new PathFinder((NodeProcessor)new WalkNodeProcessor());
        for (final Object i : ClientUtils.world().loadedEntityList) {
            if (i instanceof EntityPlayer && i != null) {
                final EntityPlayer player = (EntityPlayer)i;
                if (!player.getName().contains(name)) {
                    continue;
                }
                this.pos = player;
            }
        }
        if (this.pos != null) {
            this.move();
            final float[] rot = this.getRotationTo(this.pos.getPositionVector());
            PathFind.fakeYaw = rot[0];
            PathFind.fakePitch = rot[1];
        }
    }
    
    public void move() {
        if (ClientUtils.player().getDistance(this.pos.posX + 0.5, this.pos.posY + 0.5, this.pos.posZ + 0.5) > 0.3) {
            final PathEntity pe = this.pathFinder.func_176188_a((IBlockAccess)ClientUtils.world(), (Entity)ClientUtils.player(), (Entity)this.pos, 40.0f);
            if (pe != null && pe.getCurrentPathLength() > 1) {
                final PathPoint point = pe.getPathPointFromIndex(1);
                final float[] rot = this.getRotationTo(new Vec3(point.xCoord + 0.5, point.yCoord + 0.5, point.zCoord + 0.5));
                this.yaw = rot[0];
                final EntityPlayerSP player = ClientUtils.player();
                final EntityPlayerSP player2 = ClientUtils.player();
                final double n = 0.0;
                player2.motionZ = 0.0;
                player.motionX = 0.0;
                final double offset = 0.26;
                final double newx = Math.sin(this.yaw * 3.1415927f / 180.0f) * 0.26;
                final double newz = Math.cos(this.yaw * 3.1415927f / 180.0f) * 0.26;
                final EntityPlayerSP player5;
                final EntityPlayerSP player3 = player5 = ClientUtils.player();
                player5.motionX -= newx;
                final EntityPlayerSP player6;
                final EntityPlayerSP player4 = player6 = ClientUtils.player();
                player6.motionZ += newz;
                if (ClientUtils.player().onGround && ClientUtils.player().isCollidedHorizontally) {
                    ClientUtils.player().jump();
                }
            }
        }
    }
    
    public static float angleDifference(final float to, final float from) {
        return ((to - from) % 360.0f + 540.0f) % 360.0f - 180.0f;
    }
    
    public float[] getRotationTo(final Vec3 pos) {
        final double xD = ClientUtils.player().posX - pos.xCoord;
        final double yD = ClientUtils.player().posY + ClientUtils.player().getEyeHeight() - pos.yCoord;
        final double zD = ClientUtils.player().posZ - pos.zCoord;
        final double yaw = Math.atan2(zD, xD);
        final double pitch = Math.atan2(yD, Math.sqrt(Math.pow(xD, 2.0) + Math.pow(zD, 2.0)));
        return new float[] { (float)Math.toDegrees(yaw) + 90.0f, (float)Math.toDegrees(pitch) };
    }
}
