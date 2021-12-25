/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.utils.render;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

public class ButtonUtils {
    public static void drawBorderRect(int left, int top, int right, int bottom, int bcolor, int icolor, int bwidth) {
        Gui.drawRect(left + bwidth, top + bwidth, right - bwidth, bottom - bwidth, icolor);
        Gui.drawRect(left, top, left + bwidth, bottom, bcolor);
        Gui.drawRect(left + bwidth, top, right, top + bwidth, bcolor);
        Gui.drawRect(left + bwidth, bottom - bwidth, right, bottom, bcolor);
        Gui.drawRect(right - bwidth, top + bwidth, right, bottom - bwidth, bcolor);
    }

    public static int withAlpha(Color c2, float a2) {
        float r2 = (float)(c2.getRGB() >> 16 & 0xFF) / 255.0f;
        float g2 = (float)(c2.getRGB() >> 8 & 0xFF) / 255.0f;
        float b2 = (float)(c2.getRGB() & 0xFF) / 255.0f;
        return new Color(r2, g2, b2, a2).getRGB();
    }

    public static void drawRect(int x2, int y2, int x1, int y1, int color) {
        Gui.drawRect(x2, y2, x1, y1, color);
    }

    public static void drawOutlinedBoundingBox(AxisAlignedBB aa2) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        worldRenderer.startDrawing(3);
        worldRenderer.addVertex(aa2.minX, aa2.minY, aa2.minZ);
        worldRenderer.addVertex(aa2.maxX, aa2.minY, aa2.minZ);
        worldRenderer.addVertex(aa2.maxX, aa2.minY, aa2.maxZ);
        worldRenderer.addVertex(aa2.minX, aa2.minY, aa2.maxZ);
        worldRenderer.addVertex(aa2.minX, aa2.minY, aa2.minZ);
        tessellator.draw();
        worldRenderer.startDrawing(3);
        worldRenderer.addVertex(aa2.minX, aa2.maxY, aa2.minZ);
        worldRenderer.addVertex(aa2.maxX, aa2.maxY, aa2.minZ);
        worldRenderer.addVertex(aa2.maxX, aa2.maxY, aa2.maxZ);
        worldRenderer.addVertex(aa2.minX, aa2.maxY, aa2.maxZ);
        worldRenderer.addVertex(aa2.minX, aa2.maxY, aa2.minZ);
        tessellator.draw();
        worldRenderer.startDrawing(1);
        worldRenderer.addVertex(aa2.minX, aa2.minY, aa2.minZ);
        worldRenderer.addVertex(aa2.minX, aa2.maxY, aa2.minZ);
        worldRenderer.addVertex(aa2.maxX, aa2.minY, aa2.minZ);
        worldRenderer.addVertex(aa2.maxX, aa2.maxY, aa2.minZ);
        worldRenderer.addVertex(aa2.maxX, aa2.minY, aa2.maxZ);
        worldRenderer.addVertex(aa2.maxX, aa2.maxY, aa2.maxZ);
        worldRenderer.addVertex(aa2.minX, aa2.minY, aa2.maxZ);
        worldRenderer.addVertex(aa2.minX, aa2.maxY, aa2.maxZ);
        tessellator.draw();
    }

    public static void drawBoundingBox(AxisAlignedBB aa2) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        worldRenderer.startDrawingQuads();
        worldRenderer.addVertex(aa2.minX, aa2.minY, aa2.minZ);
        worldRenderer.addVertex(aa2.minX, aa2.maxY, aa2.minZ);
        worldRenderer.addVertex(aa2.maxX, aa2.minY, aa2.minZ);
        worldRenderer.addVertex(aa2.maxX, aa2.maxY, aa2.minZ);
        worldRenderer.addVertex(aa2.maxX, aa2.minY, aa2.maxZ);
        worldRenderer.addVertex(aa2.maxX, aa2.maxY, aa2.maxZ);
        worldRenderer.addVertex(aa2.minX, aa2.minY, aa2.maxZ);
        worldRenderer.addVertex(aa2.minX, aa2.maxY, aa2.maxZ);
        tessellator.draw();
        worldRenderer.startDrawingQuads();
        worldRenderer.addVertex(aa2.maxX, aa2.maxY, aa2.minZ);
        worldRenderer.addVertex(aa2.maxX, aa2.minY, aa2.minZ);
        worldRenderer.addVertex(aa2.minX, aa2.maxY, aa2.minZ);
        worldRenderer.addVertex(aa2.minX, aa2.minY, aa2.minZ);
        worldRenderer.addVertex(aa2.minX, aa2.maxY, aa2.maxZ);
        worldRenderer.addVertex(aa2.minX, aa2.minY, aa2.maxZ);
        worldRenderer.addVertex(aa2.maxX, aa2.maxY, aa2.maxZ);
        worldRenderer.addVertex(aa2.maxX, aa2.minY, aa2.maxZ);
        tessellator.draw();
        worldRenderer.startDrawingQuads();
        worldRenderer.addVertex(aa2.minX, aa2.maxY, aa2.minZ);
        worldRenderer.addVertex(aa2.maxX, aa2.maxY, aa2.minZ);
        worldRenderer.addVertex(aa2.maxX, aa2.maxY, aa2.maxZ);
        worldRenderer.addVertex(aa2.minX, aa2.maxY, aa2.maxZ);
        worldRenderer.addVertex(aa2.minX, aa2.maxY, aa2.minZ);
        worldRenderer.addVertex(aa2.minX, aa2.maxY, aa2.maxZ);
        worldRenderer.addVertex(aa2.maxX, aa2.maxY, aa2.maxZ);
        worldRenderer.addVertex(aa2.maxX, aa2.maxY, aa2.minZ);
        tessellator.draw();
        worldRenderer.startDrawingQuads();
        worldRenderer.addVertex(aa2.minX, aa2.minY, aa2.minZ);
        worldRenderer.addVertex(aa2.maxX, aa2.minY, aa2.minZ);
        worldRenderer.addVertex(aa2.maxX, aa2.minY, aa2.maxZ);
        worldRenderer.addVertex(aa2.minX, aa2.minY, aa2.maxZ);
        worldRenderer.addVertex(aa2.minX, aa2.minY, aa2.minZ);
        worldRenderer.addVertex(aa2.minX, aa2.minY, aa2.maxZ);
        worldRenderer.addVertex(aa2.maxX, aa2.minY, aa2.maxZ);
        worldRenderer.addVertex(aa2.maxX, aa2.minY, aa2.minZ);
        tessellator.draw();
        worldRenderer.startDrawingQuads();
        worldRenderer.addVertex(aa2.minX, aa2.minY, aa2.minZ);
        worldRenderer.addVertex(aa2.minX, aa2.maxY, aa2.minZ);
        worldRenderer.addVertex(aa2.minX, aa2.minY, aa2.maxZ);
        worldRenderer.addVertex(aa2.minX, aa2.maxY, aa2.maxZ);
        worldRenderer.addVertex(aa2.maxX, aa2.minY, aa2.maxZ);
        worldRenderer.addVertex(aa2.maxX, aa2.maxY, aa2.maxZ);
        worldRenderer.addVertex(aa2.maxX, aa2.minY, aa2.minZ);
        worldRenderer.addVertex(aa2.maxX, aa2.maxY, aa2.minZ);
        tessellator.draw();
        worldRenderer.startDrawingQuads();
        worldRenderer.addVertex(aa2.minX, aa2.maxY, aa2.maxZ);
        worldRenderer.addVertex(aa2.minX, aa2.minY, aa2.maxZ);
        worldRenderer.addVertex(aa2.minX, aa2.maxY, aa2.minZ);
        worldRenderer.addVertex(aa2.minX, aa2.minY, aa2.minZ);
        worldRenderer.addVertex(aa2.maxX, aa2.maxY, aa2.minZ);
        worldRenderer.addVertex(aa2.maxX, aa2.minY, aa2.minZ);
        worldRenderer.addVertex(aa2.maxX, aa2.maxY, aa2.maxZ);
        worldRenderer.addVertex(aa2.maxX, aa2.minY, aa2.maxZ);
        tessellator.draw();
    }

    public static void drawOutlinedBlockESP(double x2, double y2, double z2, float red, float green, float blue, float alpha, float lineWidth) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glLineWidth(lineWidth);
        GL11.glColor4f(red, green, blue, alpha);
        ButtonUtils.drawOutlinedBoundingBox(new AxisAlignedBB(x2, y2, z2, x2 + 1.0, y2 + 1.0, z2 + 1.0));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawBlockESP(double x2, double y2, double z2, float red, float green, float blue, float alpha, float lineRed, float lineGreen, float lineBlue, float lineAlpha, float lineWidth) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        ButtonUtils.drawBoundingBox(new AxisAlignedBB(x2, y2, z2, x2 + 1.0, y2 + 1.0, z2 + 1.0));
        GL11.glLineWidth(lineWidth);
        GL11.glColor4f(lineRed, lineGreen, lineBlue, lineAlpha);
        ButtonUtils.drawOutlinedBoundingBox(new AxisAlignedBB(x2, y2, z2, x2 + 1.0, y2 + 1.0, z2 + 1.0));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawClickTPESP(double x2, double y2, double z2, float red, float green, float blue, float alpha, float lineRed, float lineGreen, float lineBlue, float lineAlpha, float lineWidth) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        ButtonUtils.drawBoundingBox(new AxisAlignedBB(x2, y2 + 1.1, z2, x2 + 1.0, y2 + 1.0, z2 + 1.0));
        GL11.glLineWidth(lineWidth);
        GL11.glColor4f(lineRed, lineGreen, lineBlue, lineAlpha);
        ButtonUtils.drawOutlinedBoundingBox(new AxisAlignedBB(x2, y2 + 1.1, z2, x2 + 1.0, y2 + 1.0, z2 + 1.0));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawSolidBlockESP(double x2, double y2, double z2, float red, float green, float blue, float alpha) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        ButtonUtils.drawBoundingBox(new AxisAlignedBB(x2, y2, z2, x2 + 1.0, y2 + 1.0, z2 + 1.0));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawOutlinedEntityESP(double x2, double y2, double z2, double width, double height, float red, float green, float blue, float alpha) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        ButtonUtils.drawOutlinedBoundingBox(new AxisAlignedBB(x2 - width, y2, z2 - width, x2 + width, y2 + height, z2 + width));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawSolidEntityESP(double x2, double y2, double z2, double width, double height, float red, float green, float blue, float alpha) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        ButtonUtils.drawBoundingBox(new AxisAlignedBB(x2 - width, y2, z2 - width, x2 + width, y2 + height, z2 + width));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawEntityESP(double x2, double y2, double z2, double width, double height, float red, float green, float blue, float alpha, float lineRed, float lineGreen, float lineBlue, float lineAlpha, float lineWdith) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        ButtonUtils.drawBoundingBox(new AxisAlignedBB(x2 - width, y2, z2 - width, x2 + width, y2 + height, z2 + width));
        GL11.glLineWidth(lineWdith);
        GL11.glColor4f(lineRed, lineGreen, lineBlue, lineAlpha);
        ButtonUtils.drawOutlinedBoundingBox(new AxisAlignedBB(x2 - width, y2, z2 - width, x2 + width, y2 + height, z2 + width));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawTracerLine(double x2, double y2, double z2, float red, float green, float blue, float alpha, float lineWdith) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(lineWdith);
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glBegin(2);
        GL11.glVertex3d(0.0, Minecraft.getMinecraft().thePlayer.getEyeHeight(), 0.0);
        GL11.glVertex3d(x2, y2, z2);
        GL11.glEnd();
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawCircle(int x2, int y2, double r2, int c2) {
        float f2 = (float)(c2 >> 24 & 0xFF) / 255.0f;
        float f22 = (float)(c2 >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(c2 >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(c2 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(f22, f3, f4, f2);
        GL11.glBegin(2);
        for (int i2 = 0; i2 <= 360; ++i2) {
            double x22 = Math.sin((double)i2 * Math.PI / 180.0) * r2;
            double y22 = Math.cos((double)i2 * Math.PI / 180.0) * r2;
            GL11.glVertex2d((double)x2 + x22, (double)y2 + y22);
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }

    public static void drawFilledCircle(int x2, int y2, double r2, int c2) {
        float f2 = (float)(c2 >> 24 & 0xFF) / 255.0f;
        float f22 = (float)(c2 >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(c2 >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(c2 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(f22, f3, f4, f2);
        GL11.glBegin(6);
        for (int i2 = 0; i2 <= 360; ++i2) {
            double x22 = Math.sin((double)i2 * Math.PI / 180.0) * r2;
            double y22 = Math.cos((double)i2 * Math.PI / 180.0) * r2;
            GL11.glVertex2d((double)x2 + x22, (double)y2 + y22);
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }

    public static void dr(double i2, double j2, double k2, double l2, int i1) {
        double f2;
        if (i2 < k2) {
            f2 = i2;
            i2 = k2;
            k2 = f2;
        }
        if (j2 < l2) {
            f2 = j2;
            j2 = l2;
            l2 = f2;
        }
        float f1 = (float)(i1 >> 24 & 0xFF) / 255.0f;
        float f22 = (float)(i1 >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(i1 >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(i1 & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(f22, f3, f4, f1);
        worldRenderer.startDrawingQuads();
        worldRenderer.addVertex(i2, l2, 0.0);
        worldRenderer.addVertex(k2, l2, 0.0);
        worldRenderer.addVertex(k2, j2, 0.0);
        worldRenderer.addVertex(i2, j2, 0.0);
        tessellator.draw();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
}

