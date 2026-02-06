package com.galaxyy.lifelocke.networking;

import com.galaxyy.lifelocke.effect.Types;
import com.galaxyy.lifelocke.playerdata.UpdateData;
import com.galaxyy.lifelocke.triggers.*;
import com.galaxyy.lifelocke.triggers.activated.*;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.Level;
import java.util.HashMap;

public class PressedAbilityKeyC2SHandler implements ServerPlayNetworking.PlayPayloadHandler<@org.jetbrains.annotations.NotNull PressedAbilityKeyC2SPayload> {
    public static final HashMap<Holder<MobEffect>, ActivatedAbility> ACTIVATED_ABILITIES = new HashMap<>();
    @Override
    public void receive(PressedAbilityKeyC2SPayload payload, ServerPlayNetworking.Context context) {
        ServerPlayer playerEntity = context.player();
        Level world = context.player().level();

        for (MobEffectInstance effect : playerEntity.getActiveEffects()) {
            if (Types.TOGGLED_TYPES.contains(Types.getType(effect.getEffect()))) {
                Types.TypeContainer type = Types.getType(effect.getEffect());
                assert type != null;
                UpdateData.setToggledPower(playerEntity, type, false);
                world.playSound(null, playerEntity.blockPosition(), payload.toggledSoundEvent(), SoundSource.PLAYERS);

            } else if (ACTIVATED_ABILITIES.containsKey(effect.getEffect())) {
                if (ACTIVATED_ABILITIES.get(effect.getEffect()).activate(playerEntity, payload.hitPos())) {
                    world.playSound(null, playerEntity.blockPosition(), payload.activatedSoundEvent(), SoundSource.PLAYERS);
                }
            }
        }
    }
}
