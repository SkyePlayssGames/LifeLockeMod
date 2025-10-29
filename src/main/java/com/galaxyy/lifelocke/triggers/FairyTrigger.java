package com.galaxyy.lifelocke.triggers;

import com.galaxyy.lifelocke.util.BlockUseConsumer;
import com.galaxyy.lifelocke.util.HungerCost;
import com.galaxyy.lifelocke.util.UpdateData;
import com.galaxyy.lifelocke.util.iEntityDataSaver;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.potion.Potions;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class FairyTrigger implements BlockUseConsumer {

    @Override
    public void accept(PlayerEntity playerEntity, World world, Hand hand, BlockHitResult blockHitResult) {
        if (world.isClient() || !UpdateData.tryAndStoreCooldown(((iEntityDataSaver) playerEntity), world.getTime())
            || !(HungerCost.checkHunger(playerEntity, 4) || playerEntity.isCreative())
            || playerEntity.hasStatusEffect(StatusEffects.GLOWING)) {
            return;
        }

        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 400, 0, false, false));
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 400, 0, false, false));
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 400, 0, false, false));
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 1200, 0, false, false));

        HungerCost.takeHunger(playerEntity, 1);
    }
}
