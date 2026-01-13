package com.galaxyy.lifelocke.networking;

import com.galaxyy.lifelocke.entity.custom.GrassMobEntity;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.world.entity.Entity;

public class GrassMobAnimationS2CHandler implements ClientPlayNetworking.PlayPayloadHandler<GrassMobAnimationS2CPayload> {
    @Override
    public void receive(GrassMobAnimationS2CPayload grassMobAnimationS2CPayload, ClientPlayNetworking.Context context) {
        Entity entity = context.player().level().getEntity(grassMobAnimationS2CPayload.uuid());
        if (!(entity instanceof GrassMobEntity)) {
            if (entity == null) {
                System.err.println("Tried playing a Grass Mob Animation on a null entity");
            } else {
                System.err.println("Tried playing a Grass Mob Animation on entity \"" + entity.getType() +
                        "\" and UUID " + entity.getUUID() + "\"");
            }
        }

        assert entity instanceof GrassMobEntity;
        GrassMobEntity grassMob = ((GrassMobEntity) entity);

        switch (GrassMobAnimationS2CPayload.ANIMATION.fromString(grassMobAnimationS2CPayload.animation())) {
            case HIDE -> grassMob.playHidingAnimation = true;
            case UNHIDE -> grassMob.playUnhidingAnimation = true;
            case MAGIC_ATTACK -> grassMob.playMagicAttackAnimation = true;
            case null -> System.err.println("Tried playing invalid animation \"" + grassMobAnimationS2CPayload.animation() +
                    "\" for GrassMobEntity with UUID \"" + grassMob.getStringUUID() + "\"");
        }
    }
}
