package me.aristhena.client.module.modules.movement;

import me.aristhena.client.module.*;
import me.aristhena.client.option.*;
import me.aristhena.utils.*;
import me.aristhena.event.*;
import me.aristhena.event.events.*;

@Module.Mod
public class GlideFly extends Module
{
    @Option.Op(name = "Glide")
    private boolean glide;
    private boolean motion;
    @Option.Op(min = 0.0, max = 1.0, increment = 0.05, name = "Fall Speed")
    private double glideSpeed;
    @Option.Op(min = 0.0, max = 9.0, increment = 0.2, name = "Speed")
    private double speed;
    
    public GlideFly() {
        this.speed = 0.8;
    }
    
    @EventTarget
    private void onUpdate(final UpdateEvent event) {
        if (event.getState() == Event.State.PRE) {
            if (!this.glide) {
                if (ClientUtils.movementInput().jump) {
                    ClientUtils.player().motionY = this.speed;
                }
                else if (ClientUtils.movementInput().sneak) {
                    ClientUtils.player().motionY = -this.speed;
                }
                else {
                    ClientUtils.player().motionY = 0.0;
                }
            }
            else {
                ClientUtils.player().motionY = -this.glideSpeed;
            }
        }
    }
    
    @EventTarget
    private void onMove(final MoveEvent event) {
        ClientUtils.setMoveSpeed(event, this.speed);
    }
}
