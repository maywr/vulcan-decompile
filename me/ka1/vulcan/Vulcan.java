//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan;

import net.minecraftforge.fml.common.*;
import me.ka1.vulcan.macro.*;
import me.ka1.vulcan.ClickGui2.*;
import me.ka1.vulcan.util.friend.*;
import me.ka1.vulcan.setting.*;
import me.ka1.vulcan.module.*;
import me.ka1.vulcan.event.*;
import me.ka1.vulcan.util.font.*;
import me.ka1.vulcan.util.enemy.*;
import me.zero.alpine.*;
import java.awt.*;
import me.ka1.vulcan.util.*;
import me.ka1.vulcan.util.config.*;
import me.ka1.vulcan.command.*;
import net.minecraftforge.fml.common.event.*;
import org.lwjgl.opengl.*;
import org.apache.logging.log4j.*;

@Mod(modid = "vulcan", name = "Vulcan Client", version = "0.5")
public class Vulcan
{
    public static final String MOD_ID = "vulcan";
    public static final String MOD_NAME = "Vulcan Client";
    public static final String VERSION = "0.5";
    public static final Logger log;
    public final boolean verified = false;
    public MacroManager macroManager;
    public SaveConfiguration saveConfiguration;
    public static LoadConfiguration loadConfiguration;
    public SaveModules saveModules;
    public static LoadModules loadModules;
    public ClickGUI2 clickGUI;
    public Friends friends;
    public SettingsManager settingsManager;
    public ModuleManager moduleManager;
    EventProcessor eventProcessor;
    public static CFontRenderer fontRenderer;
    public static Enemies enemies;
    private EventManager eventManager;
    public static final EventBus EVENT_BUS;
    @Mod.Instance
    private static Vulcan INSTANCE;
    
    public Vulcan() {
        Vulcan.INSTANCE = this;
    }
    
    public EventManager getEventManager() {
        if (this.eventManager == null) {
            this.eventManager = new EventManager();
        }
        return this.eventManager;
    }
    
    @Mod.EventHandler
    public void preinit(final FMLPreInitializationEvent event) {
    }
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        (this.eventProcessor = new EventProcessor()).init();
        Vulcan.fontRenderer = new CFontRenderer(new Font("Arial", 0, 18), true, false);
        final TpsUtils tpsUtils = new TpsUtils();
        this.settingsManager = new SettingsManager();
        this.friends = new Friends();
        Vulcan.enemies = new Enemies();
        Vulcan.log.info("Friends and enemies initialized!");
        this.moduleManager = new ModuleManager();
        Vulcan.log.info("Settings initialized!");
        Vulcan.log.info("Modules initialized!");
        this.clickGUI = new ClickGUI2();
        Vulcan.log.info("ClickGUI initialized!");
        this.macroManager = new MacroManager();
        Vulcan.log.info("Macros initialized!");
        this.saveConfiguration = new SaveConfiguration();
        Runtime.getRuntime().addShutdownHook(new Stopper());
        Vulcan.log.info("Config Saved!");
        Vulcan.loadConfiguration = new LoadConfiguration();
        Vulcan.log.info("Config Loaded!");
        this.saveModules = new SaveModules();
        Runtime.getRuntime().addShutdownHook(new Stopper());
        Vulcan.log.info("Modules Saved!");
        Vulcan.loadModules = new LoadModules();
        Vulcan.log.info("Modules Loaded!");
        CommandManager.initCommands();
        Vulcan.log.info("Commands initialized!");
        Vulcan.log.info("Initialization complete!\n");
    }
    
    @Mod.EventHandler
    public void postinit(final FMLPostInitializationEvent event) {
        Display.setTitle("Vulcan Client 0.5");
        Vulcan.log.info("PostInitialization of vulcan complete!\n");
    }
    
    public static Vulcan getInstance() {
        return Vulcan.INSTANCE;
    }
    
    static {
        log = LogManager.getLogger("Vulcan Client");
        EVENT_BUS = new EventManager();
    }
}
