package me.aristhena.client.module.modules.movement;

import me.aristhena.client.module.Module;
import me.aristhena.client.module.Module.Mod;
import me.aristhena.client.module.modules.movement.longjump.Hypixel;
import me.aristhena.client.module.modules.movement.longjump.NCP;
import me.aristhena.client.option.OptionManager;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.MoveEvent;
import me.aristhena.event.events.TickEvent;
import me.aristhena.event.events.UpdateEvent;
import me.aristhena.utils.ClientUtils;
import net.minecraft.potion.Potion;

@Mod(displayName = "LongJump")
public class LongJump extends Module
{
    public static final String Speed;
    public NCP ncp;
    public Hypixel hy;
    
    static {
        Speed = null;
    }
    
    public LongJump() {
        this.ncp = new NCP("NCP Glide", true, this);
        this.hy = new Hypixel("Hypixel Glide", false, this);
    }
    
    @Override
    public void preInitialize() {
        OptionManager.getOptionList().add(this.ncp);
        OptionManager.getOptionList().add(this.hy);
        this.updateSuffix();
        super.preInitialize();
    }
    
    @Override
    public void enable() {
        this.ncp.enable();
        this.hy.enable();
        super.enable();
    }
    
    @EventTarget
    private void onMove(final MoveEvent event) {
        this.ncp.onMove(event);
        this.hy.onMove(event);
    }
    
    @EventTarget
    private void onUpdate(final UpdateEvent event) {
        this.ncp.onUpdate(event);
        this.hy.onUpdate(event);
    }
    
    @EventTarget
    private void onTick(final TickEvent event) {
        this.updateSuffix();
    }
    
    private void updateSuffix() {
        if (this.ncp.getValue()) {
            this.setSuffix("NCP Glide");
        }
        else if (this.hy.getValue()) {
            this.setSuffix("Hypixel Glide");
        }
        else {
            this.setSuffix("None");
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
