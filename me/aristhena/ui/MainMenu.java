package me.aristhena.ui;

import me.aristhena.client.Client;
import me.aristhena.client.gui.account.AccountScreen;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class MainMenu extends GuiScreen {
	
	public MainMenu() {
		
	}
	
	public void initGui() {
		Client.getInstance().getDiscordRP().update("Idle", "Main menu");
	
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		mc.getTextureManager().bindTexture(new ResourceLocation("Inquisitor/background.jpg"));
		this.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);		
		
		this.drawGradientRect(0, height - 100, width, height, 0x00000000, 0xff000000);
		
		String[] buttons = {"Singleplayer", "Multiplayer", "Settings", "Language", "Alts", "Quit"};
		
		int count = 0;
		for(String name : buttons) {
			float x = (width/buttons.length) * count + (width/buttons.length)/2f + 8 - mc.fontRendererObj.getStringWidth(name)/2f;
			float y = height - 20;
			
			boolean hovered = mouseX >= x && mouseY >= y && mouseX < x + mc.fontRendererObj.getStringWidth(name) && mouseY < y + mc.fontRendererObj.FONT_HEIGHT;
				
			this.drawCenteredString(mc.fontRendererObj, name, (width/buttons.length) * count + (width/buttons.length)/2f + 8, height - 20, hovered ? 0xff0090ff : -1);
			count++;
		}
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(width/5.3f, height/2.2f, 0);
		GlStateManager.scale(3.5, 3.5, 1);
		GlStateManager.translate(-(width/2f), -(height/2f), 0);
		this.drawCenteredString(mc.fontRendererObj, Client.name, width/2f, height/2f - mc.fontRendererObj.FONT_HEIGHT/2f, 0xFF0000);
		GlStateManager.popMatrix();
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(width/1.3f, height/2.2f, 0);
		GlStateManager.scale(3.5, 3.5, 1);
		GlStateManager.translate(-(width/2f), -(height/2f), 0);
		this.drawCenteredString(mc.fontRendererObj, Client.ver, width/2f, height/2f - mc.fontRendererObj.FONT_HEIGHT/2f, 0xFF0000);
		GlStateManager.popMatrix();
	}
	
	public void mouseClicked(int mouseX, int mouseY, int button) {
		
		String[] buttons = {"Singleplayer", "Multiplayer", "Settings", "Language", "Alts", "Quit"};
		
		int count = 0;
		for(String name : buttons) {
			float x = (width/buttons.length) * count + (width/buttons.length)/2f + 8 - mc.fontRendererObj.getStringWidth(name)/2f;
			float y = height - 20;
			
			if(mouseX >= x && mouseY >= y && mouseX < x + mc.fontRendererObj.getStringWidth(name) && mouseY < y + mc.fontRendererObj.FONT_HEIGHT) {
				switch(name) {
				
				case "Singleplayer":
					mc.displayGuiScreen(new GuiSelectWorld(this));
					break;
					
				case "Multiplayer":
					mc.displayGuiScreen(new GuiMultiplayer(this));
					break;
					
				case "Settings":
					mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
					break;
					
				case "Language":
					mc.displayGuiScreen(new GuiLanguage(this, mc.gameSettings, mc.getLanguageManager()));
					break;
					
				case "Alts":
					mc.displayGuiScreen(new AccountScreen());
					break;
					
				case "Quit":
					mc.shutdown();
					break;
				}
			}
				count++;
		}
	}
	
	public void onGuiClosed() {
		
	}

}
