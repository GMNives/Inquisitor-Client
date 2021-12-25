/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.client.module.modules.movement;

import me.aristhena.client.module.Module;
import me.aristhena.event.Event;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.MoveEvent;
import me.aristhena.event.events.UpdateEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.client.C03PacketPlayer;

@Module.Mod
public class EditFly
extends Module {
    public boolean Br1cN_TARAFINDAN_YAPILMISTIR_1;
    public boolean Br1cN_TARAFINDAN_YAPILMISTIR_2;
    public boolean Br1cN_TARAFINDAN_YAPILMISTIR_3;
    public boolean Br1cN_TARAFINDAN_YAPILMISTIR_4;
    public boolean Br1cN_TARAFINDAN_YAPILMISTIR_5;
    public boolean Br1cN_TARAFINDAN_YAPILMISTIR_6;
    public boolean Br1cN_TARAFINDAN_YAPILMISTIR_7;
    public boolean Br1cN_TARAFINDAN_YAPILMISTIR_8;
    public boolean Br1cN_TARAFINDAN_YAPILMISTIR_9;
    public boolean Br1cN_TARAFINDAN_YAPILMISTIR_10;
    public boolean Br1cN_TARAFINDAN_YAPILMISTIR_11;
    public boolean Br1cN_TARAFINDAN_YAPILMISTIR_12;
    public boolean Br1cN_TARAFINDAN_YAPILMISTIR_13;
    public boolean Br1cN_TARAFINDAN_YAPILMISTIR_14;
    protected static Minecraft mc = Minecraft.getMinecraft();
    private boolean nc = false;
    private boolean damage = true;
    private boolean glide = true;
    private double speed = 1.1;
    private double glideSpeed = 0.05;

    @Override
    public void enable() {
        if (this.damage && EditFly.mc.thePlayer.isCollidedVertically) {
            int i = 0;
            while ((double)i < 80.0) {
                EditFly.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(EditFly.mc.thePlayer.posX, EditFly.mc.thePlayer.posY + 0.049, EditFly.mc.thePlayer.posZ, false));
                EditFly.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(EditFly.mc.thePlayer.posX, EditFly.mc.thePlayer.posY, EditFly.mc.thePlayer.posZ, false));
                ++i;
            }
            EditFly.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(EditFly.mc.thePlayer.posX, EditFly.mc.thePlayer.posY, EditFly.mc.thePlayer.posZ, true));
        }
        EditFly.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(EditFly.mc.thePlayer.posX, EditFly.mc.thePlayer.posY + 0.01, EditFly.mc.thePlayer.posZ, false));
        super.enable();
    }

    @EventTarget
    private void onUpdate(UpdateEvent event) {
        if (event.getState() == Event.State.PRE) {
            if (this.nc) {
                if (!EditFly.mc.thePlayer.movementInput.jump && !EditFly.mc.thePlayer.movementInput.sneak && this.glide && (double)EditFly.mc.thePlayer.movementInput.moveForward == 0.0 && (double)EditFly.mc.thePlayer.movementInput.moveStrafe == 0.0) {
                    EntityPlayerSP thePlayer = EditFly.mc.thePlayer;
                    EntityPlayerSP thePlayer2 = EditFly.mc.thePlayer;
                    EntityPlayerSP thePlayer3 = EditFly.mc.thePlayer;
                    double motionX = 0.0;
                    thePlayer3.motionY = 0.0;
                    thePlayer2.motionZ = 0.0;
                    thePlayer.motionX = 0.0;
                    event.setCancelled(true);
                } else {
                    EditFly.mc.thePlayer.motionY = EditFly.mc.thePlayer.movementInput.jump ? this.speed : (EditFly.mc.thePlayer.movementInput.sneak ? -this.speed : (this.glide ? -this.glideSpeed : 0.0));
                }
            } else {
                EditFly.mc.thePlayer.motionY = EditFly.mc.thePlayer.movementInput.jump ? this.speed / 2.0 : (EditFly.mc.thePlayer.movementInput.sneak ? -this.speed / 2.0 : (this.glide ? -this.glideSpeed : 0.0));
            }
        }
    }

    @EventTarget
    private void onMove(MoveEvent moveEvent) {
        throw new Error("Unresolved compilation problems: \n\tThe field MoveEvent.x is not visible\n\tThe field MoveEvent.z is not visible\n");
    }
}

