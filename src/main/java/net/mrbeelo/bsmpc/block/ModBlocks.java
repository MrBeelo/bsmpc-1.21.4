package net.mrbeelo.bsmpc.block;

import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.mrbeelo.bsmpc.BsmpC;
import net.mrbeelo.bsmpc.block.custom.*;
import net.mrbeelo.bsmpc.components.ModDataComponentTypes;
import net.mrbeelo.bsmpc.effect.ModEffects;
import net.mrbeelo.bsmpc.item.ModItems;
import net.mrbeelo.bsmpc.sound.ModSounds;
import net.mrbeelo.bsmpc.world.tree.ModSaplingGenerators;

public class ModBlocks {

    //REGISTERING

    public static RegistryKey<Block> regKeyBlock(String name)
    {
        return RegistryKey.of(RegistryKeys.BLOCK, BsmpC.id(name));
    }


    public static final Block RUBY_BLOCK = registerWithItem("ruby_block", new Block(AbstractBlock.Settings.copy(Blocks.EMERALD_BLOCK).registryKey(regKeyBlock("ruby_block")).sounds(ModSounds.RUBY_BLOCK_SOUNDS)), new Item.Settings().registryKey(ModItems.regKeyItem("ruby_block")).useBlockPrefixedTranslationKey().maxCount(65));
    public static final Block RUBY_ORE = registerWithItem("ruby_ore", new Block(AbstractBlock.Settings.copy(Blocks.EMERALD_ORE).registryKey(regKeyBlock("ruby_ore")).resistance(6000F)), new Item.Settings().registryKey(ModItems.regKeyItem("ruby_ore")).useBlockPrefixedTranslationKey());
    public static final Block DEEPSLATE_RUBY_ORE = registerWithItem("deepslate_ruby_ore", new Block(AbstractBlock.Settings.copy(Blocks.DEEPSLATE_EMERALD_ORE).registryKey(regKeyBlock("deepslate_ruby_ore")).resistance(6000F)), new Item.Settings().registryKey(ModItems.regKeyItem("deepslate_ruby_ore")).useBlockPrefixedTranslationKey());
    public static final Block NETHER_RUBY_ORE = registerWithItem("nether_ruby_ore", new Block(AbstractBlock.Settings.copy(Blocks.EMERALD_ORE).registryKey(regKeyBlock("nether_ruby_ore")).resistance(6000F)), new Item.Settings().registryKey(ModItems.regKeyItem("nether_ruby_ore")).useBlockPrefixedTranslationKey());
    public static final Block END_RUBY_ORE = registerWithItem("end_ruby_ore", new Block(AbstractBlock.Settings.copy(Blocks.EMERALD_ORE).registryKey(regKeyBlock("end_ruby_ore")).resistance(6000F)), new Item.Settings().registryKey(ModItems.regKeyItem("end_ruby_ore")).useBlockPrefixedTranslationKey());

    public static final SMRUBlock SMOOTH_QUARTZ_RUBY_UPGRADER = registerWithItem("smooth_quartz_ruby_upgrader", new SMRUBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_QUARTZ).registryKey(regKeyBlock("smooth_quartz_ruby_upgrader"))), new Item.Settings().registryKey(ModItems.regKeyItem("smooth_quartz_ruby_upgrader")).useBlockPrefixedTranslationKey());
    public static final SMRDBlock SMOOTH_QUARTZ_RUBY_DOWNGRADER = registerWithItem("smooth_quartz_ruby_downgrader", new SMRDBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_QUARTZ).registryKey(regKeyBlock("smooth_quartz_ruby_downgrader"))), new Item.Settings().registryKey(ModItems.regKeyItem("smooth_quartz_ruby_downgrader")).useBlockPrefixedTranslationKey());

    public static final StairsBlock RUBY_STAIRS = registerWithItem("ruby_stairs", new StairsBlock(ModBlocks.RUBY_BLOCK.getDefaultState(), AbstractBlock.Settings.copy(ModBlocks.RUBY_BLOCK).registryKey(regKeyBlock("ruby_stairs"))), new Item.Settings().registryKey(ModItems.regKeyItem("ruby_stairs")).useBlockPrefixedTranslationKey());
    public static final SlabBlock RUBY_SLAB = registerWithItem("ruby_slab", new SlabBlock(AbstractBlock.Settings.copy(ModBlocks.RUBY_BLOCK).registryKey(regKeyBlock("ruby_slab"))), new Item.Settings().registryKey(ModItems.regKeyItem("ruby_slab")).useBlockPrefixedTranslationKey());

    public static final ButtonBlock RUBY_BUTTON = registerWithItem("ruby_button", new ButtonBlock(BlockSetType.IRON, 40, AbstractBlock.Settings.copy(Blocks.STONE_BUTTON).registryKey(regKeyBlock("ruby_button"))), new Item.Settings().registryKey(ModItems.regKeyItem("ruby_button")).useBlockPrefixedTranslationKey());
    public static final PressurePlateBlock RUBY_PRESSURE_PLATE = registerWithItem("ruby_pressure_plate", new PressurePlateBlock(BlockSetType.IRON, AbstractBlock.Settings.copy(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE).registryKey(regKeyBlock("ruby_pressure_plate"))), new Item.Settings().registryKey(ModItems.regKeyItem("ruby_pressure_plate")).useBlockPrefixedTranslationKey());

    public static final FenceBlock RUBY_FENCE = registerWithItem("ruby_fence", new FenceBlock(AbstractBlock.Settings.copy(Blocks.NETHER_BRICK_FENCE).registryKey(regKeyBlock("ruby_fence"))), new Item.Settings().registryKey(ModItems.regKeyItem("ruby_fence")).useBlockPrefixedTranslationKey());
    public static final FenceGateBlock RUBY_FENCE_GATE = registerWithItem("ruby_fence_gate", new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.copy(Blocks.NETHER_BRICK_FENCE).registryKey(regKeyBlock("ruby_fence_gate"))), new Item.Settings().registryKey(ModItems.regKeyItem("ruby_fence_gate")).useBlockPrefixedTranslationKey());
    public static final WallBlock RUBY_WALL = registerWithItem("ruby_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.NETHER_BRICK_FENCE).registryKey(regKeyBlock("ruby_wall"))), new Item.Settings().registryKey(ModItems.regKeyItem("ruby_wall")).useBlockPrefixedTranslationKey());

    public static final DoorBlock RUBY_DOOR = registerWithItem("ruby_door", new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.create().requiresTool().nonOpaque().registryKey(regKeyBlock("ruby_door"))), new Item.Settings().registryKey(ModItems.regKeyItem("ruby_door")).useBlockPrefixedTranslationKey());
    public static final TrapdoorBlock RUBY_TRAPDOOR = registerWithItem("ruby_trapdoor", new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.create().requiresTool().nonOpaque().registryKey(regKeyBlock("ruby_trapdoor"))), new Item.Settings().registryKey(ModItems.regKeyItem("ruby_trapdoor")).useBlockPrefixedTranslationKey());

    public static final KokainaCropBlock KOKAINA_CROP = Registry.register(Registries.BLOCK, BsmpC.id("kokaina_crop"), new KokainaCropBlock(AbstractBlock.Settings.create().nonOpaque().noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).registryKey(regKeyBlock("kokaina_crop"))));
    public static final Block PACKED_IRON_BLOCK = registerWithItem("packed_iron_block", new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).registryKey(regKeyBlock("packed_iron_block"))), new Item.Settings().registryKey(ModItems.regKeyItem("packed_iron_block")).useBlockPrefixedTranslationKey());
    public static final RGBBlock RGB_BLOCK = registerWithItem("rgb_block", new RGBBlock(AbstractBlock.Settings.copy(Blocks.STONE).registryKey(regKeyBlock("rgb_block"))), new Item.Settings().registryKey(ModItems.regKeyItem("rgb_block")).useBlockPrefixedTranslationKey());
    public static final FlowerBlock ROSE = registerWithItem("rose", new FlowerBlock(ModEffects.HIGH, 20, AbstractBlock.Settings.copy(Blocks.POPPY).registryKey(regKeyBlock("rose")).nonOpaque()), new Item.Settings().registryKey(ModItems.regKeyItem("rose")).useBlockPrefixedTranslationKey());
    public static final FlowerPotBlock POTTED_ROSE = register("potted_rose", new FlowerPotBlock(ROSE, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY).registryKey(regKeyBlock("potted_rose")).nonOpaque()));
    public static final PedestalBlock PEDESTAL = registerWithItem("pedestal", new PedestalBlock(AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK).registryKey(regKeyBlock("pedestal"))), new Item.Settings().registryKey(ModItems.regKeyItem("pedestal")).useBlockPrefixedTranslationKey());

    public static final PillarBlock CS_LOG = registerWithItem("cs_log", new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG).registryKey(regKeyBlock("cs_log"))), new Item.Settings().registryKey(ModItems.regKeyItem("cs_log")).useBlockPrefixedTranslationKey());
    public static final PillarBlock CS_WOOD = registerWithItem("cs_wood", new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_WOOD).registryKey(regKeyBlock("cs_wood"))), new Item.Settings().registryKey(ModItems.regKeyItem("cs_wood")).useBlockPrefixedTranslationKey());
    public static final PillarBlock STRIPPED_CS_LOG = registerWithItem("stripped_cs_log", new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG).registryKey(regKeyBlock("stripped_cs_log"))), new Item.Settings().registryKey(ModItems.regKeyItem("stripped_cs_log")).useBlockPrefixedTranslationKey());
    public static final PillarBlock STRIPPED_CS_WOOD = registerWithItem("stripped_cs_wood", new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD).registryKey(regKeyBlock("stripped_cs_wood"))), new Item.Settings().registryKey(ModItems.regKeyItem("stripped_cs_wood")).useBlockPrefixedTranslationKey());

    public static final Block CS_PLANKS = registerWithItem("cs_planks", new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).registryKey(regKeyBlock("cs_planks"))), new Item.Settings().registryKey(ModItems.regKeyItem("cs_planks")).useBlockPrefixedTranslationKey());
    public static final LeavesBlock CS_LEAVES = registerWithItem("cs_leaves", new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).registryKey(regKeyBlock("cs_leaves")).nonOpaque()), new Item.Settings().registryKey(ModItems.regKeyItem("cs_leaves")).useBlockPrefixedTranslationKey());
    public static final ModSaplingBlock CS_SAPLING = registerWithItem("cs_sapling", new ModSaplingBlock(ModSaplingGenerators.CUPRESSUS_SEMPERVIRENS, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING).registryKey(regKeyBlock("cs_sapling")), Blocks.GRASS_BLOCK), new Item.Settings().registryKey(ModItems.regKeyItem("cs_sapling")).useBlockPrefixedTranslationKey());

    public static final StairsBlock CS_STAIRS = registerWithItem("cs_stairs", new StairsBlock(ModBlocks.CS_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.OAK_STAIRS).registryKey(regKeyBlock("cs_stairs"))), new Item.Settings().registryKey(ModItems.regKeyItem("cs_stairs")).useBlockPrefixedTranslationKey());
    public static final SlabBlock CS_SLAB = registerWithItem("cs_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB).registryKey(regKeyBlock("cs_slab"))), new Item.Settings().registryKey(ModItems.regKeyItem("cs_slab")).useBlockPrefixedTranslationKey());

    public static final ButtonBlock CS_BUTTON = registerWithItem("cs_button", new ButtonBlock(BlockSetType.IRON, 10, AbstractBlock.Settings.copy(Blocks.OAK_BUTTON).registryKey(regKeyBlock("cs_button"))), new Item.Settings().registryKey(ModItems.regKeyItem("cs_button")).useBlockPrefixedTranslationKey());
    public static final PressurePlateBlock CS_PRESSURE_PLATE = registerWithItem("cs_pressure_plate", new PressurePlateBlock(BlockSetType.IRON, AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE).registryKey(regKeyBlock("cs_pressure_plate"))), new Item.Settings().registryKey(ModItems.regKeyItem("cs_pressure_plate")).useBlockPrefixedTranslationKey());

    public static final FenceBlock CS_FENCE = registerWithItem("cs_fence", new FenceBlock(AbstractBlock.Settings.copy(Blocks.OAK_FENCE).registryKey(regKeyBlock("cs_fence"))), new Item.Settings().registryKey(ModItems.regKeyItem("cs_fence")).useBlockPrefixedTranslationKey());
    public static final FenceGateBlock CS_FENCE_GATE = registerWithItem("cs_fence_gate", new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_FENCE_GATE).registryKey(regKeyBlock("cs_fence_gate"))), new Item.Settings().registryKey(ModItems.regKeyItem("cs_fence_gate")).useBlockPrefixedTranslationKey());

    public static final DoorBlock CS_DOOR = registerWithItem("cs_door", new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_DOOR).registryKey(regKeyBlock("cs_door"))), new Item.Settings().registryKey(ModItems.regKeyItem("cs_door")).useBlockPrefixedTranslationKey());
    public static final TrapdoorBlock CS_TRAPDOOR = registerWithItem("cs_trapdoor", new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR).registryKey(regKeyBlock("cs_trapdoor"))), new Item.Settings().registryKey(ModItems.regKeyItem("cs_trapdoor")).useBlockPrefixedTranslationKey());

    public static final NPlushieBlock N_PLUSHIE = registerWithItem("n_plushie", new NPlushieBlock(AbstractBlock.Settings.create().nonOpaque().registryKey(regKeyBlock("n_plushie"))), new Item.Settings().registryKey(ModItems.regKeyItem("n_plushie")).useBlockPrefixedTranslationKey());
    public static final MeeperBlock MEEPER = registerWithItem("meeper", new MeeperBlock(AbstractBlock.Settings.create().nonOpaque().registryKey(regKeyBlock("meeper"))), new Item.Settings().registryKey(ModItems.regKeyItem("meeper")).useBlockPrefixedTranslationKey());

    public static final SafeBlock SAFE = registerWithItem("safe", new SafeBlock(AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK).registryKey(regKeyBlock("safe")).nonOpaque().resistance(6000F)), new Item.Settings().registryKey(ModItems.regKeyItem("safe")).useBlockPrefixedTranslationKey().component(ModDataComponentTypes.SAFE_OCCUPIED, false));

    public static final DeliberatorBlock DELIBERATOR = registerWithItem("deliberator", new DeliberatorBlock(AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK).registryKey(regKeyBlock("deliberator")).nonOpaque()), new Item.Settings().registryKey(ModItems.regKeyItem("deliberator")).useBlockPrefixedTranslationKey());

    public static final Block COMPANION_CUBE = registerWithItem("companion_cube", new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).registryKey(regKeyBlock("companion_cube")).nonOpaque()), new Item.Settings().registryKey(ModItems.regKeyItem("companion_cube")).useBlockPrefixedTranslationKey().maxCount(1));
    public static final Block GD_CUBE = registerWithItem("gd_cube", new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).registryKey(regKeyBlock("gd_cube")).nonOpaque()), new Item.Settings().registryKey(ModItems.regKeyItem("gd_cube")).useBlockPrefixedTranslationKey().maxCount(1));

    //METHODS

    public static <T extends Block> T register(String name, T block) {
        return Registry.register(Registries.BLOCK, BsmpC.id(name), block);
    }

    public static <T extends Block> T registerWithItem(String name, T block, Item.Settings settings) {
        T registered = register(name, block);
        ModItems.register(name, new BlockItem(registered, settings));
        return registered;
    }

    public static void registerModBlocks() {
        BsmpC.LOGGER.info("Registering Mod Blocks for " + BsmpC.MOD_ID);
    }
}
