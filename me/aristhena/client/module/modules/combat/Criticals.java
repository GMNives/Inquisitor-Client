package me.aristhena.client.module.modules.combat;

import me.aristhena.client.module.Module;
import me.aristhena.client.module.Module.Mod;
import me.aristhena.client.module.modules.combat.criticals.Jump;
import me.aristhena.client.module.modules.combat.criticals.MiniJump;
import me.aristhena.client.module.modules.combat.criticals.Packet;
import me.aristhena.client.option.OptionManager;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.PacketSendEvent;
import me.aristhena.event.events.TickEvent;
import me.aristhena.utils.ClientUtils;
import me.aristhena.utils.LiquidUtils;
import net.minecraft.block.material.Material;

@Mod
public class Criticals extends Module
{
	private MiniJump minijump;
    private Jump jump;
    private Packet packet;
    
    public Criticals() {
        this.jump = new Jump("Jump", false, this);
        this.packet = new Packet("Packet", false, this);
        this.minijump = new MiniJump("MiniJump", false, this);
    }
    
    @Override
    public void preInitialize() {
        OptionManager.getOptionList().add(this.jump);
        OptionManager.getOptionList().add(this.packet);
        OptionManager.getOptionList().add(this.minijump);
        super.preInitialize();
    }
    
    @EventTarget
    public void onPacketSend(final PacketSendEvent event) {
        if (this.jump.getValue()) {
            this.jump.onPacketSend(event);
        }
            if (this.minijump.getValue()) {
                this.minijump.onPacketSend(event);
        }
        if (this.packet.getValue()) {
            this.packet.onPacketSend(event);
        }
    }
    
    @EventTarget
    public void onTick(final TickEvent event) {
        this.updateSuffix();
    }
    
    private void updateSuffix() {
        if (this.jump.getValue()) {
            this.setSuffix("Jump");
        }
        if (this.minijump.getValue()) {
            this.setSuffix("MiniJump");
   
        }
        if (this.packet.getValue()) {
            this.setSuffix("Packet");
    }
    
    }
    
    public static boolean canCrit() {
        return ClientUtils.player().onGround && !LiquidUtils.isInLiquid() && !ClientUtils.mc().thePlayer.isInsideOfMaterial(Material.lava) && !ClientUtils.mc().thePlayer.isOnLadder() && ClientUtils.mc().thePlayer.ridingEntity == null && ClientUtils.mc().thePlayer.isCollidedVertically;
    }
}
