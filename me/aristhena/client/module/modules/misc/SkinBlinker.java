package me.aristhena.client.module.modules.misc;

import java.util.Random;

import me.aristhena.client.module.Module;
import me.aristhena.client.module.Module.Mod;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.UpdateEvent;
import me.aristhena.utils.ClientUtils;
import net.minecraft.entity.player.EnumPlayerModelParts;

@Mod
public class SkinBlinker extends Module
{
    Random rand;
    
    public SkinBlinker() {
        this.rand = new Random();
    }
    
    @EventTarget
    private void onUpdate(final UpdateEvent event) {
        final EnumPlayerModelParts[] arrayOfEnumPlayerModelParts1;
        final EnumPlayerModelParts[] parts = arrayOfEnumPlayerModelParts1 = EnumPlayerModelParts.values();
        for (int j = parts.length, i = 0; i < j; ++i) {
            final EnumPlayerModelParts part = arrayOfEnumPlayerModelParts1[i];
            ClientUtils.gamesettings().func_178878_a(part, this.rand.nextBoolean());
        }
    }
}
