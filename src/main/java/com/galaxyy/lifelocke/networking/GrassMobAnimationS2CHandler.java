package com.galaxyy.lifelocke.networking;

import com.galaxyy.lifelocke.entity.custom.GrassMobEntity;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.entity.Entity;

public class GrassMobAnimationS2CHandler implements ClientPlayNetworking.PlayPayloadHandler<GrassMobAnimationS2CPayload> {
    @Override
    public void receive(GrassMobAnimationS2CPayload grassMobAnimationS2CPayload, ClientPlayNetworking.Context context) {
        Entity entity = context.player().getEntityWorld().getEntity(grassMobAnimationS2CPayload.uuid());
        if (!(entity instanceof GrassMobEntity grassMob)) {
            return;
        }

        switch (GrassMobAnimationS2CPayload.ANIMATION.fromString(grassMobAnimationS2CPayload.animation())) {
            case HIDE -> grassMob.playHidingAnimation = true;
            case UNHIDE -> grassMob.playUnhidingAnimation = true;
            case MAGIC_ATTACK -> grassMob.playMagicAttackAnimation = true;
            case null -> {}
        }
    }
}
