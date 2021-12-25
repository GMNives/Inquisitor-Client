/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.client;

import me.aristhena.utils.ClientUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Session;

public class Wrapper {
    public static Minecraft getMinecraft() {
        return Minecraft.getMinecraft();
    }

    public static EntityPlayer getPlayer() {
        return Wrapper.getMinecraft().thePlayer;
    }

    public static WorldClient getWorld() {
        Wrapper.getMinecraft();
        return Minecraft.theWorld;
    }

    public static FontRenderer getFontRenderer() {
        Wrapper.getMinecraft();
        return Minecraft.fontRendererObj;
    }

    public static Block getBlock(BlockPos pos, double offset) {
        return Minecraft.getMinecraft().thePlayer.worldObj.getBlockState(pos.add(0.0, offset, 0.0)).getBlock();
    }

    public static Session getSession() {
        return Wrapper.getMinecraft().getSession();
    }

    public static void giveItem(ItemStack stack, int slot) {
        ClientUtils.packet(new C10PacketCreativeInventoryAction(36 + slot, stack));
    }

    public static String withColors(String input) {
        String output = input;
        int index = output.indexOf("&&");
        while (output.indexOf("&&") != -1) {
            output = output.replace("&&", "\u00a7");
            index = output.indexOf("&&");
        }
        return output;
    }
}

