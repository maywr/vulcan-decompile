//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.render;

import me.ka1.vulcan.setting.*;
import me.ka1.vulcan.module.*;

public class FOVSlider extends Module
{
    Setting.Double fov;
    
    public FOVSlider() {
        super("FOVSlider", "Lets you change your FOV", Module.Category.Render);
    }
    
    public void setup() {
        this.fov = this.registerDouble("FOV", "fov", 115.0, 25.0, 180.0);
    }
    
    public void onRender() {
        if (!ModuleManager.isModuleEnabled("Zoom") && FOVSlider.mc.gameSettings.fovSetting != (float)this.fov.getValue()) {
            FOVSlider.mc.gameSettings.fovSetting = (float)this.fov.getValue();
        }
    }
}
