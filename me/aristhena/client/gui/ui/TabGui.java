package me.aristhena.client.gui.ui;

import java.util.ArrayList;
import java.util.List;

import io.netty.util.internal.StringUtil;
import me.aristhena.client.module.Module;
import me.aristhena.client.module.ModuleManager;
import me.aristhena.client.module.Module.Category;
import me.aristhena.client.module.modules.render.Hud;
import me.aristhena.utils.ClientUtils;
import me.aristhena.utils.RenderingUtils;

public class TabGui
{
	   private static final int NO_COLOR = 0;
	    private static final int INSIDE_COLOR = -1610612736;
	    private static final int BORDER_COLOR = 2013265920;
	    private static final int COMPONENT_HEIGHT = 14;
	    private static int baseCategoryWidth;
	    private static int baseCategoryHeight;
	    private static int baseModWidth;
	    private static int baseModHeight;
	    private static Section section;
	    private static int categoryTab;
	    private static int modTab;
	    private static int categoryPosition;
	    private static int categoryTargetPosition;
	    private static int modPosition;
	    private static int modTargetPosition;
	    private static boolean transitionQuickly;
	    private static long lastUpdateTime;
	    
	    static {
	        TabGui.section = Section.CATEGORY;
	        TabGui.categoryTab = 0;
	        TabGui.modTab = 0;
	        TabGui.categoryPosition = 15;
	        TabGui.categoryTargetPosition = 15;
	        TabGui.modPosition = 15;
	        TabGui.modTargetPosition = 15;
	    }
	    
	    public static void init() {
	        int highestWidth = 0;
	        Category[] values;
	        for (int length = (values = Category.values()).length, i = 0; i < length; ++i) {
	            final Category category = values[i];
	            final String name = capitalize(category.name().toLowerCase());
	            final int stringWidth = ClientUtils.clientFont.getStringWidth(name);
	            highestWidth = Math.max(stringWidth, highestWidth);
	        }
	        TabGui.baseCategoryWidth = highestWidth + 6;
	        TabGui.baseCategoryHeight = Category.values().length * 14 + 2;
	    }
	    
	    public static void render() {
	        updateBars();
	        RenderingUtils.drawRect(2.0f, 14.0f, 2 + TabGui.baseCategoryWidth, 14 + TabGui.baseCategoryHeight, -1610612736);
	        RenderingUtils.drawGradientRect(3.0f, TabGui.categoryPosition, 2 + TabGui.baseCategoryWidth - 1, TabGui.categoryPosition + 14, -9764816, -255255255);
	        int yPos = 15;
	        int yPosBottom = 29;
	        for (int i = 0; i < Category.values().length; ++i) {
	            final Category category = Category.values()[i];
	            final String name = capitalize(category.name().toLowerCase());
	            ClientUtils.clientFont.drawStringWithShadow(name, TabGui.baseCategoryWidth / 2 - ClientUtils.clientFont.getStringWidth(name) / 2 + 3, yPos + 3, -1);
	            yPos += 14;
	            yPosBottom += 14;
	        }
	        if (TabGui.section == Section.MODS) {
	            RenderingUtils.drawRect(TabGui.baseCategoryWidth + 4, TabGui.categoryPosition - 1, TabGui.baseCategoryWidth + 2 + TabGui.baseModWidth, TabGui.categoryPosition + getModsInCategory(Category.values()[TabGui.categoryTab]).size() * 14 + 1, -1610612736);
	            RenderingUtils.drawGradientRect(TabGui.baseCategoryWidth + 5, TabGui.modPosition, TabGui.baseCategoryWidth + TabGui.baseModWidth + 1, TabGui.modPosition + 14, -9764816, -255255255);
	            yPos = TabGui.categoryPosition;
	            yPosBottom = TabGui.categoryPosition + 14;
	            for (int i = 0; i < getModsInCategory(Category.values()[TabGui.categoryTab]).size(); ++i) {
	                final Module mod = getModsInCategory(Category.values()[TabGui.categoryTab]).get(i);
	                final String name = mod.getDisplayName();
	                ClientUtils.clientFont.drawStringWithShadow(name, TabGui.baseCategoryWidth + TabGui.baseModWidth / 2 - ClientUtils.clientFont.getStringWidth(name) / 2 + 3, yPos + 3, mod.isEnabled() ? -1 : -4210753);
	                yPos += 14;
	                yPosBottom += 14;
	            }
	        }
	    }
	    
	    public static void keyPress(final int key) {
	        if (TabGui.section == Section.CATEGORY) {
	            switch (key) {
	                case 205: {
	                    int highestWidth = 0;
	                    for (final Module module : getModsInCategory(Category.values()[TabGui.categoryTab])) {
	                        final String name = capitalize(module.getDisplayName().toLowerCase());
	                        final int stringWidth = ClientUtils.clientFont.getStringWidth(name);
	                        highestWidth = Math.max(stringWidth, highestWidth);
	                    }
	                    TabGui.baseModWidth = highestWidth + 6;
	                    TabGui.baseModHeight = getModsInCategory(Category.values()[TabGui.categoryTab]).size() * 14 + 2;
	                    TabGui.modTargetPosition = (TabGui.modPosition = TabGui.categoryTargetPosition);
	                    TabGui.modTab = 0;
	                    TabGui.section = Section.MODS;
	                    break;
	                }
	                case 208: {
	                    ++TabGui.categoryTab;
	                    TabGui.categoryTargetPosition += 14;
	                    if (TabGui.categoryTab > Category.values().length - 1) {
	                        TabGui.transitionQuickly = true;
	                        TabGui.categoryTargetPosition = 15;
	                        TabGui.categoryTab = 0;
	                        break;
	                    }
	                    break;
	                }
	                case 200: {
	                    --TabGui.categoryTab;
	                    TabGui.categoryTargetPosition -= 14;
	                    if (TabGui.categoryTab < 0) {
	                        TabGui.transitionQuickly = true;
	                        TabGui.categoryTargetPosition = 15 + (Category.values().length - 1) * 14;
	                        TabGui.categoryTab = Category.values().length - 1;
	                        break;
	                    }
	                    break;
	                }
	            }
	        }
	        else if (TabGui.section == Section.MODS) {
	            switch (key) {
	                case 203: {
	                    TabGui.section = Section.CATEGORY;
	                    break;
	                }
	                case 205: {
	                    final Module mod = getModsInCategory(Category.values()[TabGui.categoryTab]).get(TabGui.modTab);
	                    mod.toggle();
	                    break;
	                }
	                case 208: {
	                    ++TabGui.modTab;
	                    TabGui.modTargetPosition += 14;
	                    if (TabGui.modTab > getModsInCategory(Category.values()[TabGui.categoryTab]).size() - 1) {
	                        TabGui.transitionQuickly = true;
	                        TabGui.modTargetPosition = TabGui.categoryTargetPosition;
	                        TabGui.modTab = 0;
	                        break;
	                    }
	                    break;
	                }
	                case 200: {
	                    --TabGui.modTab;
	                    TabGui.modTargetPosition -= 14;
	                    if (TabGui.modTab < 0) {
	                        TabGui.transitionQuickly = true;
	                        TabGui.modTargetPosition = TabGui.categoryTargetPosition + (getModsInCategory(Category.values()[TabGui.categoryTab]).size() - 1) * 14;
	                        TabGui.modTab = getModsInCategory(Category.values()[TabGui.categoryTab]).size() - 1;
	                        break;
	                    }
	                    break;
	                }
	            }
	        }
	    }
	    
	    private static void updateBars() {
	        final long timeDifference = System.currentTimeMillis() - TabGui.lastUpdateTime;
	        TabGui.lastUpdateTime = System.currentTimeMillis();
	        int increment = TabGui.transitionQuickly ? 100 : 20;
	        increment = Math.max(1, Math.round(increment * timeDifference / 100L));
	        if (TabGui.categoryPosition < TabGui.categoryTargetPosition) {
	            TabGui.categoryPosition += increment;
	            if (TabGui.categoryPosition >= TabGui.categoryTargetPosition) {
	                TabGui.categoryPosition = TabGui.categoryTargetPosition;
	                TabGui.transitionQuickly = false;
	            }
	        }
	        else if (TabGui.categoryPosition > TabGui.categoryTargetPosition) {
	            TabGui.categoryPosition -= increment;
	            if (TabGui.categoryPosition <= TabGui.categoryTargetPosition) {
	                TabGui.categoryPosition = TabGui.categoryTargetPosition;
	                TabGui.transitionQuickly = false;
	            }
	        }
	        if (TabGui.modPosition < TabGui.modTargetPosition) {
	            TabGui.modPosition += increment;
	            if (TabGui.modPosition >= TabGui.modTargetPosition) {
	                TabGui.modPosition = TabGui.modTargetPosition;
	                TabGui.transitionQuickly = false;
	            }
	        }
	        else if (TabGui.modPosition > TabGui.modTargetPosition) {
	            TabGui.modPosition -= increment;
	            if (TabGui.modPosition <= TabGui.modTargetPosition) {
	                TabGui.modPosition = TabGui.modTargetPosition;
	                TabGui.transitionQuickly = false;
	            }
	        }
	    }
	    
	    private static List<Module> getModsInCategory(final Category category) {
	        final List<Module> modList = new ArrayList<Module>();
	        for (final Module mod : ModuleManager.moduleList) {
	            if (mod.getCategory() == category) {
	                modList.add(mod);
	            }
	        }
	        return modList;
	    }
	    
	    private enum Section
	    {
	        CATEGORY("CATEGORY", 0), 
	        MODS("MODS", 1);
	        
	        private Section(final String s, final int n) {
	        }
	    }
	  public static String capitalize(String line)
	  {
	    return Character.toUpperCase(line.charAt(0)) + line.substring(1);
	  }
}



