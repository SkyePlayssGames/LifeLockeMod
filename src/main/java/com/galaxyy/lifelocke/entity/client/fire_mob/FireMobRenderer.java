package com.galaxyy.lifelocke.entity.client.fire_mob;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.entity.custom.FireMobEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class FireMobRenderer extends MobRenderer<FireMobEntity, FireMobRenderState, FireMobModel> {

    public FireMobRenderer(EntityRendererProvider.Context context) {
        super(context, new FireMobModel(context.bakeLayer(FireMobModel.FIRE_MOB)), 0.5f);

    }

    @Override
    public Identifier getTextureLocation(FireMobRenderState state) {
        return Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "textures/entity/fire_mob.png");
    }

    @Override
    public FireMobRenderState createRenderState() {
        return new FireMobRenderState();
    }

    @Override
    public void extractRenderState(FireMobEntity fireMob, FireMobRenderState fireMobRenderState, float f) {
        super.extractRenderState(fireMob, fireMobRenderState, f);
        fireMobRenderState.idleAnimationState = fireMob.idleAnimationState;
    }
}
