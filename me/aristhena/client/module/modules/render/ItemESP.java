package me.aristhena.client.module.modules.render;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.network.play.client.C03PacketPlayer;
import me.aristhena.event.EventTarget;
import me.aristhena.event.MouseEvent;
import me.aristhena.event.events.Render2DEvent;
import me.aristhena.event.events.Render3DEvent;
import me.aristhena.event.events.TickEvent;
import me.aristhena.event.events.UpdateEvent;
import me.aristhena.client.module.Module;
import me.aristhena.client.option.OptionManager;
import me.aristhena.utils.RenderUtils2;

@Module.Mod(displayName = "ItemESP")
public class ItemESP extends Module
{   
	Minecraft mc = Minecraft.getMinecraft();
	
	@EventTarget
    private void onUpdate(final Render3DEvent event) {
        for (final Object e : this.mc.theWorld.loadedEntityList) {
            if (e instanceof EntityItem) {
                final EntityItem item = (EntityItem)e;
                RenderUtils2.drawItemEsp(item, new Color(0, 0, 0, 21).getRGB());
            }
        }
	}
}
