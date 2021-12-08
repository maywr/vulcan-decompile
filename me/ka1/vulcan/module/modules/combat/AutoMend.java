//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.combat;

import me.ka1.vulcan.setting.*;
import me.ka1.vulcan.module.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import java.util.*;

public class AutoMend extends Module
{
    int delay;
    Setting.Integer ThrowDelay;
    private final BlockPos[] surroundOffset;
    
    public AutoMend() {
        super("AutoMend", "Automatically mends your armor", Module.Category.Combat);
        this.delay = 0;
        this.surroundOffset = new BlockPos[] { new BlockPos(0, -1, 0), new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0) };
    }
    
    public void setup() {
        this.ThrowDelay = this.registerInteger("Throw Delay", "throwDelay", 2, 1, 10);
    }
    
    public void onUpdate() {
        final int ArmorDurability = this.getArmorDurability();
        final boolean safe = this.isSafe();
        final boolean AutoCrystal = ModuleManager.isModuleEnabled("AutoCrystal");
        final BlockPos q = AutoMend.mc.player.getPosition();
        if (!AutoCrystal && AutoMend.mc.player.isSneaking() && safe && 0 < ArmorDurability) {
            ++this.delay;
            if (this.delay % this.ThrowDelay.getValue() == 0) {
                AutoMend.mc.player.inventory.currentItem = this.findExpInHotbar();
                AutoMend.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(0.0f, 90.0f, true));
                AutoMend.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
            }
        }
        else {
            this.delay = 0;
        }
        super.onUpdate();
    }
    
    private int findExpInHotbar() {
        int slot = 0;
        for (int i = 0; i < 9; ++i) {
            if (AutoMend.mc.player.inventory.getStackInSlot(i).getItem() == Items.EXPERIENCE_BOTTLE) {
                slot = i;
                break;
            }
        }
        return slot;
    }
    
    private boolean isSafe() {
        boolean safe = true;
        final BlockPos playerPos = getPlayerPos();
        for (final BlockPos offset : this.surroundOffset) {
            final Block block = AutoMend.mc.world.getBlockState(playerPos.add((Vec3i)offset)).getBlock();
            if (block != Blocks.BEDROCK && block != Blocks.OBSIDIAN && block != Blocks.ENDER_CHEST && block != Blocks.ANVIL) {
                safe = false;
                break;
            }
        }
        return safe;
    }
    
    private static BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(AutoMend.mc.player.posX), Math.floor(AutoMend.mc.player.posY), Math.floor(AutoMend.mc.player.posZ));
    }
    
    private int getArmorDurability() {
        int TotalDurability = 0;
        for (final ItemStack itemStack : AutoMend.mc.player.inventory.armorInventory) {
            TotalDurability += itemStack.getItemDamage();
        }
        return TotalDurability;
    }
}
