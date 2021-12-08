//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.event.events;

import me.ka1.vulcan.event.*;
import net.minecraft.network.*;

public class EventNetworkPacketEvent extends MinecraftEvent
{
    public Packet m_Packet;
    
    public EventNetworkPacketEvent(final Packet p_Packet) {
        this.m_Packet = p_Packet;
    }
    
    public Packet GetPacket() {
        return this.m_Packet;
    }
    
    public Packet getPacket() {
        return this.m_Packet;
    }
}
