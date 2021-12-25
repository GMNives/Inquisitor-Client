package me.aristhena.client.module.modules.movement;

import me.aristhena.client.module.Module;
import me.aristhena.client.module.Module.Mod;
import me.aristhena.client.module.modules.movement.nofall.AAC;
import me.aristhena.client.module.modules.movement.nofall.Vanilla;
import me.aristhena.client.option.OptionManager;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.TickEvent;
import me.aristhena.event.events.UpdateEvent;

@Mod
public class NoFall extends Module
{
    public Vanilla v;
    public AAC aac;
    
    public NoFall() {
        this.v = new Vanilla("Vanilla", true, this);
        this.aac = new AAC("AAC", false, this);
    }
    
    @Override
    public void preInitialize() {
        OptionManager.getOptionList().add(this.aac);
        OptionManager.getOptionList().add(this.v);
        this.updateSuffix();
        super.preInitialize();
    }
    
    @EventTarget
    private void onUpdate(final UpdateEvent event) {
        this.aac.onUpdate(event);
        this.v.onUpdate(event);
    }
    
    @EventTarget
    private void onTick(final TickEvent event) {
        this.updateSuffix();
    }
    
    private void updateSuffix() {
        if (this.v.getValue()) {
            this.setSuffix("Vanilla");
        }
        else {
            this.setSuffix("AAC");
        }
    }
}
