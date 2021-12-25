// 
// Decompiled by Procyon v0.5.30
// 

package me.aristhena.client.command.commands;

import me.aristhena.client.command.Com;
import me.aristhena.client.command.Command;
import me.aristhena.client.module.Module;
import me.aristhena.client.module.ModuleManager;
import me.aristhena.client.module.modules.render.Hud;
import me.aristhena.utils.ClientUtils;

@Com(names = {"rename"})
public class Rename extends Command
{
    @Override
    public void runCommand(final String[] args) {
        String modName = "";
        String newName = "";
        if (args.length > 1) {
            modName = args[1];
            if (args.length > 2) {
                newName = args[2];
                if (newName.startsWith("\"") && args[args.length - 1].endsWith("\"")) {
                    newName = newName.substring(1, newName.length());
                    for (int i = 3; i < args.length; ++i) {
                        newName = String.valueOf(newName) + " " + args[i].replace("\"", "");
                    }
                }
            }
        }
        final Module module = ModuleManager.getModule(modName);
        if (module.getId().equalsIgnoreCase("null")) {
            ClientUtils.sendMessage("�������� ������.");
            return;
        }
        if (newName == "") {
            ClientUtils.sendMessage(String.valueOf(module.getId()) + " ��� ���� ��������.");
            module.setDisplayName(module.getId());
            ModuleManager.save();
            return;
        }
        module.setDisplayName(newName);
        ModuleManager.save();
        ClientUtils.sendMessage(String.valueOf(module.getId()) + " ��� ������������ � " + newName);
        ((Hud)new Hud().getInstance()).lucidTab.setupSizes();
    }
    
    @Override
    public String getHelp() {
        return ".rename (������) (��������) - ������������� ������.";
    }
}
