/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.client.module.modules.movement;

import java.util.List;
import me.aristhena.client.module.Module;
import me.aristhena.client.module.modules.movement.Speed;
import me.aristhena.client.option.Option;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.MoveEvent;
import me.aristhena.event.events.UpdateEvent;
import me.aristhena.utils.ClientUtils;

@Module.Mod(displayName="Envy LongJump")
public class EnvyLongJump
extends Module {
    private /* synthetic */ double moveSpeed;
    private /* synthetic */ double lastDist;
    public static /* synthetic */ int stage;
    @Option.Op(increment=0.1, min=0.9, max=7.0)
    private /* synthetic */ double boost;

    public /* synthetic */ EnvyLongJump() {
        this.boost = 7.0;
    }

    @Override
    public /* synthetic */ void enable() {
        stage = 0;
        super.enable();
    }

    @EventTarget
    private /* synthetic */ void onMove(MoveEvent event) {
        boolean setY = false;
        if (ClientUtils.player().moveForward != 0.0f || ClientUtils.player().moveStrafing != 0.0f) {
            if (stage == 0) {
                this.moveSpeed = 1.0 + Speed.getBaseMoveSpeed() - 0.24;
            } else if (stage == 1) {
                ClientUtils.player().motionY = 0.41142;
                event.setY(0.41142);
                this.moveSpeed *= 1.85;
            } else if (stage == 2) {
                double difference = 0.66 * (this.lastDist - Speed.getBaseMoveSpeed());
                this.moveSpeed = this.lastDist - difference;
            } else {
                this.moveSpeed = this.lastDist - this.lastDist / 159.0;
            }
            this.moveSpeed = Math.max(Speed.getBaseMoveSpeed(), this.moveSpeed);
            ClientUtils.setMoveSpeed(event, this.moveSpeed);
            List collidingList = ClientUtils.world().getCollidingBlockBoundingBoxes(ClientUtils.player(), ClientUtils.player().boundingBox.offset(0.0, ClientUtils.player().motionY, 0.0));
            List collidingList2 = ClientUtils.world().getCollidingBlockBoundingBoxes(ClientUtils.player(), ClientUtils.player().boundingBox.offset(0.0, -0.4, 0.0));
            if (!(ClientUtils.player().isCollidedVertically || collidingList.size() <= 0 && collidingList2.size() <= 0)) {
                ClientUtils.player().motionY = -2.0E-4;
                event.setY(-2.0E-4);
            }
            ++stage;
        } else if (stage > 0) {
            this.disable();
        }
    }

    @EventTarget
    private /* synthetic */ void onUpdate(UpdateEvent event) {
        if (ClientUtils.player().moveForward != 0.0f || ClientUtils.player().moveStrafing != 0.0f) {
            double xDist = ClientUtils.x() - ClientUtils.player().prevPosX;
            double zDist = ClientUtils.z() - ClientUtils.player().prevPosZ;
            this.lastDist = Math.sqrt(xDist * xDist + zDist * zDist);
        } else {
            event.setCancelled(true);
        }
    }
}

