package net.mrbeelo.bsmpc.util;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.mrbeelo.bsmpc.BsmpC;

public class ModTags {

    //REGISTERING

    public static class Blocks {
        public static final TagKey<Block> INCORRECT_FOR_RUBY_TOOL = TagKey.of(RegistryKeys.BLOCK, BsmpC.id("incorrect_for_ruby_tool"));
        public static final TagKey<Block> RUBY_ORES = TagKey.of(RegistryKeys.BLOCK, BsmpC.id("ruby_ores"));
        public static final TagKey<Block> RUBY_BLOCKS = TagKey.of(RegistryKeys.BLOCK, BsmpC.id("ruby_blocks"));
        public static final TagKey<Block> CS_BLOCKS = TagKey.of(RegistryKeys.BLOCK, BsmpC.id("cs_blocks"));
        public static final TagKey<Block> INCORRECT_FOR_WARDEN_HORN_TOOL = TagKey.of(RegistryKeys.BLOCK, BsmpC.id("incorrect_for_warden_horn_tool"));
        public static final TagKey<Block> INCORRECT_FOR_MITHRILL_TOOL = TagKey.of(RegistryKeys.BLOCK, BsmpC.id("incorrect_for_mithrill_tool"));

        public static final TagKey<Block> DELIBERILIUM_ORES = TagKey.of(RegistryKeys.BLOCK, BsmpC.id("deliberilium_ores"));
    }

    public static class Items {
        public static final TagKey<Item> RUBY_ITEMS = TagKey.of(RegistryKeys.ITEM, BsmpC.id("ruby_items"));
        public static final TagKey<Item> RUBY_TOOLS = TagKey.of(RegistryKeys.ITEM, BsmpC.id("ruby_tools"));
        public static final TagKey<Item> RUBY_ARMOR = TagKey.of(RegistryKeys.ITEM, BsmpC.id("ruby_armor"));
        public static final TagKey<Item> RUBY_VARIANTS = TagKey.of(RegistryKeys.ITEM, BsmpC.id("ruby_variants"));

        public static final TagKey<Item> RUBY_TOOL_MATERIAL = TagKey.of(RegistryKeys.ITEM, BsmpC.id("ruby_tool_material"));
        public static final TagKey<Item> MITHRILL_TOOL_MATERIAL = TagKey.of(RegistryKeys.ITEM, BsmpC.id("mithrill_tool_material"));
        public static final TagKey<Item> WARDEN_HORN_TOOL_MATERIAL = TagKey.of(RegistryKeys.ITEM, BsmpC.id("warden_horn_tool_material"));

        public static final TagKey<Item> REPAIRS_RUBY_ARMOR = TagKey.of(RegistryKeys.ITEM, BsmpC.id("repairs_ruby_armor"));
        public static final TagKey<Item> REPAIRS_MITHRILL_ARMOR = TagKey.of(RegistryKeys.ITEM, BsmpC.id("repairs_mithrill_armor"));
        public static final TagKey<Item> REPAIRS_SNEK_ARMOR = TagKey.of(RegistryKeys.ITEM, BsmpC.id("repairs_snek_armor"));
    }

    public static class EntityTypes {
        public static final TagKey<EntityType<?>> LAZER = TagKey.of(RegistryKeys.ENTITY_TYPE, BsmpC.id("lazer"));
    }
}
