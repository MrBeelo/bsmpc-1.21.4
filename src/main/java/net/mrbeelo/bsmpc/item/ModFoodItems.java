package net.mrbeelo.bsmpc.item;

import net.minecraft.component.type.Consumable;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.mrbeelo.bsmpc.effect.ModEffects;

public class ModFoodItems {
    public static final FoodComponent KOKAINA_COMPONENT = new FoodComponent.Builder()
            .alwaysEdible()
            .nutrition(6)
            .saturationModifier(0.4F)
            .alwaysEdible()
            .build();

    public static final ConsumableComponent KOKAINA_CONSUMABLE_COMPONENT = ConsumableComponent.builder()
            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(ModEffects.HIGH, 600, 0, false, true)))
            .build();
}


