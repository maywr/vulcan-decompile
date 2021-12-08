//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.player;

import me.ka1.vulcan.module.*;
import me.ka1.vulcan.event.events.*;
import me.zero.alpine.listener.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.passive.*;
import net.minecraft.world.*;
import java.util.function.*;

public class MountBypass extends Module
{
    @EventHandler
    private final Listener<EventNetworkPacketEvent> onPacketEventSend;
    
    public MountBypass() {
        super("MountBypass", "Forcefully mounts chests on entities", Module.Category.Player);
        CPacketUseEntity packet;
        this.onPacketEventSend = new Listener<EventNetworkPacketEvent>(event -> {
            if (event.getPacket() instanceof CPacketUseEntity) {
                packet = (CPacketUseEntity)event.getPacket();
                if (packet.getEntityFromWorld((World)MountBypass.mc.world) instanceof AbstractChestHorse && packet.getAction() == CPacketUseEntity.Action.INTERACT_AT) {
                    event.cancel();
                }
            }
        }, new Predicate[0]);
    }
}
