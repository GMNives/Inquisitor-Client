package me.aristhena.client.module.modules.misc;

import me.aristhena.utils.*;
import me.aristhena.event.*;
import me.aristhena.event.events.*;
import net.minecraft.network.play.server.*;
import me.aristhena.client.friend.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.*;
import net.minecraft.network.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import me.aristhena.client.enemies.*;
import me.aristhena.client.module.*;
import java.util.*;

@Module.Mod(displayName = "InquisitorBOT")
public class CMDBot extends Module
{
    private boolean following;
    private String followName;
    
    @EventTarget(1)
    public void preTick(final UpdateEvent e) {
        final Event.State state = e.getState();
        e.getState();
        if (state == Event.State.PRE) {
            if (ClientUtils.player().isDead) {
                ClientUtils.player().respawnPlayer();
            }
            if (this.following) {
                try {
                    final PathFind pf = new PathFind(this.followName);
                    e.setPitch(PathFind.fakePitch - 30.0f);
                    e.setYaw(PathFind.fakeYaw);
                }
                catch (Exception ex) {}
            }
        }
    }
    
    @EventTarget
    public void onReceivePacket(final PacketReceiveEvent e) {
        if (e.getPacket() instanceof S02PacketChat) {
            final S02PacketChat message = (S02PacketChat)e.getPacket();
            if (message.func_148915_c().getFormattedText().contains("-следуй")) {
                for (final Friend friend : FriendManager.friendsList) {
                    if (message.func_148915_c().getFormattedText().contains(friend.name)) {
                        String s = message.func_148915_c().getFormattedText();
                        s = s.substring(s.indexOf("-следуй ") + 8);
                        s = s.substring(0, s.indexOf("§"));
                        this.following = true;
                        this.followName = s;
                        ClientUtils.player().sendChatMessage("Следую за инквизитором " + s);
                        break;
                    }
                }
            }
            if (message.func_148915_c().getFormattedText().contains("-стой")) {
                for (final Friend friend : FriendManager.friendsList) {
                    if (message.func_148915_c().getFormattedText().contains(friend.name)) {
                        String s = message.func_148915_c().getFormattedText();
                        s = s.substring(s.indexOf("-стой ") + 12);
                        s = s.substring(0, s.indexOf("§"));
                        this.following = false;
                        this.followName = "";
                        ClientUtils.player().sendChatMessage("Инквизитор приказал стоять ");
                        break;
                    }
                }
            }
            if (message.func_148915_c().getFormattedText().contains("-приказ 66")) {
                for (final Friend friend : FriendManager.friendsList) {
                    if (message.func_148915_c().getFormattedText().contains(friend.name)) {
                    	ClientUtils.player().sendChatMessage("Да владыка. Выполнить приказ 66! ");
                        ClientUtils.player().sendChatMessage("/suicide");
                        break;
                    }
                }
            }
            if (message.func_148915_c().getFormattedText().contains("-хозяин")) {
                for (final Friend friend : FriendManager.friendsList) {
                    if (message.func_148915_c().getFormattedText().contains(friend.name)) {
                        ClientUtils.player().sendChatMessage("Мой хозяин " + friend.name + " служу вам верой и правдой <3");
                        break;
                    }
                }
            }
            if (message.func_148915_c().getFormattedText().contains("-тп")) {
                for (final Friend friend : FriendManager.friendsList) {
                    if (message.func_148915_c().getFormattedText().contains(friend.name)) {
                        ClientUtils.player().sendChatMessage("/tpa " + friend.name);
                        break;
                    }
                }
            }
            if (message.func_148915_c().getFormattedText().contains("-pvp")) {
                for (final Friend friend : FriendManager.friendsList) {
                    if (message.func_148915_c().getFormattedText().contains(friend.name)) {
                        ClientUtils.player().sendChatMessage("/pvp");
                        break;
                    }
                }
            }
            if (message.func_148915_c().getFormattedText().contains("-newkit")) {
                for (final Friend friend : FriendManager.friendsList) {
                    if (message.func_148915_c().getFormattedText().contains(friend.name)) {
                        ClientUtils.player().sendChatMessage("Grabbing a new kit");
                        ClientUtils.player().sendChatMessage("/newkit");
                        break;
                    }
                }
            }
            if (message.func_148915_c().getFormattedText().contains("-код 23")) {
                for (final Friend friend : FriendManager.friendsList) {
                    if (message.func_148915_c().getFormattedText().contains(friend.name)) {
                        ClientUtils.player().sendChatMessage("Перезагрузка Inquisitor's Clone.");
                        ClientUtils.packet((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
                        break;
                    }
                }
            }
            if (message.func_148915_c().getFormattedText().contains("-враг ")) {
                for (final Entity mod : ClientUtils.loadedEntityList()) {
                    if (mod instanceof EntityPlayer && message.func_148915_c().getFormattedText().contains("-враг " + mod.getName())) {
                        for (final Friend friend2 : FriendManager.friendsList) {
                            if (message.func_148915_c().getFormattedText().contains(friend2.name)) {
                                if (EnemyManager.isEnemy(mod.getName())) {
                                    ClientUtils.player().sendChatMessage(String.valueOf(String.valueOf(mod.getName())) + " уже помечен как враг.");
                                    break;
                                }
                                if (!EnemyManager.isEnemy(mod.getName())) {
                                    ClientUtils.player().sendChatMessage(String.valueOf(String.valueOf(mod.getName())) + " помечен как враг.");
                                    EnemyManager.addEnemy(mod.getName(), mod.getName());
                                    break;
                                }
                                break;
                            }
                        }
                    }
                }
            }
            if (message.func_148915_c().getFormattedText().contains("-исключить врага ")) {
                for (final Entity mod : ClientUtils.loadedEntityList()) {
                    if (mod instanceof EntityPlayer && message.func_148915_c().getFormattedText().contains("-исключить врага " + mod.getName())) {
                        for (final Friend friend2 : FriendManager.friendsList) {
                            if (message.func_148915_c().getFormattedText().contains(friend2.name)) {
                                if (EnemyManager.isEnemy(mod.getName())) {
                                    EnemyManager.removeEnemy(mod.getName());
                                    ClientUtils.player().sendChatMessage(String.valueOf(String.valueOf(mod.getName())) + " был исключён из списка врагов.");
                                    break;
                                }
                                if (!EnemyManager.isEnemy(mod.getName())) {
                                    ClientUtils.player().sendChatMessage(String.valueOf(String.valueOf(mod.getName())) + " не враг вам.");
                                    break;
                                }
                                break;
                            }
                        }
                    }
                }
            }
            if (message.func_148915_c().getFormattedText().contains("-друг ")) {
                for (final Entity mod : ClientUtils.loadedEntityList()) {
                    if (mod instanceof EntityPlayer && message.func_148915_c().getFormattedText().contains("-друг " + mod.getName())) {
                        for (final Friend friend2 : FriendManager.friendsList) {
                            if (message.func_148915_c().getFormattedText().contains(friend2.name)) {
                                if (FriendManager.isFriend(mod.getName())) {
                                    ClientUtils.player().sendChatMessage(String.valueOf(String.valueOf(mod.getName())) + " уже ваш друг.");
                                    break;
                                }
                                if (!FriendManager.isFriend(mod.getName())) {
                                    ClientUtils.player().sendChatMessage(String.valueOf(String.valueOf(mod.getName())) + " добавлен в друзья.");
                                    FriendManager.addFriend(mod.getName(), mod.getName());
                                    break;
                                }
                                break;
                            }
                        }
                    }
                }
            }
            if (message.func_148915_c().getFormattedText().contains("-исключить друга ")) {
                for (final Entity mod : ClientUtils.loadedEntityList()) {
                    if (mod instanceof EntityPlayer && message.func_148915_c().getFormattedText().contains("-исключить друга " + mod.getName())) {
                        for (final Friend friend2 : FriendManager.friendsList) {
                            if (message.func_148915_c().getFormattedText().contains(friend2.name)) {
                                if (FriendManager.isFriend(mod.getName())) {
                                    FriendManager.removeFriend(mod.getName());
                                    ClientUtils.player().sendChatMessage(String.valueOf(String.valueOf(mod.getName())) + " исключён из списка друзей.");
                                    break;
                                }
                                if (!FriendManager.isFriend(mod.getName())) {
                                    ClientUtils.player().sendChatMessage(String.valueOf(String.valueOf(mod.getName())) + " и так не друг вам.");
                                    break;
                                }
                                break;
                            }
                        }
                    }
                }
            }
            if (message.func_148915_c().getFormattedText().contains("-toggle ")) {
                for (final Module mod2 : ModuleManager.getModules()) {
                    if (message.func_148915_c().getFormattedText().contains("-toggle " + mod2.getDisplayName())) {
                        for (final Friend friend2 : FriendManager.friendsList) {
                            if (message.func_148915_c().getFormattedText().contains(friend2.name)) {
                                mod2.toggle();
                                final boolean state = mod2.isEnabled();
                                final String s2 = state ? "On" : "Off";
                                ClientUtils.player().sendChatMessage(String.valueOf(String.valueOf(mod2.getDisplayName())) + " is now " + s2);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
