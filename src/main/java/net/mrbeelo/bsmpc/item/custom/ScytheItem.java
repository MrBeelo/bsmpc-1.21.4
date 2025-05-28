package net.mrbeelo.bsmpc.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ScytheItem extends Item {
    public ScytheItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(settings.sword(toolMaterial, attackDamage, attackSpeed));
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) { // Ensure this runs only on the server side
            if (user.getItemCooldownManager().isCoolingDown(new ItemStack(this))) {
                return ActionResult.FAIL; // Prevent dashing if on cooldown
            }

            float power = 3F;
            user.setVelocity(new Vec3d(user.getRotationVec(1.0F).x * power, user.getRotationVec(1.0F).y * power, user.getRotationVec(1.0F).z * power));
            user.velocityModified = true;

            user.getItemCooldownManager().set(new ItemStack(this), 80);
        }

        return ActionResult.SUCCESS;
    }

}
