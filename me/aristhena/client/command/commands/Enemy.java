package me.aristhena.client.command.commands;

import me.aristhena.client.command.*;
import me.aristhena.utils.*;
import me.aristhena.client.enemies.*;

@Com(names = { "Enemy" })
public class Enemy extends Command
{
    public void runCommand(final String[] args) {
        if (args.length < 3) {
            ClientUtils.sendMessage(this.getHelp());
            return;
        }
        if (args[1].equalsIgnoreCase("add") || args[1].equalsIgnoreCase("a")) {
            String alias = args[2];
            if (args.length > 3) {
                alias = args[3];
                if (alias.startsWith("\"") && args[args.length - 1].endsWith("\"")) {
                    alias = alias.substring(1, alias.length());
                    for (int i = 4; i < args.length; ++i) {
                        alias = String.valueOf(String.valueOf(String.valueOf(alias))) + " " + args[i].replace("\"", "");
                    }
                }
            }
            if (EnemyManager.isEnemy(args[2]) && args.length < 3) {
                ClientUtils.sendMessage(String.valueOf(String.valueOf(String.valueOf(args[2]))) + " уже враг.");
                return;
            }
            EnemyManager.removeEnemy(args[2]);
            EnemyManager.addEnemy(args[2], alias);
            ClientUtils.sendMessage("Добавлен " + args[2] + ((args.length > 3) ? (" в виде " + alias) : ""));
        }
        else if (args[1].equalsIgnoreCase("del") || args[1].equalsIgnoreCase("d")) {
            if (EnemyManager.isEnemy(args[2])) {
                EnemyManager.removeEnemy(args[2]);
                ClientUtils.sendMessage("Удален враг: " + args[2]);
            }
            else {
                ClientUtils.sendMessage(String.valueOf(String.valueOf(String.valueOf(args[2]))) + " не враг.");
            }
        }
        else {
            ClientUtils.sendMessage(this.getHelp());
        }
    }
    
    public String getHelp() {
        return ".Enemy (add или del) (ник) - Добавить игрока в список врагов.";
    }
}
