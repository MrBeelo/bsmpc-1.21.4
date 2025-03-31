package net.mrbeelo.bsmpc.item.custom;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.mrbeelo.bsmpc.block.entity.custom.SafeBlockEntity;

public class GlobalKeyItem extends Item {
    public GlobalKeyItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ItemStack stack = context.getStack();
        PlayerEntity player = context.getPlayer();
        BlockPos blockPos = context.getBlockPos();
        BlockEntity blockEntity = context.getWorld().getBlockEntity(blockPos);

        if (!context.getWorld().isClient() && player != null && player.isSneaking() && blockEntity instanceof SafeBlockEntity safeBlockEntity) {
            if(safeBlockEntity.getOccupation()) {
                safeBlockEntity.setOccupation(false);
                player.sendMessage(Text.of("Unoccupied Safe!"), true);
            } else {
                safeBlockEntity.setOccupation(true);
                player.sendMessage(Text.of("Occupied Safe!"), true);
            }
        }

        return super.useOnBlock(context);
    }
}
