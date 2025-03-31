package net.mrbeelo.bsmpc.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.mrbeelo.bsmpc.sound.ModSounds;

public class FishItem extends Item {
    public FishItem(Settings settings) {super(settings);}

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        player.playSound(ModSounds.FISH, 1.0F, 1.0F);
        return ActionResult.SUCCESS;
    }
}
