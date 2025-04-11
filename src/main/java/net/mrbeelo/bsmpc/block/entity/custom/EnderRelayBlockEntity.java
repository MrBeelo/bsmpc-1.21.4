package net.mrbeelo.bsmpc.block.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.mrbeelo.bsmpc.block.entity.ModBlockEntities;

public class EnderRelayBlockEntity extends BlockEntity {
    public EnderRelayBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ENDER_RELAY_BE, pos, state);
    }

    BlockPos connectedPos = null;

    public BlockPos getConnectedPos() {
        return connectedPos;
    }

    public void setConnectedPos(BlockPos connectedPos) {
        this.connectedPos = connectedPos;
    }
}
