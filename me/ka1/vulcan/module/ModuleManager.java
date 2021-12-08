//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module;

import java.util.*;
import me.ka1.vulcan.module.modules.combat.*;
import me.ka1.vulcan.module.modules.movement.*;
import me.ka1.vulcan.module.modules.player.*;
import me.ka1.vulcan.module.modules.misc.*;
import me.ka1.vulcan.module.modules.client.*;
import me.ka1.vulcan.module.modules.Hud.*;
import me.ka1.vulcan.module.modules.render.*;
import me.ka1.vulcan.module.modules.chat.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.*;
import net.minecraft.entity.*;
import me.ka1.vulcan.util.*;
import me.ka1.vulcan.event.events.*;
import net.minecraft.client.renderer.*;
import net.minecraft.util.math.*;
import java.util.stream.*;

public class ModuleManager
{
    public static ArrayList<Module> modules;
    
    public ModuleManager() {
        ModuleManager.modules = new ArrayList<Module>();
        addMod(new AutoCrystal());
        addMod(new AutoMend());
        addMod(new AutoTotem());
        addMod(new Burrow());
        addMod(new BedAura());
        addMod(new EasyPearl());
        addMod(new Surround());
        addMod(new KillAura());
        addMod(new MoonWalk());
        addMod(new ReverseStep());
        addMod(new Step());
        addMod(new Sprint());
        addMod(new FastCrystal());
        addMod(new FastEXP());
        addMod(new Suicide());
        addMod(new MountBypass());
        addMod(new Xcarry());
        addMod(new RageQuit());
        addMod(new FakePlayer());
        addMod(new ArmorNotification());
        addMod(new LoadConfig());
        addMod(new FpsLimiter());
        addMod(new DiscordRPC());
        addMod(new ClickGuiModule());
        addMod(new CustomFont());
        addMod(new Rainbow());
        addMod(new CommandColor());
        addMod(new Inventory());
        addMod(new me.ka1.vulcan.module.modules.Hud.ArrayList());
        addMod(new Time());
        addMod(new Welcomer());
        addMod(new Coordinates());
        addMod(new Watermark());
        addMod(new CombatInfo());
        addMod(new TPS());
        addMod(new FPS());
        addMod(new ping());
        addMod(new HudEditor());
        addMod(new willy());
        addMod(new Tracers());
        addMod(new TextRadar());
        addMod(new BlockHighlight());
        addMod(new Logo());
        addMod(new LowHands());
        addMod(new Esp());
        addMod(new Nametags());
        addMod(new Fullbright());
        addMod(new Zoom());
        addMod(new FOVSlider());
        addMod(new ChatSuffix());
        addMod(new ChatEncrypt());
    }
    
    public static void addMod(final Module m) {
        ModuleManager.modules.add(m);
    }
    
    public static void onUpdate() {
        ModuleManager.modules.stream().filter(Module::isEnabled).forEach(Module::onUpdate);
    }
    
    public static void onRender() {
        ModuleManager.modules.stream().filter(Module::isEnabled).forEach(Module::onRender);
    }
    
    public static void onWorldRender(final RenderWorldLastEvent event) {
        Minecraft.getMinecraft().profiler.startSection("Vulcan");
        Minecraft.getMinecraft().profiler.startSection("setup");
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        GlStateManager.disableDepth();
        GlStateManager.glLineWidth(1.0f);
        final Vec3d renderPos = getInterpolatedPos((Entity)Minecraft.getMinecraft().player, event.getPartialTicks());
        final RenderEvent e = new RenderEvent((Tessellator)RenderUtil.INSTANCE, renderPos, event.getPartialTicks());
        e.resetTranslation();
        Minecraft.getMinecraft().profiler.endSection();
        final RenderEvent renderEvent;
        ModuleManager.modules.stream().filter(module -> module.isEnabled()).forEach(module -> {
            Minecraft.getMinecraft().profiler.startSection(module.getName());
            module.onWorldRender(renderEvent);
            Minecraft.getMinecraft().profiler.endSection();
            return;
        });
        Minecraft.getMinecraft().profiler.startSection("release");
        GlStateManager.glLineWidth(1.0f);
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.enableCull();
        RenderUtil.releaseGL();
        Minecraft.getMinecraft().profiler.endSection();
        Minecraft.getMinecraft().profiler.endSection();
    }
    
    public static ArrayList<Module> getModules() {
        return ModuleManager.modules;
    }
    
    public static ArrayList<Module> getModulesInCategory(final Module.Category c) {
        final ArrayList<Module> list = getModules().stream().filter(m -> m.getCategory().equals((Object)c)).collect((Collector<? super Object, ?, ArrayList<Module>>)Collectors.toList());
        return list;
    }
    
    public static void onBind(final int key) {
        if (key == 0 || key == 0) {
            return;
        }
        ModuleManager.modules.forEach(module -> {
            if (module.getBind() == key) {
                module.toggle();
            }
        });
    }
    
    public static Module getModuleByName(final String name) {
        final Module m = getModules().stream().filter(mm -> mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        return m;
    }
    
    public static boolean isModuleEnabled(final String name) {
        final Module m = getModules().stream().filter(mm -> mm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        return m.isEnabled();
    }
    
    public static boolean isModuleEnabled(final Module m) {
        return m.isEnabled();
    }
    
    public static Vec3d getInterpolatedPos(final Entity entity, final float ticks) {
        return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(getInterpolatedAmount(entity, ticks));
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final double ticks) {
        return getInterpolatedAmount(entity, ticks, ticks, ticks);
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final double x, final double y, final double z) {
        return new Vec3d((entity.posX - entity.lastTickPosX) * x, (entity.posY - entity.lastTickPosY) * y, (entity.posZ - entity.lastTickPosZ) * z);
    }
}
