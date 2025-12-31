package com.galaxyy.lifelocke.entity.client.grass_mob;

import net.minecraft.client.render.entity.animation.AnimationDefinition;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;


public class GrassMobAnimations {
    public static final AnimationDefinition hide = AnimationDefinition.Builder.create(1.0F)
            .addBoneAnimation("stem", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -6.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("stem", new Transformation(Transformation.Targets.SCALE,
                    new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 0.7F, 1.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("head", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(-26.8858F, 13.0372F, 1.3814F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("head", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(1.5F, -6.0F, 5.0F), Transformation.Interpolations.CUBIC)
            ))
            .build();

    public static final AnimationDefinition unhide = AnimationDefinition.Builder.create(1.0F)
            .addBoneAnimation("stem", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -6.0F, 0.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("stem", new Transformation(Transformation.Targets.SCALE,
                    new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 0.7F, 1.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("head", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-26.8858F, 13.0372F, 1.3814F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .addBoneAnimation("head", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(1.5F, -6.0F, 5.0F), Transformation.Interpolations.CUBIC),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.CUBIC)
            ))
            .build();
}