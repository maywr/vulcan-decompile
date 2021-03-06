//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package nonapi.io.github.classgraph.concurrency;

import java.util.concurrent.atomic.*;
import java.util.concurrent.*;

public class InterruptionChecker
{
    private final AtomicBoolean interrupted;
    private final AtomicReference<ExecutionException> thrownExecutionException;
    
    public InterruptionChecker() {
        this.interrupted = new AtomicBoolean(false);
        this.thrownExecutionException = new AtomicReference<ExecutionException>();
    }
    
    public void interrupt() {
        this.interrupted.set(true);
        Thread.currentThread().interrupt();
    }
    
    public void setExecutionException(final ExecutionException executionException) {
        if (executionException != null && this.thrownExecutionException.get() == null) {
            this.thrownExecutionException.compareAndSet(null, executionException);
        }
    }
    
    public ExecutionException getExecutionException() {
        return this.thrownExecutionException.get();
    }
    
    public static Throwable getCause(final Throwable throwable) {
        Throwable cause;
        for (cause = throwable; cause instanceof ExecutionException; cause = cause.getCause()) {}
        return (cause != null) ? cause : new ExecutionException("ExecutionException with unknown cause", null);
    }
    
    public boolean checkAndReturn() {
        if (this.interrupted.get()) {
            this.interrupt();
            return true;
        }
        if (Thread.currentThread().isInterrupted()) {
            this.interrupted.set(true);
            return true;
        }
        return false;
    }
    
    public void check() throws InterruptedException, ExecutionException {
        final ExecutionException executionException = this.getExecutionException();
        if (executionException != null) {
            throw executionException;
        }
        if (this.checkAndReturn()) {
            throw new InterruptedException();
        }
    }
}
