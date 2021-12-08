//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.client;

import me.ka1.vulcan.module.*;
import me.ka1.vulcan.setting.*;

public class Rainbow extends Module
{
    Setting.Integer speed;
    static int RainbowOffset;
    
    public Rainbow() {
        super("Rainbow", Module.Category.Client);
    }
    
    public void setup() {
        this.speed = this.registerInteger("Speed", "speed", 2, 1, 12);
        super.setup();
    }
    
    public void onRender() {
        Rainbow.RainbowOffset += this.speed.getValue();
        super.onRender();
    }
    
    public static int getRainbowOffset() {
        return Rainbow.RainbowOffset;
    }
    
    static {
        Rainbow.RainbowOffset = 0;
    }
}
