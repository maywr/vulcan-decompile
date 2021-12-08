//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.movement;

import me.ka1.vulcan.module.*;
import me.ka1.vulcan.setting.*;
import java.util.*;
import com.mojang.realmsclient.gui.*;

public class Sprint extends Module
{
    Setting.Mode mode;
    
    public Sprint() {
        super("Sprint", "Makes you sprint", Module.Category.Movement);
    }
    
    public void setup() {
        final ArrayList<String> Modes = new ArrayList<String>();
        Modes.add("Rage");
        Modes.add("Legit");
        this.mode = this.registerMode("Mode", "Mode", Modes, "Rage");
    }
    
    public void onUpdate() {
        if (this.mode.getValue().equalsIgnoreCase("Legit")) {
            if (Sprint.mc.gameSettings.keyBindForward.isKeyDown() && !Sprint.mc.player.collidedHorizontally && !Sprint.mc.player.isSprinting()) {
                Sprint.mc.player.setSprinting(true);
            }
        }
        else if (this.mode.getValue().equalsIgnoreCase("Rage") && !Sprint.mc.player.isSprinting()) {
            Sprint.mc.player.setSprinting(true);
        }
    }
    
    public String getHudInfo() {
        String t = "";
        if (this.mode.getValue().equalsIgnoreCase("Rage")) {
            t = "[" + ChatFormatting.WHITE + "Rage" + ChatFormatting.GRAY + "]";
        }
        if (this.mode.getValue().equalsIgnoreCase("Legit")) {
            t = "[" + ChatFormatting.WHITE + "Legit" + ChatFormatting.GRAY + "]";
        }
        return t;
    }
}
