/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.utils.render;

import java.awt.Color;
import java.util.ArrayList;
import me.aristhena.utils.ClientUtils;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

public final class RenderUtils {
    public static void drawEsp(EntityLivingBase ent, float pTicks, int hexColor, int hexColorIn) {
        if (ent.isEntityAlive()) {
            double x2 = RenderUtils.getDiff(ent.lastTickPosX, ent.posX, pTicks, RenderManager.renderPosX);
            double y2 = RenderUtils.getDiff(ent.lastTickPosY, ent.posY, pTicks, RenderManager.renderPosY);
            double z2 = RenderUtils.getDiff(ent.lastTickPosZ, ent.posZ, pTicks, RenderManager.renderPosZ);
            RenderUtils.boundingBox(ent, x2, y2, z2, hexColor, hexColorIn);
        }
    }

    public static void boundingBox(Entity entity, double x2, double y2, double z2, int color, int colorIn) {
        GlStateManager.pushMatrix();
        GL11.glLineWidth(1.0f);
        AxisAlignedBB var11 = entity.getEntityBoundingBox();
        AxisAlignedBB var12 = new AxisAlignedBB(var11.minX - entity.posX + x2, var11.minY - entity.posY + y2, var11.minZ - entity.posZ + z2, var11.maxX - entity.posX + x2, var11.maxY - entity.posY + y2, var11.maxZ - entity.posZ + z2);
        if (color != 0) {
            GlStateManager.disableDepth();
            RenderUtils.filledBox(var12, colorIn, true);
            GlStateManager.disableLighting();
        }
        GlStateManager.popMatrix();
    }

    public static void filledBox(AxisAlignedBB boundingBox, int color, boolean shouldColor) {
        GlStateManager.pushMatrix();
        float var11 = (float)(color >> 24 & 0xFF) / 255.0f;
        float var12 = (float)(color >> 16 & 0xFF) / 255.0f;
        float var13 = (float)(color >> 8 & 0xFF) / 255.0f;
        float var14 = (float)(color & 0xFF) / 255.0f;
        WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
        if (shouldColor) {
            GlStateManager.color(var12, var13, var14, var11);
        }
        boolean draw = true;
        worldRenderer.startDrawing(7);
        worldRenderer.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
        worldRenderer.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
        worldRenderer.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
        worldRenderer.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
        worldRenderer.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
        worldRenderer.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
        worldRenderer.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
        worldRenderer.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
        Tessellator.getInstance().draw();
        worldRenderer.startDrawing(7);
        worldRenderer.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
        worldRenderer.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
        worldRenderer.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
        worldRenderer.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
        worldRenderer.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
        worldRenderer.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
        worldRenderer.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
        worldRenderer.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
        Tessellator.getInstance().draw();
        worldRenderer.startDrawing(7);
        worldRenderer.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
        worldRenderer.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
        worldRenderer.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
        worldRenderer.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
        worldRenderer.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
        worldRenderer.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
        worldRenderer.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
        worldRenderer.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
        Tessellator.getInstance().draw();
        worldRenderer.startDrawing(7);
        worldRenderer.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
        worldRenderer.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
        worldRenderer.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
        worldRenderer.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
        worldRenderer.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
        worldRenderer.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
        worldRenderer.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
        worldRenderer.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
        Tessellator.getInstance().draw();
        worldRenderer.startDrawing(7);
        worldRenderer.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
        worldRenderer.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
        worldRenderer.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
        worldRenderer.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
        worldRenderer.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
        worldRenderer.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
        worldRenderer.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
        worldRenderer.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
        Tessellator.getInstance().draw();
        worldRenderer.startDrawing(7);
        worldRenderer.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
        worldRenderer.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.maxZ);
        worldRenderer.addVertex(boundingBox.minX, boundingBox.maxY, boundingBox.minZ);
        worldRenderer.addVertex(boundingBox.minX, boundingBox.minY, boundingBox.minZ);
        worldRenderer.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
        worldRenderer.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.minZ);
        worldRenderer.addVertex(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
        worldRenderer.addVertex(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
        Tessellator.getInstance().draw();
        GlStateManager.depthMask(true);
        GlStateManager.popMatrix();
    }

    public static void rectangle(double left, double top, double right, double bottom, int color) {
        double var6;
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
        float var61 = (float)(color >> 24 & 0xFF) / 255.0f;
        float var7 = (float)(color >> 16 & 0xFF) / 255.0f;
        float var8 = (float)(color >> 8 & 0xFF) / 255.0f;
        float var9 = (float)(color & 0xFF) / 255.0f;
        WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(var7, var8, var9, var61);
        worldRenderer.startDrawingQuads();
        worldRenderer.addVertex(left, bottom, 0.0);
        worldRenderer.addVertex(right, bottom, 0.0);
        worldRenderer.addVertex(right, top, 0.0);
        worldRenderer.addVertex(left, top, 0.0);
        Tessellator.getInstance().draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void rectangleBordered(double x2, double y2, double x1, double y1, double width, int internalColor, int borderColor, Side side) {
        RenderUtils.rectangle(x2 + width, y2 + width, x1 - width, y1 - width, internalColor);
        if (!side.equals((Object)Side.Top)) {
            RenderUtils.rectangle(x2 + width, y2, x1 - width, y2 + width, borderColor);
        }
        if (!side.equals((Object)Side.Left)) {
            RenderUtils.rectangle(x2, y2, x2 + width, y1, borderColor);
        }
        if (!side.equals((Object)Side.Right)) {
            RenderUtils.rectangle(x1 - width, y2, x1, y1, borderColor);
        }
        if (!side.equals((Object)Side.Bottom)) {
            RenderUtils.rectangle(x2 + width, y1 - width, x1 - width, y1, borderColor);
        }
    }

    public static void rectangleBordered(double x2, double y2, double x1, double y1, double width, int internalColor, int borderColor) {
        RenderUtils.rectangle(x2 + width, y2 + width, x1 - width, y1 - width, internalColor);
        RenderUtils.rectangle(x2 + width, y2, x1 - width, y2 + width, borderColor);
        RenderUtils.rectangle(x2, y2, x2 + width, y1, borderColor);
        RenderUtils.rectangle(x1 - width, y2, x1, y1, borderColor);
        RenderUtils.rectangle(x2 + width, y1 - width, x1 - width, y1, borderColor);
    }

    public static void rectangleGradient(double x1, double y1, double x2, double y2, int[] color) {
        float[] r2 = new float[color.length];
        float[] g2 = new float[color.length];
        float[] b2 = new float[color.length];
        float[] a2 = new float[color.length];
        for (int worldRenderer = 0; worldRenderer < color.length; ++worldRenderer) {
            r2[worldRenderer] = (float)(color[worldRenderer] >> 16 & 0xFF) / 255.0f;
            g2[worldRenderer] = (float)(color[worldRenderer] >> 8 & 0xFF) / 255.0f;
            b2[worldRenderer] = (float)(color[worldRenderer] & 0xFF) / 255.0f;
            a2[worldRenderer] = (float)(color[worldRenderer] >> 24 & 0xFF) / 255.0f;
        }
        GlStateManager.disableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.disableAlpha();
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GlStateManager.blendFunc(770, 771);
        GlStateManager.shadeModel(7425);
        WorldRenderer var14 = Tessellator.getInstance().getWorldRenderer();
        var14.startDrawingQuads();
        var14.setColorRGBA_F(r2[0], g2[0], b2[0], a2[0]);
        var14.addVertex(x2, y1, 0.0);
        var14.setColorRGBA_F(r2[1], g2[1], b2[1], a2[1]);
        var14.addVertex(x1, y1, 0.0);
        var14.setColorRGBA_F(r2[2], g2[2], b2[2], a2[2]);
        var14.addVertex(x1, y2, 0.0);
        var14.setColorRGBA_F(r2[3], g2[3], b2[3], a2[3]);
        var14.addVertex(x2, y2, 0.0);
        Tessellator.getInstance().draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static void rectangleOutlinedGradient(double x1, double y1, double x2, double y2, int[] color, double width) {
        RenderUtils.rectangleGradient(x1, y1, x2, y1 + width, new int[]{color[0], color[1], color[0], color[1]});
        RenderUtils.rectangleGradient(x1, y2 - width, x2, y2, new int[]{color[2], color[3], color[2], color[3]});
        RenderUtils.rectangleGradient(x1, y1 + width, x1 + width, y2 - width, color);
        RenderUtils.rectangleGradient(x2 - width, y1 + width, x2, y2 - width, color);
    }

    public static void rectangleBorderedGradient(double x1, double y1, double x2, double y2, int[] fill, int[] outline, double width) {
        RenderUtils.rectangleOutlinedGradient(x1, y1, x2, y2, outline, width);
        RenderUtils.rectangleGradient(x1 + width, y1 + width, x2 - width, y2 - width, fill);
    }

    public static int blend(int color1, int color2, float perc) {
        Color blended;
        Color x2 = new Color(color1);
        Color y2 = new Color(color2);
        float inverse_blending = 1.0f - perc;
        float red = (float)x2.getRed() * perc + (float)y2.getRed() * inverse_blending;
        float green = (float)x2.getGreen() * perc + (float)y2.getGreen() * inverse_blending;
        float blue = (float)x2.getBlue() * perc + (float)y2.getBlue() * inverse_blending;
        try {
            blended = new Color(red / 255.0f, green / 255.0f, blue / 255.0f);
        }
        catch (Exception var11) {
            blended = new Color(-1);
        }
        return blended.getRGB();
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
        if (ClientUtils.mc().thePlayer.getCurrentEquippedItem() != null) {
            stuff.add(ClientUtils.mc().thePlayer.getCurrentEquippedItem());
        }
        for (ItemStack var8 : stuff) {
            ClientUtils.mc();
            if (Minecraft.theWorld != null) {
                RenderHelper.enableGUIStandardItemLighting();
                split += 16;
            }
            GlStateManager.pushMatrix();
            GlStateManager.disableAlpha();
            GlStateManager.clear(256);
            ClientUtils.mc().getRenderItem().zLevel = -150.0f;
            ClientUtils.mc().getRenderItem().func_180450_b(var8, split + scaledRes.getScaledWidth() / 2 - 4, scaledRes.getScaledHeight() - (onwater ? 65 : 55));
            RenderItem var10000 = ClientUtils.mc().getRenderItem();
            ClientUtils.mc();
            var10000.func_175030_a(Minecraft.fontRendererObj, var8, split + scaledRes.getScaledWidth() / 2 - 4, scaledRes.getScaledHeight() - (onwater ? 65 : 55));
            ClientUtils.mc().getRenderItem().zLevel = 0.0f;
            GlStateManager.disableBlend();
            GlStateManager.scale(0.5, 0.5, 0.5);
            GlStateManager.disableDepth();
            GlStateManager.disableLighting();
            GlStateManager.enableDepth();
            GlStateManager.scale(2.0f, 2.0f, 2.0f);
            GlStateManager.enableAlpha();
            GlStateManager.popMatrix();
            var8.getEnchantmentTagList();
        }
        GL11.glPopMatrix();
    }

    public static double getDiff(double lastI, double i2, float ticks, double ownI) {
        return lastI + (i2 - lastI) * (double)ticks - ownI;
    }

    public static enum Side {
        Top("Top", 0),
        Right("Right", 1),
        Bottom("Bottom", 2),
        Left("Left", 3),
        None("None", 4);


        private Side(String s, int n2) {
        }
    }
}

