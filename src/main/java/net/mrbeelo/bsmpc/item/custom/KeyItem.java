package net.mrbeelo.bsmpc.item.custom;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.mrbeelo.bsmpc.block.custom.SafeBlock;
import net.mrbeelo.bsmpc.block.entity.custom.SafeBlockEntity;
import net.mrbeelo.bsmpc.components.ModDataComponentTypes;

public class KeyItem extends Item {
    public KeyItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ItemStack stack = context.getStack();
        PlayerEntity player = context.getPlayer();
        BlockPos blockPos = context.getBlockPos();
        BlockEntity blockEntity = context.getWorld().getBlockEntity(blockPos);
        BlockPos assignedPos = stack.get(ModDataComponentTypes.KEY_ASSIGNED_POS);
        if (!context.getWorld().isClient() && player != null && player.isSneaking() && blockEntity instanceof SafeBlockEntity safeBlockEntity) {
            if (safeBlockEntity.getOccupation() && blockPos.getX() == assignedPos.getX() && blockPos.getY() == assignedPos.getY() && blockPos.getZ() == assignedPos.getZ()) {
                player.sendMessage(Text.of("Unassigned key to safe at " + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ()), true);
                safeBlockEntity.setOccupation(false);
                stack.set(ModDataComponentTypes.KEY_ASSIGNED_POS, new BlockPos(0, 0, 0));
            } else if (!safeBlockEntity.getOccupation() && (blockPos.getX() != assignedPos.getX() || blockPos.getY() != assignedPos.getY() || blockPos.getZ() != assignedPos.getZ())) {
                if(context.getWorld().getBlockEntity(assignedPos) instanceof SafeBlockEntity safeBlockEntity1)
                {
                    if(safeBlockEntity1.getOccupation())
                    {
                        safeBlockEntity1.setOccupation(false);
                    }
                }
                player.sendMessage(Text.of("Assigned key to safe at " + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ()), true);
                safeBlockEntity.setOccupation(true);
                stack.set(ModDataComponentTypes.KEY_ASSIGNED_POS, blockPos);
            } else if(safeBlockEntity.getOccupation() && (blockPos.getX() != assignedPos.getX() || blockPos.getY() != assignedPos.getY() || blockPos.getZ() != assignedPos.getZ()))
            {
                player.sendMessage(Text.of("Safe already occupied!"), true);
            }
        }
        return super.useOnBlock(context);
    }
}
