//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package io.github.classgraph;

import java.util.*;

public class InfoList<T extends HasName> extends PotentiallyUnmodifiableList<T>
{
    static final long serialVersionUID = 1L;
    
    InfoList() {
    }
    
    InfoList(final int sizeHint) {
        super(sizeHint);
    }
    
    InfoList(final Collection<T> infoCollection) {
        super(infoCollection);
    }
    
    @Override
    public boolean equals(final Object o) {
        return super.equals(o);
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    public List<String> getNames() {
        if (this.isEmpty()) {
            return Collections.emptyList();
        }
        final List<String> names = new ArrayList<String>(this.size());
        for (final T i : this) {
            if (i != null) {
                names.add(i.getName());
            }
        }
        return names;
    }
    
    public List<String> getAsStrings() {
        if (this.isEmpty()) {
            return Collections.emptyList();
        }
        final List<String> toStringVals = new ArrayList<String>(this.size());
        for (final T i : this) {
            toStringVals.add((i == null) ? "null" : i.toString());
        }
        return toStringVals;
    }
}
