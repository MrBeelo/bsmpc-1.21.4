package net.mrbeelo.bsmpc.block.entity.custom;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.ServerRecipeManager;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mrbeelo.bsmpc.block.entity.ImplementedInventory;
import net.mrbeelo.bsmpc.block.entity.ModBlockEntities;
import net.mrbeelo.bsmpc.item.ModItems;
import net.mrbeelo.bsmpc.recipe.ModRecipes;
import net.mrbeelo.bsmpc.recipe.custom.DeliberatorRecipe;
import net.mrbeelo.bsmpc.recipe.custom.DeliberatorRecipeInput;
import net.mrbeelo.bsmpc.screen.custom.handler.DeliberatorScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

public class DeliberatorBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int DELIBERILIUM_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;

    public DeliberatorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DELIBERATOR_BE, pos, state);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return this.pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("gui.bsmpc.deliberator");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new DeliberatorScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        Inventories.readNbt(nbt, inventory, registryLookup);
        super.readNbt(nbt, registryLookup);
    }

    public void tick(World world, BlockPos pos, BlockState state)
    {
        if(hasRecipe() && canInsertIntoOutputSlot()) {
            craftItem();
            markDirty(world, pos, state);
        }
    }

    private void craftItem() {
        Optional<RecipeEntry<DeliberatorRecipe>> recipe = getCurrentRecipe();

        this.removeStack(INPUT_SLOT, 1);
        this.removeStack(DELIBERILIUM_SLOT, 1);
        this.setStack(OUTPUT_SLOT, new ItemStack(recipe.get().value().output().getItem(),
                this.getStack(OUTPUT_SLOT).getCount() + recipe.get().value().output().getCount()));
    }

    private boolean canInsertIntoOutputSlot() {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }

    private boolean hasRecipe() {
        Optional<RecipeEntry<DeliberatorRecipe>> recipe = getCurrentRecipe();

        if(recipe.isEmpty())
        {
            return false;
        }

        ItemStack output = recipe.get().value().output();

        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output) && this.getStack(DELIBERILIUM_SLOT).getItem() == ModItems.DELIBERILIUM;
    }

    private Optional<RecipeEntry<DeliberatorRecipe>> getCurrentRecipe() {
        RecipeManager recipeManager = Objects.requireNonNull(this.getWorld()).getRecipeManager();
        if(recipeManager instanceof ServerRecipeManager serverRecipeManager)
        {
            return serverRecipeManager.getFirstMatch(ModRecipes.DELIBERATOR_TYPE, new DeliberatorRecipeInput(inventory.get(INPUT_SLOT)), this.getWorld());
        }

        return Optional.empty();
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = this.getStack(OUTPUT_SLOT).isEmpty() ? 64 : this.getStack(OUTPUT_SLOT).getMaxCount();
        int currentCount = this.getStack(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
    }
}

