//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.client;

import me.ka1.vulcan.module.*;

public class LoadConfig extends Module
{
    public LoadConfig() {
        super("LoadConfig", "loadConfig", Module.Category.Client);
    }
    
    public void onEnable() {
        if (LoadConfig.mc.world != null && LoadConfig.mc.world != null) {
            this.disable();
        }
    }
}
