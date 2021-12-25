package me.aristhena.utils.minecraft;

import net.minecraft.client.network.*;
import me.aristhena.event.*;
import me.aristhena.event.events.*;
import me.aristhena.utils.*;
import net.minecraft.client.*;
import net.minecraft.client.entity.*;
import net.minecraft.entity.player.*;

public class NetworkUtils
{
    static OldServerPinger pinger;
    private Minecraft mc;
    private Timer timer;
    private static long ping;
    private long lastTime;
    private int prevDebugFPS;
    public long updatedPing;
    
    static {
        NetworkUtils.pinger = new OldServerPinger();
    }
    
    public NetworkUtils() {
        this.mc = Minecraft.getMinecraft();
        this.timer = new Timer();
        EventManager.register(this);
        final PingThread pingThread = new PingThread();
        pingThread.start();
    }
    
    public static long getPing() {
        return NetworkUtils.ping;
    }
    
    public static int getPlayerPing(final String name) {
        final EntityPlayer player = ClientUtils.mc().theWorld.getPlayerEntityByName(name);
        if (player instanceof EntityOtherPlayerMP) {
            return ((EntityOtherPlayerMP)player).field_175157_a.getResponseTime();
        }
        return 0;
    }
    
    @EventTarget
    private void on2DRender(final Render2DEvent event) {
        if (Minecraft.debugFPS != this.prevDebugFPS) {
            this.prevDebugFPS = Minecraft.debugFPS;
            NetworkUtils.ping = this.updatedPing;
        }
    }
}
