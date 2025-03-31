package net.mrbeelo.bsmpc.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.mrbeelo.bsmpc.item.ModItems;

public class EntityDeathListener implements ServerLivingEntityEvents.AfterDeath {

    @Override
    public void afterDeath(LivingEntity entity, DamageSource damageSource) {
        Entity attacker = damageSource.getAttacker();
        if (attacker instanceof PlayerEntity playerAttacker && (playerAttacker.getMainHandStack().getItem().equals(ModItems.RUBY_SWORD))) {
            double maxHealthAttr = entity.getAttributeInstance(EntityAttributes.MAX_HEALTH).getValue();
            playerAttacker.heal((float) maxHealthAttr / 4);
        }
    }
}
