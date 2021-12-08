//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package nonapi.io.github.classgraph.classloaderhandler;

import nonapi.io.github.classgraph.classpath.*;
import nonapi.io.github.classgraph.scanspec.*;
import nonapi.io.github.classgraph.utils.*;

class WebsphereTraditionalClassLoaderHandler implements ClassLoaderHandler
{
    private WebsphereTraditionalClassLoaderHandler() {
    }
    
    public static boolean canHandle(final Class<?> classLoaderClass, final LogNode log) {
        return "com.ibm.ws.classloader.CompoundClassLoader".equals(classLoaderClass.getName()) || "com.ibm.ws.classloader.ProtectionClassLoader".equals(classLoaderClass.getName()) || "com.ibm.ws.bootstrap.ExtClassLoader".equals(classLoaderClass.getName());
    }
    
    public static void findClassLoaderOrder(final ClassLoader classLoader, final ClassLoaderOrder classLoaderOrder, final LogNode log) {
        classLoaderOrder.delegateTo(classLoader.getParent(), true, log);
        classLoaderOrder.add(classLoader, log);
    }
    
    public static void findClasspathOrder(final ClassLoader classLoader, final ClasspathOrder classpathOrder, final ScanSpec scanSpec, final LogNode log) {
        final String classpath = (String)ReflectionUtils.invokeMethod(classLoader, "getClassPath", false);
        classpathOrder.addClasspathPathStr(classpath, classLoader, scanSpec, log);
    }
}
