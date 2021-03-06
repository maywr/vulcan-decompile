//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package nonapi.io.github.classgraph.classloaderhandler;

import nonapi.io.github.classgraph.utils.*;
import java.util.*;
import nonapi.io.github.classgraph.classpath.*;
import nonapi.io.github.classgraph.scanspec.*;

class PlexusClassWorldsClassRealmClassLoaderHandler implements ClassLoaderHandler
{
    private PlexusClassWorldsClassRealmClassLoaderHandler() {
    }
    
    public static boolean canHandle(final Class<?> classLoaderClass, final LogNode log) {
        return "org.codehaus.plexus.classworlds.realm.ClassRealm".equals(classLoaderClass.getName());
    }
    
    private static boolean isParentFirstStrategy(final ClassLoader classRealmInstance) {
        final Object strategy = ReflectionUtils.getFieldVal(classRealmInstance, "strategy", false);
        if (strategy != null) {
            final String strategyClassName = strategy.getClass().getName();
            if (strategyClassName.equals("org.codehaus.plexus.classworlds.strategy.SelfFirstStrategy") || strategyClassName.equals("org.codehaus.plexus.classworlds.strategy.OsgiBundleStrategy")) {
                return false;
            }
        }
        return true;
    }
    
    public static void findClassLoaderOrder(final ClassLoader classRealm, final ClassLoaderOrder classLoaderOrder, final LogNode log) {
        final Object foreignImports = ReflectionUtils.getFieldVal(classRealm, "foreignImports", false);
        if (foreignImports != null) {
            final SortedSet<Object> foreignImportEntries = (SortedSet<Object>)foreignImports;
            for (final Object entry : foreignImportEntries) {
                final ClassLoader foreignImportClassLoader = (ClassLoader)ReflectionUtils.invokeMethod(entry, "getClassLoader", false);
                classLoaderOrder.delegateTo(foreignImportClassLoader, true, log);
            }
        }
        final boolean isParentFirst = isParentFirstStrategy(classRealm);
        if (!isParentFirst) {
            classLoaderOrder.add(classRealm, log);
        }
        final ClassLoader parentClassLoader = (ClassLoader)ReflectionUtils.invokeMethod(classRealm, "getParentClassLoader", false);
        classLoaderOrder.delegateTo(parentClassLoader, true, log);
        classLoaderOrder.delegateTo(classRealm.getParent(), true, log);
        if (isParentFirst) {
            classLoaderOrder.add(classRealm, log);
        }
    }
    
    public static void findClasspathOrder(final ClassLoader classLoader, final ClasspathOrder classpathOrder, final ScanSpec scanSpec, final LogNode log) {
        URLClassLoaderHandler.findClasspathOrder(classLoader, classpathOrder, scanSpec, log);
    }
}
