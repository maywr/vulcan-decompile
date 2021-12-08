//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.macro;

import net.minecraft.client.*;

public class Macro
{
    int key;
    String value;
    
    public Macro(final int k, final String v) {
        this.key = k;
        this.value = v;
    }
    
    public void onMacro() {
        if (Minecraft.getMinecraft().player != null) {
            Minecraft.getMinecraft().player.sendChatMessage(this.value);
        }
    }
    
    public int getKey() {
        return this.key;
    }
    
    public String getValue() {
        return this.value;
    }
}
