// 
// Decompiled by Procyon v0.5.30
// 

package me.aristhena.client.command.commands;

import java.util.Iterator;

import me.aristhena.client.command.Com;
import me.aristhena.client.command.Command;
import me.aristhena.client.command.CommandManager;
import me.aristhena.utils.ClientUtils;

@Com(names = { "help" })
public class Help extends Command
{
    @Override
    public void runCommand(final String[] args) {
        for (final Command command : CommandManager.commandList) {
            if (command instanceof OptionCommand) {
                continue;
            }
            if (command.getHelp() == null) {
                continue;
            }
            ClientUtils.sendMessage(command.getHelp());
        }
        ClientUtils.sendMessage(OptionCommand.getHelpString());
    }
    
    @Override
    public String getHelp() {
        return ".help - Список команд.";
    }
}
