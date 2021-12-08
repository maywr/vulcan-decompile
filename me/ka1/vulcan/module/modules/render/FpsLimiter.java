//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.render;

import me.ka1.vulcan.module.*;
import me.ka1.vulcan.setting.*;

public class FpsLimiter extends Module
{
    Setting.Integer maxFps;
    
    public FpsLimiter() {
        super("FPSLimiter", "fpsLimiter", Module.Category.Client);
    }
    
    public void setup() {
        this.maxFps = this.registerInteger("Max Fps", "fpsLimiterMax", 240, 5, 1000);
    }
    
    public void onUpdate() {
        FpsLimiter.mc.gameSettings.limitFramerate = this.maxFps.getValue();
    }
}
