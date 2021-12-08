//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package nonapi.io.github.classgraph.classloaderhandler;

import nonapi.io.github.classgraph.utils.*;
import nonapi.io.github.classgraph.classpath.*;
import nonapi.io.github.classgraph.scanspec.*;
import java.net.*;

class URLClassLoaderHandler implements ClassLoaderHandler
{
    private URLClassLoaderHandler() {
    }
    
    public static boolean canHandle(final Class<?> classLoaderClass, final LogNode log) {
        return "java.net.URLClassLoader".equals(classLoaderClass.getName());
    }
    
    public static void findClassLoaderOrder(final ClassLoader classLoader, final ClassLoaderOrder classLoaderOrder, final LogNode log) {
        classLoaderOrder.delegateTo(classLoader.getParent(), true, log);
        classLoaderOrder.add(classLoader, log);
    }
    
    public static void findClasspathOrder(final ClassLoader classLoader, final ClasspathOrder classpathOrder, final ScanSpec scanSpec, final LogNode log) {
        final URL[] urls = ((URLClassLoader)classLoader).getURLs();
        if (urls != null) {
            for (final URL url : urls) {
                if (url != null) {
                    classpathOrder.addClasspathEntry(url, classLoader, scanSpec, log);
                }
            }
        }
    }
}
