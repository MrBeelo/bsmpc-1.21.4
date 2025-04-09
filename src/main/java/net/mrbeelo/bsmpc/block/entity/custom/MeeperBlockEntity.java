package net.mrbeelo.bsmpc.block.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mrbeelo.bsmpc.block.entity.ModBlockEntities;
import net.mrbeelo.bsmpc.sound.ModSounds;

public class MeeperBlockEntity extends BlockEntity {
    private int tickCounter = 0; // Counter to track time

    public MeeperBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MEEPER_BE, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, MeeperBlockEntity be) {
        if (world.isClient) return; // Ensure it runs only on the server

        be.tickCounter++;
        if (be.tickCounter >= 100) { // 100 ticks = 5 seconds
            be.tickCounter = 0;

            world.playSound(null, pos, ModSounds.MEEP, SoundCategory.BLOCKS, 1.0f, 1.0f);
        }
    }
}
