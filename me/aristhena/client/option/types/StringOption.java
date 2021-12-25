/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.client.option.types;

import java.lang.reflect.Field;
import me.aristhena.client.option.Option;
import me.aristhena.client.module.Module;

public class StringOption
extends Option {
    public StringOption(String id2, String name, String value, Module module) {
        super(id2, name, value, module);
    }

    public void setValue(String value) {
        super.setValue(value);
        for (Field field : this.getModule().getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (!field.isAnnotationPresent(Option.Op.class) || !field.getName().equalsIgnoreCase(this.getId())) continue;
            try {
                if (!field.getType().isAssignableFrom(String.class)) continue;
                field.set(this.getModule(), value);
            }
            catch (Exception var7) {
                var7.printStackTrace();
            }
        }
    }
}

