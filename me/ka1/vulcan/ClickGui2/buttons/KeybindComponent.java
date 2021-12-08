//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.ClickGui2.buttons;

import com.mojang.realmsclient.gui.*;
import org.lwjgl.input.*;
import me.ka1.vulcan.ClickGui2.frame.*;
import me.ka1.vulcan.util.font.*;
import me.ka1.vulcan.module.*;
import me.ka1.vulcan.*;

public class KeybindComponent extends Component
{
    private boolean hovered;
    private boolean binding;
    private final Buttons parent;
    private int offset;
    private int x;
    private int y;
    String name;
    int nameWidth;
    int centeredNameCoords;
    
    public KeybindComponent(final Buttons button, final int offset) {
        this.parent = button;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        this.offset = offset;
        this.name = null;
        this.nameWidth = 0;
        this.centeredNameCoords = 0;
    }
    
    @Override
    public void setOff(final int newOff) {
        this.offset = newOff;
    }
    
    @Override
    public void renderComponent() {
        if (this.binding) {
            this.name = "Key...";
        }
        else {
            this.name = "Key: " + ChatFormatting.GRAY + Keyboard.getKeyName(this.parent.mod.getBind());
        }
        Renderer.drawRectStatic(this.parent.parent.getX(), this.parent.parent.getY() + this.offset, this.parent.parent.getX() + this.parent.parent.getWidth(), this.parent.parent.getY() + this.offset + 12, Renderer.getTransColor(false));
        this.nameWidth = FontUtils.getStringWidth(false, this.name);
        this.centeredNameCoords = (this.parent.parent.getWidth() - this.nameWidth) / 2;
        if (ModuleManager.isModuleEnabled("CUstomFont")) {
            Vulcan.fontRenderer.drawStringWithShadow(this.binding ? "Key..." : ("Key: " + ChatFormatting.GRAY + Keyboard.getKeyName(this.parent.mod.getBind())), this.parent.parent.getX() + this.centeredNameCoords, this.parent.parent.getY() + this.offset + 2, Renderer.getFontColor(true, 0).getRGB());
        }
        else {
            FontUtils.drawStringWithShadow(false, this.binding ? "Key..." : ("Key: " + ChatFormatting.GRAY + Keyboard.getKeyName(this.parent.mod.getBind())), this.parent.parent.getX() + this.centeredNameCoords, this.parent.parent.getY() + this.offset + 2, Renderer.getFontColor(true, 0).getRGB());
        }
    }
    
    @Override
    public void updateComponent(final int mouseX, final int mouseY) {
        this.hovered = this.isMouseOnButton(mouseX, mouseY);
        this.y = this.parent.parent.getY() + this.offset;
        this.x = this.parent.parent.getX();
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.isMouseOnButton(mouseX, mouseY) && button == 0 && this.parent.open) {
            this.binding = !this.binding;
        }
    }
    
    @Override
    public void keyTyped(final char typedChar, final int key) {
        if (this.binding) {
            if (key == 211) {
                this.parent.mod.setBind(0);
            }
            else if (key == 1) {
                this.binding = false;
            }
            else {
                this.parent.mod.setBind(key);
            }
            this.binding = false;
        }
    }
    
    public boolean isMouseOnButton(final int x, final int y) {
        return x > this.x && x < this.x + 100 && y > this.y && y < this.y + 12;
    }
}
