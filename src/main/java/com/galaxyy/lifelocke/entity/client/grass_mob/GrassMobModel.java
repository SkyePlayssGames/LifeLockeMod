package com.galaxyy.lifelocke.entity.client.grass_mob;

import com.galaxyy.lifelocke.LifeLocke;
import com.galaxyy.lifelocke.entity.client.fire_mob.FireMobRenderState;
import com.galaxyy.lifelocke.entity.custom.GrassMobEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class GrassMobModel extends EntityModel<GrassMobRenderState> {
    public static final EntityModelLayer GRASS_MOB = new EntityModelLayer(Identifier.of(LifeLocke.MOD_ID, "grass_mob"), "main");
    private final ModelPart grass_mob;
    private final ModelPart base;
    private final ModelPart lower_petals;
    private final ModelPart higher_petals;
    private final ModelPart stem;
    private final ModelPart head;
    private final ModelPart head_petals;
    private final ModelPart top_left_petal;
    private final ModelPart bottom_left_petal;
    private final ModelPart top_right_petal;
    private final ModelPart bottom_right_petal;
    private final ModelPart top_petal;
    private final ModelPart right_petal;
    private final ModelPart bottom_petal;
    private final ModelPart left_petal;
    public GrassMobModel(ModelPart root) {
        super(root);
        this.grass_mob = root.getChild("grass_mob");
        this.base = this.grass_mob.getChild("base");
        this.lower_petals = this.base.getChild("lower_petals");
        this.higher_petals = this.base.getChild("higher_petals");
        this.stem = this.grass_mob.getChild("stem");
        this.head = this.grass_mob.getChild("head");
        this.head_petals = this.head.getChild("head_petals");
        this.top_left_petal = this.head_petals.getChild("top_left_petal");
        this.bottom_left_petal = this.head_petals.getChild("bottom_left_petal");
        this.top_right_petal = this.head_petals.getChild("top_right_petal");
        this.bottom_right_petal = this.head_petals.getChild("bottom_right_petal");
        this.top_petal = this.head_petals.getChild("top_petal");
        this.right_petal = this.head_petals.getChild("right_petal");
        this.bottom_petal = this.head_petals.getChild("bottom_petal");
        this.left_petal = this.head_petals.getChild("left_petal");
    }
    public static TexturedModelData getTexturedModelData() {
        final int Y = 24;

        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData grass_mob = modelPartData.addChild("grass_mob", ModelPartBuilder.create(), ModelTransform.rotation(0.0F, 0.0F, 0.0F));

        ModelPartData base = grass_mob.addChild("base", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -1.0F+Y, -5.0F, 10.0F, 1.0F, 10.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, 0.0F, 0.0F));

        ModelPartData lower_petals = base.addChild("lower_petals", ModelPartBuilder.create().uv(0, 21).cuboid(-2.0F, -2.0F+Y, -6.0F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(28, 18).cuboid(-2.0F, -2.0F+Y, 4.0F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, 0.0F, 0.0F));

        ModelPartData cube_r1 = lower_petals.addChild("cube_r1", ModelPartBuilder.create().uv(12, 28).cuboid(-10.0F, -2.0F+Y, 2.0F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 0.0F, -8.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData cube_r2 = lower_petals.addChild("cube_r2", ModelPartBuilder.create().uv(0, 25).cuboid(-10.0F, -2.0F+Y, 2.0F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 0.0F, -8.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData higher_petals = base.addChild("higher_petals", ModelPartBuilder.create().uv(28, 22).cuboid(5.0F, -3.0F+Y, -4.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(24, 28).cuboid(5.0F, -3.0F+Y, 1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 29).cuboid(-7.0F, -3.0F+Y, 1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(10, 32).cuboid(-7.0F, -3.0F+Y, -4.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, 0.0F, 0.0F));

        ModelPartData cube_r3 = higher_petals.addChild("cube_r3", ModelPartBuilder.create().uv(34, 27).cuboid(-1.0F, -2.0F+Y, -1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -1.0F, 6.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData cube_r4 = higher_petals.addChild("cube_r4", ModelPartBuilder.create().uv(0, 34).cuboid(-1.0F, -2.0F+Y, -1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, -1.0F, 6.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData cube_r5 = higher_petals.addChild("cube_r5", ModelPartBuilder.create().uv(30, 33).cuboid(-1.0F, -2.0F+Y, -1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, -1.0F, -6.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData cube_r6 = higher_petals.addChild("cube_r6", ModelPartBuilder.create().uv(20, 33).cuboid(-1.0F, -2.0F+Y, -1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -1.0F, -6.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData stem = grass_mob.addChild("stem", ModelPartBuilder.create().uv(20, 18).cuboid(-1.0F, -8.0F+Y, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F))
                .uv(12, 21).cuboid(0.0F, -12.0F+Y, 0.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F))
                .uv(10, 37).cuboid(-1.0F, -14.0F+Y, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, 0.0F, 0.0F));

        ModelPartData head = grass_mob.addChild("head", ModelPartBuilder.create().uv(0, 11).cuboid(-4.0F, -18.0F+Y, -4.0F, 8.0F, 8.0F, 2.0F, new Dilation(0.0F))
                .uv(20, 11).cuboid(-3.0F, -16.0F+Y, -3.0F, 6.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, 0.0F, 0.0F));

        ModelPartData head_petals = head.addChild("head_petals", ModelPartBuilder.create(), ModelTransform.rotation(0.0F, 0.0F, 0.0F));

        ModelPartData top_left_petal = head_petals.addChild("top_left_petal", ModelPartBuilder.create().uv(10, 29).cuboid(-7.0F, -20.0F+Y, -4.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(38, 24).cuboid(-6.0F, -20.0F+Y, -4.0F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F))
                .uv(20, 32).cuboid(-6.0F, -21.0F+Y, -4.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.rotation(-0.0F, -0.0F, -0.0F));

        ModelPartData bottom_left_petal = head_petals.addChild("bottom_left_petal", ModelPartBuilder.create().uv(8, 39).cuboid(-2.0F-Y, 0.0F, -1.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(26, 38).cuboid(-1.0F-Y, 0.0F, -1.0F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 39).cuboid(-1.0F-Y, -1.0F, -1.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, -9.0F, -3.0F, 0.0F, 0.0F, -1.5708F));

        ModelPartData top_right_petal = head_petals.addChild("top_right_petal", ModelPartBuilder.create().uv(40, 3).cuboid(-2.0F+Y, 0.0F, -1.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(32, 38).cuboid(-1.0F+Y, 0.0F, -1.0F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F))
                .uv(4, 39).cuboid(-1.0F+Y, -1.0F, -1.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(6.0F, -19.0F, -3.0F, 0.0F, 0.0F, 1.5708F));

        ModelPartData bottom_right_petal = head_petals.addChild("bottom_right_petal", ModelPartBuilder.create().uv(4, 40).cuboid(-2.0F, 0.0F-Y, -1.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(38, 38).cuboid(-1.0F, 0.0F-Y, -1.0F, 3.0F, 3.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 40).cuboid(-1.0F, -1.0F-Y, -1.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, -8.0F, -3.0F, 0.0F, 0.0F, -3.1416F));

        ModelPartData top_petal = head_petals.addChild("top_petal", ModelPartBuilder.create().uv(38, 16).cuboid(-2.0F, -20.0F+Y, -4.0F, 4.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(38, 11).cuboid(-3.0F, -21.0F+Y, -4.0F, 6.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(40, 0).cuboid(-1.0F, -22.0F+Y, -4.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, 0, 0F));

        ModelPartData right_petal = head_petals.addChild("right_petal", ModelPartBuilder.create().uv(18, 38).cuboid(-2.0F+Y, -2.0F, 0.0F, 4.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(38, 12).cuboid(-3.0F+Y, -3.0F, 0.0F, 6.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(40, 1).cuboid(-1.0F+Y, -4.0F, 0.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, -14.0F, -4.0F, 0.0F, 0.0F, 1.5708F));

        ModelPartData bottom_petal = head_petals.addChild("bottom_petal", ModelPartBuilder.create().uv(38, 22).cuboid(-2.0F, -2.0F-Y, 0.0F, 4.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(38, 13).cuboid(-3.0F, -3.0F-Y, 0.0F, 6.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(28, 27).cuboid(-1.0F, -4.0F-Y, 0.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -10.0F, -4.0F, 0.0F, 0.0F, -3.1416F));

        ModelPartData left_petal = head_petals.addChild("left_petal", ModelPartBuilder.create().uv(38, 14).cuboid(-2.0F-Y, -2.0F, 0.0F, 4.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(34, 32).cuboid(-3.0F-Y, -3.0F, 0.0F, 6.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(40, 2).cuboid(-1.0F-Y, -4.0F, 0.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, -14.0F, -4.0F, 0.0F, 0.0F, -1.5708F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(GrassMobRenderState state) {
        super.setAngles(state);
        this.setHeadAngles(state.relativeHeadYaw, state.pitch);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.head.yaw = headYaw * (float) (Math.PI / 180.0);
        this.head.pitch = headPitch * (float) (Math.PI / 180.0);
    }
}