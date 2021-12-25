/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.client.command.commands;

import me.aristhena.client.command.Com;
import me.aristhena.client.command.Command;
import me.aristhena.utils.ClientUtils;
import net.minecraft.client.Minecraft;

@Com(names={"vclip"})
public class VClip
extends Command {
    @Override
    public void runCommand(String[] args) {
        double posMod = Double.parseDouble(args[1]);
        Minecraft mc2 = Minecraft.getMinecraft();
        ClientUtils.player().setPosition(ClientUtils.player().posX, ClientUtils.player().posY + posMod, ClientUtils.player().posZ);
        ClientUtils.sendMessage("Teleported " + posMod + " blocks vertically.");
    }

    @Override
    public String getHelp() {
        return ".vclip <число блоков> - “елепортирует вас по вертикали";
    }
}

