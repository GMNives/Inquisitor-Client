package me.aristhena.client.module;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import me.aristhena.client.Client;
import me.aristhena.client.option.Option;
import me.aristhena.client.option.OptionManager;
import me.aristhena.event.EventManager;
import me.aristhena.utils.ClientUtils;

public class Module
{
    private String id;
    private String displayName;
    private boolean enabled;
    private Category category;
    private int keybind;
    private String suffix;
    private boolean shown;
    
    public void setProperties(final String id, final String displayName, final Category type, final int keybind, final String suffix, final boolean shown) {
        this.id = id;
        this.displayName = displayName;
        this.category = type;
        this.keybind = keybind;
        this.suffix = suffix;
        this.shown = shown;
    }
    
    public void preInitialize() {
    }
    
    public void toggle() {
        if (this.enabled) {
            this.disable();
        }
        else {
            this.enable();
        }
    }
    
    public void enable() {
        this.enabled = true;
        EventManager.register(this);
    }
    
    public void disable() {
        this.enabled = false;
        EventManager.unregister(this);
    }
    
    public List<Option> getOptions() {
        final List<Option> optionList = new ArrayList<Option>();
        for (final Option option : OptionManager.getOptionList()) {
            if (option.getModule().equals(this)) {
                optionList.add(option);
            }
        }
        return optionList;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getDisplayName() {
        return this.displayName;
    }
    
    public boolean drawDisplayName(final float x, final float y, final int color) {
        if (this.enabled && this.shown) {
            ClientUtils.clientFont().drawStringWithShadow(String.format("%s" + ((this.suffix.length() > 0) ? " §7[%s]" : ""), this.displayName, this.suffix), x, y, color);
            return true;
        }
        return false;
    }
    
    public boolean drawDisplayName(final float x, final float y) {
        if (this.enabled && this.shown) {
            ClientUtils.clientFont().drawStringWithShadow(String.format("%s" + ((this.suffix.length() > 0) ? " §7[%s]" : ""), this.displayName, this.suffix), x, y, getColor());
            return true;
        }
        return false;
    }
    
    public int getColor() {
        switch (this.category) {
            case Combat: {
                return -4042164;
            }
            case Render: {
                return -1781619;
            }
            case Movement: {
                return -4927508;
            }
            case Misc: {
                return -8921737;
            }
            case Player: {
                return -8921737;
            }
            case Test: {
                return -13911383;
            }
            case Auto: {
                return -13911383;
            }
            default: {
                return -1;
            }
        }
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public int getKeybind() {
        return this.keybind;
    }
    
    public void setKeybind(final int keybind) {
        this.keybind = keybind;
    }
    
    public String getSuffix() {
        return this.suffix;
    }
    
    public void setSuffix(final String suffix) {
        this.suffix = suffix;
    }
    
    public boolean isShown() {
        return this.shown;
    }
    
    public void setShown(final boolean shown) {
        this.shown = shown;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }
    
    public Module getInstance() {
        for (final Module mod : ModuleManager.getModules()) {
            if (mod.getClass().equals(this.getClass())) {
                return mod;
            }
        }
        return null;
    }
    
    public enum Category
    {
        Combat("Combat", 1), 
        Render("Render", 2), 
        Movement("Movement", 3), 
        Player("Player", 4), 
        Misc("Misc", 5),
        Test("Test", 6),
        Auto("Auto", 7); 
        
        private Category(final String s, final int n) {
        }
    }
    
    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Mod {
        String displayName() default "null";
        
        boolean enabled() default false;
        
        int keybind() default -1;
        
        boolean shown() default true;
        
        String suffix() default "";
    }

    public void postInitialize() {
    }

    public static Module getModByName(String name) {
        for (Module mod : ModuleManager.getModules()) {
            if (!mod.getDisplayName().trim().equalsIgnoreCase(name.trim()) && !mod.toString().trim().equalsIgnoreCase(name.trim())) continue;
            return mod;
        }
        return null;
    }
}
