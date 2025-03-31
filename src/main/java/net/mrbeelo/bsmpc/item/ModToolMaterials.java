package net.mrbeelo.bsmpc.item;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.mrbeelo.bsmpc.BsmpC;
import net.mrbeelo.bsmpc.util.ModTags;

public record ModToolMaterials() {
    public static final ToolMaterial MITHRILL = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_MITHRILL_TOOL, 6000, 17.0F, 10.0F, 15, ModTags.Items.MITHRILL_TOOL_MATERIAL);
    public static final ToolMaterial RUBY = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_RUBY_TOOL, 3100, 14.0F, 6.0F,  15, ModTags.Items.RUBY_TOOL_MATERIAL);
    public static final ToolMaterial WARDEN_HORN = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_WARDEN_HORN_TOOL, 4000, 12.0F, 7.0F,  15, ModTags.Items.WARDEN_HORN_TOOL_MATERIAL);

    public static void registerModToolMaterials() {
        BsmpC.LOGGER.info("Registering Mod Tool Materials for " + BsmpC.MOD_ID);
    }
}
