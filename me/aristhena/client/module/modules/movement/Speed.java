// 
// Decompiled by Procyon v0.5.30
// 

package me.aristhena.client.module.modules.movement;

import me.aristhena.client.module.Module;
import me.aristhena.client.module.Module.Mod;
import me.aristhena.client.module.modules.movement.modespeed.Arenabrawl;
import me.aristhena.client.module.modules.movement.modespeed.Bhop;
import me.aristhena.client.module.modules.movement.modespeed.GayHop;
import me.aristhena.client.module.modules.movement.modespeed.Jump;
import me.aristhena.client.module.modules.movement.modespeed.Latest;
import me.aristhena.client.module.modules.movement.modespeed.LowHop;
import me.aristhena.client.module.modules.movement.modespeed.Minez;
import me.aristhena.client.module.modules.movement.modespeed.Vanilla;
import me.aristhena.client.module.modules.movement.modespeed.Vhop;
import me.aristhena.client.option.Option;
import me.aristhena.client.option.OptionManager;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.MoveEvent;
import me.aristhena.event.events.TickEvent;
import me.aristhena.event.events.UpdateEvent;
import me.aristhena.utils.ClientUtils;
import net.minecraft.potion.Potion;
@Mod
public class Speed extends Module
{
    public Latest latest;
    public Bhop bhop;
    private Vanilla vanilla;
    private Minez minez;
    private Jump fuckingcool;
    public GayHop gayHop;
    private Arenabrawl arenabrawl;
    private Vhop vhop;
    private LowHop lowhop;
    @Option.Op(min = 0.2, max = 10.0, increment = 0.05)
    public double speed;
    
    public Speed() {
        this.latest = new Latest("Latest", true, this);
        this.bhop = new Bhop("Bhop", false, this);
        this.vhop = new Vhop("Vhop", false, this);
        this.lowhop = new LowHop("LowHop", false, this);
        this.vanilla = new Vanilla("Vanilla", false, this);
        this.minez = new Minez("Minez", false, this);
        this.fuckingcool = new Jump("Fucking Cool", false, this);
        this.gayHop = new GayHop("GayHop", false, this);
        this.arenabrawl = new Arenabrawl("Arenabrawl", false, this);
        this.speed = 0.5;
    }
    
    @Override
    public void preInitialize() {
        OptionManager.getOptionList().add(this.latest);
        OptionManager.getOptionList().add(this.bhop);
        OptionManager.getOptionList().add(this.vhop);
        OptionManager.getOptionList().add(this.lowhop);
        OptionManager.getOptionList().add(this.gayHop);
        OptionManager.getOptionList().add(this.vanilla);
        OptionManager.getOptionList().add(this.minez);
        OptionManager.getOptionList().add(this.fuckingcool);
        OptionManager.getOptionList().add(this.arenabrawl);
        this.updateSuffix();
        super.preInitialize();
    }
    
    @Override
    public void enable() {
        this.latest.enable();
        this.vhop.enable();
        this.lowhop.enable();
        this.arenabrawl.enable();
        this.vanilla.enable();
        this.minez.enable();
        this.fuckingcool.enable();
        this.arenabrawl.enable();
        super.enable();
    }
    
    @EventTarget
    private void onMove(final MoveEvent event) {
        this.latest.onMove(event);
        this.bhop.onMove(event);
        this.vhop.onMove(event);
        this.lowhop.onMove(event);
        this.gayHop.onMove(event);
        this.vanilla.onMove(event);
        this.minez.onMove(event);
        this.fuckingcool.onMove(event);
        this.arenabrawl.onMove(event);
    }
    
    @EventTarget
    private void onUpdate(final UpdateEvent event) {
        this.latest.onUpdate(event);
        this.bhop.onUpdate(event);
        this.vhop.onUpdate(event);
        this.lowhop.onUpdate(event);
        this.gayHop.onUpdate(event);
        this.vanilla.onUpdate(event);
        this.minez.onUpdate(event);
        this.fuckingcool.onUpdate(event);
    }
    
    @EventTarget
    private void onTick(final TickEvent event) {
        this.updateSuffix();
    }
    
    private void updateSuffix() {
        if (this.latest.getValue()) {
            this.setSuffix("Latest");
        }
        else if (this.bhop.getValue()) {
            this.setSuffix("Bhop");
        }
        else if (this.vhop.getValue()) {
            this.setSuffix("Vhop");
        }
        else if (this.lowhop.getValue()) {
            this.setSuffix("LowHop");
        }
        else if (this.gayHop.getValue()) {
            this.setSuffix("Gay Hop");
        }
        else if (this.vanilla.getValue()) {
            this.setSuffix("Vanilla");
        }
        else if (this.arenabrawl.getValue()) {
            this.setSuffix("Arenabrawl");
        }
        else if (this.fuckingcool.getValue()) {
            this.setSuffix("Jump");
        }
        else {
            this.setSuffix("Minez");
        }
    }
    
    public static double getBaseMoveSpeed() {
        double baseSpeed = 0.2873;
        if (ClientUtils.player().isPotionActive(Potion.moveSpeed)) {
            final int amplifier = ClientUtils.player().getActivePotionEffect(Potion.moveSpeed).getAmplifier();
            baseSpeed *= 1.0 + 0.2 * (amplifier + 1);
        }
        return baseSpeed;
    }
}
