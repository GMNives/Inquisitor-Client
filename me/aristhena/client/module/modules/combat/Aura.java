package me.aristhena.client.module.modules.combat;

import net.minecraft.item.*;
import net.minecraft.entity.item.*;
import net.minecraft.util.*;
import net.minecraft.network.*;
import net.minecraft.entity.player.*;
import net.minecraft.network.play.server.*;
import net.minecraft.entity.*;
import net.minecraft.entity.boss.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import me.aristhena.client.friend.*;
import me.aristhena.client.module.*;
import me.aristhena.client.module.Module.Mod;
import me.aristhena.client.module.modules.combat.aura.*;
import me.aristhena.client.option.*;
import me.aristhena.event.*;
import me.aristhena.event.events.*;
import me.aristhena.utils.*;
import me.aristhena.utils.Timer;
import net.minecraft.enchantment.*;
import net.minecraft.potion.*;
import net.minecraft.network.play.client.*;

@Mod
public class Aura extends Module
{
    private Switch switchMode;
    private BreakerSwitch breakerswitch;
    private Single single;
    private Dick22 regenswitch;
    private TickEdit tickedit;
    private Juan juan;
    @Option.Op(min = 0.0, max = 20.0, increment = 0.25)
    public double speed;
    @Option.Op(min = 0.1, max = 7.0, increment = 0.1)
    public double range;
    @Option.Op(name = "Block Range", min = 3.5, max = 12.0, increment = 0.5)
    public double blockRange;
    @Option.Op(min = 0.0, max = 180.0, increment = 5.0)
    public double degrees;
    @Option.Op(name = "Ticks Existed", min = 0.0, max = 25.0, increment = 1.0)
    public double ticksExisted;
    @Option.Op
    private boolean players;
    @Option.Op
    private boolean monsters;
    @Option.Op
    private boolean animals;
    @Option.Op
    private boolean bats;
    @Option.Op
    private boolean villagers;
    @Option.Op
    private boolean golems;
    @Option.Op
    private boolean booblebee;
    @Option.Op
    private boolean noswing;
    @Option.Op
    public boolean noslowdown;
    @Option.Op
    public boolean criticals;
    @Option.Op
    public boolean focus;
    @Option.Op
    public boolean durabilty2;
    @Option.Op(name = "Durability")
    public boolean dura;
    @Option.Op(name = "Clans")
    public boolean clans;
    @Option.Op(name = "Auto Block")
    public boolean autoblock;
    @Option.Op(name = "Armor Check")
    private boolean armorCheck;
    @Option.Op(name = "Attack Friends")
    private boolean attackFriends;
    private boolean jumpNextTick;
    private ItemStack predictedItem;
    private Timer pickupTimer;
    private Timer potTimer;
    
    public Aura() {
        this.switchMode = new Switch("Switch", true, this);
        this.breakerswitch = new BreakerSwitch("BreakerSwitch", false, this);
        this.single = new Single("Single", false, this);
        this.regenswitch = new Dick22("RegenSwitch", false, this);
        this.tickedit = new TickEdit("TickEdit", false, this);
        this.juan = new Juan("Juan", false, this);
        this.speed = 8.0;
        this.range = 4.2;
        this.blockRange = 8.0;
        this.degrees = 90.0;
        this.ticksExisted = 10.0;
        this.players = true;
        this.booblebee = true;
        this.pickupTimer = new Timer();
        this.potTimer = new Timer();
    }
    
    @Override
    public void preInitialize() {
        OptionManager.getOptionList().add(this.switchMode);
        OptionManager.getOptionList().add(this.breakerswitch);
        OptionManager.getOptionList().add(this.single);
        OptionManager.getOptionList().add(this.regenswitch);
        OptionManager.getOptionList().add(this.tickedit);
        OptionManager.getOptionList().add(this.juan);
        this.updateSuffix();
        super.preInitialize();
    }
    
    @Override
    public void enable() {
        this.single.enable();
        this.breakerswitch.enable();
        this.regenswitch.enable();
        this.tickedit.enable();
        this.juan.enable();
        super.enable();
    }
    
    private void onPacketReceive(final PacketReceiveEvent event) {
        if (event.getPacket() instanceof S0DPacketCollectItem) {
            final S0DPacketCollectItem packet = (S0DPacketCollectItem)event.getPacket();
            final Entity item = ClientUtils.world().getEntityByID(packet.func_149354_c());
            if (item instanceof EntityItem) {
                final EntityItem itemEntity = (EntityItem)item;
                this.predictedItem = itemEntity.getEntityItem();
                this.pickupTimer.reset();
            }
        }
        if (event.getPacket() instanceof S2FPacketSetSlot) {
            final S2FPacketSetSlot packet2 = (S2FPacketSetSlot)event.getPacket();
            if (!this.potTimer.delay(6.0f) && packet2.func_149173_d() == -1 && packet2.func_149175_c() == -1) {
                this.potTimer.setDifference(7L);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(6L);
                        }
                        catch (InterruptedException ex) {}
                        ClientUtils.sendMessage("Post - " + System.currentTimeMillis());
                        ClientUtils.packet(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.DROP_ALL_ITEMS, BlockPos.ORIGIN, EnumFacing.DOWN));
                        ClientUtils.playerController().windowClick(ClientUtils.player().inventoryContainer.windowId, -999, 0, 5, ClientUtils.player());
                        ClientUtils.playerController().windowClick(ClientUtils.player().inventoryContainer.windowId, 36 + ClientUtils.player().inventory.currentItem, 1, 5, ClientUtils.player());
                        ClientUtils.playerController().windowClick(ClientUtils.player().inventoryContainer.windowId, -999, 2, 5, ClientUtils.player());
                        if (ClientUtils.player().inventory.getItemStack() != null) {
                            ClientUtils.sendMessage("Fake Placingxd");
                            ClientUtils.playerController().windowClick(ClientUtils.player().inventoryContainer.windowId, 36 + ClientUtils.player().inventory.currentItem, 0, 0, ClientUtils.player());
                        }
                    }
                }).start();
            }
            if (!this.pickupTimer.delay(10.0f) && this.predictedItem != null && ItemStack.areItemStackTagsEqual(this.predictedItem, packet2.func_149174_e()) && packet2.func_149173_d() == 36) {
                event.setCancelled(true);
                this.predictedItem = null;
            }
            else {
                this.predictedItem = null;
            }
        }
        if (event.getPacket() instanceof S30PacketWindowItems && this.potTimer.delay(400.0f)) {
            final S30PacketWindowItems packet3 = (S30PacketWindowItems)event.getPacket();
            ClientUtils.sendMessage("Pre - " + System.currentTimeMillis());
            this.potTimer.reset();
        }
        this.potTimer.delay(10.0f);
    }
    
    @EventTarget
    private void onJump(final JumpEvent event) {
        if (StateManager.offsetLastPacketAura()) {
            event.setCancelled(this.jumpNextTick = true);
        }
    }
    
    @EventTarget
    private void onUpdate(final UpdateEvent event) {
        if (this.jumpNextTick && !StateManager.offsetLastPacketAura()) {
            ClientUtils.player().jump();
            this.jumpNextTick = false;
        }
        this.switchMode.onUpdate(event);
        this.breakerswitch.onUpdate(event);
        this.single.onUpdate(event);
        this.regenswitch.onUpdate(event);
        this.tickedit.onUpdate(event);
        this.juan.onUpdate(event);
    }
    
    @EventTarget
    private void onTick(final TickEvent event) {
        this.updateSuffix();
    }
    
    private void updateSuffix() {
        if (this.switchMode.getValue()) {
            this.setSuffix("Switch");
        }
        if (this.breakerswitch.getValue()) {
            this.setSuffix("BreakerSwitch");
        }
        else if (this.regenswitch.getValue()) {
            this.setSuffix("RegenSwitch");
        }
        else if (this.tickedit.getValue()) {
            this.setSuffix("TickEdit");
        }
        else if (this.juan.getValue()) {
            this.setSuffix("Juan");
        }
        else {
            this.setSuffix("Single");
        }
    }
    
    public boolean isEntityValid(final Entity entity) {
        if (entity instanceof EntityLivingBase) {
            final EntityLivingBase entityLiving = (EntityLivingBase)entity;
            if (!ClientUtils.player().isEntityAlive() || !entityLiving.isEntityAlive() || entityLiving.getDistanceToEntity(ClientUtils.player()) > (ClientUtils.player().canEntityBeSeen(entityLiving) ? this.range : 3.0)) {
                return false;
            }
            if (entityLiving.ticksExisted < this.ticksExisted) {
                return false;
            }
            if (this.players && entityLiving instanceof EntityPlayer) {
                final EntityPlayer entityPlayer = (EntityPlayer)entityLiving;
                if (FriendManager.isFriend(entityPlayer.getName()) && !this.attackFriends) {
                    return false;
                }
                if (this.armorCheck && !this.hasArmor(entityPlayer)) {
                    return false;
                }
                final ItemStack boots = entityPlayer.getEquipmentInSlot(1);
                final ItemStack legs = entityPlayer.getEquipmentInSlot(2);
                final ItemStack chest = entityPlayer.getEquipmentInSlot(3);
                final ItemStack helm = entityPlayer.getEquipmentInSlot(4);
                boolean fuckedUpArmorOrder = false;
                if (boots != null && boots.getUnlocalizedName().contains("helmet")) {
                    fuckedUpArmorOrder = true;
                }
                if (legs != null && legs.getUnlocalizedName().contains("chestplate")) {
                    fuckedUpArmorOrder = true;
                }
                if (chest != null && chest.getUnlocalizedName().contains("leggings")) {
                    fuckedUpArmorOrder = true;
                }
                if (helm != null && helm.getUnlocalizedName().contains("boots")) {
                    fuckedUpArmorOrder = true;
                }
                return !fuckedUpArmorOrder;
            }
            else {
                if (this.monsters && (entityLiving instanceof EntityMob || entityLiving instanceof EntityGhast || entityLiving instanceof EntityDragon || entityLiving instanceof EntityWither || entityLiving instanceof EntitySlime || (entityLiving instanceof EntityWolf && ((EntityWolf)entityLiving).getOwner() != ClientUtils.player()))) {
                    return true;
                }
                if (this.golems && entityLiving instanceof EntityGolem) {
                    return true;
                }
                if (this.animals && (entityLiving instanceof EntityAnimal || entityLiving instanceof EntitySquid)) {
                    return true;
                }
                if (this.bats && entityLiving instanceof EntityBat) {
                    return true;
                }
                if (this.villagers && entityLiving instanceof EntityVillager) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @EventTarget
    private void onPacketSend(final PacketSendEvent event) {
        if (event.getPacket() instanceof C09PacketHeldItemChange) {
            Switch.potTimer.reset();
        }
    }
    
    public boolean isEntityValidType(final Entity entity) {
        if (entity instanceof EntityLivingBase) {
            final EntityLivingBase entityLiving = (EntityLivingBase)entity;
            if (!ClientUtils.player().isEntityAlive() || !entityLiving.isEntityAlive()) {
                return false;
            }
            if (this.players && entityLiving instanceof EntityPlayer) {
                final EntityPlayer entityPlayer = (EntityPlayer)entityLiving;
                if (entityPlayer.isInvisible() && !this.booblebee) {
                    return false;
                }
                if (FriendManager.isFriend(entityPlayer.getName()) && !this.attackFriends) {
                    return false;
                }
                if (this.armorCheck && !this.hasArmor(entityPlayer)) {
                    return false;
                }
                final ItemStack boots = entityPlayer.getEquipmentInSlot(1);
                final ItemStack legs = entityPlayer.getEquipmentInSlot(2);
                final ItemStack chest = entityPlayer.getEquipmentInSlot(3);
                final ItemStack helm = entityPlayer.getEquipmentInSlot(4);
                boolean fuckedUpArmorOrder = false;
                if (boots != null && boots.getUnlocalizedName().contains("helmet")) {
                    fuckedUpArmorOrder = true;
                }
                if (legs != null && legs.getUnlocalizedName().contains("chestplate")) {
                    fuckedUpArmorOrder = true;
                }
                if (chest != null && chest.getUnlocalizedName().contains("leggings")) {
                    fuckedUpArmorOrder = true;
                }
                if (helm != null && helm.getUnlocalizedName().contains("boots")) {
                    fuckedUpArmorOrder = true;
                }
                return !fuckedUpArmorOrder;
            }
            else {
                if (this.monsters && (entityLiving instanceof EntityMob || entityLiving instanceof EntityGhast || entityLiving instanceof EntityDragon || entityLiving instanceof EntityWither || entityLiving instanceof EntitySlime || (entityLiving instanceof EntityWolf && ((EntityWolf)entityLiving).getOwner() != ClientUtils.player()))) {
                    return true;
                }
                if (this.golems && entityLiving instanceof EntityGolem) {
                    return true;
                }
                if (this.animals && (entityLiving instanceof EntityAnimal || entityLiving instanceof EntitySquid)) {
                    return true;
                }
                if (this.bats && entityLiving instanceof EntityBat) {
                    return true;
                }
                if (this.villagers && entityLiving instanceof EntityVillager) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean hasArmor(final EntityPlayer player) {
        final ItemStack boots = player.inventory.armorInventory[0];
        final ItemStack pants = player.inventory.armorInventory[1];
        final ItemStack chest = player.inventory.armorInventory[2];
        final ItemStack head = player.inventory.armorInventory[3];
        return boots != null || pants != null || chest != null || head != null;
    }
    
    public void attack(final EntityLivingBase entity) {
        this.attack(entity, this.criticals);
    }
    
    public void attack(final EntityLivingBase entity, final boolean crit) {
        this.swingItem();
        final float sharpLevel = EnchantmentHelper.func_152377_a(ClientUtils.player().getHeldItem(), entity.getCreatureAttribute());
        final boolean vanillaCrit = ClientUtils.player().fallDistance > 0.0f && !ClientUtils.player().onGround && !ClientUtils.player().isOnLadder() && !ClientUtils.player().isInWater() && !ClientUtils.player().isPotionActive(Potion.blindness) && ClientUtils.player().ridingEntity == null;
        ClientUtils.packet(new C02PacketUseEntity(entity, C02PacketUseEntity.Action.ATTACK));
        if (crit || vanillaCrit) {
            ClientUtils.player().onCriticalHit(entity);
        }
        if (sharpLevel > 0.0f) {
            ClientUtils.player().onEnchantmentCritical(entity);
        }
    }
    
    public void pseudoAttack(final EntityLivingBase entity, final boolean crit) {
        this.swingItem();
        final float sharpLevel = EnchantmentHelper.func_152377_a(ClientUtils.player().getHeldItem(), entity.getCreatureAttribute());
        final boolean vanillaCrit = ClientUtils.player().fallDistance > 0.0f && !ClientUtils.player().onGround && !ClientUtils.player().isOnLadder() && !ClientUtils.player().isInWater() && !ClientUtils.player().isPotionActive(Potion.blindness) && ClientUtils.player().ridingEntity == null;
        if (crit || vanillaCrit) {
            ClientUtils.player().onCriticalHit(entity);
        }
        if (sharpLevel > 0.0f) {
            ClientUtils.player().onEnchantmentCritical(entity);
        }
    }
    
    public void swingItem() {
        if (!this.noswing) {
            ClientUtils.player().swingItem();
        }
    }
    
    @Override
    public void disable() {
        StateManager.setOffsetLastPacketAura(false);
        this.breakerswitch.disable();
        super.disable();
        StateManager.setOffsetLastPacketAura(false);
        this.regenswitch.disable();
        super.disable();
        StateManager.setOffsetLastPacketAura(false);
        this.tickedit.disable();
        super.disable();
    }
}
