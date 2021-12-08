//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.util;

import net.minecraft.client.gui.*;
import net.minecraft.client.*;
import net.minecraft.client.entity.*;
import net.minecraft.entity.*;
import net.minecraft.world.*;
import org.lwjgl.input.*;

public class Wrapper
{
    private static FontRenderer fontRenderer;
    public static Minecraft mc;
    
    public static Minecraft getMinecraft() {
        return Minecraft.getMinecraft();
    }
    
    public static EntityPlayerSP getPlayer() {
        return getMinecraft().player;
    }
    
    public static Entity getRenderEntity() {
        return Wrapper.mc.getRenderViewEntity();
    }
    
    public static World getWorld() {
        return (World)getMinecraft().world;
    }
    
    public static int getKey(final String keyname) {
        return Keyboard.getKeyIndex(keyname.toUpperCase());
    }
    
    public static FontRenderer getFontRenderer() {
        return Wrapper.fontRenderer;
    }
    
    static {
        Wrapper.mc = Minecraft.getMinecraft();
    }
}
