package net.mrbeelo.bsmpc.recipe.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeBookCategories;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;
import net.mrbeelo.bsmpc.recipe.ModRecipes;

public record DeliberatorRecipe(Ingredient inputItem, ItemStack output) implements Recipe<DeliberatorRecipeInput> {

    @Override
    public boolean matches(DeliberatorRecipeInput input, World world) {
        if(world.isClient()) {
            return false;
        }

        return inputItem.test(input.getStackInSlot(0));
    }

    @Override
    public ItemStack craft(DeliberatorRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public RecipeSerializer<? extends Recipe<DeliberatorRecipeInput>> getSerializer() {
        return ModRecipes.DELIBERATOR_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<DeliberatorRecipeInput>> getType() {
        return ModRecipes.DELIBERATOR_TYPE;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        return IngredientPlacement.forSingleSlot(inputItem());
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    public static class Serializer implements RecipeSerializer<DeliberatorRecipe> {
        public static final MapCodec<DeliberatorRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC.fieldOf("ingredient").forGetter(DeliberatorRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(DeliberatorRecipe::output)
        ).apply(inst, DeliberatorRecipe::new));
        public static final PacketCodec<RegistryByteBuf, DeliberatorRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, DeliberatorRecipe::inputItem,
                        ItemStack.PACKET_CODEC, DeliberatorRecipe::output,
                        DeliberatorRecipe::new);

        @Override
        public MapCodec<DeliberatorRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, DeliberatorRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}
