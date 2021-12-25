package me.aristhena.client.module.modules.movement;

import me.aristhena.client.module.Module;
import me.aristhena.client.module.Module.Mod;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.MoveEvent;
import me.aristhena.utils.ClientUtils;

@Mod(displayName = "Fast Swim")
public class FastSwim extends Module
{
    public int ticks;
    
    @EventTarget
    public void moveEntity(final MoveEvent event) {
        if (ClientUtils.player().isInWater()) {
            ++this.ticks;
            if (this.ticks == 4) {
                ClientUtils.setMoveSpeed(event, 0.4000000059604645);
            }
            if (this.ticks >= 5) {
                ClientUtils.setMoveSpeed(event, 0.30000001192092896);
                this.ticks = 0;
            }
        }
    }
    
    public void onDisabled() {
        super.disable();
        this.ticks = 0;
    }
}
