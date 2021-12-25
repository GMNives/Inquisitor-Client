package me.aristhena.client.module.modules.movement.longjump;

import net.minecraft.entity.*;
import java.util.*;

import me.aristhena.client.module.*;
import me.aristhena.client.module.modules.movement.*;
import me.aristhena.event.*;
import me.aristhena.event.events.*;
import me.aristhena.utils.*;

public class Hypixel extends LongJumpModes
{
    private static final double SPEED_BASE = 0.2873;
    private double moveSpeed;
    private double lastDist;
    public static int stage;
    public static int settingUpTicks;
    
    public Hypixel(final String name, final boolean value, final Module module) {
        super(name, value, module);
    }
    
    @Override
    public boolean enable() {
        if (super.enable()) {
            Hypixel.stage = 0;
            NCP.settingUpTicks = 2;
        }
        return true;
    }
    
    @Override
    public boolean onMove(final MoveEvent event) {
        if (super.onMove(event)) {
            if (ClientUtils.player().isCollidedHorizontally || (ClientUtils.player().moveForward == 0.0f && ClientUtils.player().moveStrafing != 0.0f)) {
                Hypixel.stage = 0;
                NCP.settingUpTicks = 5;
            }
            else {
                if (NCP.settingUpTicks > 0 && (ClientUtils.player().moveForward != 0.0f || ClientUtils.player().moveStrafing != 0.0f)) {
                    this.moveSpeed = 0.09;
                    --NCP.settingUpTicks;
                }
                else if (Hypixel.stage == 1 && ClientUtils.player().isCollidedVertically && (ClientUtils.player().moveForward != 0.0f || ClientUtils.player().moveStrafing != 0.0f)) {
                    this.moveSpeed = 0.6 + Speed.getBaseMoveSpeed() - 0.05;
                }
                else if (Hypixel.stage == 2 && ClientUtils.player().isCollidedVertically && (ClientUtils.player().moveForward != 0.0f || ClientUtils.player().moveStrafing != 0.0f)) {
                    event.setY(ClientUtils.player().motionY = 0.415);
                    this.moveSpeed *= 2.13;
                }
                else if (Hypixel.stage == 3) {
                    final double difference = 0.66 * (this.lastDist - Speed.getBaseMoveSpeed());
                    this.moveSpeed = this.lastDist - difference;
                }
                else {
                    this.moveSpeed = this.lastDist - this.lastDist / 159.0;
                }
                ClientUtils.setMoveSpeed(event, this.moveSpeed);
                final List collidingList = ClientUtils.world().getCollidingBlockBoundingBoxes(ClientUtils.player(), ClientUtils.player().boundingBox.offset(0.0, ClientUtils.player().motionY, 0.0));
                final List collidingList2 = ClientUtils.world().getCollidingBlockBoundingBoxes(ClientUtils.player(), ClientUtils.player().boundingBox.offset(0.0, -0.4, 0.0));
                if (!ClientUtils.player().isCollidedVertically && (collidingList.size() > 0 || collidingList2.size() > 0) && Hypixel.stage > 10) {
                    if (Hypixel.stage >= 38) {
                        event.setY(ClientUtils.player().motionY = -0.4);
                        Hypixel.stage = 0;
                        NCP.settingUpTicks = 5;
                    }
                    else {
                        event.setY(ClientUtils.player().motionY = -0.001);
                    }
                }
                if (NCP.settingUpTicks <= 0 && (ClientUtils.player().moveForward != 0.0f || ClientUtils.player().moveStrafing != 0.0f)) {
                    ++Hypixel.stage;
                }
            }
        }
        return true;
    }
    
    @Override
    public boolean onUpdate(final UpdateEvent event) {
        if (super.onUpdate(event) && event.getState() == Event.State.PRE) {
            final double xDist = ClientUtils.x() - ClientUtils.player().prevPosX;
            final double zDist = ClientUtils.z() - ClientUtils.player().prevPosZ;
            this.lastDist = Math.sqrt(xDist * xDist + zDist * zDist);
        }
        return true;
    }
}
