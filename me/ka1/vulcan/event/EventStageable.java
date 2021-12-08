//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.event;

public class EventStageable
{
    private EventStage stage;
    
    public EventStageable() {
    }
    
    public EventStageable(final EventStage stage) {
        this.stage = stage;
    }
    
    public EventStage getStage() {
        return this.stage;
    }
    
    public enum EventStage
    {
        PRE, 
        POST
    }
}
