// 
// Decompiled by Procyon v0.5.30
// 

package me.aristhena.client.command.commands;

import me.aristhena.client.command.Com;
import me.aristhena.client.command.Command;
import me.aristhena.client.module.Module;
import me.aristhena.client.module.ModuleManager;
import me.aristhena.utils.ClientUtils;

@Com(names = {"hide"})
public class Visible extends Command
{
    @Override
    public void runCommand(final String[] args) {
        String modName = "";
        if (args.length > 1) {
            modName = args[1];
        }
        final Module module = ModuleManager.getModule(modName);
        if (module.getId().equalsIgnoreCase("null")) {
            ClientUtils.sendMessage("Ќеверный модуль.");
            return;
        }
        module.setShown(!module.isShown());
        ClientUtils.sendMessage(String.valueOf(module.getDisplayName()) + " сейчас " + (module.isEnabled() ? "показано" : "скрыто"));
        ModuleManager.save();
    }
    
    @Override
    public String getHelp() {
        return ".hide (модуль) - ѕоказывает или скрывает модуль в Arraylist.";
    }
}
