package net.mrbeelo.bsmpc.block.custom;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LodestoneTrackerComponent;
import net.minecraft.entity.Dismounting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.CollisionView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import net.mrbeelo.bsmpc.block.entity.custom.EnderRelayBlockEntity;
import net.mrbeelo.bsmpc.util.ModProperties;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class EnderRelayBlock extends BlockWithEntity {
    public static final MapCodec<EnderRelayBlock> CODEC = createCodec(EnderRelayBlock::new);
    public static final int NO_CHARGES = 0;
    public static final int MAX_CHARGES = 1;
    public static final IntProperty RELAY_CHARGES = ModProperties.RELAY_CHARGES;
    private static final ImmutableList<Vec3i> VALID_HORIZONTAL_SPAWN_OFFSETS = ImmutableList.of(
            new Vec3i(0, 0, -1),
            new Vec3i(-1, 0, 0),
            new Vec3i(0, 0, 1),
            new Vec3i(1, 0, 0),
            new Vec3i(-1, 0, -1),
            new Vec3i(1, 0, -1),
            new Vec3i(-1, 0, 1),
            new Vec3i(1, 0, 1)
    );
    private static final ImmutableList<Vec3i> VALID_SPAWN_OFFSETS = new ImmutableList.Builder<Vec3i>()
            .addAll(VALID_HORIZONTAL_SPAWN_OFFSETS)
            .addAll(VALID_HORIZONTAL_SPAWN_OFFSETS.stream().map(Vec3i::down).iterator())
            .addAll(VALID_HORIZONTAL_SPAWN_OFFSETS.stream().map(Vec3i::up).iterator())
            .add(new Vec3i(0, 1, 0))
            .build();

    @Override
    public MapCodec<EnderRelayBlock> getCodec() {
        return CODEC;
    }

    public EnderRelayBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(RELAY_CHARGES, 0));
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (isChargeItem(stack) && canCharge(state)) {
            charge(player, world, pos, state);
            stack.decrementUnlessCreative(1, player);
            return ActionResult.SUCCESS;
        } else if(stack.getItem() == Items.COMPASS && stack.contains(DataComponentTypes.LODESTONE_TRACKER)) {
            LodestoneTrackerComponent tracker = stack.get(DataComponentTypes.LODESTONE_TRACKER);
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof EnderRelayBlockEntity endRelayBlockEntity) {
                if (tracker != null && tracker.tracked() && tracker.target().isPresent()) {
                    BlockPos lodestonePos = tracker.target().get().pos();
                    stack.decrement(1);
                    if(endRelayBlockEntity.getConnectedPos() != null)
                    {
                        ItemStack newCompass = new ItemStack(Items.COMPASS, 1);

                        if(world.getBlockState(endRelayBlockEntity.getConnectedPos()).getBlock() == Blocks.LODESTONE) {
                            newCompass.set(DataComponentTypes.LODESTONE_TRACKER, new LodestoneTrackerComponent(Optional.of(new GlobalPos(World.END, endRelayBlockEntity.getConnectedPos())), true));
                        }

                        world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY() + 1, pos.getZ(), newCompass));
                    }

                    if (!world.isClient) {
                        player.sendMessage(Text.literal("Lodestone position: " + lodestonePos), true);
                        endRelayBlockEntity.setConnectedPos(lodestonePos);
                    }

                    return ActionResult.SUCCESS;
                }
            }
        } else {
            return (ActionResult)(hand == Hand.MAIN_HAND && isChargeItem(player.getStackInHand(Hand.OFF_HAND)) && canCharge(state)
                    ? ActionResult.PASS
                    : ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION);
        }
        return ActionResult.PASS;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if ((Integer)state.get(RELAY_CHARGES) == 0) {
            return ActionResult.PASS;
        } else if (!isEnd(world)) {
            if (!world.isClient) {
                this.explode(state, world, pos);
            }

            return ActionResult.SUCCESS;
        } else {
            if (!world.isClient) {
                BlockEntity blockEntity = world.getBlockEntity(pos);

                if (blockEntity instanceof EnderRelayBlockEntity endRelayBlockEntity && endRelayBlockEntity.getConnectedPos() != null) {
                    BlockPos connectedPos = endRelayBlockEntity.getConnectedPos();
                    ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) player;
                    if(world.getBlockState(connectedPos).getBlock() == Blocks.LODESTONE) {
                        serverPlayerEntity.sendMessage(Text.of("TPed to lodestone at " + connectedPos.getX() + ", " + connectedPos.getY() + ", " + connectedPos.getZ()), true);
                        serverPlayerEntity.requestTeleport(connectedPos.getX(), connectedPos.getY() + 1, connectedPos.getZ());
                        world.playSound(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, SoundEvents.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        world.setBlockState(pos, state.with(RELAY_CHARGES, 0), Block.NOTIFY_ALL);
                    } else {
                        serverPlayerEntity.sendMessage(Text.of("Lodestone at " + connectedPos.getX() + ", " + connectedPos.getY() + ", " + connectedPos.getZ() + " is broken!"), true);
                    }
                    return ActionResult.SUCCESS_SERVER;
                }
            }

            return ActionResult.CONSUME;
        }
    }

    private static boolean isChargeItem(ItemStack stack) {
        return stack.isOf(Items.END_CRYSTAL);
    }

    private static boolean canCharge(BlockState state) {
        return (Integer)state.get(RELAY_CHARGES) < 1;
    }

    private static boolean hasStillWater(BlockPos pos, World world) {
        FluidState fluidState = world.getFluidState(pos);
        if (!fluidState.isIn(FluidTags.WATER)) {
            return false;
        } else if (fluidState.isStill()) {
            return true;
        } else {
            float f = fluidState.getLevel();
            if (f < 2.0F) {
                return false;
            } else {
                FluidState fluidState2 = world.getFluidState(pos.down());
                return !fluidState2.isIn(FluidTags.WATER);
            }
        }
    }

    private void explode(BlockState state, World world, BlockPos explodedPos) {
        world.removeBlock(explodedPos, false);
        boolean bl = Direction.Type.HORIZONTAL.stream().map(explodedPos::offset).anyMatch(pos -> hasStillWater(pos, world));
        final boolean bl2 = bl || world.getFluidState(explodedPos.up()).isIn(FluidTags.WATER);
        ExplosionBehavior explosionBehavior = new ExplosionBehavior() {
            @Override
            public Optional<Float> getBlastResistance(Explosion explosion, BlockView worldx, BlockPos pos, BlockState blockState, FluidState fluidState) {
                return pos.equals(explodedPos) && bl2
                        ? Optional.of(Blocks.WATER.getBlastResistance())
                        : super.getBlastResistance(explosion, worldx, pos, blockState, fluidState);
            }
        };
        Vec3d vec3d = explodedPos.toCenterPos();
        world.createExplosion(null, world.getDamageSources().badRespawnPoint(vec3d), explosionBehavior, vec3d, 5.0F, true, World.ExplosionSourceType.BLOCK);
    }

    public static boolean isEnd(World world) {
        return world.getRegistryKey() == World.END;
    }

    public static void charge(@Nullable Entity charger, World world, BlockPos pos, BlockState state) {
        BlockState blockState = state.with(RELAY_CHARGES, (Integer)state.get(RELAY_CHARGES) + 1);
        world.setBlockState(pos, blockState, Block.NOTIFY_ALL);
        world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(charger, blockState));
        world.playSound(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if ((Integer)state.get(RELAY_CHARGES) != 0) {
            if (random.nextInt(100) == 0) {
                world.playSoundAtBlockCenter(pos, SoundEvents.BLOCK_RESPAWN_ANCHOR_AMBIENT, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }

            double d = pos.getX() + 0.5 + (0.5 - random.nextDouble());
            double e = pos.getY() + 1.0;
            double f = pos.getZ() + 0.5 + (0.5 - random.nextDouble());
            double g = random.nextFloat() * 0.04;
            world.addParticle(ParticleTypes.REVERSE_PORTAL, d, e, f, 0.0, g, 0.0);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(RELAY_CHARGES);
    }

    @Override
    protected boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    public static int getLightLevel(BlockState state, int maxLevel) {
        return MathHelper.floor(((Integer) state.get(RELAY_CHARGES)) / 4.0F * maxLevel);
    }

    @Override
    protected int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return getLightLevel(state, 15);
    }

    public static Optional<Vec3d> findRespawnPosition(EntityType<?> entity, CollisionView world, BlockPos pos) {
        Optional<Vec3d> optional = findRespawnPosition(entity, world, pos, true);
        return optional.isPresent() ? optional : findRespawnPosition(entity, world, pos, false);
    }

    private static Optional<Vec3d> findRespawnPosition(EntityType<?> entity, CollisionView world, BlockPos pos, boolean ignoreInvalidPos) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for (Vec3i vec3i : VALID_SPAWN_OFFSETS) {
            mutable.set(pos).move(vec3i);
            Vec3d vec3d = Dismounting.findRespawnPos(entity, world, mutable, ignoreInvalidPos);
            if (vec3d != null) {
                return Optional.of(vec3d);
            }
        }

        return Optional.empty();
    }

    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new EnderRelayBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}

