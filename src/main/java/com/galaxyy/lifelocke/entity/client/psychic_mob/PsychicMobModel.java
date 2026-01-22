package com.galaxyy.lifelocke.entity.client.psychic_mob;

import com.galaxyy.lifelocke.LifeLocke;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AnimationState;

public class PsychicMobModel extends EntityModel<PsychicMobRenderState> {
    public static final ModelLayerLocation PSYCHIC_MOB = new ModelLayerLocation(Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "psychic_mob"), "main");
    private final ModelPart head;

    private final KeyframeAnimation idleAnimation;

    public PsychicMobModel(ModelPart root) {
        super(root);
        this.head = root.getChild("root").getChild("swirls").getChild("head");

        this.idleAnimation = PsychicMobAnimations.IDLE_ANIM.bake(root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 23.0F, 0.0F));

        PartDefinition swirls = root.addOrReplaceChild("swirls", CubeListBuilder.create(), PartPose.rotation(0.0F, 0.0F, 0.0F));

        PartDefinition layer0 = swirls.addOrReplaceChild("layer0", CubeListBuilder.create().texOffs(38, 8).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(38, 10).addBox(-1.0F, 0.0F, 1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(20, 32).addBox(1.0F, 0.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(20, 37).addBox(-2.0F, 0.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition layer1 = swirls.addOrReplaceChild("layer1", CubeListBuilder.create().texOffs(28, 25).addBox(2.0F, 0.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(28, 30).addBox(-3.0F, 0.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(20, 35).addBox(-2.0F, 0.0F, 2.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(30, 35).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

        PartDefinition layer2 = swirls.addOrReplaceChild("layer2", CubeListBuilder.create().texOffs(0, 25).addBox(-5.0F, 0.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(14, 25).addBox(4.0F, 0.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(24, 8).addBox(-3.0F, 0.0F, 4.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(24, 10).addBox(-3.0F, 0.0F, -5.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

        PartDefinition layer3 = swirls.addOrReplaceChild("layer3", CubeListBuilder.create().texOffs(0, 32).addBox(3.0F, 0.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 37).addBox(-2.0F, 0.0F, -4.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(10, 32).addBox(-4.0F, 0.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(10, 37).addBox(-2.0F, 0.0F, 3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 0.0F));

        PartDefinition layer4 = swirls.addOrReplaceChild("layer4", CubeListBuilder.create().texOffs(0, 23).addBox(-5.0F, 0.0F, -6.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 12).addBox(5.0F, 0.0F, -5.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(22, 12).addBox(-6.0F, 0.0F, -5.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(22, 23).addBox(-5.0F, 0.0F, 5.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

        PartDefinition layer5 = swirls.addOrReplaceChild("layer5", CubeListBuilder.create().texOffs(38, 25).addBox(-1.0F, 0.0F, -7.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(26, 37).addBox(-7.0F, 0.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(38, 27).addBox(-1.0F, 0.0F, 6.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(32, 37).addBox(6.0F, 0.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, 0.0F));

        PartDefinition head = swirls.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

        PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create().texOffs(24, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));
        return LayerDefinition.create(modelData, 64, 64);
    }

    @Override
    public void setupAnim(PsychicMobRenderState state) {
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
