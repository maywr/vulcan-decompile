//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package io.github.classgraph;

import nonapi.io.github.classgraph.fastzipfilereader.*;
import nonapi.io.github.classgraph.scanspec.*;
import nonapi.io.github.classgraph.concurrency.*;
import nonapi.io.github.classgraph.classloaderhandler.*;
import nonapi.io.github.classgraph.classpath.*;
import java.util.concurrent.atomic.*;
import java.nio.file.attribute.*;
import java.nio.file.*;
import java.nio.*;
import nonapi.io.github.classgraph.fileslice.reader.*;
import nonapi.io.github.classgraph.fileslice.*;
import java.io.*;
import nonapi.io.github.classgraph.utils.*;
import java.net.*;
import java.util.*;

class ClasspathElementFileDir extends ClasspathElement
{
    private final File classpathEltDir;
    private final File packageRootDir;
    private final Set<String> scannedCanonicalPaths;
    private final NestedJarHandler nestedJarHandler;
    
    ClasspathElementFileDir(final File classpathEltDir, final String packageRootPrefix, final ClassLoader classLoader, final NestedJarHandler nestedJarHandler, final ScanSpec scanSpec) {
        super(classLoader, scanSpec);
        this.scannedCanonicalPaths = new HashSet<String>();
        this.classpathEltDir = classpathEltDir;
        this.packageRootDir = new File(classpathEltDir, packageRootPrefix);
        this.nestedJarHandler = nestedJarHandler;
    }
    
    void open(final WorkQueue<Scanner.ClasspathEntryWorkUnit> workQueue, final LogNode log) {
        if (!this.scanSpec.scanDirs) {
            if (log != null) {
                this.log(this.classpathElementIdx, "Skipping classpath element, since dir scanning is disabled: " + this.classpathEltDir, log);
            }
            this.skipClasspathElement = true;
            return;
        }
        try {
            int childClasspathEntryIdx = 0;
            for (final String libDirPrefix : ClassLoaderHandlerRegistry.AUTOMATIC_LIB_DIR_PREFIXES) {
                final File libDir = new File(this.classpathEltDir, libDirPrefix);
                if (FileUtils.canReadAndIsDir(libDir)) {
                    final File[] listFiles = libDir.listFiles();
                    if (listFiles != null) {
                        Arrays.sort(listFiles);
                        for (final File file : listFiles) {
                            if (file.isFile() && file.getName().endsWith(".jar")) {
                                if (log != null) {
                                    this.log(this.classpathElementIdx, "Found lib jar: " + file, log);
                                }
                                workQueue.addWorkUnit(new Scanner.ClasspathEntryWorkUnit(new ClasspathOrder.ClasspathElementAndClassLoader(file.getPath(), this.classLoader), this, childClasspathEntryIdx++));
                            }
                        }
                    }
                }
            }
            if (this.packageRootDir.equals(this.classpathEltDir)) {
                for (final String packageRootPrefix : ClassLoaderHandlerRegistry.AUTOMATIC_PACKAGE_ROOT_PREFIXES) {
                    final File packageRoot = new File(this.classpathEltDir, packageRootPrefix);
                    if (FileUtils.canReadAndIsDir(packageRoot)) {
                        if (log != null) {
                            this.log(this.classpathElementIdx, "Found package root: " + packageRoot, log);
                        }
                        workQueue.addWorkUnit(new Scanner.ClasspathEntryWorkUnit(new ClasspathOrder.ClasspathElementAndClassLoader(this.classpathEltDir, packageRootPrefix, this.classLoader), this, childClasspathEntryIdx++));
                    }
                }
            }
        }
        catch (SecurityException e) {
            if (log != null) {
                this.log(this.classpathElementIdx, "Skipping classpath element, since dir cannot be accessed: " + this.classpathEltDir, log);
            }
            this.skipClasspathElement = true;
        }
    }
    
    private Resource newResource(final String pathRelativeToPackageRoot, final File resourceFile, final NestedJarHandler nestedJarHandler) {
        return new Resource(this, resourceFile.length()) {
            private FileSlice fileSlice;
            protected final AtomicBoolean isOpen = new AtomicBoolean();
            
            @Override
            public String getPath() {
                String path;
                for (path = FastPathResolver.resolve(pathRelativeToPackageRoot); path.startsWith("/"); path = path.substring(1)) {}
                return path;
            }
            
            @Override
            public String getPathRelativeToClasspathElement() {
                final File resourceFile = new File(ClasspathElementFileDir.this.packageRootDir, pathRelativeToPackageRoot);
                String pathRelativeToClasspathElt;
                for (pathRelativeToClasspathElt = FastPathResolver.resolve(resourceFile.getPath().substring(ClasspathElementFileDir.this.classpathEltDir.getPath().length())); pathRelativeToClasspathElt.startsWith("/"); pathRelativeToClasspathElt = pathRelativeToClasspathElt.substring(1)) {}
                return pathRelativeToClasspathElt;
            }
            
            @Override
            public long getLastModified() {
                return resourceFile.lastModified();
            }
            
            @Override
            public Set<PosixFilePermission> getPosixFilePermissions() {
                Set<PosixFilePermission> posixFilePermissions = null;
                try {
                    posixFilePermissions = Files.readAttributes(resourceFile.toPath(), PosixFileAttributes.class).permissions();
                }
                catch (UnsupportedOperationException ex) {}
                catch (IOException ex2) {}
                catch (SecurityException ex3) {}
                return posixFilePermissions;
            }
            
            @Override
            public ByteBuffer read() throws IOException {
                if (ClasspathElementFileDir.this.skipClasspathElement) {
                    throw new IOException("Parent directory could not be opened");
                }
                if (this.isOpen.getAndSet(true)) {
                    throw new IOException("Resource is already open -- cannot open it again without first calling close()");
                }
                this.fileSlice = new FileSlice(resourceFile, nestedJarHandler, null);
                this.length = this.fileSlice.sliceLength;
                return this.byteBuffer = this.fileSlice.read();
            }
            
            @Override
            ClassfileReader openClassfile() throws IOException {
                if (ClasspathElementFileDir.this.skipClasspathElement) {
                    throw new IOException("Parent directory could not be opened");
                }
                if (this.isOpen.getAndSet(true)) {
                    throw new IOException("Resource is already open -- cannot open it again without first calling close()");
                }
                this.fileSlice = new FileSlice(resourceFile, nestedJarHandler, null);
                this.length = this.fileSlice.sliceLength;
                return new ClassfileReader(this.fileSlice);
            }
            
            @Override
            public InputStream open() throws IOException {
                if (ClasspathElementFileDir.this.skipClasspathElement) {
                    throw new IOException("Parent directory could not be opened");
                }
                if (this.isOpen.getAndSet(true)) {
                    throw new IOException("Resource is already open -- cannot open it again without first calling close()");
                }
                this.fileSlice = new FileSlice(resourceFile, nestedJarHandler, null);
                this.inputStream = this.fileSlice.open();
                this.length = this.fileSlice.sliceLength;
                return this.inputStream;
            }
            
            @Override
            public byte[] load() throws IOException {
                this.read();
                try (final Resource res = this) {
                    this.fileSlice = new FileSlice(resourceFile, nestedJarHandler, null);
                    final byte[] bytes = this.fileSlice.load();
                    this.length = bytes.length;
                    return bytes;
                }
            }
            
            @Override
            public void close() {
                super.close();
                if (this.isOpen.getAndSet(false)) {
                    if (this.byteBuffer != null) {
                        this.byteBuffer = null;
                    }
                    if (this.fileSlice != null) {
                        this.fileSlice.close();
                        nestedJarHandler.markSliceAsClosed(this.fileSlice);
                        this.fileSlice = null;
                    }
                }
            }
        };
    }
    
    Resource getResource(final String pathRelativeToPackageRoot) {
        final File resourceFile = new File(this.packageRootDir, pathRelativeToPackageRoot);
        return FileUtils.canReadAndIsFile(resourceFile) ? this.newResource(pathRelativeToPackageRoot, resourceFile, this.nestedJarHandler) : null;
    }
    
    private void scanDirRecursively(final File dir, final LogNode log) {
        if (this.skipClasspathElement) {
            return;
        }
        try {
            final String canonicalPath = dir.getCanonicalPath();
            if (!this.scannedCanonicalPaths.add(canonicalPath)) {
                if (log != null) {
                    log.log("Reached symlink cycle, stopping recursion: " + dir);
                }
                return;
            }
        }
        catch (IOException | SecurityException ex2) {
            final Exception ex;
            final Exception e = ex;
            if (log != null) {
                log.log("Could not canonicalize path: " + dir, e);
            }
            return;
        }
        final String dirPath = dir.getPath();
        final int ignorePrefixLen = this.packageRootDir.getPath().length() + 1;
        final String dirRelativePath = (ignorePrefixLen > dirPath.length()) ? "/" : (dirPath.substring(ignorePrefixLen).replace(File.separatorChar, '/') + "/");
        final boolean isDefaultPackage = "/".equals(dirRelativePath);
        if (this.nestedClasspathRootPrefixes != null && this.nestedClasspathRootPrefixes.contains(dirRelativePath)) {
            if (log != null) {
                log.log("Reached nested classpath root, stopping recursion to avoid duplicate scanning: " + dirRelativePath);
            }
            return;
        }
        if (dirRelativePath.startsWith("META-INF/versions/")) {
            if (log != null) {
                log.log("Found unexpected nested versioned entry in directory classpath element -- skipping: " + dirRelativePath);
            }
            return;
        }
        this.checkResourcePathAcceptReject(dirRelativePath, log);
        if (this.skipClasspathElement) {
            return;
        }
        final ScanSpec.ScanSpecPathMatch parentMatchStatus = this.scanSpec.dirAcceptMatchStatus(dirRelativePath);
        if (parentMatchStatus == ScanSpec.ScanSpecPathMatch.HAS_REJECTED_PATH_PREFIX) {
            if (log != null) {
                log.log("Reached rejected directory, stopping recursive scan: " + dirRelativePath);
            }
            return;
        }
        if (parentMatchStatus == ScanSpec.ScanSpecPathMatch.NOT_WITHIN_ACCEPTED_PATH) {
            return;
        }
        String canonicalPath;
        final LogNode subLog = (log == null) ? null : log.log("1:" + canonicalPath, "Scanning directory: " + dir + (dir.getPath().equals(canonicalPath) ? "" : (" ; canonical path: " + canonicalPath)));
        final File[] filesInDir = dir.listFiles();
        if (filesInDir == null) {
            if (log != null) {
                log.log("Invalid directory " + dir);
            }
            return;
        }
        Arrays.sort(filesInDir);
        final boolean isModularJar = VersionFinder.JAVA_MAJOR_VERSION >= 9 && this.getModuleName() != null;
        if (parentMatchStatus != ScanSpec.ScanSpecPathMatch.ANCESTOR_OF_ACCEPTED_PATH) {
            for (final File fileInDir : filesInDir) {
                if (fileInDir.isFile()) {
                    final String fileInDirRelativePath = (dirRelativePath.isEmpty() || isDefaultPackage) ? fileInDir.getName() : (dirRelativePath + fileInDir.getName());
                    if (!isModularJar || !isDefaultPackage || !fileInDirRelativePath.endsWith(".class") || fileInDirRelativePath.equals("module-info.class")) {
                        this.checkResourcePathAcceptReject(fileInDirRelativePath, subLog);
                        if (this.skipClasspathElement) {
                            return;
                        }
                        if (parentMatchStatus == ScanSpec.ScanSpecPathMatch.HAS_ACCEPTED_PATH_PREFIX || parentMatchStatus == ScanSpec.ScanSpecPathMatch.AT_ACCEPTED_PATH || (parentMatchStatus == ScanSpec.ScanSpecPathMatch.AT_ACCEPTED_CLASS_PACKAGE && this.scanSpec.classfileIsSpecificallyAccepted(fileInDirRelativePath))) {
                            final Resource resource = this.newResource(fileInDirRelativePath, fileInDir, this.nestedJarHandler);
                            this.addAcceptedResource(resource, parentMatchStatus, false, subLog);
                            this.fileToLastModified.put(fileInDir, fileInDir.lastModified());
                        }
                        else if (subLog != null) {
                            subLog.log("Skipping non-accepted file: " + fileInDirRelativePath);
                        }
                    }
                }
            }
        }
        else if (this.scanSpec.enableClassInfo && dirRelativePath.equals("/")) {
            for (final File fileInDir : filesInDir) {
                if (fileInDir.getName().equals("module-info.class") && fileInDir.isFile()) {
                    final Resource resource2 = this.newResource("module-info.class", fileInDir, this.nestedJarHandler);
                    this.addAcceptedResource(resource2, parentMatchStatus, true, subLog);
                    this.fileToLastModified.put(fileInDir, fileInDir.lastModified());
                    break;
                }
            }
        }
        for (final File fileInDir : filesInDir) {
            if (fileInDir.isDirectory()) {
                this.scanDirRecursively(fileInDir, subLog);
                if (this.skipClasspathElement) {
                    if (subLog != null) {
                        subLog.addElapsedTime();
                    }
                    return;
                }
            }
        }
        if (subLog != null) {
            subLog.addElapsedTime();
        }
        this.fileToLastModified.put(dir, dir.lastModified());
    }
    
    void scanPaths(final LogNode log) {
        if (this.skipClasspathElement) {
            return;
        }
        if (this.scanned.getAndSet(true)) {
            throw new IllegalArgumentException("Already scanned classpath element " + this);
        }
        final LogNode subLog = (log == null) ? null : this.log(this.classpathElementIdx, "Scanning directory classpath element " + this.packageRootDir, log);
        this.scanDirRecursively(this.packageRootDir, subLog);
        this.finishScanPaths(subLog);
    }
    
    public String getModuleName() {
        return (this.moduleNameFromModuleDescriptor == null || this.moduleNameFromModuleDescriptor.isEmpty()) ? null : this.moduleNameFromModuleDescriptor;
    }
    
    public File getFile() {
        return this.classpathEltDir;
    }
    
    URI getURI() {
        return this.packageRootDir.toURI();
    }
    
    public String toString() {
        return this.packageRootDir.toString();
    }
    
    public int hashCode() {
        return Objects.hash(this.classpathEltDir, this.packageRootDir);
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClasspathElementFileDir)) {
            return false;
        }
        final ClasspathElementFileDir other = (ClasspathElementFileDir)obj;
        return Objects.equals(this.classpathEltDir, other.classpathEltDir) && Objects.equals(this.packageRootDir, other.packageRootDir);
    }
}
