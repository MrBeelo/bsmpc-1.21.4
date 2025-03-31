package net.mrbeelo.bsmpc.item;

import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.mrbeelo.bsmpc.BsmpC;

import static net.minecraft.item.equipment.EquipmentAssetKeys.REGISTRY_KEY;

public class ModEquipmentAssets {
    public static final RegistryKey<EquipmentAsset> RUBY = register("ruby");
    public static final RegistryKey<EquipmentAsset> MITHRILL = register("mithrill");
    public static final RegistryKey<EquipmentAsset> SNEK = register("snek");
    public static final RegistryKey<EquipmentAsset> JETPACK = register("jetpack");

    static RegistryKey<EquipmentAsset> register(String name) {
        return RegistryKey.of(REGISTRY_KEY, Identifier.ofVanilla(name));
    }

    public static void registerModEquipmentAssets() {
        BsmpC.LOGGER.info("Registering Mod Equipment Assets for " + BsmpC.MOD_ID);
    }
}
