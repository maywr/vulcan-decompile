//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.command.commands;

import me.ka1.vulcan.command.*;
import me.ka1.vulcan.util.friend.*;
import com.mojang.realmsclient.gui.*;
import me.ka1.vulcan.*;

public class FriendCommand extends Command
{
    public String[] getAlias() {
        return new String[] { "friend", "friends", "f" };
    }
    
    public String getSyntax() {
        return "friend <add | del> <Name>";
    }
    
    public void onCommand(final String command, final String[] args) throws Exception {
        if (args[0].equalsIgnoreCase("add")) {
            if (Friends.isFriend(args[1])) {
                Command.sendClientMessage(args[1] + ChatFormatting.GRAY + " is already a friend!");
                return;
            }
            if (!Friends.isFriend(args[1])) {
                Vulcan.getInstance().friends.addFriend(args[1]);
                Command.sendClientMessage("Added " + args[1] + " to friends list");
            }
        }
        if (args[0].equalsIgnoreCase("del") || args[0].equalsIgnoreCase("remove")) {
            if (!Friends.isFriend(args[1])) {
                Command.sendClientMessage(args[1] + " is not a friend!");
                return;
            }
            if (Friends.isFriend(args[1])) {
                Vulcan.getInstance().friends.delFriend(args[1]);
                Command.sendClientMessage("Removed " + args[1] + " from friends list");
            }
        }
    }
}
