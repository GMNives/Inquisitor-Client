package me.aristhena.client.module.modules.render;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.Sphere;

import me.aristhena.client.module.Module;
import me.aristhena.client.module.Module.Mod;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.Render3DEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

@Mod(displayName = "PenisEsp")
public class PenisEsp extends Module { 

private int amount;
private float spin;
private float cumSize;
public static double red;
public static double green;
public static double blue;
public static boolean rainbow;
public static boolean mode;
private final List<Entity> entities;
private int ticks;
public int index;
public Timer time;
private Minecraft mc = Minecraft.getMinecraft();

public PenisEsp() {
    this.entities = new ArrayList<Entity>();
    this.ticks = 0;
    this.index = 0;
    this.time = new Timer();
    this.mc = Minecraft.getMinecraft();
}

public void onEnable() {
    if (mc.theWorld != null) {
        this.ticks = 0;
    }
}




@EventTarget
public void onRender(final Render3DEvent event) {
    for (final Object o : this.mc.theWorld.loadedEntityList) {
        if (o instanceof EntityPlayer) {
            final EntityPlayer player = (EntityPlayer)o;
            if (player == mc.thePlayer) {
                final double n = player.lastTickPosX + (player.posX - player.lastTickPosX) * this.mc.timer.renderPartialTicks;
                this.mc.getRenderManager();
                final double x = n - RenderManager.renderPosX;
                final double n2 = player.lastTickPosY + (player.posY - player.lastTickPosY) * this.mc.timer.renderPartialTicks;
                this.mc.getRenderManager();
                final double y = n2 - RenderManager.renderPosY;
                final double n3 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * this.mc.timer.renderPartialTicks;
                this.mc.getRenderManager();
                final double z = n3 - RenderManager.renderPosZ;
                GL11.glPushMatrix();
                RenderHelper.disableStandardItemLighting();
                this.esp(player, x, y, z);
                RenderHelper.enableStandardItemLighting();
                GL11.glPopMatrix();
            }
        }
       this.spin = 10;
    }
}

public void esp(final EntityPlayer player, final double x, final double y, final double z) {
    GL11.glDisable(2896);
    GL11.glDisable(3553);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    GL11.glDisable(2929);
    GL11.glEnable(2848);
    GL11.glDepthMask(true);
    GL11.glLineWidth(1.0f);
    GL11.glTranslated(x, y, z);
    GL11.glRotatef(-player.rotationYaw, 0.0f, player.height, 0.0f);
    GL11.glTranslated(-x, -y, -z);
    GL11.glTranslated(x, y + player.height / 2.0f - 0.22499999403953552, z);
    GL11.glColor4f(1.38f, 0.55f, 2.38f, 1.0f);
    GL11.glRotatef((player.isSneaking() ? 35 : 0) + this.spin, 1.0f + this.spin, 0.0f, this.cumSize);
    final int lines = 20;
    GL11.glTranslated(0.0, 0.0, 0.07500000298023224);
    final Cylinder shaft = new Cylinder();
    shaft.setDrawStyle(100013);
    shaft.draw(0.1f, 0.11f, 0.4f, 25, 20);
    GL11.glColor4f(1.38f, 0.85f, 1.38f, 1.0f);
    GL11.glTranslated(0.0, 0.0, -0.12500000298023223);
    GL11.glTranslated(-0.09000000074505805, 0.0, 0.0);
    final Sphere right = new Sphere();
    right.setDrawStyle(100013);
    right.draw(0.14f, 10, 20);
    GL11.glTranslated(0.16000000149011612, 0.0, 0.0);
    final Sphere left = new Sphere();
   left.setDrawStyle(100013);
    left.draw(0.14f, 10, 20);
   GL11.glColor4f(1.35f, 0.0f, 0.0f, 1.0f);
   GL11.glTranslated(-0.07000000074505806, 0.0, 0.589999952316284);
    final Sphere tip = new Sphere();
    tip.setDrawStyle(100013);
    tip.draw(0.13f, 15, 20);
    GL11.glDepthMask(true);
    GL11.glDisable(2848);
    GL11.glEnable(2929);
    GL11.glDisable(3042);
    GL11.glEnable(2896);
    GL11.glEnable(3553);
}
}
