/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.client.option;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import me.aristhena.client.module.Module;

public class Option<T> {
    private String id;
    private String name;
    private T value;
    private Module module;
    private List<Option> optionList = new ArrayList<Option>();

    public Option(String id, String name, T value, Module module) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.module = module;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    public String getId() {
        return this.id;
    }

    public String getDisplayName() {
        return this.name;
    }

    public Module getModule() {
        return this.module;
    }

    public List<Option> getOptions() {
        return this.optionList;
    }

    @Target(value={ElementType.FIELD})
    @Retention(value=RetentionPolicy.RUNTIME)
    public static @interface Op {
        public String name() default "null";

        public double min() default 1.0;

        public double max() default 10.0;

        public double increment() default 1.0;
    }
}

