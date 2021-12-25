package me.aristhena.client.module.modules.auto;

import me.aristhena.client.module.Module;
import me.aristhena.client.module.Module.Mod;
import me.aristhena.utils.ClientUtils;
import net.minecraft.network.play.client.C01PacketChatMessage;

@Mod(displayName="/heal")
public class heal
extends Module
{
public void enable()
{
ClientUtils.mc().getNetHandler().addToSendQueue(new C01PacketChatMessage("/eheal"));
super.disable();
}
}