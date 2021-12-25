// 
// Decompiled by Procyon v0.5.30
// 

package me.aristhena.client.command.commands;

import me.aristhena.client.command.Com;
import me.aristhena.client.command.Command;
import me.aristhena.client.module.modules.combat.aura.Dick22;
import me.aristhena.utils.ClientUtils;
import net.minecraft.entity.EntityLivingBase;

@Com(names = { "focus" })
public class Focus extends Command
{
    @Override
    public void runCommand(final String[] args) {
        if (args.length < 2) {
            ClientUtils.sendMessage(this.getHelp());
            return;
        }
        if (args[1].equalsIgnoreCase("clear") || args[1].equalsIgnoreCase("c")) {
            Dick22.focusTarget = null;
            ClientUtils.sendMessage("Цель фокуса очищена");
            return;
        }
        final String targetName = args[1];
        if (ClientUtils.world().getPlayerEntityByName(targetName) != null) {
            final EntityLivingBase target = Dick22.focusTarget = ClientUtils.world().getPlayerEntityByName(targetName);
            ClientUtils.sendMessage("Цель фокусировки установлена на " + target.getName());
        }
        else {
            ClientUtils.sendMessage("Нет игрока с именем " + targetName);
        }
    }
    
    @Override
    public String getHelp() {
        return ".focus Ник или clear - Добавить или очистить фокусировку цели";
    }
}
