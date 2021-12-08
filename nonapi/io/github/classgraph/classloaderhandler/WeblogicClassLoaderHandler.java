//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package nonapi.io.github.classgraph.classloaderhandler;

import nonapi.io.github.classgraph.classpath.*;
import nonapi.io.github.classgraph.scanspec.*;
import nonapi.io.github.classgraph.utils.*;

class WeblogicClassLoaderHandler implements ClassLoaderHandler
{
    private WeblogicClassLoaderHandler() {
    }
    
    public static boolean canHandle(final Class<?> classLoaderClass, final LogNode log) {
        return "weblogic.utils.classloaders.ChangeAwareClassLoader".equals(classLoaderClass.getName()) || "weblogic.utils.classloaders.GenericClassLoader".equals(classLoaderClass.getName()) || "weblogic.utils.classloaders.FilteringClassLoader".equals(classLoaderClass.getName()) || "weblogic.servlet.jsp.JspClassLoader".equals(classLoaderClass.getName()) || "weblogic.servlet.jsp.TagFileClassLoader".equals(classLoaderClass.getName());
    }
    
    public static void findClassLoaderOrder(final ClassLoader classLoader, final ClassLoaderOrder classLoaderOrder, final LogNode log) {
        classLoaderOrder.delegateTo(classLoader.getParent(), true, log);
        classLoaderOrder.add(classLoader, log);
    }
    
    public static void findClasspathOrder(final ClassLoader classLoader, final ClasspathOrder classpathOrder, final ScanSpec scanSpec, final LogNode log) {
        classpathOrder.addClasspathPathStr((String)ReflectionUtils.invokeMethod(classLoader, "getFinderClassPath", false), classLoader, scanSpec, log);
        classpathOrder.addClasspathPathStr((String)ReflectionUtils.invokeMethod(classLoader, "getClassPath", false), classLoader, scanSpec, log);
    }
}
