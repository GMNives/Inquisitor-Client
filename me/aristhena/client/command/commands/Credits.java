package me.aristhena.client.command.commands;

import me.aristhena.client.command.*;
import me.aristhena.utils.*;
import java.util.*;

@Com(names = { "credits", "cred", "Autors", "dev", "Developers" })
public class Credits extends Command
{
    public void runCommand(final String[] args) {
            ClientUtils.sendMessage("�9�l�o������� ����������� �������:");
            ClientUtils.sendMessage("�4�lGMNives (���������) �7- �6�nhttps://vk.com/lord_nives");
            ClientUtils.sendMessage("�c�lkent1997 (���� ���������) �7- �6�nhttps://vk.com/yabogvk");
            ClientUtils.sendMessage("�5�lMrCherep (���� ���������) �7- �6�nhttps://vk.com/skyfiers");
            ClientUtils.sendMessage("�a�l������ ���� �7- �6�nhttps://vk.com/inquisitor_private");
        }

    public String getHelp() {
        return ".credits - ��������� ����";
    }
}
