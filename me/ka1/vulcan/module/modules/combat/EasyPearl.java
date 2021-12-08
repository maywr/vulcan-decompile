//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.combat;

import me.ka1.vulcan.module.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class EasyPearl extends Module
{
    int oldSlot;
    int tick;
    
    public EasyPearl() {
        super("EasyPearl", "Throws a pearl on toggle", Module.Category.Combat);
        this.oldSlot = 0;
        this.tick = 0;
    }
    
    protected void onEnable() {
        this.oldSlot = EasyPearl.mc.player.inventory.currentItem;
        EasyPearl.mc.player.inventory.currentItem = this.findPearlInHotbar();
    }
    
    public void onUpdate() {
        ++this.tick;
        if (EasyPearl.mc.player.inventory.getStackInSlot(EasyPearl.mc.player.inventory.currentItem).getItem() == Items.ENDER_PEARL && this.tick == 3) {
            EasyPearl.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
        }
        else if (EasyPearl.mc.player.inventory.getStackInSlot(EasyPearl.mc.player.inventory.currentItem).getItem() != Items.ENDER_PEARL && this.tick == 3) {
            EasyPearl.mc.player.inventory.currentItem = this.findPearlInHotbar();
            EasyPearl.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
        }
        if (EasyPearl.mc.player.inventory.getStackInSlot(EasyPearl.mc.player.inventory.currentItem).getItem() == Items.ENDER_PEARL && this.tick == 6) {
            this.disable();
        }
    }
    
    protected void onDisable() {
        EasyPearl.mc.player.inventory.currentItem = this.oldSlot;
        this.tick = 0;
    }
    
    private int findPearlInHotbar() {
        int slot = 0;
        for (int i = 0; i < 9; ++i) {
            if (EasyPearl.mc.player.inventory.getStackInSlot(i).getItem() == Items.ENDER_PEARL) {
                slot = i;
                break;
            }
        }
        return slot;
    }
}
