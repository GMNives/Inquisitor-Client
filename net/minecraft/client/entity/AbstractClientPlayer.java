package net.minecraft.client.entity;

import net.minecraft.entity.player.*;
import net.minecraft.client.network.*;
import net.minecraft.util.*;
import com.mojang.authlib.minecraft.*;
import java.util.*;
import net.minecraft.client.*;
import java.io.*;
import me.aristhena.event.events.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.*;
import com.mojang.authlib.*;
import net.minecraft.client.resources.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.*;
import java.awt.image.*;
import java.awt.*;
import net.minecraft.init.*;
import me.aristhena.client.module.modules.render.*;
import me.aristhena.client.module.*;
import net.minecraft.src.*;
import net.minecraft.world.*;

public abstract class AbstractClientPlayer extends EntityPlayer
{
    private static final String __OBFID;
    private ResourceLocation ofLocationCape;
    public NetworkPlayerInfo field_175157_a;
    
    private void downloadCape(String stripControlCodes) {
        if (stripControlCodes != null && !stripControlCodes.isEmpty()) {
            stripControlCodes = StringUtils.stripControlCodes(stripControlCodes);
            final MinecraftProfileTexture minecraftProfileTexture = new MinecraftProfileTexture(String.valueOf(new StringBuilder("http://s.optifine.net/capes/").append(stripControlCodes).append(".png")), (Map)new HashMap());
            final ResourceLocation resourceLocation = new ResourceLocation(String.valueOf(new StringBuilder("skins/").append(minecraftProfileTexture.getHash())));
            Minecraft.getMinecraft().getTextureManager().loadTexture(resourceLocation, (ITextureObject)new ThreadDownloadImageData((File)null, minecraftProfileTexture.getUrl(), (ResourceLocation)null, (IImageBuffer)new AbstractClientPlayer$1(this, this, resourceLocation)));
        }
    }
    
    public void setSprinting(final boolean b) {
        final SprintEvent sprintEvent = new SprintEvent(b);
        sprintEvent.call();
        final boolean sprinting = sprintEvent.isSprinting();
        super.setSprinting(sprinting);
        final IAttributeInstance entityAttribute = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
        if (entityAttribute.getModifier(AbstractClientPlayer.sprintingSpeedBoostModifierUUID) != null) {
            entityAttribute.removeModifier(AbstractClientPlayer.sprintingSpeedBoostModifier);
        }
        if (sprinting) {
            entityAttribute.applyModifier(AbstractClientPlayer.sprintingSpeedBoostModifier);
        }
    }
    
    public static ResourceLocation getLocationSkin(final String s) {
        return new ResourceLocation(String.valueOf(new StringBuilder("skins/").append(StringUtils.stripControlCodes(s))));
    }
    
    public AbstractClientPlayer(final World world, final GameProfile gameProfile) {
        super(world, gameProfile);
        this.ofLocationCape = null;
        this.downloadCape(gameProfile.getName());
    }
    
    static {
        __OBFID = "CL_00000935";
    }
    
    public boolean hasCape() {
        return this.func_175155_b() != null;
    }
    
    public static ThreadDownloadImageData getDownloadImageSkin(final ResourceLocation resourceLocation, final String s) {
        final TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();
        Object texture = textureManager.getTexture(resourceLocation);
        if (texture == null) {
            texture = new ThreadDownloadImageData((File)null, String.format("http://skins.minecraft.net/MinecraftSkins/%s.png", StringUtils.stripControlCodes(s)), DefaultPlayerSkin.func_177334_a(EntityPlayer.func_175147_b(s)), (IImageBuffer)new ImageBufferDownload());
            textureManager.loadTexture(resourceLocation, (ITextureObject)texture);
        }
        return (ThreadDownloadImageData)texture;
    }
    
    protected NetworkPlayerInfo func_175155_b() {
        if (this.field_175157_a == null) {
            this.field_175157_a = Minecraft.getMinecraft().getNetHandler().func_175102_a(this.getUniqueID());
        }
        return this.field_175157_a;
    }
    
    private BufferedImage parseCape(final BufferedImage bufferedImage) {
        int n = 64;
        int n2 = 32;
        for (int width = bufferedImage.getWidth(), height = bufferedImage.getHeight(); n < width || n2 < height; n *= 2, n2 *= 2) {}
        final BufferedImage bufferedImage2 = new BufferedImage(n, n2, 2);
        final Graphics graphics = bufferedImage2.getGraphics();
        graphics.drawImage(bufferedImage, 0, 0, null);
        graphics.dispose();
        return bufferedImage2;
    }
    
    public float func_175156_o() {
        float n = 1.0f;
        if (this.capabilities.isFlying) {
            n *= 1.1f;
        }
        float n2 = n * (float)((this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue() / this.capabilities.getWalkSpeed() + 1.0) / 2.0);
        if (this.capabilities.getWalkSpeed() == 0.0f || Float.isNaN(n2) || Float.isInfinite(n2)) {
            n2 = 1.0f;
        }
        if (this.isUsingItem() && this.getItemInUse().getItem() == Items.bow) {
            final float n3 = this.getItemInUseDuration() / 20.0f;
            float n4;
            if (n3 > 1.0f) {
                n4 = 1.0f;
            }
            else {
                n4 = n3 * n3;
            }
            n2 *= 1.0f - n4 * 0.15f;
        }
        return n2;
    }
    
    public ResourceLocation getLocationCape() {
//        final Capes capes = (Capes)ModuleManager.getModule("Capes");
        if (!Config.isShowCapes()) {
            return null;
        }
        if (this.ofLocationCape != null) {
//            return (capes.isEnabled() && capes.canRender(this)) ? capes.getCape() : this.ofLocationCape;
        }
        final NetworkPlayerInfo playerInfo = this.getPlayerInfo();
//        return (playerInfo == null) ? null : ((capes.isEnabled() && capes.canRender(this)) ? capes.getCape() : playerInfo.func_178861_h());
		return ofLocationCape;
    }
    
    public boolean func_175149_v() {
        final NetworkPlayerInfo func_175102_a = Minecraft.getMinecraft().getNetHandler().func_175102_a(this.getGameProfile().getId());
        return func_175102_a != null && func_175102_a.getGameType() == WorldSettings.GameType.SPECTATOR;
    }
    
    public boolean hasSkin() {
        final NetworkPlayerInfo func_175155_b = this.func_175155_b();
        return func_175155_b != null && func_175155_b.func_178856_e();
    }
    
    public ResourceLocation getLocationSkin() {
        final NetworkPlayerInfo func_175155_b = this.func_175155_b();
        return (func_175155_b == null) ? DefaultPlayerSkin.func_177334_a(this.getUniqueID()) : func_175155_b.func_178837_g();
    }
    
    protected NetworkPlayerInfo getPlayerInfo() {
        if (this.field_175157_a == null) {
            this.field_175157_a = Minecraft.getMinecraft().getNetHandler().func_175102_a(this.getUniqueID());
        }
        return this.field_175157_a;
    }
    
    static void access$1(final AbstractClientPlayer abstractClientPlayer, final ResourceLocation ofLocationCape) {
        abstractClientPlayer.ofLocationCape = ofLocationCape;
    }
    
    public String func_175154_l() {
        final NetworkPlayerInfo func_175155_b = this.func_175155_b();
        return (func_175155_b == null) ? DefaultPlayerSkin.func_177332_b(this.getUniqueID()) : func_175155_b.func_178851_f();
    }

    public boolean hasPlayerInfo()
    {
        return this.getPlayerInfo() != null;
    }
}
