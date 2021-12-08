//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.player;

import me.ka1.vulcan.module.*;
import me.ka1.vulcan.event.events.*;
import me.zero.alpine.listener.*;
import net.minecraft.network.play.client.*;
import java.util.function.*;
import me.ka1.vulcan.*;

public class Xcarry extends Module
{
    @EventHandler
    private final Listener<PacketEvent.Send> listener;
    
    public Xcarry() {
        super("XCarry", "Lets you store items in crafting slots", Module.Category.Player);
        this.listener = new Listener<PacketEvent.Send>(event -> {
            if (event.getPacket() instanceof CPacketCloseWindow) {
                event.cancel();
            }
        }, new Predicate[0]);
    }
    
    public void onEnable() {
        Vulcan.EVENT_BUS.subscribe(this);
    }
    
    public void onDisable() {
        Vulcan.EVENT_BUS.unsubscribe(this);
    }
}
