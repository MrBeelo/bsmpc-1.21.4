package net.mrbeelo.bsmpc.world;

import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.dimension.DimensionOptions;
import net.mrbeelo.bsmpc.BsmpC;

import java.util.function.Predicate;

public class ModBiomeSelectors {
    public static Predicate<BiomeSelectionContext> foundInCSBiome() {
        return context -> context.canGenerateIn(CS_DIMENSION);
    }

    public static final RegistryKey<DimensionOptions> CS_DIMENSION = RegistryKey.of(RegistryKeys.DIMENSION, BsmpC.id("cs_dimension"));
}
