package me.aristhena.client.command.commands;

import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import me.aristhena.client.command.*;
import net.minecraft.client.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

@Com(names = { "skull" })
public class Skull extends Command
{
    @Override
    public String getHelp() {
        return ".skull Ник - Дает вам голову игрока";
    }
    
    @Override
    public void runCommand(final String[] args) {
        String player = "";
        if (args.length > 1) {
            player = args[1];
        }
        final ItemStack item = new ItemStack(Items.skull, 1, 3);
        final NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("SkullOwner", player);
        item.stackTagCompound = nbt;
        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, item));
    }
}
