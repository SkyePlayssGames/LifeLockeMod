package com.galaxyy.lifelocke.entity.client;

import com.galaxyy.lifelocke.LifeLocke;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.function.Function;

public class FireMobModel extends EntityModel<FireMobRenderState> {
    public static final EntityModelLayer FIRE_MOB = new EntityModelLayer(Identifier.of(LifeLocke.MOD_ID, "fire_mob"), "main");
    public final ModelPart firemob;
    public final ModelPart head;
    private final Animation idleAnimation;

    public FireMobModel(ModelPart root) {
        super(root);
        this.firemob = root.getChild("firemob");
        this.head = this.firemob.getChild("head");
        this.idleAnimation = FireMobAnimations.FIRE_MOB_IDLE_ANIM.createAnimation(root);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData firemob = modelPartData.addChild("firemob", ModelPartBuilder.create(), ModelTransform.rotation(0.0F, 0.0F, 0.0F));

        ModelPartData leg1 = firemob.addChild("leg1", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, 21.0F, 3.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, 0.0F, 0.0F));

        ModelPartData leg3 = firemob.addChild("leg3", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, 21.0F, -6.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, 0.0F, 0.0F));

        ModelPartData leg2 = firemob.addChild("leg2", ModelPartBuilder.create().uv(22, 16).cuboid(3.0F, 21.0F, -6.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, 0.0F, 0.0F));

        ModelPartData leg4 = firemob.addChild("leg4", ModelPartBuilder.create().uv(22, 21).cuboid(3.0F, 21.0F, 3.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, 0.0F, 0.0F));

        ModelPartData body = firemob.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, 16.0F, -6.0F, 10.0F, 5.0F, 11.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, 0.0F, 0.0F));

        ModelPartData head = firemob.addChild("head", ModelPartBuilder.create().uv(0, 16).cuboid(-3.0F, 13.0F, -9.0F, 6.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(FireMobRenderState state) {
        super.setAngles(state);
        this.setHeadAngles(state.relativeHeadYaw, state.pitch);

        this.idleAnimation.apply(state.idleAnimationState, state.age);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.head.yaw = headYaw * (float) (Math.PI / 180.0);
        this.head.pitch = headPitch * (float) (Math.PI / 180.0);
    }
}