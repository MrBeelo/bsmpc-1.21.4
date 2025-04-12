package net.mrbeelo.bsmpc.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.mrbeelo.bsmpc.entity.client.ai.ProtectorAttackGoal;

public class ProtectorBossEntity extends HostileEntity {
    public ProtectorBossEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.setAiDisabled(true);
    }

    private final ServerBossBar bossBar = (ServerBossBar)new ServerBossBar(this.getDisplayName(), BossBar.Color.GREEN, BossBar.Style.PROGRESS).setDarkenSky(false);
    public int currentAttackIndex = 0;

    private static final TrackedData<Boolean> DOING_GENERIC_ATTACK = DataTracker.registerData(ProtectorBossEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> DOING_ATTACK_1 = DataTracker.registerData(ProtectorBossEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> DOING_ATTACK_2 = DataTracker.registerData(ProtectorBossEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> DOING_ATTACK_3 = DataTracker.registerData(ProtectorBossEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> AWAKENING = DataTracker.registerData(ProtectorBossEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> STATIC = DataTracker.registerData(ProtectorBossEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState genericAttackAnimationState = new AnimationState();
    public int genericAttackAnimationTimeout = 0;

    public final AnimationState attack1AnimationState = new AnimationState();
    public int attack1AnimationTimeout = 0;

    public final AnimationState attack2AnimationState = new AnimationState();
    private int attack2AnimationTimeout = 0;

    public final AnimationState attack3AnimationState = new AnimationState();
    private int attack3AnimationTimeout = 0;

    public final AnimationState awakeningAnimationState = new AnimationState();
    private int awakeningAnimationTimeout = 0;
    private int awakeningTicksLeft = 170;

    public final AnimationState staticAnimationState = new AnimationState();
    private int staticAnimationTimeout = 0;

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new ProtectorAttackGoal(this, 1.0, true));
        this.goalSelector.add(2, new WanderAroundGoal(this, 1.0));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new RevengeGoal(this));
        super.initGoals();
    }

    private void setupAnimationStates() {
        // Static Animation
        if (isKneeling() && this.staticAnimationTimeout <= 0) {
            this.staticAnimationTimeout = 20;
            this.staticAnimationState.start(this.age);
        } else {
            --this.staticAnimationTimeout;
        }

        if (!isKneeling()) {
            this.staticAnimationState.stop();
        }

        // Awakening Animation
        if (isAwakening() && this.awakeningAnimationTimeout <= 0) {
            this.awakeningAnimationTimeout = 170;
            this.awakeningAnimationState.start(this.age);
        } else {
            --this.awakeningAnimationTimeout;
        }

        if (!isAwakening()) {
            this.awakeningAnimationState.stop();
        }

        // Idle Animation
        if (!this.isAttacking() && !this.dataTracker.get(AWAKENING) && !this.dataTracker.get(STATIC) && this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 20;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
        if (this.isAttacking() || this.dataTracker.get(AWAKENING) || this.dataTracker.get(STATIC)) {
            this.idleAnimationState.stop();
        }

        // Generic Attack Animation
        if (isDoingGenericAttack() && this.genericAttackAnimationTimeout <= 0) {
            this.genericAttackAnimationTimeout = ProtectorAttackGoal.getTicksInAnimationBeforeAttack(0) + ProtectorAttackGoal.getTicksInAnimationAfterAttack(0);
            this.genericAttackAnimationState.start(this.age);
        } else {
            --this.genericAttackAnimationTimeout;
        }

        if (!isDoingGenericAttack()) {
            this.genericAttackAnimationState.stop();
        }

        // Attack 1
        if (isDoingAttack1() && this.attack1AnimationTimeout <= 0) {
            this.attack1AnimationTimeout = ProtectorAttackGoal.getTicksInAnimationBeforeAttack(0) + ProtectorAttackGoal.getTicksInAnimationAfterAttack(0);
            this.attack1AnimationState.start(this.age);
        } else {
            --this.attack1AnimationTimeout;
        }

        if (!isDoingAttack1()) {
            this.attack1AnimationState.stop();
        }

        // Attack 2
        if (this.dataTracker.get(DOING_ATTACK_2)) {
            if (this.attack2AnimationTimeout <= 0) {
                this.attack2AnimationTimeout = 40;
                this.attack2AnimationState.start(this.age);
            } else {
                --this.attack2AnimationTimeout;
            }
        } else {
            this.attack2AnimationState.stop();
        }

        // Attack 3
        if (this.dataTracker.get(DOING_ATTACK_3)) {
            if (this.attack3AnimationTimeout <= 0) {
                this.attack3AnimationTimeout = 40;
                this.attack3AnimationState.start(this.age);
            } else {
                --this.attack3AnimationTimeout;
            }
        } else {
            this.attack3AnimationState.stop();
        }
    }


    @Override
    public boolean isAttacking() {
        return this.dataTracker.get(DOING_GENERIC_ATTACK)
                || this.dataTracker.get(DOING_ATTACK_1)
                || this.dataTracker.get(DOING_ATTACK_2)
                || this.dataTracker.get(DOING_ATTACK_3);
    }

    @Override
    public void tick() {
        super.tick();

        if(this.getWorld().isClient()) {
            this.setupAnimationStates();
        }

        if (isKneeling() && !isAwakening()) {
            for (PlayerEntity player : this.getWorld().getPlayers()) {
                if(player instanceof ServerPlayerEntity serverPlayer) {
                    if (!serverPlayer.isCreative() && !serverPlayer.isSpectator() && serverPlayer.squaredDistanceTo(this) < 6 * 6) { // 10 blocks range
                        startAwakening();
                        break;
                    }
                }
            }
        }

        if (isAwakening()) {
            if (awakeningTicksLeft > 0) {
                awakeningTicksLeft--;
            } else {
                setAwakening(false);
                this.setAiDisabled(false);
                this.initGoals();
                for (PlayerEntity player : this.getWorld().getPlayers()) {
                    if(player instanceof ServerPlayerEntity serverPlayer && serverPlayer.squaredDistanceTo(this) < 20 * 20) {
                        this.onStartedTrackingBy(serverPlayer);
                    }
                }
            }
        }
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        if(!isKneeling() && !isAwakening()) {
            this.bossBar.addPlayer(player);
        }
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
    }

    public static DefaultAttributeContainer.Builder createProtectorBossAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 350)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.ATTACK_DAMAGE, 20)
                .add(EntityAttributes.ARMOR, 20)
                .add(EntityAttributes.KNOCKBACK_RESISTANCE, 0.5f);
    }

    @Override
    public void mobTick(ServerWorld serverWorld) {
        if (this.age % 20 == 0) {
            this.heal(1.0F);
        }

        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
    }

    public void setDoingGenericAttack(boolean attacking) {
        this.dataTracker.set(DOING_GENERIC_ATTACK, attacking);
    }

    public boolean isDoingGenericAttack() {
        return this.dataTracker.get(DOING_GENERIC_ATTACK);
    }

    public void setAwakening(boolean awakening) {
        this.dataTracker.set(AWAKENING, awakening);
    }

    public boolean isAwakening() {
        return this.dataTracker.get(AWAKENING);
    }

    public void setKneeling(boolean kneeling) {
        this.dataTracker.set(STATIC, kneeling);
    }

    public boolean isKneeling() {
        return this.dataTracker.get(STATIC);
    }

    public void setDoingAttack1(boolean attacking) {
        this.dataTracker.set(DOING_ATTACK_1, attacking);
    }

    public boolean isDoingAttack1() {
        return this.dataTracker.get(DOING_ATTACK_1);
    }

    @Override
    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        if (source.getTypeRegistryEntry().isIn(DamageTypeTags.IS_PROJECTILE))
        {
            return false;
        }

        return super.damage(world, source, amount);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(DOING_GENERIC_ATTACK, false);
        builder.add(DOING_ATTACK_1, false);
        builder.add(DOING_ATTACK_2, false);
        builder.add(DOING_ATTACK_3, false);
        builder.add(AWAKENING, false);
        builder.add(STATIC, true);
    }

    private void startAwakening() {
        setKneeling(false);
        setAwakening(true);
        this.setAiDisabled(true);
        awakeningTicksLeft = 170; // Or however long your animation lasts
    }

    public void stopAllAnims()
    {
        setDoingGenericAttack(false);
        setDoingAttack1(false);
        setAwakening(false);
        setKneeling(false);

        genericAttackAnimationTimeout = 0;
        attack1AnimationTimeout = 0;
        attack2AnimationTimeout = 0;
        attack3AnimationTimeout = 0;
        awakeningAnimationTimeout = 0;
        staticAnimationTimeout = 0;
    }
}
