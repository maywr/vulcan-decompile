//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.player;

import me.ka1.vulcan.module.*;
import net.minecraft.init.*;

public class FastCrystal extends Module
{
    public FastCrystal() {
        super("FastCrystal", "mc.rightClickDelayTimer = 0;", Module.Category.Player);
    }
    
    public void onUpdate() {
        if (FastCrystal.mc.player == null || FastCrystal.mc.world == null) {
            return;
        }
        if (FastCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL || FastCrystal.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL) {
            FastCrystal.mc.rightClickDelayTimer = 0;
        }
    }
}
