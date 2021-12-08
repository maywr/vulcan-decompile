//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.Hud;

import me.ka1.vulcan.setting.*;
import me.ka1.vulcan.module.*;
import me.ka1.vulcan.*;
import com.google.common.collect.*;
import java.awt.*;
import java.util.*;

public class ArrayList extends Module
{
    public List<Module> enabledModules;
    private int moduleCount;
    Setting.Boolean dock;
    Setting.Integer x;
    Setting.Integer y;
    
    public ArrayList() {
        super("ArrayList", "Displays a list of enabled modules", Module.Category.Hud);
        this.enabledModules = new java.util.ArrayList<Module>();
    }
    
    public void setup() {
        this.dock = this.registerBoolean("Dock", "dock", true);
        this.x = this.registerInteger("X", "arrayListx", 1, 1, 1280);
        this.y = this.registerInteger("Y", "arrayListy", 1, 1, 960);
    }
    
    public void onRender() {
        if (ArrayList.mc.player == null || ArrayList.mc.world == null) {
            return;
        }
        this.enabledModules.clear();
        for (final Module module2 : ModuleManager.getModules()) {
            if (ModuleManager.isModuleEnabled(module2) && module2.isDrawn() && module2.getCategory() != Module.Category.Client && module2.getCategory() != Module.Category.Hud) {
                this.enabledModules.add(module2);
            }
        }
        this.enabledModules.sort(Comparator.comparing(module -> Vulcan.fontRenderer.getStringWidth(module.getName() + module.getHudInfo())));
        this.enabledModules = (List<Module>)Lists.reverse((List)this.enabledModules);
        this.moduleCount = 0;
        for (final Module module2 : this.enabledModules) {
            if (this.x.getValue() <= 640) {
                if (this.y.getValue() <= 250) {
                    Vulcan.fontRenderer.drawStringWithShadow(module2.getName() + " " + module2.getHudInfo(), this.x.getValue(), this.y.getValue() + this.moduleCount * 10, new Color(255, 255, 255).getRGB());
                }
                else {
                    Vulcan.fontRenderer.drawStringWithShadow(module2.getName() + " " + module2.getHudInfo(), this.x.getValue(), this.y.getValue() - this.moduleCount * 10, new Color(255, 255, 255).getRGB());
                }
            }
            else if (this.x.getValue() > 640) {
                if (this.y.getValue() < 250) {
                    Vulcan.fontRenderer.drawStringWithShadow(module2.getName() + " " + module2.getHudInfo(), this.x.getValue() - Vulcan.fontRenderer.getStringWidth(module2.getName() + module2.getHudInfo()), this.y.getValue() + this.moduleCount * 10, new Color(255, 255, 255).getRGB());
                }
                else {
                    Vulcan.fontRenderer.drawStringWithShadow(module2.getName() + " " + module2.getHudInfo(), this.x.getValue() - Vulcan.fontRenderer.getStringWidth(module2.getName() + module2.getHudInfo()), this.y.getValue() - this.moduleCount * 10, new Color(255, 255, 255).getRGB());
                }
            }
            ++this.moduleCount;
        }
    }
}
