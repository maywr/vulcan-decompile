//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package nonapi.io.github.classgraph.classloaderhandler;

import nonapi.io.github.classgraph.classpath.*;
import nonapi.io.github.classgraph.scanspec.*;
import nonapi.io.github.classgraph.utils.*;

class UnoOneJarClassLoaderHandler implements ClassLoaderHandler
{
    private UnoOneJarClassLoaderHandler() {
    }
    
    public static boolean canHandle(final Class<?> classLoaderClass, final LogNode log) {
        return "com.needhamsoftware.unojar.JarClassLoader".equals(classLoaderClass.getName()) || "com.simontuffs.onejar.JarClassLoader".equals(classLoaderClass.getName());
    }
    
    public static void findClassLoaderOrder(final ClassLoader classLoader, final ClassLoaderOrder classLoaderOrder, final LogNode log) {
        classLoaderOrder.delegateTo(classLoader.getParent(), true, log);
        classLoaderOrder.add(classLoader, log);
    }
    
    public static void findClasspathOrder(final ClassLoader classLoader, final ClasspathOrder classpathOrder, final ScanSpec scanSpec, final LogNode log) {
        final String unoJarOneJarPath = (String)ReflectionUtils.invokeMethod(classLoader, "getOneJarPath", false);
        classpathOrder.addClasspathEntry(unoJarOneJarPath, classLoader, scanSpec, log);
        final String unoJarJarPath = System.getProperty("uno-jar.jar.path");
        classpathOrder.addClasspathEntry(unoJarJarPath, classLoader, scanSpec, log);
        final String oneJarJarPath = System.getProperty("one-jar.jar.path");
        classpathOrder.addClasspathEntry(oneJarJarPath, classLoader, scanSpec, log);
        final String oneJarClassPath = System.getProperty("one-jar.class.path");
        if (oneJarClassPath != null) {
            classpathOrder.addClasspathEntryObject(oneJarClassPath.split("\\|"), classLoader, scanSpec, log);
        }
    }
}
