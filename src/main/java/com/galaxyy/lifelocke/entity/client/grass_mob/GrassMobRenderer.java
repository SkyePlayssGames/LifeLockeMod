package com.galaxyy.lifelocke.entity.client.grass_mob;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.entity.custom.GrassMobEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class GrassMobRenderer extends MobRenderer<GrassMobEntity, GrassMobRenderState, GrassMobModel> {
    public GrassMobRenderer(EntityRendererProvider.Context context) {
        super(context, new GrassMobModel(context.bakeLayer(GrassMobModel.GRASS_MOB)), 0.65f);
    }

    @Override
    public Identifier getTextureLocation(GrassMobRenderState state) {
        return Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "textures/entity/grass_mob.png");
    }

    @Override
    public GrassMobRenderState createRenderState() {
        return new GrassMobRenderState();
    }

    @Override
    public void extractRenderState(GrassMobEntity grassMob, GrassMobRenderState grassMobRenderState, float f) {
        super.extractRenderState(grassMob, grassMobRenderState, f);

        grassMobRenderState.hidingAnimationState = grassMob.hidingAnimationState;
        grassMobRenderState.unhidingAnimationState = grassMob.unhidingAnimationState;
        grassMobRenderState.magicAttackAnimationState = grassMob.magicAttackAnimationState;
    }
}
