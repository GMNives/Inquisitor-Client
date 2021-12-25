/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.client.module.modules.player;

import me.aristhena.client.Client;
import me.aristhena.client.module.Module;
import me.aristhena.event.Event;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.UpdateEvent;
import me.aristhena.utils.ClientUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S30PacketWindowItems;

@Module.Mod(displayName="Chest Stealer")
public class ChestStealer
extends Module {
    private S30PacketWindowItems packet;

    @EventTarget
    private void onUpdate(UpdateEvent event) {
        if (event.getState().equals((Object)Event.State.PRE)) {
            ClientUtils.mc();
            if (Minecraft.currentScreen instanceof GuiChest) {
                if (!this.isContainerEmpty(ClientUtils.player().openContainer)) {
                    ClientUtils.mc();
                    GuiChest guiChest = (GuiChest)Minecraft.currentScreen;
                    boolean full = true;
                    ItemStack[] mainInventory = ClientUtils.player().inventory.mainInventory;
                    int length = ClientUtils.player().inventory.mainInventory.length;
                    for (int i = 0; i < length; ++i) {
                        ItemStack item = mainInventory[i];
                        if (item != null) continue;
                        full = false;
                        break;
                    }
                    if (!full) {
                        for (int index = 0; index < guiChest.lowerChestInventory.getSizeInventory(); ++index) {
                            ItemStack stack = guiChest.lowerChestInventory.getStackInSlot(index);
                            if (stack == null) continue;
                            ClientUtils.playerController().windowClick(guiChest.inventorySlots.windowId, index, 0, 1, ClientUtils.player());
                            break;
                        }
                    }
                } else {
                    ClientUtils.player().closeScreen();
                }
            }
        }
    }

    public boolean isContainerEmpty(Container container) {
        boolean temp = true;
        int slotAmount = container.inventorySlots.size() == 90 ? 54 : 27;
        for (int i = 0; i < slotAmount; ++i) {
            if (!container.getSlot(i).getHasStack()) continue;
            temp = false;
        }
        return temp;
    }
}

