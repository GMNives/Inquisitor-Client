package me.aristhena.client.module.modules.player;

import java.util.Random;

import me.aristhena.client.module.Module;
import me.aristhena.client.module.Module.Mod;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.MoveEvent;
import me.aristhena.event.events.UpdateEvent;
import me.aristhena.utils.ClientUtils;
import net.minecraft.util.BlockPos;

@Mod
public class AntiAFK extends Module
{
    public int ticks;
    private BlockPos block;
    private Random random;
    private BlockPos nextBlock;
    
    @EventTarget
    public void moveEntity(final UpdateEvent event) {
        if (ClientUtils.player().isDead) {
            ClientUtils.player().respawnPlayer();
        }
        ++this.ticks;
        if (this.ticks == 10 && ClientUtils.player().onGround) {
            ClientUtils.player().jump();
        }
        if (this.ticks == 25 && ClientUtils.player().onGround) {
            ClientUtils.player().jump();
        }
        if (this.ticks == 55 && ClientUtils.player().onGround) {
            ClientUtils.player().jump();
        }
        if (this.ticks >= 65 && ClientUtils.player().onGround) {
            ClientUtils.player().jump();
            this.ticks = 0;
        }
    }
    
    @EventTarget
    public void moveEntity(final MoveEvent event) {
    }
    
    public void onDisabled() {
        super.disable();
        this.ticks = 0;
    }
}
