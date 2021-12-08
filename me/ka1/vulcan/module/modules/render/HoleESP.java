//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.render;

import me.ka1.vulcan.module.*;
import me.ka1.vulcan.setting.*;
import net.minecraft.util.math.*;
import java.util.*;
import me.ka1.vulcan.util.*;

public class HoleESP extends Module
{
    Setting.Mode mode;
    static Setting.Integer range;
    Setting.Integer r;
    Setting.Integer g;
    Setting.Integer b;
    Setting.Integer a;
    BlockPos blockPos;
    List<BlockPos> validBlockPos;
    
    public HoleESP() {
        super("HoleESP", "Higlights holes in the ground", Module.Category.Render);
    }
    
    public void setup() {
        final ArrayList<String> modes = new ArrayList<String>();
        modes.add("Gradient");
        modes.add("FlatOutline");
        modes.add("Flat");
        modes.add("Outline");
        this.mode = this.registerMode("Mode", "mode", modes, "Gradient");
        HoleESP.range = this.registerInteger("range", "range", 8, 4, 14);
        this.r = this.registerInteger("Red", "red", 100, 0, 255);
        this.g = this.registerInteger("Green", "green", 100, 0, 255);
        this.b = this.registerInteger("Blue", "blue", 100, 0, 255);
        this.a = this.registerInteger("Alpha", "alpha", 100, 0, 255);
    }
    
    public void onUpdate() {
        if (!RenderUtil.worldCheck()) {
            return;
        }
    }
    
    List findHoles() {
        return this.validBlockPos;
    }
    
    public static BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(HoleESP.mc.player.posX), Math.floor(HoleESP.mc.player.posY), Math.floor(HoleESP.mc.player.posZ));
    }
    
    static boolean IsHole(final BlockPos blockPos) {
        return true;
    }
}
