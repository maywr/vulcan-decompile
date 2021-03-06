//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package nonapi.io.github.classgraph.fastzipfilereader;

import java.nio.file.*;
import nonapi.io.github.classgraph.utils.*;
import nonapi.io.github.classgraph.fileslice.*;
import java.io.*;
import java.util.*;

class PhysicalZipFile
{
    private Path path;
    private File file;
    private final String pathStr;
    Slice slice;
    NestedJarHandler nestedJarHandler;
    private int hashCode;
    
    PhysicalZipFile(final File file, final NestedJarHandler nestedJarHandler, final LogNode log) throws IOException {
        this.nestedJarHandler = nestedJarHandler;
        FileUtils.checkCanReadAndIsFile(file);
        this.file = file;
        this.pathStr = FastPathResolver.resolve(FileUtils.CURR_DIR_PATH, file.getPath());
        this.slice = new FileSlice(file, nestedJarHandler, log);
    }
    
    PhysicalZipFile(final Path path, final NestedJarHandler nestedJarHandler, final LogNode log) throws IOException {
        this.nestedJarHandler = nestedJarHandler;
        FileUtils.checkCanReadAndIsFile(path);
        this.path = path;
        this.pathStr = FastPathResolver.resolve(FileUtils.CURR_DIR_PATH, path.toString());
        this.slice = new PathSlice(path, nestedJarHandler);
    }
    
    PhysicalZipFile(final byte[] arr, final File outermostFile, final String pathStr, final NestedJarHandler nestedJarHandler) throws IOException {
        this.nestedJarHandler = nestedJarHandler;
        this.file = outermostFile;
        this.pathStr = pathStr;
        this.slice = new ArraySlice(arr, false, 0L, nestedJarHandler);
    }
    
    PhysicalZipFile(final InputStream inputStream, final long inputStreamLengthHint, final String pathStr, final NestedJarHandler nestedJarHandler, final LogNode log) throws IOException {
        this.nestedJarHandler = nestedJarHandler;
        this.pathStr = pathStr;
        this.slice = nestedJarHandler.readAllBytesWithSpilloverToDisk(inputStream, pathStr, inputStreamLengthHint, log);
        this.file = ((this.slice instanceof FileSlice) ? ((FileSlice)this.slice).file : null);
    }
    
    public Path getPath() {
        return this.path;
    }
    
    public File getFile() {
        return this.file;
    }
    
    public String getPathStr() {
        return this.pathStr;
    }
    
    public long length() {
        return this.slice.sliceLength;
    }
    
    @Override
    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = ((this.file == null) ? 0 : this.file.hashCode());
            if (this.hashCode == 0) {
                this.hashCode = 1;
            }
        }
        return this.hashCode;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PhysicalZipFile)) {
            return false;
        }
        final PhysicalZipFile other = (PhysicalZipFile)o;
        return Objects.equals(this.file, other.file);
    }
    
    @Override
    public String toString() {
        return this.pathStr;
    }
}
