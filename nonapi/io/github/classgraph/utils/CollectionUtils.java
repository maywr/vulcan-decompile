//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package nonapi.io.github.classgraph.utils;

import java.util.*;

public final class CollectionUtils
{
    private CollectionUtils() {
    }
    
    public static <T extends Comparable<? super T>> void sortIfNotEmpty(final List<T> list) {
        if (!list.isEmpty()) {
            Collections.sort(list);
        }
    }
    
    public static <T> void sortIfNotEmpty(final List<T> list, final Comparator<? super T> comparator) {
        if (!list.isEmpty()) {
            Collections.sort(list, comparator);
        }
    }
}
