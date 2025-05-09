package net.mrbeelo.bsmpc.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.mrbeelo.bsmpc.BsmpC;
import net.mrbeelo.bsmpc.block.ModBlocks;
import net.mrbeelo.bsmpc.effect.ModEffects;
import net.mrbeelo.bsmpc.enchantment.ModEnchantments;
import net.mrbeelo.bsmpc.entity.ModEntities;
import net.mrbeelo.bsmpc.fluid.ModFluids;
import net.mrbeelo.bsmpc.item.ModItemGroups;
import net.mrbeelo.bsmpc.item.ModItems;
import net.mrbeelo.bsmpc.potion.ModPotions;
import net.mrbeelo.bsmpc.sound.ModSounds;
import net.mrbeelo.bsmpc.villager.ModVillagers;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

import net.minecraft.sound.SoundEvent;

public class ModTranslationProvider extends FabricLanguageProvider {
    public ModTranslationProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    private static void addText(@NotNull TranslationBuilder builder, @NotNull Text text, @NotNull String value) {
        if (text.getContent() instanceof TranslatableTextContent translatableTextContent) {
            builder.add(translatableTextContent.getKey(), value);
        } else {
            BsmpC.LOGGER.warn("Cannot translate text: {}", text.getString());
        }
    }

    private static void addSoundEvent(@NotNull TranslationBuilder builder, @NotNull SoundEvent soundEvent, @NotNull String value) {
        String key = "sound." + soundEvent.id().toString().replace(":", ".");
        builder.add(key, value);
    }

    private static void addVillager(@NotNull TranslationBuilder builder, @NotNull VillagerProfession villagerProfession, @NotNull String value) {
        String key = "entity.minecraft.villager." + villagerProfession.id();
        builder.add(key, value);
    }

    private static void addPotionTranslations(@NotNull TranslationBuilder builder, RegistryEntry<Potion> potionEntry, @NotNull String value) {
        Potion potion = potionEntry.value();
        Identifier potionId = Registries.POTION.getId(potion);

        if (potionId != null) {
            String baseKey = "item.minecraft.potion.effect." + potionId.getPath();
            builder.add(baseKey, "Potion of " + value); // Customize translation value

            String splashKey = "item.minecraft.splash_potion.effect." + potionId.getPath();
            builder.add(splashKey, "Splash Potion of " + value); // Customize translation value

            String lingeringKey = "item.minecraft.lingering_potion.effect." + potionId.getPath();
            builder.add(lingeringKey, "Lingering Potion of " + value); // Customize translation value

            String tippedArrowKey = "item.minecraft.tipped_arrow.effect." + potionId.getPath();
            builder.add(tippedArrowKey, "Tipped Arrow with " + value); // Customize translation value
        }
    }

    private static void addMusicDisc(@NotNull TranslationBuilder builder, @NotNull String discId, @NotNull String discName, @NotNull String discDescription) {
        String discKey = "item.bsmpc." + discId;
        String discDescriptionKey = "item.bsmpc." + discId + ".desc";
        builder.add(discKey, discName);
        builder.add(discDescriptionKey, discDescription);
    }

    private static void addDamageType(@NotNull TranslationBuilder builder, @NotNull String messageId, @NotNull String messageValue) {
        String messageSingleKey = "death.attack." + messageId;
        builder.add(messageSingleKey, messageValue);
        String messagePlayerKey = "death.attack." + messageId + ".player";
        builder.add(messagePlayerKey, messageValue);
    }

    private static void addGui(@NotNull TranslationBuilder builder, @NotNull String messageId, @NotNull String messageValue) {
        String guiKey = "gui.bsmpc." + messageId;
        builder.add(guiKey, messageValue);
    }

    private static void addBoat(@NotNull TranslationBuilder builder, @NotNull String messageId, @NotNull String messageValue) {
        String guiKey = "entity.bsmpc." + messageId + "_boat";
        builder.add(guiKey, messageValue);
    }

    private static void addChestBoat(@NotNull TranslationBuilder builder, @NotNull String messageId, @NotNull String messageValue) {
        String guiKey = "entity.bsmpc." + messageId + "_chest_boat";
        builder.add(guiKey, messageValue);
    }

    private static void addPainting(@NotNull TranslationBuilder builder, @NotNull String paintingId, @NotNull String paintingName, @NotNull String paintingAuthor) {
        String paintingKey = "painting.bsmpc." + paintingId + ".title";
        String paintingAuthorKey = "painting.bsmpc." + paintingId + ".author";
        builder.add(paintingKey, paintingName);
        builder.add(paintingAuthorKey, paintingAuthor);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.RUBY, "Ruby");
        translationBuilder.add(ModItems.RUBY_INGOT, "Ruby Ingot");
        translationBuilder.add(ModItems.RUBY_CLUSTER, "Ruby Cluster");
        translationBuilder.add(ModItems.RUBY_SHARD, "Ruby Shard");
        translationBuilder.add(ModItems.KOKAINA, "Kokaina");
        translationBuilder.add(ModItems.KOKAINA_SEED, "Kokaina Seed");
        translationBuilder.add(ModItems.RUBY_KNOCKER, "Ruby Knockbacker");
        translationBuilder.add(ModItems.RUBY_SWORD, "Ruby Sword");
        translationBuilder.add(ModItems.RUBY_PICKAXE, "Ruby Pickaxe");
        translationBuilder.add(ModItems.RUBY_AXE, "Ruby Axe");
        translationBuilder.add(ModItems.RUBY_SHOVEL, "Ruby Shovel");
        translationBuilder.add(ModItems.RUBY_HOE, "Ruby Hoe");
        translationBuilder.add(ModItems.RUBY_HELMET, "Ruby Helmet");
        translationBuilder.add(ModItems.RUBY_CHESTPLATE, "Ruby Chestplate");
        translationBuilder.add(ModItems.RUBY_LEGGINGS, "Ruby Leggings");
        translationBuilder.add(ModItems.RUBY_BOOTS, "Ruby Boots");
        translationBuilder.add(ModItems.RUBY_BOW, "Ruby Bow");
        translationBuilder.add(ModBlocks.RUBY_BLOCK, "Ruby Block");
        translationBuilder.add(ModBlocks.RUBY_ORE, "Ruby Ore");
        translationBuilder.add(ModBlocks.DEEPSLATE_RUBY_ORE, "Deepslate Ruby Ore");
        translationBuilder.add(ModBlocks.NETHER_RUBY_ORE, "Nether Ruby Ore");
        translationBuilder.add(ModBlocks.END_RUBY_ORE, "End Ruby Ore");
        translationBuilder.add(ModBlocks.RUBY_STAIRS, "Ruby Stairs");
        translationBuilder.add(ModBlocks.RUBY_SLAB, "Ruby Slab");
        translationBuilder.add(ModBlocks.RUBY_BUTTON, "Ruby Button");
        translationBuilder.add(ModBlocks.RUBY_PRESSURE_PLATE, "Ruby Pressure Plate");
        translationBuilder.add(ModBlocks.RUBY_FENCE, "Ruby Fence");
        translationBuilder.add(ModBlocks.RUBY_FENCE_GATE, "Ruby Fence Gate");
        translationBuilder.add(ModBlocks.RUBY_WALL, "Ruby Wall");
        translationBuilder.add(ModBlocks.RUBY_DOOR, "Ruby Door");
        translationBuilder.add(ModBlocks.RUBY_TRAPDOOR, "Ruby Trapdoor");
        translationBuilder.add(ModEffects.HIGH.value(), "High");
        translationBuilder.add(ModItems.PISTOL, "Tactical Pistol");
        translationBuilder.add(ModItems.BULLET, "Bullet");
        translationBuilder.add(ModBlocks.PACKED_IRON_BLOCK, "Dense Iron Block");
        translationBuilder.add(ModItems.DEATH_AXE, "Death Axe");
        translationBuilder.add(ModItems.NUKE_BUTTON, "핵 버튼");
        translationBuilder.add(ModItems.MASTER_BEELO_STAFF, "Master Beelo Staff");
        translationBuilder.add(ModBlocks.SMOOTH_QUARTZ_RUBY_UPGRADER, "Smooth Quartz Ruby Upgrader");
        translationBuilder.add(ModBlocks.SMOOTH_QUARTZ_RUBY_DOWNGRADER, "Smooth Quartz Ruby Downgrader");
        translationBuilder.add(ModBlocks.PEDESTAL, "Pedestal");
        translationBuilder.add(ModItems.SNEK_SPAWN_EGG, "Snek Spawn Egg");
        translationBuilder.add(ModFluids.FROOTOP_BUCKET, "Bucket of Frootop");
        translationBuilder.add(ModItems.RANDOM_ARTIFACT, "Random Artifact");
        translationBuilder.add(ModBlocks.RGB_BLOCK, "RGB Block");
        translationBuilder.add(ModItems.POKE_BALL, "Poke Ball");
        translationBuilder.add(ModBlocks.ROSE, "En Rosé");
        translationBuilder.add(ModBlocks.POTTED_ROSE, "Potted Rosé");
        translationBuilder.add(ModEntities.SNEK, "Snek");
        translationBuilder.add(ModEntities.BLOB, "Blob");
        translationBuilder.add(ModEntities.PYRO, "Pyroraptor");
        translationBuilder.add(ModEntities.POKE_BALL_PROJECTILE, "Flying Poke ball");
        translationBuilder.add(ModBlocks.CS_LOG, "Cupressus Sempervirens Log");
        translationBuilder.add(ModBlocks.CS_WOOD, "Cupressus Sempervirens Lignum");
        translationBuilder.add(ModBlocks.STRIPPED_CS_LOG, "Spoliatus Cupressus Sempervirens Log");
        translationBuilder.add(ModBlocks.STRIPPED_CS_WOOD, "Spoliatus Cupressus Sempervirens Lignum");
        translationBuilder.add(ModBlocks.CS_PLANKS, "Cupressus Sempervirens Tabulae");
        translationBuilder.add(ModBlocks.CS_LEAVES, "Cupressus Sempervirens Folia");
        translationBuilder.add(ModBlocks.CS_SAPLING, "Cupressus Sempervirens Virgultum");
        translationBuilder.add(ModBlocks.CS_STAIRS, "Cupressus Sempervirens Scalae");
        translationBuilder.add(ModBlocks.CS_SLAB, "Cupressus Sempervirens Tabula");
        translationBuilder.add(ModBlocks.CS_BUTTON, "Cupressus Sempervirens Button");
        translationBuilder.add(ModBlocks.CS_PRESSURE_PLATE, "Ruby Pressure Pressura Laminam");
        translationBuilder.add(ModBlocks.CS_FENCE, "Cupressus Sempervirens Saepes");
        translationBuilder.add(ModBlocks.CS_FENCE_GATE, "Cupressus Sempervirens Saepes Porta");
        translationBuilder.add(ModBlocks.CS_DOOR, "Cupressus Sempervirens Ostium");
        translationBuilder.add(ModBlocks.CS_TRAPDOOR, "Cupressus Sempervirens Trapostium");
        translationBuilder.add(ModItems.CS_BERRIES, "Cupressus Sempervirens Bacca");
        translationBuilder.add(ModBlocks.DELIBERILIUM_ORE, "Deliberilium Ore");
        translationBuilder.add(ModBlocks.DEEPSLATE_DELIBERILIUM_ORE, "Deepslate Deliberilium Ore");
        //translationBuilder.add(ModItems.CS_SIGN, "Cupressus Sempervirens Signum");
        //translationBuilder.add(ModItems.HANGING_CS_SIGN, "Cupressus Sempervirens Tentorium Signum");
        //translationBuilder.add(ModItems.CS_BOAT, "Cupressus Sempervirens Cymba");
        //translationBuilder.add(ModItems.CS_CHEST_BOAT, "Cupressus Sempervirens Cysta Navi");
        translationBuilder.add(ModItems.WARDEN_SWORD, "Warden Sword");
        translationBuilder.add(ModItems.SNEK_HELMET, "Snek Helmet");
        translationBuilder.add(ModItems.SNEK_CHESTPLATE, "Snek Chestplate");
        translationBuilder.add(ModItems.SNEK_LEGGINGS, "Snek Leggings");
        translationBuilder.add(ModItems.SNEK_BOOTS, "Snek Boots");
        translationBuilder.add(ModItems.WARDEN_HORN, "Warden Horn");
        translationBuilder.add(ModItems.SNEK_SCALE, "Snek Scale");
        translationBuilder.add(ModItems.BLOB_SPAWN_EGG, "Blob Spawn Egg");
        translationBuilder.add(ModItems.PYRO_SPAWN_EGG, "Pyroraptor Spawn Egg");
        translationBuilder.add(ModItems.JETPACK, "Jetpack");
        translationBuilder.add(ModItems.MITHRILL_SWORD, "Mithrill Sword");
        translationBuilder.add(ModItems.MITHRILL_PICKAXE, "Mithrill Pickaxe");
        translationBuilder.add(ModItems.MITHRILL_AXE, "Mithrill Axe");
        translationBuilder.add(ModItems.MITHRILL_SHOVEL, "Mithrill Shovel");
        translationBuilder.add(ModItems.MITHRILL_HOE, "Mithrill Hoe");
        translationBuilder.add(ModItems.MITHRILL, "Mithrill");
        translationBuilder.add(ModItems.MITHRILL_HELMET, "Mithrill Helmet");
        translationBuilder.add(ModItems.MITHRILL_CHESTPLATE, "Mithrill Chestplate");
        translationBuilder.add(ModItems.MITHRILL_LEGGINGS, "Mithrill Leggings");
        translationBuilder.add(ModItems.MITHRILL_BOOTS, "Mithrill Boots");
        translationBuilder.add(ModItems.THALLEOUS_LONGSWORD, "Thalleous' Longsword");
        translationBuilder.add(ModItems.MITHRILLED_THALLEOUS_LONGSWORD, "Mithrilled Thalleous' Longsword");
        translationBuilder.add(ModItems.FISH_MEME, "F I S H");
        translationBuilder.add(ModBlocks.N_PLUSHIE, "N Plushie");
        translationBuilder.add(ModItems.ENDER_SWORD, "Ender Sword");
        translationBuilder.add(ModItems.BEELO_SWORD, "Beelo Sword");
        translationBuilder.add(ModItems.STICC, "S T I C C");
        translationBuilder.add(ModBlocks.SAFE, "Safe");
        translationBuilder.add(ModItems.SCYTHE, "Scythe");
        translationBuilder.add(ModItems.BARDICHE, "Bardiche");
        translationBuilder.add(ModItems.GRAF_EISER, "Graf Eiser");
        translationBuilder.add(ModItems.METAL_ROD, "Metal Rod");
        translationBuilder.add(ModItems.ENERGY_CRYSTAL, "Energy Crystal");
        translationBuilder.add(ModItems.DELIBERILIUM, "Deliberilium");
        translationBuilder.add(ModItems.KEY, "Key");
        translationBuilder.add(ModItems.GLOBAL_KEY, "Global Key");
        translationBuilder.add(ModBlocks.MEEPER, "Meeper");
        translationBuilder.add(ModBlocks.DELIBERATOR, "Deliberator");
        translationBuilder.add(ModItems.RULER, "Baldi's Ruler");
        translationBuilder.add(ModBlocks.COMPANION_CUBE, "Companion Cube");
        translationBuilder.add(ModBlocks.GD_CUBE, "Geometry Dash Cube");
        translationBuilder.add(ModItems.RAW_BEEF_WELLINGTON, "Raw Beef Wellington");
        translationBuilder.add(ModItems.OVERCOOKED_BEEF_WELLINGTON, "Overcooked Beef Wellington");
        translationBuilder.add(ModItems.RUBBER_DUCKY, "Rubber Ducky");
        translationBuilder.add(ModBlocks.ENDER_RELAY, "End Relay");
        translationBuilder.add(ModItems.PROTECTOR_SPAWN_EGG, "Protector Spawn Egg");
        translationBuilder.add(ModEntities.PROTECTOR, "The Protector");
        translationBuilder.add(ModItems.LAVA_CHICKEN, "λα λα λα λαβαααα κο κο κο κοταααα");
        translationBuilder.addEnchantment(ModEnchantments.BANG_EFFECT_KEY, "Bang");
        addSoundEvent(translationBuilder, ModSounds.RUBY_BLOCK_BREAK, "Ruby Block Broken");
        addSoundEvent(translationBuilder, ModSounds.RUBY_BLOCK_FALL, "Ruby Block Fallen");
        addSoundEvent(translationBuilder, ModSounds.RUBY_BLOCK_HIT, "Ruby Block Hit");
        addSoundEvent(translationBuilder, ModSounds.RUBY_BLOCK_STEP, "Ruby Block Stepped On");
        addSoundEvent(translationBuilder, ModSounds.RUBY_BLOCK_PLACE, "Ruby Block Placed");
        addSoundEvent(translationBuilder, ModSounds.HIGH, "High Person Running");
        addSoundEvent(translationBuilder, ModSounds.PEW, "Gunshot");
        addSoundEvent(translationBuilder, ModSounds.FISH, "F I S H");
        addText(translationBuilder, ModItemGroups.RUBY_ITEM_GROUP.getDisplayName(), "The Ruby Collection");
        addText(translationBuilder, ModItemGroups.BSMPS3_ARTIFACTS.getDisplayName(), "BSMPS3 Artifacts & Memes");
        addText(translationBuilder, ModItemGroups.CS_GROUP.getDisplayName(), "Cupressus Sempervirens");
        addPainting(translationBuilder, "world", "The World Of Errors", "MrBeelo");
        addVillager(translationBuilder, ModVillagers.RUBERT, "Rubert");
        addPotionTranslations(translationBuilder, ModPotions.KOKAINA, "Kokaina");
        addMusicDisc(translationBuilder, "xo_music_disc", "XO Music Disc", "XO (eden cover and remake)");
        addDamageType(translationBuilder, "emotional_damage", "Emotional Damage.");
        addGui(translationBuilder, "safe", "Safe");
        addGui(translationBuilder, "deliberator", "Deliberator");
        addBoat(translationBuilder, "cs", "Cupressus Sempervirens Cymba");
        addChestBoat(translationBuilder, "cs", "Cupressus Sempervirens Cysta Navi");
    }
}