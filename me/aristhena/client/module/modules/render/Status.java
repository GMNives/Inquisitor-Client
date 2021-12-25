/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.client.module.modules.render;

import java.awt.Color;

import me.aristhena.client.module.Module;
import me.aristhena.client.option.Option;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.Render2DEvent;
import me.aristhena.utils.ClientUtils;
import me.aristhena.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

@Module.Mod(enabled=true, shown=false)
public class Status
extends Module {
    protected static Minecraft mc = Minecraft.getMinecraft();
    private FontRenderer fr;
    @Option.Op(name="PotionStatus")
    public boolean PotionStatus;
    private static int pY = 0;
    public boolean Platinum_1;
    public boolean Platinum_2;
    public boolean Platinum_3;
    public boolean Platinum_4;
    public boolean Platinum_5;
    public boolean Platinum_6;
    public boolean Platinum_7;
    public boolean Platinum_8;
    public boolean Platinum_9;
    public boolean Platinum_10;
    public boolean Platinum_11;
    public boolean Platinum_12;
    public boolean Platinum_13;
    public boolean Platinum_14;
    private int pX;

    public Status() {
        this.fr = Minecraft.getMinecraft().fontRendererObj;
    }

    @EventTarget
    private void onRender2D(Render2DEvent event) {
        ScaledResolution sr = new ScaledResolution(ClientUtils.mc(), ClientUtils.mc().displayWidth, ClientUtils.mc().displayHeight);
        if (this.PotionStatus) {
            this.PotionStatus(sr);
        }
    }

    private void PotionStatus(ScaledResolution sr) {
        pY = 70;
        for (Object o : ClientUtils.mc().thePlayer.getActivePotionEffects()) {
            PotionEffect effect = (PotionEffect)o;
            Potion potion = Potion.potionTypes[effect.getPotionID()];
            String PType = I18n.format(potion.getName(), new Object[0]);
            if (effect.getAmplifier() == 0) {
                PType = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(PType)))))) + " 1";
            } else if (effect.getAmplifier() == 1) {
                PType = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(PType)))))) + " 2";
            } else if (effect.getAmplifier() == 2) {
                PType = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(PType)))))) + " 3";
            } else if (effect.getAmplifier() == 3) {
                PType = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(PType)))))) + " 4";
            } else if (effect.getAmplifier() == 4) {
                PType = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(PType)))))) + " 5";
            } else if (effect.getAmplifier() == 5) {
                PType = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(PType)))))) + " 6";
            } else if (effect.getAmplifier() == 6) {
                PType = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(PType)))))) + " 7";
            }
            if (effect.getDuration() < 600 && effect.getDuration() > 300) {
                PType = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(PType)))))) + " \u00a70: \u00a7c" + Potion.getDurationString(effect);
            } else if (effect.getDuration() < 300) {
                PType = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(PType)))))) + " \u00a70: \u00a74" + Potion.getDurationString(effect);
            } else if (effect.getDuration() > 600) {
                PType = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(PType)))))) + " \u00a70: \u00a7a" + Potion.getDurationString(effect);
            }
            this.setpX(sr.getScaledWidth() - this.fr.getStringWidth(PType) - 4);
            ClientUtils.clientFont().drawStringWithShadow("\u00a75" + PType, 5.0, pY, -1);
            pY += 10;
        }
    }

    public static void Br1cNHerdaim(String string, int x, int y) {
        int xpos = x;
        for (int i = 0; i < string.length(); ++i) {
            String s = String.valueOf(string.charAt(i));
            ClientUtils.clientFont().drawStringWithShadow(s, xpos, y, Status.getRainbow(1000, -30 * i));
            xpos += ClientUtils.clientFont().getStringWidth(s);
        }
    }

    public static int getRainbow(int speed, int offset) {
        float hue = (System.currentTimeMillis() + (long)offset) % (long)speed;
        return Color.getHSBColor(hue /= (float)speed, 0.75f, 1.0f).getRGB();
    }

    protected void drawVerticalLine(int x, int startY, int endY, int color) {
        if (endY < startY) {
            int var5 = startY;
            startY = endY;
            endY = var5;
        }
        RenderUtils.rectangleBordered(x, startY + 1, x + 1, endY, color, color, color);
    }

    protected void drawHorizontalLine(int startX, int endX, int y, int color) {
        if (endX < startX) {
            int var5 = startX;
            startX = endX;
            endX = var5;
        }
        RenderUtils.rectangleBordered(startX, y, endX + 1, y + 1, color, color, color);
    }

    public int getpX() {
        return this.pX;
    }

    public void setpX(int pX) {
        this.pX = pX;
    }
}

