//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package nonapi.io.github.classgraph.fileslice.reader;

import java.io.*;

public interface SequentialReader
{
    byte readByte() throws IOException;
    
    int readUnsignedByte() throws IOException;
    
    short readShort() throws IOException;
    
    int readUnsignedShort() throws IOException;
    
    int readInt() throws IOException;
    
    long readUnsignedInt() throws IOException;
    
    long readLong() throws IOException;
    
    void skip(final int p0) throws IOException;
    
    String readString(final int p0, final boolean p1, final boolean p2) throws IOException;
    
    String readString(final int p0) throws IOException;
}
