package me.aristhena.client.module.modules.render;

import me.aristhena.client.module.*;
import me.aristhena.client.option.*;

@Module.Mod(displayName = "Wings")
public class WingsColor extends Module
{
    @Option.Op(min = 0.0, max = 1.0, increment = 1.0)
    public static float red;
    @Option.Op(min = 0.0, max = 1.0, increment = 1.0)
    public static float green;
    @Option.Op(min = 0.0, max = 1.0, increment = 1.0)
    public static float blue;
    @Option.Op(min = 0.0, max = 1.0, increment = 1.0)
    public static float alpha;
    @Option.Op(min = 0.0, max = 1.0, increment = 0.01)
    public static float scale1;
    @Option.Op(min = 0.0, max = 1.0, increment = 0.01)
    public static float scale2;
    @Option.Op(min = 0.0, max = 1.0, increment = 0.01)
    public static float scale3;
}
