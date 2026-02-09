package com.galaxyy.lifelocke.power;

import com.galaxyy.lifelocke.effect.Types;
import com.galaxyy.lifelocke.playerdata.iEntityDataSaver;
import com.galaxyy.lifelocke.triggers.HungerCost;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public abstract class ToggledPower {

    protected abstract Types.TypeContainer getType();

    protected boolean canAffect(Player playerEntity, Level level, Entity entity) {
        return (playerEntity.hasEffect(getType().type) && !level.isClientSide() &&
                (HungerCost.checkHunger(playerEntity, 4) || playerEntity.isCreative()) &&
                ((iEntityDataSaver) playerEntity).lifelocke$getPersistentData().getStringOr("toggled_power", "lifelocke:null")
                        .equals(getType().id.toString()) &&
                entity instanceof LivingEntity);
    }
}
