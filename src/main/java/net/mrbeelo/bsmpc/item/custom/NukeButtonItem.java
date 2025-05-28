package net.mrbeelo.bsmpc.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mrbeelo.bsmpc.sound.ModSounds;

import java.util.List;

import static net.mrbeelo.bsmpc.BsmpC.getBlockInFrontXZ;


public class NukeButtonItem extends Item {
    public NukeButtonItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) {
            return ActionResult.PASS;
        }

        user.getWorld().playSound(null, user.getX(), user.getY(), user.getZ(), ModSounds.BEEP, user.getSoundCategory(), 1.0F, 1.0F);


        BlockPos frontOfPlayer = getBlockInFrontXZ(user, 15).up(50);

        Entity nuke = new TntEntity(EntityType.TNT, world);
        nuke.setPosition(frontOfPlayer.toCenterPos());
        world.spawnEntity(nuke);

        return ActionResult.SUCCESS;
    }
}
