//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package io.github.classgraph;

import java.util.*;

public class AnnotationParameterValueList extends MappableInfoList<AnnotationParameterValue>
{
    private static final long serialVersionUID = 1L;
    static final AnnotationParameterValueList EMPTY_LIST;
    
    public static AnnotationParameterValueList emptyList() {
        return AnnotationParameterValueList.EMPTY_LIST;
    }
    
    public AnnotationParameterValueList() {
    }
    
    public AnnotationParameterValueList(final int sizeHint) {
        super(sizeHint);
    }
    
    public AnnotationParameterValueList(final Collection<AnnotationParameterValue> annotationParameterValueCollection) {
        super(annotationParameterValueCollection);
    }
    
    protected void findReferencedClassInfo(final Map<String, ClassInfo> classNameToClassInfo, final Set<ClassInfo> refdClassInfo) {
        for (final AnnotationParameterValue apv : this) {
            apv.findReferencedClassInfo(classNameToClassInfo, refdClassInfo);
        }
    }
    
    void convertWrapperArraysToPrimitiveArrays(final ClassInfo annotationClassInfo) {
        for (final AnnotationParameterValue apv : this) {
            apv.convertWrapperArraysToPrimitiveArrays(annotationClassInfo);
        }
    }
    
    public Object getValue(final String parameterName) {
        final AnnotationParameterValue apv = this.get(parameterName);
        return (apv == null) ? null : apv.getValue();
    }
    
    static {
        (EMPTY_LIST = new AnnotationParameterValueList()).makeUnmodifiable();
    }
}
