/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.client.module.modules.combat;

import java.util.Iterator;

import me.aristhena.client.friend.FriendManager;
import me.aristhena.client.module.Module;
import me.aristhena.client.option.Option;
import me.aristhena.event.Event;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.UpdateEvent;
import me.aristhena.utils.ClientUtils;
import me.aristhena.utils.RotationUtils;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

@Module.Mod(displayName="Aim Assist")
public class AimAssist
extends Module {
    @Option.Op(name="Smoothness", min=1.0, max=30.0, increment=1.0)
    private double speed = 5.0;
    @Option.Op(min=0.0, max=180.0, increment=5.0, name="Degrees")
    public double degrees = 45.0;
    @Option.Op(min=1.0, max=6.0, increment=0.25, name="Range")
    private double range = 5.0;
    private EntityLivingBase target;

    public void onEnable() {
        this.target = null;
        super.enable();
    }

    @EventTarget(value=3)
    public void onUpdate(UpdateEvent local_01) {
        if (local_01.getState().equals((Object)Event.State.PRE) && ClientUtils.player().isEntityAlive()) {
            Iterator var3 = ClientUtils.world().loadedEntityList.iterator();
            while (true) {
                if (!var3.hasNext()) {
                    return;
                }
                Object local_3 = var3.next();
                if (!(local_3 instanceof EntityLivingBase)) continue;
                this.target = this.isEntityValid((Entity)local_3) && ((EntityLivingBase)local_3).isEntityAlive() ? (EntityLivingBase)local_3 : null;
                if (this.target == null) continue;
                EntityPlayerSP player = ClientUtils.player();
                player.rotationPitch += (float)((double)this.getPitchChange(this.target) / this.speed);
                EntityPlayerSP player2 = ClientUtils.player();
                player2.rotationYaw += (float)((double)this.getYawChange(this.target) / this.speed);
            }
        }
    }

    public float getPitchChange(Entity local_01) {
        double local_2 = local_01.posX - ClientUtils.mc().thePlayer.posX;
        double local_3 = local_01.posZ - ClientUtils.mc().thePlayer.posZ;
        double local_4 = local_01.posY - 2.2 + (double)local_01.getEyeHeight() - ClientUtils.mc().thePlayer.posY;
        double local_5 = MathHelper.sqrt_double(local_2 * local_2 + local_3 * local_3);
        double local_6 = -Math.toDegrees(Math.atan(local_4 / local_5));
        return -MathHelper.wrapAngleTo180_float(ClientUtils.mc().thePlayer.rotationPitch - (float)local_6) - 2.5f;
    }

    public float getYawChange(Entity local_01) {
        double local_2 = local_01.posX - ClientUtils.mc().thePlayer.posX;
        double local_3 = local_01.posZ - ClientUtils.mc().thePlayer.posZ;
        double local_4 = 0.0;
        local_4 = local_3 < 0.0 && local_2 < 0.0 ? 90.0 + Math.toDegrees(Math.atan(local_3 / local_2)) : (local_3 < 0.0 && local_2 > 0.0 ? -90.0 + Math.toDegrees(Math.atan(local_3 / local_2)) : Math.toDegrees(-Math.atan(local_2 / local_3)));
        return MathHelper.wrapAngleTo180_float(-(ClientUtils.mc().thePlayer.rotationYaw - (float)local_4));
    }

    public boolean isEntityValid(Entity local_01) {
        block5: {
            double local_10;
            block7: {
                block6: {
                    if (!(local_01 instanceof EntityLivingBase)) break block5;
                    if (!ClientUtils.player().isEntityAlive() || !((EntityLivingBase)local_01).isEntityAlive()) break block6;
                    double d2 = local_01.getDistanceToEntity(ClientUtils.player());
                    double d3 = ClientUtils.player().canEntityBeSeen(local_01) ? this.range : 3.0;
                    if (!(d2 > d3)) break block7;
                }
                return false;
            }
            double local_3 = local_01.posX - ClientUtils.player().posX;
            double local_4 = local_01.posZ - ClientUtils.player().posZ;
            double local_5 = ClientUtils.player().posY + (double)ClientUtils.player().getEyeHeight() - (local_01.posY + (double)local_01.getEyeHeight());
            double local_6 = Math.sqrt(local_3 * local_3 + local_4 * local_4);
            float local_7 = (float)(Math.atan2(local_4, local_3) * 180.0 / Math.PI) - 90.0f;
            float local_8 = (float)(Math.atan2(local_5, local_6) * 180.0 / Math.PI);
            double local_9 = RotationUtils.getDistanceBetweenAngles(local_7, ClientUtils.player().rotationYaw % 360.0f);
            double local_11 = Math.sqrt(local_9 * local_9 + (local_10 = (double)RotationUtils.getDistanceBetweenAngles(local_8, ClientUtils.player().rotationPitch % 360.0f)) * local_10);
            if (local_11 > this.degrees) {
                return false;
            }
            if (local_01 instanceof EntityPlayer) {
                return !FriendManager.isFriend(((EntityPlayer)local_01).getName());
            }
        }
        return false;
    }
}

