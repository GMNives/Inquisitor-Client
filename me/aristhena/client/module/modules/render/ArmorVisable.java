package me.aristhena.client.module.modules.render;

import net.minecraft.client.*;

import org.lwjgl.opengl.*;

import me.aristhena.client.module.*;
import me.aristhena.event.*;
import me.aristhena.event.events.*;
import me.aristhena.utils.*;
import net.minecraft.client.renderer.*;
import net.minecraft.item.*;

import java.awt.*;

@Module.Mod(displayName = "ArmorVisable")
public class ArmorVisable extends Module
{
    public Minecraft mc;
    
    public ArmorVisable() {
        this.mc = Minecraft.getMinecraft();
    }
    
    @EventTarget
    private void onRender2D(final Render2DEvent e) {
        int itemX = e.getWidth() / 2 + 9;
        for (int i = 0; i < 5; ++i) {
            final ItemStack ia = this.mc.thePlayer.getEquipmentInSlot(i);
            if (ia != null) {
                final float oldZ = this.mc.getRenderItem().zLevel;
                GL11.glPushMatrix();
                GlStateManager.clear(256);
                GlStateManager.disableAlpha();
                RenderHelper.enableStandardItemLighting();
                this.mc.getRenderItem().zLevel = -100.0f;
                this.mc.getRenderItem().renderItemIntoGUI(ia, itemX, e.getHeight() - 55);
                this.mc.getRenderItem().renderItemOverlays(this.mc.fontRendererObj, ia, itemX, e.getHeight() - 55);
                this.mc.getRenderItem().zLevel = oldZ;
                RenderHelper.disableStandardItemLighting();
                GlStateManager.enableAlpha();
                GL11.glPopMatrix();
                if (ia.getItem() instanceof ItemSword || ia.getItem() instanceof ItemTool || ia.getItem() instanceof ItemArmor || ia.getItem() instanceof ItemBow) {
                    GlStateManager.pushMatrix();
                    final int durability = ia.getMaxDamage() - ia.getItemDamage();
                    final int y = e.getHeight() - 60;
                    GlStateManager.scale(0.5, 0.5, 0.5);
                    ClientUtils.clientFont().drawStringWithShadow(new StringBuilder(String.valueOf(durability)).toString(), (double)((itemX + 4) / 0.5f), (double)(y / 0.5f), getRainbow(15000, -15 * y));
                    GlStateManager.popMatrix();
                }
                itemX += 16;
            }
        }
    }
    
    public static int getRainbow(final int speed, final int offset) {
        float hue = (System.currentTimeMillis() + offset) % speed;
        hue /= speed;
        return Color.getHSBColor(hue, 1.0f, 1.0f).getRGB();
    }
}
