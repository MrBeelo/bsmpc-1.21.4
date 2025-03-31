package net.mrbeelo.bsmpc.item;

import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvents;
import net.mrbeelo.bsmpc.BsmpC;
import net.mrbeelo.bsmpc.util.ModTags;

import java.util.Map;

public class ModArmorMaterials {

    //REGISTERING

    public static final RegistryKey<EquipmentAsset> RUBY_ARMOR_MATERIAL_KEY = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, BsmpC.id("ruby"));

    public static final ArmorMaterial RUBY = new ArmorMaterial(4000, Map.of(
                    EquipmentType.HELMET, 5,
                    EquipmentType.CHESTPLATE, 9,
                    EquipmentType.LEGGINGS, 8,
                    EquipmentType.BOOTS, 4
            ),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            4,
            0.2F,
            ModTags.Items.REPAIRS_RUBY_ARMOR,
            RUBY_ARMOR_MATERIAL_KEY);

    public static final RegistryKey<EquipmentAsset> SNEK_ARMOR_MATERIAL_KEY = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, BsmpC.id("snek"));

    public static final ArmorMaterial SNEK = new ArmorMaterial(7000, Map.of(
                    EquipmentType.HELMET, 8,
                    EquipmentType.CHESTPLATE, 12,
                    EquipmentType.LEGGINGS, 11,
                    EquipmentType.BOOTS, 7
            ),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            6,
            0.3F,
            ModTags.Items.REPAIRS_SNEK_ARMOR,
            SNEK_ARMOR_MATERIAL_KEY);

    public static final RegistryKey<EquipmentAsset> MITHRILL_ARMOR_MATERIAL_KEY = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, BsmpC.id("mithrill"));

    public static final ArmorMaterial MITHRILL = new ArmorMaterial(6000, Map.of(
                    EquipmentType.HELMET, 7,
                    EquipmentType.CHESTPLATE, 11,
                    EquipmentType.LEGGINGS, 10,
                    EquipmentType.BOOTS, 6
            ),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            8,
            0.4F,
            ModTags.Items.REPAIRS_MITHRILL_ARMOR,
            MITHRILL_ARMOR_MATERIAL_KEY);

    public static final RegistryKey<EquipmentAsset> JETPACK_ARMOR_MATERIAL_KEY = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, BsmpC.id("jetpack"));

    public static final ArmorMaterial JETPACK = new ArmorMaterial(10000, Map.of(
                    EquipmentType.CHESTPLATE, 1
            ),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            1,
            0F,
            null,
            JETPACK_ARMOR_MATERIAL_KEY);

    public static void registerModArmorMaterials() {
        BsmpC.LOGGER.info("Registering Mod Armor Materials for " + BsmpC.MOD_ID);
    }
}






