package net.mrbeelo.bsmpc.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Blocks;
import net.minecraft.client.data.*;
import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.mrbeelo.bsmpc.BsmpC;
import net.mrbeelo.bsmpc.block.ModBlocks;
import net.mrbeelo.bsmpc.block.custom.CSBerryBushBlock;
import net.mrbeelo.bsmpc.block.custom.KokainaCropBlock;
import net.mrbeelo.bsmpc.block.custom.RGBBlock;
import net.mrbeelo.bsmpc.fluid.ModFluids;
import net.mrbeelo.bsmpc.item.ModEquipmentAssets;
import net.mrbeelo.bsmpc.item.ModItems;
import net.mrbeelo.bsmpc.util.ModProperties;

import java.util.Optional;

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


        blockStateModelGenerator.registerLog(ModBlocks.CS_LOG).log(ModBlocks.CS_LOG).wood(ModBlocks.CS_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_CS_LOG).log(ModBlocks.STRIPPED_CS_LOG).wood(ModBlocks.STRIPPED_CS_WOOD);

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

        Identifier rgbOffIdentifier = TexturedModel.CUBE_ALL.upload(ModBlocks.RGB_BLOCK, blockStateModelGenerator.modelCollector);
        Identifier rgbOnIdentifier = blockStateModelGenerator.createSubModel(ModBlocks.RGB_BLOCK, "_on", Models.CUBE_ALL, TextureMap::all);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.RGB_BLOCK)
                .coordinate(BlockStateModelGenerator.createBooleanModelMap(RGBBlock.CLICKED, rgbOnIdentifier, rgbOffIdentifier)));

        //blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.CS_BERRY_BUSH, BlockStateModelGenerator.CrossType.NOT_TINTED, CSBerryBushBlock.AGE, 0, 1, 2, 3);
        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(ModBlocks.CS_BERRY_BUSH).coordinate(
                        BlockStateVariantMap.create(Properties.AGE_3)
                                .register(stage -> BlockStateVariant.create()
                                        .put(VariantSettings.MODEL, blockStateModelGenerator.createSubModel(ModBlocks.CS_BERRY_BUSH, "_stage" + stage, Models.CROSS, TextureMap::cross)))
                )
        );
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
        itemModelGenerator.registerArmor(ModItems.RUBY_HELMET, ModEquipmentAssets.RUBY, "helmet", false);
        itemModelGenerator.registerArmor(ModItems.RUBY_CHESTPLATE, ModEquipmentAssets.RUBY, "chestplate", false);
        itemModelGenerator.registerArmor(ModItems.RUBY_LEGGINGS, ModEquipmentAssets.RUBY, "leggings", false);
        itemModelGenerator.registerArmor(ModItems.RUBY_BOOTS, ModEquipmentAssets.RUBY, "boots", false);
        itemModelGenerator.registerBow(ModItems.RUBY_BOW);
        itemModelGenerator.register(ModItems.PISTOL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BULLET, Models.GENERATED);
        itemModelGenerator.register(ModItems.DEATH_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NUKE_BUTTON, Models.GENERATED);
        itemModelGenerator.registerSpawnEgg(ModItems.SNEK_SPAWN_EGG, 0x78fa40, 0x5ed32b);
        itemModelGenerator.register(ModItems.XO_MUSIC_DISC, Models.GENERATED);
        itemModelGenerator.register(ModFluids.FROOTOP_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RANDOM_ARTIFACT, Models.GENERATED);
        itemModelGenerator.register(ModItems.POKE_BALL, Models.GENERATED);
        itemModelGenerator.register(ModItems.WARDEN_SWORD, Models.HANDHELD);
        itemModelGenerator.registerArmor(ModItems.SNEK_HELMET, ModEquipmentAssets.SNEK, "helmet", false);
        itemModelGenerator.registerArmor(ModItems.SNEK_CHESTPLATE, ModEquipmentAssets.SNEK, "chestplate", false);
        itemModelGenerator.registerArmor(ModItems.SNEK_LEGGINGS, ModEquipmentAssets.SNEK, "leggings", false);
        itemModelGenerator.registerArmor(ModItems.SNEK_BOOTS, ModEquipmentAssets.SNEK, "boots", false);
        itemModelGenerator.register(ModItems.WARDEN_HORN, Models.GENERATED);
        itemModelGenerator.register(ModItems.SNEK_SCALE, Models.GENERATED);
        itemModelGenerator.registerSpawnEgg(ModItems.BLOB_SPAWN_EGG, 0x7bd4ff, 0xffa7a4);
        itemModelGenerator.registerSpawnEgg(ModItems.PYRO_SPAWN_EGG, 0x3e3e42, 0xd21129);
        itemModelGenerator.registerArmor(ModItems.JETPACK, ModEquipmentAssets.JETPACK, "chestplate", false);
        itemModelGenerator.register(ModItems.MITHRILL, Models.GENERATED);
        itemModelGenerator.register(ModItems.MITHRILL_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MITHRILL_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MITHRILL_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MITHRILL_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.MITHRILL_HOE, Models.HANDHELD);
        itemModelGenerator.registerArmor(ModItems.MITHRILL_HELMET, ModEquipmentAssets.MITHRILL, "helmet", false);
        itemModelGenerator.registerArmor(ModItems.MITHRILL_CHESTPLATE, ModEquipmentAssets.MITHRILL, "chestplate", false);
        itemModelGenerator.registerArmor(ModItems.MITHRILL_LEGGINGS, ModEquipmentAssets.MITHRILL, "leggings", false);
        itemModelGenerator.registerArmor(ModItems.MITHRILL_BOOTS, ModEquipmentAssets.MITHRILL, "boots", false);
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
        //itemModelGenerator.register(ModItems.CS_BOAT, Models.GENERATED);
        //itemModelGenerator.register(ModItems.CS_CHEST_BOAT, Models.GENERATED);
    }

    private void registerEndRelay(BlockStateModelGenerator blockStateModelGenerator) {
        Identifier identifier = TextureMap.getSubId(ModBlocks.END_RELAY, "_bottom");
        Identifier[] identifiers = new Identifier[2];

        for (int i = 0; i < 2; i++) {
            TextureMap textureMap = new TextureMap()
                    .put(TextureKey.BOTTOM, identifier)
                    .put(TextureKey.TOP, TextureMap.getSubId(ModBlocks.END_RELAY, "_top_" + i))
                    .put(TextureKey.SIDE, TextureMap.getSubId(ModBlocks.END_RELAY, "_side_" + i));
            identifiers[i] = Models.CUBE_BOTTOM_TOP.upload(ModBlocks.END_RELAY, "_" + i, textureMap, blockStateModelGenerator.modelCollector);
        }

        blockStateModelGenerator.blockStateCollector
                .accept(
                        VariantsBlockStateSupplier.create(ModBlocks.END_RELAY)
                                .coordinate(
                                        BlockStateVariantMap.create(ModProperties.RELAY_CHARGES).register(relay_charges -> BlockStateVariant.create().put(VariantSettings.MODEL, identifiers[relay_charges]))
                                )
                );
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.END_RELAY, identifiers[0]);
    }
}
