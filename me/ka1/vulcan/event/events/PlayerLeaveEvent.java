//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.event.events;

import me.ka1.vulcan.event.*;

public class PlayerLeaveEvent extends Event
{
    private final String name;
    
    public PlayerLeaveEvent(final String n) {
        this.name = n;
    }
    
    public String getName() {
        return this.name;
    }
}
