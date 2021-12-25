package me.aristhena.client.module.modules.combat;

import me.aristhena.client.module.Module;
import me.aristhena.client.module.Module.Mod;
import me.aristhena.client.module.modules.combat.velocity.AAC;
import me.aristhena.client.module.modules.combat.velocity.NCP;
import me.aristhena.client.option.OptionManager;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.PacketReceiveEvent;
import me.aristhena.event.events.TickEvent;
import me.aristhena.event.events.UpdateEvent;

@Mod
public class Velocity extends Module
{
    public NCP ncp;
    public AAC aac;
    
    public Velocity() {
        this.ncp = new NCP("NCP", true, this);
        this.aac = new AAC("AAC", false, this);
    }
    
    @Override
    public void preInitialize() {
        OptionManager.getOptionList().add(this.aac);
        OptionManager.getOptionList().add(this.ncp);
        this.updateSuffix();
        super.preInitialize();
    }
    
    @EventTarget
    private void onPacketReceive(final PacketReceiveEvent event) {
        this.ncp.onPacketReceiveEvent(event);
    }
    
    @EventTarget
    private void onUpdate(final UpdateEvent event) {
        this.aac.onUpdate(event);
    }
    
    @EventTarget
    private void onTick(final TickEvent event) {
        this.updateSuffix();
    }
    
    private void updateSuffix() {
        if (this.ncp.getValue()) {
            this.setSuffix("NCP");
        }
        else {
            this.setSuffix("AAC");
        }
    }
}
