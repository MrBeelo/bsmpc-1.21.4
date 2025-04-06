package net.mrbeelo.bsmpc.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import net.mrbeelo.bsmpc.entity.ModEntities;
import net.mrbeelo.bsmpc.world.biome.ModBiomes;

public class ModEntitySpawns {
    public static void generateEntitySpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(ModBiomes.CS_BIOME),
                SpawnGroup.CREATURE, ModEntities.SNEK, 2, 1, 1);
        SpawnRestriction.register(ModEntities.SNEK, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnIgnoreLightLevel);

    }
}
