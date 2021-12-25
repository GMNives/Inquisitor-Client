/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.utils.render;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import me.aristhena.event.events.Render3DEvent;
import me.aristhena.utils.ClientUtils;
import me.aristhena.utils.render.RenderBox;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import org.lwjgl.opengl.GL11;

public final class RenderingUtils {
    public static final RenderItem RENDER_ITEM = new RenderItem(Minecraft.getMinecraft().renderEngine, Minecraft.getMinecraft().modelManager);
    private static ScaledResolution scaledResolution;
    private static int Y;
    private static int pY;
    private static float pY2;
    private static int rectY;

    static {
        Y = 22;
        pY = 0;
        pY2 = 0.0f;
        rectY = 32;
    }

    public static void drawSearchBlock(Block block, BlockPos blockPos, Render3DEvent event) {
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        GlStateManager.pushMatrix();
        GL11.glLineWidth(1.0f);
        GlStateManager.disableDepth();
        RenderingUtils.disableLighting();
        double var8 = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double)event.getPartialTicks();
        double var10 = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double)event.getPartialTicks();
        double var12 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double)event.getPartialTicks();
        Minecraft.getMinecraft();
        RenderGlobal.drawOutlinedBoundingBox(block.getSelectedBoundingBox(Minecraft.theWorld, blockPos).expand(0.002f, 0.002f, 0.002f).offset(-var8, -var10, -var12), -1);
        GlStateManager.popMatrix();
    }

    public static void drawBoundingBox(AxisAlignedBB axisalignedbb) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrender = Tessellator.getInstance().getWorldRenderer();
        worldrender.startDrawingQuads();
        worldrender.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        worldrender.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        worldrender.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        worldrender.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        worldrender.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        worldrender.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        worldrender.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        worldrender.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        worldrender.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        worldrender.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        worldrender.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        worldrender.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        worldrender.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        worldrender.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        worldrender.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        worldrender.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        worldrender.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        worldrender.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        worldrender.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        worldrender.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        worldrender.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        worldrender.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        worldrender.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        worldrender.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        worldrender.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        worldrender.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        worldrender.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        worldrender.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        worldrender.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        worldrender.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        worldrender.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        worldrender.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        worldrender.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        worldrender.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        worldrender.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        worldrender.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        worldrender.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        worldrender.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        worldrender.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        worldrender.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        worldrender.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        worldrender.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        worldrender.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        worldrender.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        worldrender.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        worldrender.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        worldrender.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        worldrender.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.draw();
    }

    public static void drawOutlinedBlockESP(double x2, double y2, double z2, float red, float green, float blue, float alpha, float lineWidth, int hashcode) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glLineWidth(lineWidth);
        GL11.glColor4f(red, green, blue, alpha);
        RenderingUtils.drawOutlinedBoundingBox(new AxisAlignedBB(x2, y2, z2, x2 + 1.0, y2 + 1.0, z2 + 1.0));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
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

    public static void drawEsp(EntityLivingBase ent, float pTicks, int hexColor, int hexColorIn) {
        if (ent.isEntityAlive()) {
            double x2 = RenderingUtils.getDiff(ent.lastTickPosX, ent.posX, pTicks, RenderManager.renderPosX);
            double y2 = RenderingUtils.getDiff(ent.lastTickPosY, ent.posY, pTicks, RenderManager.renderPosY);
            double z2 = RenderingUtils.getDiff(ent.lastTickPosZ, ent.posZ, pTicks, RenderManager.renderPosZ);
            RenderingUtils.boundingBox(ent, x2, y2, z2, hexColor, hexColorIn);
        }
    }

    public static void boundingBox(Entity entity, double x2, double y2, double z2, int color, int colorIn) {
        GlStateManager.pushMatrix();
        GL11.glLineWidth(1.0f);
        AxisAlignedBB var11 = entity.getEntityBoundingBox();
        AxisAlignedBB var12 = new AxisAlignedBB(var11.minX - entity.posX + x2, var11.minY - entity.posY + y2, var11.minZ - entity.posZ + z2, var11.maxX - entity.posX + x2, var11.maxY - entity.posY + y2, var11.maxZ - entity.posZ + z2);
        if (color != 0) {
            GlStateManager.disableDepth();
            RenderingUtils.filledBox(var12, colorIn, true);
            RenderingUtils.disableLighting();
            RenderGlobal.drawOutlinedBoundingBox(var12, color);
        }
        GlStateManager.popMatrix();
    }

    public static void enableLighting() {
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glDisable(2848);
        GL11.glDisable(3042);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glMatrixMode(5890);
        GL11.glLoadIdentity();
        float var3 = 0.0039063f;
        GL11.glScalef(var3, var3, var3);
        GL11.glTranslatef(8.0f, 8.0f, 8.0f);
        GL11.glMatrixMode(5888);
        GL11.glTexParameteri(3553, 10241, 9729);
        GL11.glTexParameteri(3553, 10240, 9729);
        GL11.glTexParameteri(3553, 10242, 10496);
        GL11.glTexParameteri(3553, 10243, 10496);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    public static void disableLighting() {
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(3553);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glDisable(2896);
        GL11.glDisable(3553);
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

    public static void disableGL2D() {
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }

    public static void enableGL3D(float lineWidth) {
        GL11.glDisable(3008);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glEnable(2884);
        Minecraft.getMinecraft().entityRenderer.disableLightmap();
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        GL11.glLineWidth(lineWidth);
    }

    public static void disableGL3D() {
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(3042);
        GL11.glEnable(3008);
        GL11.glDepthMask(true);
        GL11.glCullFace(1029);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }

    public static void drawRect(float x2, float y2, float x1, float y1, int color) {
        RenderingUtils.enableGL2D();
        RenderingUtils.glColor(color);
        RenderingUtils.drawRect(x2, y2, x1, y1);
        RenderingUtils.disableGL2D();
    }

    public static void drawBorderedRect(float x2, float y2, float x1, float y1, float width, int internalColor, int borderColor) {
        RenderingUtils.enableGL2D();
        RenderingUtils.glColor(internalColor);
        RenderingUtils.drawRect(x2 + width, y2 + width, x1 - width, y1 - width);
        RenderingUtils.glColor(borderColor);
        RenderingUtils.drawRect(x2 + width, y2, x1 - width, y2 + width);
        RenderingUtils.drawRect(x2, y2, x2 + width, y1);
        RenderingUtils.drawRect(x1 - width, y2, x1, y1);
        RenderingUtils.drawRect(x2 + width, y1 - width, x1 - width, y1);
        RenderingUtils.disableGL2D();
    }

    public static void drawBorderedRect(int x2, int y2, int x1, int y1, int insideC, int borderC) {
        RenderingUtils.enableGL2D();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        RenderingUtils.drawVLine(x2 *= 2, y2 *= 2, (y1 *= 2) - 1, borderC);
        RenderingUtils.drawVLine((x1 *= 2) - 1, y2, y1, borderC);
        RenderingUtils.drawHLine(x2, x1 - 1, y2, borderC);
        RenderingUtils.drawHLine(x2, x1 - 2, y1 - 1, borderC);
        RenderingUtils.drawRect(x2 + 1, y2 + 1, x1 - 1, y1 - 1, insideC);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        RenderingUtils.disableGL2D();
    }

    public static void drawBorderedRectReliant(float x2, float y2, float x1, float y1, float lineWidth, int inside, int border) {
        RenderingUtils.enableGL2D();
        RenderingUtils.drawRect(x2, y2, x1, y1, inside);
        RenderingUtils.glColor(border);
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
        RenderingUtils.disableGL2D();
    }

    public static void drawGradientBorderedRectReliant(float x2, float y2, float x1, float y1, float lineWidth, int border, int bottom, int top) {
        RenderingUtils.enableGL2D();
        RenderingUtils.drawGradientRect(x2, y2, x1, y1, top, bottom);
        RenderingUtils.glColor(border);
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
        RenderingUtils.disableGL2D();
    }

    public static void drawBorderedRect(Rectangle rectangle, float width, int internalColor, int borderColor) {
        float x2 = rectangle.x;
        float y2 = rectangle.y;
        float x1 = rectangle.x + rectangle.width;
        float y1 = rectangle.y + rectangle.height;
        RenderingUtils.enableGL2D();
        RenderingUtils.glColor(internalColor);
        RenderingUtils.drawRect(x2 + width, y2 + width, x1 - width, y1 - width);
        RenderingUtils.glColor(borderColor);
        RenderingUtils.drawRect(x2 + 1.0f, y2, x1 - 1.0f, y2 + width);
        RenderingUtils.drawRect(x2, y2, x2 + width, y1);
        RenderingUtils.drawRect(x1 - width, y2, x1, y1);
        RenderingUtils.drawRect(x2 + 1.0f, y1 - width, x1 - 1.0f, y1);
        RenderingUtils.disableGL2D();
    }

    public static void drawGradientRect(float x2, float y2, float x1, float y1, int topColor, int bottomColor) {
        RenderingUtils.enableGL2D();
        GL11.glShadeModel(7425);
        GL11.glBegin(7);
        RenderingUtils.glColor(topColor);
        GL11.glVertex2f(x2, y1);
        GL11.glVertex2f(x1, y1);
        RenderingUtils.glColor(bottomColor);
        GL11.glVertex2f(x1, y2);
        GL11.glVertex2f(x2, y2);
        GL11.glEnd();
        GL11.glShadeModel(7424);
        RenderingUtils.disableGL2D();
    }

    public static void drawGradientHRect(float x2, float y2, float x1, float y1, int topColor, int bottomColor) {
        RenderingUtils.enableGL2D();
        GL11.glShadeModel(7425);
        GL11.glBegin(7);
        RenderingUtils.glColor(topColor);
        GL11.glVertex2f(x2, y2);
        GL11.glVertex2f(x2, y1);
        RenderingUtils.glColor(bottomColor);
        GL11.glVertex2f(x1, y1);
        GL11.glVertex2f(x1, y2);
        GL11.glEnd();
        GL11.glShadeModel(7424);
        RenderingUtils.disableGL2D();
    }

    public static void drawGradientRect(double x2, double y2, double x22, double y22, int col1, int col2) {
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        RenderingUtils.glColor(col1);
        GL11.glVertex2d(x22, y2);
        GL11.glVertex2d(x2, y2);
        RenderingUtils.glColor(col2);
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
        RenderingUtils.enableGL2D();
        GL11.glPushMatrix();
        RenderingUtils.glColor(col1);
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
        RenderingUtils.drawGradientRect(x2, y2, x22, y22, col2, col3);
        RenderingUtils.disableGL2D();
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
        RenderingUtils.disableGL2D();
        GL11.glDisable(3479);
        GL11.glPopMatrix();
    }

    public static void drawHLine(float x2, float y2, float x1, int y1) {
        if (y2 < x2) {
            float var5 = x2;
            x2 = y2;
            y2 = var5;
        }
        RenderingUtils.drawRect(x2, x1, y2 + 1.0f, x1 + 1.0f, y1);
    }

    public static void drawVLine(float x2, float y2, float x1, int y1) {
        if (x1 < y2) {
            float var5 = y2;
            y2 = x1;
            x1 = var5;
        }
        RenderingUtils.drawRect(x2, y2 + 1.0f, x2 + 1.0f, x1, y1);
    }

    public static void drawHLine(float x2, float y2, float x1, int y1, int y22) {
        if (y2 < x2) {
            float var5 = x2;
            x2 = y2;
            y2 = var5;
        }
        RenderingUtils.drawGradientRect(x2, x1, y2 + 1.0f, x1 + 1.0f, y1, y22);
    }

    public static void drawRect(float x2, float y2, float x1, float y1, float r2, float g2, float b2, float a2) {
        RenderingUtils.enableGL2D();
        GL11.glColor4f(r2, g2, b2, a2);
        RenderingUtils.drawRect(x2, y2, x1, y1);
        RenderingUtils.disableGL2D();
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
        GL11.glPushMatrix();
        cx *= 2.0f;
        cy2 *= 2.0f;
        float f2 = (float)(c2 >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(c2 >> 16 & 0xFF) / 255.0f;
        float f22 = (float)(c2 >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(c2 & 0xFF) / 255.0f;
        float theta = (float)(6.2831852 / (double)num_segments);
        float p2 = (float)Math.cos(theta);
        float s = (float)Math.sin(theta);
        float x2 = r2 *= 2.0f;
        float y2 = 0.0f;
        RenderingUtils.enableGL2D();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        GL11.glColor4f(f1, f22, f3, f2);
        GL11.glBegin(2);
        for (int ii2 = 0; ii2 < num_segments; ++ii2) {
            GL11.glVertex2f(x2 + cx, y2 + cy2);
            float t = x2;
            x2 = p2 * x2 - s * y2;
            y2 = s * t + p2 * y2;
        }
        GL11.glEnd();
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        RenderingUtils.disableGL2D();
        GL11.glPopMatrix();
    }

    public static void drawFullCircle(int cx, int cy2, double r2, int c2) {
        r2 *= 2.0;
        cx *= 2;
        cy2 *= 2;
        float f2 = (float)(c2 >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(c2 >> 16 & 0xFF) / 255.0f;
        float f22 = (float)(c2 >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(c2 & 0xFF) / 255.0f;
        RenderingUtils.enableGL2D();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        GL11.glColor4f(f1, f22, f3, f2);
        GL11.glBegin(6);
        for (int i2 = 0; i2 <= 360; ++i2) {
            double x2 = Math.sin((double)i2 * Math.PI / 180.0) * r2;
            double y2 = Math.cos((double)i2 * Math.PI / 180.0) * r2;
            GL11.glVertex2d((double)cx + x2, (double)cy2 + y2);
        }
        GL11.glEnd();
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        RenderingUtils.disableGL2D();
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

    public static void updateScaledResolution() {
        scaledResolution = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
    }

    public static ScaledResolution getScaledResolution() {
        return scaledResolution;
    }

    public static void prepareScissorBox(float x2, float y2, float x22, float y22) {
        RenderingUtils.updateScaledResolution();
        int factor = scaledResolution.getScaleFactor();
        GL11.glScissor((int)(x2 * (float)factor), (int)(((float)scaledResolution.getScaledHeight() - y22) * (float)factor), (int)((x22 - x2) * (float)factor), (int)((y22 - y2) * (float)factor));
    }

    public static void drawOutlinedBox(AxisAlignedBB boundingBox) {
        if (boundingBox != null) {
            GL11.glBegin(3);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxZ, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxZ, boundingBox.maxZ, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxZ, boundingBox.maxZ, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxZ, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxZ, boundingBox.maxZ);
            GL11.glEnd();
            GL11.glBegin(3);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxZ, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxZ, boundingBox.maxZ, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxZ, boundingBox.maxZ, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxZ, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxZ, boundingBox.maxZ);
            GL11.glEnd();
            GL11.glBegin(1);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxZ, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxZ, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxZ, boundingBox.maxZ, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxZ, boundingBox.maxZ, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxZ, boundingBox.maxZ, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.maxZ, boundingBox.maxZ, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxZ, boundingBox.maxZ);
            GL11.glVertex3d(boundingBox.minX, boundingBox.maxZ, boundingBox.maxZ);
            GL11.glEnd();
        }
    }

    public static void drawBox(RenderBox box) {
        GL11.glEnable(1537);
        if (box != null) {
            GL11.glBegin(7);
            GL11.glVertex3d(box.minX, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.maxZ, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.maxZ, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.minX, box.maxZ, box.maxZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.maxZ, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.minX, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.minX, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.maxZ, box.maxZ, box.maxZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.minX, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.minX, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.minX, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.minX, box.maxZ, box.maxZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.minX, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.minX, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.minX, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.minX, box.maxZ, box.maxZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.maxZ, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.maxZ, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.maxZ, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.maxZ, box.maxZ, box.maxZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.maxZ, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.maxZ, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.maxZ, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.maxZ, box.maxZ, box.maxZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.minX, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.maxZ, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.maxZ, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.minX, box.maxZ, box.maxZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.maxZ, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.minX, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.minX, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.maxZ, box.maxZ, box.maxZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.minX, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.maxZ, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.maxZ, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.minX, box.maxZ, box.maxZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.maxZ, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.minX, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.minX, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.maxZ, box.maxZ, box.maxZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.minX, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.maxZ, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.maxZ, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.minX, box.maxZ, box.maxZ);
            GL11.glEnd();
            GL11.glBegin(7);
            GL11.glVertex3d(box.maxZ, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.minX, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.minX, box.maxZ, box.maxZ);
            GL11.glVertex3d(box.maxZ, box.maxZ, box.maxZ);
            GL11.glEnd();
        }
    }

    public static void filledBox(AxisAlignedBB aa2, int color, boolean shouldColor) {
        GlStateManager.pushMatrix();
        float var11 = (float)(color >> 24 & 0xFF) / 255.0f;
        float var6 = (float)(color >> 16 & 0xFF) / 255.0f;
        float var7 = (float)(color >> 8 & 0xFF) / 255.0f;
        float var8 = (float)(color & 0xFF) / 255.0f;
        Tessellator var9 = Tessellator.getInstance();
        WorldRenderer t = var9.getWorldRenderer();
        if (shouldColor) {
            GlStateManager.color(var6, var7, var8, var11);
        }
        int draw = 7;
        t.startDrawing(draw);
        t.addVertex(aa2.minX, aa2.minY, aa2.minZ);
        t.addVertex(aa2.minX, aa2.maxY, aa2.minZ);
        t.addVertex(aa2.maxX, aa2.minY, aa2.minZ);
        t.addVertex(aa2.maxX, aa2.maxY, aa2.minZ);
        t.addVertex(aa2.maxX, aa2.minY, aa2.maxZ);
        t.addVertex(aa2.maxX, aa2.maxY, aa2.maxZ);
        t.addVertex(aa2.minX, aa2.minY, aa2.maxZ);
        t.addVertex(aa2.minX, aa2.maxY, aa2.maxZ);
        var9.draw();
        t.startDrawing(draw);
        t.addVertex(aa2.maxX, aa2.maxY, aa2.minZ);
        t.addVertex(aa2.maxX, aa2.minY, aa2.minZ);
        t.addVertex(aa2.minX, aa2.maxY, aa2.minZ);
        t.addVertex(aa2.minX, aa2.minY, aa2.minZ);
        t.addVertex(aa2.minX, aa2.maxY, aa2.maxZ);
        t.addVertex(aa2.minX, aa2.minY, aa2.maxZ);
        t.addVertex(aa2.maxX, aa2.maxY, aa2.maxZ);
        t.addVertex(aa2.maxX, aa2.minY, aa2.maxZ);
        var9.draw();
        t.startDrawing(draw);
        t.addVertex(aa2.minX, aa2.maxY, aa2.minZ);
        t.addVertex(aa2.maxX, aa2.maxY, aa2.minZ);
        t.addVertex(aa2.maxX, aa2.maxY, aa2.maxZ);
        t.addVertex(aa2.minX, aa2.maxY, aa2.maxZ);
        t.addVertex(aa2.minX, aa2.maxY, aa2.minZ);
        t.addVertex(aa2.minX, aa2.maxY, aa2.maxZ);
        t.addVertex(aa2.maxX, aa2.maxY, aa2.maxZ);
        t.addVertex(aa2.maxX, aa2.maxY, aa2.minZ);
        var9.draw();
        t.startDrawing(draw);
        t.addVertex(aa2.minX, aa2.minY, aa2.minZ);
        t.addVertex(aa2.maxX, aa2.minY, aa2.minZ);
        t.addVertex(aa2.maxX, aa2.minY, aa2.maxZ);
        t.addVertex(aa2.minX, aa2.minY, aa2.maxZ);
        t.addVertex(aa2.minX, aa2.minY, aa2.minZ);
        t.addVertex(aa2.minX, aa2.minY, aa2.maxZ);
        t.addVertex(aa2.maxX, aa2.minY, aa2.maxZ);
        t.addVertex(aa2.maxX, aa2.minY, aa2.minZ);
        var9.draw();
        t.startDrawing(draw);
        t.addVertex(aa2.minX, aa2.minY, aa2.minZ);
        t.addVertex(aa2.minX, aa2.maxY, aa2.minZ);
        t.addVertex(aa2.minX, aa2.minY, aa2.maxZ);
        t.addVertex(aa2.minX, aa2.maxY, aa2.maxZ);
        t.addVertex(aa2.maxX, aa2.minY, aa2.maxZ);
        t.addVertex(aa2.maxX, aa2.maxY, aa2.maxZ);
        t.addVertex(aa2.maxX, aa2.minY, aa2.minZ);
        t.addVertex(aa2.maxX, aa2.maxY, aa2.minZ);
        var9.draw();
        t.startDrawing(draw);
        t.addVertex(aa2.minX, aa2.maxY, aa2.maxZ);
        t.addVertex(aa2.minX, aa2.minY, aa2.maxZ);
        t.addVertex(aa2.minX, aa2.maxY, aa2.minZ);
        t.addVertex(aa2.minX, aa2.minY, aa2.minZ);
        t.addVertex(aa2.maxX, aa2.maxY, aa2.minZ);
        t.addVertex(aa2.maxX, aa2.minY, aa2.minZ);
        t.addVertex(aa2.maxX, aa2.maxY, aa2.maxZ);
        t.addVertex(aa2.maxX, aa2.minY, aa2.maxZ);
        var9.draw();
        GlStateManager.depthMask(true);
        GlStateManager.popMatrix();
    }

    private static double getDiff(double lastI, double i2, float ticks, double ownI) {
        return lastI + (i2 - lastI) * (double)ticks - ownI;
    }

    public static void drawBeacon(BlockPos pos, int color, int colorIn, float partialTicks) {
        GlStateManager.pushMatrix();
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        double x2 = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double)partialTicks;
        double y2 = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double)partialTicks;
        double z2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double)partialTicks;
        GL11.glLineWidth(1.0f);
        AxisAlignedBB var11 = new AxisAlignedBB(pos.getX() + 1, pos.getY(), pos.getZ() + 1, pos.getX(), pos.getY() + 200, pos.getZ());
        AxisAlignedBB var12 = new AxisAlignedBB(var11.minX - x2, var11.minY - y2, var11.minZ - z2, var11.maxX - x2, var11.maxY - y2, var11.maxZ - z2);
        if (color != 0) {
            GlStateManager.disableDepth();
            RenderingUtils.filledBox(var12, colorIn, true);
            RenderingUtils.disableLighting();
            RenderGlobal.drawOutlinedBoundingBox(var12, color);
        }
        GlStateManager.popMatrix();
    }

    public static Color getRainbow(long offset, float fade) {
        float hue = (float)(System.nanoTime() + offset) / 5.0E9f % 1.0f;
        long color = Long.parseLong(Integer.toHexString(Color.HSBtoRGB(hue, 1.0f, 1.0f)), 16);
        Color c2 = new Color((int)color);
        return new Color((float)c2.getRed() / 255.0f * fade, (float)c2.getGreen() / 255.0f * fade, (float)c2.getBlue() / 255.0f * fade, (float)c2.getAlpha() / 255.0f);
    }

    public static void drawPotionStatus(ScaledResolution sr2) {
        pY = 0;
        for (Object o : ClientUtils.mc().thePlayer.getActivePotionEffects()) {
        	PotionEffect effect = (PotionEffect)o;
        	Potion potion = Potion.potionTypes[effect.getPotionID()];
            String PType = I18n.format(potion.getName(), new Object[0]);
            if (effect.getAmplifier() == 1) {
                PType = String.valueOf(PType) + " II";
            } else if (effect.getAmplifier() == 2) {
                PType = String.valueOf(PType) + " III";
            } else if (effect.getAmplifier() == 3) {
                PType = String.valueOf(PType) + " IV";
            }
            if (effect.getDuration() < 600 && effect.getDuration() > 300) {
                PType = String.valueOf(PType) + "\u00c2\u00a77:\u00c2\u00a76 " + Potion.getDurationString(effect);
            } else if (effect.getDuration() < 300) {
                PType = String.valueOf(PType) + "\u00c2\u00a77:\u00c2\u00a7c " + Potion.getDurationString(effect);
            } else if (effect.getDuration() > 600) {
                PType = String.valueOf(PType) + "\u00c2\u00a77:\u00c2\u00a77 " + Potion.getDurationString(effect);
            }
            ClientUtils.clientFont().drawStringWithShadow(PType, sr2.getScaledWidth() - ClientUtils.clientFont().getStringWidth(PType), sr2.getScaledHeight() - ClientUtils.clientFont().FONT_HEIGHT + pY, potion.getLiquidColor());
            pY -= 9;
        }
    }
    

    public static void drawStuffStatus(ScaledResolution scaledRes) {
        boolean currentItem = true;
        GL11.glPushMatrix();
        new RenderItem(ClientUtils.mc().getTextureManager(), ClientUtils.mc().modelManager);
        ArrayList<ItemStack> stuff = new ArrayList<ItemStack>();
        boolean onwater = ClientUtils.mc().thePlayer.isEntityAlive() && ClientUtils.mc().thePlayer.isInsideOfMaterial(Material.water);
        int split = -3;
        for (int errything = 3; errything >= 0; --errything) {
            ItemStack armer = ClientUtils.mc().thePlayer.inventory.armorInventory[errything];
            if (armer == null) continue;
            stuff.add(armer);
        }
        if (ClientUtils.mc().thePlayer.getCurrentEquippedItem() != null && currentItem) {
            stuff.add(ClientUtils.mc().thePlayer.getCurrentEquippedItem());
        }
        for (ItemStack var9 : stuff) {
            ClientUtils.mc();
            if (Minecraft.theWorld != null) {
                RenderHelper.enableGUIStandardItemLighting();
                split += 16;
            }
            GlStateManager.pushMatrix();
            GlStateManager.disableAlpha();
            GlStateManager.clear(256);
            ClientUtils.mc().getRenderItem().zLevel = -150.0f;
            ClientUtils.mc().getRenderItem().func_180450_b(var9, split + scaledRes.getScaledWidth() / 2 - 4, scaledRes.getScaledHeight() - (onwater ? 65 : 55));
            RenderItem var10000 = ClientUtils.mc().getRenderItem();
            ClientUtils.mc();
            var10000.func_175030_a(Minecraft.fontRendererObj, var9, split + scaledRes.getScaledWidth() / 2 - 4, scaledRes.getScaledHeight() - (onwater ? 65 : 55));
            ClientUtils.mc().getRenderItem().zLevel = 0.0f;
            GlStateManager.disableBlend();
            GlStateManager.scale(0.5, 0.5, 0.5);
            GlStateManager.disableDepth();
            GlStateManager.disableLighting();
            RenderingUtils.renderEnchantText(var9, split + scaledRes.getScaledWidth() / 2 - 4, scaledRes.getScaledHeight() - (onwater ? 65 : 55));
            GlStateManager.enableDepth();
            GlStateManager.scale(2.0f, 2.0f, 2.0f);
            GlStateManager.enableAlpha();
            GlStateManager.popMatrix();
            NBTTagList nBTTagList = var9.getEnchantmentTagList();
        }
        GL11.glPopMatrix();
    }

    public static void renderEnchantText(ItemStack stack, int x2, int y2) {
        int uLevel;
        int fLevel;
        int kLevel;
        int sLevel;
        int encY = y2 + 276;
        if (stack.getItem() instanceof ItemArmor) {
            sLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId, stack);
            kLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.thorns.effectId, stack);
            fLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack);
            if (sLevel > 0) {
                ClientUtils.clientFont().drawStringWithShadow("\u00c2\u00a7fp" + sLevel, x2 * 2, encY, 0xFFFFFF);
                encY += 7;
            }
            if (kLevel > 0) {
                ClientUtils.clientFont().drawStringWithShadow("\u00c2\u00a7ft" + kLevel, x2 * 2, encY, 0xFFFFFF);
                encY += 7;
            }
            if (fLevel > 0) {
                ClientUtils.clientFont().drawStringWithShadow("\u00c2\u00a7fu" + fLevel, x2 * 2, encY, 0xFFFFFF);
                encY += 7;
            }
        }
        if (stack.getItem() instanceof ItemBow) {
            sLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);
            kLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);
            fLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack);
            uLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack);
            if (sLevel > 0) {
                ClientUtils.clientFont().drawStringWithShadow("\u00c2\u00a7fd" + sLevel, x2 * 2, encY, 0xFFFFFF);
                encY += 7;
            }
            if (kLevel > 0) {
                ClientUtils.clientFont().drawStringWithShadow("\u00c2\u00a7fk" + kLevel, x2 * 2, encY, 0xFFFFFF);
                encY += 7;
            }
            if (fLevel > 0) {
                ClientUtils.clientFont().drawStringWithShadow("\u00c2\u00a7ff" + fLevel, x2 * 2, encY, 0xFFFFFF);
                encY += 7;
            }
            if (uLevel > 0) {
                ClientUtils.clientFont().drawStringWithShadow("\u00c2\u00a7fu" + uLevel, x2 * 2, encY, 0xFFFFFF);
                encY += 7;
            }
        }
        if (stack.getItem() instanceof ItemSword) {
            sLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack);
            kLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.knockback.effectId, stack);
            fLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, stack);
            uLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack);
            if (sLevel > 0) {
                ClientUtils.clientFont().drawStringWithShadow("\u00c2\u00a7fs" + sLevel, x2 * 2, encY, 0xFFFFFF);
                encY += 7;
            }
            if (kLevel > 0) {
                ClientUtils.clientFont().drawStringWithShadow("\u00c2\u00a7fk" + kLevel, x2 * 2, encY, 0xFFFFFF);
                encY += 7;
            }
            if (fLevel > 0) {
                ClientUtils.clientFont().drawStringWithShadow("\u00c2\u00a7ff" + fLevel, x2 * 2, encY, 0xFFFFFF);
                encY += 7;
            }
            if (uLevel > 0) {
                ClientUtils.clientFont().drawStringWithShadow("\u00c2\u00a7fu" + uLevel, x2 * 2, encY, 0xFFFFFF);
            }
        }
    }
}

