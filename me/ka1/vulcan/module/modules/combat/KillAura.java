//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.combat;

import me.ka1.vulcan.setting.*;
import me.ka1.vulcan.event.events.*;
import me.zero.alpine.listener.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import java.util.function.*;
import net.minecraft.entity.player.*;
import me.ka1.vulcan.util.friend.*;
import java.util.*;
import java.util.stream.*;
import net.minecraft.item.*;
import me.ka1.vulcan.module.*;
import net.minecraft.entity.*;
import me.ka1.vulcan.*;
import net.minecraft.util.text.*;
import net.minecraft.util.*;

public class KillAura extends Module
{
    private Setting.Boolean swordOnly;
    private Setting.Boolean caCheck;
    private Setting.Boolean criticals;
    private Setting.Double range;
    private String targetName;
    private boolean isAttacking;
    @EventHandler
    private final Listener<PacketEvent.Send> listener;
    
    public KillAura() {
        super("KillAura", "Automatically smacks people", Module.Category.Combat);
        this.isAttacking = false;
        this.listener = new Listener<PacketEvent.Send>(event -> {
            if (event.getPacket() instanceof CPacketUseEntity && this.criticals.getValue() && ((CPacketUseEntity)event.getPacket()).getAction() == CPacketUseEntity.Action.ATTACK && KillAura.mc.player.onGround && this.isAttacking) {
                KillAura.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(KillAura.mc.player.posX, KillAura.mc.player.posY + 0.10000000149011612, KillAura.mc.player.posZ, false));
                KillAura.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(KillAura.mc.player.posX, KillAura.mc.player.posY, KillAura.mc.player.posZ, false));
            }
        }, new Predicate[0]);
    }
    
    public void setup() {
        this.range = this.registerDouble("Range", "Range", 5.0, 0.0, 10.0);
        this.swordOnly = this.registerBoolean("Sword Only", "SwordOnly", true);
        this.criticals = this.registerBoolean("Criticals", "Criticals", true);
        this.caCheck = this.registerBoolean("AC Check", "ACCheck", false);
    }
    
    public void onUpdate() {
        if (KillAura.mc.player == null || KillAura.mc.player.isDead) {
            return;
        }
        final List<Entity> targets = (List<Entity>)KillAura.mc.world.loadedEntityList.stream().filter(entity -> entity != KillAura.mc.player).filter(entity -> KillAura.mc.player.getDistance(entity) <= this.range.getValue()).filter(entity -> !entity.isDead).filter(entity -> entity instanceof EntityPlayer).filter(entity -> entity.getHealth() > 0.0f).filter(entity -> !Friends.isFriend(entity.getName())).sorted(Comparator.comparing(e -> KillAura.mc.player.getDistance(e))).collect(Collectors.toList());
        targets.forEach(target -> {
            if (!this.swordOnly.getValue() || KillAura.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword) {
                if (!this.caCheck.getValue() || !((AutoCrystal)ModuleManager.getModuleByName("AutoCrystal")).isActive) {
                    if (target.getDistance((Entity)KillAura.mc.player) < this.range.getValue() && target != null) {
                        this.targetName = target.getName();
                    }
                    else if (target == null || target.getDistance((Entity)KillAura.mc.player) > this.range.getValue()) {
                        this.targetName = "No target!";
                    }
                    this.attack(target);
                }
            }
        });
    }
    
    public void onEnable() {
        Vulcan.EVENT_BUS.subscribe(this);
    }
    
    public void onDisable() {
        Vulcan.EVENT_BUS.unsubscribe(this);
    }
    
    public String getHudInfo() {
        final String s = TextFormatting.GRAY + "[" + TextFormatting.WHITE + this.targetName + TextFormatting.GRAY + "]";
        return s;
    }
    
    public void attack(final Entity e) {
        if (KillAura.mc.player.getCooledAttackStrength(0.0f) >= 1.0f) {
            this.isAttacking = true;
            KillAura.mc.playerController.attackEntity((EntityPlayer)KillAura.mc.player, e);
            KillAura.mc.player.swingArm(EnumHand.MAIN_HAND);
            this.isAttacking = false;
        }
    }
}
