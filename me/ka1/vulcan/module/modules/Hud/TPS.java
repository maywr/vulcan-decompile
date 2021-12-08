//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.Hud;

import me.ka1.vulcan.module.*;
import me.ka1.vulcan.setting.*;
import java.util.*;
import java.text.*;
import me.ka1.vulcan.*;
import me.ka1.vulcan.util.*;
import java.awt.*;

public class TPS extends Module
{
    Setting.Mode tpsMode;
    Setting.Integer r;
    Setting.Integer g;
    Setting.Integer b;
    Setting.Integer x;
    Setting.Integer y;
    
    public TPS() {
        super("TPS", "tps", Module.Category.Hud);
    }
    
    public void setup() {
        final ArrayList<String> tpsModes = new ArrayList<String>();
        tpsModes.add("Double");
        tpsModes.add("Float");
        this.tpsMode = this.registerMode("Precision", "tpsMode", (List)tpsModes, "Double");
        this.r = this.registerInteger("Red", "red", 255, 0, 255);
        this.g = this.registerInteger("Green", "green", 255, 0, 255);
        this.b = this.registerInteger("Blue", "blue", 255, 0, 255);
        this.x = this.registerInteger("X", "xTps", 0, 0, 1280);
        this.y = this.registerInteger("Y", "yTps", 50, 0, 960);
    }
    
    public void onRender() {
        final DecimalFormat dbl = new DecimalFormat("##.##");
        final DecimalFormat flt = new DecimalFormat("##.####");
        if (this.tpsMode.getValue() == "Double") {
            Vulcan.fontRenderer.drawStringWithShadow("TPS: " + dbl.format(TpsUtils.getTickRate()), this.x.getValue(), this.y.getValue(), new Color(this.r.getValue(), this.g.getValue(), this.b.getValue(), 255).getRGB());
        }
        else if (this.tpsMode.getValue() == "Float") {
            Vulcan.fontRenderer.drawStringWithShadow("TPS: " + flt.format(TpsUtils.getTickRate()), this.x.getValue(), this.y.getValue(), new Color(this.r.getValue(), this.g.getValue(), this.b.getValue(), 255).getRGB());
        }
    }
}
