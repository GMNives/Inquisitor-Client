/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.client.module.modules.combat.modes.autoheal;

import me.aristhena.client.module.Module;
import me.aristhena.client.option.Option;
import me.aristhena.client.option.OptionManager;
import me.aristhena.client.option.types.BooleanOption;
import me.aristhena.event.events.UpdateEvent;

public class AutoHealMode
extends BooleanOption {
    public AutoHealMode(String name, boolean value, Module module) {
        super(name, name, value, module, value);
    }

    @Override
    public void setValue(Boolean value) {
        if (value.booleanValue()) {
            for (Option option : OptionManager.getOptionList()) {
                if (!option.getModule().equals(this.getModule()) || !(option instanceof AutoHealMode)) continue;
                ((BooleanOption)option).setValueHard(false);
            }
        } else {
            for (Option option : OptionManager.getOptionList()) {
                if (!option.getModule().equals(this.getModule()) || !(option instanceof AutoHealMode) || option == this) continue;
                ((BooleanOption)option).setValueHard(true);
                break;
            }
        }
        super.setValue(value);
    }

    public boolean enable() {
        return (Boolean)this.getValue();
    }

    public boolean onUpdate(UpdateEvent event) {
        return (Boolean)this.getValue();
    }

    public boolean disable() {
        return (Boolean)this.getValue();
    }
}

