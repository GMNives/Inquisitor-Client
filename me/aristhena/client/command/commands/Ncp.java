// 
// Decompiled by Procyon v0.5.30
// 

package me.aristhena.client.command.commands;

import me.aristhena.client.command.Com;
import me.aristhena.client.command.Command;
import me.aristhena.utils.ClientUtils;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C01PacketChatMessage;

@Com(names = {"ncp"})
public class Ncp extends Command
{
    @Override
    public void runCommand(final String[] args) {
        if (args.length > 1) {
            ClientUtils.packet(new C01PacketChatMessage("/testncp input"));
            ClientUtils.packet(new C01PacketChatMessage("/testncp input " + args[1]));
        }
        else {
            ClientUtils.sendMessage(this.getHelp());
        }
    }
    
    @Override
    public String getHelp() {
        return ".ncp ник - Устанавливает игрока в качестве цели testncp";
    }
}
