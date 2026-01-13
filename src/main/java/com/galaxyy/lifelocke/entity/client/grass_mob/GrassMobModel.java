package com.galaxyy.lifelocke.entity.client.grass_mob;

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

public class GrassMobModel extends EntityModel<GrassMobRenderState> {
    public static final ModelLayerLocation GRASS_MOB = new ModelLayerLocation(Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "grass_mob"), "main");
    private final ModelPart head;

    private final KeyframeAnimation hidingAnimation;
    private final KeyframeAnimation unhidingAnimation;
    private final KeyframeAnimation magicAttackAnimation;

    public GrassMobModel(ModelPart root) {
        super(root);
        ModelPart grass_mob = root.getChild("grass_mob");
        this.head = grass_mob.getChild("head");

        this.hidingAnimation = GrassMobAnimations.HIDING_ANIM.bake(root);
        this.unhidingAnimation = GrassMobAnimations.UNHIDING_ANIM.bake(root);
        this.magicAttackAnimation = GrassMobAnimations.MAGIC_ATTACK_ANIM.bake(root);
    }
    public static LayerDefinition getTexturedModelData() {
        final int Y = 24;

        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition grass_mob = modelPartData.addOrReplaceChild("grass_mob", CubeListBuilder.create(), PartPose.rotation(0.0F, 0.0F, 0.0F));

        PartDefinition base = grass_mob.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -1.0F+Y, -5.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, 0.0F, 0.0F));

        PartDefinition lower_petals = base.addOrReplaceChild("lower_petals", CubeListBuilder.create().texOffs(0, 21).addBox(-2.0F, -2.0F+Y, -6.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(28, 18).addBox(-2.0F, -2.0F+Y, 4.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = lower_petals.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(12, 28).addBox(-10.0F, -2.0F+Y, 2.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, -8.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition cube_r2 = lower_petals.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 25).addBox(-10.0F, -2.0F+Y, 2.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, -8.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition higher_petals = base.addOrReplaceChild("higher_petals", CubeListBuilder.create().texOffs(28, 22).addBox(5.0F, -3.0F+Y, -4.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(24, 28).addBox(5.0F, -3.0F+Y, 1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 29).addBox(-7.0F, -3.0F+Y, 1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(10, 32).addBox(-7.0F, -3.0F+Y, -4.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r3 = higher_petals.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(34, 27).addBox(-1.0F, -2.0F+Y, -1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -1.0F, 6.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition cube_r4 = higher_petals.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 34).addBox(-1.0F, -2.0F+Y, -1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, 6.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition cube_r5 = higher_petals.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(30, 33).addBox(-1.0F, -2.0F+Y, -1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, -6.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition cube_r6 = higher_petals.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(20, 33).addBox(-1.0F, -2.0F+Y, -1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -1.0F, -6.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition stem = grass_mob.addOrReplaceChild("stem", CubeListBuilder.create().texOffs(20, 18).addBox(-1.0F, -8.0F+Y, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(12, 21).addBox(0.0F, -12.0F+Y, 0.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(10, 37).addBox(-1.0F, -14.0F+Y, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, 0.0F, 0.0F));

        PartDefinition head = grass_mob.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 11).addBox(-4.0F, -18.0F+Y, -4.0F, 8.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(20, 11).addBox(-3.0F, -16.0F+Y, -3.0F, 6.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, 0.0F, 0.0F));

        PartDefinition head_petals = head.addOrReplaceChild("head_petals", CubeListBuilder.create(), PartPose.rotation(0.0F, 0.0F, 0.0F));

        PartDefinition top_left_petal = head_petals.addOrReplaceChild("top_left_petal", CubeListBuilder.create().texOffs(10, 29).addBox(-7.0F, -20.0F+Y, -3.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(38, 24).addBox(-6.0F, -20.0F+Y, -3.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(20, 32).addBox(-6.0F, -21.0F+Y, -3.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.rotation(-0.0F, -0.0F, -0.0F));

        PartDefinition bottom_left_petal = head_petals.addOrReplaceChild("bottom_left_petal", CubeListBuilder.create().texOffs(8, 39).addBox(-2.0F-Y, 0.0F, -0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(26, 38).addBox(-1.0F-Y, 0.0F, -0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 39).addBox(-1.0F-Y, -1.0F, -0.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -9.0F, -3.0F, 0.0F, 0.0F, -1.5708F));

        PartDefinition top_right_petal = head_petals.addOrReplaceChild("top_right_petal", CubeListBuilder.create().texOffs(40, 3).addBox(-2.0F+Y, 0.0F, -0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(32, 38).addBox(-1.0F+Y, 0.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(4, 39).addBox(-1.0F+Y, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -19.0F, -3.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition bottom_right_petal = head_petals.addOrReplaceChild("bottom_right_petal", CubeListBuilder.create().texOffs(4, 40).addBox(-2.0F, 0.0F-Y, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(38, 38).addBox(-1.0F, 0.0F-Y, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 40).addBox(-1.0F, -1.0F-Y, 0.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -8.0F, -3.0F, 0.0F, 0.0F, -3.1416F));

        PartDefinition top_petal = head_petals.addOrReplaceChild("top_petal", CubeListBuilder.create().texOffs(38, 16).addBox(-2.0F, -20.0F+Y, -3.0F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(38, 11).addBox(-3.0F, -21.0F+Y, -3.0F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(40, 0).addBox(-1.0F, -22.0F+Y, -3.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, 0, 0F));

        PartDefinition right_petal = head_petals.addOrReplaceChild("right_petal", CubeListBuilder.create().texOffs(18, 38).addBox(-2.0F+Y, -2.0F, 1.0F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(38, 12).addBox(-3.0F+Y, -3.0F, 1.0F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(40, 1).addBox(-1.0F+Y, -4.0F, 1.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -14.0F, -4.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition bottom_petal = head_petals.addOrReplaceChild("bottom_petal", CubeListBuilder.create().texOffs(38, 22).addBox(-2.0F, -2.0F-Y, 1.0F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(38, 13).addBox(-3.0F, -3.0F-Y, 1.0F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(28, 27).addBox(-1.0F, -4.0F-Y, 1.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, -4.0F, 0.0F, 0.0F, -3.1416F));

        PartDefinition left_petal = head_petals.addOrReplaceChild("left_petal", CubeListBuilder.create().texOffs(38, 14).addBox(-2.0F-Y, -2.0F, 1.0F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(34, 32).addBox(-3.0F-Y, -3.0F, 1.0F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(40, 2).addBox(-1.0F-Y, -4.0F, 1.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -14.0F, -4.0F, 0.0F, 0.0F, -1.5708F));
        return LayerDefinition.create(modelData, 64, 64);
    }

    @Override
    public void setupAnim(GrassMobRenderState state) {
        super.setupAnim(state);
        this.setHeadAngles(state.yRot, state.xRot);

        this.hidingAnimation.apply(state.hidingAnimationState, state.ageInTicks);
        this.unhidingAnimation.apply(state.unhidingAnimationState, state.ageInTicks);
        this.magicAttackAnimation.apply(state.magicAttackAnimationState, state.ageInTicks);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30.0F, 30.0F);
        headPitch = Mth.clamp(headPitch, -25.0F, 45.0F);

        this.head.yRot = headYaw * (float) (Math.PI / 180.0);
        this.head.xRot = headPitch * (float) (Math.PI / 180.0);
    }
}