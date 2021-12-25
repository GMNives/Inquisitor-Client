/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.client.module.modules.auto;

import me.aristhena.client.module.Module;
import me.aristhena.client.module.modules.combat.modes.autoheal.JumpPot;
import me.aristhena.client.module.modules.combat.modes.autoheal.Pot;
import me.aristhena.client.module.modules.combat.modes.autoheal.TimeHelper;
import me.aristhena.client.option.Option;
import me.aristhena.client.option.OptionManager;
import me.aristhena.event.Event;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.UpdateEvent;
import me.aristhena.utils.ClientUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;

@Module.Mod(displayName="AutoHeal")
public class AutoHeal
extends Module {
    @Option.Op(name="Soup")
    public static boolean soup;
    @Option.Op(name="Slot", min=0.0, max=9.0, increment=1.0)
    public static double slot;
    @Option.Op(name="Health", min=0.5, max=10.0, increment=0.5)
    public static double health;
    @Option.Op(name="Delay", min=200.0, max=1000.0, increment=25.0)
    public static double delay;
    @Option.Op(name="Compatible")
    public static boolean compatible;
    @Option.Op(name="Potion Counter")
    public static boolean potcounter;
    public static Pot pot;
    public static JumpPot jPot;
    private TimeHelper timer;
    private static int[] $SWITCH_TABLE$me$marisa$hackery$eventapi$Event$State;

    public AutoHeal() {
        pot = new Pot("Potion", true, this);
        jPot = new JumpPot("Jump Potion", false, this);
        this.timer = new TimeHelper();
    }

    @Override
    public void preInitialize() {
        OptionManager.getOptionList().add(pot);
        OptionManager.getOptionList().add(jPot);
        super.postInitialize();
    }

    @Override
    public void enable() {
        pot.enable();
        jPot.enable();
        super.enable();
    }

    @EventTarget(value=3)
    public void onUpdate(UpdateEvent event) {
        if (((Boolean)pot.getValue()).booleanValue() && !soup && !potcounter) {
            pot.onUpdate(event);
            jPot.onUpdate(event);
            if (soup) {
                switch (AutoHeal.$SWITCH_TABLE$me$marisa$hackery$eventapi$Event$State()[event.getState().ordinal()]) {
                    case 2: {
                        int soupSlot = AutoHeal.getSoupFromInventory();
                        if (AutoHeal.getSoupFromInventory() == -1 || !((double)ClientUtils.player().getHealth() <= health * 2.0) || !this.timer.delay((float)delay)) break;
                        this.swap(AutoHeal.getSoupFromInventory(), (int)slot);
                        ClientUtils.packet(new C09PacketHeldItemChange((int)slot));
                        ClientUtils.packet(new C08PacketPlayerBlockPlacement(ClientUtils.player().inventory.getCurrentItem()));
                        ClientUtils.packet(new C09PacketHeldItemChange(ClientUtils.player().inventory.currentItem));
                        this.timer.reset();
                    }
                }
            }
        }
    }

    @Override
    public void disable() {
        pot.disable();
        jPot.disable();
        super.disable();
    }

    protected void swap(int slot, int hotbarNum) {
        ClientUtils.playerController().windowClick(ClientUtils.player().inventoryContainer.windowId, slot, hotbarNum, 2, ClientUtils.player());
    }

    public static int getSoupFromInventory() {
        int soup = -1;
        for (int i = 1; i < 45; ++i) {
            ItemStack is;
            Item item;
            if (!ClientUtils.player().inventoryContainer.getSlot(i).getHasStack() || Item.getIdFromItem(item = (is = ClientUtils.player().inventoryContainer.getSlot(i).getStack()).getItem()) != 282) continue;
            soup = i;
        }
        return soup;
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

