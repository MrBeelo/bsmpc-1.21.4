package net.mrbeelo.bsmpc.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.mrbeelo.bsmpc.world.ModPlacedFeatures;
import net.mrbeelo.bsmpc.world.biome.ModBiomes;

public class ModTreeGeneration {
    public static void generateTrees() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.CS_BIOME),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.CS_PLACED_KEY);
    }
}
