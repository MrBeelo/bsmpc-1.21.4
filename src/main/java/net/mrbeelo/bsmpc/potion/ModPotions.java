package net.mrbeelo.bsmpc.potion;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.mrbeelo.bsmpc.BsmpC;
import net.mrbeelo.bsmpc.effect.ModEffects;

public class ModPotions {
    public static final RegistryEntry<Potion> KOKAINA = register("kokaina", new Potion("kokaina", new StatusEffectInstance(ModEffects.HIGH, 1000, 0)));

    public static RegistryEntry<Potion> register(String name, Potion potion) {
        return Registry.registerReference(Registries.POTION, BsmpC.id(name), potion);
    }

    public static void registerModPotions() {
        BsmpC.LOGGER.info("Registering Mod Potions for " + BsmpC.MOD_ID);
    }
}
