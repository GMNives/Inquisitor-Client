/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.utils.render;

import java.awt.Color;
import java.awt.Rectangle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class GuiUtils {
    public static final RenderItem RENDER_ITEM = new RenderItem(Minecraft.getMinecraft().renderEngine, Minecraft.getMinecraft().modelManager);
    private static ScaledResolution scaledResolution;

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

    public static void disableGL2D() {
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }

    public static void drawRect(Rectangle rectangle, int color) {
        GuiUtils.drawRect(rectangle.x, rectangle.y, rectangle.x + rectangle.width, rectangle.y + rectangle.height, color);
    }

    public static void drawRect(float x2, float y2, float x1, float y1, int color) {
        GuiUtils.enableGL2D();
        GuiUtils.glColor(color);
        GuiUtils.drawRect(x2, y2, x1, y1);
        GuiUtils.disableGL2D();
    }

    public static void drawBorderedRect(float x2, float y2, float x1, float y1, float width, int internalColor, int borderColor) {
        GuiUtils.enableGL2D();
        GuiUtils.glColor(internalColor);
        GuiUtils.drawRect(x2 + width, y2 + width, x1 - width, y1 - width);
        GuiUtils.glColor(borderColor);
        GuiUtils.drawRect(x2 + width, y2, x1 - width, y2 + width);
        GuiUtils.drawRect(x2, y2, x2 + width, y1);
        GuiUtils.drawRect(x1 - width, y2, x1, y1);
        GuiUtils.drawRect(x2 + width, y1 - width, x1 - width, y1);
        GuiUtils.disableGL2D();
    }

    public static void drawBorderedRect(float x2, float y2, float x1, float y1, int insideC, int borderC) {
        GuiUtils.enableGL2D();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        GuiUtils.drawVLine(x2 *= 2.0f, y2 *= 2.0f, y1 *= 2.0f, borderC);
        GuiUtils.drawVLine((x1 *= 2.0f) - 1.0f, y2, y1, borderC);
        GuiUtils.drawHLine(x2, x1 - 1.0f, y2, borderC);
        GuiUtils.drawHLine(x2, x1 - 2.0f, y1 - 1.0f, borderC);
        GuiUtils.drawRect(x2 + 1.0f, y2 + 1.0f, x1 - 1.0f, y1 - 1.0f, insideC);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        GuiUtils.disableGL2D();
    }

    public static void drawBorderedRectReliant(float x2, float y2, float x1, float y1, float lineWidth, int inside, int border) {
        GuiUtils.enableGL2D();
        GuiUtils.drawRect(x2, y2, x1, y1, inside);
        GuiUtils.glColor(border);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(lineWidth);
        GL11.glBegin(3);
        GL11.glVertex2f(x2, y2);
        GL11.glVertex2f(x2, y1);
        GL11.glVertex2f(x1, y1);
        GL11.glVertex2f(x1, y2);
        GL11.glVertex2f(x2, y2);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GuiUtils.disableGL2D();
    }

    public static void drawGradientBorderedRectReliant(float x2, float y2, float x1, float y1, float lineWidth, int border, int bottom, int top) {
        GuiUtils.enableGL2D();
        GuiUtils.drawGradientRect(x2, y2, x1, y1, top, bottom);
        GuiUtils.glColor(border);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(lineWidth);
        GL11.glBegin(3);
        GL11.glVertex2f(x2, y2);
        GL11.glVertex2f(x2, y1);
        GL11.glVertex2f(x1, y1);
        GL11.glVertex2f(x1, y2);
        GL11.glVertex2f(x2, y2);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GuiUtils.disableGL2D();
    }

    public static void drawRoundedRect(float x2, float y2, float x1, float y1, int borderC, int insideC) {
        GuiUtils.enableGL2D();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        GuiUtils.drawVLine(x2 *= 2.0f, (y2 *= 2.0f) + 1.0f, (y1 *= 2.0f) - 2.0f, borderC);
        GuiUtils.drawVLine((x1 *= 2.0f) - 1.0f, y2 + 1.0f, y1 - 2.0f, borderC);
        GuiUtils.drawHLine(x2 + 2.0f, x1 - 3.0f, y2, borderC);
        GuiUtils.drawHLine(x2 + 2.0f, x1 - 3.0f, y1 - 1.0f, borderC);
        GuiUtils.drawHLine(x2 + 1.0f, x2 + 1.0f, y2 + 1.0f, borderC);
        GuiUtils.drawHLine(x1 - 2.0f, x1 - 2.0f, y2 + 1.0f, borderC);
        GuiUtils.drawHLine(x1 - 2.0f, x1 - 2.0f, y1 - 2.0f, borderC);
        GuiUtils.drawHLine(x2 + 1.0f, x2 + 1.0f, y1 - 2.0f, borderC);
        GuiUtils.drawRect(x2 + 1.0f, y2 + 1.0f, x1 - 1.0f, y1 - 1.0f, insideC);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        GuiUtils.disableGL2D();
    }

    public static void drawBorderedRect(Rectangle rectangle, float width, int internalColor, int borderColor) {
        float x2 = rectangle.x;
        float y2 = rectangle.y;
        float x22 = rectangle.x + rectangle.width;
        float y22 = rectangle.y + rectangle.height;
        GuiUtils.enableGL2D();
        GuiUtils.glColor(internalColor);
        GuiUtils.drawRect(x2 + width, y2 + width, x22 - width, y22 - width);
        GuiUtils.glColor(borderColor);
        GuiUtils.drawRect(x2 + 1.0f, y2, x22 - 1.0f, y2 + width);
        GuiUtils.drawRect(x2, y2, x2 + width, y22);
        GuiUtils.drawRect(x22 - width, y2, x22, y22);
        GuiUtils.drawRect(x2 + 1.0f, y22 - width, x22 - 1.0f, y22);
        GuiUtils.disableGL2D();
    }

    public static void drawGradientRect(float x2, float y2, float x1, float y1, int topColor, int bottomColor) {
        GuiUtils.enableGL2D();
        GL11.glShadeModel(7425);
        GL11.glBegin(7);
        GuiUtils.glColor(topColor);
        GL11.glVertex2f(x2, y1);
        GL11.glVertex2f(x1, y1);
        GuiUtils.glColor(bottomColor);
        GL11.glVertex2f(x1, y2);
        GL11.glVertex2f(x2, y2);
        GL11.glEnd();
        GL11.glShadeModel(7424);
        GuiUtils.disableGL2D();
    }

    public static void drawGradientHRect(float x2, float y2, float x1, float y1, int topColor, int bottomColor) {
        GuiUtils.enableGL2D();
        GL11.glShadeModel(7425);
        GL11.glBegin(7);
        GuiUtils.glColor(topColor);
        GL11.glVertex2f(x2, y2);
        GL11.glVertex2f(x2, y1);
        GuiUtils.glColor(bottomColor);
        GL11.glVertex2f(x1, y1);
        GL11.glVertex2f(x1, y2);
        GL11.glEnd();
        GL11.glShadeModel(7424);
        GuiUtils.disableGL2D();
    }

    public static void drawGradientRect(double x2, double y2, double x22, double y22, int col1, int col2) {
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GuiUtils.glColor(col1);
        GL11.glVertex2d(x22, y2);
        GL11.glVertex2d(x2, y2);
        GuiUtils.glColor(col2);
        GL11.glVertex2d(x2, y22);
        GL11.glVertex2d(x22, y22);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glShadeModel(7424);
    }

    public static void drawGradientBorderedRect(double x2, double y2, double x22, double y22, float l1, int col1, int col2, int col3) {
        GuiUtils.enableGL2D();
        GL11.glPushMatrix();
        GuiUtils.glColor(col1);
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
        GuiUtils.drawGradientRect(x2, y2, x22, y22, col2, col3);
        GuiUtils.disableGL2D();
    }

    public static void glColor(Color color) {
        GL11.glColor4f((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
    }

    public static void glColor(int hex) {
        float alpha = (float)(hex >> 24 & 0xFF) / 255.0f;
        float red = (float)(hex >> 16 & 0xFF) / 255.0f;
        float green = (float)(hex >> 8 & 0xFF) / 255.0f;
        float blue = (float)(hex & 0xFF) / 255.0f;
        GL11.glColor4f(red, green, blue, alpha);
    }

    public static void glColor(float alpha, int redRGB, int greenRGB, int blueRGB) {
        float red = 0.003921569f * (float)redRGB;
        float green = 0.003921569f * (float)greenRGB;
        float blue = 0.003921569f * (float)blueRGB;
        GL11.glColor4f(red, green, blue, alpha);
    }

    public static void drawStrip(int x2, int y2, float width, double angle, float points, float radius, int color) {
        float yc2;
        float xc2;
        float a2;
        int i2;
        float f1 = (float)(color >> 24 & 0xFF) / 255.0f;
        float f2 = (float)(color >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(color & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glTranslated(x2, y2, 0.0);
        GL11.glColor4f(f2, f3, f4, f1);
        GL11.glLineWidth(width);
        if (angle > 0.0) {
            GL11.glBegin(3);
            i2 = 0;
            while ((double)i2 < angle) {
                a2 = (float)((double)i2 * (angle * Math.PI / (double)points));
                xc2 = (float)(Math.cos(a2) * (double)radius);
                yc2 = (float)(Math.sin(a2) * (double)radius);
                GL11.glVertex2f(xc2, yc2);
                ++i2;
            }
            GL11.glEnd();
        }
        if (angle < 0.0) {
            GL11.glBegin(3);
            i2 = 0;
            while ((double)i2 > angle) {
                a2 = (float)((double)i2 * (angle * Math.PI / (double)points));
                xc2 = (float)(Math.cos(a2) * (double)(-radius));
                yc2 = (float)(Math.sin(a2) * (double)(-radius));
                GL11.glVertex2f(xc2, yc2);
                --i2;
            }
            GL11.glEnd();
        }
        GuiUtils.disableGL2D();
        GL11.glDisable(3479);
        GL11.glPopMatrix();
    }

    public static void drawHLine(float x2, float y2, float x1, int y1) {
        if (y2 < x2) {
            float var5 = x2;
            x2 = y2;
            y2 = var5;
        }
        GuiUtils.drawRect(x2, x1, y2 + 1.0f, x1 + 1.0f, y1);
    }

    public static void drawVLine(float x2, float y2, float x1, int y1) {
        if (x1 < y2) {
            float var5 = y2;
            y2 = x1;
            x1 = var5;
        }
        GuiUtils.drawRect(x2, y2 + 1.0f, x2 + 1.0f, x1, y1);
    }

    public static void drawHLine(float x2, float y2, float x1, int y1, int y22) {
        if (y2 < x2) {
            float var5 = x2;
            x2 = y2;
            y2 = var5;
        }
        GuiUtils.drawGradientRect(x2, x1, y2 + 1.0f, x1 + 1.0f, y1, y22);
    }

    public static void drawRect(float x2, float y2, float x1, float y1, float r2, float g2, float b2, float a2) {
        GuiUtils.enableGL2D();
        GL11.glColor4f(r2, g2, b2, a2);
        GuiUtils.drawRect(x2, y2, x1, y1);
        GuiUtils.disableGL2D();
    }

    public static void drawRect(float x2, float y2, float x1, float y1) {
        GL11.glBegin(7);
        GL11.glVertex2f(x2, y1);
        GL11.glVertex2f(x1, y1);
        GL11.glVertex2f(x1, y2);
        GL11.glVertex2f(x2, y2);
        GL11.glEnd();
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
        GuiUtils.enableGL2D();
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
        GuiUtils.disableGL2D();
    }

    public static void drawFullCircle(int cx, int cy2, double r2, int c2) {
        r2 *= 2.0;
        cx *= 2;
        cy2 *= 2;
        float f2 = (float)(c2 >> 24 & 0xFF) / 255.0f;
        float f22 = (float)(c2 >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(c2 >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(c2 & 0xFF) / 255.0f;
        GuiUtils.enableGL2D();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        GL11.glColor4f(f22, f3, f4, f2);
        GL11.glBegin(6);
        for (int i2 = 0; i2 <= 360; ++i2) {
            double x2 = Math.sin((double)i2 * Math.PI / 180.0) * r2;
            double y2 = Math.cos((double)i2 * Math.PI / 180.0) * r2;
            GL11.glVertex2d((double)cx + x2, (double)cy2 + y2);
        }
        GL11.glEnd();
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        GuiUtils.disableGL2D();
    }

    public static void drawSmallString(String s, int x2, int y2, int color) {
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        Minecraft.getMinecraft();
        Minecraft.fontRendererObj.drawString(s, x2 * 2, y2 * 2, color);
        GL11.glPopMatrix();
    }

    public static void drawLargeString(String text, int x2, int y2, int color) {
        GL11.glPushMatrix();
        GL11.glScalef(1.5f, 1.5f, 1.5f);
        Minecraft.getMinecraft();
        Minecraft.fontRendererObj.drawString(text, x2 *= 2, y2, color);
        GL11.glPopMatrix();
    }

    public static ScaledResolution getScaledResolution() {
        return scaledResolution;
    }

    public static void draw2D(Entity e2, double posX, double posY, double posZ, float alpha, float red, float green, float blue) {
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
        GuiUtils.drawOutlineRect(-6.2f, -19.2f, 6.2f, 2.2f, new Color(0, 0, 0, 80).getRGB());
        GuiUtils.drawRect(-5.8f, -18.8f, 5.8f, 1.8f, new Color(0, 0, 0, 80).getRGB());
        GuiUtils.drawOutlinedRect(-6.0f, -19.0f, 6.0f, 2.0f, alpha, red, green, blue);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glEnable(2896);
        GlStateManager.popMatrix();
    }

    public static void drawRectColor(double d2, double e2, double f2, double f3, float alpha, float red, float green, float blue) {
        GlStateManager.enableBlend();
        GuiUtils.enableGL2D();
        GlStateManager.func_179090_x();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glPushMatrix();
        GL11.glColor4f(alpha, red, green, blue);
        GL11.glBegin(7);
        GL11.glVertex2d(f2, e2);
        GL11.glVertex2d(d2, e2);
        GL11.glVertex2d(d2, f3);
        GL11.glVertex2d(f2, f3);
        GL11.glEnd();
        GuiUtils.disableGL2D();
        GlStateManager.func_179098_w();
        GlStateManager.disableBlend();
        GL11.glPopMatrix();
    }

    public static void drawOutlineRect(float drawX, float drawY, float drawWidth, float drawHeight, int color) {
        GuiUtils.drawRect(drawX, drawY, drawWidth, drawY + 0.5f, color);
        GuiUtils.drawRect(drawX, drawY + 0.5f, drawX + 0.5f, drawHeight, color);
        GuiUtils.drawRect(drawWidth - 0.5f, drawY + 0.5f, drawWidth, drawHeight - 0.5f, color);
        GuiUtils.drawRect(drawX + 0.5f, drawHeight - 0.5f, drawWidth, drawHeight, color);
    }

    public static void drawOutlinedRect(float drawX, float drawY, float drawWidth, float drawHeight, float alpha, float red, float green, float blue) {
        GuiUtils.drawRectColor(drawX, drawY, drawWidth, drawY + 0.5f, alpha, red, green, blue);
        GuiUtils.drawRectColor(drawX, drawY + 0.5f, drawX + 0.5f, drawHeight, alpha, red, green, blue);
        GuiUtils.drawRectColor(drawWidth - 0.5f, drawY + 0.5f, drawWidth, drawHeight - 0.5f, alpha, red, green, blue);
        GuiUtils.drawRectColor(drawX + 0.5f, drawHeight - 0.5f, drawWidth, drawHeight, alpha, red, green, blue);
    }
}

