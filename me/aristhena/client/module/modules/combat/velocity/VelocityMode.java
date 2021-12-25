package me.aristhena.client.module.modules.combat.velocity;

import java.util.*;

import me.aristhena.client.module.*;
import me.aristhena.client.option.*;
import me.aristhena.client.option.types.*;
import me.aristhena.event.events.*;

public class VelocityMode extends BooleanOption
{
    public VelocityMode(final String name, final boolean value, final Module module) {
        super(name, name, value, module, true);
    }
    
    @Override
    public void setValue(final Boolean value) {
        if (value) {
            for (final Option option : OptionManager.getOptionList()) {
                if (option.getModule().equals(this.getModule()) && option instanceof VelocityMode) {
                    ((BooleanOption)option).setValueHard(false);
                }
            }
        }
        else {
            for (final Option option : OptionManager.getOptionList()) {
                if (option.getModule().equals(this.getModule()) && option instanceof VelocityMode && option != this) {
                    ((BooleanOption)option).setValueHard(true);
                    break;
                }
            }
        }
        super.setValue(value);
    }
    
    public boolean enable() {
        return this.getValue();
    }
    
    public boolean onPacketReceiveEvent(final PacketReceiveEvent event) {
        return this.getValue();
    }
    
    public boolean onUpdate(final UpdateEvent event) {
        return this.getValue();
    }
}
