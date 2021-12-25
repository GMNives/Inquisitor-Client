package me.aristhena.client.module.modules.player;

import me.aristhena.client.module.Module;
import me.aristhena.client.module.Module.Mod;
import me.aristhena.client.module.modules.player.antibot.AAC;
import me.aristhena.client.module.modules.player.antibot.Hypixel;
import me.aristhena.client.option.OptionManager;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.TickEvent;
import me.aristhena.event.events.UpdateEvent;

@Mod
public class AntiBot extends Module
{
    public Hypixel hy;
    public AAC gw;
    
    public AntiBot() {
        this.hy = new Hypixel("Hypixel", true, this);
        this.gw = new AAC("AAC", false, this);
    }
    
    @Override
    public void preInitialize() {
        OptionManager.getOptionList().add(this.hy);
        OptionManager.getOptionList().add(this.gw);
        super.preInitialize();
    }
    
    @Override
    public void enable() {
        this.hy.enable();
        this.gw.enable();
        super.enable();
    }
    
    @EventTarget
    private void onUpdate(final UpdateEvent event) {
        this.hy.onUpdate(event);
        this.gw.onUpdate(event);
    }
    
    @EventTarget
    private void onTick(final TickEvent event) {
        this.updateSuffix();
    }
    
    private void updateSuffix() {
        if (this.hy.getValue()) {
            this.setSuffix("Hypixel");
        }
        else if (this.gw.getValue()) {
            this.setSuffix("Gwen");
        }
    }
}
