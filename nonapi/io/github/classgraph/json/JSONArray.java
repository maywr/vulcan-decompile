//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package nonapi.io.github.classgraph.json;

import java.util.*;

class JSONArray
{
    List<Object> items;
    
    public JSONArray() {
        this.items = new ArrayList<Object>();
    }
    
    public JSONArray(final List<Object> items) {
        this.items = items;
    }
    
    void toJSONString(final Map<ReferenceEqualityKey<JSONReference>, CharSequence> jsonReferenceToId, final boolean includeNullValuedFields, final int depth, final int indentWidth, final StringBuilder buf) {
        final boolean prettyPrint = indentWidth > 0;
        final int n = this.items.size();
        if (n == 0) {
            buf.append("[]");
        }
        else {
            buf.append('[');
            if (prettyPrint) {
                buf.append('\n');
            }
            for (int i = 0; i < n; ++i) {
                final Object item = this.items.get(i);
                if (prettyPrint) {
                    JSONUtils.indent(depth + 1, indentWidth, buf);
                }
                JSONSerializer.jsonValToJSONString(item, jsonReferenceToId, includeNullValuedFields, depth + 1, indentWidth, buf);
                if (i < n - 1) {
                    buf.append(',');
                }
                if (prettyPrint) {
                    buf.append('\n');
                }
            }
            if (prettyPrint) {
                JSONUtils.indent(depth, indentWidth, buf);
            }
            buf.append(']');
        }
    }
}
