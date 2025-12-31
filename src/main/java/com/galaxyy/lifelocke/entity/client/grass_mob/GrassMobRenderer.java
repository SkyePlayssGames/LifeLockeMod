package com.galaxyy.lifelocke.entity.client.grass_mob;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.entity.custom.GrassMobEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class GrassMobRenderer extends MobEntityRenderer<GrassMobEntity, GrassMobRenderState, GrassMobModel> {
    public GrassMobRenderer(EntityRendererFactory.Context context) {
        super(context, new GrassMobModel(context.getPart(GrassMobModel.GRASS_MOB)), 0.65f);
    }

    @Override
    public Identifier getTexture(GrassMobRenderState state) {
        return Identifier.of(LifeLocke.MOD_ID, "textures/entity/grass_mob.png");
    }

    @Override
    public GrassMobRenderState createRenderState() {
        return new GrassMobRenderState();
    }

    @Override
    public void updateRenderState(GrassMobEntity grassMob, GrassMobRenderState grassMobRenderState, float f) {
        super.updateRenderState(grassMob, grassMobRenderState, f);

        grassMobRenderState.hidingAnimationState = grassMob.hidingAnimationState;
        grassMobRenderState.unhidingAnimationState = grassMob.unhidingAnimationState;
    }
}
