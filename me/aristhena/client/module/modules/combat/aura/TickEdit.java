package me.aristhena.client.module.modules.combat.aura;

import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import net.minecraft.util.*;
import net.minecraft.network.*;
import net.minecraft.item.*;
import net.minecraft.enchantment.*;
import net.minecraft.potion.*;
import net.minecraft.network.play.client.*;

import java.util.*;

import me.aristhena.client.module.*;
import me.aristhena.client.module.modules.auto.AutoPot;
import me.aristhena.client.module.modules.combat.*;
import me.aristhena.client.module.modules.movement.*;
import me.aristhena.client.module.modules.movement.modespeed.*;
import me.aristhena.event.*;
import me.aristhena.event.events.*;
import me.aristhena.utils.*;
import me.aristhena.utils.Timer;

public class TickEdit extends AuraMode
{
    private boolean setupTick;
    private EntityLivingBase target;
    private Timer timer;
    private boolean secondAttack;
    private boolean swapTargetItTurnedIntoAMessyThingFUCK;
    public static boolean cancelNext;
    private UpdateEvent preUpdate;
    
    public TickEdit(final String name, final boolean value, final Module module) {
        super(name, value, module);
        this.timer = new Timer();
    }
    
    @Override
    public boolean enable() {
        if (super.enable()) {
            this.target = null;
        }
        return super.enable();
    }
    
    @Override
    public boolean onUpdate(final UpdateEvent event) {
        if (super.onUpdate(event)) {
            if (event.getState().equals(Event.State.PRE)) {
                this.preUpdate = event;
            }
            switch (event.getState()) {
                case PRE: {
                    StateManager.setOffsetLastPacketAura(false);
                    final Aura auraModule = (Aura)this.getModule();
                    final NoSlowdown noSlowdownModule = (NoSlowdown)new NoSlowdown().getInstance();
                    if (this.target == null) {
                        this.timer.reset();
                        this.target = this.getTarget();
                    }
                    this.lowerTicks();
                    final double oldRange = auraModule.range;
                    auraModule.range = auraModule.blockRange;
                    int nearbyEntitiesBlock = 0;
                    for (final Entity entity : ClientUtils.loadedEntityList()) {
                        if (auraModule.isEntityValid(entity)) {
                            ++nearbyEntitiesBlock;
                        }
                    }
                    auraModule.range = oldRange;
                    int nearbyEntities = 0;
                    for (final Entity entity2 : ClientUtils.loadedEntityList()) {
                        if (auraModule.isEntityValid(entity2)) {
                            ++nearbyEntities;
                        }
                    }
                    if (nearbyEntitiesBlock > 0 && auraModule.autoblock && ClientUtils.player().getCurrentEquippedItem() != null && ClientUtils.player().getCurrentEquippedItem().getItem() instanceof ItemSword) {
                        ClientUtils.playerController().sendUseItem(ClientUtils.player(), ClientUtils.world(), ClientUtils.player().getCurrentEquippedItem());
                        if (!noSlowdownModule.isEnabled() && auraModule.noslowdown) {
                            ClientUtils.packet(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
                        }
                    }
                    if (this.target != null) {
                        final float[] rotations = RotationUtils.getRotations(this.target);
                        event.setYaw(rotations[0]);
                        event.setPitch(rotations[1]);
                    }
                    final boolean criticals = auraModule.criticals && !auraModule.dura;
                    if (this.setupTick) {
                        if (nearbyEntities > 0 && criticals && ClientUtils.player().isCollidedVertically && this.bhopCheck()) {
                            StateManager.setOffsetLastPacketAura(true);
                            event.setY(event.getY() + 0.07);
                            event.setGround(false);
                        }
                    }
                    else {
                        if (nearbyEntities > 0 && criticals && ClientUtils.player().isCollidedVertically && this.bhopCheck()) {
                            event.setGround(false);
                            event.setAlwaysSend(true);
                        }
                        if (ClientUtils.player().fallDistance > 0.0f && ClientUtils.player().fallDistance < 0.66) {
                            event.setGround(true);
                        }
                    }
                    this.setupTick = !this.setupTick;
                    break;
                }
                case POST: {
                    final Aura auraModule = (Aura)this.getModule();
                    if (this.target != null && auraModule.isEntityValid(this.target) && this.secondAttack && AutoPot.timer.getDifference() >= 550L) {
                        if (AutoPot.potting || this.preUpdate.getPitch() != RotationUtils.getRotations(this.target)[1]) {
                            final EntityLivingBase target3;
                            final EntityLivingBase target = target3 = this.target;
                            ++target3.auraTicks;
                        }
                        else {
                            if (!auraModule.dura && ClientUtils.player().isBlocking()) {
                                ClientUtils.packet(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
                            }
                            if (auraModule.dura && ClientUtils.player().inventory.getItemStack() == null) {
                                ClientUtils.playerController().windowClick(ClientUtils.player().inventoryContainer.windowId, 36 + ClientUtils.player().inventory.currentItem, 0, 0, ClientUtils.player());
                                this.attack(this.target, false);
                                this.attack(this.target, true);
                                TickEdit.cancelNext = true;
                                ClientUtils.packet(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.DROP_ALL_ITEMS, BlockPos.ORIGIN, EnumFacing.DOWN));
                                ClientUtils.playerController().windowClick(ClientUtils.player().inventoryContainer.windowId, -999, 0, 5, ClientUtils.player());
                                ClientUtils.playerController().windowClick(ClientUtils.player().inventoryContainer.windowId, 36 + ClientUtils.player().inventory.currentItem, 1, 5, ClientUtils.player());
                                ClientUtils.playerController().windowClick(ClientUtils.player().inventoryContainer.windowId, -999, 2, 5, ClientUtils.player());
                                this.attack(this.target, false);
                                this.attack(this.target, true);
                            }
                            else if (auraModule.dura) {
                                ClientUtils.packet(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.DROP_ALL_ITEMS, BlockPos.ORIGIN, EnumFacing.DOWN));
                                this.attack(this.target, false);
                                this.attack(this.target, true);
                                TickEdit.cancelNext = true;
                                ClientUtils.packet(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.DROP_ALL_ITEMS, BlockPos.ORIGIN, EnumFacing.DOWN));
                                ClientUtils.player().inventory.setInventorySlotContents(ClientUtils.player().inventory.currentItem, null);
                                ClientUtils.playerController().windowClick(ClientUtils.player().inventoryContainer.windowId, -999, 0, 5, ClientUtils.player());
                                ClientUtils.playerController().windowClick(ClientUtils.player().inventoryContainer.windowId, 36 + ClientUtils.player().inventory.currentItem, 1, 5, ClientUtils.player());
                                ClientUtils.playerController().windowClick(ClientUtils.player().inventoryContainer.windowId, -999, 2, 5, ClientUtils.player());
                                this.attack(this.target, false);
                                this.attack(this.target, true);
                            }
                            else {
                                auraModule.attack(this.target);
                            }
                            this.swapTargetItTurnedIntoAMessyThingFUCK = true;
                            AutoPot.potNextCompat = true;
                        }
                    }
                    if (this.secondAttack && AutoPot.timer.getDifference() >= 550L) {
                        this.secondAttack = false;
                    }
                    if (this.target != null && auraModule.isEntityValid(this.target) && (this.target.auraTicks <= 10 || this.target.auraTicks <= 0) && this.setupTick) {
                        if (AutoPot.potting) {
                            final EntityLivingBase target4;
                            final EntityLivingBase target2 = target4 = this.target;
                            ++target4.auraTicks;
                        }
                        else {
                            if (!auraModule.dura) {
                                auraModule.attack(this.target);
                                if (this.target.auraTicks <= 0) {
                                    auraModule.attack(this.target);
                                    this.swapTargetItTurnedIntoAMessyThingFUCK = true;
                                    AutoPot.potNextCompat = true;
                                }
                                else {
                                    this.secondAttack = true;
                                }
                            }
                            else {
                                this.secondAttack = true;
                            }
                            this.target.auraTicks = 20;
                        }
                    }
                    if ((AutoPot.swapTarget && this.timer.delay(450.0f)) || (this.timer.delay(450.0f) && (this.swapTargetItTurnedIntoAMessyThingFUCK || this.target == null || !auraModule.isEntityValid(this.target)) && !this.secondAttack)) {
                        AutoPot.swapTarget = false;
                        this.timer.reset();
                        this.swapTargetItTurnedIntoAMessyThingFUCK = false;
                        this.target = this.getTarget();
                        break;
                    }
                    break;
                }
            }
        }
        return true;
    }
    
    private void attack(final EntityLivingBase ent, final boolean crit) {
        final Aura auraModule = (Aura)this.getModule();
        auraModule.swingItem();
        if (crit) {
            this.crit();
        }
        else {
            ClientUtils.packet(new C03PacketPlayer(true));
        }
        final float sharpLevel = EnchantmentHelper.func_152377_a(ClientUtils.player().getHeldItem(), ent.getCreatureAttribute());
        final boolean vanillaCrit = ClientUtils.player().fallDistance > 0.0f && !ClientUtils.player().onGround && !ClientUtils.player().isOnLadder() && !ClientUtils.player().isInWater() && !ClientUtils.player().isPotionActive(Potion.blindness) && ClientUtils.player().ridingEntity == null;
        ClientUtils.packet(new C02PacketUseEntity(ent, C02PacketUseEntity.Action.ATTACK));
        if (crit || vanillaCrit) {
            ClientUtils.player().onCriticalHit(ent);
        }
        if (sharpLevel > 0.0f) {
            ClientUtils.player().onEnchantmentCritical(ent);
        }
    }
    
    private void crit() {
        if (ClientUtils.player().isCollidedVertically) {
            ClientUtils.packet(new C03PacketPlayer.C04PacketPlayerPosition(ClientUtils.x(), ClientUtils.y() + 0.0624, ClientUtils.z(), true));
            ClientUtils.packet(new C03PacketPlayer.C04PacketPlayerPosition(ClientUtils.x(), ClientUtils.y(), ClientUtils.z(), false));
            ClientUtils.packet(new C03PacketPlayer.C04PacketPlayerPosition(ClientUtils.x(), ClientUtils.y() + 1.11E-4, ClientUtils.z(), false));
            ClientUtils.packet(new C03PacketPlayer.C04PacketPlayerPosition(ClientUtils.x(), ClientUtils.y(), ClientUtils.z(), false));
        }
    }
    
    protected void swap(final int slot, final int hotbarNum) {
        ClientUtils.playerController().windowClick(ClientUtils.player().inventoryContainer.windowId, slot, hotbarNum, 2, ClientUtils.player());
    }
    
    private boolean bhopCheck() {
        if (new Speed().getInstance().isEnabled() && ((Speed)new Speed().getInstance()).bhop.getValue()) {
            if (ClientUtils.player().moveForward != 0.0f || ClientUtils.player().moveStrafing != 0.0f) {
                return false;
            }
            Bhop.stage = -4;
        }
        return true;
    }
    
    private EntityLivingBase getTarget() {
        final List<EntityLivingBase> targets = new ArrayList<EntityLivingBase>();
        final Aura auraModule = (Aura)this.getModule();
        for (final Entity ent : ClientUtils.loadedEntityList()) {
            if (ent instanceof EntityLivingBase) {
                final EntityLivingBase entity = (EntityLivingBase)ent;
                if ((entity.auraTicks > 11 || this.setupTick) && (entity.auraTicks > 10 || !this.setupTick) && entity.auraTicks > 0) {
                    continue;
                }
                if (!((Aura)this.getModule()).isEntityValid(entity)) {
                    continue;
                }
                targets.add(entity);
            }
        }

        return targets.get(0);
    }
    
    private void lowerTicks() {
        for (final Entity ent : ClientUtils.loadedEntityList()) {
            if (ent instanceof EntityLivingBase) {
                final EntityLivingBase entity;
                final EntityLivingBase entityLivingBase2;
                final EntityLivingBase entityLivingBase = entityLivingBase2 = (entity = (EntityLivingBase)ent);
                --entityLivingBase2.auraTicks;
            }
        }
    }
    
    @Override
    public boolean disable() {
        TickEdit.cancelNext = false;
        return super.disable();
    }
}
