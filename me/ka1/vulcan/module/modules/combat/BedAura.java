//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.combat;

import me.ka1.vulcan.module.*;
import me.ka1.vulcan.setting.*;
import net.minecraft.item.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.init.*;
import java.util.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.tileentity.*;

public class BedAura extends Module
{
    Setting.Double range;
    Setting.Boolean dimensionCheck;
    Setting.Boolean rotate;
    Setting.Boolean refill;
    boolean moving;
    
    public BedAura() {
        super("BedAura", "Places and explodes beds for you", Module.Category.Combat);
        this.moving = false;
        this.range = this.registerDouble("Range", "BedAuraRange", 4.5, 0.0, 10.0);
        this.rotate = this.registerBoolean("Rotate", "BedAuraRotate", true);
        this.dimensionCheck = this.registerBoolean("DimensionCheck", "BedAuraDimensionCheck", true);
        this.refill = this.registerBoolean("RefillHotbar", "BedAuraRefillHotbar", false);
    }
    
    public void onUpdate() {
        if (this.refill.getValue()) {
            int slot = -1;
            for (int i = 0; i < 9; ++i) {
                if (BedAura.mc.player.inventory.getStackInSlot(i) == ItemStack.EMPTY) {
                    slot = i;
                    break;
                }
            }
            if (this.moving && slot != -1) {
                BedAura.mc.playerController.windowClick(0, slot + 36, 0, ClickType.PICKUP, (EntityPlayer)BedAura.mc.player);
                this.moving = false;
                slot = -1;
            }
            if (slot != -1 && !(BedAura.mc.currentScreen instanceof GuiContainer) && BedAura.mc.player.inventory.getItemStack().isEmpty()) {
                int t = -1;
                for (int j = 0; j < 45; ++j) {
                    if (BedAura.mc.player.inventory.getStackInSlot(j).getItem() == Items.BED && j >= 9) {
                        t = j;
                        break;
                    }
                }
                if (t != -1) {
                    BedAura.mc.playerController.windowClick(0, t, 0, ClickType.PICKUP, (EntityPlayer)BedAura.mc.player);
                    this.moving = true;
                }
            }
        }
        BedAura.mc.world.loadedTileEntityList.stream().filter(e -> e instanceof TileEntityBed).filter(e -> BedAura.mc.player.getDistance((double)e.getPos().getX(), (double)e.getPos().getY(), (double)e.getPos().getZ()) <= this.range.getValue()).sorted(Comparator.comparing(e -> BedAura.mc.player.getDistance((double)e.getPos().getX(), (double)e.getPos().getY(), (double)e.getPos().getZ()))).forEach(bed -> {
            if (!this.dimensionCheck.getValue() || BedAura.mc.player.dimension != 0) {
                BedAura.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(bed.getPos(), EnumFacing.UP, EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
            }
        });
    }
}
