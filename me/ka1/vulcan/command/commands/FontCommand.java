//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.command.commands;

import me.ka1.vulcan.command.*;
import java.awt.*;
import me.ka1.vulcan.util.font.*;
import me.ka1.vulcan.*;

public class FontCommand extends Command
{
    public String[] getAlias() {
        return new String[] { "font", "setfont" };
    }
    
    public String getSyntax() {
        return "font <Name> <Size>";
    }
    
    public void onCommand(final String command, final String[] args) throws Exception {
        final String font = args[0].replace("_", " ");
        final int size = Integer.parseInt(args[1]);
        (Vulcan.fontRenderer = new CFontRenderer(new Font(font, 0, size), true, false)).setFontName(font);
        Vulcan.fontRenderer.setFontSize(size);
    }
}
