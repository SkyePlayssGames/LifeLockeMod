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

public abstract class ToggledPower implements AttackEntityCallback {
    protected Types.TypeContainer type;

    public void setType(Types.TypeContainer type) {
        this.type = type;
    }

    protected boolean canAffect(Player playerEntity, Level level, Entity entity) {
        return (playerEntity.hasEffect(Types.DARK_TYPE.type) && !level.isClientSide() &&
                (HungerCost.checkHunger(playerEntity, 4) || playerEntity.isCreative()) &&
                ((iEntityDataSaver) playerEntity).lifelocke$getPersistentData().getStringOr("toggled_power", "lifelocke:null")
                        .equals(Types.DARK_TYPE.id.toString()) &&
                entity instanceof LivingEntity);
    }
}
