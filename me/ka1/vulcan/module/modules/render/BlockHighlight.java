//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.render;

import me.ka1.vulcan.module.*;
import me.ka1.vulcan.setting.*;
import me.ka1.vulcan.event.events.*;
import net.minecraft.world.*;
import net.minecraft.block.material.*;
import me.ka1.vulcan.util.*;
import java.awt.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.renderer.*;
import me.ka1.vulcan.util.font.*;
import net.minecraft.util.math.*;

public class BlockHighlight extends Module
{
    Setting.Integer w;
    Setting.Boolean shade;
    Setting.Boolean coords;
    Setting.Integer r;
    Setting.Integer g;
    Setting.Integer b;
    Setting.Integer a;
    
    public BlockHighlight() {
        super("BlockHighlight", Module.Category.Render);
    }
    
    public void setup() {
        this.shade = this.registerBoolean("Fill", "Fill", false);
        this.w = this.registerInteger("Width", "Width", 2, 1, 10);
        this.r = this.registerInteger("Red", "red", 255, 0, 255);
        this.g = this.registerInteger("Green", "green", 255, 0, 255);
        this.b = this.registerInteger("Blue", "blue", 255, 0, 255);
        this.a = this.registerInteger("Alpha", "alpha", 255, 0, 255);
        this.coords = this.registerBoolean("Coordinates", "coords", true);
    }
    
    public void onWorldRender(final RenderEvent event) {
        final RayTraceResult ray = BlockHighlight.mc.objectMouseOver;
        if (ray != null && ray.typeOfHit == RayTraceResult.Type.BLOCK) {
            final BlockPos pos = ray.getBlockPos();
            final AxisAlignedBB bb = BlockHighlight.mc.world.getBlockState(pos).getSelectedBoundingBox((World)BlockHighlight.mc.world, pos);
            if (bb != null && pos != null && BlockHighlight.mc.world.getBlockState(pos).getMaterial() != Material.AIR) {
                RenderUtil.prepareGL();
                RenderUtil.drawBoundingBox(bb, (float)this.w.getValue(), new Color(this.r.getValue(), this.g.getValue(), this.b.getValue(), this.a.getValue()).getRGB());
                RenderUtil.releaseGL();
                if (this.shade.getValue()) {
                    RenderUtil.prepare(7);
                    RenderUtil.drawBox(bb, new Color(this.r.getValue(), this.g.getValue(), this.b.getValue(), 50).getRGB(), 63);
                    RenderUtil.release();
                }
                if (this.coords.getValue()) {
                    RenderUtil.glBillboardDistanceScaled(pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f, (EntityPlayer)BlockHighlight.mc.player, 1.0f);
                    final int cx = pos.getX();
                    final int cy = pos.getY();
                    final int cz = pos.getZ();
                    final String CoordsText = Math.floor(cx) + " " + Math.floor(cy) + " " + Math.floor(cz);
                    GlStateManager.disableDepth();
                    GlStateManager.translate(-(BlockHighlight.mc.fontRenderer.getStringWidth(CoordsText) / 2.0), 0.0, 0.0);
                    FontUtils.drawStringWithShadow(false, CoordsText, 0, 0, new Color(255, 255, 255).getRGB());
                }
            }
        }
    }
}
