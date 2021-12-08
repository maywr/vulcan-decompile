//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package io.github.classgraph;

import nonapi.io.github.classgraph.scanspec.*;
import nonapi.io.github.classgraph.concurrency.*;
import java.util.concurrent.atomic.*;
import java.nio.file.attribute.*;
import java.nio.*;
import nonapi.io.github.classgraph.fileslice.reader.*;
import nonapi.io.github.classgraph.utils.*;
import nonapi.io.github.classgraph.recycler.*;
import java.util.*;
import java.io.*;
import java.net.*;

class ClasspathElementModule extends ClasspathElement
{
    final ModuleRef moduleRef;
    SingletonMap<ModuleRef, Recycler<ModuleReaderProxy, IOException>, IOException> moduleRefToModuleReaderProxyRecyclerMap;
    private Recycler<ModuleReaderProxy, IOException> moduleReaderProxyRecycler;
    private final Set<String> allResourcePaths;
    
    ClasspathElementModule(final ModuleRef moduleRef, final ClassLoader classLoader, final SingletonMap<ModuleRef, Recycler<ModuleReaderProxy, IOException>, IOException> moduleRefToModuleReaderProxyRecyclerMap, final ScanSpec scanSpec) {
        super(classLoader, scanSpec);
        this.allResourcePaths = new HashSet<String>();
        this.moduleRefToModuleReaderProxyRecyclerMap = moduleRefToModuleReaderProxyRecyclerMap;
        this.moduleRef = moduleRef;
    }
    
    void open(final WorkQueue<Scanner.ClasspathEntryWorkUnit> workQueueIgnored, final LogNode log) throws InterruptedException {
        if (!this.scanSpec.scanModules) {
            if (log != null) {
                this.log(this.classpathElementIdx, "Skipping module, since module scanning is disabled: " + this.getModuleName(), log);
            }
            this.skipClasspathElement = true;
            return;
        }
        try {
            this.moduleReaderProxyRecycler = this.moduleRefToModuleReaderProxyRecyclerMap.get(this.moduleRef, log);
        }
        catch (IOException | SingletonMap.NullSingletonException ex2) {
            final Exception ex;
            final Exception e = ex;
            if (log != null) {
                this.log(this.classpathElementIdx, "Skipping invalid module " + this.getModuleName() + " : " + e, log);
            }
            this.skipClasspathElement = true;
        }
    }
    
    private Resource newResource(final String resourcePath) {
        return new Resource(this, -1L) {
            private ModuleReaderProxy moduleReaderProxy;
            protected final AtomicBoolean isOpen = new AtomicBoolean();
            
            @Override
            public String getPath() {
                return resourcePath;
            }
            
            @Override
            public String getPathRelativeToClasspathElement() {
                return resourcePath;
            }
            
            @Override
            public long getLastModified() {
                return 0L;
            }
            
            @Override
            public Set<PosixFilePermission> getPosixFilePermissions() {
                return null;
            }
            
            @Override
            public ByteBuffer read() throws IOException {
                if (ClasspathElementModule.this.skipClasspathElement) {
                    throw new IOException("Module could not be opened");
                }
                if (this.isOpen.getAndSet(true)) {
                    throw new IOException("Resource is already open -- cannot open it again without first calling close()");
                }
                try {
                    this.moduleReaderProxy = ClasspathElementModule.this.moduleReaderProxyRecycler.acquire();
                    this.byteBuffer = this.moduleReaderProxy.read(resourcePath);
                    this.length = this.byteBuffer.remaining();
                    return this.byteBuffer;
                }
                catch (SecurityException | OutOfMemoryError ex) {
                    final Throwable t;
                    final Throwable e = t;
                    this.close();
                    throw new IOException("Could not open " + this, e);
                }
            }
            
            @Override
            ClassfileReader openClassfile() throws IOException {
                return new ClassfileReader(this.open());
            }
            
            @Override
            public InputStream open() throws IOException {
                if (ClasspathElementModule.this.skipClasspathElement) {
                    throw new IOException("Module could not be opened");
                }
                if (this.isOpen.getAndSet(true)) {
                    throw new IOException("Resource is already open -- cannot open it again without first calling close()");
                }
                try {
                    this.moduleReaderProxy = ClasspathElementModule.this.moduleReaderProxyRecycler.acquire();
                    this.inputStream = this.moduleReaderProxy.open(resourcePath);
                    this.length = -1L;
                    return this.inputStream;
                }
                catch (SecurityException e) {
                    this.close();
                    throw new IOException("Could not open " + this, e);
                }
            }
            
            @Override
            public byte[] load() throws IOException {
                this.read();
                try (final Resource res = this) {
                    byte[] byteArray;
                    if (this.byteBuffer.hasArray() && this.byteBuffer.position() == 0 && this.byteBuffer.limit() == this.byteBuffer.capacity()) {
                        byteArray = this.byteBuffer.array();
                    }
                    else {
                        byteArray = new byte[this.byteBuffer.remaining()];
                        this.byteBuffer.get(byteArray);
                    }
                    this.length = byteArray.length;
                    return byteArray;
                }
            }
            
            @Override
            public void close() {
                super.close();
                if (this.isOpen.getAndSet(false) && this.moduleReaderProxy != null) {
                    if (this.byteBuffer != null) {
                        this.moduleReaderProxy.release(this.byteBuffer);
                    }
                    ClasspathElementModule.this.moduleReaderProxyRecycler.recycle(this.moduleReaderProxy);
                    this.moduleReaderProxy = null;
                }
            }
        };
    }
    
    Resource getResource(final String relativePath) {
        return this.allResourcePaths.contains(relativePath) ? this.newResource(relativePath) : null;
    }
    
    void scanPaths(final LogNode log) {
        if (this.skipClasspathElement) {
            return;
        }
        if (this.scanned.getAndSet(true)) {
            throw new IllegalArgumentException("Already scanned classpath element " + this);
        }
        final LogNode subLog = (log == null) ? null : this.log(this.classpathElementIdx, "Scanning module " + this.moduleRef.getName(), log);
        final boolean isModularJar = VersionFinder.JAVA_MAJOR_VERSION >= 9 && this.getModuleName() != null;
        try (final RecycleOnClose<ModuleReaderProxy, IOException> moduleReaderProxyRecycleOnClose = this.moduleReaderProxyRecycler.acquireRecycleOnClose()) {
            List<String> resourceRelativePaths;
            try {
                resourceRelativePaths = moduleReaderProxyRecycleOnClose.get().list();
            }
            catch (SecurityException e) {
                if (subLog != null) {
                    subLog.log("Could not get resource list for module " + this.moduleRef.getName(), e);
                }
                return;
            }
            CollectionUtils.sortIfNotEmpty((List)resourceRelativePaths);
            String prevParentRelativePath = null;
            ScanSpec.ScanSpecPathMatch prevParentMatchStatus = null;
            for (final String relativePath : resourceRelativePaths) {
                if (relativePath.endsWith("/")) {
                    continue;
                }
                if (relativePath.startsWith("META-INF/versions/")) {
                    if (subLog == null) {
                        continue;
                    }
                    subLog.log("Found unexpected nested versioned entry in module -- skipping: " + relativePath);
                }
                else {
                    if (isModularJar && relativePath.indexOf(47) < 0 && relativePath.endsWith(".class") && !relativePath.equals("module-info.class")) {
                        continue;
                    }
                    this.checkResourcePathAcceptReject(relativePath, log);
                    if (this.skipClasspathElement) {
                        return;
                    }
                    final int lastSlashIdx = relativePath.lastIndexOf(47);
                    final String parentRelativePath = (lastSlashIdx < 0) ? "/" : relativePath.substring(0, lastSlashIdx + 1);
                    final boolean parentRelativePathChanged = !parentRelativePath.equals(prevParentRelativePath);
                    final ScanSpec.ScanSpecPathMatch parentMatchStatus = (prevParentRelativePath == null || parentRelativePathChanged) ? this.scanSpec.dirAcceptMatchStatus(parentRelativePath) : prevParentMatchStatus;
                    prevParentRelativePath = parentRelativePath;
                    if ((prevParentMatchStatus = parentMatchStatus) == ScanSpec.ScanSpecPathMatch.HAS_REJECTED_PATH_PREFIX) {
                        if (subLog == null) {
                            continue;
                        }
                        subLog.log("Skipping rejected path: " + relativePath);
                    }
                    else {
                        if (!this.allResourcePaths.add(relativePath)) {
                            continue;
                        }
                        if (parentMatchStatus == ScanSpec.ScanSpecPathMatch.HAS_ACCEPTED_PATH_PREFIX || parentMatchStatus == ScanSpec.ScanSpecPathMatch.AT_ACCEPTED_PATH || (parentMatchStatus == ScanSpec.ScanSpecPathMatch.AT_ACCEPTED_CLASS_PACKAGE && this.scanSpec.classfileIsSpecificallyAccepted(relativePath))) {
                            this.addAcceptedResource(this.newResource(relativePath), parentMatchStatus, false, subLog);
                        }
                        else {
                            if (!this.scanSpec.enableClassInfo || !relativePath.equals("module-info.class")) {
                                continue;
                            }
                            this.addAcceptedResource(this.newResource(relativePath), parentMatchStatus, true, subLog);
                        }
                    }
                }
            }
            final File moduleFile = this.moduleRef.getLocationFile();
            if (moduleFile != null && moduleFile.exists()) {
                this.fileToLastModified.put(moduleFile, moduleFile.lastModified());
            }
        }
        catch (IOException e2) {
            if (subLog != null) {
                subLog.log("Exception opening module " + this.moduleRef.getName(), e2);
            }
            this.skipClasspathElement = true;
        }
        this.finishScanPaths(subLog);
    }
    
    ModuleRef getModuleRef() {
        return this.moduleRef;
    }
    
    public String getModuleName() {
        String moduleName = this.moduleRef.getName();
        if (moduleName == null || moduleName.isEmpty()) {
            moduleName = this.moduleNameFromModuleDescriptor;
        }
        return (moduleName == null || moduleName.isEmpty()) ? null : moduleName;
    }
    
    private String getModuleNameOrEmpty() {
        final String moduleName = this.getModuleName();
        return (moduleName == null) ? "" : moduleName;
    }
    
    URI getURI() {
        final URI uri = this.moduleRef.getLocation();
        if (uri == null) {
            throw new IllegalArgumentException("Module " + this.getModuleName() + " has a null location");
        }
        return uri;
    }
    
    File getFile() {
        try {
            final URI uri = this.moduleRef.getLocation();
            if (uri != null && !uri.getScheme().equals("jrt")) {
                final File file = new File(uri);
                if (file.exists()) {
                    return file;
                }
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    public String toString() {
        return this.moduleRef.toString();
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClasspathElementModule)) {
            return false;
        }
        final ClasspathElementModule other = (ClasspathElementModule)obj;
        return this.getModuleNameOrEmpty().equals(other.getModuleNameOrEmpty());
    }
    
    public int hashCode() {
        return this.getModuleNameOrEmpty().hashCode();
    }
}
