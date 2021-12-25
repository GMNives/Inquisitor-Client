package me.aristhena.client.module.modules.render;

import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.renderer.entity.*;

import org.lwjgl.opengl.*;

import me.aristhena.client.friend.*;
import me.aristhena.client.module.*;
import me.aristhena.client.option.*;
import me.aristhena.event.*;
import me.aristhena.event.events.*;
import me.aristhena.utils.*;

import java.util.*;
import net.minecraft.client.renderer.*;
import net.minecraft.enchantment.*;
import net.minecraft.item.*;

@Module.Mod(shown = true, displayName = "DuraTags")
public class DuraTags extends Module
{
    @Option.Op(min = 1.0, max = 20.0, increment = 1.0, name = "Distance")
    private double distance;
    @Option.Op(min = 0.0, max = 2.0, increment = 0.1, name = "Scale")
    private double scale;
    @Option.Op(name = "Armor")
    private boolean armor;
    private Character formatChar;
    public static Map<EntityLivingBase, double[]> entityPositions;
    
    static {
        DuraTags.entityPositions = new HashMap<EntityLivingBase, double[]>();
    }
    
    public DuraTags() {
        this.distance = 8.0;
        this.scale = 0.1;
        this.armor = true;
        this.formatChar = '�';
    }
    
    @EventTarget(3)
    private void onRender3DEvent(final Render3DEvent event) {
        for (final Object o : ClientUtils.mc().theWorld.loadedEntityList) {
            final Entity ent = (Entity)o;
            if (ent != ClientUtils.mc().thePlayer) {
                if (ent instanceof EntityPlayer) {
                    final double posX = ent.lastTickPosX + (ent.posX - ent.lastTickPosX) * event.getPartialTicks() - RenderManager.renderPosX;
                    final double posY = ent.lastTickPosY + (ent.posY - ent.lastTickPosY) * event.getPartialTicks() - RenderManager.renderPosY + ent.height + 0.5;
                    final double posZ = ent.lastTickPosZ + (ent.posZ - ent.lastTickPosZ) * event.getPartialTicks() - RenderManager.renderPosZ;
                    String str = ent.getDisplayName().getFormattedText();
                    if (ent.isSneaking()) {
                        str = str.replace(ent.getName(), this.formatChar + "4" + this.formatChar + "l" + ent.getName());
                    }
                    if (FriendManager.isFriend(ent.getName())) {
                        str = str.replace(ent.getName(), this.formatChar + "b" + FriendManager.getAliasName(ent.getName()));
                    }
                    String colorString = this.formatChar.toString();
                    final double health = MathUtils.roundToPlace((double)((EntityPlayer)ent).getHealth(), 1);
                    if (health >= 12.0) {
                        colorString = String.valueOf(colorString) + "2";
                    }
                    else if (health >= 4.0) {
                        colorString = String.valueOf(colorString) + "6";
                    }
                    else {
                        colorString = String.valueOf(colorString) + "4";
                    }
                    str = String.valueOf(str) + colorString + " \u2764" + health;
                    final float dist = ClientUtils.mc().thePlayer.getDistanceToEntity(ent);
                    float scale = 0.02672f;
                    final float factor = (float)((dist <= this.distance) ? (this.distance * this.scale) : (dist * this.scale));
                    scale *= factor;
                    GlStateManager.pushMatrix();
                    GlStateManager.disableDepth();
                    GlStateManager.translate(posX, posY, posZ);
                    GL11.glNormal3f(0.0f, 1.0f, 0.0f);
                    final RenderManager renderManager = ClientUtils.mc().renderManager;
                    GlStateManager.rotate(-RenderManager.playerViewY, 0.0f, 1.0f, 0.0f);
                    final RenderManager renderManager2 = ClientUtils.mc().renderManager;
                    GL11.glRotatef(RenderManager.playerViewX, 1.0f, 0.0f, 0.0f);
                    GlStateManager.scale(-scale, -scale, scale);
                    GlStateManager.disableLighting();
                    GlStateManager.enableBlend();
                    GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                    final Tessellator tessellator = Tessellator.getInstance();
                    final WorldRenderer worldRenderer = tessellator.getWorldRenderer();
                    GlStateManager.disableTexture2D();
                    worldRenderer.startDrawingQuads();
                    final int stringWidth = ClientUtils.clientFont().getStringWidth(str) / 2;
                    GL11.glColor3f(0.0f, 0.0f, 0.0f);
                    GL11.glLineWidth(1.0E-6f);
                    GL11.glBegin(3);
                    GL11.glVertex2d((double)(-stringWidth - 2), -0.8);
                    GL11.glVertex2d((double)(-stringWidth - 2), 8.8);
                    GL11.glVertex2d((double)(-stringWidth - 2), 8.8);
                    GL11.glVertex2d((double)(stringWidth + 2), 8.8);
                    GL11.glVertex2d((double)(stringWidth + 2), 8.8);
                    GL11.glVertex2d((double)(stringWidth + 2), -0.8);
                    GL11.glVertex2d((double)(stringWidth + 2), -0.8);
                    GL11.glVertex2d((double)(-stringWidth - 2), -0.8);
                    GL11.glEnd();
                    worldRenderer.setColorRGBA_I(0, 100);
                    worldRenderer.addVertex((double)(-stringWidth - 2), -0.8, 0.0);
                    worldRenderer.addVertex((double)(-stringWidth - 2), 8.8, 0.0);
                    worldRenderer.addVertex((double)(stringWidth + 2), 8.8, 0.0);
                    worldRenderer.addVertex((double)(stringWidth + 2), -0.8, 0.0);
                    tessellator.draw();
                    GlStateManager.enableTexture2D();
                    ClientUtils.clientFont().drawString(str, (double)(-ClientUtils.clientFont().getStringWidth(str) / 2), 0.0, -1);
                    GlStateManager.enableLighting();
                    GlStateManager.enableDepth();
                    if (this.armor && ClientUtils.player().getDistanceSqToEntity(ent) <= 1600.0) {
                        int xOffset = 0;
                        ItemStack[] arrayOfItemStack1;
                        for (int j = (arrayOfItemStack1 = ((EntityPlayer)ent).inventory.armorInventory).length, i = 0; i < j; ++i) {
                            final ItemStack armourStack = arrayOfItemStack1[i];
                            if (armourStack != null) {
                                xOffset -= 8;
                            }
                        }
                        if (((EntityPlayer)ent).getHeldItem() != null) {
                            xOffset -= 8;
                            final Object renderStack = ((EntityPlayer)ent).getHeldItem().copy();
                            if (((ItemStack)renderStack).hasEffect() && (((ItemStack)renderStack).getItem() instanceof ItemTool || ((ItemStack)renderStack).getItem() instanceof ItemArmor)) {
                                ((ItemStack)renderStack).stackSize = 1;
                            }
                            this.renderItemStack((ItemStack)renderStack, xOffset, -20);
                            xOffset += 16;
                        }
                        ItemStack[] arrayOfItemStack2;
                        for (int k = (arrayOfItemStack2 = ((EntityPlayer)ent).inventory.armorInventory).length, l = 0; l < k; ++l) {
                            final ItemStack armourStack2 = arrayOfItemStack2[l];
                            if (armourStack2 != null) {
                                final ItemStack renderStack2 = armourStack2.copy();
                                if (renderStack2.hasEffect() && (renderStack2.getItem() instanceof ItemTool || renderStack2.getItem() instanceof ItemArmor)) {
                                    renderStack2.stackSize = 1;
                                }
                                this.renderItemStack(renderStack2, xOffset, -20);
                                xOffset += 16;
                            }
                        }
                    }
                    GlStateManager.popMatrix();
                }
                GlStateManager.disableBlend();
            }
        }
    }
    
    @EventTarget
    private void onNametagRender(final NametagRenderEvent event) {
        event.setCancelled(true);
    }
    
    public void renderItemStack(final ItemStack stack, final int x, final int y) {
        GlStateManager.pushMatrix();
        GlStateManager.disableAlpha();
        GlStateManager.clear(256);
        ClientUtils.mc().getRenderItem().zLevel = -150.0f;
        ClientUtils.mc().getRenderItem().func_180450_b(stack, x, y);
        ClientUtils.mc().getRenderItem().func_175030_a(ClientUtils.mc().fontRendererObj, stack, x, y);
        ClientUtils.mc().getRenderItem().zLevel = 0.0f;
        GlStateManager.disableBlend();
        GlStateManager.scale(0.5, 0.5, 0.5);
        GlStateManager.disableDepth();
        GlStateManager.disableLighting();
        this.renderEnchantText(stack, x, y);
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        GlStateManager.scale(2.0f, 2.0f, 2.0f);
        GlStateManager.enableAlpha();
        GlStateManager.popMatrix();
    }
    
    public void renderEnchantText(final ItemStack stack, final int x, final int y) {
        int encY = y - 24;
        if (stack.getItem() instanceof ItemArmor) {
            final int pLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId, stack);
            final int tLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.thorns.effectId, stack);
            final int uLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack);
            if (pLevel > 0) {
                ClientUtils.clientFont().drawStringWithShadow(this.formatChar + "fp" + pLevel, (double)(x * 2), (double)encY, 16777215);
                encY += 7;
            }
            if (tLevel > 0) {
                ClientUtils.clientFont().drawStringWithShadow(this.formatChar + "ft" + tLevel, (double)(x * 2), (double)encY, 16777215);
                encY += 7;
            }
            if (uLevel > 0) {
                ClientUtils.clientFont().drawStringWithShadow(this.formatChar + "fu" + uLevel, (double)(x * 2), (double)encY, 16777215);
                encY += 7;
            }
            if (stack.getMaxDamage() - stack.getItemDamage() < stack.getMaxDamage()) {
                ClientUtils.clientFont().drawStringWithShadow(new StringBuilder(String.valueOf(stack.getMaxDamage() - stack.getItemDamage())).toString(), (double)(x * 2), (double)(encY + 4), -26215);
            }
        }
        if (stack.getItem() instanceof ItemBow) {
            final int sLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);
            final int kLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);
            final int fLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack);
            final int uLevel2 = EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack);
            if (sLevel > 0) {
                ClientUtils.clientFont().drawStringWithShadow(this.formatChar + "fd" + sLevel, (double)(x * 2), (double)encY, 16777215);
                encY += 7;
            }
            if (kLevel > 0) {
                ClientUtils.clientFont().drawStringWithShadow(this.formatChar + "fk" + kLevel, (double)(x * 2), (double)encY, 16777215);
                encY += 7;
            }
            if (fLevel > 0) {
                ClientUtils.clientFont().drawStringWithShadow(this.formatChar + "ff" + fLevel, (double)(x * 2), (double)encY, 16777215);
                encY += 7;
            }
            if (uLevel2 > 0) {
                ClientUtils.clientFont().drawStringWithShadow(this.formatChar + "fu" + uLevel2, (double)(x * 2), (double)encY, 16777215);
                encY += 7;
            }
        }
        if (stack.getItem() instanceof ItemSword) {
            final int sLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack);
            final int kLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.knockback.effectId, stack);
            final int fLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, stack);
            final int uLevel2 = EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack);
            if (sLevel > 0) {
                ClientUtils.clientFont().drawStringWithShadow(this.formatChar + "fs" + sLevel, (double)(x * 2), (double)encY, 16777215);
                encY += 7;
            }
            if (kLevel > 0) {
                ClientUtils.clientFont().drawStringWithShadow(this.formatChar + "fk" + kLevel, (double)(x * 2), (double)encY, 16777215);
                encY += 7;
            }
            if (fLevel > 0) {
                ClientUtils.clientFont().drawStringWithShadow(this.formatChar + "ff" + fLevel, (double)(x * 2), (double)encY, 16777215);
                encY += 7;
            }
            if (uLevel2 > 0) {
                ClientUtils.clientFont().drawStringWithShadow(this.formatChar + "fu" + uLevel2, (double)(x * 2), (double)encY, 16777215);
            }
        }
    }
}
