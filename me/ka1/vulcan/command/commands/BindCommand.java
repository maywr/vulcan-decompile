//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.command.commands;

import me.ka1.vulcan.command.*;
import org.lwjgl.input.*;
import me.ka1.vulcan.module.*;

public class BindCommand extends Command
{
    public String[] getAlias() {
        return new String[] { "bind", "b" };
    }
    
    public String getSyntax() {
        return "bind <Module> <Key>";
    }
    
    public void onCommand(final String command, final String[] args) throws Exception {
        final int key = Keyboard.getKeyIndex(args[1].toUpperCase());
        final int bind;
        ModuleManager.getModules().forEach(m -> {
            if (args[0].equalsIgnoreCase(m.getName())) {
                m.setBind(bind);
                Command.sendClientMessage(args[0] + " bound to " + args[1].toUpperCase());
            }
        });
    }
}
