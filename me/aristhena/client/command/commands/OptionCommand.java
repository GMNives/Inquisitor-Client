// 
// Decompiled by Procyon v0.5.30
// 

package me.aristhena.client.command.commands;

import me.aristhena.client.command.Command;
import me.aristhena.client.module.Module;
import me.aristhena.client.module.ModuleManager;
import me.aristhena.client.option.Option;
import me.aristhena.client.option.OptionManager;
import me.aristhena.client.option.types.BooleanOption;
import me.aristhena.client.option.types.NumberOption;
import me.aristhena.utils.ClientUtils;

public class OptionCommand extends Command
{
    @Override
    public void runCommand(final String[] args) {
        if (args.length < 2) {
            ClientUtils.sendMessage(getHelpString());
            return;
        }
        final Module mod = ModuleManager.getModule(args[0]);
        if (!mod.getId().equalsIgnoreCase("Null")) {
            final Option option = OptionManager.getOption(args[1], mod.getId());
            if (option instanceof BooleanOption) {
                final BooleanOption booleanOption = (BooleanOption)option;
                booleanOption.setValue(Boolean.valueOf(!booleanOption.getValue()));
                ClientUtils.sendMessage(String.valueOf(option.getDisplayName()) + " установлен в " + option.getValue());
                OptionManager.save();
            }
            else if (option instanceof NumberOption) {
                try {
                    option.setValue(Double.parseDouble(args[2]));
                    ClientUtils.sendMessage(String.valueOf(option.getDisplayName()) + " установлен в " + args[2]);
                }
                catch (NumberFormatException e) {
                    ClientUtils.sendMessage("Ошибка формата параметра числа.");
                }
                OptionManager.save();
            }
            else {
                ClientUtils.sendMessage("Опция не распознана.");
            }
        }
        else {
            ClientUtils.sendMessage(getHelpString());
        }
    }
    
    public static String getHelpString() {
        return "Установить значение модуля - Пример: .aura range 5";
    }
}
