//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.util.config;

import me.ka1.vulcan.*;

public class Stopper extends Thread
{
    @Override
    public void run() {
        saveConfig();
    }
    
    public static void saveConfig() {
        Vulcan.getInstance().saveModules.saveModules();
        SaveConfiguration.saveBinds();
        SaveConfiguration.saveDrawn();
        SaveConfiguration.saveEnabled();
        SaveConfiguration.saveEnemies();
        SaveConfiguration.saveFont();
        SaveConfiguration.saveFriends();
        SaveConfiguration.saveGUI();
        SaveConfiguration.saveMacros();
        SaveConfiguration.saveMessages();
        SaveConfiguration.savePrefix();
    }
}
