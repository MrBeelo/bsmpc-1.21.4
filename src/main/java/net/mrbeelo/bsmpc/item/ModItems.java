package net.mrbeelo.bsmpc.item;

//import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.mrbeelo.bsmpc.BsmpC;
import net.mrbeelo.bsmpc.block.ModBlocks;
import net.mrbeelo.bsmpc.components.ModDataComponentTypes;
import net.mrbeelo.bsmpc.entity.ModEntities;
import net.mrbeelo.bsmpc.item.custom.*;
import net.mrbeelo.bsmpc.sound.ModSounds;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ModItems {

    //REGISTERING

    public static RegistryKey<Item> regKeyItem(String name)
    {
        return RegistryKey.of(RegistryKeys.ITEM, BsmpC.id(name));
    }

    public static final Item RUBY = register("ruby", new Item(new Item.Settings().registryKey(regKeyItem("ruby")).maxCount(86)));
    public static final Item RUBY_INGOT = register("ruby_ingot", new Item(new Item.Settings().registryKey(regKeyItem("ruby_ingot"))));
    public static final Item RUBY_CLUSTER = register("ruby_cluster", new Item(new Item.Settings().registryKey(regKeyItem("ruby_cluster"))));
    public static final Item RUBY_SHARD = register("ruby_shard", new Item(new Item.Settings().registryKey(regKeyItem("ruby_shard"))));

    public static final Item KOKAINA_SEED = register("kokaina_seed", new BlockItem(ModBlocks.KOKAINA_CROP, new Item.Settings().registryKey(regKeyItem("kokaina_seed"))));
    public static final KokainaItem KOKAINA = register("kokaina", new KokainaItem(new Item.Settings().registryKey(regKeyItem("kokaina")).maxCount(16).food(ModFoodItems.KOKAINA_COMPONENT, ModFoodItems.KOKAINA_CONSUMABLE_COMPONENT)));

    public static final SwordItem RUBY_SWORD = register("ruby_sword", new SwordItem(ModToolMaterials.RUBY, 3, -2.4F, new Item.Settings().registryKey(regKeyItem("ruby_sword")))); //2 BONUS DMG INSTEAD OF 1
    public static final PickaxeItem RUBY_PICKAXE = register("ruby_pickaxe", new PickaxeItem(ModToolMaterials.RUBY, 0, -2.8f, new Item.Settings().registryKey(regKeyItem("ruby_pickaxe"))));
    public static final AxeItem RUBY_AXE = register("ruby_axe", new AxeItem(ModToolMaterials.RUBY, 0, -2.8f, new Item.Settings().registryKey(regKeyItem("ruby_axe")))); //2 BONUS DMG INSTEAD OF 1
    public static final ShovelItem RUBY_SHOVEL = register("ruby_shovel", new ShovelItem(ModToolMaterials.RUBY, 0.5f, -3f, new Item.Settings().registryKey(regKeyItem("ruby_shovel"))));
    public static final HoeItem RUBY_HOE = register("ruby_hoe", new HoeItem(ModToolMaterials.RUBY, -6f, 0f, new Item.Settings().registryKey(regKeyItem("ruby_hoe"))));
    public static final ArmorItem RUBY_HELMET = register("ruby_helmet", new ArmorItem(ModArmorMaterials.RUBY, EquipmentType.HELMET, new Item.Settings().registryKey(regKeyItem("ruby_helmet")).maxDamage(EquipmentType.HELMET.getMaxDamage(45))));
    public static final ModArmorItem RUBY_CHESTPLATE = register("ruby_chestplate", new ModArmorItem(ModArmorMaterials.RUBY, EquipmentType.CHESTPLATE, new Item.Settings().registryKey(regKeyItem("ruby_chestplate")).maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(45))));
    public static final ArmorItem RUBY_LEGGINGS = register("ruby_leggings", new ArmorItem(ModArmorMaterials.RUBY, EquipmentType.LEGGINGS, new Item.Settings().registryKey(regKeyItem("ruby_leggings")).maxDamage(EquipmentType.LEGGINGS.getMaxDamage(45))));
    public static final ArmorItem RUBY_BOOTS = register("ruby_boots", new ArmorItem(ModArmorMaterials.RUBY, EquipmentType.BOOTS, new Item.Settings().registryKey(regKeyItem("ruby_boots")).maxDamage(EquipmentType.BOOTS.getMaxDamage(45))));
    public static final HighKnockbackItem RUBY_KNOCKER = register("ruby_knocker", new HighKnockbackItem(new Item.Settings().registryKey(regKeyItem("ruby_knocker")).maxCount(1), 8f));

    public static final Item PISTOL = register("pistol", new PistolItem(new Item.Settings().registryKey(regKeyItem("pistol")).maxCount(1)));
    public static final Item BULLET = register("bullet", new Item(new Item.Settings().registryKey(regKeyItem("bullet"))));
    public static final DeathAxeItem DEATH_AXE = register("death_axe", new DeathAxeItem(9997, -3.5f, new Item.Settings().registryKey(regKeyItem("death_axe"))));
    public static final NukeButtonItem NUKE_BUTTON = register("nuke_button", new NukeButtonItem(new Item.Settings().registryKey(regKeyItem("nuke_button")).maxCount(1)));
    public static final MBSItem MASTER_BEELO_STAFF = register("master_beelo_staff", new MBSItem(new Item.Settings().registryKey(regKeyItem("master_beelo_staff")).maxCount(1).component(ModDataComponentTypes.MBS_STAGE, 0)));
    public static final Item XO_MUSIC_DISC = register("xo_music_disc", new Item(new Item.Settings().registryKey(regKeyItem("xo_music_disc")).jukeboxPlayable(ModSounds.XO_KEY)));
    public static final Item RANDOM_ARTIFACT = register("random_artifact", new RandomArtifactItem(new Item.Settings().registryKey(regKeyItem("random_artifact")).maxCount(1)));
    public static final PokeBallItem POKE_BALL = register("poke_ball", new PokeBallItem(new Item.Settings().registryKey(regKeyItem("poke_ball"))));

    public static final Item WARDEN_HORN = register("warden_horn", new Item(new Item.Settings().registryKey(regKeyItem("warden_horn"))));
    public static final SwordItem WARDEN_SWORD = register("warden_sword", new SwordItem(ModToolMaterials.WARDEN_HORN, 3, -2.4f, new Item.Settings().registryKey(regKeyItem("warden_sword"))));

    public static final ArmorItem SNEK_HELMET = register("snek_helmet", new ArmorItem(ModArmorMaterials.SNEK, EquipmentType.HELMET, new Item.Settings().registryKey(regKeyItem("snek_helmet")).maxDamage(EquipmentType.HELMET.getMaxDamage(50))));
    public static final ModArmorItem SNEK_CHESTPLATE = register("snek_chestplate", new ModArmorItem(ModArmorMaterials.SNEK, EquipmentType.CHESTPLATE, new Item.Settings().registryKey(regKeyItem("snek_chestplate")).maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(50))));
    public static final ArmorItem SNEK_LEGGINGS = register("snek_leggings", new ArmorItem(ModArmorMaterials.SNEK, EquipmentType.LEGGINGS, new Item.Settings().registryKey(regKeyItem("snek_leggings")).maxDamage(EquipmentType.LEGGINGS.getMaxDamage(50))));
    public static final ArmorItem SNEK_BOOTS = register("snek_boots", new ArmorItem(ModArmorMaterials.SNEK, EquipmentType.BOOTS, new Item.Settings().registryKey(regKeyItem("snek_boots")).maxDamage(EquipmentType.BOOTS.getMaxDamage(50))));
    public static final Item SNEK_SCALE = register("snek_scale", new Item(new Item.Settings().registryKey(regKeyItem("snek_scale"))));

    public static final Item SNEK_SPAWN_EGG = register("snek_spawn_egg", new SpawnEggItem(ModEntities.SNEK,  new Item.Settings().registryKey(regKeyItem("snek_spawn_egg"))));
    public static final Item BLOB_SPAWN_EGG = register("blob_spawn_egg", new SpawnEggItem(ModEntities.BLOB,  new Item.Settings().registryKey(regKeyItem("blob_spawn_egg"))));
    public static final Item PYRO_SPAWN_EGG = register("pyro_spawn_egg", new SpawnEggItem(ModEntities.PYRO,  new Item.Settings().registryKey(regKeyItem("pyro_spawn_egg"))));

    public static final JetpackItem JETPACK = register("jetpack", new JetpackItem(ModArmorMaterials.JETPACK, EquipmentType.CHESTPLATE, new Item.Settings().registryKey(regKeyItem("jetpack")).maxDamage(20000)));

    public static final Item MITHRILL = register("mithrill", new Item(new Item.Settings().registryKey(regKeyItem("mithrill"))));
    public static final SwordItem MITHRILL_SWORD = register("mithrill_sword", new SwordItem(ModToolMaterials.MITHRILL, 2, -2.6f, new Item.Settings().registryKey(regKeyItem("mithrill_sword")))); //2 BONUS DMG INSTEAD OF 1
    public static final PickaxeItem MITHRILL_PICKAXE = register("mithrill_pickaxe", new PickaxeItem(ModToolMaterials.MITHRILL, -3f, -2.8f, new Item.Settings().registryKey(regKeyItem("mithrill_pickaxe"))));
    public static final AxeItem MITHRILL_AXE = register("mithrill_axe", new AxeItem(ModToolMaterials.MITHRILL, 5, -3.1f, new Item.Settings().registryKey(regKeyItem("mithrill_axe")))); //2 BONUS DMG INSTEAD OF 1
    public static final ShovelItem MITHRILL_SHOVEL = register("mithrill_shovel", new ShovelItem(ModToolMaterials.MITHRILL, -2.5f, -3f, new Item.Settings().registryKey(regKeyItem("mithrill_shovel"))));
    public static final HoeItem MITHRILL_HOE = register("mithrill_hoe", new HoeItem(ModToolMaterials.MITHRILL, -2.5f, -3f, new Item.Settings().registryKey(regKeyItem("mithrill_hoe"))));
    public static final ArmorItem MITHRILL_HELMET = register("mithrill_helmet", new ArmorItem(ModArmorMaterials.MITHRILL, EquipmentType.HELMET, new Item.Settings().registryKey(regKeyItem("mithrill_helmet")).maxDamage(EquipmentType.HELMET.getMaxDamage(50))));
    public static final ModArmorItem MITHRILL_CHESTPLATE = register("mithrill_chestplate", new ModArmorItem(ModArmorMaterials.MITHRILL, EquipmentType.CHESTPLATE, new Item.Settings().registryKey(regKeyItem("mithrill_chestplate")).maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(50))));
    public static final ArmorItem MITHRILL_LEGGINGS = register("mithrill_leggings", new ArmorItem(ModArmorMaterials.MITHRILL, EquipmentType.LEGGINGS, new Item.Settings().registryKey(regKeyItem("mithrill_leggings")).maxDamage(EquipmentType.LEGGINGS.getMaxDamage(50))));
    public static final ArmorItem MITHRILL_BOOTS = register("mithrill_boots", new ArmorItem(ModArmorMaterials.MITHRILL, EquipmentType.BOOTS, new Item.Settings().registryKey(regKeyItem("mithrill_boots")).maxDamage(EquipmentType.BOOTS.getMaxDamage(50))));

    public static final SwordItem THALLEOUS_LONGSWORD = register("thalleous_longsword", new SwordItem(ToolMaterial.DIAMOND, 8, -2.4f, new Item.Settings().registryKey(regKeyItem("thalleous_longsword"))));
    public static final SwordItem MITHRILLED_THALLEOUS_LONGSWORD = register("mithrilled_thalleous_longsword", new SwordItem(ModToolMaterials.MITHRILL, 4, -2.4f, new Item.Settings().registryKey(regKeyItem("mithrilled_thalleous_longsword"))));

    public static final FishItem FISH_MEME = register("fish_meme", new FishItem(new Item.Settings().registryKey(regKeyItem("fish_meme")).maxCount(99)));

    public static final EnderSwordItem ENDER_SWORD = register("ender_sword", new EnderSwordItem(ModToolMaterials.MITHRILL, 1, -2.6f, new Item.Settings().registryKey(regKeyItem("ender_sword"))));
    public static final SwordItem BEELO_SWORD = register("beelo_sword", new SwordItem(ModToolMaterials.RUBY, 10, -2.4f, new Item.Settings().registryKey(regKeyItem("beelo_sword"))));

    public static final HighKnockbackItem STICC = register("sticc", new HighKnockbackItem(new Item.Settings().registryKey(regKeyItem("sticc")).maxCount(1), 40f));

    public static final ScytheItem SCYTHE = register("scythe", new ScytheItem(ToolMaterial.DIAMOND, 7, -2.4f, new Item.Settings().registryKey(regKeyItem("scythe"))));
    public static final AxeItem BARDICHE = register("bardiche", new AxeItem(ToolMaterial.DIAMOND, 9, -3.0f, new Item.Settings().registryKey(regKeyItem("bardiche"))));
    public static final AxeItem GRAF_EISER = register("graf_eiser", new AxeItem(ToolMaterial.DIAMOND, 11, -3.3f, new Item.Settings().registryKey(regKeyItem("graf_eiser"))));
    public static final Item METAL_ROD = register("metal_rod", new Item(new Item.Settings().registryKey(regKeyItem("metal_rod"))));
    public static final Item ENERGY_CRYSTAL = register("energy_crystal", new Item(new Item.Settings().registryKey(regKeyItem("energy_crystal"))));

    public static final Item DELIBERILIUM = register("deliberilium", new Item(new Item.Settings().registryKey(regKeyItem("deliberilium"))));

    public static final KeyItem KEY = register("key", new KeyItem(new Item.Settings().registryKey(regKeyItem("key")).maxCount(1).component(ModDataComponentTypes.KEY_ASSIGNED_POS, new BlockPos(0, 0, 0))));
    public static final GlobalKeyItem GLOBAL_KEY = register("global_key", new GlobalKeyItem(new Item.Settings().registryKey(regKeyItem("global_key")).maxCount(1)));

    public static final RulerItem RULER = register("ruler", new RulerItem(new Item.Settings().registryKey(regKeyItem("ruler")).maxCount(1)));

    public static final BeefWellingtonItem RAW_BEEF_WELLINGTON = register("raw_beef_wellington", new BeefWellingtonItem(new Item.Settings().registryKey(regKeyItem("raw_beef_wellington")).food(ModFoodItems.RAW_BF_COMPONENT, ModFoodItems.RAW_BF_CONSUMABLE_COMPONENT), true));
    public static final BeefWellingtonItem OVERCOOKED_BEEF_WELLINGTON = register("overcooked_beef_wellington", new BeefWellingtonItem(new Item.Settings().registryKey(regKeyItem("overcooked_beef_wellington")).food(ModFoodItems.OVERCOOKED_BF_COMPONENT, ModFoodItems.OVERCOOKED_BF_CONSUMABLE_COMPONENT), false));

    public static final BlockItem CS_BERRIES = register("cs_berries", new BlockItem(ModBlocks.CS_BERRY_BUSH, new Item.Settings().food(ModFoodItems.CS_BERRY_COMPONENT, ModFoodItems.CS_BERRY_CONSUMABLE_COMPONENT).registryKey(regKeyItem("cs_berries"))));

    //public static final SignItem CS_SIGN = register("cs_sign", new SignItem(ModBlocks.STANDING_CS_SIGN, ModBlocks.WALL_CS_SIGN, new Item.Settings().registryKey(regKeyItem("cs_sign")).maxCount(16)));
    //public static final HangingSignItem HANGING_CS_SIGN = register("cs_hanging_sign", new HangingSignItem(ModBlocks.HANGING_CS_SIGN, ModBlocks.WALL_HANGING_CS_SIGN, new Item.Settings().registryKey(regKeyItem("cs_hanging_sign")).maxCount(16)));

    //public static final Item CS_BOAT = TerraformBoatItemHelper.registerBoatItem(BsmpC.id("cs"), new Item.Settings().registryKey(regKeyItem("cs_boat")), false, false);
    //public static final Item CS_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(BsmpC.id("cs"), new Item.Settings().registryKey(regKeyItem("cs_chest_boat")), true, false);

    //METHODS

    public static <T extends Item> T register(String name, T item) {
        return Registry.register(Registries.ITEM, BsmpC.id(name), item);
    }

    public static void registerModItems() {
        BsmpC.LOGGER.info("Registering Mod Items for " + BsmpC.MOD_ID);
    }
}
