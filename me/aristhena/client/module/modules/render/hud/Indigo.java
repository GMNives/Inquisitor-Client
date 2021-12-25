package me.aristhena.client.module.modules.render.hud;

import java.text.*;
import net.minecraft.client.renderer.*;

import java.util.*;

import me.aristhena.client.module.*;
import me.aristhena.event.events.*;
import me.aristhena.utils.*;

public class Indigo extends Theme
{
    private static final int MIN_HEX = -23614;
    private static final int MAX_HEX = -3394561;
    private static final int MAX_FADE = 20;
    private static int currentColor;
    private static int fadeState;
    private static boolean goingUp;
    
    public Indigo(final String name, final boolean value, final Module module) {
        super(name, value, module);
    }
    
    @Override
    public boolean onRender(final Render2DEvent event) {
        if (super.onRender(event)) {
            String time = new SimpleDateFormat("hh:mm a").format(new Date());
            if (time.startsWith("0")) {
                time = time.replaceFirst("0", "");
            }
            GlStateManager.pushMatrix();
            ClientUtils.clientFont().drawStringWithShadow("§d§lK§b§lirka §d§lb§b§l8", 2.0, 2.0, -221160221);
            GlStateManager.popMatrix();
            int y = 2;
            for (final Module mod : ModuleManager.getModulesForRender()) {
                if (mod.drawDisplayName((float)(event.getWidth() - ClientUtils.clientFont().getStringWidth(String.format("%s" + ((mod.getSuffix().length() > 0) ? " §0[%s]" : ""), mod.getDisplayName(), mod.getSuffix())) - 2), (float)y, Indigo.currentColor)) {
                    y += 10;
                }
            }
        }
        return super.onRender(event);
    }
    
    public static void updateFade() {
        if (Indigo.fadeState >= 20 || Indigo.fadeState <= 0) {
            Indigo.goingUp = !Indigo.goingUp;
        }
        if (Indigo.goingUp) {
            ++Indigo.fadeState;
        }
        else {
            --Indigo.fadeState;
        }
        final double ratio = Indigo.fadeState / 20.0;
        Indigo.currentColor = getFadeHex(-1, 16711935, ratio);
    }
    
    private static int getFadeHex(final int hex1, final int hex2, final double ratio) {
        int r = hex1 >> 16;
        int g = hex1 >> 8 & 0xFF;
        int b = hex1 & 0xFF;
        r += (int)(((hex2 >> 16) - r) * ratio);
        g += (int)(((hex2 >> 8 & 0xFF) - g) * ratio);
        b += (int)(((hex2 & 0xFF) - b) * ratio);
        return r << 16 | g << 8 | b;
    }
}
