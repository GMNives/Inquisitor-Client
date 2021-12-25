/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.utils;

import java.util.List;

public abstract class ListManager<T> {
    protected List<T> contents;

    public final List<T> getContentList() {
        return this.contents;
    }

    public abstract void setup();
}

