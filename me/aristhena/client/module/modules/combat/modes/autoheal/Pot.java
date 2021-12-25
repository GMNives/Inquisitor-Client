/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.client.module.modules.combat.modes.autoheal;

import me.aristhena.client.module.Module;
import me.aristhena.client.module.modules.auto.AutoHeal;
import me.aristhena.client.module.modules.combat.modes.autoheal.AutoHealMode;
import me.aristhena.client.module.modules.combat.modes.autoheal.TimeHelper;
import me.aristhena.event.Event;
import me.aristhena.event.events.UpdateEvent;
import me.aristhena.utils.ClientUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class Pot
extends AutoHealMode {
    private TimeHelper timer = new TimeHelper();
    private static int[] $SWITCH_TABLE$me$marisa$hackery$eventapi$Event$State;

    public Pot(String name, boolean value, Module module) {
        super(name, value, module);
    }

    @Override
    public boolean onUpdate(UpdateEvent event) {
        if (super.onUpdate(event)) {
            switch (Pot.$SWITCH_TABLE$me$marisa$hackery$eventapi$Event$State()[event.getState().ordinal()]) {
                case 1: {
                    if (Pot.getPotionFromInventory() == -1 || !((double)ClientUtils.player().getHealth() <= AutoHeal.health * 2.0) || !this.timer.delay((float)AutoHeal.delay)) break;
                    event.setAlwaysSend(true);
                    event.setPitch(110.0f);
                    break;
                }
                case 2: {
                    int potSlot = Pot.getPotionFromInventory();
                    if (potSlot == -1 || !((double)ClientUtils.player().getHealth() <= AutoHeal.health * 2.0) || !this.timer.delay((float)AutoHeal.delay)) break;
                    int currentItem = ClientUtils.player().inventory.currentItem;
                    this.swap(potSlot, 8);
                    ClientUtils.packet(new C09PacketHeldItemChange(8));
                    ClientUtils.packet(new C08PacketPlayerBlockPlacement(ClientUtils.player().inventory.getCurrentItem()));
                    ClientUtils.packet(new C09PacketHeldItemChange(currentItem));
                    this.timer.reset();
                }
            }
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

