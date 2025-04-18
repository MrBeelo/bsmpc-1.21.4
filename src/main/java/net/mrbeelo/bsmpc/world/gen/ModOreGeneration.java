package net.mrbeelo.bsmpc.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import net.mrbeelo.bsmpc.world.ModPlacedFeatures;
import net.mrbeelo.bsmpc.world.dimension.ModDimensions;

public class ModOreGeneration {
    public static void generateOres() {
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.OVERWORLD_RUBY_ORE_PLACED_KEY
        );

        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheNether(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.NETHER_RUBY_ORE_PLACED_KEY
        );

        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheEnd(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.END_RUBY_ORE_PLACED_KEY
        );

        BiomeModifications.addFeature(
                ModDimensions.foundInCSBiome(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                ModPlacedFeatures.CS_BIOME_DELIBERILIUM_ORE_PLACED_KEY
        );
    }
}
