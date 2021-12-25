/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.client.command.commands;

import me.aristhena.client.command.Com;
import me.aristhena.client.command.Command;
import me.aristhena.utils.ClientUtils;

@Com(names={"clearchat", "cc"})
public class ClearChat
extends Command {
    @Override
    public void runCommand(String[] args) {
        ClientUtils.mc().ingameGUI.getChatGUI().clearChatMessages();
    }

    @Override
    public String getHelp() {
        return ".clearchat или .cc - Очистить чат";
    }
}

