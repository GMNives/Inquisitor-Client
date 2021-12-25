package me.aristhena.client.module.modules.render;

import me.aristhena.client.module.*;
import me.aristhena.client.option.*;
import me.aristhena.event.events.*;
import net.minecraft.client.gui.*;
import me.aristhena.event.*;
import java.awt.*;
import net.minecraft.client.*;
import org.lwjgl.input.*;
import me.aristhena.utils.*;

@Module.Mod(enabled = true, shown = false)
public class Keystrokes extends Module
{
    @Option.Op(min = 500.0, max = 6000.0, increment = 50.0)
    public double Frequency;
    @Option.Op(min = 0.0, max = 1.0, increment = 0.01)
    public float Saturation;
    @Option.Op(min = 0.0, max = 255.0, increment = 1.0)
    public double Red;
    @Option.Op(min = 0.0, max = 255.0, increment = 1.0)
    public double Green;
    @Option.Op(min = 0.0, max = 255.0, increment = 1.0)
    public double Blue;
    @Option.Op(name = "Rainbow")
    public boolean Rainbow;
    
    public Keystrokes() {
        this.Frequency = 2500.0;
        this.Saturation = 0.3f;
        this.Red = 0.0;
        this.Green = 250.0;
        this.Blue = 0.0;
        this.Rainbow = true;
        this.Frequency = 400.0;
        this.Saturation = 0.0f;
    }
    
    @EventTarget
    public void render(final Render2DEvent e) {
        final ScaledResolution sr3 = new ScaledResolution(ClientUtils.mc(), ClientUtils.mc().displayWidth, ClientUtils.mc().displayHeight);
        this.renderKeystrokes(sr3);
    }
    
    private void renderKeystrokes(final ScaledResolution sr3) {
        final Color color2 = new Color((int)this.Red, (int)this.Green, (int)this.Blue);
        final int wAlpha = Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindForward.getKeyCode()) ? 200 : 100;
        final int aAlpha = Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindLeft.getKeyCode()) ? 200 : 100;
        final int sAlpha = Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindBack.getKeyCode()) ? 200 : 100;
        final int dAlpha = Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindRight.getKeyCode()) ? 200 : 100;
        final int spaceAlpha = Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode()) ? 200 : 100;
        RenderUtils.rectangleBordered(sr3.getScaledWidth() - 29 - 29, sr3.getScaledHeight() - 4 - 25 - 49, sr3.getScaledWidth() - 4 - 29, sr3.getScaledHeight() - 4 - 49, 1.0, new Color(0, 0, 0, wAlpha).getRGB(), this.Rainbow ? this.Sasuke((int)this.Frequency, -15) : color2.getRGB());
        RenderUtils.rectangleBordered(sr3.getScaledWidth() - 29 - 29 - 29, sr3.getScaledHeight() - 4 - 45, sr3.getScaledWidth() - 4 - 29 - 29, sr3.getScaledHeight() - 24, 1.0, new Color(0, 0, 0, aAlpha).getRGB(), this.Rainbow ? this.Sasuke((int)this.Frequency, -15) : color2.getRGB());
        RenderUtils.rectangleBordered(sr3.getScaledWidth() - 29 - 29, sr3.getScaledHeight() - 4 - 45, sr3.getScaledWidth() - 4 - 29, sr3.getScaledHeight() - 24, 1.0, new Color(0, 0, 0, sAlpha).getRGB(), this.Rainbow ? this.Sasuke((int)this.Frequency, -15) : color2.getRGB());
        RenderUtils.rectangleBordered(sr3.getScaledWidth() - 29, sr3.getScaledHeight() - 4 - 45, sr3.getScaledWidth() - 4, sr3.getScaledHeight() - 24, 1.0, new Color(0, 0, 0, dAlpha).getRGB(), this.Rainbow ? this.Sasuke((int)this.Frequency, -15) : color2.getRGB());
        RenderUtils.rectangleBordered(sr3.getScaledWidth() - 29 - 29 - 29, sr3.getScaledHeight() - 4 - 15, sr3.getScaledWidth() - 4, sr3.getScaledHeight() - 4, 1.0, new Color(0, 0, 0, spaceAlpha).getRGB(), this.Rainbow ? this.Sasuke((int)this.Frequency, -15) : color2.getRGB());
        ClientUtils.clientFont().drawString(Keyboard.getKeyName(Minecraft.getMinecraft().gameSettings.keyBindForward.getKeyCode()), sr3.getScaledWidth() - 4 - 29 - 13 - ClientUtils.clientFont().getStringWidth(Keyboard.getKeyName(Minecraft.getMinecraft().gameSettings.keyBindForward.getKeyCode())) / 2, sr3.getScaledHeight() - 4 - 25 - 35 - Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT / 2, this.Rainbow ? this.Sasuke((int)this.Frequency, -15) : color2.getRGB());
        ClientUtils.clientFont().drawString(Keyboard.getKeyName(Minecraft.getMinecraft().gameSettings.keyBindLeft.getKeyCode()), sr3.getScaledWidth() - 4 - 57 - 14 - ClientUtils.clientFont().getStringWidth(Keyboard.getKeyName(Minecraft.getMinecraft().gameSettings.keyBindLeft.getKeyCode())) / 2, sr3.getScaledHeight() - 4 + 2 - 34 - Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT / 2, this.Rainbow ? this.Sasuke((int)this.Frequency, -15) : color2.getRGB());
        ClientUtils.clientFont().drawString(Keyboard.getKeyName(Minecraft.getMinecraft().gameSettings.keyBindBack.getKeyCode()), sr3.getScaledWidth() - 4 - 28 - 14 - ClientUtils.clientFont().getStringWidth(Keyboard.getKeyName(Minecraft.getMinecraft().gameSettings.keyBindBack.getKeyCode())) / 2, sr3.getScaledHeight() - 4 + 2 - 34 - Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT / 2, this.Rainbow ? this.Sasuke((int)this.Frequency, -15) : color2.getRGB());
        ClientUtils.clientFont().drawString(Keyboard.getKeyName(Minecraft.getMinecraft().gameSettings.keyBindRight.getKeyCode()), sr3.getScaledWidth() - 4 + 1 - 14 - ClientUtils.clientFont().getStringWidth(Keyboard.getKeyName(Minecraft.getMinecraft().gameSettings.keyBindRight.getKeyCode())) / 2, sr3.getScaledHeight() - 4 + 2 - 34 - Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT / 2, this.Rainbow ? this.Sasuke((int)this.Frequency, -15) : color2.getRGB());
    }
    
    private int Sasuke(final int speed, final int offset) {
        float hue = (float)((System.currentTimeMillis() + offset) % speed);
        hue /= speed;
        return Color.getHSBColor(hue, this.Saturation, 1.0f).getRGB();
    }
}
