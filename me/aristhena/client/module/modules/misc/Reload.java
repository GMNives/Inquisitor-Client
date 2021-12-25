// 
// Decompiled by Procyon v0.5.30
// 

package me.aristhena.client.module.modules.misc;

import me.aristhena.client.friend.FriendManager;
import me.aristhena.client.gui.click.ClickGui;
import me.aristhena.client.module.Module;
import me.aristhena.client.module.ModuleManager;
import me.aristhena.client.module.Module.Mod;
import me.aristhena.client.option.OptionManager;
import me.aristhena.utils.ClientUtils;
@Mod(displayName = "Reload Config")
public class Reload extends Module
{
    @Override
    public void enable() {
        ClientUtils.mc().currentScreen = null;
        ModuleManager.load();
        OptionManager.load();
        FriendManager.load();
        ClickGui.getInstance().load();
    }
}
