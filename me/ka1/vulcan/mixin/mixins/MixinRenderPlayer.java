//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.entity.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.ka1.vulcan.event.events.*;
import me.ka1.vulcan.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ RenderPlayer.class })
public class MixinRenderPlayer
{
    @Inject(method = { "renderEntityName" }, at = { @At("HEAD") }, cancellable = true)
    public void renderLivingLabel(final AbstractClientPlayer entityIn, final double x, final double y, final double z, final String name, final double distanceSq, final CallbackInfo info) {
        final EventRenderEntityName l_Event = new EventRenderEntityName(entityIn, x, y, z, name, distanceSq);
        Vulcan.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled()) {
            info.cancel();
        }
    }
}
