package net.mrbeelo.bsmpc.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvents;

public class DeathAxeItem extends AxeItem {
    public DeathAxeItem(int attackDamage, float attackSpeed, Settings settings) {
        super(ToolMaterial.IRON, attackDamage, attackSpeed, settings);
    }

    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        attacker.getWorld().playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(), SoundEvents.BLOCK_ANVIL_LAND, attacker.getSoundCategory(), 4.0F, 1.0F);
        stack.decrement(1);
    }
}
