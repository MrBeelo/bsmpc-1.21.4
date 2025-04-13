package net.mrbeelo.bsmpc.entity.client.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.mrbeelo.bsmpc.entity.custom.ProtectorBossEntity;
import oshi.util.tuples.Pair;

import java.util.*;

public class ProtectorAttackGoal extends MeleeAttackGoal {
    private final ProtectorBossEntity entity;
    private int currentAttack = 0;
    private int ticksUntilNextAttack = 0;
    private boolean shouldCountTillNextAttack = false;
    public static final Map<Integer, Pair<Integer, Integer>> attackData = Map.of(
            0, new Pair<>(15, 15),
            1, new Pair<>(40, 15),
            2, new Pair<>(50, 40),
            3, new Pair<>(30, 50)
    );

    public ProtectorAttackGoal(PathAwareEntity mob, double speed, boolean pauseWhenMobIdle) {
        super(mob, speed, pauseWhenMobIdle);
        this.entity = (ProtectorBossEntity) mob;
    }


    @Override
    public boolean canStart() {
        LivingEntity target = this.mob.getTarget();
        return target != null && target.isAlive();
    }

    @Override
    public void start() {
        setAttackCooldownForStartOfAnimation();
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
        entity.stopAllAnims();
    }

    @Override
    public void tick() {
        super.tick();

        if (entity.isDoingGenericAttack() || entity.isDoingAttack1() || entity.isDoingAttack2() || entity.isDoingAttack3()) {
            mob.getNavigation().stop();
        }

        if(shouldCountTillNextAttack)
        {
            this.ticksUntilNextAttack = Math.max(ticksUntilNextAttack - 1, 0);
        }

        if(currentAttack == 2 && ticksUntilNextAttack == getTicksInAnimationBeforeAttack(currentAttack) - 35)
        {
            LivingEntity lEntity = entity.getTarget();
            assert lEntity != null;
            Vec3d launchDirectionToPlayer = lEntity.getPos().subtract(entity.getPos()).normalize();
            entity.setVelocity(launchDirectionToPlayer.multiply(entity.distanceTo(lEntity) / 6).add(0, 0.8, 0));  // Adjust the multiplier as needed
            entity.velocityModified = true;
        }
    }

    @Override
    protected void attack(LivingEntity target) {
        if (isEnemySpotted(target)) {
            shouldCountTillNextAttack = true;


            if (isTimeToStartAttackAnimation()) {
                entity.stopAllAnims();
                switch (currentAttack)
                {
                    case 0:
                        if(isEnemyWithinDistance(target, 6))
                        { entity.setDoingGenericAttack(true); }
                        break;

                    case 1:
                        if(isEnemyWithinDistance(target, 9))
                        { entity.setDoingAttack1(true); }
                        break;

                    case 2:
                        if(isEnemyWithinDistance(target, 14))
                        { entity.setDoingAttack2(true); }
                        break;

                    case 3:
                        if(isEnemyWithinDistance(target, 9))
                        { entity.setDoingAttack3(true); }
                        break;
                }
            }

            if (isTimeToAttack() && target.getWorld() instanceof ServerWorld serverWorld) {
                mob.getLookControl().lookAt(target.getX(), target.getY(), target.getZ());
                performAttack(serverWorld, target);
            }
        } else {
            shouldCountTillNextAttack = false;
            entity.stopAllAnims();
        }
    }

    protected void performAttack(ServerWorld world, LivingEntity lEntity)
    {
        setAttackCooldownForEndOfAnimation();
        mob.swingHand(Hand.MAIN_HAND);
        mob.tryAttack(world, lEntity);

        switch (currentAttack)
        {
            case 0, 2:
                break;

            case 1:
                lEntity.setVelocity(new Vec3d(0, 1.5f, 0));
                lEntity.velocityModified = true;
                break;

            case 3:
                Vec3d launchDirection = lEntity.getPos().subtract(entity.getPos()).normalize();
                lEntity.setVelocity(launchDirection.multiply(2.5f).add(0, 0.8, 0));
                lEntity.velocityModified = true;
                lEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 140, 0, true, false, false));
                lEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 140, 3, true, false, false));
                break;
        }

        currentAttack = getRandomAttack();
        setAttackCooldownForStartOfAnimation();
    }

    protected void setAttackCooldownForEndOfAnimation()
    { this.ticksUntilNextAttack += this.getTickCount(getTicksInAnimationAfterAttack(currentAttack)); }

    protected void setAttackCooldownForStartOfAnimation()
    { this.ticksUntilNextAttack += this.getTickCount(getTicksInAnimationBeforeAttack(currentAttack)); }

    protected boolean isTimeToStartAttackAnimation()
    { return ticksUntilNextAttack <= getTicksInAnimationBeforeAttack(currentAttack); }

    protected boolean isTimeToAttack()
    { return ticksUntilNextAttack <= 0; }

    private boolean isEnemySpotted(LivingEntity lEntity)
    {
        return entity.canSee(lEntity);
    }

    private boolean isEnemyWithinDistance(LivingEntity lEntity, float distance)
    {
        return entity.distanceTo(lEntity) <= distance;
    }

    public static int getTicksInAnimationBeforeAttack(int currentAttack)
    {
        return attackData.get(currentAttack).getA();
    }

    public static int getTicksInAnimationAfterAttack(int currentAttack)
    {
        return attackData.get(currentAttack).getB();
    }

    public static int getRandomAttack() {
        Random random = new Random();
        int weightedRandom = random.nextInt(100);  // Generate a random number between 0 and 99

        // Higher chance for attack 0 (e.g., 60% chance), then distribute remaining chances
        if (weightedRandom < 60) {
            return 0;  // 60% chance of returning 0
        } else {
            return random.nextInt(attackData.size() - 1) + 1;  // 40% chance of 1, 2, or 3
        }
    }
}

/*TODO KNOWN ISSUES

1. WHEN REJOINING GAME, PRROTECTOR IS IN KNEELING POSE
2. WHEN JOINING GAME, BOSSBAR GOES AWAY

 */
