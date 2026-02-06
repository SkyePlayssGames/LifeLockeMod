package com.galaxyy.lifelocke.event;

import com.galaxyy.lifelocke.effect.ModEffects;
import com.galaxyy.lifelocke.effect.Types;
import com.galaxyy.lifelocke.playerdata.UpdateData;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import java.util.HashMap;
import java.util.function.Function;

public class JoinIconFixEvent implements ServerPlayerEvents.Join {
    @Override
    public void onJoin(ServerPlayer player) {
        for (Types.TypeContainer type : Types.TOGGLED_TYPES) {
            if (player.hasEffect(type.type)) {
                UpdateData.setToggledPower(player, type, false);
                UpdateData.setToggledPower(player, type, false);
                return;
            }
        }
    }
}
