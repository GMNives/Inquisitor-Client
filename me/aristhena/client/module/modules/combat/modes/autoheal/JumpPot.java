/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.client.module.modules.combat.modes.autoheal;

import me.aristhena.client.module.Module;
import me.aristhena.client.module.modules.auto.AutoHeal;
import me.aristhena.client.module.modules.combat.Aura;
import me.aristhena.client.module.modules.combat.modes.autoheal.AutoHealMode;
import me.aristhena.client.module.modules.combat.modes.autoheal.TimeHelper;
import me.aristhena.client.module.modules.movement.Speed;
import me.aristhena.client.option.OptionManager;
import me.aristhena.client.option.types.BooleanOption;
import me.aristhena.event.Event;
import me.aristhena.event.events.UpdateEvent;
import me.aristhena.utils.ClientUtils;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class JumpPot
extends AutoHealMode {
    private int haltTicks;
    public static TimeHelper timer;
    public static boolean potting;
    public static boolean potNextCompat;
    public static boolean swapTarget;
    private double x;
    private double y;
    private double z;
    private static int[] $SWITCH_TABLE$me$marisa$hackery$eventapi$Event$State;

    public JumpPot(String name, boolean value, Module module) {
        super(name, value, module);
        timer = new TimeHelper();
    }

    @Override
    public boolean enable() {
        if (super.enable()) {
            this.haltTicks = -1;
        }
        return true;
    }

    @Override
    public boolean onUpdate(UpdateEvent event) {
        if (super.onUpdate(event)) {
            Aura auraMod = new Aura();
            auraMod = (Aura)auraMod.getInstance();
            switch (JumpPot.$SWITCH_TABLE$me$marisa$hackery$eventapi$Event$State()[event.getState().ordinal()]) {
                case 1: {
                    boolean targets = false;
                    boolean nearbyEntities = false;
                    for (Entity entity : ClientUtils.loadedEntityList()) {
                        if (!auraMod.isEntityValid(entity)) continue;
                        targets = true;
                        break;
                    }
                    boolean check = timer.delay((float)AutoHeal.delay);
                    if (AutoHeal.compatible && auraMod.isEnabled() && targets && ((Boolean)((BooleanOption)OptionManager.getOption("Tick", "Aura")).getValue()).booleanValue()) {
                        check = potNextCompat;
                    }
                    if (JumpPot.getPotionFromInventory() != -1 && (double)ClientUtils.player().getHealth() <= AutoHeal.health * 2.0 && check) {
                        if (ClientUtils.player().isCollidedVertically && !new Speed().getInstance().isEnabled()) {
                            swapTarget = true;
                            timer.reset();
                            ClientUtils.packet(new C03PacketPlayer.C06PacketPlayerPosLook(ClientUtils.x(), ClientUtils.y(), ClientUtils.z(), ClientUtils.yaw(), -90.0f, true));
                            this.swap(JumpPot.getPotionFromInventory(), (int)AutoHeal.slot);
                            ClientUtils.packet(new C09PacketHeldItemChange((int)AutoHeal.slot));
                            ClientUtils.packet(new C08PacketPlayerBlockPlacement(ClientUtils.player().inventory.getCurrentItem()));
                            ClientUtils.packet(new C09PacketHeldItemChange(ClientUtils.player().inventory.currentItem));
                            ClientUtils.packet(new C03PacketPlayer.C04PacketPlayerPosition(ClientUtils.x(), ClientUtils.y() + 0.42, ClientUtils.z(), true));
                            ClientUtils.packet(new C03PacketPlayer.C04PacketPlayerPosition(ClientUtils.x(), ClientUtils.y() + 0.75, ClientUtils.z(), true));
                            ClientUtils.packet(new C03PacketPlayer.C04PacketPlayerPosition(ClientUtils.x(), ClientUtils.y() + 1.0, ClientUtils.z(), true));
                            ClientUtils.packet(new C03PacketPlayer.C04PacketPlayerPosition(ClientUtils.x(), ClientUtils.y() + 1.16, ClientUtils.z(), true));
                            ClientUtils.packet(new C03PacketPlayer.C04PacketPlayerPosition(ClientUtils.x(), ClientUtils.y() + 1.24, ClientUtils.z(), true));
                            this.x = ClientUtils.x();
                            this.y = ClientUtils.y() + 1.24;
                            this.z = ClientUtils.z();
                            this.haltTicks = 5;
                        } else {
                            event.setAlwaysSend(true);
                            event.setPitch(90.0f);
                            potting = true;
                            swapTarget = true;
                            timer.reset();
                        }
                    }
                    if (this.haltTicks >= 0) {
                        event.setCancelled(true);
                    }
                    if (this.haltTicks == 0) {
                        EntityPlayerSP player = ClientUtils.player();
                        EntityPlayerSP player2 = ClientUtils.player();
                        double n = 0.0;
                        player2.motionZ = 0.0;
                        player.motionX = 0.0;
                        ClientUtils.player().setPositionAndUpdate(this.x, this.y, this.z);
                        ClientUtils.player().motionY = -0.08;
                    }
                    --this.haltTicks;
                    break;
                }
                case 2: {
                    int potSlot = JumpPot.getPotionFromInventory();
                    if (!potting) break;
                    this.swap(JumpPot.getPotionFromInventory(), (int)AutoHeal.slot);
                    ClientUtils.packet(new C09PacketHeldItemChange((int)AutoHeal.slot));
                    ClientUtils.packet(new C08PacketPlayerBlockPlacement(ClientUtils.player().inventory.getCurrentItem()));
                    ClientUtils.packet(new C09PacketHeldItemChange(ClientUtils.player().inventory.currentItem));
                    timer.reset();
                    potting = false;
                }
            }
            if (event.getState().equals((Object)Event.State.PRE) && potNextCompat) {
                potNextCompat = false;
            }
        }
        return true;
    }

    @Override
    public boolean disable() {
        if (super.disable()) {
            potting = false;
        }
        return true;
    }

    protected void swap(int slot, int hotbarNum) {
        ClientUtils.playerController().windowClick(ClientUtils.player().inventoryContainer.windowId, slot, hotbarNum, 2, ClientUtils.player());
    }

    public static int getPotionFromInventory() {
        int pot = -1;
        for (int i = 1; i < 45; ++i) {
            ItemPotion potion;
            ItemStack is;
            Item item;
            if (!ClientUtils.player().inventoryContainer.getSlot(i).getHasStack() || !((item = (is = ClientUtils.player().inventoryContainer.getSlot(i).getStack()).getItem()) instanceof ItemPotion) || (potion = (ItemPotion)item).getEffects(is) == null) continue;
            for (Object o : potion.getEffects(is)) {
                PotionEffect effect = (PotionEffect)o;
                if (effect.getPotionID() != Potion.heal.id || !ItemPotion.isSplash(is.getItemDamage())) continue;
                pot = i;
            }
        }
        return pot;
    }

    static int[] $SWITCH_TABLE$me$marisa$hackery$eventapi$Event$State() {
        int[] var10000 = $SWITCH_TABLE$me$marisa$hackery$eventapi$Event$State;
        if (var10000 != null) {
            return var10000;
        }
        int[] var10001 = new int[Event.State.values().length];
        try {
            var10001[Event.State.POST.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            var10001[Event.State.PRE.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        $SWITCH_TABLE$me$marisa$hackery$eventapi$Event$State = var10001;
        return var10001;
    }
}

