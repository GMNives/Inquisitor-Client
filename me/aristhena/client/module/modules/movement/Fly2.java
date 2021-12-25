/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.client.module.modules.movement;

import me.aristhena.client.module.Module;
import me.aristhena.client.option.Option;
import me.aristhena.event.Event;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.BlockBB;
import me.aristhena.event.events.MoveEvent;
import me.aristhena.event.events.UpdateEvent;
import me.aristhena.utils.BlockHelper;
import me.aristhena.utils.Timer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.C03PacketPlayer;

@Module.Mod(displayName="Fly2")
public class Fly2
extends Module {
    private final float motion;
    @Option.Op(name="latest")
    private boolean latest;
    private final Timer time = new Timer();
    private Minecraft mc = Minecraft.getMinecraft();

    public Fly2() {
        this.motion = 1.0f;
    }

    @Override
    public void enable() {
        super.enable();
        if (Minecraft.thePlayer != null && Minecraft.thePlayer.onGround) {
            if (this.latest) {
                this.damagePlayer();
                Minecraft.thePlayer.capabilities.setFlySpeed(0.015f);
                return;
            }
            double x = Minecraft.thePlayer.posX;
            double y = Minecraft.thePlayer.posY;
            double z = Minecraft.thePlayer.posZ;
            if (Minecraft.thePlayer != null) {
                for (int i = 0; i < 81; ++i) {
                    Minecraft.getMinecraft();
                    NetHandlerPlayClient netHandlerPlayClient = Minecraft.getNetHandler();
                    Minecraft.getMinecraft();
                    Minecraft.getMinecraft();
                    Minecraft.getMinecraft();
                    netHandlerPlayClient.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(Minecraft.thePlayer.posX, Minecraft.thePlayer.posY + 0.05, Minecraft.thePlayer.posZ, false));
                    Minecraft.getMinecraft();
                    NetHandlerPlayClient netHandlerPlayClient2 = Minecraft.getNetHandler();
                    Minecraft.getMinecraft();
                    Minecraft.getMinecraft();
                    Minecraft.getMinecraft();
                    netHandlerPlayClient2.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(Minecraft.thePlayer.posX, Minecraft.thePlayer.posY, Minecraft.thePlayer.posZ, false));
                }
            }
            this.time.reset();
        }
    }

    @Override
    public void disable() {
        super.disable();
        if (Minecraft.thePlayer != null) {
            Minecraft.thePlayer.capabilities.isFlying = false;
        }
    }

    @EventTarget
    private boolean onEvent(UpdateEvent e) {
        if (e.getState().equals((Object)Event.State.PRE)) {
            if (this.latest) {
                Minecraft.thePlayer.capabilities.isFlying = true;
            }
            if (!Minecraft.thePlayer.capabilities.isFlying && Minecraft.thePlayer.fallDistance > 0.0f && !Minecraft.thePlayer.isSneaking() && this.time.hasReached(500L)) {
                Minecraft.thePlayer.motionY = -0.03127;
            }
            if (this.mc.gameSettings.keyBindSneak.getIsKeyPressed()) {
                Minecraft.thePlayer.motionY = -0.5;
            }
        }
        return true;
    }

    @EventTarget
    private void blockBB(BlockBB e) {
        if (!this.latest) {
            BlockBB event = e;
            if (!Minecraft.thePlayer.capabilities.isFlying && Minecraft.thePlayer.fallDistance > 0.0f && !Minecraft.thePlayer.isSneaking() && this.time.hasReached(500L) && (double)event.getY() < Minecraft.thePlayer.posY + 0.5) {
                event.setBoundingBox(null);
                if (BlockHelper.isInsideBlock(Minecraft.thePlayer)) {
                    Minecraft.getMinecraft();
                    Minecraft.thePlayer.func_174826_a(Minecraft.thePlayer.getEntityBoundingBox().offset(0.0, 0.0626, 0.0));
                }
            }
        }
    }

    @EventTarget
    private void onMove(MoveEvent e) {
        if (!this.latest) {
            MoveEvent event2 = e;
            if (this.time.hasReached(200L) && !this.mc.gameSettings.keyBindSneak.getIsKeyPressed()) {
                event2.setY(event2.getY() * 1.0);
            }
        }
    }

    public void damagePlayer() {
        if (Minecraft.thePlayer != null) {
            int i = 0;
            while ((double)i < 92.5) {
                Minecraft.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(Minecraft.thePlayer.posX, Minecraft.thePlayer.posY + 0.049, Minecraft.thePlayer.posZ, false));
                Minecraft.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(Minecraft.thePlayer.posX, Minecraft.thePlayer.posY, Minecraft.thePlayer.posZ, false));
                ++i;
            }
            Minecraft.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(Minecraft.thePlayer.posX, Minecraft.thePlayer.posY, Minecraft.thePlayer.posZ, false));
        }
    }
}

