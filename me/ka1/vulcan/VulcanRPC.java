///Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan;

import net.minecraft.client.*;
import club.minnced.discord.rpc.*;

public class VulcanRPC
{
    private static final String ClientId = "780466138680524890";
    private static final Minecraft mc;
    private static final DiscordRPC rpc;
    public static DiscordRichPresence presence;
    private static String details;
    private static String state;
    
    public static void init() {
        final DiscordEventHandlers handlers = new DiscordEventHandlers();
        handlers.disconnected = ((var1, var2) -> System.out.println("Discord RPC disconnected, var1: " + var1 + ", var2: " + var2));
        VulcanRPC.rpc.Discord_Initialize("780466138680524890", handlers, true, "");
        VulcanRPC.presence.startTimestamp = System.currentTimeMillis() / 1000L;
        VulcanRPC.presence.details = "Vulcan Client 0.5";
        VulcanRPC.presence.state = me.ka1.vulcan.module.modules.client.DiscordRPC.presenceState.getValue();
        VulcanRPC.presence.largeImageKey = me.ka1.vulcan.module.modules.client.DiscordRPC.logoMode.getValue().toLowerCase();
        VulcanRPC.presence.largeImageText = "Vulcan Client 0.5";
        VulcanRPC.presence.smallImageKey = "transparent";
        VulcanRPC.presence.smallImageText = VulcanRPC.mc.getSession().getUsername();
        VulcanRPC.rpc.Discord_UpdatePresence(VulcanRPC.presence);
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    VulcanRPC.rpc.Discord_RunCallbacks();
                    VulcanRPC.details = "Vulcan Client0.5";
                    VulcanRPC.state = me.ka1.vulcan.module.modules.client.DiscordRPC.presenceState.getValue();
                    if (VulcanRPC.mc.isIntegratedServerRunning()) {
                        VulcanRPC.details = "Singleplayer";
                    }
                    else if (VulcanRPC.mc.getCurrentServerData() != null && me.ka1.vulcan.module.modules.client.DiscordRPC.server.getValue()) {
                        if (!VulcanRPC.mc.getCurrentServerData().serverIP.equals("")) {
                            VulcanRPC.details = "shanking ops on " + VulcanRPC.mc.getCurrentServerData().serverIP;
                        }
                    }
                    else {
                        VulcanRPC.details = " Chilling in da main menu";
                    }
                    if (!VulcanRPC.details.equals(VulcanRPC.presence.details) || !VulcanRPC.state.equals(VulcanRPC.presence.state)) {
                        VulcanRPC.presence.startTimestamp = System.currentTimeMillis() / 1000L;
                    }
                    VulcanRPC.presence.details = VulcanRPC.details;
                    VulcanRPC.presence.state = VulcanRPC.state;
                    VulcanRPC.rpc.Discord_UpdatePresence(VulcanRPC.presence);
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
                try {
                    Thread.sleep(5000L);
                }
                catch (InterruptedException e3) {
                    e3.printStackTrace();
                }
            }
        }, "Discord-RPC-Callback-Handler").start();
    }
    
    public static void shutdown() {
        VulcanRPC.rpc.Discord_Shutdown();
    }
    
    static {
        mc = Minecraft.getMinecraft();
        rpc = DiscordRPC.INSTANCE;
        VulcanRPC.presence = new DiscordRichPresence();
    }
}
