//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package io.github.classgraph;

import java.util.*;

public class ModuleInfoList extends MappableInfoList<ModuleInfo>
{
    private static final long serialVersionUID = 1L;
    
    ModuleInfoList() {
    }
    
    ModuleInfoList(final int sizeHint) {
        super(sizeHint);
    }
    
    ModuleInfoList(final Collection<ModuleInfo> moduleInfoCollection) {
        super(moduleInfoCollection);
    }
    
    public ModuleInfoList filter(final ModuleInfoFilter filter) {
        final ModuleInfoList moduleInfoFiltered = new ModuleInfoList();
        for (final ModuleInfo resource : this) {
            if (filter.accept(resource)) {
                moduleInfoFiltered.add(resource);
            }
        }
        return moduleInfoFiltered;
    }
    
    @FunctionalInterface
    public interface ModuleInfoFilter
    {
        boolean accept(final ModuleInfo p0);
    }
}
