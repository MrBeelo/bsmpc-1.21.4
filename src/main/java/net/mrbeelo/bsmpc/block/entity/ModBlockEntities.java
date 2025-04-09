package net.mrbeelo.bsmpc.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.mrbeelo.bsmpc.BsmpC;
import net.mrbeelo.bsmpc.block.ModBlocks;
import net.mrbeelo.bsmpc.block.entity.custom.*;

public class ModBlockEntities {
    public static final BlockEntityType<PedestalBlockEntity> PEDESTAL_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, BsmpC.id("pedestal_be"),
                    FabricBlockEntityTypeBuilder.create(PedestalBlockEntity::new, ModBlocks.PEDESTAL).build());

    public static final BlockEntityType<SafeBlockEntity> SAFE_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, BsmpC.id("safe_be"),
                    FabricBlockEntityTypeBuilder.create(SafeBlockEntity::new, ModBlocks.SAFE).build());

    public static final BlockEntityType<MeeperBlockEntity> MEEPER_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, BsmpC.id("meeper_be"),
                    FabricBlockEntityTypeBuilder.create(MeeperBlockEntity::new, ModBlocks.MEEPER).build());

    public static final BlockEntityType<DeliberatorBlockEntity> DELIBERATOR_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, BsmpC.id("deliberator_be"),
                    FabricBlockEntityTypeBuilder.create(DeliberatorBlockEntity::new, ModBlocks.DELIBERATOR).build());

    public static final BlockEntityType<EndRelayBlockEntity> END_RELAY_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, BsmpC.id("end_relay_be"),
                    FabricBlockEntityTypeBuilder.create(EndRelayBlockEntity::new, ModBlocks.END_RELAY).build());


    public static void registerModBlockEntities() {
        BsmpC.LOGGER.info("Registering Mod Block Entities for " + BsmpC.MOD_ID);
    }
}
