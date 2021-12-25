/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.utils.render;

import me.aristhena.client.Wrapper;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.Display;

public class VitalScreen {
    public static int getWidth() {
        return Display.getWidth() / VitalScreen.getScaleFactor();
    }

    public static int getHeight() {
        return Display.getHeight() / VitalScreen.getScaleFactor();
    }

    public static int getScaleFactor() {
        ScaledResolution sr2 = new ScaledResolution(Wrapper.getMinecraft(), Wrapper.getMinecraft().displayWidth, Wrapper.getMinecraft().displayHeight);
        return sr2.getScaleFactor();
    }

    public static ScaledResolution getScaledRes() {
        ScaledResolution sr2 = new ScaledResolution(Wrapper.getMinecraft(), Wrapper.getMinecraft().displayWidth, Wrapper.getMinecraft().displayHeight);
        return sr2;
    }
}

