//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.misc;

import me.ka1.vulcan.module.*;
import org.apache.logging.log4j.*;

public class RageQuit extends Module
{
    public static final Logger log;
    
    public RageQuit() {
        super("Rage Quit", "Currently coping??", Module.Category.Misc);
    }
    
    public void onUpdate() {
        if (this.isEnabled()) {
            this.disable();
        }
        RageQuit.log.info("Stay mad kid");
        System.exit(0);
    }
    
    static {
        log = LogManager.getLogger();
    }
}
