//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.util;

import me.ka1.vulcan.event.events.*;
import me.zero.alpine.listener.*;
import net.minecraft.network.play.server.*;
import java.util.function.*;
import java.util.*;
import me.ka1.vulcan.*;
import net.minecraft.util.math.*;

public class TpsUtils
{
    private static final float[] tickRates;
    private int nextIndex;
    private long timeLastTimeUpdate;
    @EventHandler
    Listener<PacketEvent.Receive> listener;
    
    public TpsUtils() {
        this.nextIndex = 0;
        this.listener = new Listener<PacketEvent.Receive>(event -> {
            if (event.getPacket() instanceof SPacketTimeUpdate) {
                this.onTimeUpdate();
            }
            return;
        }, new Predicate[0]);
        this.nextIndex = 0;
        this.timeLastTimeUpdate = -1L;
        Arrays.fill(TpsUtils.tickRates, 0.0f);
        Vulcan.EVENT_BUS.subscribe(this);
    }
    
    public static float getTickRate() {
        float numTicks = 0.0f;
        float sumTickRates = 0.0f;
        for (final float tickRate : TpsUtils.tickRates) {
            if (tickRate > 0.0f) {
                sumTickRates += tickRate;
                ++numTicks;
            }
        }
        return MathHelper.clamp(sumTickRates / numTicks, 0.0f, 20.0f);
    }
    
    private void onTimeUpdate() {
        if (this.timeLastTimeUpdate != -1L) {
            final float timeElapsed = (System.currentTimeMillis() - this.timeLastTimeUpdate) / 1000.0f;
            TpsUtils.tickRates[this.nextIndex % TpsUtils.tickRates.length] = MathHelper.clamp(20.0f / timeElapsed, 0.0f, 20.0f);
            ++this.nextIndex;
        }
        this.timeLastTimeUpdate = System.currentTimeMillis();
    }
    
    static {
        tickRates = new float[20];
    }
}
