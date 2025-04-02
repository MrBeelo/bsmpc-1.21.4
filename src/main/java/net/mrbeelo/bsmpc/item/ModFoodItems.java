package net.mrbeelo.bsmpc.item;

import net.minecraft.component.type.Consumable;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.mrbeelo.bsmpc.effect.ModEffects;

public class ModFoodItems {
    public static final FoodComponent KOKAINA_COMPONENT = new FoodComponent.Builder()
            .alwaysEdible()
            .nutrition(6)
            .saturationModifier(0.4F)
            .build();

    public static final ConsumableComponent KOKAINA_CONSUMABLE_COMPONENT = ConsumableComponent.builder()
            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(ModEffects.HIGH, 600, 0, false, true)))
            .build();

    public static final FoodComponent RAW_BF_COMPONENT = new FoodComponent.Builder()
            .alwaysEdible()
            .nutrition(2)
            .saturationModifier(0.1F)
            .build();

    public static final ConsumableComponent RAW_BF_CONSUMABLE_COMPONENT = ConsumableComponent.builder()
            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.POISON, 600, 0, false, true)))
            .build();

    public static final FoodComponent OVERCOOKED_BF_COMPONENT = new FoodComponent.Builder()
            .alwaysEdible()
            .nutrition(4)
            .saturationModifier(0.2F)
            .build();

    public static final ConsumableComponent OVERCOOKED_BF_CONSUMABLE_COMPONENT = ConsumableComponent.builder()
            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600, 0, false, true)))
            .build();
}


