//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.event;

import net.minecraft.client.*;
import java.awt.*;
import me.zero.alpine.listener.*;
import net.minecraft.network.play.server.*;
import me.ka1.vulcan.*;
import me.ka1.vulcan.event.events.*;
import java.util.function.*;
import com.google.common.collect.*;
import java.util.*;
import me.ka1.vulcan.module.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.lwjgl.input.*;
import me.ka1.vulcan.command.*;
import com.mojang.realmsclient.gui.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.event.world.*;
import java.net.*;
import org.apache.commons.io.*;
import org.json.simple.*;
import java.io.*;
import org.json.simple.parser.*;
import net.minecraftforge.common.*;
import me.ka1.vulcan.macro.*;

public class EventProcessor
{
    public static EventProcessor INSTANCE;
    Minecraft mc;
    CommandManager commandManager;
    float hue;
    Color c;
    int rgb;
    int speed;
    @EventHandler
    private final Listener<PacketEvent.Receive> receiveListener;
    private final Map<String, String> uuidNameCache;
    
    public EventProcessor() {
        this.mc = Minecraft.getMinecraft();
        this.commandManager = new CommandManager();
        this.hue = 0.0f;
        this.speed = 2;
        SPacketPlayerListItem packet;
        final Iterator<SPacketPlayerListItem.AddPlayerData> iterator;
        SPacketPlayerListItem.AddPlayerData playerData;
        String name;
        final Iterator<SPacketPlayerListItem.AddPlayerData> iterator2;
        SPacketPlayerListItem.AddPlayerData playerData2;
        String name2;
        this.receiveListener = new Listener<PacketEvent.Receive>(event -> {
            if (event.getPacket() instanceof SPacketPlayerListItem) {
                packet = (SPacketPlayerListItem)event.getPacket();
                if (packet.getAction() == SPacketPlayerListItem.Action.ADD_PLAYER) {
                    packet.getEntries().iterator();
                    while (iterator.hasNext()) {
                        playerData = iterator.next();
                        if (playerData.getProfile().getId() != this.mc.session.getProfile().getId()) {
                            new Thread(() -> {
                                name = this.resolveName(playerData.getProfile().getId().toString());
                                if (name != null && this.mc.player != null && this.mc.player.ticksExisted >= 1000) {
                                    Vulcan.EVENT_BUS.post(new PlayerJoinEvent(name));
                                }
                                return;
                            }).start();
                        }
                    }
                }
                if (packet.getAction() == SPacketPlayerListItem.Action.REMOVE_PLAYER) {
                    packet.getEntries().iterator();
                    while (iterator2.hasNext()) {
                        playerData2 = iterator2.next();
                        if (playerData2.getProfile().getId() != this.mc.session.getProfile().getId()) {
                            new Thread(() -> {
                                name2 = this.resolveName(playerData2.getProfile().getId().toString());
                                if (name2 != null && this.mc.player != null && this.mc.player.ticksExisted >= 1000) {
                                    Vulcan.EVENT_BUS.post(new PlayerLeaveEvent(name2));
                                }
                            }).start();
                        }
                    }
                }
            }
            return;
        }, new Predicate[0]);
        this.uuidNameCache = (Map<String, String>)Maps.newConcurrentMap();
        EventProcessor.INSTANCE = this;
    }
    
    public int getRgb() {
        return this.rgb;
    }
    
    public Color getC() {
        return this.c;
    }
    
    public void setRainbowSpeed(final int s) {
        this.speed = s;
    }
    
    public int getRainbowSpeed() {
        return this.speed;
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
        final float[] hue = { System.currentTimeMillis() % 11520L / 11520.0f };
        final int rgb = Color.HSBtoRGB(hue[0], 1.0f, 1.0f);
        final int red = rgb >> 16 & 0xFF;
        final int green = rgb >> 8 & 0xFF;
        final int blue = rgb & 0xFF;
        final float[] array = hue;
        final int n = 0;
        array[n] += 0.02f;
        this.c = new Color(red, green, blue);
        if (this.mc.player != null) {
            ModuleManager.onUpdate();
        }
    }
    
    @SubscribeEvent
    public void onWorldRender(final RenderWorldLastEvent event) {
        if (event.isCanceled()) {
            return;
        }
        ModuleManager.onWorldRender(event);
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Post event) {
        Vulcan.EVENT_BUS.post(event);
        if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            ModuleManager.onRender();
        }
    }
    
    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onKeyInput(final InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState()) {
            if (Keyboard.getEventKey() == 0 || Keyboard.getEventKey() == 0) {
                return;
            }
            ModuleManager.onBind(Keyboard.getEventKey());
            Vulcan.getInstance().macroManager.getMacros().forEach(m -> {
                if (m.getKey() == Keyboard.getEventKey()) {
                    m.onMacro();
                }
            });
        }
    }
    
    @SubscribeEvent
    public void onMouseInput(final InputEvent.MouseInputEvent event) {
        if (Mouse.getEventButtonState()) {
            Vulcan.EVENT_BUS.post(event);
        }
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onChatSent(final ClientChatEvent event) {
        if (event.getMessage().startsWith(Command.getPrefix())) {
            event.setCanceled(true);
            try {
                this.mc.ingameGUI.getChatGUI().addToSentMessages(event.getMessage());
                this.commandManager.callCommand(event.getMessage().substring(1));
            }
            catch (Exception e) {
                e.printStackTrace();
                Command.sendClientMessage(ChatFormatting.DARK_RED + "Error: " + e.getMessage());
            }
        }
    }
    
    @SubscribeEvent
    public void onRenderScreen(final RenderGameOverlayEvent.Text event) {
        Vulcan.EVENT_BUS.post(event);
    }
    
    @SubscribeEvent
    public void onChatReceived(final ClientChatReceivedEvent event) {
        Vulcan.EVENT_BUS.post(event);
    }
    
    @SubscribeEvent
    public void onAttackEntity(final AttackEntityEvent event) {
        Vulcan.EVENT_BUS.post(event);
    }
    
    @SubscribeEvent
    public void onPlayerRespawn(final PlayerEvent.PlayerRespawnEvent event) {
        Vulcan.EVENT_BUS.post(event);
    }
    
    @SubscribeEvent
    public void onDrawBlockHighlight(final DrawBlockHighlightEvent event) {
        Vulcan.EVENT_BUS.post(event);
    }
    
    @SubscribeEvent
    public void onRenderBlockOverlay(final RenderBlockOverlayEvent event) {
        Vulcan.EVENT_BUS.post(event);
    }
    
    @SubscribeEvent
    public void onLivingDamage(final LivingDamageEvent event) {
        Vulcan.EVENT_BUS.post(event);
    }
    
    @SubscribeEvent
    public void onLivingEntityUseItemFinish(final LivingEntityUseItemEvent.Finish event) {
        Vulcan.EVENT_BUS.post(event);
    }
    
    @SubscribeEvent
    public void onInputUpdate(final InputUpdateEvent event) {
        Vulcan.EVENT_BUS.post(event);
    }
    
    @SubscribeEvent
    public void onLivingDeath(final LivingDeathEvent event) {
        Vulcan.EVENT_BUS.post(event);
    }
    
    @SubscribeEvent
    public void onPlayerPush(final PlayerSPPushOutOfBlocksEvent event) {
        Vulcan.EVENT_BUS.post(event);
    }
    
    @SubscribeEvent
    public void onWorldUnload(final WorldEvent.Unload event) {
        Vulcan.EVENT_BUS.post(event);
    }
    
    @SubscribeEvent
    public void onWorldLoad(final WorldEvent.Load event) {
        Vulcan.EVENT_BUS.post(event);
    }
    
    public String resolveName(String uuid) {
        uuid = uuid.replace("-", "");
        if (this.uuidNameCache.containsKey(uuid)) {
            return this.uuidNameCache.get(uuid);
        }
        final String url = "https://api.mojang.com/user/profiles/" + uuid + "/names";
        try {
            final String nameJson = IOUtils.toString(new URL(url));
            if (nameJson != null && nameJson.length() > 0) {
                final JSONArray jsonArray = (JSONArray)JSONValue.parseWithException(nameJson);
                if (jsonArray != null) {
                    final JSONObject latestName = (JSONObject)jsonArray.get(jsonArray.size() - 1);
                    if (latestName != null) {
                        return latestName.get("name").toString();
                    }
                }
            }
        }
        catch (IOException | ParseException ex2) {
            final Exception ex;
            final Exception e = ex;
            e.printStackTrace();
        }
        return null;
    }
    
    public void init() {
        Vulcan.EVENT_BUS.subscribe(this);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
}
