package net.mrbeelo.bsmpc.villager;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import net.mrbeelo.bsmpc.BsmpC;
import net.mrbeelo.bsmpc.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

public class ModVillagers {

    //REGISTERING

    public static final RegistryKey<PointOfInterestType> RUBY_POI_KEY = poiKey("rubypoi");
    public static final PointOfInterestType RUBY_POI = registerPOI("rubypoi", ModBlocks.RUBY_BLOCK);

    public static final RegistryKey<VillagerProfession> RUBERT_KEY = professionKey("rubert");
    public static final VillagerProfession RUBERT = registerProfession("rubert", RUBY_POI_KEY);

    //METHODS

    private static RegistryKey<VillagerProfession> professionKey(String id) {
        return RegistryKey.of(RegistryKeys.VILLAGER_PROFESSION, BsmpC.id(id));
    }

    private static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> type) {
        return Registry.register(Registries.VILLAGER_PROFESSION, BsmpC.id(name),
                new VillagerProfession(Text.of(name), entry -> entry.matchesKey(type), entry -> entry.matchesKey(type),
                        ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_SHEPHERD));
    }

    private static PointOfInterestType registerPOI(String name, Block block) {
        return PointOfInterestHelper.register(BsmpC.id(name), 1, 2, block);
    }

    private static RegistryKey<PointOfInterestType> poiKey(String name) {
        return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, BsmpC.id(name));
    }

    public static void registerModVillagers() {
        BsmpC.LOGGER.info("Registering Mod Villagers for " + BsmpC.MOD_ID);
    }
}
