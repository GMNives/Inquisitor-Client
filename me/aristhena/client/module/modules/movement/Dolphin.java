/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.client.module.modules.movement;

import me.aristhena.client.module.Module;
import me.aristhena.event.Event;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.UpdateEvent;
import me.aristhena.utils.ClientUtils;
import me.aristhena.utils.LiquidUtils;

@Module.Mod
public class Dolphin
extends Module {
    private boolean wasWater = false;
    private int ticks = 0;

    @Override
    public void enable() {
        this.wasWater = false;
        super.enable();
    }

    @EventTarget(value=0)
    public void onMotion(UpdateEvent event) {
        if (event.getState() == Event.State.POST) {
            return;
        }
        if (ClientUtils.player().onGround || ClientUtils.player().isOnLadder()) {
            this.wasWater = false;
        }
        if (ClientUtils.player().motionY > 0.0 && this.wasWater) {
            if (ClientUtils.player().motionY <= 0.11) {
                ClientUtils.player().motionY *= 1.2671;
            }
            ClientUtils.player().motionY += 0.05172;
        }
        if (LiquidUtils.isInLiquid() && !ClientUtils.player().isSneaking()) {
            if (this.ticks < 3) {
                ClientUtils.player().motionY = 0.13;
                ++this.ticks;
                this.wasWater = false;
            } else {
                ClientUtils.player().motionY = 0.5;
                this.ticks = 0;
                this.wasWater = true;
            }
        }
    }
}

