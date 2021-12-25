package me.aristhena.client.module.modules.auto;

import me.aristhena.client.module.*;
import me.aristhena.event.events.*;
import me.aristhena.utils.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.entity.player.*;
import me.aristhena.event.*;
import net.minecraft.item.*;
import net.minecraft.enchantment.*;

@Module.Mod(displayName = "Auto Armor")
public class AutoArmor extends Module
{
    @EventTarget
    private void onTick(final TickEvent event) {
        if (ClientUtils.player() != null && (ClientUtils.mc().currentScreen == null || ClientUtils.mc().currentScreen instanceof GuiInventory || !ClientUtils.mc().currentScreen.getClass().getName().contains("inventory"))) {
            int slotID = -1;
            double maxProt = -1.0;
            for (int i = 9; i < 45; ++i) {
                final ItemStack stack = ClientUtils.player().inventoryContainer.getSlot(i).getStack();
                if (stack != null) {
                    if (this.canEquip(stack)) {
                        final double protValue = this.getProtectionValue(stack);
                        if (protValue >= maxProt) {
                            slotID = i;
                            maxProt = protValue;
                        }
                    }
                }
            }
            if (slotID != -1) {
                ClientUtils.playerController().windowClick(ClientUtils.player().inventoryContainer.windowId, slotID, 0, 1, (EntityPlayer)ClientUtils.player());
            }
        }
    }
    
    private boolean canEquip(final ItemStack stack) {
        return (ClientUtils.player().getEquipmentInSlot(1) == null && stack.getUnlocalizedName().contains("boots")) || (ClientUtils.player().getEquipmentInSlot(2) == null && stack.getUnlocalizedName().contains("leggings")) || (ClientUtils.player().getEquipmentInSlot(3) == null && stack.getUnlocalizedName().contains("chestplate")) || (ClientUtils.player().getEquipmentInSlot(4) == null && stack.getUnlocalizedName().contains("helmet"));
    }
    
    private double getProtectionValue(final ItemStack stack) {
        return ((ItemArmor)stack.getItem()).damageReduceAmount + (100 - ((ItemArmor)stack.getItem()).damageReduceAmount * 4) * EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId, stack) * 4 * 0.0075;
    }
}
