package net.mrbeelo.bsmpc.potion;

import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.potion.Potions;
import net.mrbeelo.bsmpc.BsmpC;
import net.mrbeelo.bsmpc.item.ModItems;

import static net.mrbeelo.bsmpc.potion.ModPotions.KOKAINA;

public class ModPotionRecipes {
    public static void registerModPotionRecipes() {
        BsmpC.LOGGER.info("Registering Mod Potion Recipes for " + BsmpC.MOD_ID);

        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(Potions.AWKWARD, ModItems.KOKAINA, KOKAINA);
        });
    }

}
