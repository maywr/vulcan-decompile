//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.movement;

import me.ka1.vulcan.module.*;
import me.ka1.vulcan.setting.*;
import net.minecraft.entity.*;

public class ReverseStep extends Module
{
    Setting.Double height;
    
    public ReverseStep() {
        super("ReverseStep", "Allows you to instantly step down blocks", Module.Category.Movement);
    }
    
    public void setup() {
        this.height = this.registerDouble("Height", "Height", 2.5, 0.5, 15.0);
    }
    
    public void onUpdate() {
        if (ReverseStep.mc.world == null || ReverseStep.mc.player == null || ReverseStep.mc.player.isInWater() || ReverseStep.mc.player.isInLava() || ReverseStep.mc.player.isOnLadder() || ReverseStep.mc.gameSettings.keyBindJump.isKeyDown()) {
            return;
        }
        if (ReverseStep.mc.player != null && ReverseStep.mc.player.onGround && !ReverseStep.mc.player.isInWater() && !ReverseStep.mc.player.isOnLadder()) {
            for (double y = 0.0; y < this.height.getValue() + 0.5; y += 0.01) {
                if (!ReverseStep.mc.world.getCollisionBoxes((Entity)ReverseStep.mc.player, ReverseStep.mc.player.getEntityBoundingBox().offset(0.0, -y, 0.0)).isEmpty()) {
                    ReverseStep.mc.player.motionY = -10.0;
                    break;
                }
            }
        }
    }
}
