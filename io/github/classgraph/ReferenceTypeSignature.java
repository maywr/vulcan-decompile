//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package io.github.classgraph;

import nonapi.io.github.classgraph.types.*;

public abstract class ReferenceTypeSignature extends TypeSignature
{
    protected ReferenceTypeSignature() {
    }
    
    static ReferenceTypeSignature parseReferenceTypeSignature(final Parser parser, final String definingClassName) throws ParseException {
        final ClassRefTypeSignature classTypeSignature = ClassRefTypeSignature.parse(parser, definingClassName);
        if (classTypeSignature != null) {
            return classTypeSignature;
        }
        final TypeVariableSignature typeVariableSignature = TypeVariableSignature.parse(parser, definingClassName);
        if (typeVariableSignature != null) {
            return typeVariableSignature;
        }
        final ArrayTypeSignature arrayTypeSignature = ArrayTypeSignature.parse(parser, definingClassName);
        if (arrayTypeSignature != null) {
            return arrayTypeSignature;
        }
        return null;
    }
    
    static ReferenceTypeSignature parseClassBound(final Parser parser, final String definingClassName) throws ParseException {
        parser.expect(':');
        return parseReferenceTypeSignature(parser, definingClassName);
    }
}
