package net.minecraft.client.model;

import net.minecraft.util.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.*;
import me.aristhena.utils.*;
import me.aristhena.client.module.modules.render.*;

public class ModelPlayer extends ModelBiped
{
    public ModelRenderer field_178734_a;
    public ModelRenderer field_178732_b;
    public ModelRenderer field_178733_c;
    public ModelRenderer field_178731_d;
    public ModelRenderer field_178730_v;
    private ModelRenderer field_178729_w;
    private ModelRenderer field_178736_x;
    private ModelRenderer wing;
    private ModelRenderer wingTip;
    public ModelRenderer villagerNose;
    private boolean field_178735_y;
    boolean flying;
    private static final ResourceLocation enderDragonTextures;
    private static final String __OBFID = "CL_00002626";
    
    static {
        enderDragonTextures = new ResourceLocation("textures/entity/enderdragon/dragon.png");
    }
    
    public ModelPlayer(final float p_i46304_1_, final boolean p_i46304_2_) {
        super(p_i46304_1_, 0.0f, 64, 64);
        this.flying = false;
        this.field_178735_y = p_i46304_2_;
        (this.field_178736_x = new ModelRenderer((ModelBase)this, 24, 0)).addBox(-3.0f, -6.0f, -1.0f, 6, 6, 1, p_i46304_1_);
        (this.field_178729_w = new ModelRenderer((ModelBase)this, 0, 0)).setTextureSize(64, 32);
        this.field_178729_w.addBox(-5.0f, 0.0f, -1.0f, 10, 16, 1, p_i46304_1_);
        this.setTextureOffset("wingtip.bone", 112, 136);
        this.setTextureOffset("wing.skin", -56, 88);
        this.setTextureOffset("wing.bone", 112, 88);
        this.setTextureOffset("wingtip.skin", -56, 144);
        final int bw2 = this.textureWidth;
        final int bh2 = this.textureWidth;
        this.textureWidth = 256;
        this.textureWidth = 256;
        (this.wing = new ModelRenderer((ModelBase)this, "wing")).setTextureSize(256, 256);
        this.wing.setRotationPoint(-12.0f, 5.0f, 2.0f);
        this.wing.addBox("bone", -56.0f, -4.0f, -4.0f, 56, 8, 8);
        this.wing.addBox("skin", -56.0f, 0.0f, 2.0f, 56, 0, 56);
        this.wing.isHidden = true;
        (this.wingTip = new ModelRenderer((ModelBase)this, "wingtip")).setTextureSize(256, 256);
        this.wingTip.setRotationPoint(-56.0f, 0.0f, 0.0f);
        this.wingTip.isHidden = true;
        this.wingTip.addBox("bone", -56.0f, -2.0f, -2.0f, 56, 4, 4);
        this.wingTip.addBox("skin", -56.0f, 0.0f, 2.0f, 56, 0, 56);
        this.wing.addChild(this.wingTip);
        this.textureWidth = bw2;
        this.textureWidth = bh2;
        if (p_i46304_2_) {
            (this.bipedLeftArm = new ModelRenderer((ModelBase)this, 32, 48)).addBox(-1.0f, -2.0f, -2.0f, 3, 12, 4, p_i46304_1_);
            this.bipedLeftArm.setRotationPoint(5.0f, 2.5f, 0.0f);
            (this.bipedRightArm = new ModelRenderer((ModelBase)this, 40, 16)).addBox(-2.0f, -2.0f, -2.0f, 3, 12, 4, p_i46304_1_);
            this.bipedRightArm.setRotationPoint(-5.0f, 2.5f, 0.0f);
            (this.field_178734_a = new ModelRenderer((ModelBase)this, 48, 48)).addBox(-1.0f, -2.0f, -2.0f, 3, 12, 4, p_i46304_1_ + 0.25f);
            this.field_178734_a.setRotationPoint(5.0f, 2.5f, 0.0f);
            (this.field_178732_b = new ModelRenderer((ModelBase)this, 40, 32)).addBox(-2.0f, -2.0f, -2.0f, 3, 12, 4, p_i46304_1_ + 0.25f);
            this.field_178732_b.setRotationPoint(-5.0f, 2.5f, 10.0f);
        }
        else {
            (this.bipedLeftArm = new ModelRenderer((ModelBase)this, 32, 48)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4, p_i46304_1_);
            this.bipedLeftArm.setRotationPoint(5.0f, 2.0f, 0.0f);
            (this.field_178734_a = new ModelRenderer((ModelBase)this, 48, 48)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4, p_i46304_1_ + 0.25f);
            this.field_178734_a.setRotationPoint(5.0f, 2.0f, 0.0f);
            (this.field_178732_b = new ModelRenderer((ModelBase)this, 40, 32)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4, p_i46304_1_ + 0.25f);
            this.field_178732_b.setRotationPoint(-5.0f, 2.0f, 10.0f);
        }
        (this.bipedLeftLeg = new ModelRenderer((ModelBase)this, 16, 48)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, p_i46304_1_);
        this.bipedLeftLeg.setRotationPoint(1.9f, 12.0f, 0.0f);
        (this.field_178733_c = new ModelRenderer((ModelBase)this, 0, 48)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, p_i46304_1_ + 0.25f);
        this.field_178733_c.setRotationPoint(1.9f, 12.0f, 0.0f);
        (this.field_178731_d = new ModelRenderer((ModelBase)this, 0, 32)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, p_i46304_1_ + 0.25f);
        this.field_178731_d.setRotationPoint(-1.9f, 12.0f, 0.0f);
        (this.field_178730_v = new ModelRenderer((ModelBase)this, 16, 32)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4, p_i46304_1_ + 0.25f);
        this.field_178730_v.setRotationPoint(0.0f, 0.0f, 0.0f);
    }
    
    public void render(final Entity p_78088_1_, final float p_78088_2_, final float p_78088_3_, final float p_78088_4_, final float p_78088_5_, final float p_78088_6_, final float p_78088_7_) {
        super.render(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
        GlStateManager.pushMatrix();
        if (this.isChild) {
            final float var8 = 2.0f;
            GlStateManager.scale(0.5f, 0.5f, 0.5f);
            GlStateManager.translate(0.0f, 24.0f * p_78088_7_, 0.0f);
            this.field_178733_c.render(p_78088_7_);
            this.field_178731_d.render(p_78088_7_);
            this.field_178734_a.render(p_78088_7_);
            this.field_178732_b.render(p_78088_7_);
            this.field_178730_v.render(p_78088_7_);
        }
        else {
            if (p_78088_1_.isSneaking()) {
                GlStateManager.translate(0.0f, 0.2f, 0.0f);
            }
            this.field_178733_c.render(p_78088_7_);
            this.field_178731_d.render(p_78088_7_);
            this.field_178734_a.render(p_78088_7_);
            this.field_178732_b.render(p_78088_7_);
            this.field_178730_v.render(p_78088_7_);
            Minecraft.getMinecraft();
            Minecraft.getMinecraft();
            if (p_78088_1_ == ClientUtils.player()) {
                GlStateManager.pushMatrix();
                float anSpeed = 100.0f;
                if (!p_78088_1_.onGround || this.flying) {
                    anSpeed = 10.0f;
                    this.flying = true;
                }
                final float f1 = p_78088_3_ + p_78088_4_ / anSpeed;
                final float f2 = p_78088_3_ + p_78088_4_ / 100.0f;
                final float f3 = f1 * 3.1415927f * 2.0f;
                final float f4 = 0.125f - (float)Math.cos(f3) * 0.2f;
                final float fs5 = f2 * 3.1415927f * 2.0f;
                final float f5 = 0.125f - (float)Math.cos(fs5) * 0.2f;
                if (this.flying && (int)(f4 * 100.0f) == (int)(f5 * 100.0f)) {
                    this.flying = false;
                    anSpeed = 100.0f;
                }
                Minecraft.getMinecraft();
                if (Minecraft.getMinecraft().thePlayer != null) {
                    Minecraft.getMinecraft().getTextureManager().bindTexture(ModelPlayer.enderDragonTextures);
                    GlStateManager.scale(WingsColor.scale1, WingsColor.scale2, WingsColor.scale3);
                    GlStateManager.translate(0.0, -0.3, 1.1);
                    GlStateManager.rotate(50.0f, -50.0f, 0.0f, 0.0f);
                    final boolean x2 = false;
                    final boolean index = false;
                    for (int i2 = 0; i2 < 2; ++i2) {
                        GlStateManager.color(WingsColor.red, WingsColor.green, WingsColor.blue, WingsColor.alpha);
                        GlStateManager.enableCull();
                        final float f6 = f1 * 3.1415927f * 2.0f;
                        this.wing.rotateAngleX = 0.125f - (float)Math.cos(f6) * 0.2f;
                        this.wing.rotateAngleY = 0.25f;
                        this.wing.rotateAngleZ = (float)(Math.sin(f6) + 1.225) * 0.3f;
                        this.wingTip.rotateAngleZ = -(float)(Math.sin(f6 + 2.0f) + 0.5) * 0.75f;
                        this.wing.isHidden = false;
                        this.wingTip.isHidden = false;
                        this.wing.render(p_78088_7_);
                        this.wing.isHidden = true;
                        this.wingTip.isHidden = true;
                        GlStateManager.color(1.0f, 1.0f, 1.0f);
                        if (i2 == 0) {
                            GlStateManager.scale(-1.0f, 1.0f, 1.0f);
                        }
                    }
                }
                GlStateManager.popMatrix();
            }
        }
        GlStateManager.popMatrix();
    }
    
    public void func_178727_b(final float p_178727_1_) {
        ModelBase.func_178685_a(this.bipedHead, this.field_178736_x);
        this.field_178736_x.rotationPointX = 0.0f;
        this.field_178736_x.rotationPointY = 0.0f;
        this.field_178736_x.render(p_178727_1_);
    }
    
    public void func_178728_c(final float p_178728_1_) {
        this.field_178729_w.render(p_178728_1_);
    }
    
    public void setRotationAngles(final float p_78087_1_, final float p_78087_2_, final float p_78087_3_, final float p_78087_4_, final float p_78087_5_, final float p_78087_6_, final Entity p_78087_7_) {
        super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
        ModelBase.func_178685_a(this.bipedLeftLeg, this.field_178733_c);
        ModelBase.func_178685_a(this.bipedRightLeg, this.field_178731_d);
        ModelBase.func_178685_a(this.bipedLeftArm, this.field_178734_a);
        ModelBase.func_178685_a(this.bipedRightArm, this.field_178732_b);
        ModelBase.func_178685_a(this.bipedBody, this.field_178730_v);
        this.field_178729_w.rotationPointY = (p_78087_7_.isSneaking() ? 2.0f : 0.0f);
    }
    
    public void func_178725_a() {
        this.bipedRightArm.render(0.0625f);
        this.field_178732_b.render(0.0625f);
    }
    
    public void func_178726_b() {
        this.bipedLeftArm.render(0.0625f);
        this.field_178734_a.render(0.0625f);
    }
    
    public void func_178719_a(final boolean p_178719_1_) {
        super.func_178719_a(p_178719_1_);
        this.field_178734_a.showModel = p_178719_1_;
        this.field_178732_b.showModel = p_178719_1_;
        this.field_178733_c.showModel = p_178719_1_;
        this.field_178731_d.showModel = p_178719_1_;
        this.field_178730_v.showModel = p_178719_1_;
        this.field_178729_w.showModel = p_178719_1_;
        this.field_178736_x.showModel = p_178719_1_;
        Minecraft.getMinecraft();
        if (ClientUtils.player() != null) {
            this.wing.showModel = true;
            this.wingTip.showModel = true;
        }
    }
    
    public void postRenderHiddenArm(final float p_178718_1_) {
        if (this.field_178735_y) {
            final ModelRenderer bipedRightArm4;
            final ModelRenderer modelRenderer;
            final ModelRenderer bipedRightArm3 = modelRenderer = (bipedRightArm4 = this.bipedRightArm);
            ++modelRenderer.rotationPointX;
            this.bipedRightArm.postRender(p_178718_1_);
            final ModelRenderer bipedRightArm6;
            final ModelRenderer modelRenderer2;
            final ModelRenderer bipedRightArm5 = modelRenderer2 = (bipedRightArm6 = this.bipedRightArm);
            --modelRenderer2.rotationPointX;
        }
        else {
            this.bipedRightArm.postRender(p_178718_1_);
        }
    }
}
