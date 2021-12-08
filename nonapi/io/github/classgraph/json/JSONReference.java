//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package nonapi.io.github.classgraph.json;

class JSONReference
{
    Object idObject;
    
    public JSONReference(final Object idObject) {
        if (idObject == null) {
            throw new IllegalArgumentException("idObject cannot be null");
        }
        this.idObject = idObject;
    }
}
