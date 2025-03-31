package net.mrbeelo.bsmpc.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;

public class HighKnockbackItem extends Item {
    private final float knockbackMultiplier;
    public HighKnockbackItem(Settings settings, float knockbackMultiplier) {
        super(settings);
        this.knockbackMultiplier = knockbackMultiplier;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.getWorld().isClient) {
            // Apply a high knockback force
            Vec3d knockbackVector = target.getPos().subtract(attacker.getPos()).normalize().multiply(knockbackMultiplier); // Adjust strength here
            target.addVelocity(knockbackVector.x, 0.5, knockbackVector.z);
            target.velocityDirty = true; // Ensure velocity updates on the client
        }
        return super.postHit(stack, target, attacker);
    }
}
