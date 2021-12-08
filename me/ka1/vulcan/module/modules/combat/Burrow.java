//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.combat;

import me.ka1.vulcan.module.*;
import me.ka1.vulcan.setting.*;
import net.minecraft.init.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import me.ka1.vulcan.util.friend.*;
import net.minecraft.block.*;
import me.ka1.vulcan.util.*;
import me.ka1.vulcan.command.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.entity.item.*;
import net.minecraft.util.math.*;
import java.util.*;

public class Burrow extends Module
{
    Setting.Boolean rotate;
    Setting.Double offset;
    Setting.Boolean smart;
    Setting.Double smartDistance;
    private BlockPos originalPos;
    private int oldSlot;
    
    public Burrow() {
        super("Burrow", "Glitches you into a block", Module.Category.Combat);
        this.oldSlot = -1;
    }
    
    public void setup() {
        this.rotate = this.registerBoolean("Rotate", "RotateBurrow", false);
        this.offset = this.registerDouble("Offset", "offsetBurrow", 7.0, -10.0, 10.0);
        this.smart = this.registerBoolean("Smart", "smartBurrow", false);
        this.smartDistance = this.registerDouble("Distance", "distanceBurrow", 2.5, 1.0, 7.0);
    }
    
    public void onEnable() {
        super.onEnable();
        this.originalPos = new BlockPos(Burrow.mc.player.posX, Burrow.mc.player.posY, Burrow.mc.player.posZ);
        if (Burrow.mc.world.getBlockState(new BlockPos(Burrow.mc.player.posX, Burrow.mc.player.posY, Burrow.mc.player.posZ)).getBlock().equals(Blocks.OBSIDIAN) || this.intersectsWithEntity(this.originalPos)) {
            this.toggle();
            return;
        }
        this.oldSlot = Burrow.mc.player.inventory.currentItem;
    }
    
    public void onUpdate() {
        if (this.smart.getValue() && MovementUtils.isInHole((Entity)Burrow.mc.player)) {
            Burrow.mc.world.loadedEntityList.stream().filter(e -> e instanceof EntityPlayer).filter(e -> e != Burrow.mc.player).forEach(e -> {
                if (!Friends.isFriend(e.getName())) {
                    if (e.getDistance((Entity)Burrow.mc.player) + 0.22f <= this.smartDistance.getValue()) {
                        this.doShit();
                    }
                }
            });
        }
        else {
            this.doShit();
        }
    }
    
    public void doShit() {
        if (BurrowUtil.findHotbarBlock(BlockObsidian.class) == -1) {
            Command.sendClientMessage("Can't find obsidian in hotbar!");
            this.toggle();
            return;
        }
        BurrowUtil.switchToSlot(BurrowUtil.findHotbarBlock(BlockObsidian.class));
        Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 0.41999998688698, Burrow.mc.player.posZ, true));
        Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 0.7531999805211997, Burrow.mc.player.posZ, true));
        Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 1.00133597911214, Burrow.mc.player.posZ, true));
        Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 1.16610926093821, Burrow.mc.player.posZ, true));
        BurrowUtil.placeBlock(this.originalPos, EnumHand.MAIN_HAND, this.rotate.getValue(), true, false);
        Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY + this.offset.getValue(), Burrow.mc.player.posZ, false));
        BurrowUtil.switchToSlot(this.oldSlot);
        this.toggle();
    }
    
    private boolean intersectsWithEntity(final BlockPos pos) {
        for (final Entity entity : Burrow.mc.world.loadedEntityList) {
            if (entity.equals((Object)Burrow.mc.player)) {
                continue;
            }
            if (entity instanceof EntityItem) {
                continue;
            }
            if (new AxisAlignedBB(pos).intersects(entity.getEntityBoundingBox())) {
                return true;
            }
        }
        return false;
    }
}
