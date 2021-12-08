//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.movement;

import me.ka1.vulcan.module.*;
import me.ka1.vulcan.setting.*;
import net.minecraft.block.material.*;
import net.minecraft.client.entity.*;

public class MoonWalk extends Module
{
    Setting.Double poop;
    
    public MoonWalk() {
        super("MoonWalk", "Tux is uwu", Module.Category.Movement);
    }
    
    public void setup() {
        this.poop = this.registerDouble("tuxisblack", "poop", 69.0, 6.9, 420.0);
    }
    
    public void onUpdate() {
        if (MoonWalk.mc.player.onGround && MoonWalk.mc.gameSettings.keyBindJump.isPressed()) {
            MoonWalk.mc.player.motionY = 0.25;
        }
        else if (MoonWalk.mc.player.isAirBorne && !MoonWalk.mc.player.isInWater() && !MoonWalk.mc.player.isOnLadder() && !MoonWalk.mc.player.isInsideOfMaterial(Material.LAVA)) {
            MoonWalk.mc.player.motionY = 1.0E-6;
            final EntityPlayerSP player = MoonWalk.mc.player;
            player.jumpMovementFactor *= 1.21337f;
        }
    }
}
