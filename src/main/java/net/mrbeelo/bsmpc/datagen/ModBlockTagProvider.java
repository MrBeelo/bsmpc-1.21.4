package net.mrbeelo.bsmpc.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.mrbeelo.bsmpc.block.ModBlocks;
import net.mrbeelo.bsmpc.util.ModTags;

import javax.swing.text.html.BlockView;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        getOrCreateTagBuilder(ModTags.Blocks.RUBY_ORES)
                .add(ModBlocks.RUBY_ORE)
                .add(ModBlocks.DEEPSLATE_RUBY_ORE)
                .add(ModBlocks.NETHER_RUBY_ORE)
                .add(ModBlocks.END_RUBY_ORE);

        getOrCreateTagBuilder(ModTags.Blocks.RUBY_BLOCKS)
                .add(ModBlocks.RUBY_BLOCK)
                .add(ModBlocks.RUBY_STAIRS)
                .add(ModBlocks.RUBY_SLAB)
                .add(ModBlocks.RUBY_BUTTON)
                .add(ModBlocks.RUBY_PRESSURE_PLATE)
                .add(ModBlocks.RUBY_FENCE)
                .add(ModBlocks.RUBY_FENCE_GATE)
                .add(ModBlocks.RUBY_WALL)
                .add(ModBlocks.RUBY_DOOR)
                .add(ModBlocks.RUBY_TRAPDOOR)
                .addTag(ModTags.Blocks.RUBY_ORES);

        getOrCreateTagBuilder(ModTags.Blocks.CS_BLOCKS)
                .add(ModBlocks.CS_LOG)
                .add(ModBlocks.CS_WOOD)
                .add(ModBlocks.STRIPPED_CS_LOG)
                .add(ModBlocks.STRIPPED_CS_WOOD)
                .add(ModBlocks.CS_PLANKS)
                .add(ModBlocks.CS_LEAVES)
                .add(ModBlocks.CS_SAPLING)
                .add(ModBlocks.CS_STAIRS)
                .add(ModBlocks.CS_SLAB)
                .add(ModBlocks.CS_BUTTON)
                .add(ModBlocks.CS_PRESSURE_PLATE)
                .add(ModBlocks.CS_FENCE)
                .add(ModBlocks.CS_FENCE_GATE)
                .add(ModBlocks.CS_DOOR)
                .add(ModBlocks.CS_TRAPDOOR);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL).addTag(ModTags.Blocks.RUBY_BLOCKS);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .addTag(ModTags.Blocks.RUBY_BLOCKS)
                .add(ModBlocks.SAFE)
                .add(ModBlocks.N_PLUSHIE)
                .add(ModBlocks.COMPANION_CUBE)
                .add(ModBlocks.GD_CUBE)
                .add(ModBlocks.DELIBERATOR);


        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).addTag(ModTags.Blocks.CS_BLOCKS);

        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(ModBlocks.CS_LEAVES);

        getOrCreateTagBuilder(BlockTags.SAPLINGS)
                .add(ModBlocks.CS_SAPLING);

        getOrCreateTagBuilder(BlockTags.BUTTONS)
                .add(ModBlocks.CS_BUTTON)
                .add(ModBlocks.RUBY_BUTTON);

        getOrCreateTagBuilder(BlockTags.PRESSURE_PLATES)
                .add(ModBlocks.CS_PRESSURE_PLATE)
                .add(ModBlocks.RUBY_PRESSURE_PLATE);

        getOrCreateTagBuilder(BlockTags.SLABS)
                .add(ModBlocks.CS_SLAB)
                .add(ModBlocks.RUBY_SLAB);

        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.CS_SLAB);

        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(ModBlocks.CS_FENCE)
                .add(ModBlocks.RUBY_FENCE);

        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.CS_FENCE_GATE)
                .add(ModBlocks.RUBY_FENCE_GATE);

        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.RUBY_WALL);

        getOrCreateTagBuilder(BlockTags.DOORS)
                .add(ModBlocks.CS_DOOR)
                .add(ModBlocks.RUBY_DOOR);

        getOrCreateTagBuilder(BlockTags.TRAPDOORS)
                .add(ModBlocks.CS_TRAPDOOR)
                .add(ModBlocks.RUBY_TRAPDOOR);

        //getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add(ModBlocks.STANDING_CS_SIGN);
        //getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add(ModBlocks.WALL_CS_SIGN);
        //getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS).add(ModBlocks.HANGING_CS_SIGN);
        //getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS).add(ModBlocks.WALL_HANGING_CS_SIGN);

        getOrCreateTagBuilder(BlockTags.BEACON_BASE_BLOCKS)
                .add(ModBlocks.RUBY_BLOCK)
                .add(ModBlocks.PACKED_IRON_BLOCK);

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.CS_LOG, ModBlocks.CS_WOOD, ModBlocks.STRIPPED_CS_LOG, ModBlocks.STRIPPED_CS_WOOD);
    }
}
