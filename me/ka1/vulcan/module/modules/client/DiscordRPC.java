//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.client;

import me.ka1.vulcan.module.*;
import me.ka1.vulcan.setting.*;
import java.util.*;
import me.ka1.vulcan.*;

public class DiscordRPC extends Module
{
    public static Setting.Mode logoMode;
    public static Setting.Mode presenceState;
    public static Setting.Boolean server;
    
    public DiscordRPC() {
        super("DiscordRPC", "Displays a customisable RPC", Module.Category.Client);
    }
    
    public void setup() {
        final ArrayList<String> logoModes = new ArrayList<String>();
        logoModes.add("Normal");
        logoModes.add("Transparent");
        final ArrayList<String> stateModes = new ArrayList<String>();
        stateModes.add("Currently on top");
        stateModes.add("VULCAN based");
        stateModes.add("Hey :D");
        stateModes.add("V-Vulcan uwu");
        DiscordRPC.logoMode = this.registerMode("Logo Mode", "RpcLogoMode", logoModes, "Normal");
        DiscordRPC.presenceState = this.registerMode("Text", "presenceState", stateModes, "VULCAN based");
        DiscordRPC.server = this.registerBoolean("Server", "serverRpc", true);
    }
    
    public void onEnable() {
        VulcanRPC.init();
    }
    
    public void onDisable() {
        VulcanRPC.shutdown();
    }
}
