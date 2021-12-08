//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.combat;

import me.ka1.vulcan.module.*;
import org.lwjgl.input.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.init.*;

public class MiddleClickEXP extends Module
{
    int prevItem;
    
    public MiddleClickEXP() {
        super("MiddleClickEXP", "Throws exp bottles at the ground when middle clicking", Module.Category.Combat);
    }
    
    public void setup() {
    }
    
    public void update() {
        if (Mouse.isButtonDown(2)) {
            this.prevItem = MiddleClickEXP.mc.player.inventory.currentItem;
            MiddleClickEXP.mc.player.inventory.currentItem = this.findItemExpInHotbar();
            MiddleClickEXP.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(0.0f, 90.0f, true));
            while (Mouse.isButtonDown(2)) {}
        }
    }
    
    private int findItemExpInHotbar() {
        int slot = 0;
        for (int i = 0; i < 9; ++i) {
            if (MiddleClickEXP.mc.player.inventory.getStackInSlot(i).getItem() == Items.EXPERIENCE_BOTTLE) {
                slot = i;
                break;
            }
        }
        return slot;
    }
}
