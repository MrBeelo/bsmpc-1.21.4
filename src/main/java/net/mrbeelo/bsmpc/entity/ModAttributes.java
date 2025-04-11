package net.mrbeelo.bsmpc.entity;


import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.mrbeelo.bsmpc.BsmpC;
import net.mrbeelo.bsmpc.entity.custom.BlobEntity;
import net.mrbeelo.bsmpc.entity.custom.ProtectorBossEntity;
import net.mrbeelo.bsmpc.entity.custom.SnekEntity;
import net.mrbeelo.bsmpc.entity.custom.PyroEntity;

public class ModAttributes {
    public static void registerModAttributes() {
        BsmpC.LOGGER.info("Registering Mod Attributes for " + BsmpC.MOD_ID);

        FabricDefaultAttributeRegistry.register(ModEntities.SNEK, SnekEntity.createSnekAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.BLOB, BlobEntity.createBlobAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.PYRO, PyroEntity.createPyroAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.PROTECTOR, ProtectorBossEntity.createProtectorBossAttributes());
    }
}
