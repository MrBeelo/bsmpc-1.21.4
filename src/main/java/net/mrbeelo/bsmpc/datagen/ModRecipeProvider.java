package net.mrbeelo.bsmpc.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.mrbeelo.bsmpc.BsmpC;
import net.mrbeelo.bsmpc.block.ModBlocks;
import net.mrbeelo.bsmpc.fluid.ModFluids;
import net.mrbeelo.bsmpc.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;



public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                RegistryWrapper.Impl<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);

                createShaped(RecipeCategory.MISC, ModBlocks.RUBY_BLOCK, 1)
                        .input('E', ModItems.RUBY)
                        .pattern("EEE")
                        .pattern("EEE")
                        .pattern("EEE")
                        .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "ruby_block")));

                createShaped(RecipeCategory.MISC, ModBlocks.RUBY_STAIRS, 4)
                        .input('E', ModBlocks.RUBY_BLOCK)
                        .pattern("E  ")
                        .pattern("EE ")
                        .pattern("EEE")
                        .criterion(hasItem(ModBlocks.RUBY_BLOCK), conditionsFromItem(ModBlocks.RUBY_BLOCK))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "ruby_stairs")));

                createShaped(RecipeCategory.MISC, ModBlocks.RUBY_SLAB, 6)
                        .input('E', ModBlocks.RUBY_BLOCK)
                        .pattern("EEE")
                        .criterion(hasItem(ModBlocks.RUBY_BLOCK), conditionsFromItem(ModBlocks.RUBY_BLOCK))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "ruby_slab")));

                createShapeless(RecipeCategory.MISC, ModBlocks.RUBY_BUTTON, 1)
                        .input(ModBlocks.RUBY_BLOCK)
                        .criterion(hasItem(ModBlocks.RUBY_BLOCK), conditionsFromItem(ModBlocks.RUBY_BLOCK))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "ruby_button")));

                createShaped(RecipeCategory.MISC, ModBlocks.RUBY_PRESSURE_PLATE, 6)
                        .input('E', ModBlocks.RUBY_BLOCK)
                        .pattern("EE")
                        .criterion(hasItem(ModBlocks.RUBY_BLOCK), conditionsFromItem(ModBlocks.RUBY_BLOCK))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "ruby_pressure_plate")));

                createShaped(RecipeCategory.MISC, ModBlocks.RUBY_FENCE, 3)
                        .input('E', ModBlocks.RUBY_BLOCK)
                        .input('F', ModItems.RUBY)
                        .pattern("EFE")
                        .pattern("EFE")
                        .criterion(hasItem(ModBlocks.RUBY_BLOCK), conditionsFromItem(ModBlocks.RUBY_BLOCK))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "ruby_fence")));

                createShaped(RecipeCategory.MISC, ModBlocks.RUBY_FENCE_GATE, 1)
                        .input('E', ModBlocks.RUBY_BLOCK)
                        .input('F', ModItems.RUBY)
                        .pattern("FEF")
                        .pattern("FEF")
                        .criterion(hasItem(ModBlocks.RUBY_BLOCK), conditionsFromItem(ModBlocks.RUBY_BLOCK))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "ruby_fence_gate")));

                createShaped(RecipeCategory.MISC, ModBlocks.RUBY_WALL, 6)
                        .input('E', ModBlocks.RUBY_BLOCK)
                        .pattern("EEE")
                        .pattern("EEE")
                        .criterion(hasItem(ModBlocks.RUBY_BLOCK), conditionsFromItem(ModBlocks.RUBY_BLOCK))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "ruby_wall")));

                createShaped(RecipeCategory.MISC, ModBlocks.RUBY_DOOR, 3)
                        .input('E', ModBlocks.RUBY_BLOCK)
                        .pattern("EE")
                        .pattern("EE")
                        .pattern("EE")
                        .criterion(hasItem(ModBlocks.RUBY_BLOCK), conditionsFromItem(ModBlocks.RUBY_BLOCK))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "ruby_door")));

                createShaped(RecipeCategory.MISC, ModBlocks.RUBY_TRAPDOOR, 3)
                        .input('E', ModBlocks.RUBY_BLOCK)
                        .pattern("EEE")
                        .pattern("E E")
                        .pattern("EEE")
                        .criterion(hasItem(ModBlocks.RUBY_BLOCK), conditionsFromItem(ModBlocks.RUBY_BLOCK))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "ruby_trapdoor")));

                createShaped(RecipeCategory.MISC, ModItems.RUBY_INGOT, 1)
                        .input('E', ModItems.RUBY)
                        .pattern(" E ")
                        .pattern("EEE")
                        .pattern(" E ")
                        .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "ruby_ingot_from_rubies")));

                createShapeless(RecipeCategory.MISC, ModItems.RUBY, 5)
                        .input(ModItems.RUBY_INGOT)
                        .criterion(hasItem(ModItems.RUBY_INGOT), conditionsFromItem(ModItems.RUBY_INGOT))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "rubies_from_ruby_ingot")));

                createShaped(RecipeCategory.MISC, ModItems.RUBY_CLUSTER, 1)
                        .input('E', ModItems.RUBY_INGOT)
                        .pattern(" E ")
                        .pattern("EEE")
                        .pattern(" E ")
                        .criterion(hasItem(ModItems.RUBY_INGOT), conditionsFromItem(ModItems.RUBY_INGOT))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "ruby_cluster_from_ruby_ingots")));

                createShapeless(RecipeCategory.MISC, ModItems.RUBY_INGOT, 5)
                        .input(ModItems.RUBY_CLUSTER)
                        .criterion(hasItem(ModItems.RUBY_CLUSTER), conditionsFromItem(ModItems.RUBY_CLUSTER))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "ruby_ingots_from_ruby_cluster")));

                createShaped(RecipeCategory.MISC, ModItems.RUBY, 1)
                        .input('E', ModItems.RUBY_SHARD)
                        .pattern(" E ")
                        .pattern("EEE")
                        .pattern(" E ")
                        .criterion(hasItem(ModItems.RUBY_SHARD), conditionsFromItem(ModItems.RUBY_SHARD))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "rubies_from_ruby_shards")));

                createShapeless(RecipeCategory.MISC, ModItems.RUBY_SHARD, 5)
                        .input(ModItems.RUBY)
                        .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "ruby_shards_from_rubies")));

                createShaped(RecipeCategory.COMBAT, ModItems.RUBY_SWORD, 1)
                        .input('E', ModItems.RUBY_INGOT)
                        .input('F', Items.STICK)
                        .pattern("E")
                        .pattern("E")
                        .pattern("F")
                        .criterion(hasItem(ModItems.RUBY_INGOT), conditionsFromItem(ModItems.RUBY_INGOT))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "ruby_sword")));

                createShaped(RecipeCategory.MISC, ModItems.RUBY_PICKAXE, 1)
                        .input('E', ModItems.RUBY_INGOT)
                        .input('F', Items.STICK)
                        .pattern("EEE")
                        .pattern(" F ")
                        .pattern(" F ")
                        .criterion(hasItem(ModItems.RUBY_INGOT), conditionsFromItem(ModItems.RUBY_INGOT))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "ruby_pickaxe")));

                createShaped(RecipeCategory.MISC, ModItems.RUBY_AXE, 1)
                        .input('E', ModItems.RUBY_INGOT)
                        .input('F', Items.STICK)
                        .pattern("EE")
                        .pattern("FE")
                        .pattern("F ")
                        .criterion(hasItem(ModItems.RUBY_INGOT), conditionsFromItem(ModItems.RUBY_INGOT))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "ruby_axe")));

                createShaped(RecipeCategory.MISC, ModItems.RUBY_SHOVEL, 1)
                        .input('E', ModItems.RUBY_INGOT)
                        .input('F', Items.STICK)
                        .pattern("E")
                        .pattern("F")
                        .pattern("F")
                        .criterion(hasItem(ModItems.RUBY_INGOT), conditionsFromItem(ModItems.RUBY_INGOT))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "ruby_shovel")));

                createShaped(RecipeCategory.MISC, ModItems.RUBY_HOE, 1)
                        .input('E', ModItems.RUBY_INGOT)
                        .input('F', Items.STICK)
                        .pattern("EE")
                        .pattern("F ")
                        .pattern("F ")
                        .criterion(hasItem(ModItems.RUBY_INGOT), conditionsFromItem(ModItems.RUBY_INGOT))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "ruby_hoe")));

                createShaped(RecipeCategory.MISC, ModItems.RUBY_HELMET, 1)
                        .input('E', ModItems.RUBY_INGOT)
                        .pattern("EEE")
                        .pattern("E E")
                        .criterion(hasItem(ModItems.RUBY_INGOT), conditionsFromItem(ModItems.RUBY_INGOT))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "ruby_helmet")));

                createShaped(RecipeCategory.MISC, ModItems.RUBY_CHESTPLATE, 1)
                        .input('E', ModItems.RUBY_INGOT)
                        .pattern("E E")
                        .pattern("EEE")
                        .pattern("EEE")
                        .criterion(hasItem(ModItems.RUBY_INGOT), conditionsFromItem(ModItems.RUBY_INGOT))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "ruby_chestplate")));

                createShaped(RecipeCategory.MISC, ModItems.RUBY_LEGGINGS, 1)
                        .input('E', ModItems.RUBY_INGOT)
                        .pattern("EEE")
                        .pattern("E E")
                        .pattern("E E")
                        .criterion(hasItem(ModItems.RUBY_INGOT), conditionsFromItem(ModItems.RUBY_INGOT))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "ruby_leggings")));

                createShaped(RecipeCategory.MISC, ModItems.RUBY_BOOTS, 1)
                        .input('E', ModItems.RUBY_INGOT)
                        .pattern("E E")
                        .pattern("E E")
                        .criterion(hasItem(ModItems.RUBY_INGOT), conditionsFromItem(ModItems.RUBY_INGOT))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "ruby_boots")));

                createShaped(RecipeCategory.MISC, ModItems.RUBY_BOW, 1)
                        .input('E', ModItems.RUBY_INGOT)
                        .input('F', Items.STRING)
                        .pattern(" EF")
                        .pattern("E F")
                        .pattern(" EF")
                        .criterion(hasItem(ModItems.RUBY_INGOT), conditionsFromItem(ModItems.RUBY_INGOT))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "ruby_bow")));

                createShapeless(RecipeCategory.MISC, ModItems.BULLET, 2)
                        .input(Items.IRON_NUGGET)
                        .input(Items.GUNPOWDER)
                        .criterion(hasItem(Items.GUNPOWDER), conditionsFromItem(Items.GUNPOWDER))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "bullet")));

                createShapeless(RecipeCategory.MISC, ModBlocks.CS_PLANKS, 4)
                        .input(ModBlocks.CS_LOG)
                        .criterion(hasItem(Items.GUNPOWDER), conditionsFromItem(Items.GUNPOWDER))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "cs_planks_from_cs_log")));

                createShapeless(RecipeCategory.MISC, ModBlocks.CS_PLANKS, 2)
                        .input(ModBlocks.STRIPPED_CS_LOG)
                        .criterion(hasItem(Items.GUNPOWDER), conditionsFromItem(Items.GUNPOWDER))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "cs_planks_from_stripped_cs_log")));

                createShaped(RecipeCategory.MISC, ModBlocks.CS_STAIRS, 4)
                        .input('E', ModBlocks.CS_PLANKS)
                        .pattern("E  ")
                        .pattern("EE ")
                        .pattern("EEE")
                        .criterion(hasItem(ModBlocks.CS_PLANKS), conditionsFromItem(ModBlocks.CS_PLANKS))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "cs_stairs")));

                createShaped(RecipeCategory.MISC, ModBlocks.CS_SLAB, 6)
                        .input('E', ModBlocks.CS_PLANKS)
                        .pattern("EEE")
                        .criterion(hasItem(ModBlocks.CS_PLANKS), conditionsFromItem(ModBlocks.CS_PLANKS))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "cs_slab")));

                createShapeless(RecipeCategory.MISC, ModBlocks.CS_BUTTON, 1)
                        .input(ModBlocks.CS_PLANKS)
                        .criterion(hasItem(ModBlocks.CS_PLANKS), conditionsFromItem(ModBlocks.CS_PLANKS))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "cs_button")));

                createShaped(RecipeCategory.MISC, ModBlocks.CS_PRESSURE_PLATE, 6)
                        .input('E', ModBlocks.CS_PLANKS)
                        .pattern("EE")
                        .criterion(hasItem(ModBlocks.CS_PLANKS), conditionsFromItem(ModBlocks.CS_PLANKS))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "cs_pressure_plate")));

                createShaped(RecipeCategory.MISC, ModBlocks.CS_FENCE, 3)
                        .input('E', ModBlocks.CS_PLANKS)
                        .input('F', Items.STICK)
                        .pattern("EFE")
                        .pattern("EFE")
                        .criterion(hasItem(ModBlocks.CS_PLANKS), conditionsFromItem(ModBlocks.CS_PLANKS))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "cs_fence")));

                createShaped(RecipeCategory.MISC, ModBlocks.CS_FENCE_GATE, 1)
                        .input('E', ModBlocks.CS_PLANKS)
                        .input('F', Items.STICK)
                        .pattern("FEF")
                        .pattern("FEF")
                        .criterion(hasItem(ModBlocks.CS_PLANKS), conditionsFromItem(ModBlocks.CS_PLANKS))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "cs_fence_gate")));

                createShaped(RecipeCategory.MISC, ModBlocks.CS_DOOR, 3)
                        .input('E', ModBlocks.CS_PLANKS)
                        .pattern("EE")
                        .pattern("EE")
                        .pattern("EE")
                        .criterion(hasItem(ModBlocks.CS_PLANKS), conditionsFromItem(ModBlocks.CS_PLANKS))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "cs_door")));

                createShaped(RecipeCategory.MISC, ModBlocks.CS_TRAPDOOR, 3)
                        .input('E', ModBlocks.CS_PLANKS)
                        .pattern("EEE")
                        .pattern("EEE")
                        .criterion(hasItem(ModBlocks.CS_PLANKS), conditionsFromItem(ModBlocks.CS_PLANKS))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "cs_trapdoor")));

                /*createShaped(RecipeCategory.MISC, ModItems.CS_SIGN, 3)
                        .input('E', ModBlocks.CS_PLANKS)
                        .input('F', Items.STICK)
                        .pattern("EEE")
                        .pattern("EEE")
                        .pattern(" F ")
                        .criterion(hasItem(ModBlocks.CS_PLANKS), conditionsFromItem(ModBlocks.CS_PLANKS))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "cs_sign")));

                createShaped(RecipeCategory.MISC, ModItems.HANGING_CS_SIGN, 6)
                        .input('E', ModBlocks.CS_PLANKS)
                        .input('F', Items.CHAIN)
                        .pattern("F F")
                        .pattern("EEE")
                        .pattern("EEE")
                        .criterion(hasItem(ModBlocks.CS_PLANKS), conditionsFromItem(ModBlocks.CS_PLANKS))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "cs_hanging_sign")));

                createShaped(RecipeCategory.MISC, ModItems.CS_BOAT, 1)
                        .input('E', ModBlocks.CS_PLANKS)
                        .pattern("E E")
                        .pattern("EEE")
                        .criterion(hasItem(ModBlocks.CS_PLANKS), conditionsFromItem(ModBlocks.CS_PLANKS))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "cs_boat")));

                createShaped(RecipeCategory.MISC, ModItems.CS_CHEST_BOAT, 1)
                        .input('E', ModBlocks.CS_PLANKS)
                        .input('F', Blocks.CHEST)
                        .pattern("EFE")
                        .pattern("EEE")
                        .criterion(hasItem(ModBlocks.CS_PLANKS), conditionsFromItem(ModBlocks.CS_PLANKS))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "cs_chest_boat")));*/

                createShaped(RecipeCategory.COMBAT, ModItems.WARDEN_SWORD, 1)
                        .input('E', ModItems.WARDEN_HORN)
                        .input('F', Items.STICK)
                        .pattern("E")
                        .pattern("E")
                        .pattern("F")
                        .criterion(hasItem(ModItems.WARDEN_HORN), conditionsFromItem(ModItems.WARDEN_HORN))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "warden_sword")));

                createShaped(RecipeCategory.MISC, ModItems.SNEK_HELMET, 1)
                        .input('E', ModItems.SNEK_SCALE)
                        .pattern("EEE")
                        .pattern("E E")
                        .criterion(hasItem(ModItems.SNEK_SCALE), conditionsFromItem(ModItems.SNEK_SCALE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "snek_helmet")));

                createShaped(RecipeCategory.MISC, ModItems.SNEK_CHESTPLATE, 1)
                        .input('E', ModItems.SNEK_SCALE)
                        .pattern("E E")
                        .pattern("EEE")
                        .pattern("EEE")
                        .criterion(hasItem(ModItems.SNEK_SCALE), conditionsFromItem(ModItems.SNEK_SCALE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "snek_chestplate")));

                createShaped(RecipeCategory.MISC, ModItems.SNEK_LEGGINGS, 1)
                        .input('E', ModItems.SNEK_SCALE)
                        .pattern("EEE")
                        .pattern("E E")
                        .pattern("E E")
                        .criterion(hasItem(ModItems.SNEK_SCALE), conditionsFromItem(ModItems.SNEK_SCALE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "snek_leggings")));

                createShaped(RecipeCategory.MISC, ModItems.SNEK_BOOTS, 1)
                        .input('E', ModItems.SNEK_SCALE)
                        .pattern("E E")
                        .pattern("E E")
                        .criterion(hasItem(ModItems.SNEK_SCALE), conditionsFromItem(ModItems.SNEK_SCALE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "snek_boots")));

                createShaped(RecipeCategory.MISC, ModBlocks.PACKED_IRON_BLOCK, 1)
                        .input('E', Blocks.IRON_BLOCK)
                        .pattern("EE")
                        .pattern("EE")
                        .criterion(hasItem(Blocks.IRON_BLOCK), conditionsFromItem(Blocks.IRON_BLOCK))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "packed_iron_block")));

                createShaped(RecipeCategory.MISC, Blocks.IRON_BLOCK, 4)
                        .input('E', ModBlocks.PACKED_IRON_BLOCK)
                        .pattern("E")
                        .criterion(hasItem(ModBlocks.PACKED_IRON_BLOCK), conditionsFromItem(ModBlocks.PACKED_IRON_BLOCK))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "iron_block")));

                createShaped(RecipeCategory.MISC, ModItems.MITHRILL, 1)
                        .input('E', ModItems.RUBY_INGOT)
                        .input('F', Items.DIAMOND)
                        .pattern("EEE")
                        .pattern("EFE")
                        .pattern("EEE")
                        .criterion(hasItem(ModItems.RUBY_INGOT), conditionsFromItem(ModItems.RUBY_INGOT))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "mithrill")));

                createShaped(RecipeCategory.COMBAT, ModItems.MITHRILL_SWORD, 1)
                        .input('E', ModItems.MITHRILL)
                        .input('F', Items.STICK)
                        .pattern("E")
                        .pattern("E")
                        .pattern("F")
                        .criterion(hasItem(ModItems.MITHRILL), conditionsFromItem(ModItems.MITHRILL))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "mithrill_sword")));

                createShaped(RecipeCategory.TOOLS, ModItems.MITHRILL_PICKAXE, 1)
                        .input('E', ModItems.MITHRILL)
                        .input('F', Items.STICK)
                        .pattern("EEE")
                        .pattern(" F ")
                        .pattern(" F ")
                        .criterion(hasItem(ModItems.MITHRILL), conditionsFromItem(ModItems.MITHRILL))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "mithrill_pickaxe")));

                createShaped(RecipeCategory.TOOLS, ModItems.MITHRILL_AXE, 1)
                        .input('E', ModItems.MITHRILL)
                        .input('F', Items.STICK)
                        .pattern("EE")
                        .pattern("FE")
                        .pattern("F ")
                        .criterion(hasItem(ModItems.MITHRILL), conditionsFromItem(ModItems.MITHRILL))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "mithrill_axe")));

                createShaped(RecipeCategory.TOOLS, ModItems.MITHRILL_SHOVEL, 1)
                        .input('E', ModItems.MITHRILL)
                        .input('F', Items.STICK)
                        .pattern("E")
                        .pattern("F")
                        .pattern("F")
                        .criterion(hasItem(ModItems.MITHRILL), conditionsFromItem(ModItems.MITHRILL))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "mithrill_shovel")));

                createShaped(RecipeCategory.TOOLS, ModItems.MITHRILL_HOE, 1)
                        .input('E', ModItems.MITHRILL)
                        .input('F', Items.STICK)
                        .pattern("EE")
                        .pattern("F ")
                        .pattern("F ")
                        .criterion(hasItem(ModItems.MITHRILL), conditionsFromItem(ModItems.MITHRILL))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "mithrill_hoe")));

                createShaped(RecipeCategory.COMBAT, ModItems.MITHRILL_HELMET, 1)
                        .input('E', ModItems.MITHRILL)
                        .pattern("EEE")
                        .pattern("E E")
                        .criterion(hasItem(ModItems.MITHRILL), conditionsFromItem(ModItems.MITHRILL))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "mithrill_helmet")));

                createShaped(RecipeCategory.COMBAT, ModItems.MITHRILL_CHESTPLATE, 1)
                        .input('E', ModItems.MITHRILL)
                        .pattern("E E")
                        .pattern("EEE")
                        .pattern("EEE")
                        .criterion(hasItem(ModItems.MITHRILL), conditionsFromItem(ModItems.MITHRILL))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "mithrill_chestplate")));

                createShaped(RecipeCategory.COMBAT, ModItems.MITHRILL_LEGGINGS, 1)
                        .input('E', ModItems.MITHRILL)
                        .pattern("EEE")
                        .pattern("E E")
                        .pattern("E E")
                        .criterion(hasItem(ModItems.MITHRILL), conditionsFromItem(ModItems.MITHRILL))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "mithrill_leggings")));

                createShaped(RecipeCategory.COMBAT, ModItems.MITHRILL_BOOTS, 1)
                        .input('E', ModItems.MITHRILL)
                        .pattern("E E")
                        .pattern("E E")
                        .criterion(hasItem(ModItems.MITHRILL), conditionsFromItem(ModItems.MITHRILL))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "mithrill_boots")));

                createShaped(RecipeCategory.COMBAT, ModItems.THALLEOUS_LONGSWORD, 1)
                        .input('E', Items.DIAMOND)
                        .input('F', Items.STICK)
                        .input('G', ModItems.MITHRILL)
                        .pattern(" E ")
                        .pattern("EGE")
                        .pattern(" F ")
                        .criterion(hasItem(ModItems.MITHRILL), conditionsFromItem(ModItems.MITHRILL))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "thalleous_longsword")));

                createShaped(RecipeCategory.MISC, ModBlocks.SMOOTH_QUARTZ_RUBY_DOWNGRADER, 1)
                        .input('E', ModItems.RUBY)
                        .input('F', Items.SMOOTH_QUARTZ)
                        .pattern("FEF")
                        .pattern("EFE")
                        .pattern("FEF")
                        .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "smooth_quartz_ruby_downgrader")));

                createShaped(RecipeCategory.MISC, ModBlocks.SMOOTH_QUARTZ_RUBY_UPGRADER, 1)
                        .input('E', ModItems.RUBY)
                        .input('F', Items.SMOOTH_QUARTZ)
                        .pattern("EFE")
                        .pattern("FEF")
                        .pattern("EFE")
                        .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "smooth_quartz_ruby_upgrader")));

                createShaped(RecipeCategory.MISC, ModItems.ENDER_SWORD, 1)
                        .input('E', ModItems.RUBY_INGOT)
                        .input('F', ModItems.RUBY)
                        .input('G', ModItems.MITHRILL)
                        .input('H', Items.STICK)
                        .pattern(" E ")
                        .pattern("FGF")
                        .pattern(" H ")
                        .criterion(hasItem(ModItems.MITHRILL), conditionsFromItem(ModItems.MITHRILL))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "ender_sword")));

                createShaped(RecipeCategory.COMBAT, ModItems.MITHRILLED_THALLEOUS_LONGSWORD, 1)
                        .input('E', ModItems.MITHRILL)
                        .input('F', ModItems.RUBY_INGOT)
                        .input('G', ModItems.THALLEOUS_LONGSWORD)
                        .pattern(" E ")
                        .pattern("FGF")
                        .pattern(" E ")
                        .criterion(hasItem(ModItems.THALLEOUS_LONGSWORD), conditionsFromItem(ModItems.THALLEOUS_LONGSWORD))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "mithrilled_thalleous_longsword")));

                createShapeless(RecipeCategory.MISC, Items.RED_DYE, 1)
                        .input(ModBlocks.ROSE)
                        .criterion(hasItem(ModBlocks.ROSE), conditionsFromItem(ModBlocks.ROSE))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "red_dye")));

                createShaped(RecipeCategory.MISC, ModItems.METAL_ROD, 4)
                        .input('E', ModBlocks.PACKED_IRON_BLOCK)
                        .pattern("E")
                        .pattern("E")
                        .criterion(hasItem(ModBlocks.PACKED_IRON_BLOCK), conditionsFromItem(ModBlocks.PACKED_IRON_BLOCK))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "metal_rod")));

                createShaped(RecipeCategory.MISC, ModItems.ENERGY_CRYSTAL, 1)
                        .input('E', Items.GOLD_INGOT)
                        .input('F', Items.REDSTONE)
                        .pattern("EEE")
                        .pattern("EFE")
                        .pattern("EEE")
                        .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "energy_crystal")));

                createShaped(RecipeCategory.COMBAT, ModItems.SCYTHE, 1)
                        .input('E', ModItems.METAL_ROD)
                        .input('F', ModBlocks.PACKED_IRON_BLOCK)
                        .input('G', ModItems.ENERGY_CRYSTAL)
                        .pattern("FFG")
                        .pattern("  E")
                        .pattern("  E")
                        .criterion(hasItem(ModItems.ENERGY_CRYSTAL), conditionsFromItem(ModItems.ENERGY_CRYSTAL))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "scythe")));

                createShaped(RecipeCategory.COMBAT, ModItems.BARDICHE, 1)
                        .input('E', ModItems.METAL_ROD)
                        .input('F', ModBlocks.PACKED_IRON_BLOCK)
                        .input('G', ModItems.ENERGY_CRYSTAL)
                        .pattern("FG")
                        .pattern("FE")
                        .pattern(" E")
                        .criterion(hasItem(ModItems.ENERGY_CRYSTAL), conditionsFromItem(ModItems.ENERGY_CRYSTAL))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "bardiche")));

                createShaped(RecipeCategory.COMBAT, ModItems.GRAF_EISER, 1)
                        .input('E', ModItems.METAL_ROD)
                        .input('F', ModBlocks.PACKED_IRON_BLOCK)
                        .input('G', Blocks.GOLD_BLOCK)
                        .pattern("FGF")
                        .pattern(" E ")
                        .pattern(" E ")
                        .criterion(hasItem(Blocks.GOLD_BLOCK), conditionsFromItem(Blocks.GOLD_BLOCK))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "graf_eiser")));

                createShaped(RecipeCategory.MISC, ModBlocks.SAFE, 1)
                        .input('E', ModBlocks.PACKED_IRON_BLOCK)
                        .input('F', Blocks.CHEST)
                        .pattern("E")
                        .pattern("F")
                        .pattern("E")
                        .criterion(hasItem(ModBlocks.PACKED_IRON_BLOCK), conditionsFromItem(ModBlocks.PACKED_IRON_BLOCK))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "safe")));

                createShaped(RecipeCategory.MISC, ModItems.KEY, 1)
                        .input('E', Items.GOLD_INGOT)
                        .pattern("E")
                        .pattern("E")
                        .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "key")));

                createShapeless(RecipeCategory.MISC, ModItems.DELIBERILIUM, 1)
                        .input(ModItems.RUBY)
                        .input(Items.COAL)
                        .input(Items.COPPER_INGOT)
                        .input(Items.LAPIS_LAZULI)
                        .input(Items.IRON_INGOT)
                        .input(Items.REDSTONE)
                        .input(Items.DIAMOND)
                        .input(Items.GOLD_INGOT)
                        .input(Items.EMERALD)
                        .criterion(hasItem(ModItems.RUBY), conditionsFromItem(ModItems.RUBY))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "deliberilium")));

                offerSmelting(
                        List.of(ModItems.RAW_BEEF_WELLINGTON), // Inputs
                        RecipeCategory.FOOD, // Category
                        ModItems.OVERCOOKED_BEEF_WELLINGTON, // Output
                        0.1f, // Experience
                        300, // Cooking time
                        "raw_beef_wellington_to_overcooked_beef_wellington" // group
                );

                createShaped(RecipeCategory.MISC, ModFluids.FROOTOP_BUCKET, 1)
                        .input('E', Items.WATER_BUCKET)
                        .input('F', Items.RESIN_BRICK)
                        .pattern("FFF")
                        .pattern("FEF")
                        .pattern("FFF")
                        .criterion(hasItem(Items.WATER_BUCKET), conditionsFromItem(Items.WATER_BUCKET))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "frootop_bucket")));

                createShaped(RecipeCategory.MISC, ModBlocks.END_RELAY, 1)
                        .input('E', Items.OBSIDIAN)
                        .input('F', Items.POPPED_CHORUS_FRUIT)
                        .input('G', Items.ENDER_EYE)
                        .pattern("EFE")
                        .pattern("FGF")
                        .pattern("EFE")
                        .criterion(hasItem(Items.WATER_BUCKET), conditionsFromItem(Items.WATER_BUCKET))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(BsmpC.MOD_ID, "end_relay")));
            }
        };
    }

    @Override
    public String getName() {
        return "ModRecipeProvider";
    }
}
