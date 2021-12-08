//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package io.github.classgraph;

import java.util.*;
import nonapi.io.github.classgraph.types.*;

public abstract class TypeSignature extends HierarchicalTypeSignature
{
    protected TypeSignature() {
    }
    
    protected void findReferencedClassNames(final Set<String> refdClassNames) {
        final String className = this.getClassName();
        if (className != null && !className.isEmpty()) {
            refdClassNames.add(this.getClassName());
        }
    }
    
    protected final void findReferencedClassInfo(final Map<String, ClassInfo> classNameToClassInfo, final Set<ClassInfo> refdClassInfo) {
        final Set<String> refdClassNames = new HashSet<String>();
        this.findReferencedClassNames(refdClassNames);
        for (final String refdClassName : refdClassNames) {
            final ClassInfo classInfo = ClassInfo.getOrCreateClassInfo(refdClassName, classNameToClassInfo);
            classInfo.scanResult = this.scanResult;
            refdClassInfo.add(classInfo);
        }
    }
    
    public abstract boolean equalsIgnoringTypeParams(final TypeSignature p0);
    
    protected abstract String toStringInternal(final boolean p0);
    
    public String toStringWithSimpleNames() {
        return this.toStringInternal(true);
    }
    
    public String toString() {
        return this.toStringInternal(false);
    }
    
    static TypeSignature parse(final Parser parser, final String definingClass) throws ParseException {
        final ReferenceTypeSignature referenceTypeSignature = ReferenceTypeSignature.parseReferenceTypeSignature(parser, definingClass);
        if (referenceTypeSignature != null) {
            return referenceTypeSignature;
        }
        final BaseTypeSignature baseTypeSignature = BaseTypeSignature.parse(parser);
        if (baseTypeSignature != null) {
            return baseTypeSignature;
        }
        return null;
    }
    
    static TypeSignature parse(final String typeDescriptor, final String definingClass) throws ParseException {
        final Parser parser = new Parser(typeDescriptor);
        final TypeSignature typeSignature = parse(parser, definingClass);
        if (typeSignature == null) {
            throw new ParseException(parser, "Could not parse type signature");
        }
        if (parser.hasMore()) {
            throw new ParseException(parser, "Extra characters at end of type descriptor");
        }
        return typeSignature;
    }
}
