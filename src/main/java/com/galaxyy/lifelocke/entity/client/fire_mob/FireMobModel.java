package com.galaxyy.lifelocke.entity.client.fire_mob;

import com.galaxyy.lifelocke.LifeLocke;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class FireMobModel extends EntityModel<FireMobRenderState> {
    public static final ModelLayerLocation FIRE_MOB = new ModelLayerLocation(Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "fire_mob"), "main");
    public final ModelPart firemob;
    public final ModelPart head;
    private final KeyframeAnimation idleAnimation;

    public FireMobModel(ModelPart root) {
        super(root);
        this.firemob = root.getChild("firemob");
        this.head = this.firemob.getChild("head");
        this.idleAnimation = FireMobAnimations.FIRE_MOB_IDLE_ANIM.bake(root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition firemob = modelPartData.addOrReplaceChild("firemob", CubeListBuilder.create(), PartPose.rotation(0.0F, 0.0F, 0.0F));

        PartDefinition leg1 = firemob.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, 21.0F, 3.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, 0.0F, 0.0F));

        PartDefinition leg3 = firemob.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, 21.0F, -6.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, 0.0F, 0.0F));

        PartDefinition leg2 = firemob.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(22, 16).addBox(3.0F, 21.0F, -6.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, 0.0F, 0.0F));

        PartDefinition leg4 = firemob.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(22, 21).addBox(3.0F, 21.0F, 3.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, 0.0F, 0.0F));

        PartDefinition body = firemob.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, 16.0F, -6.0F, 10.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, 0.0F, 0.0F));

        PartDefinition head = firemob.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, 13.0F, -9.0F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, 0.0F, 0.0F));
        return LayerDefinition.create(modelData, 64, 64);
    }

    @Override
    public void setupAnim(FireMobRenderState state) {
        super.setupAnim(state);
        this.setHeadAngles(state.yRot, state.xRot);

        this.idleAnimation.apply(state.idleAnimationState, state.ageInTicks);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30.0F, 30.0F);
        headPitch = Mth.clamp(headPitch, -25.0F, 45.0F);

        this.head.yRot = headYaw * (float) (Math.PI / 180.0);
        this.head.xRot = headPitch * (float) (Math.PI / 180.0);
    }
}