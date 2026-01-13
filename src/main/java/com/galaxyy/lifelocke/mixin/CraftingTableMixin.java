package com.galaxyy.lifelocke.mixin;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.gamerule.ModGameRules;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CraftingTableBlock.class)
public abstract class CraftingTableMixin extends Block {
    public CraftingTableMixin(Properties settings) {
        super(settings);
    }

    @Inject(at = @At("HEAD"), method = "useWithoutItem", cancellable = true)
    protected void onOnUse(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit, CallbackInfoReturnable<InteractionResult> cir) {
        if (player.hasEffect(ModEffects.FIGHTING) && !world.isClientSide() &&
        ((ServerLevel) world).getGameRules().get(ModGameRules.FIGHTING_CRAFTING_NERF)) {
            cir.setReturnValue(InteractionResult.FAIL);
        }
    }

}
