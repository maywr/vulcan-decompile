//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.clickgui.buttons;

import me.ka1.vulcan.setting.*;
import me.ka1.vulcan.module.modules.client.*;
import java.awt.*;
import me.ka1.vulcan.clickgui.*;
import net.minecraft.client.gui.*;
import me.ka1.vulcan.module.*;
import com.mojang.realmsclient.gui.*;
import me.ka1.vulcan.util.font.*;
import java.math.*;

public class DoubleComponent extends Component
{
    private boolean hovered;
    private final Setting.Double set;
    private final Buttons parent;
    private int offset;
    private int x;
    private int y;
    private boolean dragging;
    private double renderWidth;
    
    public DoubleComponent(final Setting.Double value, final Buttons button, final int offset) {
        this.dragging = false;
        this.set = value;
        this.parent = button;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        this.offset = offset;
    }
    
    @Override
    public void renderComponent() {
        ClickGUI.color = new Color(ClickGuiModule.red.getValue(), ClickGuiModule.green.getValue(), ClickGuiModule.blue.getValue(), ClickGuiModule.opacity.getValue()).getRGB();
        Gui.drawRect(this.parent.parent.getX(), this.parent.parent.getY() + this.offset + 1, this.parent.parent.getX() + this.parent.parent.getWidth(), this.parent.parent.getY() + this.offset + 16, this.hovered ? new Color(0, 0, 0, ClickGuiModule.opacity.getValue() - 50).darker().darker().getRGB() : new Color(0, 0, 0, ClickGuiModule.opacity.getValue() - 50).getRGB());
        final int drag = (int)(this.set.getValue() / this.set.getMax() * this.parent.parent.getWidth());
        Gui.drawRect(this.parent.parent.getX(), this.parent.parent.getY() + this.offset + 1, this.parent.parent.getX() + (int)this.renderWidth, this.parent.parent.getY() + this.offset + 16, ClickGUI.color);
        Gui.drawRect(this.parent.parent.getX(), this.parent.parent.getY() + this.offset, this.parent.parent.getX() + this.parent.parent.getWidth(), this.parent.parent.getY() + this.offset + 1, new Color(0, 0, 0, ClickGuiModule.opacity.getValue() - 50).getRGB());
        FontUtils.drawStringWithShadow(ModuleManager.isModuleEnabled("CustomFont"), this.set.getName() + " " + ChatFormatting.GRAY + this.set.getValue(), this.parent.parent.getX() + 2, this.parent.parent.getY() + this.offset + 4, -1);
    }
    
    @Override
    public void setOff(final int newOff) {
        this.offset = newOff;
    }
    
    @Override
    public void updateComponent(final int mouseX, final int mouseY) {
        this.hovered = (this.isMouseOnButtonD(mouseX, mouseY) || this.isMouseOnButtonI(mouseX, mouseY));
        this.y = this.parent.parent.getY() + this.offset;
        this.x = this.parent.parent.getX();
        final double diff = Math.min(100, Math.max(0, mouseX - this.x));
        final double min = this.set.getMin();
        final double max = this.set.getMax();
        this.renderWidth = 100.0 * (this.set.getValue() - min) / (max - min);
        if (this.dragging) {
            if (diff == 0.0) {
                this.set.setValue(this.set.getMin());
            }
            else {
                final double newValue = roundToPlace(diff / 100.0 * (max - min) + min, 2);
                this.set.setValue(newValue);
            }
        }
    }
    
    private static double roundToPlace(final double value, final int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.isMouseOnButtonD(mouseX, mouseY) && button == 0 && this.parent.open) {
            this.dragging = true;
        }
        if (this.isMouseOnButtonI(mouseX, mouseY) && button == 0 && this.parent.open) {
            this.dragging = true;
        }
    }
    
    @Override
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
        this.dragging = false;
    }
    
    public boolean isMouseOnButtonD(final int x, final int y) {
        return x > this.x && x < this.x + (this.parent.parent.getWidth() / 2 + 1) && y > this.y && y < this.y + 16;
    }
    
    public boolean isMouseOnButtonI(final int x, final int y) {
        return x > this.x + this.parent.parent.getWidth() / 2 && x < this.x + this.parent.parent.getWidth() && y > this.y && y < this.y + 16;
    }
}
