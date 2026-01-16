package com.galaxyy.lifelocke.entity.client.ghost_mob;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.entity.custom.GhostMobEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class GhostMobRenderer extends MobRenderer<GhostMobEntity, GhostMobRenderState, GhostMobModel> {
    public GhostMobRenderer(EntityRendererProvider.Context context) {
        super(context, new GhostMobModel(context.bakeLayer(GhostMobModel.GHOST_MOB)), 0.4f);
    }

    @Override
    public Identifier getTextureLocation(GhostMobRenderState livingEntityRenderState) {
        return Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "textures/entity/ghost_mob.png");
    }

    @Override
    public GhostMobRenderState createRenderState() {
        return new GhostMobRenderState();
    }

    @Override
    public void extractRenderState(GhostMobEntity ghostMobEntity, GhostMobRenderState ghostMobRenderState, float f) {
        super.extractRenderState(ghostMobEntity, ghostMobRenderState, f);

        ghostMobRenderState.attackAnimationState = ghostMobEntity.attackAnimationState;
    }
}
