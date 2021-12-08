//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.Hud;

import me.ka1.vulcan.module.*;
import me.ka1.vulcan.setting.*;
import me.ka1.vulcan.*;
import java.awt.*;

public class willy extends Module
{
    Setting.Integer r;
    Setting.Integer g;
    Setting.Integer b;
    Setting.Integer x;
    Setting.Integer y;
    Setting.Boolean anal;
    
    public willy() {
        super("willy", "funny willy lol", Module.Category.Hud);
    }
    
    public void setup() {
        this.anal = this.registerBoolean("willylol", "willy", false);
        this.r = this.registerInteger("Red", "redwilly", 255, 0, 255);
        this.g = this.registerInteger("Green", "greenwilly", 255, 0, 255);
        this.b = this.registerInteger("Blue", "bluewilly", 255, 0, 255);
        this.x = this.registerInteger("X", "xwilly", 0, 0, 1280);
        this.y = this.registerInteger("Y", "ywilly", 90, 0, 960);
    }
    
    public void onRender() {
        if (this.anal.getValue() && willy.mc.player.posY == 69.0) {
            Vulcan.fontRenderer.drawStringWithShadow("anal", this.x.getValue(), this.y.getValue(), new Color(this.r.getValue(), this.g.getValue(), this.b.getValue()).getRGB());
        }
        else {
            Vulcan.fontRenderer.drawStringWithShadow("fortnite", this.x.getValue(), this.y.getValue(), new Color(this.r.getValue(), this.g.getValue(), this.b.getValue()).getRGB());
        }
    }
}
