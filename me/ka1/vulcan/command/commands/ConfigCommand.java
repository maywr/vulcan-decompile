//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.command.commands;

import me.ka1.vulcan.command.*;
import me.ka1.vulcan.*;
import com.mojang.realmsclient.gui.*;
import me.ka1.vulcan.util.config.*;

public class ConfigCommand extends Command
{
    public String[] getAlias() {
        return new String[] { "config", "configs", "c" };
    }
    
    public String getSyntax() {
        return "config < save > ";
    }
    
    public void onCommand(final String command, final String[] args) {
        if (args[0].equalsIgnoreCase("load")) {
            Vulcan.loadConfiguration.loadEnabled();
            Vulcan.loadConfiguration.loadBinds();
            Vulcan.loadConfiguration.loadGUI();
            Vulcan.loadConfiguration.loadFont();
            Vulcan.loadConfiguration.loadFriends();
            Vulcan.loadConfiguration.loadPrefix();
            Vulcan.loadModules.loadHud();
            Vulcan.loadModules.loadClient();
            Vulcan.loadModules.loadMisc();
            Vulcan.loadModules.loadCombat();
            Vulcan.loadModules.loadMovement();
            Vulcan.loadModules.loadPlayer();
            Vulcan.loadModules.loadRender();
            Command.sendClientMessage(ChatFormatting.BOLD + "Loaded Config");
        }
        else if (args[0].equalsIgnoreCase("save")) {
            Stopper.saveConfig();
            sendClientMessage(ChatFormatting.BOLD + "Saved Config");
        }
        else {
            sendClientMessage(this.getSyntax());
        }
    }
}
