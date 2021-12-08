//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package nonapi.io.github.classgraph.classloaderhandler;

import java.util.*;
import java.lang.reflect.*;
import nonapi.io.github.classgraph.utils.*;
import nonapi.io.github.classgraph.classpath.*;
import nonapi.io.github.classgraph.scanspec.*;

public class ClassLoaderHandlerRegistry
{
    public static final List<ClassLoaderHandlerRegistryEntry> CLASS_LOADER_HANDLERS;
    public static final ClassLoaderHandlerRegistryEntry FALLBACK_HANDLER;
    public static final String[] AUTOMATIC_LIB_DIR_PREFIXES;
    public static final String[] AUTOMATIC_PACKAGE_ROOT_PREFIXES;
    
    private ClassLoaderHandlerRegistry() {
    }
    
    static {
        CLASS_LOADER_HANDLERS = Collections.unmodifiableList((List<? extends ClassLoaderHandlerRegistryEntry>)Arrays.asList(new ClassLoaderHandlerRegistryEntry((Class)AntClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry((Class)EquinoxClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry((Class)EquinoxContextFinderClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry((Class)FelixClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry((Class)JBossClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry((Class)WeblogicClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry((Class)WebsphereLibertyClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry((Class)WebsphereTraditionalClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry((Class)OSGiDefaultClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry((Class)SpringBootRestartClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry((Class)TomcatWebappClassLoaderBaseHandler.class), new ClassLoaderHandlerRegistryEntry((Class)PlexusClassWorldsClassRealmClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry((Class)QuarkusClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry((Class)UnoOneJarClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry((Class)ParentLastDelegationOrderTestClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry((Class)JPMSClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry((Class)URLClassLoaderHandler.class)));
        FALLBACK_HANDLER = new ClassLoaderHandlerRegistryEntry((Class)FallbackClassLoaderHandler.class);
        AUTOMATIC_LIB_DIR_PREFIXES = new String[] { "BOOT-INF/lib/", "WEB-INF/lib/", "WEB-INF/lib-provided/", "META-INF/lib/", "lib/", "lib/ext/", "main/" };
        AUTOMATIC_PACKAGE_ROOT_PREFIXES = new String[] { "classes/", "test-classes/", "BOOT-INF/classes/", "WEB-INF/classes/" };
    }
    
    public static class ClassLoaderHandlerRegistryEntry
    {
        private final Method canHandleMethod;
        private final Method findClassLoaderOrderMethod;
        private final Method findClasspathOrderMethod;
        public final Class<? extends ClassLoaderHandler> classLoaderHandlerClass;
        
        private ClassLoaderHandlerRegistryEntry(final Class<? extends ClassLoaderHandler> classLoaderHandlerClass) {
            this.classLoaderHandlerClass = classLoaderHandlerClass;
            try {
                this.canHandleMethod = classLoaderHandlerClass.getDeclaredMethod("canHandle", Class.class, LogNode.class);
            }
            catch (Exception e) {
                throw new RuntimeException("Could not find canHandle method for " + classLoaderHandlerClass.getName(), e);
            }
            try {
                this.findClassLoaderOrderMethod = classLoaderHandlerClass.getDeclaredMethod("findClassLoaderOrder", ClassLoader.class, ClassLoaderOrder.class, LogNode.class);
            }
            catch (Exception e) {
                throw new RuntimeException("Could not find findClassLoaderOrder method for " + classLoaderHandlerClass.getName(), e);
            }
            try {
                this.findClasspathOrderMethod = classLoaderHandlerClass.getDeclaredMethod("findClasspathOrder", ClassLoader.class, ClasspathOrder.class, ScanSpec.class, LogNode.class);
            }
            catch (Exception e) {
                throw new RuntimeException("Could not find findClasspathOrder method for " + classLoaderHandlerClass.getName(), e);
            }
        }
        
        public boolean canHandle(final Class<?> classLoader, final LogNode log) {
            try {
                return (boolean)this.canHandleMethod.invoke(null, classLoader, log);
            }
            catch (Throwable e) {
                throw new RuntimeException("Exception while calling canHandle for " + this.classLoaderHandlerClass.getName(), e);
            }
        }
        
        public void findClassLoaderOrder(final ClassLoader classLoader, final ClassLoaderOrder classLoaderOrder, final LogNode log) {
            try {
                this.findClassLoaderOrderMethod.invoke(null, classLoader, classLoaderOrder, log);
            }
            catch (Throwable e) {
                throw new RuntimeException("Exception while calling findClassLoaderOrder for " + this.classLoaderHandlerClass.getName(), e);
            }
        }
        
        public void findClasspathOrder(final ClassLoader classLoader, final ClasspathOrder classpathOrder, final ScanSpec scanSpec, final LogNode log) {
            try {
                this.findClasspathOrderMethod.invoke(null, classLoader, classpathOrder, scanSpec, log);
            }
            catch (Throwable e) {
                throw new RuntimeException("Exception while calling findClassLoaderOrder for " + this.classLoaderHandlerClass.getName(), e);
            }
        }
    }
}
