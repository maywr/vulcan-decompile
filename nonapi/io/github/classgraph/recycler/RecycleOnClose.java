//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package nonapi.io.github.classgraph.recycler;

public class RecycleOnClose<T, E extends Exception> implements AutoCloseable
{
    private final Recycler<T, E> recycler;
    private final T instance;
    
    RecycleOnClose(final Recycler<T, E> recycler, final T instance) {
        this.recycler = recycler;
        this.instance = instance;
    }
    
    public T get() {
        return this.instance;
    }
    
    @Override
    public void close() {
        this.recycler.recycle(this.instance);
    }
}
