package me.aristhena.client.command.commands;

import me.aristhena.client.command.*;
import me.aristhena.utils.*;
import net.minecraft.client.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.item.*;

@Com(names = { "hat" })
public class Hat extends Command
{
    @Override
    public String getHelp() {
        return ".hat - Помещает экипированный предмет вам в голову. (Только в креативе)";
    }
    
    @Override
    public void runCommand(final String[] args) {
        String player = "";
        if (args.length > 1) {
            player = args[1];
        }
        final ItemStack item = ClientUtils.player().getCurrentEquippedItem();
        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(5, item));
    }
}
