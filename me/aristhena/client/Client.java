// 
// Decompiled by Procyon v0.5.30
// 

package me.aristhena.client;

import javax.swing.JOptionPane;

import org.lwjgl.opengl.Display;

import me.aristhena.client.command.CommandManager;
import me.aristhena.client.friend.FriendManager;
import me.aristhena.client.gui.account.AccountScreen;
import me.aristhena.client.gui.click.ClickGui;
import me.aristhena.client.module.ModuleManager;
import me.aristhena.client.option.OptionManager;
import me.aristhena.ui.DiscordRP;
import me.aristhena.ui.loading.SplashProgress;
import me.aristhena.utils.ClientUtils;
import me.aristhena.utils.MCStencil;


public final class Client
{
    public static String clientName;
    public static final double VERSION = 20;
    public static AccountScreen accountScreen;
    
    public static String name = "Inquisitor", ver = "20", creator = "Created by vk.com/inquisitor_private";
	public static boolean authorized;

    static {
        Client.clientName = "§4§lInquisitor";
    }
    
    public static void start() {
    	SplashProgress.setProgress(10, "Client - Welcome!");
    	JOptionPane.showMessageDialog(null, "Free SRC Inquisitor, слил автор чита", "Inquisitor Client | Старая версия чита v20 (новая v30)", 1);
		Display.setTitle(Client.name + " v" + Client.ver + " " + Client.creator);
        ClientUtils.loadClientFont();
        ModuleManager.start();
        CommandManager.start();
        OptionManager.start();
        FriendManager.start();
        ClickGui.start();
        MCStencil.checkSetupFBO();
    }
    
    //Discord
    private static final Client INSTANCE = new Client();
	public static final Client getInstance(){
		return INSTANCE;
	}
	
	private DiscordRP discordRP = new DiscordRP();
	
	public void init(){
		SplashProgress.setProgress(1, "Client - Initalising Discord RP!");
		discordRP.start();
	}
	
	public void shutdown(){
		discordRP.shutdown();
	}
	
	public DiscordRP getDiscordRP(){
		return discordRP;
	}
    
    
    
}