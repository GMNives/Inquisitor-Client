// 
// Decompiled by Procyon v0.5.30
// 

package me.aristhena.client.module.modules.misc;

import org.lwjgl.input.Keyboard;

import me.aristhena.client.module.Module;
import me.aristhena.client.module.Module.Mod;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.Render2DEvent;
import me.aristhena.utils.ClientUtils;
import net.minecraft.client.gui.GuiChat;
@Mod(displayName = "InvMove")
public class ScreenWalk extends Module
{
    @EventTarget
    private void onRender(final Render2DEvent event) {
        if (ClientUtils.mc().currentScreen != null && !(ClientUtils.mc().currentScreen instanceof GuiChat)) {
            if (Keyboard.isKeyDown(200)) {
                ClientUtils.pitch(ClientUtils.pitch() - 2.0f);
            }
            if (Keyboard.isKeyDown(208)) {
                ClientUtils.pitch(ClientUtils.pitch() + 2.0f);
            }
            if (Keyboard.isKeyDown(203)) {
                ClientUtils.yaw(ClientUtils.yaw() - 3.0f);
            }
            if (Keyboard.isKeyDown(205)) {
                ClientUtils.yaw(ClientUtils.yaw() + 3.0f);
            }
        }
    }
}
