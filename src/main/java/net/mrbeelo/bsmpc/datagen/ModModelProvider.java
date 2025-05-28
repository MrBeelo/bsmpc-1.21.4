package net.mrbeelo.bsmpc.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Blocks;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.mrbeelo.bsmpc.block.ModBlocks;
import net.mrbeelo.bsmpc.block.custom.KokainaCropBlock;
import net.mrbeelo.bsmpc.block.custom.RGBBlock;
import net.mrbeelo.bsmpc.fluid.ModFluids;
import net.mrbeelo.bsmpc.item.ModEquipmentAssets;
import net.mrbeelo.bsmpc.item.ModItems;
import net.mrbeelo.bsmpc.util.ModProperties;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        BlockStateModelGenerator.BlockTexturePool rubyTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.RUBY_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RUBY_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_RUBY_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.NETHER_RUBY_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.END_RUBY_ORE);
        blockStateModelGenerator.registerCrop(ModBlocks.KOKAINA_CROP, KokainaCropBlock.AGE, 0, 1, 2, 3);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.ROSE, ModBlocks.POTTED_ROSE, BlockStateModelGenerator.CrossType.NOT_TINTED);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PACKED_IRON_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SMOOTH_QUARTZ_RUBY_UPGRADER);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SMOOTH_QUARTZ_RUBY_DOWNGRADER);
        rubyTexturePool.stairs(ModBlocks.RUBY_STAIRS);
        rubyTexturePool.slab(ModBlocks.RUBY_SLAB);
        rubyTexturePool.button(ModBlocks.RUBY_BUTTON);
        rubyTexturePool.pressurePlate(ModBlocks.RUBY_PRESSURE_PLATE);
        rubyTexturePool.fence(ModBlocks.RUBY_FENCE);
        rubyTexturePool.fenceGate(ModBlocks.RUBY_FENCE_GATE);
        rubyTexturePool.wall(ModBlocks.RUBY_WALL);
        blockStateModelGenerator.registerDoor(ModBlocks.RUBY_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.RUBY_TRAPDOOR);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COMPANION_CUBE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GD_CUBE);

        blockStateModelGenerator.createLogTexturePool(ModBlocks.CS_LOG).log(ModBlocks.CS_LOG).wood(ModBlocks.CS_WOOD);
        blockStateModelGenerator.createLogTexturePool(ModBlocks.STRIPPED_CS_LOG).log(ModBlocks.STRIPPED_CS_LOG).wood(ModBlocks.STRIPPED_CS_WOOD);

        BlockStateModelGenerator.BlockTexturePool csPlankTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CS_PLANKS);
        blockStateModelGenerator.registerTintableCross(ModBlocks.CS_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        //blockStateModelGenerator.registerHangingSign(ModBlocks.STRIPPED_CS_LOG, ModBlocks.HANGING_CS_SIGN, ModBlocks.WALL_HANGING_CS_SIGN);

        //csPlankTexturePool.family(ModBlocks.CS_FAMILY);
        csPlankTexturePool.stairs(ModBlocks.CS_STAIRS);
        csPlankTexturePool.slab(ModBlocks.CS_SLAB);
        csPlankTexturePool.button(ModBlocks.CS_BUTTON);
        csPlankTexturePool.pressurePlate(ModBlocks.CS_PRESSURE_PLATE);
        csPlankTexturePool.fence(ModBlocks.CS_FENCE);
        csPlankTexturePool.fenceGate(ModBlocks.CS_FENCE_GATE);
        blockStateModelGenerator.registerDoor(ModBlocks.CS_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.CS_TRAPDOOR);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DELIBERILIUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_DELIBERILIUM_ORE);

        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.N_PLUSHIE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.SAFE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.MEEPER);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.DELIBERATOR);

        registerEndRelay(blockStateModelGenerator);

        blockStateModelGenerator.registerTintedBlockAndItem(ModBlocks.CS_LEAVES, TexturedModel.LEAVES, -12012264);

        WeightedVariant rgbOffIdentifier = BlockStateModelGenerator.createWeightedVariant(TexturedModel.CUBE_ALL.upload(ModBlocks.RGB_BLOCK, blockStateModelGenerator.modelCollector));
        WeightedVariant rgbOnIdentifier = BlockStateModelGenerator.createWeightedVariant(blockStateModelGenerator.createSubModel(ModBlocks.RGB_BLOCK, "_on", Models.CUBE_ALL, TextureMap::all));

        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(ModBlocks.RGB_BLOCK)
                .with(BlockStateModelGenerator.createBooleanModelMap(RGBBlock.CLICKED, rgbOnIdentifier, rgbOffIdentifier)));

        //blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.CS_BERRY_BUSH, BlockStateModelGenerator.CrossType.NOT_TINTED, CSBerryBushBlock.AGE, 0, 1, 2, 3);
        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockModelDefinitionCreator.of(ModBlocks.CS_BERRY_BUSH).with(
                        BlockStateVariantMap.models(Properties.AGE_3)
                                .generate(stage -> BlockStateModelGenerator.createWeightedVariant(blockStateModelGenerator.createSubModel(ModBlocks.CS_BERRY_BUSH, "_stage" + stage, Models.CROSS, TextureMap::cross))
                )
        ));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.RUBY, Models.GENERATED);
        itemModelGenerator.register(ModItems.RUBY_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.RUBY_CLUSTER, Models.GENERATED);
        itemModelGenerator.register(ModItems.RUBY_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.KOKAINA, Models.GENERATED);
        itemModelGenerator.register(ModItems.RUBY_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RUBY_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RUBY_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RUBY_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RUBY_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.RUBY_KNOCKER, Models.HANDHELD);
        itemModelGenerator.registerArmor(ModItems.RUBY_HELMET, ModEquipmentAssets.RUBY, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.RUBY_CHESTPLATE, ModEquipmentAssets.RUBY, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.RUBY_LEGGINGS, ModEquipmentAssets.RUBY, ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.RUBY_BOOTS, ModEquipmentAssets.RUBY, ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerBow(ModItems.RUBY_BOW);
        itemModelGenerator.register(ModItems.PISTOL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BULLET, Models.GENERATED);
        itemModelGenerator.register(ModItems.DEATH_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NUKE_BUTTON, Models.GENERATED);
        itemModelGenerator.register(ModItems.SNEK_SPAWN_EGG, Models.GENERATED);
        itemModelGenerator.register(ModItems.XO_MUSIC_DISC, Models.GENERATED);
        itemModelGenerator.register(ModFluids.FROOTOP_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RANDOM_ARTIFACT, Models.GENERATED);
        itemModelGenerator.register(ModItems.POKE_BALL, Models.GENERATED);
        itemModelGenerator.register(ModItems.WARDEN_SWORD, Models.HANDHELD);
        itemModelGenerator.registerArmor(ModItems.SNEK_HELMET, ModEquipmentAssets.SNEK, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.SNEK_CHESTPLATE, ModEquipmentAssets.SNEK, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.SNEK_LEGGINGS, ModEquipmentAssets.SNEK, ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.SNEK_BOOTS, ModEquipmentAssets.SNEK, ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, false);
        itemModelGenerator.register(ModItems.WARDEN_HORN, Models.GENERATED);
        itemModelGenerator.register(ModItems.SNEK_SCALE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLOB_SPAWN_EGG, Models.GENERATED);
        itemModelGenerator.register(ModItems.PYRO_SPAWN_EGG, Models.GENERATED);
        itemModelGenerator.registerArmor(ModItems.JETPACK, ModEquipmentAssets.JETPACK, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, false);
        itemModelGenerator.register(ModItems.MITHRILL, Models.GENERATED);
        itemModelGenerator.register(ModItems.MITHRILL_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MITHRILL_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MITHRILL_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MITHRILL_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MITHRILL_HOE, Models.HANDHELD);
        itemModelGenerator.registerArmor(ModItems.MITHRILL_HELMET, ModEquipmentAssets.MITHRILL, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.MITHRILL_CHESTPLATE, ModEquipmentAssets.MITHRILL, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.MITHRILL_LEGGINGS, ModEquipmentAssets.MITHRILL, ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ModItems.MITHRILL_BOOTS, ModEquipmentAssets.MITHRILL, ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, false);
        itemModelGenerator.register(ModItems.THALLEOUS_LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MITHRILLED_THALLEOUS_LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.FISH_MEME, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDER_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BEELO_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STICC, Models.GENERATED);
        itemModelGenerator.register(ModItems.METAL_ROD, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENERGY_CRYSTAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.DELIBERILIUM, Models.GENERATED);
        itemModelGenerator.register(ModItems.KEY, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLOBAL_KEY, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_BEEF_WELLINGTON, Models.GENERATED);
        itemModelGenerator.register(ModItems.OVERCOOKED_BEEF_WELLINGTON, Models.GENERATED);
        itemModelGenerator.register(ModItems.CS_BERRIES, Models.GENERATED);
        itemModelGenerator.register(ModItems.RUBBER_DUCKY, Models.GENERATED);
        itemModelGenerator.register(ModItems.PROTECTOR_SPAWN_EGG, Models.GENERATED);
        itemModelGenerator.register(ModItems.LAVA_CHICKEN, Models.GENERATED);
        //itemModelGenerator.register(ModItems.CS_BOAT, Models.GENERATED);
        //itemModelGenerator.register(ModItems.CS_CHEST_BOAT, Models.GENERATED);
    }

    private void registerEndRelay(BlockStateModelGenerator blockStateModelGenerator) {
        Identifier identifier = TextureMap.getSubId(ModBlocks.ENDER_RELAY, "_bottom");
        Identifier[] identifiers = new Identifier[2];

        for (int i = 0; i < 2; i++) {
            TextureMap textureMap = new TextureMap()
                    .put(TextureKey.BOTTOM, identifier)
                    .put(TextureKey.TOP, TextureMap.getSubId(ModBlocks.ENDER_RELAY, "_top_" + i))
                    .put(TextureKey.SIDE, TextureMap.getSubId(ModBlocks.ENDER_RELAY, "_side_" + i));
            identifiers[i] = Models.CUBE_BOTTOM_TOP.upload(ModBlocks.ENDER_RELAY, "_" + i, textureMap, blockStateModelGenerator.modelCollector);
        }

        blockStateModelGenerator.blockStateCollector
                .accept(
                        VariantsBlockModelDefinitionCreator.of(ModBlocks.ENDER_RELAY)
                                .with(BlockStateVariantMap.models(ModProperties.RELAY_CHARGES).generate(relay_charges -> BlockStateModelGenerator.createWeightedVariant(identifiers[relay_charges])))
                                //.coordinate(
                                        //BlockStateVariantMap.create(ModProperties.RELAY_CHARGES).register(relay_charges -> BlockStateVariant.create().put(VariantSettings.MODEL, identifiers[relay_charges]))
                                //)
                );
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.ENDER_RELAY, identifiers[0]);
    }
}
