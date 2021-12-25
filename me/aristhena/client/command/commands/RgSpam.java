package me.aristhena.client.command.commands;

import net.minecraft.client.Minecraft;
import me.aristhena.client.command.Com;
import me.aristhena.client.command.Command;
import me.aristhena.utils.ClientUtils;

@Com(names = {"rgspam"})
public class RgSpam extends Command
{
    public static Minecraft mc;
    
    static {
        RgSpam.mc = Minecraft.getMinecraft();
    }
    
    @Override
    public void runCommand(final String[] args) {
        String regionName = "";
        String playerName = "";
        if (args.length > 1 || regionName != "" || playerName != "") {
            regionName = args[1];
            playerName = args[2];
            RgSpam.mc.thePlayer.sendChatMessage("/rg claim " + regionName);
            RgSpam.mc.thePlayer.sendChatMessage("/rg addmember " + regionName + " " + playerName);
            RgSpam.mc.thePlayer.sendChatMessage("/rg removeowner " + regionName + " " + RgSpam.mc.thePlayer.getGameProfile().getName());
            ClientUtils.sendMessage("Регион: " + regionName + ", добавлен " + playerName + " информация /rg list");
        }
        if (regionName == "" || playerName == "" || args.length <= 1) {
            ClientUtils.sendMessage("Недостаточно аргументов! Используйте .rgspam (название региона) (ник)");
        }
    }
    
    @Override
    public String getHelp() {
        return ".rgspam (название региона) (ник) - Спам созданием региона";
    }
}
