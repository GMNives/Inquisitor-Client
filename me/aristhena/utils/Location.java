/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.utils;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;

public class Location
{
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    
    public Location(final double x, final double y, final double z, final float yaw, final float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }
    
    public Location(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = 0.0f;
        this.pitch = 0.0f;
    }
    
    public Location(final BlockPos pos) {
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
        this.yaw = 0.0f;
        this.pitch = 0.0f;
    }
    
    public Location(final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = 0.0f;
        this.pitch = 0.0f;
    }
    
    public Location(final EntityLivingBase entity) {
        this.x = entity.posX;
        this.y = entity.posY;
        this.z = entity.posZ;
        this.yaw = 0.0f;
        this.pitch = 0.0f;
    }
    
    public Location(final Entity entity) {
        this.x = entity.posX;
        this.y = entity.posY;
        this.z = entity.posZ;
        this.yaw = 0.0f;
        this.pitch = 0.0f;
    }
    
    public Location add(final int x, final int y, final int z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }
    
    public Location add(final double x, final double y, final double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }
    
    public Location subtract(final int x, final int y, final int z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }
    
    public Location subtract(final double x, final double y, final double z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }
    
    public Block getBlock() {
        Minecraft.getMinecraft();
        return Minecraft.theWorld.getBlockState(this.toBlockPos()).getBlock();
    }
    
    public double getX() {
        return this.x;
    }
    
    public Location setX(final double x) {
        this.x = x;
        return this;
    }
    
    public double getY() {
        return this.y;
    }
    
    public Location setY(final double y) {
        this.y = y;
        return this;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public Location setZ(final double z) {
        this.z = z;
        return this;
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public Location setYaw(final float yaw) {
        this.yaw = yaw;
        return this;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public Location setPitch(final float pitch) {
        this.pitch = pitch;
        return this;
    }
    
    public static Location fromBlockPos(final BlockPos blockPos) {
        return new Location(blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }
    
    public BlockPos toBlockPos() {
        return new BlockPos(this.getX(), this.getY(), this.getZ());
    }
    
    public double distanceTo(final Location loc) {
        final double dx = loc.x - this.x;
        final double dz = loc.z - this.z;
        final double dy = loc.y - this.y;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
    
    public double distanceToY(final Location loc) {
        final double dy = loc.y - this.y;
        return Math.sqrt(dy * dy);
    }

	public void offset(double xoff, double yoff, double zoff) {
        this.x += xoff;
        this.y += yoff;
        this.z += zoff;
    }

    public void rotate(float rot, boolean x, boolean y, boolean z) {
        double radians = Math.toRadians(rot);
        if (x) {
            this.y = this.y * Math.cos(radians) - this.z * Math.sin(radians);
            this.z = this.y * Math.sin(radians) + this.z * Math.cos(radians);
        } else if (y) {
            this.z = this.z * Math.cos(radians) - this.x * Math.sin(radians);
            this.x = this.z * Math.sin(radians) + this.x * Math.cos(radians);
        } else if (z) {
            this.x = this.x * Math.cos(radians) - this.y * Math.sin(radians);
            this.y = this.x * Math.sin(radians) + this.y * Math.cos(radians);
        }
    }
}
