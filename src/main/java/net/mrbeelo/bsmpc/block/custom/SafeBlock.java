package net.mrbeelo.bsmpc.block.custom;

import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.mrbeelo.bsmpc.block.entity.custom.SafeBlockEntity;
import net.mrbeelo.bsmpc.components.ModDataComponentTypes;
import net.mrbeelo.bsmpc.item.ModItems;
import net.mrbeelo.bsmpc.item.custom.GlobalKeyItem;
import net.mrbeelo.bsmpc.item.custom.KeyItem;
import org.jetbrains.annotations.Nullable;

public class SafeBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final MapCodec<SafeBlock> CODEC = createCodec(SafeBlock::new);

    public static final Property<Direction> FACING = Properties.HORIZONTAL_FACING;

    public SafeBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SafeBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void onStateReplaced(BlockState state, ServerWorld world, BlockPos pos, boolean moved) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if(blockEntity instanceof SafeBlockEntity) {
            ItemScatterer.spawn(world, pos, ((SafeBlockEntity) blockEntity));
            world.updateComparators(pos, this);
        }
        super.onStateReplaced(state, world, pos, moved);
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos,
                                             PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof SafeBlockEntity safeBlockEntity) {
            if (!player.isSneaking() && !world.isClient() && stack.getItem() instanceof KeyItem keyItem) {
                BlockPos assignedPos = stack.get(ModDataComponentTypes.KEY_ASSIGNED_POS);
                if((assignedPos.getX() == pos.getX() && assignedPos.getY() == pos.getY() && assignedPos.getZ() == pos.getZ())) {
                    player.openHandledScreen(safeBlockEntity);
                } else {
                    player.sendMessage(Text.of("Using different key!"), true);
                }
            } else if (!player.isSneaking() && !world.isClient() && stack.getItem() instanceof GlobalKeyItem globalKeyItem) {
                player.openHandledScreen(safeBlockEntity);
            } else if (!player.isSneaking() && !world.isClient())
            {
                player.sendMessage(Text.of("Safe locked!"), true);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    protected void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        // Register a callback for block break events
        PlayerBlockBreakEvents.BEFORE.register((eventWorld, playerEntity, pos1, state1, entity1) -> {
            if (eventWorld.getBlockEntity(pos1) instanceof SafeBlockEntity safeBlockEntity && safeBlockEntity.getOccupation()) {
                // If the safe is occupied, prevent breaking
                playerEntity.sendMessage(Text.of("The safe is occupied and cannot be destroyed!"), true);
                return false;  // Returning false cancels the block breaking
            }
            return true;  // Allow breaking otherwise
        });

        super.onBlockBreakStart(state, world, pos, player);
    }
}
