//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.misc;

import me.ka1.vulcan.module.*;
import me.ka1.vulcan.setting.*;
import java.util.*;
import com.mojang.authlib.*;
import net.minecraft.client.entity.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;

public class FakePlayer extends Module
{
    Setting.Boolean copyInv;
    Setting.Mode multi;
    Setting.Double offset;
    Setting.Double speed;
    Setting.Boolean dynamic;
    
    public FakePlayer() {
        super("FakePlayer", "Creates a fake player", Module.Category.Misc);
    }
    
    public void setup() {
        final ArrayList<String> modes = new ArrayList<String>();
        modes.add("Single");
        modes.add("Multi");
        this.copyInv = this.registerBoolean("Copy Inventory", "copyInv", true);
        this.multi = this.registerMode("Mode", "mode", modes, "Single");
        this.offset = this.registerDouble("Offset", "offset", 4.0, 1.0, 14.0);
    }
    
    public void onEnable() {
        if (FakePlayer.mc.world == null) {
            return;
        }
        if (this.multi.getValue().equalsIgnoreCase("Single")) {
            this.spawnSinglePlayer();
        }
        else {
            if (!this.multi.getValue().equalsIgnoreCase("Multi")) {
                return;
            }
            this.spawnMultiplePlayers();
        }
    }
    
    public void onDisable() {
        FakePlayer.mc.world.removeEntityFromWorld(-100);
        FakePlayer.mc.world.removeEntityFromWorld(-101);
        FakePlayer.mc.world.removeEntityFromWorld(-102);
    }
    
    public void spawnSinglePlayer() {
        final EntityOtherPlayerMP fakePlayer = new EntityOtherPlayerMP((World)FakePlayer.mc.world, new GameProfile(UUID.fromString("fd277a5b-6558-4050-9ca5-e27f67db8277"), "_KA1"));
        fakePlayer.copyLocationAndAnglesFrom((Entity)FakePlayer.mc.player);
        fakePlayer.rotationYawHead = FakePlayer.mc.player.rotationYawHead;
        FakePlayer.mc.world.addEntityToWorld(-100, (Entity)fakePlayer);
        if (this.copyInv.getValue()) {
            fakePlayer.inventory.copyInventory(FakePlayer.mc.player.inventory);
        }
    }
    
    public void spawnMultiplePlayers() {
        final double posX = FakePlayer.mc.player.posX;
        final double posY = FakePlayer.mc.player.posY;
        final double posZ = FakePlayer.mc.player.posZ;
        final float yaw = FakePlayer.mc.player.rotationYawHead;
        final float pitch = FakePlayer.mc.player.rotationPitch;
        final EntityOtherPlayerMP fakePlayer0 = new EntityOtherPlayerMP((World)FakePlayer.mc.world, new GameProfile(UUID.fromString("fd277a5b-6558-4050-9ca5-e27f67db8277"), "_KA1"));
        final EntityOtherPlayerMP fakePlayer2 = new EntityOtherPlayerMP((World)FakePlayer.mc.world, new GameProfile(UUID.fromString("90674968-4cb5-47a7-a616-cdd11e17c22c"), "epic_bush_1"));
        final EntityOtherPlayerMP fakePlayer3 = new EntityOtherPlayerMP((World)FakePlayer.mc.world, new GameProfile(UUID.fromString("408902e4-11af-450c-b1e4-7cdf75936afd"), "FemboySex"));
        fakePlayer0.setPositionAndRotation(posX - this.offset.getValue(), posY, posZ, yaw, pitch);
        fakePlayer2.setPositionAndRotation(posX, posY, posZ, yaw, pitch);
        fakePlayer3.setPositionAndRotation(posX + this.offset.getValue(), posY, posZ, yaw, pitch);
        FakePlayer.mc.world.addEntityToWorld(-100, (Entity)fakePlayer0);
        FakePlayer.mc.world.addEntityToWorld(-101, (Entity)fakePlayer2);
        FakePlayer.mc.world.addEntityToWorld(-102, (Entity)fakePlayer3);
        if (this.copyInv.getValue()) {
            fakePlayer0.inventory.copyInventory(FakePlayer.mc.player.inventory);
            fakePlayer2.inventory.copyInventory(FakePlayer.mc.player.inventory);
            fakePlayer3.inventory.copyInventory(FakePlayer.mc.player.inventory);
        }
    }
}
