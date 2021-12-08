//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.command.commands;

import me.ka1.vulcan.command.*;
import me.ka1.vulcan.util.enemy.*;
import com.mojang.realmsclient.gui.*;

public class EnemyCommand extends Command
{
    public String[] getAlias() {
        return new String[] { "enemy", "enemies", "e" };
    }
    
    public String getSyntax() {
        return "enemy <add | del> <name>";
    }
    
    public void onCommand(final String command, final String[] args) throws Exception {
        if (args[0].equalsIgnoreCase("add")) {
            if (!Enemies.getEnemies().contains(Enemies.getEnemyByName(args[1]))) {
                Enemies.addEnemy(args[1]);
                sendClientMessage(ChatFormatting.GRAY + "Added enemy with name " + args[1]);
            }
            else {
                sendClientMessage(ChatFormatting.GRAY + args[1] + " is already an enemy!");
            }
        }
        else if (args[0].equalsIgnoreCase("del") || args[0].equalsIgnoreCase("remove")) {
            Enemies.delEnemy(args[1]);
            sendClientMessage(ChatFormatting.GRAY + "Removed enemy with name " + args[1]);
        }
        else {
            sendClientMessage(this.getSyntax());
        }
    }
}
