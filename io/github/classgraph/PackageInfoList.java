//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package io.github.classgraph;

import java.util.*;

public class PackageInfoList extends MappableInfoList<PackageInfo>
{
    private static final long serialVersionUID = 1L;
    static final PackageInfoList EMPTY_LIST;
    
    PackageInfoList() {
    }
    
    PackageInfoList(final int sizeHint) {
        super(sizeHint);
    }
    
    PackageInfoList(final Collection<PackageInfo> packageInfoCollection) {
        super(packageInfoCollection);
    }
    
    public PackageInfoList filter(final PackageInfoFilter filter) {
        final PackageInfoList packageInfoFiltered = new PackageInfoList();
        for (final PackageInfo resource : this) {
            if (filter.accept(resource)) {
                packageInfoFiltered.add(resource);
            }
        }
        return packageInfoFiltered;
    }
    
    static {
        EMPTY_LIST = new PackageInfoList() {
            private static final long serialVersionUID = 1L;
            
            public boolean add(final PackageInfo e) {
                throw new IllegalArgumentException("List is immutable");
            }
            
            public void add(final int index, final PackageInfo element) {
                throw new IllegalArgumentException("List is immutable");
            }
            
            public boolean remove(final Object o) {
                throw new IllegalArgumentException("List is immutable");
            }
            
            public PackageInfo remove(final int index) {
                throw new IllegalArgumentException("List is immutable");
            }
            
            public boolean addAll(final Collection<? extends PackageInfo> c) {
                throw new IllegalArgumentException("List is immutable");
            }
            
            public boolean addAll(final int index, final Collection<? extends PackageInfo> c) {
                throw new IllegalArgumentException("List is immutable");
            }
            
            public boolean removeAll(final Collection<?> c) {
                throw new IllegalArgumentException("List is immutable");
            }
            
            public boolean retainAll(final Collection<?> c) {
                throw new IllegalArgumentException("List is immutable");
            }
            
            public void clear() {
                throw new IllegalArgumentException("List is immutable");
            }
            
            public PackageInfo set(final int index, final PackageInfo element) {
                throw new IllegalArgumentException("List is immutable");
            }
        };
    }
    
    @FunctionalInterface
    public interface PackageInfoFilter
    {
        boolean accept(final PackageInfo p0);
    }
}
