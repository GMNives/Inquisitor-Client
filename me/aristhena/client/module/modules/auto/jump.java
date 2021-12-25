package me.aristhena.client.module.modules.auto;

import me.aristhena.client.module.Module;
import me.aristhena.client.module.Module.Mod;
import me.aristhena.utils.ClientUtils;
import net.minecraft.network.play.client.C01PacketChatMessage;

@Mod(displayName="/jump")
public class jump
extends Module
{
public void enable()
{
ClientUtils.mc().getNetHandler().addToSendQueue(new C01PacketChatMessage("/ejump"));
super.disable();
}
}