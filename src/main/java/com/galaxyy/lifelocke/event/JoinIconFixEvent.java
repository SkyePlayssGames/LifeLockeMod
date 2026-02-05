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
    private static final HashMap<Holder<MobEffect>, Function<ServerPlayer, Boolean>> TOGGLE_TYPES = new HashMap<>();

    @Override
    public void onJoin(ServerPlayer player) {
        for (Holder<MobEffect> status : TOGGLE_TYPES.keySet()) {
            if (player.hasEffect(status)) {
                TOGGLE_TYPES.get(status).apply(player);
                TOGGLE_TYPES.get(status).apply(player);
                return;
            }
        }
    }

    public static void registerJoinFixEventTypes() {
        TOGGLE_TYPES.put(Types.DARK_TYPE.type, UpdateData::toggleDarkPower);
        TOGGLE_TYPES.put(Types.ICE_TYPE.type, UpdateData::toggleIcePower);
        TOGGLE_TYPES.put(Types.ELECTRIC_TYPE.type, UpdateData::toggleElectricPower);
        TOGGLE_TYPES.put(Types.PSYCHIC_TYPE.type, UpdateData::togglePsychicPower);
        TOGGLE_TYPES.put(Types.POISON_TYPE.type, UpdateData::togglePoisonPower);
    }
}
