package net.mrbeelo.bsmpc.world;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.SpruceFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.GiantTrunkPlacer;
import net.mrbeelo.bsmpc.BsmpC;
import net.mrbeelo.bsmpc.block.ModBlocks;
import net.mrbeelo.bsmpc.block.custom.CSBerryBushBlock;

import java.util.List;

public class ModConfiguredFeatures {

    //REGISTERING

    public static final RegistryKey<ConfiguredFeature<?, ?>> OVERWORLD_RUBY_ORE_KEY = registerKey("overworld_ruby_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> NETHER_RUBY_ORE_KEY = registerKey("nether_ruby_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> END_RUBY_ORE_KEY = registerKey("end_ruby_ore");

    public static final RegistryKey<ConfiguredFeature<?, ?>> ROSE_KEY = registerKey("rose");

    public static final RegistryKey<ConfiguredFeature<?, ?>> CS_KEY = registerKey("cs");

    public static final RegistryKey<ConfiguredFeature<?, ?>> RUBY_GEODE_KEY = registerKey("ruby_geode");

    public static final RegistryKey<ConfiguredFeature<?, ?>> CS_BERRY_BUSH_KEY = registerKey("cs_berry_bush");

    public static final RegistryKey<ConfiguredFeature<?, ?>> CS_BIOME_DELIBERILIUM_ORE_KEY = registerKey("cs_biome_deliberilium_ore");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneOreReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateOreReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherOreReplaceables = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endOreReplaceables = new BlockMatchRuleTest(Blocks.END_STONE);

        List<OreFeatureConfig.Target> overworldRubyTargets = List.of(
                OreFeatureConfig.createTarget(stoneOreReplaceables, ModBlocks.RUBY_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateOreReplaceables, ModBlocks.DEEPSLATE_RUBY_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> netherRubyTargets = List.of(
                OreFeatureConfig.createTarget(netherOreReplaceables, ModBlocks.NETHER_RUBY_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> endRubyTargets = List.of(
                OreFeatureConfig.createTarget(endOreReplaceables, ModBlocks.END_RUBY_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> csBiomeDeliberiliumTargets = List.of(
                OreFeatureConfig.createTarget(stoneOreReplaceables, ModBlocks.DELIBERILIUM_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateOreReplaceables, ModBlocks.DEEPSLATE_DELIBERILIUM_ORE.getDefaultState()));

        register(context, OVERWORLD_RUBY_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldRubyTargets, 3, 0.2F));
        register(context, NETHER_RUBY_ORE_KEY, Feature.ORE, new OreFeatureConfig(netherRubyTargets, 3, 0.2F));
        register(context, END_RUBY_ORE_KEY, Feature.ORE, new OreFeatureConfig(endRubyTargets, 1));

        register(context, ROSE_KEY, Feature.FLOWER, new RandomPatchFeatureConfig(32, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.ROSE)))));

        register(context, CS_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.CS_LOG),
                new GiantTrunkPlacer(20, 7, 14),
                BlockStateProvider.of(ModBlocks.CS_LEAVES),
                new SpruceFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(5), ConstantIntProvider.create(20)),
                new TwoLayersFeatureSize(1, 0, 2)).dirtProvider(BlockStateProvider.of(Blocks.GRASS_BLOCK)).build());

        register(context, RUBY_GEODE_KEY, Feature.GEODE, new GeodeFeatureConfig(new GeodeLayerConfig(BlockStateProvider.of(Blocks.AIR),
                BlockStateProvider.of(Blocks.DEEPSLATE),
                BlockStateProvider.of(ModBlocks.DEEPSLATE_RUBY_ORE),
                BlockStateProvider.of(Blocks.DEEPSLATE),
                BlockStateProvider.of(Blocks.AIR),
                List.of(Blocks.AIR.getDefaultState()),
                BlockTags.FEATURES_CANNOT_REPLACE , BlockTags.GEODE_INVALID_BLOCKS),
                new GeodeLayerThicknessConfig(1.0D, 0.75D, 1.5D, 2.0D),
                new GeodeCrackConfig(0.25D, 1.5D, 1),
                0.5D, 0.1D,
                true, UniformIntProvider.create(3, 8),
                UniformIntProvider.create(2, 6), UniformIntProvider.create(1, 2),
                -8, 8, 0.075D, 1));

        register(context, CS_BERRY_BUSH_KEY, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.CS_BERRY_BUSH
                                .getDefaultState().with(CSBerryBushBlock.AGE, 3))),
                        List.of(Blocks.GRASS_BLOCK)));

        register(context, CS_BIOME_DELIBERILIUM_ORE_KEY, Feature.ORE, new OreFeatureConfig(csBiomeDeliberiliumTargets, 3, 0.2F));

        //METHODS

        RegistryEntryLookup<PlacedFeature> registryLookup = context.getRegistryLookup(RegistryKeys.PLACED_FEATURE);


    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, BsmpC.id(name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
