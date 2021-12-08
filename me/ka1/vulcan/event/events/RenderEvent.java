//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.event.events;

import me.ka1.vulcan.event.*;
import net.minecraft.util.math.*;
import net.minecraft.client.renderer.*;

public class RenderEvent extends Event
{
    private final Tessellator tessellator;
    private final Vec3d renderPos;
    private final float partialTicks;
    
    public RenderEvent(final Tessellator tessellator, final Vec3d renderPos, final float ticks) {
        this.tessellator = tessellator;
        this.renderPos = renderPos;
        this.partialTicks = ticks;
    }
    
    public Tessellator getTessellator() {
        return this.tessellator;
    }
    
    public BufferBuilder getBuffer() {
        return this.tessellator.getBuffer();
    }
    
    public Vec3d getRenderPos() {
        return this.renderPos;
    }
    
    public void setTranslation(final Vec3d translation) {
        this.getBuffer().setTranslation(-translation.x, -translation.y, -translation.z);
    }
    
    public void resetTranslation() {
        this.setTranslation(this.renderPos);
    }
    
    public float getPartialTicks() {
        return this.partialTicks;
    }
}
