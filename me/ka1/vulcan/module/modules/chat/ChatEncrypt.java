//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.chat;

import me.ka1.vulcan.module.*;
import me.ka1.vulcan.event.events.*;
import me.zero.alpine.listener.*;
import net.minecraft.network.play.client.*;
import me.ka1.vulcan.command.*;
import java.util.function.*;

public class ChatEncrypt extends Module
{
    @EventHandler
    private final Listener<PacketEvent.Send> listener;
    
    public ChatEncrypt() {
        super("ChatEncrypt", "Hide da secrets", Module.Category.Chat);
        String message;
        this.listener = new Listener<PacketEvent.Send>(event -> {
            if (event.getPacket() instanceof CPacketChatMessage) {
                if (!((CPacketChatMessage)event.getPacket()).getMessage().startsWith("/") && !((CPacketChatMessage)event.getPacket()).getMessage().startsWith(Command.getPrefix())) {
                    if (!((CPacketChatMessage)event.getPacket()).getMessage().startsWith("!") && !((CPacketChatMessage)event.getPacket()).getMessage().startsWith(Command.getPrefix())) {
                        if (!((CPacketChatMessage)event.getPacket()).getMessage().startsWith(";") && !((CPacketChatMessage)event.getPacket()).getMessage().startsWith(Command.getPrefix())) {
                            if (!((CPacketChatMessage)event.getPacket()).getMessage().startsWith(".") && !((CPacketChatMessage)event.getPacket()).getMessage().startsWith(Command.getPrefix())) {
                                if (!((CPacketChatMessage)event.getPacket()).getMessage().startsWith(":") && !((CPacketChatMessage)event.getPacket()).getMessage().startsWith(Command.getPrefix())) {
                                    if (!((CPacketChatMessage)event.getPacket()).getMessage().startsWith("-") && !((CPacketChatMessage)event.getPacket()).getMessage().startsWith(Command.getPrefix())) {
                                        message = ((CPacketChatMessage)event.getPacket()).getMessage();
                                        this.encrypt(message);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }, new Predicate[0]);
    }
    
    public void encrypt(final String message) {
        final String encryptedmessage = "aaa " + message;
    }
}
