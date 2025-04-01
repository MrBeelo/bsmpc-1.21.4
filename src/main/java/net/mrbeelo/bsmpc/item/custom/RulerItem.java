package net.mrbeelo.bsmpc.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.mrbeelo.bsmpc.sound.ModSounds;

import java.util.List;

public class RulerItem extends Item {
    public RulerItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);

        if(entity instanceof PlayerEntity player && player.getWeaponStack() == stack && world instanceof ServerWorld serverWorld)
        {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 1, 10, false, false, false));
            player.setSprinting(false);

            List<Entity> nearbyEntities = world.getOtherEntities(player, player.getBoundingBox().expand(1.5));
            for(Entity entity1 : nearbyEntities)
            {
                entity1.kill(serverWorld);
                world.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.BZZZ, player.getSoundCategory(), 4.0f, 1.0f);
            }
        }
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) { // Ensure this runs only on the server side
            if (user.getItemCooldownManager().isCoolingDown(new ItemStack(this))) {
                return ActionResult.FAIL; // Prevent dashing if on cooldown
            }

            user.getItemCooldownManager().set(new ItemStack(this), 40);
            world.playSound(null, user.getX(), user.getY(), user.getZ(), ModSounds.SLAP, user.getSoundCategory(), 4.0f, 1.0f);

            float power = 3F;
            user.setVelocity(new Vec3d(user.getRotationVec(1.0F).x * power, 0, user.getRotationVec(1.0F).z * power));
            user.velocityModified = true;
        }

        return ActionResult.SUCCESS;
    }
}
