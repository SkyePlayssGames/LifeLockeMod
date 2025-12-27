package com.galaxyy.lifelocke.entity.client;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.entity.custom.FireMobEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class FireMobRenderer extends MobEntityRenderer<FireMobEntity, FireMobRenderState, FireMobModel> {

    public FireMobRenderer(EntityRendererFactory.Context context) {
        super(context, new FireMobModel(context.getPart(FireMobModel.FIRE_MOB)), 0.5f);

    }

    @Override
    public Identifier getTexture(FireMobRenderState state) {
        return Identifier.of(LifeLocke.MOD_ID, "textures/entity/fire_mob.png");
    }

    @Override
    public FireMobRenderState createRenderState() {
        return new FireMobRenderState();
    }

    @Override
    public void updateRenderState(FireMobEntity fireMob, FireMobRenderState fireMobRenderState, float f) {
        super.updateRenderState(fireMob, fireMobRenderState, f);
        fireMobRenderState.idleAnimationState = fireMob.idleAnimationState;
    }
}
