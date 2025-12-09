package com.galaxyy.lifelocke.mixin;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.gamerule.ModGameRules;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CrafterBlock;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CrafterBlock.class)
public abstract class CrafterMixin extends Block {
    public CrafterMixin(Settings settings) {
        super(settings);
    }

    @Inject(at = @At("HEAD"), method = "onUse", cancellable = true)
    protected void onOnUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        if (player.hasStatusEffect(ModEffects.FIGHTING) && !world.isClient() &&
        ((ServerWorld) world).getGameRules().getValue(ModGameRules.FIGHTING_CRAFTING_NERF)) {
            cir.setReturnValue(ActionResult.FAIL);
        }
    }

}
