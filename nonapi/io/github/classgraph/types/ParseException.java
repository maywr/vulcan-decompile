//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package nonapi.io.github.classgraph.types;

public class ParseException extends Exception
{
    static final long serialVersionUID = 1L;
    
    public ParseException(final Parser parser, final String msg) {
        super((parser == null) ? msg : (msg + " (" + parser.getPositionInfo() + ")"));
    }
}
