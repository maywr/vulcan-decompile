//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.macro;

import java.util.*;

public class MacroManager
{
    List<Macro> macros;
    
    public MacroManager() {
        this.macros = new ArrayList<Macro>();
    }
    
    public List<Macro> getMacros() {
        return this.macros;
    }
    
    public Macro getMacroByValue(final String v) {
        final Macro m = this.getMacros().stream().filter(mm -> mm.getValue() == v).findFirst().orElse(null);
        return m;
    }
    
    public Macro getMacroByKey(final int key) {
        final Macro m = this.getMacros().stream().filter(mm -> mm.getKey() == key).findFirst().orElse(null);
        return m;
    }
    
    public void addMacro(final Macro m) {
        this.macros.add(m);
    }
    
    public void delMacro(final Macro m) {
        this.macros.remove(m);
    }
}
