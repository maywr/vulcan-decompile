//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.event;

public class EventCancellable extends EventStageable
{
    private boolean canceled;
    
    public EventCancellable() {
    }
    
    public EventCancellable(final EventStage stage) {
        super(stage);
    }
    
    public boolean isCanceled() {
        return this.canceled;
    }
    
    public void setCanceled(final boolean canceled) {
        this.canceled = canceled;
    }
}
