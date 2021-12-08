//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package nonapi.io.github.classgraph.recycler;

import java.util.concurrent.*;
import java.util.*;

public abstract class Recycler<T, E extends Exception> implements AutoCloseable
{
    private final Set<T> usedInstances;
    private final Queue<T> unusedInstances;
    
    public Recycler() {
        this.usedInstances = Collections.newSetFromMap(new ConcurrentHashMap<T, Boolean>());
        this.unusedInstances = new ConcurrentLinkedQueue<T>();
    }
    
    public abstract T newInstance() throws E, Exception;
    
    public T acquire() throws E, Exception {
        final T recycledInstance = this.unusedInstances.poll();
        T instance;
        if (recycledInstance == null) {
            final T newInstance = this.newInstance();
            if (newInstance == null) {
                throw new NullPointerException("Failed to allocate a new recyclable instance");
            }
            instance = newInstance;
        }
        else {
            instance = recycledInstance;
        }
        this.usedInstances.add(instance);
        return instance;
    }
    
    public RecycleOnClose<T, E> acquireRecycleOnClose() throws E, Exception {
        return (RecycleOnClose<T, E>)new RecycleOnClose(this, this.acquire());
    }
    
    public final void recycle(final T instance) {
        if (instance != null) {
            if (!this.usedInstances.remove(instance)) {
                throw new IllegalArgumentException("Tried to recycle an instance that was not in use");
            }
            if (instance instanceof Resettable) {
                ((Resettable)instance).reset();
            }
            if (!this.unusedInstances.add(instance)) {
                throw new IllegalArgumentException("Tried to recycle an instance twice");
            }
        }
    }
    
    @Override
    public void close() {
        T unusedInstance;
        while ((unusedInstance = this.unusedInstances.poll()) != null) {
            if (unusedInstance instanceof AutoCloseable) {
                try {
                    ((AutoCloseable)unusedInstance).close();
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public void forceClose() {
        for (final T usedInstance : new ArrayList<T>((Collection<? extends T>)this.usedInstances)) {
            if (this.usedInstances.remove(usedInstance)) {
                this.unusedInstances.add(usedInstance);
            }
        }
        this.close();
    }
}
