//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.player;

import me.ka1.vulcan.module.*;
import net.minecraft.init.*;

public class FastEXP extends Module
{
    public FastEXP() {
        super("FastEXP", "Allows you to place exp at a higher rate", Module.Category.Player);
    }
    
    public void onUpdate() {
        if (FastEXP.mc.player == null || FastEXP.mc.world == null) {
            return;
        }
        if (FastEXP.mc.player.inventory.getStackInSlot(FastEXP.mc.player.inventory.currentItem).getItem() == Items.EXPERIENCE_BOTTLE) {
            FastEXP.mc.rightClickDelayTimer = 0;
        }
    }
}
