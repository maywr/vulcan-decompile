//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.Hud;

import me.ka1.vulcan.module.*;
import me.ka1.vulcan.setting.*;
import java.util.*;
import me.ka1.vulcan.*;
import net.minecraft.client.*;
import java.awt.*;

public class Watermark extends Module
{
    Setting.Integer r;
    Setting.Integer g;
    Setting.Integer b;
    Setting.Integer x;
    Setting.Integer y;
    Setting.Mode mode;
    Setting.Boolean onion;
    
    public Watermark() {
        super("Watermark", "displays the modname and ver (optional)", Module.Category.Hud);
    }
    
    public void setup() {
        final ArrayList<String> Modes = new ArrayList<String>();
        Modes.add("Vulcan Client");
        Modes.add("Vulcan Client 0.5");
        Modes.add("OnionWare");
        this.mode = this.registerMode("Mode", "watermarkMode", (List)Modes, "Vulcan Client");
        this.r = this.registerInteger("Red", "red", 255, 0, 255);
        this.g = this.registerInteger("Green", "green", 255, 0, 255);
        this.b = this.registerInteger("Blue", "blue", 255, 0, 255);
        this.x = this.registerInteger("X", "xWaternark", 1, 0, 1280);
        this.y = this.registerInteger("Y", "yWatermark", 1, 0, 960);
        this.onion = this.registerBoolean("OnionMode", "onion", false);
    }
    
    public void onRender() {
        if (this.onion.getValue()) {
            Vulcan.fontRenderer.drawStringWithShadow("OnionWareMc V1 | " + Minecraft.getDebugFPS() + " | " + Watermark.mc.player.getName(), this.x.getValue(), this.y.getValue(), new Color(this.r.getValue(), this.g.getValue(), this.b.getValue(), 255).getRGB());
        }
        else {
            Vulcan.fontRenderer.drawStringWithShadow(this.mode.getValue(), this.x.getValue(), this.y.getValue(), new Color(this.r.getValue(), this.g.getValue(), this.b.getValue(), 255).getRGB());
        }
    }
}
