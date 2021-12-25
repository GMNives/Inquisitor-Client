/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.utils.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

import me.aristhena.client.Wrapper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class VitalMath {
    public static int getMiddle(int i2, int i1) {
        return (i2 + i1) / 2;
    }

    public static double getMiddle(double i2, double i1) {
        return (i2 + i1) / 2.0;
    }

    public static float getAngleDifference(float direction, float rotationYaw) {
        float phi = Math.abs(rotationYaw - direction) % 360.0f;
        float distance = phi > 180.0f ? 360.0f - phi : phi;
        return distance;
    }

    public static float[] getRotations(Entity entity) {
        double diffY;
        Vec3 localPos = Wrapper.getPlayer().getPositionVector();
        Vec3 entityPos = entity.getPositionVector();
        Vec3 diffPos = entityPos.subtract(localPos.xCoord, 0.0, localPos.zCoord);
        if (entity instanceof EntityLivingBase) {
            EntityLivingBase dist = (EntityLivingBase)entity;
            diffY = dist.posY + ((double)dist.getEyeHeight() - 0.4) - (Wrapper.getPlayer().posY + (double)Wrapper.getPlayer().getEyeHeight());
        } else {
            diffY = (entity.boundingBox.minY + entity.boundingBox.maxY) / 2.0 - (Wrapper.getPlayer().posY + (double)Wrapper.getPlayer().getEyeHeight());
        }
        double dist1 = MathHelper.sqrt_double(Math.pow(diffPos.xCoord, 2.0) + Math.pow(diffPos.zCoord, 2.0));
        float yaw = (float)Math.toDegrees(Math.atan2(diffPos.xCoord, diffPos.zCoord)) - 90.0f;
        float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, dist1)));
        return new float[]{yaw, pitch};
    }

    public static double roundToPlace(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd2 = new BigDecimal(value);
        bd2 = bd2.setScale(places, RoundingMode.HALF_UP);
        return bd2.doubleValue();
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd2 = new BigDecimal(value);
        bd2 = bd2.setScale(places, RoundingMode.HALF_UP);
        return bd2.doubleValue();
    }
}

