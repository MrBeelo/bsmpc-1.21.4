package net.mrbeelo.bsmpc.components;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.math.BlockPos;
import net.mrbeelo.bsmpc.BsmpC;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    public static final ComponentType<Integer> MBS_STAGE = Registry.register(Registries.DATA_COMPONENT_TYPE, BsmpC.id("mbs_stage"), ComponentType.<Integer>builder().codec(Codec.INT).build());
    public static final ComponentType<Integer> BUMPSCOSITY = Registry.register(Registries.DATA_COMPONENT_TYPE, BsmpC.id("bumpscosity"), ComponentType.<Integer>builder().codec(Codec.INT).build());
    public static final ComponentType<BlockPos> KEY_ASSIGNED_POS = register("key_assigned_pos", builder -> builder.codec(BlockPos.CODEC));
    public static final ComponentType<BlockPos> END_RELAY_ASSIGNED_POS = register("end_relay_assigned_pos", builder -> builder.codec(BlockPos.CODEC));
    public static final ComponentType<Boolean> SAFE_OCCUPIED = register("safe_occupied", builder -> builder.codec(Codec.BOOL));

    private static <T> ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, BsmpC.id(name),
                (builderOperator.apply(ComponentType.builder())).build());
    }

    public static void registerModDataComponentTypes() {
        BsmpC.LOGGER.info("Registering Mod Data Component Types for " + BsmpC.MOD_ID);
    }
}
