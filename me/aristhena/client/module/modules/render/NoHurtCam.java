/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.client.module.modules.render;

import me.aristhena.client.module.Module;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.UpdateEvent;
import me.aristhena.utils.ClientUtils;

@Module.Mod
public class NoHurtCam
extends Module {
    @EventTarget
    private void onUpdate(UpdateEvent event) {
        if (ClientUtils.mc().thePlayer.hurtTime > 0) {
            ClientUtils.mc().thePlayer.maxHurtTime = 0;
        }
    }
}

