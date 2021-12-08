//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.Hud;

import me.ka1.vulcan.module.*;
import me.ka1.vulcan.setting.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import net.minecraft.item.*;

public class Inventory extends Module
{
    Setting.Integer r;
    Setting.Integer g;
    Setting.Integer b;
    Setting.Integer x;
    Setting.Integer y;
    Setting.Integer alpha;
    Setting.Double lineGirth;
    Setting.Integer w;
    Setting.Integer h;
    
    public Inventory() {
        super("Inventory", "Renders inventory GUI and items on screen.", Module.Category.Hud);
    }
    
    public void setup() {
        this.r = this.registerInteger("Red", "redInv", 255, 0, 255);
        this.g = this.registerInteger("Green", "greenInv", 255, 0, 255);
        this.b = this.registerInteger("Blue", "blueInv", 255, 0, 255);
        this.x = this.registerInteger("X", "xInv", 0, 0, 1280);
        this.y = this.registerInteger("Y", "yInv", 10, 0, 960);
        this.alpha = this.registerInteger("Alpha", "outlineAlphaInv", 90, 0, 255);
        this.lineGirth = this.registerDouble("Outline Width", "outlineWidthInv", 0.8, 0.0, 1.0);
        this.w = this.registerInteger("w", "w", 50, 0, 1000);
        this.h = this.registerInteger("h", "h", 50, 0, 1000);
    }
    
    public void onRender() {
        if (Inventory.mc.player == null || Inventory.mc.world == null) {
            return;
        }
        GlStateManager.pushMatrix();
        RenderHelper.enableGUIStandardItemLighting();
        GL11.glLineWidth((float)this.lineGirth.getValue());
        Gui.drawRect(this.x.getValue(), this.y.getValue(), this.x.getValue() + this.w.getValue(), this.y.getValue() + this.h.getValue(), new Color(this.r.getValue(), this.g.getValue(), this.b.getValue(), this.alpha.getValue()).getRGB());
        for (int i = 0; i < 27; ++i) {
            final ItemStack item_stack = (ItemStack)Inventory.mc.player.inventory.mainInventory.get(i + 9);
            final int item_position_x = this.x.getValue() + i % 9 * 16;
            final int item_position_y = this.y.getValue() + i / 9 * 16;
            Inventory.mc.getRenderItem().renderItemAndEffectIntoGUI(item_stack, item_position_x, item_position_y);
            Inventory.mc.getRenderItem().renderItemOverlayIntoGUI(Inventory.mc.fontRenderer, item_stack, item_position_x, item_position_y, (String)null);
        }
        Inventory.mc.getRenderItem().zLevel = -5.0f;
        RenderHelper.disableStandardItemLighting();
        GlStateManager.popMatrix();
    }
}
