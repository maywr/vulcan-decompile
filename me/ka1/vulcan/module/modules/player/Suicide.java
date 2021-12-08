//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.player;

import me.ka1.vulcan.module.*;

public class Suicide extends Module
{
    public Suicide() {
        super("Suicide", "/kills you lol", Module.Category.Player);
    }
    
    public void onEnable() {
        this.disable();
        Suicide.mc.player.sendChatMessage("/kill");
    }
}
