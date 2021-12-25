// 
// Decompiled by Procyon v0.5.30
// 

package me.aristhena.client.module.modules.render.hud;

import java.util.Iterator;

import me.aristhena.client.Client;
import me.aristhena.client.module.Module;
import me.aristhena.client.module.ModuleManager;
import me.aristhena.event.events.Render2DEvent;
import me.aristhena.utils.ClientUtils;

import java.util.Date;
import java.text.SimpleDateFormat;

public class CrestTheme extends Theme
{
    public CrestTheme(final String name, final boolean value, final Module module) {
        super(name, value, module);
    }
    
    @Override
    public boolean onRender(final Render2DEvent event) {
        if (super.onRender(event)) {
            String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
            if (time.startsWith("0")) {
                time = time.replaceFirst("0", "");
            }
            ClientUtils.clientFont().drawStringWithShadow(String.format("�4�lI�4nquisitor �r(%s - %s)", time, Client.ver), 2.0, 2.0, 13356753);
            int y = 2;
            for (final Module mod : ModuleManager.getModulesForRender()) {
                if (mod.drawDisplayName(event.getWidth() - ClientUtils.clientFont().getStringWidth(String.format("%s" + ((mod.getSuffix().length() > 0) ? " �0[%s]" : ""), mod.getDisplayName(), mod.getSuffix())) - 2, y, 13356753)) {
                    y += 10;
                }
            }
        }
        return super.onRender(event);
    }
}
