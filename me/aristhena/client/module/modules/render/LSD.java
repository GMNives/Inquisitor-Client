package me.aristhena.client.module.modules.render;

import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;
import me.aristhena.event.EventTarget;
import me.aristhena.event.MouseEvent;
import me.aristhena.event.events.TickEvent;
import me.aristhena.event.events.UpdateEvent;
import me.aristhena.client.module.Module;
import me.aristhena.client.option.OptionManager;
import me.aristhena.utils.ClientUtils;

@Module.Mod(displayName = "LSD")
public class LSD extends Module
{   
	Minecraft mc = Minecraft.getMinecraft();
	
    public void enable() {
        mc.entityRenderer.shaderIndex = 18;
        mc.entityRenderer.activateNextShader();
    	super.enable();
    }
	
    @EventTarget
    public void onUpdate(final UpdateEvent event) {
	if (ClientUtils.mc().entityRenderer.theShaderGroup == null) {
        mc.entityRenderer.shaderIndex = 18;
        mc.entityRenderer.activateNextShader();
	}
    }
    
	public void disable() {
        mc.entityRenderer.shaderIndex = 0;
        mc.entityRenderer.activateNextShader();
		super.disable();
	}
}
