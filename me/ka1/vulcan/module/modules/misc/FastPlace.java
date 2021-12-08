//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.misc;

import me.ka1.vulcan.module.*;
import me.ka1.vulcan.setting.*;
import net.minecraft.item.*;

public class FastPlace extends Module
{
    Setting.Boolean exp;
    Setting.Boolean crystals;
    Setting.Boolean breaking;
    Setting.Boolean placing;
    Setting.Boolean Echest;
    private Object BlockEnderChest;
    
    public FastPlace() {
        super("FastPlace", "Increases your place speed", Module.Category.Misc);
    }
    
    public void setup() {
        this.exp = this.registerBoolean("Exp", "Exp", false);
        this.crystals = this.registerBoolean("Crystals", "Crystals", false);
        this.breaking = this.registerBoolean("Breaking", "Breaking", false);
        this.placing = this.registerBoolean("Placing", "Placing", false);
        this.Echest = this.registerBoolean("EChest", "EChest", false);
    }
    
    public void onUpdate() {
        final Item main = FastPlace.mc.player.getHeldItemMainhand().getItem();
        final Item off = FastPlace.mc.player.getHeldItemOffhand().getItem();
        final boolean main_exp = main instanceof ItemExpBottle;
        final boolean off_exp = off instanceof ItemExpBottle;
        final boolean main_cry = main instanceof ItemEndCrystal;
        final boolean off_cry = off instanceof ItemEndCrystal;
        if ((main_exp | off_exp) && this.exp.getValue()) {
            FastPlace.mc.rightClickDelayTimer = 0;
        }
        if ((main_cry | off_cry) && this.crystals.getValue()) {
            FastPlace.mc.rightClickDelayTimer = 0;
        }
        if (this.placing.getValue()) {
            FastPlace.mc.rightClickDelayTimer = 0;
        }
        if (this.breaking.getValue()) {
            FastPlace.mc.playerController.blockHitDelay = 0;
        }
        if (this.Echest.getValue() || FastPlace.mc.player.getHeldItemMainhand() == this.BlockEnderChest || FastPlace.mc.player.getHeldItemOffhand() == this.BlockEnderChest) {
            FastPlace.mc.rightClickDelayTimer = 0;
        }
    }
}
