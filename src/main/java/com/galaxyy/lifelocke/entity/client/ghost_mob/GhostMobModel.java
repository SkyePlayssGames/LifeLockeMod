// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.galaxyy.lifelocke.entity.client.ghost_mob;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.entity.client.grass_mob.GrassMobRenderState;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

import javax.swing.text.html.parser.Entity;

public class GhostMobModel extends EntityModel<GhostMobRenderState> {
    public static final ModelLayerLocation GHOST_MOB = new ModelLayerLocation(Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "ghost_mob"), "main");
    private final ModelPart head;

    private final KeyframeAnimation attackAnimation;

    public GhostMobModel(ModelPart root) {
        super(root);
        this.head = root.getChild("root").getChild("body").getChild("head");

        attackAnimation = GhostMobAnimations.attack.bake(root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, -1.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -25.0F, 0.0F, 6.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, 0.0F, 0.0F));

        PartDefinition legs = body.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.rotation(0.0F, 0.0F, 0.0F));

        PartDefinition leg1 = legs.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(22, 0).addBox(-3.0F, -13.0F, 2.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, 0.0F, 0.0F));

        PartDefinition leg2 = legs.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(22, 15).addBox(1.0F, -13.0F, 2.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, 0.0F, 0.0F));

        PartDefinition arms = body.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.rotation(0.0F, 0.0F, 0.0F));

        PartDefinition arm1 = arms.addOrReplaceChild("arm1", CubeListBuilder.create().texOffs(0, 28).addBox(-5.0F, -24.0F, 1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, 0.0F, 0.0F));

        PartDefinition arm2 = arms.addOrReplaceChild("arm2", CubeListBuilder.create().texOffs(8, 28).addBox(3.0F, -24.0F, 1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, 0.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 17).addBox(-3.0F, -29.0F, -3.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, 0.0F, 0.0F));
        return LayerDefinition.create(modelData, 64, 64);
    }

    @Override
    public void setupAnim(GhostMobRenderState state) {
        super.setupAnim(state);
        this.setHeadAngles(state.yRot, state.xRot);

        this.attackAnimation.apply(state.attackAnimationState, state.ageInTicks);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30.0F, 30.0F);
        headPitch = Mth.clamp(headPitch, -25.0F, 45.0F);

        this.head.yRot = headYaw * (float) (Math.PI / 180.0);
        this.head.xRot = headPitch * (float) (Math.PI / 180.0);
    }
}