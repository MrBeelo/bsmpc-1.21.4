package net.mrbeelo.bsmpc.entity.client.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.mrbeelo.bsmpc.entity.ModDamageTypes;
import net.mrbeelo.bsmpc.entity.custom.ProtectorBossEntity;
import net.mrbeelo.bsmpc.entity.custom.PyroEntity;
import net.mrbeelo.bsmpc.entity.custom.SnekEntity;
import net.mrbeelo.bsmpc.sound.ModSounds;

public class ProtectorGenericAttackGoal extends MeleeAttackGoal {
    private final ProtectorBossEntity entity;
    private int attackDelay = 15;
    private int ticksUntilNextAttack = 15;
    private boolean shouldCountTillNextAttack = false;

    public ProtectorGenericAttackGoal(ProtectorBossEntity entity, double speed, boolean pauseWhenIdle) {
        super(entity, speed, pauseWhenIdle);
        this.entity = entity;
    }

    @Override
    public void start() {
        super.start();
        attackDelay = 15;
        ticksUntilNextAttack = 15;
    }

    @Override
    public void stop() {
        entity.setDoingGenericAttack(false);
        super.stop();
    }

    @Override
    public void tick() {
        super.tick();
        if(shouldCountTillNextAttack)
        {
            this.ticksUntilNextAttack = Math.max(ticksUntilNextAttack - 1, 0);
        }
    }

    @Override
    protected void attack(LivingEntity target) {
        if (isEnemyWithinDistance(target)) {
            shouldCountTillNextAttack = true;


            if (isTimeToStartAttackAnimation()) {
                entity.setDoingGenericAttack(true);
            }

            if (isTimeToAttack() && target.getWorld() instanceof ServerWorld serverWorld) {
                mob.getLookControl().lookAt(target.getX(), target.getY(), target.getZ());
                performAttack(serverWorld, target);
            }
        } else {
            resetAttackCooldown();
            shouldCountTillNextAttack = false;
            entity.setDoingGenericAttack(false);
            entity.genericAttackAnimationTimeout = 0;
        }
    }

    protected void performAttack(ServerWorld world, LivingEntity lEntity)
    {
        resetAttackCooldown();
        mob.swingHand(Hand.MAIN_HAND);
        mob.tryAttack(world, lEntity);
    }

    protected void resetAttackCooldown()
    { this.ticksUntilNextAttack = this.getTickCount(attackDelay * 2); }

    protected boolean isTimeToStartAttackAnimation()
    { return ticksUntilNextAttack <= attackDelay; }

    protected boolean isTimeToAttack()
    { return ticksUntilNextAttack <= 0; }

    private boolean isEnemyWithinDistance(LivingEntity lEntity)
    { return entity.distanceTo(lEntity) <= 5f; }
}
