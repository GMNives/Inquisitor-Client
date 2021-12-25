// 
// Decompiled by Procyon v0.5.30
// 

package me.aristhena.client.command.commands;

import java.net.URLConnection;
import net.minecraft.util.EnumChatFormatting;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;
import java.util.Iterator;

import net.minecraft.network.play.server.S38PacketPlayerListItem;

import java.util.ArrayList;
import com.mojang.authlib.GameProfile;

import me.aristhena.client.command.Com;
import me.aristhena.client.command.Command;
import me.aristhena.event.EventManager;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.PacketReceiveEvent;
import me.aristhena.event.events.TickEvent;
import me.aristhena.utils.ChatMessage;
import me.aristhena.utils.ClientUtils;

import java.util.List;

@Com(names = {"vanish"})
public class Vanish extends Command
{
    private List<GameProfile> vanishedPlayers;
    
    public Vanish() {
        this.vanishedPlayers = new ArrayList<GameProfile>();
        EventManager.register(this);
    }
    
    @EventTarget
    public void onReceivePacket(final PacketReceiveEvent event) {
        if (event.getPacket() instanceof S38PacketPlayerListItem) {
            final S38PacketPlayerListItem packet = (S38PacketPlayerListItem)event.getPacket();
            if (packet.func_179768_b() == S38PacketPlayerListItem.Action.UPDATE_LATENCY) {
                for (final Object o : packet.func_179767_a()) {
                	S38PacketPlayerListItem.AddPlayerData data = (S38PacketPlayerListItem.AddPlayerData)o;
                    if (ClientUtils.mc().getNetHandler().func_175102_a(data.field_179964_d.getId()) == null && !this.vanishedPlayers.contains(data.field_179964_d)) {
                        this.vanishedPlayers.add(data.field_179964_d);
                    }
                }
            }
        }
    }
    
    @EventTarget
    public void onTick(final TickEvent event) {
        if (ClientUtils.player() != null) {
            for (final GameProfile profile : this.vanishedPlayers) {
                if (ClientUtils.mc().getNetHandler().func_175102_a(profile.getId()) != null) {
                    this.vanishedPlayers.remove(profile);
                }
            }
        }
        else {
            this.vanishedPlayers.clear();
        }
    }
    
    @Override
    public void runCommand(final String[] args) {
        if (this.vanishedPlayers.isEmpty()) {
            ClientUtils.sendMessage("��� ����������� �������");
        }
        else {
            ClientUtils.sendMessage("����������� ������:");
            for (final GameProfile profile : this.vanishedPlayers) {
                if (profile != null) {
                    this.printName(profile.getId());
                }
            }
        }
    }
    
    public void printName(final UUID uuid) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final URL url = new URL("https://namemc.com/profile/" + uuid.toString());
                    final URLConnection connection = url.openConnection();
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.7; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
                    final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String name = null;
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("<title>")) {
                            name = line.split("�")[0].trim().replaceAll("<title>", "").replaceAll("</title>", "").replaceAll("\u2013 Minecraft Profile \u2013 NameMC", "").replaceAll("\u00e2\u20ac\u201c Minecraft Profile \u00e2\u20ac\u201c NameMC", "");
                        }
                    }
                    reader.close();
                    new ChatMessage.ChatMessageBuilder(false, false).appendText(name).setColor(EnumChatFormatting.GRAY).setColor(EnumChatFormatting.ITALIC).build().displayClientSided();
                }
                catch (Exception e) {
                    e.printStackTrace();
                    new ChatMessage.ChatMessageBuilder(false, false).appendText(uuid.toString()).setColor(EnumChatFormatting.GRAY).setColor(EnumChatFormatting.ITALIC).build().displayClientSided();
                }
            }
        }).start();
    }
    
    @Override
    public String getHelp() {
        return ".vanish - ���������� ������ ����������� �������";
    }
}
