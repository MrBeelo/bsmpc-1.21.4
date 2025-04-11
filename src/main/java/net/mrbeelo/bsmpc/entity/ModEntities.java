package net.mrbeelo.bsmpc.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.mrbeelo.bsmpc.BsmpC;
import net.mrbeelo.bsmpc.entity.custom.*;

public class ModEntities {
    public static final EntityType<SnekEntity> SNEK = Registry.register(Registries.ENTITY_TYPE,
            BsmpC.id("snek"),
            EntityType.Builder.create(SnekEntity::new, SpawnGroup.CREATURE).dimensions(2f, 6f).build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(BsmpC.MOD_ID, "snek"))));

    public static final EntityType<PokeBallProjectileEntity> POKE_BALL_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            BsmpC.id("poke_ball_projectile"),
            EntityType.Builder.<PokeBallProjectileEntity>create(PokeBallProjectileEntity::new, SpawnGroup.MISC).dimensions(0.25f, 0.25f).build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(BsmpC.MOD_ID, "poke_ball_projectile"))));

    public static final EntityType<PurifyBombProjectileEntity> PURIFY_BOMB_PROJECTILE = Registry.register(
            Registries.ENTITY_TYPE,
            BsmpC.id("purify_bomb_projectile"),
            EntityType.Builder.create(PurifyBombProjectileEntity::new, SpawnGroup.MISC).dimensions(0.5f, 0.5f).build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(BsmpC.MOD_ID, "purify_bomb_projectile")))
    );

    public static final EntityType<BulletProjectileEntity> BULLET_PROJECTILE = Registry.register(
            Registries.ENTITY_TYPE,
            BsmpC.id("bullet_projectile"),
            EntityType.Builder.create(BulletProjectileEntity::new, SpawnGroup.MISC).dimensions(0.5f, 0.5f).build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(BsmpC.MOD_ID, "bullet_projectile")))
    );

    public static final EntityType<BlobEntity> BLOB = Registry.register(Registries.ENTITY_TYPE,
            BsmpC.id("blob"),
            EntityType.Builder.create(BlobEntity::new, SpawnGroup.CREATURE).dimensions(1f, 1.5f).build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(BsmpC.MOD_ID, "blob"))));

    public static final EntityType<LazerEntity> LAZER = Registry.register(Registries.ENTITY_TYPE,
            BsmpC.id("lazer"),
            EntityType.Builder.create(LazerEntity::new, SpawnGroup.MISC).dimensions(1f, 1f).build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(BsmpC.MOD_ID, "lazer"))));

    public static final EntityType<PyroEntity> PYRO = Registry.register(Registries.ENTITY_TYPE,
            BsmpC.id("pyro"),
            EntityType.Builder.create(PyroEntity::new, SpawnGroup.CREATURE).dimensions(1.2f, 1.0f).build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(BsmpC.MOD_ID, "pyro"))));

    public static final EntityType<ProtectorBossEntity> PROTECTOR = Registry.register(Registries.ENTITY_TYPE,
            BsmpC.id("protector"),
            EntityType.Builder.create(ProtectorBossEntity::new, SpawnGroup.CREATURE).dimensions(2.5f, 4.0f).build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(BsmpC.MOD_ID, "protector"))));

    public static void registerModEntities() {
        BsmpC.LOGGER.info("Registering Mod Entities for " + BsmpC.MOD_ID);
    }
}
