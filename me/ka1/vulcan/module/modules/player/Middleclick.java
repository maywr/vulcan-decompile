//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.player;

import me.ka1.vulcan.module.*;
import me.ka1.vulcan.setting.*;
import java.util.*;
import org.lwjgl.input.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import net.minecraft.init.*;

public class Middleclick extends Module
{
    Setting.Mode item;
    private int prevItem;
    private int i;
    
    public Middleclick() {
        super("MiddleClick", "allows you to use items by middleclicking", Module.Category.Player);
    }
    
    public void setup() {
        final ArrayList<String> items = new ArrayList<String>();
        items.add("Exp");
        items.add("Pearl");
        this.item = this.registerMode("Item", "item", items, "Exp");
    }
    
    public void onUpdate() {
        if (Middleclick.mc.world == null || Middleclick.mc.player == null) {
            return;
        }
        this.i = 0;
        if (Mouse.isButtonDown(2)) {
            this.prevItem = Middleclick.mc.player.inventory.currentItem;
            if (this.item.getValue().equalsIgnoreCase("Exp")) {
                Middleclick.mc.rightClickDelayTimer = 1;
                Middleclick.mc.player.inventory.currentItem = this.findItemExpInHotbar();
                Middleclick.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(0.0f, 90.0f, true));
                while (Mouse.isButtonDown(2)) {
                    Middleclick.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem());
                }
            }
        }
        else if (!Mouse.isButtonDown(2)) {
            Middleclick.mc.player.inventory.currentItem = this.prevItem;
        }
    }
    
    private int findItemExpInHotbar() {
        int slot = 0;
        for (int i = 0; i < 9; ++i) {
            if (Middleclick.mc.player.inventory.getStackInSlot(i).getItem() == Items.EXPERIENCE_BOTTLE) {
                slot = i;
                break;
            }
        }
        return slot;
    }
    
    private int findItemPearlInHotbar() {
        int slot = 0;
        for (int i = 0; i < 9; ++i) {
            if (Middleclick.mc.player.inventory.getStackInSlot(i).getItem() == Items.ENDER_PEARL) {
                slot = i;
                break;
            }
        }
        return slot;
    }
}
