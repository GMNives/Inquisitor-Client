package me.aristhena.client.module.modules.render.hud;

import java.text.*;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.util.*;

import me.aristhena.client.Client;
import me.aristhena.client.module.*;
import me.aristhena.event.events.*;
import me.aristhena.utils.*;

public class Trendy extends Theme
{
    private float[] hue;
    
    public Trendy(final String name, final boolean value, final Module module) {
        super(name, value, module);
        this.hue = new float[] { 0.0f };
    }
    
    @Override
    public boolean onRender(final Render2DEvent render2DEvent) {
        if (super.onRender(render2DEvent)) {
            final String format = new SimpleDateFormat("hh:mm a").format(new Date());
            if (format.startsWith("0")) {
                format.replaceFirst("0", "");
            }
            //Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("Logolar/Br1cNLogo2.png"));
            ClientUtils.mc().getRenderItem().func_180450_b(new ItemStack(Item.getByNameOrId("diamond")), 105, -3);
            ClientUtils.clientFont().drawStringWithShadow("§4§lInquisitor §c§l" + Client.ver, 2.0, 2.0, Color.getHSBColor(this.hue[0] / 255.0f, 1.0f, 1.0f).getRGB());
            ClientUtils.clientFont().drawStringWithShadow("§c§o " + Client.creator, -1.0, 12.0, Color.getHSBColor(this.hue[0] / 255.0f, 1.0f, 1.0f).getRGB());
            int n = 2;
            final float[] array = { this.hue[0] };
            for (final Module module : ModuleManager.getModulesForRender()) {
                if (module.drawDisplayName((float)(render2DEvent.getWidth() - ClientUtils.clientFont().getStringWidth(String.format("%s" + ((module.getSuffix().length() > 0) ? " §0[%s]" : ""), module.getDisplayName(), module.getSuffix())) - 2), (float)n, Color.getHSBColor(array[0] / 255.0f, 1.0f, 1.0f).getRGB())) {
                    final float[] array2 = array;
                    final int n2 = 0;
                    array2[n2] += 9.0f;
                    if (array[0] > 255.0f) {
                        final float[] array3 = array;
                        final int n3 = 0;
                        array3[n3] -= 255.0f;
                    }
                    n += 10;
                }
            }
            final float[] hue = this.hue;
            final int n4 = 0;
            hue[n4] += 0.5;
            if (this.hue[0] > 255.0f) {
                final float[] hue2 = this.hue;
                final int n5 = 0;
                hue2[n5] -= 255.0f;
            }
        }
        return super.onRender(render2DEvent);
    }
}
