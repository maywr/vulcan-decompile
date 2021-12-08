//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package nonapi.io.github.classgraph.classloaderhandler;

import nonapi.io.github.classgraph.classpath.*;
import nonapi.io.github.classgraph.scanspec.*;
import nonapi.io.github.classgraph.utils.*;
import java.io.*;

class OSGiDefaultClassLoaderHandler implements ClassLoaderHandler
{
    private OSGiDefaultClassLoaderHandler() {
    }
    
    public static boolean canHandle(final Class<?> classLoaderClass, final LogNode log) {
        return "org.eclipse.osgi.internal.baseadaptor.DefaultClassLoader".equals(classLoaderClass.getName());
    }
    
    public static void findClassLoaderOrder(final ClassLoader classLoader, final ClassLoaderOrder classLoaderOrder, final LogNode log) {
        classLoaderOrder.delegateTo(classLoader.getParent(), true, log);
        classLoaderOrder.add(classLoader, log);
    }
    
    public static void findClasspathOrder(final ClassLoader classLoader, final ClasspathOrder classpathOrder, final ScanSpec scanSpec, final LogNode log) {
        final Object classpathManager = ReflectionUtils.invokeMethod(classLoader, "getClasspathManager", false);
        final Object[] entries = (Object[])ReflectionUtils.getFieldVal(classpathManager, "entries", false);
        if (entries != null) {
            for (final Object entry : entries) {
                final Object bundleFile = ReflectionUtils.invokeMethod(entry, "getBundleFile", false);
                final File baseFile = (File)ReflectionUtils.invokeMethod(bundleFile, "getBaseFile", false);
                if (baseFile != null) {
                    classpathOrder.addClasspathEntry(baseFile.getPath(), classLoader, scanSpec, log);
                }
            }
        }
    }
}
