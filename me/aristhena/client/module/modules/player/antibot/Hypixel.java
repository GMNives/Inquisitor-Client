package me.aristhena.client.module.modules.player.antibot;

import net.minecraft.client.*;
import net.minecraft.entity.*;
import java.util.*;

import me.aristhena.client.module.*;
import me.aristhena.event.*;
import me.aristhena.event.events.*;

@Module.Mod(displayName = "Hypixel")
public class Hypixel extends AntiBotMode
{
    public Hypixel(final String name, final boolean value, final Module module) {
        super(name, value, module);
    }
    
    @EventTarget
    @Override
    public boolean onUpdate(final UpdateEvent event) {
        for (final Object o : Minecraft.getMinecraft().theWorld.playerEntities) {
            final Entity ent = (Entity)o;
            if (ent.isInvisible()) {
                Minecraft.getMinecraft().theWorld.removeEntity(ent);
            }
        }
        return true;
    }
}
