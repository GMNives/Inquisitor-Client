package me.aristhena.client.module.modules.render.hud;

import java.text.*;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;

import java.awt.*;
import java.util.*;

import me.aristhena.client.Client;
import me.aristhena.client.module.*;
import me.aristhena.event.events.*;
import me.aristhena.utils.*;

public class Nautilus extends Theme
{
	public boolean rect;
    private static int Y;
    private static int pY;
    private static float pY2;
    private static int rectY;
	public Color blackColor2;
    public Color blackColor1 = new Color(0, 0, 0, 0);
    private float[] hue;
    
    public Nautilus(final String name, final boolean value, final Module module) {
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
            String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
            String history = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            String Day = new SimpleDateFormat("EEEEE").format(new Date());
            if (time.startsWith("")) {
                time = time.replaceFirst("", "");
            }
            GlStateManager.pushMatrix();
            GlStateManager.scale(1.4, 1.4, 1.4);
            Nautilus.RenkRainbow("\u3010 Inquisitor v" + Client.ver + " \u3011", 4, 2, 0.7f);
            GlStateManager.popMatrix();
            ScaledResolution scaledRes = new ScaledResolution(ClientUtils.mc(), ClientUtils.mc().displayWidth, ClientUtils.mc().displayHeight);
            Minecraft.getMinecraft();
            Nautilus.RenkRainbow("FPS \u300b  " + Minecraft.func_175610_ah(), 4, 20, 0.7f);
            Nautilus.RenkRainbow("Name \u300b " + Minecraft.getMinecraft().thePlayer.getName(), 4, 30, 0.7f);
            Nautilus.RenkRainbow("Health \u300b " + Minecraft.getMinecraft().thePlayer.getHealth(), 4, 40, 0.7f);
            Nautilus.RenkRainbow("X \u300b " + MathHelper.floor_double(ClientUtils.player().posX), 4, 60, 0.7f);
            Nautilus.RenkRainbow("Y \u300b " + MathHelper.floor_double(ClientUtils.player().posY), 4, 70, 0.7f);
            Nautilus.RenkRainbow("Z \u300b " + MathHelper.floor_double(ClientUtils.player().posZ), 4, 80, 0.7f);
            Nautilus.RenkRainbow("DATE \u300b " + history, 4, 100, 0.7f);
            Nautilus.RenkRainbow("TIME \u300b " + time, 4, 110, 0.7f);
            int n = 2;
            final float[] array = { this.hue[0] };
            for (final Module module : ModuleManager.getModulesForRender()) {
                final float[] array2 = array;
                final int n2 = 0;
                array2[n2] += 9.0f;
                if (array[0] > 255.0f) {
                    final float[] array3 = array;
                    final int n3 = 0;
                    array3[n3] -= 255.0f;
                }
                if (module.drawDisplayName((float)(render2DEvent.getWidth() - ClientUtils.clientFont().getStringWidth(String.format("%s" + ((module.getSuffix().length() > 0) ? " §0[%s]" : ""), module.getDisplayName(), module.getSuffix())) - 2), (float)n, Color.getHSBColor(array[0] / 255.0f, 1.0f, 1.0f).getRGB())) {
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
    
    public void RenkliHud() {
        this.blackColor2 = new Color(0, 0, 0, 127);
    }
    public static void RenkRainbow(String string, int x, int y, float brightness) {
        int xpos = x;
        for (int i = 0; i < string.length(); ++i) {
            String s = String.valueOf(string.charAt(i));
            ClientUtils.clientFont().drawStringWithShadow(s, xpos, y, Nautilus.getRainbow(1000, -15 * i * 3));
            xpos += ClientUtils.clientFont().getStringWidth(s);
        }
    }

    public static int getRainbow(int speed, int offset) {
        float hue = (System.currentTimeMillis() + (long)offset) % (long)speed;
        return Color.getHSBColor(hue /= (float)speed, 0.7f, 0.7f).getRGB();
    }
}
