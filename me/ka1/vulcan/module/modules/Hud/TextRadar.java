//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.Hud;

import me.ka1.vulcan.module.*;
import net.minecraft.util.text.*;
import me.ka1.vulcan.setting.*;
import net.minecraft.entity.player.*;
import me.ka1.vulcan.util.friend.*;
import net.minecraft.entity.*;
import me.ka1.vulcan.*;

public class TextRadar extends Module
{
    TextFormatting distance;
    Setting.Boolean friends;
    TextFormatting friend;
    TextFormatting health;
    Setting.Integer range;
    Setting.Integer x;
    Setting.Integer y;
    int playerAmount;
    float enemyHealth;
    
    public TextRadar() {
        super("TextRadar", "textRadar", Module.Category.Hud);
    }
    
    public void setup() {
        this.friends = this.registerBoolean("Friends", "friends", true);
        this.range = this.registerInteger("Range", "range", 100, 1, 260);
        this.x = this.registerInteger("X", "xtr", 0, 0, 1280);
        this.y = this.registerInteger("Y", "ytr", 0, 0, 960);
    }
    
    public void onRender() {
        this.playerAmount = 0;
        if (TextRadar.mc.player == null || TextRadar.mc.world == null) {
            return;
        }
        if (this.range.getValue() / 16 >= TextRadar.mc.gameSettings.renderDistanceChunks) {
            this.range.setValue(TextRadar.mc.gameSettings.renderDistanceChunks * 16);
        }
        TextRadar.mc.world.loadedEntityList.stream().filter(e -> e instanceof EntityPlayer).filter(e -> e != TextRadar.mc.player).forEach(e -> {
            if (this.friends.getValue() || !Friends.isFriend(((Entity)e).getName())) {
                if (TextRadar.mc.player.getDistance((Entity)e) < this.range.getValue()) {
                    if (Friends.isFriend(((Entity)e).getName())) {
                        this.friend = TextFormatting.GREEN;
                    }
                    else if (!Friends.isFriend(((Entity)e).getName())) {
                        this.friend = TextFormatting.RED;
                    }
                    if (e.getHealth() + e.getAbsorptionAmount() <= 5.0f) {
                        this.health = TextFormatting.RED;
                    }
                    if (e.getHealth() + e.getAbsorptionAmount() > 5.0f && e.getHealth() + e.getAbsorptionAmount() < 15.0f) {
                        this.health = TextFormatting.YELLOW;
                    }
                    if (e.getHealth() + e.getAbsorptionAmount() >= 15.0f) {
                        this.health = TextFormatting.GREEN;
                    }
                    if (TextRadar.mc.player.getDistance((Entity)e) < 25.0f) {
                        this.distance = TextFormatting.RED;
                    }
                    if (TextRadar.mc.player.getDistance((Entity)e) >= 25.0f && TextRadar.mc.player.getDistance((Entity)e) < 50.0f) {
                        this.distance = TextFormatting.YELLOW;
                    }
                    if (TextRadar.mc.player.getDistance((Entity)e) >= 50.0f) {
                        this.distance = TextFormatting.GREEN;
                    }
                    this.enemyHealth = e.getHealth() + e.getAbsorptionAmount();
                    if (this.x.getValue() > 645) {
                        Vulcan.fontRenderer.drawStringWithShadow(TextFormatting.GRAY + "[" + this.health + (int)this.enemyHealth + TextFormatting.GRAY + "] " + this.friend + ((Entity)e).getName() + TextFormatting.GRAY + " [" + this.distance + TextRadar.mc.player.getDistance((Entity)e) + TextFormatting.GRAY + "]", this.x.getValue() - this.getWidth(TextFormatting.GRAY + "[" + this.health + (e.getHealth() + e.getAbsorptionAmount()) + TextFormatting.GRAY + "] " + this.friend + ((Entity)e).getName() + TextFormatting.GRAY + " [" + this.distance + TextRadar.mc.player.getDistance((Entity)e) + TextFormatting.GRAY + "]"), this.y.getValue() + this.playerAmount * 10, -1);
                    }
                    else {
                        Vulcan.fontRenderer.drawStringWithShadow(TextFormatting.GRAY + "[" + this.health + (int)this.enemyHealth + TextFormatting.GRAY + "] " + this.friend + ((Entity)e).getName() + TextFormatting.GRAY + " [" + this.distance + TextRadar.mc.player.getDistance((Entity)e) + TextFormatting.GRAY + "]", this.x.getValue(), this.y.getValue() + this.playerAmount * 10, -1);
                    }
                    ++this.playerAmount;
                }
            }
        });
    }
    
    private int getWidth(final String s) {
        return Vulcan.fontRenderer.getStringWidth(s);
    }
}
