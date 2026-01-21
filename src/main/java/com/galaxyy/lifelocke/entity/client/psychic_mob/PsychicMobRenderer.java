package com.galaxyy.lifelocke.entity.client.psychic_mob;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.entity.custom.PsychicMobEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class PsychicMobRenderer extends MobRenderer<PsychicMobEntity, PsychicMobRenderState, PsychicMobModel> {
    public PsychicMobRenderer(EntityRendererProvider.Context context) {
        super(context, new PsychicMobModel(context.bakeLayer(PsychicMobModel.PSYCHIC_MOB)), 1);
    }

    @Override
    public Identifier getTextureLocation(PsychicMobRenderState livingEntityRenderState) {
        return Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "textures/entity/psychic_mob.png");
    }

    @Override
    public PsychicMobRenderState createRenderState() {
        return new PsychicMobRenderState();
    }
}
