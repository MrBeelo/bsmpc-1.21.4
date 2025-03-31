package net.mrbeelo.bsmpc.recipe;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mrbeelo.bsmpc.BsmpC;
import net.mrbeelo.bsmpc.recipe.custom.DeliberatorRecipe;

public class ModRecipes {
    public static final RecipeSerializer<DeliberatorRecipe> DELIBERATOR_SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, BsmpC.id("deliberating"), new DeliberatorRecipe.Serializer());
    public static final RecipeType<DeliberatorRecipe> DELIBERATOR_TYPE = Registry.register(Registries.RECIPE_TYPE, Identifier.of(BsmpC.MOD_ID, "deliberating"), new RecipeType<>() {
        @Override
        public String toString() {
            return "deliberating";
        }
    });


    public static void registerModRecipes() {
        BsmpC.LOGGER.info("Registering Mod Recipes for " + BsmpC.MOD_ID);
    }
}
