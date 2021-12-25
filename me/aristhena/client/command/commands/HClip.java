/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.client.command.commands;

import me.aristhena.client.command.Com;
import me.aristhena.client.command.Command;
import me.aristhena.utils.ClientUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumFacing;

@Com(names={"hclip"})
public class HClip
extends Command {
    @Override
    public void runCommand(String[] args) {
        double posMod = Double.parseDouble(args[1]);
        Minecraft mc2 = Minecraft.getMinecraft();
        if (ClientUtils.player().getHorizontalFacing() == EnumFacing.SOUTH) {
            ClientUtils.player().setPosition(ClientUtils.player().posX, ClientUtils.player().posY, ClientUtils.player().posZ + posMod);
        }
        if (ClientUtils.player().getHorizontalFacing() == EnumFacing.WEST) {
            ClientUtils.player().setPosition(ClientUtils.player().posX + -posMod, ClientUtils.player().posY, ClientUtils.player().posZ);
        }
        if (ClientUtils.player().getHorizontalFacing() == EnumFacing.EAST) {
            ClientUtils.player().setPosition(ClientUtils.player().posX + posMod, ClientUtils.player().posY, ClientUtils.player().posZ);
        }
        if (ClientUtils.player().getHorizontalFacing() == EnumFacing.NORTH) {
            ClientUtils.player().setPosition(ClientUtils.player().posX, ClientUtils.player().posY, ClientUtils.player().posZ + -posMod);
        }
        ClientUtils.sendMessage("“елепортирован на " + posMod + " по горизонтали.");
    }

    @Override
    public String getHelp() {
        return ".hclip <число блоков> - “елепортирует вас по горизонтали";
    }
}

