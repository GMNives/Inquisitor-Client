/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.utils.render;

import java.awt.Color;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureUtil;
import org.lwjgl.opengl.GL11;

public class WinterRenderUtils {
    protected static int glTextureId = -1;

    public static void setColor(Color c2) {
        GL11.glColor4d((float)c2.getRed() / 255.0f, (float)c2.getGreen() / 255.0f, (float)c2.getBlue() / 255.0f, (float)c2.getAlpha() / 255.0f);
    }

    public static void drawTri(double x1, double y1, double x2, double y2, double x3, double y3, double width, Color c2) {
        GlStateManager.pushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        WinterRenderUtils.setColor(c2);
        GL11.glLineWidth((float)width);
        GL11.glBegin(3);
        GL11.glVertex2d(x1, y1);
        GL11.glVertex2d(x2, y2);
        GL11.glVertex2d(x3, y3);
        GL11.glEnd();
        GlStateManager.popMatrix();
    }

    private static int getGlTextureId() {
        if (glTextureId == -1) {
            glTextureId = TextureUtil.glGenTextures();
        }
        return glTextureId;
    }

    public static void drawBorderedRect(float x2, float y2, float x1, float y1, float size, Color mainColor, Color borderColor) {
        WinterRenderUtils.drawRect(x2, y2, x1, y1, mainColor);
        WinterRenderUtils.drawRect(x2 - size, y2 - size, x1, y2, borderColor);
        WinterRenderUtils.drawRect(x2, y2, x2 - size, y1, borderColor);
        WinterRenderUtils.drawRect(x1, y1, x1 + size, y2 - size, borderColor);
        WinterRenderUtils.drawRect(x2 - size, y1 + size, x1 + size, y1, borderColor);
    }

    public static void drawRect(float left, float top, float right, float bottom, Color color) {
        float var6;
        if (left < right) {
            var6 = left;
            left = right;
            right = var6;
        }
        if (top < bottom) {
            var6 = top;
            top = bottom;
            bottom = var6;
        }
        Tessellator var61 = Tessellator.getInstance();
        WorldRenderer var7 = var61.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.func_179090_x();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        WinterRenderUtils.setColor(color);
        var7.startDrawingQuads();
        var7.addVertex(left, bottom, 0.0);
        var7.addVertex(right, bottom, 0.0);
        var7.addVertex(right, top, 0.0);
        var7.addVertex(left, top, 0.0);
        var61.draw();
        GlStateManager.func_179098_w();
        GlStateManager.disableBlend();
        WinterRenderUtils.setColor(Color.WHITE);
    }
}

