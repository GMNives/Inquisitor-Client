// 
// Decompiled by Procyon v0.5.30
// 

package me.aristhena.client.module.modules.render;

import me.aristhena.client.module.Module;
import me.aristhena.client.module.Module.Mod;
import me.aristhena.client.module.modules.render.hud.CrestTheme;
import me.aristhena.client.module.modules.render.hud.IndigoTheme;
import me.aristhena.client.module.modules.render.hud.Nautilus;
import me.aristhena.client.module.modules.render.hud.NewVirtueTheme;
import me.aristhena.client.module.modules.render.hud.PlainTheme;
import me.aristhena.client.module.modules.render.hud.Trendy;
import me.aristhena.client.module.modules.render.hud.TrendyTheme;
import me.aristhena.client.module.modules.render.hud.tabgui.LucidTabGui;
import me.aristhena.client.option.OptionManager;
import me.aristhena.event.EventTarget;
import me.aristhena.event.events.KeyPressEvent;
import me.aristhena.event.events.Render2DEvent;
import me.aristhena.event.events.TickEvent;
@Mod(enabled = true, shown = false)
public class Hud extends Module
{
    private PlainTheme plainTheme;
    private NewVirtueTheme newVirtueTheme;
    private CrestTheme crestTheme;
    private TrendyTheme trendyTheme;
    private Nautilus nautilus;
    private IndigoTheme indigoTheme;
    public LucidTabGui lucidTab;
    private Trendy trendy;
    private boolean tabgui;
    
    public Hud() {
    	this.plainTheme = new PlainTheme("Yongling Theme", true, this);
        this.newVirtueTheme = new NewVirtueTheme("Padavan Theme", false, this);
        this.crestTheme = new CrestTheme("Knight Theme", false, this);
        this.trendyTheme = new TrendyTheme("Master Theme", false, this);
        this.indigoTheme = new IndigoTheme("GrandMaster Theme", false, this);
        this.nautilus = new Nautilus("Sith Theme", false, this);
        this.trendy = new Trendy("Developer Theme", false, this);
        this.lucidTab = new LucidTabGui("Solaris TabGui", false, this);
        this.tabgui = true;
    }
    
    @Override
    public void preInitialize() {
        if (this.tabgui) {
            this.lucidTab.setupSizes();
        }
        OptionManager.getOptionList().add(this.plainTheme);
        OptionManager.getOptionList().add(this.newVirtueTheme);
        OptionManager.getOptionList().add(this.crestTheme);
        OptionManager.getOptionList().add(this.trendyTheme);
        OptionManager.getOptionList().add(this.indigoTheme);
        OptionManager.getOptionList().add(this.nautilus);
        OptionManager.getOptionList().add(this.trendy);
        OptionManager.getOptionList().add(this.lucidTab);
        super.preInitialize();
    }
    
    @EventTarget
    private void onRender2D(final Render2DEvent event) {
        this.plainTheme.onRender(event);
        this.newVirtueTheme.onRender(event);
        this.crestTheme.onRender(event);
        this.trendyTheme.onRender(event);
        this.indigoTheme.onRender(event);
        this.nautilus.onRender(event);
        this.trendy.onRender(event);
        if (this.tabgui) {
            this.lucidTab.onRender(event);
        }
    }
    
    @EventTarget
    private void onTick(final TickEvent event) {
        IndigoTheme.updateFade();
    }
    
    @EventTarget
    private void onKeypress(final KeyPressEvent event) {
        if (this.tabgui) {
            this.lucidTab.onKeypress(event);
        }
    }
}
