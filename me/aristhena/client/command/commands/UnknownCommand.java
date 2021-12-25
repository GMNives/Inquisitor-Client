// 
// Decompiled by Procyon v0.5.30
// 

package me.aristhena.client.command.commands;

import me.aristhena.client.command.Com;
import me.aristhena.client.command.Command;
import me.aristhena.utils.ClientUtils;

@Com(names = { "" })
public class UnknownCommand extends Command
{
    @Override
    public void runCommand(final String[] args) {
        ClientUtils.sendMessage("Неизвестная команда. Введите \".help\" для получения списка команд.");
    }
}
