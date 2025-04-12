package net.mrbeelo.bsmpc.entity.client.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.mrbeelo.bsmpc.entity.custom.ProtectorBossEntity;
import oshi.util.tuples.Pair;
import oshi.util.tuples.Triplet;

import java.util.*;

public class ProtectorAttackGoal extends MeleeAttackGoal {
    private final ProtectorBossEntity entity;
    private int currentAttack = 0;
    private int ticksUntilNextAttack = 0;
    private boolean shouldCountTillNextAttack = false;
    public static final Map<Integer, Pair<Integer, Integer>> attackData = Map.of(
            0, new Pair<>(15, 15),
            1, new Pair<>(40, 15)
    );

    public ProtectorAttackGoal(PathAwareEntity mob, double speed, boolean pauseWhenMobIdle) {
        super(mob, speed, pauseWhenMobIdle);
        this.entity = (ProtectorBossEntity) mob;
    }


    @Override
    public boolean canStart() {
        return true;
    }

    @Override
    public void start() {
        setAttackCooldownForStartOfAnimation();
        super.start();
    }

    @Override
    public void stop() {
        entity.stopAllAnims();
        super.stop();
    }

    @Override
    public void tick() {
        super.tick();

        if (entity.isDoingGenericAttack() || entity.isDoingAttack1()) {
            mob.getNavigation().stop();
        }

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
                switch (currentAttack)
                {
                    case 0:
                        entity.stopAllAnims();
                        entity.setDoingGenericAttack(true);
                        break;

                    case 1:
                        entity.stopAllAnims();
                        entity.setDoingAttack1(true);
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
        setAttackCooldownForStartOfAnimation();
        mob.swingHand(Hand.MAIN_HAND);
        mob.tryAttack(world, lEntity);

        switch (currentAttack)
        {
            case 0:
                break;

            case 1:
                lEntity.setVelocity(new Vec3d(0, 1.5f, 0));
                lEntity.velocityModified = true;
                break;
        }

        currentAttack = getRandomAttack();
        setAttackCooldownForEndOfAnimation();
    }

    protected void setAttackCooldownForEndOfAnimation()
    { this.ticksUntilNextAttack += this.getTickCount(getTicksInAnimationAfterAttack(currentAttack)); }

    protected void setAttackCooldownForStartOfAnimation()
    { this.ticksUntilNextAttack += this.getTickCount(getTicksInAnimationBeforeAttack(currentAttack)); }

    protected boolean isTimeToStartAttackAnimation()
    { return ticksUntilNextAttack <= getTicksInAnimationBeforeAttack(currentAttack); }

    protected boolean isTimeToAttack()
    { return ticksUntilNextAttack <= 0; }

    private boolean isEnemyWithinDistance(LivingEntity lEntity)
    {
        return switch (currentAttack) {
            case 0 -> entity.distanceTo(lEntity) <= 6f;
            case 1 -> entity.distanceTo(lEntity) <= 10f;
            default -> true;
        };

    }

    public static int getTicksInAnimationBeforeAttack(int currentAttack)
    {
        return attackData.get(currentAttack).getA();
    }

    public static int getTicksInAnimationAfterAttack(int currentAttack)
    {
        return attackData.get(currentAttack).getB();
    }

    public static int getRandomAttack()
    {
        Random random = new Random();
        return random.nextInt(attackData.size());
    }
}
