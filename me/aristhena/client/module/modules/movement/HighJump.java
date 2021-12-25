package me.aristhena.client.module.modules.movement;

import me.aristhena.client.module.Module;
import me.aristhena.client.module.Module.Mod;
import me.aristhena.client.option.Option;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.UpdateEvent;
import me.aristhena.utils.ClientUtils;
import net.minecraft.client.Minecraft;

@Mod
public class HighJump extends Module
{
    @Option.Op(min = 0.4, max = 10.0, increment = 0.025)
    public double boost;
    
    @EventTarget(0)
    private void onUpdate(final UpdateEvent event) {
        if (ClientUtils.player().onGround) {
            Minecraft.getMinecraft().thePlayer.motionY = this.boost;
        }
    }
}
