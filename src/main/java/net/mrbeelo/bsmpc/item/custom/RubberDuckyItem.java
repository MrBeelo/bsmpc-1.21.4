package net.mrbeelo.bsmpc.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.mrbeelo.bsmpc.sound.ModSounds;

public class RubberDuckyItem extends Item {
    public RubberDuckyItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        user.playSound(ModSounds.QUACK, 1.0F, 1.0F);
        return ActionResult.SUCCESS;
    }
}
