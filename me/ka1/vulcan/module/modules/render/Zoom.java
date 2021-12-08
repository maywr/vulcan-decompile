//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.render;

import me.ka1.vulcan.module.*;
import me.ka1.vulcan.setting.*;

public class Zoom extends Module
{
    Setting.Double fov;
    Setting.Boolean smooth;
    public float oldVal;
    
    public Zoom() {
        super("Zoom", "Zooms In", Module.Category.Render);
    }
    
    public void setup() {
        this.fov = this.registerDouble("FOV", "fov", 25.0, 10.0, 100.0);
        this.smooth = this.registerBoolean("Cinematic", "cinematic", true);
    }
    
    protected void onEnable() {
        this.oldVal = Zoom.mc.gameSettings.fovSetting;
        Zoom.mc.gameSettings.smoothCamera = this.smooth.getValue();
        Zoom.mc.gameSettings.fovSetting = (float)this.fov.getValue();
        super.onEnable();
    }
    
    protected void onDisable() {
        Zoom.mc.gameSettings.fovSetting = this.oldVal;
        Zoom.mc.gameSettings.smoothCamera = false;
        super.onDisable();
    }
}
