//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.mixin;

import net.minecraftforge.fml.relauncher.*;
import me.ka1.vulcan.*;
import org.spongepowered.asm.launch.*;
import org.spongepowered.asm.mixin.*;
import javax.annotation.*;
import java.util.*;

public class MixinLoader implements IFMLLoadingPlugin
{
    private static boolean isObfuscatedEnvironment;
    
    public MixinLoader() {
        Vulcan.log.info("Mixins initialized");
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.vulcan.json");
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
        Vulcan.log.info(MixinEnvironment.getDefaultEnvironment().getObfuscationContext());
    }
    
    public String[] getASMTransformerClass() {
        return new String[0];
    }
    
    public String getModContainerClass() {
        return null;
    }
    
    @Nullable
    public String getSetupClass() {
        return null;
    }
    
    public void injectData(final Map<String, Object> data) {
        MixinLoader.isObfuscatedEnvironment = data.get("runtimeDeobfuscationEnabled");
    }
    
    public String getAccessTransformerClass() {
        return null;
    }
    
    static {
        MixinLoader.isObfuscatedEnvironment = false;
    }
}
