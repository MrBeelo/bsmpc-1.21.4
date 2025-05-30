package net.mrbeelo.bsmpc.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mrbeelo.bsmpc.BsmpC;

public class ModParticles {

    public static final SimpleParticleType SPARKLE_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType MOBILIUM_PARTICLE = FabricParticleTypes.simple();

    public static void registerModParticles() {
        BsmpC.LOGGER.info("Registering Mod Particles for " + BsmpC.MOD_ID);

        Registry.register(Registries.PARTICLE_TYPE, BsmpC.id("sparkle_particle"), SPARKLE_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, BsmpC.id("mobilium_particle"), MOBILIUM_PARTICLE);
    }

}
