/*
 * Decompiled with CFR 0.150.
 */
package me.aristhena.client.module.modules.player;

import me.aristhena.client.module.Module;
import me.aristhena.client.option.Option;

@Module.Mod(displayName="Speed Mine")
public class SpeedMine
extends Module {
    @Option.Op(min=1.0, max=10.0, increment=1.0)
    public static double amplifier = 1.0;
}

