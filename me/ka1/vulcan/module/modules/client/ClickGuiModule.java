//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.client;

import me.ka1.vulcan.module.*;
import me.ka1.vulcan.setting.*;
import java.awt.*;
import me.ka1.vulcan.*;
import net.minecraft.client.gui.*;

public class ClickGuiModule extends Module
{
    public static int color;
    public static Setting.Integer opacity;
    public static Setting.Integer red;
    public static Setting.Integer green;
    public static Setting.Integer blue;
    public static Setting.Integer fontRed;
    public static Setting.Integer fontGreen;
    public static Setting.Integer fontBlue;
    
    public ClickGuiModule() {
        super("ClickGUI", Module.Category.Client);
        this.setDrawn(false);
        this.setBind(25);
    }
    
    public void setup() {
        ClickGuiModule.red = this.registerInteger("Red", "RedHUD", 255, 0, 255);
        ClickGuiModule.green = this.registerInteger("Green", "GreenHUD", 255, 0, 255);
        ClickGuiModule.blue = this.registerInteger("Blue", "BlueHUD", 255, 0, 255);
        ClickGuiModule.fontRed = this.registerInteger("Font Red", "RedFONT", 255, 0, 255);
        ClickGuiModule.fontGreen = this.registerInteger("Font Green", "GreenFONT", 255, 0, 255);
        ClickGuiModule.fontBlue = this.registerInteger("Font Blue", "BlueFONT", 255, 0, 255);
        ClickGuiModule.opacity = this.registerInteger("Opacity", "Opacity", 200, 50, 255);
        ClickGuiModule.color = new Color(ClickGuiModule.fontRed.getValue(), ClickGuiModule.fontBlue.getValue(), ClickGuiModule.fontGreen.getValue(), ClickGuiModule.opacity.getValue()).getRGB();
    }
    
    public void onEnable() {
        ClickGuiModule.mc.displayGuiScreen((GuiScreen)Vulcan.getInstance().clickGUI);
        this.disable();
    }
}
