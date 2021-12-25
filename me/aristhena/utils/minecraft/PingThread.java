package me.aristhena.utils.minecraft;

import net.minecraft.client.gui.*;
import java.net.*;

import me.aristhena.utils.*;

public class PingThread extends Thread
{
    @Override
    public void run() {
        while (true) {
            if (ClientUtils.mc().getCurrentServerData() != null) {
                try {
                    if (!(ClientUtils.mc().currentScreen instanceof GuiMultiplayer)) {
                        NetworkUtils.pinger.ping(ClientUtils.mc().getCurrentServerData());
                    }
                }
                catch (UnknownHostException ex) {}
            }
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
