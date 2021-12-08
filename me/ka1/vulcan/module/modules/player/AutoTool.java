//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\clients\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.ka1.vulcan.module.modules.player;

import me.ka1.vulcan.module.*;
import me.ka1.vulcan.setting.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;
import me.ka1.vulcan.util.*;
import me.zero.alpine.listener.*;
import me.ka1.vulcan.event.events.*;
import net.minecraft.network.play.client.*;
import java.util.function.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.network.*;
import net.minecraft.item.*;
import net.minecraft.block.*;
import net.minecraft.world.*;
import net.minecraft.block.state.*;
import net.minecraft.enchantment.*;
import net.minecraft.entity.*;
import net.minecraft.block.material.*;
import net.minecraftforge.event.*;
import net.minecraft.init.*;

public class AutoTool extends Module
{
    Setting.Boolean GoBack;
    Setting.Boolean silent;
    RayTraceResult ray;
    BlockPos pos;
    private boolean send;
    public BlockPos position;
    public EnumFacing facing;
    private int _previousSlot;
    private final Timer _timer;
    @EventHandler
    private final Listener<PacketEvent.Send> listener;
    @EventHandler
    private final Listener<EventPlayerUpdate> PlayerUpdate;
    @EventHandler
    private final Listener<EventNetworkPacketEvent> PacketEvent;
    
    public AutoTool() {
        super("AutoTool", "Switches to the correct tool when breaking blocks", Module.Category.Player);
        this.ray = AutoTool.mc.objectMouseOver;
        this._previousSlot = -1;
        this._timer = new Timer();
        final BlockPos pos;
        final int slot;
        this.listener = new Listener<PacketEvent.Send>(event -> {
            pos = this.ray.getBlockPos();
            if (event.getPacket() instanceof CPacketPlayerDigging) {
                this._timer.reset();
            }
            slot = this.getToolHotbar(pos);
            if (slot != -1 && AutoTool.mc.player.inventory.currentItem != slot) {
                if (this._previousSlot != slot) {
                    this._previousSlot = AutoTool.mc.player.inventory.currentItem;
                }
                AutoTool.mc.player.inventory.currentItem = slot;
                AutoTool.mc.playerController.updateController();
            }
            return;
        }, new Predicate[0]);
        this.PlayerUpdate = new Listener<EventPlayerUpdate>(event -> {
            if (this._previousSlot != -1 && this._timer.passed(250.0) && !AutoTool.mc.playerController.isHittingBlock) {
                this._timer.reset();
                if (this.GoBack.getValue()) {
                    AutoTool.mc.player.inventory.currentItem = this._previousSlot;
                    AutoTool.mc.playerController.updateController();
                }
                this._previousSlot = -1;
            }
            return;
        }, new Predicate[0]);
        CPacketPlayerDigging packet;
        int slot2;
        int hotbar;
        this.PacketEvent = new Listener<EventNetworkPacketEvent>(p_Event -> {
            if (this.send) {
                this.send = false;
            }
            else if (p_Event.getPacket() instanceof CPacketPlayerDigging && this.silent.getValue()) {
                packet = (CPacketPlayerDigging)p_Event.getPacket();
                if (packet.getAction() == CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK) {
                    this.position = packet.getPosition();
                    this.facing = packet.getFacing();
                    if (this.position != null && this.facing != null) {
                        slot2 = this.getToolInventory(packet.getPosition());
                        if (slot2 != -1) {
                            p_Event.cancel();
                            AutoTool.mc.playerController.windowClick(AutoTool.mc.player.inventoryContainer.windowId, slot2, AutoTool.mc.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)AutoTool.mc.player);
                            this.send = true;
                            AutoTool.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.position, this.facing));
                            AutoTool.mc.playerController.windowClick(AutoTool.mc.player.inventoryContainer.windowId, slot2, AutoTool.mc.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)AutoTool.mc.player);
                        }
                        else {
                            hotbar = this.getToolHotbar(packet.getPosition());
                            if (hotbar != -1) {
                                p_Event.cancel();
                                AutoTool.mc.playerController.windowClick(AutoTool.mc.player.inventoryContainer.windowId, hotbar, AutoTool.mc.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)AutoTool.mc.player);
                                this.send = true;
                                AutoTool.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.position, this.facing));
                                AutoTool.mc.playerController.windowClick(AutoTool.mc.player.inventoryContainer.windowId, hotbar, AutoTool.mc.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)AutoTool.mc.player);
                            }
                        }
                    }
                }
            }
        }, new Predicate[0]);
    }
    
    public void setup() {
        this.GoBack = this.registerBoolean("SwitchBack", "switchback", false);
        this.silent = this.registerBoolean("Silent", "silent", false);
    }
    
    private float blockStrength(final BlockPos pos, final ItemStack stack) {
        final float hardness = AutoTool.mc.world.getBlockState(pos).getBlockHardness((World)AutoTool.mc.world, pos);
        if (hardness < 0.0f) {
            return 0.0f;
        }
        if (!this.canHarvestBlock(AutoTool.mc.world.getBlockState(pos).getBlock(), pos, stack)) {
            return this.getDigSpeed(AutoTool.mc.world.getBlockState(pos), pos, stack) / hardness / 100.0f;
        }
        return this.getDigSpeed(AutoTool.mc.world.getBlockState(pos), pos, stack) / hardness / 30.0f;
    }
    
    private boolean canHarvestBlock(final Block block, final BlockPos pos, final ItemStack stack) {
        IBlockState state = AutoTool.mc.world.getBlockState(pos);
        state = state.getBlock().getActualState(state, (IBlockAccess)AutoTool.mc.world, pos);
        if (state.getMaterial().isToolNotRequired()) {
            return true;
        }
        final String tool = block.getHarvestTool(state);
        if (stack.isEmpty() || tool == null) {
            return AutoTool.mc.player.canHarvestBlock(state);
        }
        final int toolLevel = stack.getItem().getHarvestLevel(stack, tool, (EntityPlayer)AutoTool.mc.player, state);
        if (toolLevel < 0) {
            return AutoTool.mc.player.canHarvestBlock(state);
        }
        return toolLevel >= block.getHarvestLevel(state);
    }
    
    private float getDestroySpeed(final IBlockState state, final ItemStack stack) {
        float f = 1.0f;
        f *= stack.getDestroySpeed(state);
        return f;
    }
    
    private float getDigSpeed(final IBlockState state, final BlockPos pos, final ItemStack stack) {
        float f = this.getDestroySpeed(state, stack);
        if (f > 1.0f) {
            final int i = EnchantmentHelper.getEfficiencyModifier((EntityLivingBase)AutoTool.mc.player);
            if (i > 0 && !stack.isEmpty()) {
                f += i * i + 1;
            }
        }
        if (AutoTool.mc.player.isPotionActive(MobEffects.HASTE)) {
            f *= 1.0f + (AutoTool.mc.player.getActivePotionEffect(MobEffects.HASTE).getAmplifier() + 1) * 0.2f;
        }
        if (AutoTool.mc.player.isPotionActive(MobEffects.MINING_FATIGUE)) {
            float f2 = 0.0f;
            switch (AutoTool.mc.player.getActivePotionEffect(MobEffects.MINING_FATIGUE).getAmplifier()) {
                case 0: {
                    f2 = 0.3f;
                    break;
                }
                case 1: {
                    f2 = 0.09f;
                    break;
                }
                case 2: {
                    f2 = 0.0027f;
                    break;
                }
                default: {
                    f2 = 8.1E-4f;
                    break;
                }
            }
            f *= f2;
        }
        if (AutoTool.mc.player.isInsideOfMaterial(Material.WATER) && !EnchantmentHelper.getAquaAffinityModifier((EntityLivingBase)AutoTool.mc.player)) {
            f /= 5.0f;
        }
        if (!AutoTool.mc.player.onGround) {
            f /= 5.0f;
        }
        f = ForgeEventFactory.getBreakSpeed((EntityPlayer)AutoTool.mc.player, state, f, pos);
        return (f < 0.0f) ? 0.0f : f;
    }
    
    private int getToolInventory(final BlockPos pos) {
        int index = -1;
        float speed = 1.0f;
        for (int i = 9; i < 36; ++i) {
            final ItemStack stack = AutoTool.mc.player.inventoryContainer.getSlot(i).getStack();
            if (stack != null && stack != ItemStack.EMPTY) {
                final float digSpeed = (float)EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, stack);
                final float destroySpeed = stack.getDestroySpeed(AutoTool.mc.world.getBlockState(pos));
                if (digSpeed + destroySpeed > speed) {
                    speed = digSpeed + destroySpeed;
                    index = i;
                }
            }
        }
        return index;
    }
    
    private int getToolHotbar(final BlockPos pos) {
        int index = -1;
        float speed = 1.0f;
        for (int i = 0; i <= 9; ++i) {
            final ItemStack stack = AutoTool.mc.player.inventory.getStackInSlot(i);
            if (stack != null && stack != ItemStack.EMPTY) {
                final float digSpeed = (float)EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, stack);
                final float destroySpeed = stack.getDestroySpeed(AutoTool.mc.world.getBlockState(pos));
                if (digSpeed + destroySpeed > speed) {
                    speed = digSpeed + destroySpeed;
                    index = i;
                }
            }
        }
        return index;
    }
}
