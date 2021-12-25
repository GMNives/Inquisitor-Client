/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.utils.render;

import me.aristhena.utils.render.GuiUtils;
import me.aristhena.utils.render.RenderingUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class DarkRenderer {
    private static FontRenderer fr;

    static {
        Minecraft.getMinecraft();
        fr = Minecraft.fontRendererObj;
    }

    public static void enableGL2D() {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
    }

    public static void drawSmallString(String text, int x2, int y2) {
        GL11.glScalef(0.8f, 0.8f, 0.8f);
        Minecraft.getMinecraft();
        Minecraft.fontRendererObj.func_175063_a(text, x2 *= 2, y2, 0xFFFFFF);
        GL11.glScalef(1.25f, 1.25f, 1.25f);
    }

    public static void drawLargerString(String text, int x2, int y2) {
        GL11.glScalef(1.5f, 1.5f, 1.5f);
        Minecraft.getMinecraft();
        Minecraft.fontRendererObj.func_175063_a(text, x2 *= 2, y2, 0xFFFFFF);
        GL11.glScalef(0.665f, 0.665f, 0.665f);
    }

    public static void disableGL2D() {
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }

    public static void drawCentredStringWithShadow(String s, int x2, int y2, int colour) {
        fr.drawString(s, x2 -= fr.getStringWidth(s) / 2, y2, colour);
    }

    public static void drawRect(int x2, int y2, int x22, int y22, int color) {
        Gui.drawRect(x2, y2, x22, y22, color);
    }

    public static void drawRect(float paramXStart, float paramYStart, float paramXEnd, float paramYEnd, int paramColor) {
        float alpha = (float)(paramColor >> 24 & 0xFF) / 255.0f;
        float red = (float)(paramColor >> 16 & 0xFF) / 255.0f;
        float green = (float)(paramColor >> 8 & 0xFF) / 255.0f;
        float blue = (float)(paramColor & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glBegin(7);
        GL11.glVertex2d(paramXEnd, paramYStart);
        GL11.glVertex2d(paramXStart, paramYStart);
        GL11.glVertex2d(paramXStart, paramYEnd);
        GL11.glVertex2d(paramXEnd, paramYEnd);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }

    public static void drawPoint(int x2, int y2, int color) {
        DarkRenderer.drawRect(x2, y2, x2 + 1, y2 + 1, color);
    }

    public static void drawVerticalLine(int x2, int y2, int height, int color) {
        DarkRenderer.drawRect(x2, y2, x2 + 1, height, color);
    }

    public static void drawHorizontalLine(int x2, int y2, int width, int color) {
        DarkRenderer.drawRect(x2, y2, width, y2 + 1, color);
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

    public static void drawHat(double x2, double y2, double z2, double width, double height, float red, float green, float blue, float alpha, float lineRed, float lineGreen, float lineBlue, float lineAlpha, float lineWdith) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, alpha);
        DarkRenderer.drawBoundingBox(new AxisAlignedBB(x2 - width, y2, z2 - width, x2 + width, y2 + height, z2 + width));
        GL11.glLineWidth(lineWdith);
        GL11.glColor4f(lineRed, lineGreen, lineBlue, lineAlpha);
        DarkRenderer.drawOutlinedBoundingBox(new AxisAlignedBB(x2 - width, y2, z2 - width, x2 + width, y2 + height, z2 + width));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawBorderedRect(int x2, int y2, int x1, int y1, int bord, int color) {
        DarkRenderer.drawRect(x2 + 1, y2 + 1, x1, y1, color);
        DarkRenderer.drawVerticalLine(x2, y2, y1, bord);
        DarkRenderer.drawVerticalLine(x1, y2, y1, bord);
        DarkRenderer.drawHorizontalLine(x2 + 1, y2, x1, bord);
        DarkRenderer.drawHorizontalLine(x2, y1, x1 + 1, bord);
    }

    public static void drawFineBorderedRect(int x2, int y2, int x1, int y1, int bord, int color) {
        GL11.glScaled(0.5, 0.5, 0.5);
        DarkRenderer.drawRect((x2 *= 2) + 1, (y2 *= 2) + 1, x1 *= 2, y1 *= 2, color);
        DarkRenderer.drawVerticalLine(x2, y2, y1, bord);
        DarkRenderer.drawVerticalLine(x1, y2, y1, bord);
        DarkRenderer.drawHorizontalLine(x2 + 1, y2, x1, bord);
        DarkRenderer.drawHorizontalLine(x2, y1, x1 + 1, bord);
        GL11.glScaled(2.0, 2.0, 2.0);
    }

    public static void drawBorderRectNoCorners(int x2, int y2, int x22, int y22, int bord, int color) {
        GL11.glScaled(0.5, 0.5, 0.5);
        DarkRenderer.drawRect((x2 *= 2) + 1, (y2 *= 2) + 1, x22 *= 2, y22 *= 2, color);
        DarkRenderer.drawVerticalLine(x2, y2 + 1, y22, bord);
        DarkRenderer.drawVerticalLine(x22, y2 + 1, y22, bord);
        DarkRenderer.drawHorizontalLine(x2 + 1, y2, x22, bord);
        DarkRenderer.drawHorizontalLine(x2 + 1, y22, x22, bord);
        GL11.glScaled(2.0, 2.0, 2.0);
    }

    public static void drawBorderedGradient(int x2, int y2, int x1, int y1, int bord, int gradTop, int gradBot) {
        GL11.glScaled(0.5, 0.5, 0.5);
        float f2 = (float)(gradTop >> 24 & 0xFF) / 255.0f;
        float f22 = (float)(gradTop >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(gradTop >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(gradTop & 0xFF) / 255.0f;
        float f5 = (float)(gradBot >> 24 & 0xFF) / 255.0f;
        float f6 = (float)(gradBot >> 16 & 0xFF) / 255.0f;
        float f7 = (float)(gradBot >> 8 & 0xFF) / 255.0f;
        float f8 = (float)(gradBot & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(f22, f3, f4, f2);
        GL11.glVertex2d(x1 *= 2, (y2 *= 2) + 1);
        GL11.glVertex2d((x2 *= 2) + 1, y2 + 1);
        GL11.glColor4f(f6, f7, f8, f5);
        GL11.glVertex2d(x2 + 1, y1 *= 2);
        GL11.glVertex2d(x1, y1);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glShadeModel(7424);
        DarkRenderer.drawVLine(x2, y2, y1 - 1, bord);
        DarkRenderer.drawVLine(x1 - 1, y2, y1 - 1, bord);
        DarkRenderer.drawHLine(x2, x1 - 1, y2, bord);
        DarkRenderer.drawHLine(x2, x1 - 1, y1 - 1, bord);
        GL11.glScaled(2.0, 2.0, 2.0);
    }

    public static void draw2DCorner(EntityLivingBase entity, double posX, double posY, double posZ, int color) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(posX, posY, posZ);
        GL11.glNormal3f(0.0f, 0.0f, 0.0f);
        GlStateManager.rotate(-RenderManager.playerViewY, 0.0f, 1.0f, 0.0f);
        GlStateManager.scale(-0.1, -0.1, 0.1);
        GL11.glDisable(2896);
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GlStateManager.func_179098_w();
        GlStateManager.depthMask(true);
        GuiUtils.drawRect(4.0f, -20.0f, 7.0f, -19.0f, color);
        GuiUtils.drawRect(-7.0f, -20.0f, -4.0f, -19.0f, color);
        GuiUtils.drawRect(6.0f, -20.0f, 7.0f, -17.5f, color);
        GuiUtils.drawRect(-7.0f, -20.0f, -6.0f, -17.5f, color);
        GuiUtils.drawRect(-7.0f, 2.0f, -4.0f, 3.0f, color);
        GuiUtils.drawRect(4.0f, 2.0f, 7.0f, 3.0f, color);
        GuiUtils.drawRect(-7.0f, 0.5f, -6.0f, 3.0f, color);
        GuiUtils.drawRect(6.0f, 0.5f, 7.0f, 3.0f, color);
        GuiUtils.drawRect(7.0f, -20.0f, 7.3f, -17.5f, -16777216);
        GuiUtils.drawRect(-7.3f, -20.0f, -7.0f, -17.5f, -16777216);
        GuiUtils.drawRect(4.0f, -20.3f, 7.3f, -20.0f, -16777216);
        GuiUtils.drawRect(-7.3f, -20.3f, -4.0f, -20.0f, -16777216);
        GuiUtils.drawRect(-7.0f, 3.0f, -4.0f, 3.3f, -16777216);
        GuiUtils.drawRect(4.0f, 3.0f, 7.0f, 3.3f, -16777216);
        GuiUtils.drawRect(-7.3f, 0.5f, -7.0f, 3.3f, -16777216);
        GuiUtils.drawRect(7.0f, 0.5f, 7.3f, 3.3f, -16777216);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glEnable(2896);
        GlStateManager.popMatrix();
    }

    public static void drawRoundedRect(float x2, float y2, float x1, float y1, int insideC) {
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        DarkRenderer.drawVLine(x2 *= 2.0f, (y2 *= 2.0f) + 1.0f, (y1 *= 2.0f) - 2.0f, insideC);
        DarkRenderer.drawVLine((x1 *= 2.0f) - 1.0f, y2 + 1.0f, y1 - 2.0f, insideC);
        DarkRenderer.drawHLine(x2 + 2.0f, x1 - 3.0f, y2, insideC);
        DarkRenderer.drawHLine(x2 + 2.0f, x1 - 3.0f, y1 - 1.0f, insideC);
        DarkRenderer.drawHLine(x2 + 1.0f, x2 + 1.0f, y2 + 1.0f, insideC);
        DarkRenderer.drawHLine(x1 - 2.0f, x1 - 2.0f, y2 + 1.0f, insideC);
        DarkRenderer.drawHLine(x1 - 2.0f, x1 - 2.0f, y1 - 2.0f, insideC);
        DarkRenderer.drawHLine(x2 + 1.0f, x2 + 1.0f, y1 - 2.0f, insideC);
        GL11.glPushMatrix();
        DarkRenderer.drawRect(x2 + 1.0f, y2 + 1.0f, x1 - 1.0f, y1 - 1.0f, insideC);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    public static void drawRoundedBorderedRect(float x2, float y2, float x1, float y1, int borderC, int insideC) {
        RenderingUtils.enableGL2D();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        RenderingUtils.drawVLine(x2 *= 2.0f, (y2 *= 2.0f) + 1.0f, (y1 *= 2.0f) - 2.0f, borderC);
        RenderingUtils.drawVLine((x1 *= 2.0f) - 1.0f, y2 + 1.0f, y1 - 2.0f, borderC);
        RenderingUtils.drawHLine(x2 + 2.0f, x1 - 3.0f, y2, borderC);
        RenderingUtils.drawHLine(x2 + 2.0f, x1 - 3.0f, y1 - 1.0f, borderC);
        RenderingUtils.drawHLine(x2 + 1.0f, x2 + 1.0f, y2 + 1.0f, borderC);
        RenderingUtils.drawHLine(x1 - 2.0f, x1 - 2.0f, y2 + 1.0f, borderC);
        RenderingUtils.drawHLine(x1 - 2.0f, x1 - 2.0f, y1 - 2.0f, borderC);
        RenderingUtils.drawHLine(x2 + 1.0f, x2 + 1.0f, y1 - 2.0f, borderC);
        RenderingUtils.drawRect(x2 + 1.0f, y2 + 1.0f, x1 - 1.0f, y1 - 1.0f, insideC);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        RenderingUtils.disableGL2D();
    }

    public static void drawHLine(float par1, float par2, float par3, int par4) {
        if (par2 < par1) {
            float var5 = par1;
            par1 = par2;
            par2 = var5;
        }
        DarkRenderer.drawRect(par1, par3, par2 + 1.0f, par3 + 1.0f, par4);
    }

    public static void drawVLine(float par1, float par2, float par3, int par4) {
        if (par3 < par2) {
            float var5 = par2;
            par2 = par3;
            par3 = var5;
        }
        DarkRenderer.drawRect(par1, par2 + 1.0f, par1 + 1.0f, par3, par4);
    }

    public static void drawGradientBorderedRect(double x2, double y2, double x22, double y22, float l1, int col1, int col2, int col3) {
        float f2 = (float)(col1 >> 24 & 0xFF) / 255.0f;
        float f22 = (float)(col1 >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(col1 >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(col1 & 0xFF) / 255.0f;
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glDisable(3042);
        GL11.glPushMatrix();
        GL11.glColor4f(f22, f3, f4, f2);
        GL11.glLineWidth(1.0f);
        GL11.glBegin(1);
        GL11.glVertex2d(x2, y2);
        GL11.glVertex2d(x2, y22);
        GL11.glVertex2d(x22, y22);
        GL11.glVertex2d(x22, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glVertex2d(x22, y2);
        GL11.glVertex2d(x2, y22);
        GL11.glVertex2d(x22, y22);
        GL11.glEnd();
        GL11.glPopMatrix();
        DarkRenderer.drawGradientRect(x2, y2, x22, y22, col2, col3);
        GL11.glEnable(3042);
        GL11.glEnable(3553);
        GL11.glDisable(2848);
    }

    public static void drawGradientRect(double x2, double y2, double x22, double y22, int col1, int col2) {
        float f2 = (float)(col1 >> 24 & 0xFF) / 255.0f;
        float f22 = (float)(col1 >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(col1 >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(col1 & 0xFF) / 255.0f;
        float f5 = (float)(col2 >> 24 & 0xFF) / 255.0f;
        float f6 = (float)(col2 >> 16 & 0xFF) / 255.0f;
        float f7 = (float)(col2 >> 8 & 0xFF) / 255.0f;
        float f8 = (float)(col2 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(f22, f3, f4, f2);
        GL11.glVertex2d(x22, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glColor4f(f6, f7, f8, f5);
        GL11.glVertex2d(x2, y22);
        GL11.glVertex2d(x22, y22);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glShadeModel(7424);
    }

    public static void drawSidewaysGradientRect(double x2, double y2, double x22, double y22, int col1, int col2) {
        float f2 = (float)(col1 >> 24 & 0xFF) / 255.0f;
        float f22 = (float)(col1 >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(col1 >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(col1 & 0xFF) / 255.0f;
        float f5 = (float)(col2 >> 24 & 0xFF) / 255.0f;
        float f6 = (float)(col2 >> 16 & 0xFF) / 255.0f;
        float f7 = (float)(col2 >> 8 & 0xFF) / 255.0f;
        float f8 = (float)(col2 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(f22, f3, f4, f2);
        GL11.glVertex2d(x2, y2);
        GL11.glVertex2d(x2, y22);
        GL11.glColor4f(f6, f7, f8, f5);
        GL11.glVertex2d(x22, y22);
        GL11.glVertex2d(x22, y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glShadeModel(7424);
    }

    public static void drawCircle(float cx, float cy2, float r2, int num_segments, int c2) {
        cx *= 2.0f;
        cy2 *= 2.0f;
        float f2 = (float)(c2 >> 24 & 0xFF) / 255.0f;
        float f22 = (float)(c2 >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(c2 >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(c2 & 0xFF) / 255.0f;
        float theta = (float)(6.2831852 / (double)num_segments);
        float p2 = (float)Math.cos(theta);
        float s = (float)Math.sin(theta);
        float x2 = r2 *= 2.0f;
        float y2 = 0.0f;
        DarkRenderer.enableGL2D();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        GL11.glColor4f(f22, f3, f4, f2);
        GL11.glBegin(2);
        for (int ii2 = 0; ii2 < num_segments; ++ii2) {
            GL11.glVertex2f(x2 + cx, y2 + cy2);
            float t = x2;
            x2 = p2 * x2 - s * y2;
            y2 = s * t + p2 * y2;
        }
        GL11.glEnd();
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        DarkRenderer.disableGL2D();
    }

    public static void drawFullCircle(int cx, int cy2, double radius, int color) {
        radius *= 2.0;
        cx *= 2;
        cy2 *= 2;
        float f2 = (float)(color >> 24 & 0xFF) / 255.0f;
        float f22 = (float)(color >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(color & 0xFF) / 255.0f;
        DarkRenderer.enableGL2D();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        GL11.glColor4f(f22, f3, f4, f2);
        GL11.glBegin(6);
        for (int i2 = 0; i2 <= 360; ++i2) {
            double x2 = Math.sin((double)i2 * Math.PI / 180.0) * radius;
            double y2 = Math.cos((double)i2 * Math.PI / 180.0) * radius;
            GL11.glVertex2d((double)cx + x2, (double)cy2 + y2);
        }
        GL11.glEnd();
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        DarkRenderer.disableGL2D();
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
        DarkRenderer.drawOutlinedBoundingBox(new AxisAlignedBB(x2, y2, z2, x2 + 1.0, y2 + 1.0, z2 + 1.0));
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
        DarkRenderer.drawBoundingBox(new AxisAlignedBB(x2, y2, z2, x2 + 1.0, y2 + 1.0, z2 + 1.0));
        GL11.glLineWidth(lineWidth);
        GL11.glColor4f(lineRed, lineGreen, lineBlue, lineAlpha);
        DarkRenderer.drawOutlinedBoundingBox(new AxisAlignedBB(x2, y2, z2, x2 + 1.0, y2 + 1.0, z2 + 1.0));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static double getAlphaFromHex(int color) {
        return (float)(color >> 24 & 0xFF) / 255.0f;
    }

    public static double getRedFromHex(int color) {
        return (float)(color >> 16 & 0xFF) / 255.0f;
    }

    public static double getGreenFromHex(int color) {
        return (float)(color >> 8 & 0xFF) / 255.0f;
    }

    public static double getBlueFromHex(int color) {
        return (float)(color & 0xFF) / 255.0f;
    }

    public static void drawCircle(double x2, double y2, double radius, int c2) {
        float f2 = (float)(c2 >> 24 & 0xFF) / 255.0f;
        float f22 = (float)(c2 >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(c2 >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(c2 & 0xFF) / 255.0f;
        GlStateManager.alphaFunc(516, 0.001f);
        GlStateManager.color(f22, f3, f4, f2);
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        Tessellator tes = Tessellator.getInstance();
        for (double i2 = 0.0; i2 < 360.0; i2 += 1.0) {
            double f1 = Math.sin(i2 * Math.PI / 180.0) * radius;
            double f21 = Math.cos(i2 * Math.PI / 180.0) * radius;
            GL11.glVertex2d((double)f3 + x2, (double)f4 + y2);
        }
        GlStateManager.disableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.alphaFunc(516, 0.1f);
    }

    public static void drawIcon(float x2, float y2, int sizex, int sizey, ResourceLocation resourceLocation) {
        GL11.glPushMatrix();
        Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.1f);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        GL11.glTranslatef(x2, y2, 0.0f);
        DarkRenderer.drawScaledRect(0, 0, 0.0f, 0.0f, sizex, sizey, sizex, sizey, sizex, sizey);
        GlStateManager.disableAlpha();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableLighting();
        GlStateManager.disableRescaleNormal();
        GL11.glDisable(2848);
        GlStateManager.disableBlend();
        GL11.glPopMatrix();
    }

    public static void drawScaledRect(int x2, int y2, float u2, float v2, int uWidth, int vHeight, int width, int height, float tileWidth, float tileHeight) {
        Gui.drawScaledCustomSizeModalRect(x2, y2, u2, v2, uWidth, vHeight, width, height, tileWidth, tileHeight);
    }
}

