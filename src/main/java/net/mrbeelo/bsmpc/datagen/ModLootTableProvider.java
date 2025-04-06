package net.mrbeelo.bsmpc.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.CropBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.mrbeelo.bsmpc.block.ModBlocks;
import net.mrbeelo.bsmpc.block.custom.CSBerryBushBlock;
import net.mrbeelo.bsmpc.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        RegistryWrapper.Impl<Enchantment> impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);

        addDrop(ModBlocks.RUBY_BLOCK);
        addDrop(ModBlocks.RUBY_ORE, oreDrops(ModBlocks.RUBY_ORE, ModItems.RUBY));
        addDrop(ModBlocks.DEEPSLATE_RUBY_ORE, oreDrops(ModBlocks.DEEPSLATE_RUBY_ORE, ModItems.RUBY));
        addDrop(ModBlocks.NETHER_RUBY_ORE, multipleOreDrops(ModBlocks.NETHER_RUBY_ORE, ModItems.RUBY_SHARD,1, 3));
        addDrop(ModBlocks.PACKED_IRON_BLOCK);
        addDrop(ModBlocks.SMOOTH_QUARTZ_RUBY_UPGRADER);
        addDrop(ModBlocks.SMOOTH_QUARTZ_RUBY_DOWNGRADER);
        addDrop(ModBlocks.RUBY_STAIRS);
        addDrop(ModBlocks.RUBY_SLAB, slabDrops(ModBlocks.RUBY_SLAB));
        addDrop(ModBlocks.RUBY_BUTTON);
        addDrop(ModBlocks.RUBY_PRESSURE_PLATE);
        addDrop(ModBlocks.RUBY_FENCE);
        addDrop(ModBlocks.RUBY_FENCE_GATE);
        addDrop(ModBlocks.RUBY_WALL);
        addDrop(ModBlocks.RUBY_DOOR, doorDrops(ModBlocks.RUBY_DOOR));
        addDrop(ModBlocks.RUBY_TRAPDOOR);
        addDrop(ModBlocks.PEDESTAL);
        addDrop(ModBlocks.ROSE);
        addDrop(ModBlocks.POTTED_ROSE, pottedPlantDrops(ModBlocks.ROSE));

        addDrop(ModBlocks.CS_LOG);
        addDrop(ModBlocks.CS_WOOD);
        addDrop(ModBlocks.STRIPPED_CS_LOG);
        addDrop(ModBlocks.STRIPPED_CS_WOOD);
        addDrop(ModBlocks.CS_PLANKS);
        addDrop(ModBlocks.CS_SAPLING);
        addDrop(ModBlocks.CS_LEAVES, leavesDrops(ModBlocks.CS_LEAVES, ModBlocks.CS_SAPLING, 0.0625f));
        addDrop(ModBlocks.CS_STAIRS);
        addDrop(ModBlocks.CS_SLAB, slabDrops(ModBlocks.CS_SLAB));
        addDrop(ModBlocks.CS_BUTTON);
        addDrop(ModBlocks.CS_PRESSURE_PLATE);
        addDrop(ModBlocks.CS_FENCE);
        addDrop(ModBlocks.CS_FENCE_GATE);
        addDrop(ModBlocks.CS_DOOR, doorDrops(ModBlocks.CS_DOOR));
        addDrop(ModBlocks.CS_TRAPDOOR);

        addDrop(ModBlocks.SAFE);
        addDrop(ModBlocks.N_PLUSHIE);
        addDrop(ModBlocks.MEEPER);

        addDrop(ModBlocks.DELIBERATOR);

        addDrop(ModBlocks.COMPANION_CUBE);
        addDrop(ModBlocks.GD_CUBE);

        //addDrop(ModBlocks.STANDING_CS_SIGN, ModItems.CS_SIGN);
        //addDrop(ModBlocks.WALL_CS_SIGN, ModItems.CS_SIGN);
        //addDrop(ModBlocks.HANGING_CS_SIGN, ModItems.HANGING_CS_SIGN);
        //addDrop(ModBlocks.WALL_HANGING_CS_SIGN, ModItems.HANGING_CS_SIGN);


        LootCondition.Builder builder = BlockStatePropertyLootCondition.builder(ModBlocks.KOKAINA_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(CropBlock.AGE, 3));
        addDrop(ModBlocks.KOKAINA_CROP, cropDrops(ModBlocks.KOKAINA_CROP, ModItems.KOKAINA, ModItems.KOKAINA_SEED, builder));

        addDrop(ModBlocks.CS_BERRY_BUSH,
                block -> applyExplosionDecay(
                        block,LootTable.builder().pool(LootPool.builder().conditionally(
                                                BlockStatePropertyLootCondition.builder(ModBlocks.CS_BERRY_BUSH).properties(StatePredicate.Builder.create().exactMatch(CSBerryBushBlock.AGE, 3))
                                        )
                                        .with(ItemEntry.builder(ModItems.CS_BERRIES))
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F)))
                                        .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))
                        ).pool(LootPool.builder().conditionally(
                                        BlockStatePropertyLootCondition.builder(ModBlocks.CS_BERRY_BUSH).properties(StatePredicate.Builder.create().exactMatch(CSBerryBushBlock.AGE, 2))
                                ).with(ItemEntry.builder(ModItems.CS_BERRIES))
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                                .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE))))));
    }

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}





