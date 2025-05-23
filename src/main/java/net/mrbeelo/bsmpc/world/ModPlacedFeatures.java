package net.mrbeelo.bsmpc.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import net.mrbeelo.bsmpc.BsmpC;
import net.mrbeelo.bsmpc.block.ModBlocks;

import java.util.List;

public class ModPlacedFeatures {

    //REGISTERING

    public static final RegistryKey<net.minecraft.world.gen.feature.PlacedFeature> OVERWORLD_RUBY_ORE_PLACED_KEY = registerKey("overworld_ruby_ore_placed");
    public static final RegistryKey<net.minecraft.world.gen.feature.PlacedFeature> NETHER_RUBY_ORE_PLACED_KEY = registerKey("nether_ruby_ore_placed");
    public static final RegistryKey<net.minecraft.world.gen.feature.PlacedFeature> END_RUBY_ORE_PLACED_KEY = registerKey("end_ruby_ore_placed");

    public static final RegistryKey<net.minecraft.world.gen.feature.PlacedFeature> ROSE_PLACED_KEY = registerKey("rose_placed");

    public static final RegistryKey<net.minecraft.world.gen.feature.PlacedFeature> CS_PLACED_KEY = registerKey("cs_placed");

    public static final RegistryKey<net.minecraft.world.gen.feature.PlacedFeature> RUBY_GEODE_PLACED_KEY = registerKey("ruby_geode_placed");

    public static final RegistryKey<net.minecraft.world.gen.feature.PlacedFeature> CS_BERRY_BUSH_PLACED_KEY = registerKey("cs_berry_bush_placed");

    public static final RegistryKey<net.minecraft.world.gen.feature.PlacedFeature> CS_BIOME_DELIBERILIUM_ORE_PLACED_KEY = registerKey("cs_biome_deliberilium_ore_placed");

    public static void bootstrap(Registerable<net.minecraft.world.gen.feature.PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, OVERWORLD_RUBY_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.OVERWORLD_RUBY_ORE_KEY),
                ModOrePlacement.modifiersWithCount(5, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(64))));

        register(context, NETHER_RUBY_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.NETHER_RUBY_ORE_KEY),
                ModOrePlacement.modifiersWithCount(5, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(128))));

        register(context, END_RUBY_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.END_RUBY_ORE_KEY),
                ModOrePlacement.modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(128))));

        register(context, ROSE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ROSE_KEY),
                RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        register(context, CS_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.CS_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(1, 0.05f, 1), ModBlocks.CS_SAPLING));

        register(context, RUBY_GEODE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.RUBY_GEODE_KEY),
                RarityFilterPlacementModifier.of(50),
                HeightRangePlacementModifier.trapezoid(YOffset.fixed(-64), YOffset.fixed(16)),
                BiomePlacementModifier.of(),
                SquarePlacementModifier.of());

        register(context, CS_BERRY_BUSH_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.CS_BERRY_BUSH_KEY),
                RarityFilterPlacementModifier.of(32), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        register(context, CS_BIOME_DELIBERILIUM_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.CS_BIOME_DELIBERILIUM_ORE_KEY),
                ModOrePlacement.modifiersWithCount(15, HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(64))));
    }

    //METHODS

    private static RegistryKey<net.minecraft.world.gen.feature.PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, BsmpC.id(name));
    }

    private static void register(Registerable<net.minecraft.world.gen.feature.PlacedFeature> context, RegistryKey<net.minecraft.world.gen.feature.PlacedFeature> key, RegistryEntry<net.minecraft.world.gen.feature.ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new net.minecraft.world.gen.feature.PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<net.minecraft.world.gen.feature.PlacedFeature> context, RegistryKey<net.minecraft.world.gen.feature.PlacedFeature> key,
                                                                                   RegistryEntry<net.minecraft.world.gen.feature.ConfiguredFeature<?, ?>> configuration,
                                                                                   PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
