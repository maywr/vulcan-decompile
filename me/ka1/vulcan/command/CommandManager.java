//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.command;

import java.util.*;
import me.ka1.vulcan.command.commands.*;
import com.mojang.realmsclient.gui.*;

public class CommandManager
{
    private static ArrayList<Command> commands;
    boolean b;
    
    public static void initCommands() {
        CommandManager.commands = new ArrayList<Command>();
        addCommand(new ConfigCommand());
        addCommand(new BindCommand());
        addCommand(new FontCommand());
        addCommand(new FriendCommand());
        addCommand(new EnemyCommand());
    }
    
    public static void addCommand(final Command c) {
        CommandManager.commands.add(c);
    }
    
    public static ArrayList<Command> getCommands() {
        return CommandManager.commands;
    }
    
    public void callCommand(final String input) {
        final String[] split = input.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        final String command = split[0];
        final String args = input.substring(command.length()).trim();
        this.b = false;
        final String[] array;
        int length;
        int i = 0;
        String s;
        final String anotherString;
        final String s2;
        CommandManager.commands.forEach(c -> {
            c.getAlias();
            for (length = array.length; i < length; ++i) {
                s = array[i];
                if (s.equalsIgnoreCase(anotherString)) {
                    this.b = true;
                    try {
                        c.onCommand(s2, s2.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"));
                    }
                    catch (Exception e) {
                        Command.sendClientMessage(ChatFormatting.GRAY + c.getSyntax());
                    }
                }
            }
            return;
        });
        if (!this.b) {
            Command.sendClientMessage(ChatFormatting.GRAY + "Unknown command!");
        }
    }
}
