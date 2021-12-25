// 
// Decompiled by Procyon v0.5.30
// 

package me.aristhena.client.gui.click.component.window;

import java.util.Iterator;

import me.aristhena.client.gui.click.ClickGui;
import me.aristhena.client.gui.click.component.Component;
import me.aristhena.client.gui.click.component.slot.ModuleSlotComponent;
import me.aristhena.client.gui.click.component.slot.SlotComponent;
import me.aristhena.client.module.Module;
import me.aristhena.client.module.ModuleManager;
import me.aristhena.utils.RenderUtils;

public class ModuleWindow extends Window<Module.Category>
{
    private static final float BORDER_WIDTH = 1.5f;
    private static final double PADDING = 4.0;
    private static final double SLOT_COMPONENT_HEIGHT = 16.0;
    
    public ModuleWindow(final Module.Category category, final double x, final double y, double width) {
        super(category, x, y, 0.0, 0.0, new Handle(category.name(), x + 1.5 - 1.5, y - 18.0 + 1.5 + 0.5, width + 16.0 - 3.0 + 3.0, 18.0));
        double height = 1.5;
        for (final Module mod : ModuleManager.getModules()) {
            if (mod.getCategory().equals(category)) {
                final ModuleSlotComponent component = new ModuleSlotComponent(mod, x + 1.5 + 1.5, y + height - 0.5, width + 16.0 - 3.0 - 3.0, 17.0, this);
                this.getSlotList().add(component);
                height += 18.0;
            }
        }
        width += 16.0;
        height += 1.5;
        this.setWidth(width);
        this.setHeight(height);
        getHandle().setParent(this);
    }
    
    @Override
    public void draw(final int mouseX, final int mouseY) {
        this.getHandle().draw(mouseX, mouseY, this.isExtended());
        if (this.isExtended()) {
        	final int[] fillGradient = { -5646555, -5646555, RenderUtils.blend(-489, -221160221, 0.95f), RenderUtils.blend(-489, -221160221, 0.95f) };
            final int[] outlineGradient = { RenderUtils.blend(-5646555, -1610612736, 0.95f), RenderUtils.blend(-5465646, -1610612736, 0.95f), -221160221, -546465456 };
            RenderUtils.rectangleBorderedGradient(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight(), fillGradient, outlineGradient, 0.5);
            for (final SlotComponent slotComponent : this.getSlotList()) {
                slotComponent.draw(mouseX, mouseY);
            }
        }
    }
    
    @Override
    public void click(final int mouseX, final int mouseY, final int button) {
        final Window topWindow = ClickGui.getInstance().getTopWindow(mouseX, mouseY);
        if (topWindow != null && topWindow.equals(this) && this.getHandle().hovering(mouseX, mouseY)) {
            this.getHandle().click(mouseX, mouseY, button);
        }
        for (final SlotComponent slot : this.getSlotList()) {
            slot.click(mouseX, mouseY, button);
        }
    }
    
    @Override
    public void drag(final int mouseX, final int mouseY, final int button) {
        if (this.isDragging()) {
            double xDifference = this.getX() - (mouseX - this.getStartOffset()[0]);
            double yDifference = this.getY() - (mouseY - this.getStartOffset()[1]);
            xDifference = Math.round(xDifference / 10.0) * 10L;
            yDifference = Math.round(yDifference / 10.0) * 10L;
            for (final SlotComponent slot : this.getSlotList()) {
                ((ModuleSlotComponent)slot).drag(xDifference, yDifference, this.getStartOffset());
            }
            this.setX(this.getX() - xDifference);
            this.setY(this.getY() - yDifference);
            this.getHandle().setX(this.getHandle().getX() - xDifference);
            this.getHandle().setY(this.getHandle().getY() - yDifference);
        }
        for (final SlotComponent slot2 : this.getSlotList()) {
            ((ModuleSlotComponent)slot2).drag(mouseX, mouseY, button);
        }
    }
    
    @Override
    public void release(final int mouseX, final int mouseY, final int button) {
        this.getHandle().release(mouseX, mouseY, button);
    }
    
    @Override
    public void keyPress(final int keyInt, final char keyChar) {
        for (final SlotComponent slot : this.getSlotList()) {
            slot.keyPress(keyInt, keyChar);
        }
    }
}
