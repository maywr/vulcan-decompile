//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.setting;

import me.ka1.vulcan.module.*;
import java.util.stream.*;
import java.util.*;

public class SettingsManager
{
    private final List<Setting> settings;
    
    public SettingsManager() {
        this.settings = new ArrayList<Setting>();
    }
    
    public List<Setting> getSettings() {
        return this.settings;
    }
    
    public void addSetting(final Setting setting) {
        this.settings.add(setting);
    }
    
    public Setting getSettingByNameAndMod(final String name, final Module parent) {
        return this.settings.stream().filter(s -> s.getParent().equals(parent)).filter(s -> s.getConfigName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
    
    public Setting getSettingByNameAndModConfig(final String configname, final Module parent) {
        return this.settings.stream().filter(s -> s.getParent().equals(parent)).filter(s -> s.getConfigName().equalsIgnoreCase(configname)).findFirst().orElse(null);
    }
    
    public List<Setting> getSettingsForMod(final Module parent) {
        return this.settings.stream().filter(s -> s.getParent().equals(parent)).collect((Collector<? super Object, ?, List<Setting>>)Collectors.toList());
    }
    
    public List<Setting> getSettingsByCategory(final Module.Category category) {
        return this.settings.stream().filter(s -> s.getCategory().equals(category)).collect((Collector<? super Object, ?, List<Setting>>)Collectors.toList());
    }
    
    public Setting getSettingByName(final String name) {
        for (final Setting set : this.getSettings()) {
            if (set.getName().equalsIgnoreCase(name)) {
                return set;
            }
        }
        System.err.println("[Vulcan] Error Setting NOT found: '" + name + "'!");
        return null;
    }
}
