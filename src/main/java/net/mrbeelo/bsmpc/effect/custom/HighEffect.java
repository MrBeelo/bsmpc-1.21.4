package net.mrbeelo.bsmpc.effect.custom;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerRotationS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.mrbeelo.bsmpc.BsmpC;
import net.mrbeelo.bsmpc.effect.ModEffects;
import net.mrbeelo.bsmpc.sound.ModSounds;
import java.util.HashSet;
import java.util.Set;

public class HighEffect extends StatusEffect {
    public HighEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    public final static Set<PlayerEntity> playersWithHighSound = new HashSet<>();

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity) {
            World world = entity.getWorld();
            if (!playersWithHighSound.contains(entity) && world instanceof ServerWorld) {
                playersWithHighSound.add((PlayerEntity) entity);
                if (entity instanceof ServerPlayerEntity serverPlayerEntity) {
                    serverPlayerEntity.networkHandler.sendPacket(new net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket(RegistryEntry.of(ModSounds.HIGH), SoundCategory.PLAYERS, serverPlayerEntity.getX(), serverPlayerEntity.getY(), serverPlayerEntity.getZ(), 50F, 1F, serverPlayerEntity.getWorld().getRandom().nextLong()));
                }
            }
        }
        super.onApplied(entity, amplifier);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        super.applyUpdateEffect(world, entity, amplifier);
        if (entity instanceof PlayerEntity playerEntity) {
            float yaw = playerEntity.getYaw();
            float pitch = playerEntity.getPitch();

            yaw += ((float) Math.random() - 0.5f) * 10;
            pitch += ((float) Math.random() - 0.5f) * 10;

            playerEntity.setYaw(yaw);
            playerEntity.setPitch(pitch);

            if(playerEntity instanceof ServerPlayerEntity serverPlayer) {
                serverPlayer.networkHandler.sendPacket(new PlayerRotationS2CPacket(yaw, pitch));
            }

            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 80, 0, false, false, false));
        }
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
