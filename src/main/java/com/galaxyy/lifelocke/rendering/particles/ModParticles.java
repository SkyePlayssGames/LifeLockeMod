package com.galaxyy.lifelocke.rendering.particles;

import com.galaxyy.lifelocke.LifeLocke;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.GlowParticle;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class ModParticles {
    public static final SimpleParticleType TEST_PARTICLE = FabricParticleTypes.simple();

    public static void registerModParticlesServer() {
        Registry.register(BuiltInRegistries.PARTICLE_TYPE,
                Identifier.fromNamespaceAndPath(LifeLocke.MOD_ID, "test"), TEST_PARTICLE
        );
    }

    public static void registerModParticlesClient() {
        ParticleFactoryRegistry.getInstance().register(TEST_PARTICLE, GlowParticle.WaxOnProvider::new);
    }
}
