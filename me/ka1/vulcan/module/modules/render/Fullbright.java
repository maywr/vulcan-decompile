//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.render;

import me.ka1.vulcan.module.*;
import me.ka1.vulcan.setting.*;
import java.util.*;
import net.minecraft.potion.*;

public class Fullbright extends Module
{
    Setting.Mode fullbrightMode;
    
    public Fullbright() {
        super("Fullbright", "Brightens up your world", Module.Category.Render);
    }
    
    public void setup() {
        final ArrayList<String> Modes = new ArrayList<String>();
        Modes.add("Potion");
        Modes.add("Gamma");
        this.fullbrightMode = this.registerMode("Mode", "fullbrightMode", Modes, "Potion");
    }
    
    public void onRender() {
        if (this.fullbrightMode.getValue().equals("Potion")) {
            final PotionEffect NightVis = new PotionEffect(Potion.getPotionById(16), 696969696, 5);
            NightVis.setPotionDurationMax(true);
            Fullbright.mc.player.addPotionEffect(NightVis);
        }
        else if (this.fullbrightMode.getValue().equals("Gamma")) {
            Fullbright.mc.gameSettings.gammaSetting = 420.0f;
            Fullbright.mc.player.removePotionEffect(Potion.getPotionById(16));
        }
    }
}
