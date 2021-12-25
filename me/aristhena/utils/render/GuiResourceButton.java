/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.utils.render;

import me.aristhena.utils.ClientUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class GuiResourceButton
extends GuiButton {
    private ResourceLocation resLoc;
    private ResourceLocation resLoc2;
    private int textColor;
    private int hoversize;

    public GuiResourceButton(int buttonId, int x2, int y2, int widthIn, int heightIn, String buttonText, ResourceLocation resLoc) {
        super(buttonId, x2, y2, widthIn, heightIn, buttonText);
        this.resLoc = resLoc;
        this.textColor = -1;
    }

    public GuiResourceButton(int buttonId, int x2, int y2, String buttonText, ResourceLocation resLoc) {
        super(buttonId, x2, y2, buttonText);
        this.resLoc = resLoc;
        this.textColor = -1;
    }

    public GuiResourceButton(int buttonId, int x2, int y2, int widthIn, int heightIn, String buttonText, ResourceLocation resLoc, int color) {
        super(buttonId, x2, y2, widthIn, heightIn, buttonText);
        this.resLoc = resLoc;
        this.textColor = color;
    }

    public GuiResourceButton(int buttonId, int x2, int y2, String buttonText, ResourceLocation resLoc, int color) {
        super(buttonId, x2, y2, buttonText);
        this.resLoc = resLoc;
        this.textColor = color;
    }

    @Override
    public void drawButton(Minecraft mc2, int mouseX, int mouseY) {
        boolean isOverButton;
        boolean bl2 = isOverButton = mouseX >= this.xPosition && mouseX <= this.xPosition + this.width && mouseY >= this.yPosition && mouseY <= this.yPosition + this.height;
        if (this.visible) {
            if (isOverButton && this.hoversize <= 1) {
                ++this.hoversize;
            } else if (!isOverButton && this.hoversize >= 0) {
                --this.hoversize;
            }
            mc2.getTextureManager().bindTexture(this.resLoc);
            Gui.drawScaledCustomSizeModalRect(this.xPosition, this.yPosition - this.hoversize, 0.0f, 0.0f, 50, this.height, this.width, this.height, this.width, this.height);
            ClientUtils.clientFont().drawString(this.displayString, this.xPosition + this.width / 2 - ClientUtils.clientFont().getStringWidth(this.displayString) / 2 + 1, this.yPosition + this.height + 4, this.textColor);
            if (!this.enabled) {
                Gui.drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 0x60000000);
            }
        }
    }
}

