//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package nonapi.io.github.classgraph.fileslice.reader;

import java.nio.*;
import java.io.*;

public interface RandomAccessReader
{
    int read(final long p0, final ByteBuffer p1, final int p2, final int p3) throws IOException;
    
    int read(final long p0, final byte[] p1, final int p2, final int p3) throws IOException;
    
    byte readByte(final long p0) throws IOException;
    
    int readUnsignedByte(final long p0) throws IOException;
    
    short readShort(final long p0) throws IOException;
    
    int readUnsignedShort(final long p0) throws IOException;
    
    int readInt(final long p0) throws IOException;
    
    long readUnsignedInt(final long p0) throws IOException;
    
    long readLong(final long p0) throws IOException;
    
    String readString(final long p0, final int p1, final boolean p2, final boolean p3) throws IOException;
    
    String readString(final long p0, final int p1) throws IOException;
}
