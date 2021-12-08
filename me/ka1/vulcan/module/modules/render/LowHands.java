//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.render;

import me.ka1.vulcan.module.*;
import net.minecraft.client.renderer.*;
import me.ka1.vulcan.setting.*;

public class LowHands extends Module
{
    ItemRenderer itemRenderer;
    Setting.Double off;
    Setting.Double main;
    
    public LowHands() {
        super("LowHands", Module.Category.Render);
        this.itemRenderer = LowHands.mc.entityRenderer.itemRenderer;
    }
    
    public void setup() {
        this.off = this.registerDouble("Off Height", "LowOffhandHeight", 0.5, 0.0, 1.0);
        this.main = this.registerDouble("Main Height", "LowMainhandHeight", 0.5, 0.0, 1.0);
    }
    
    public void onRender() {
        this.itemRenderer.equippedProgressOffHand = (float)this.off.getValue();
        this.itemRenderer.equippedProgressMainHand = (float)this.main.getValue();
    }
}
