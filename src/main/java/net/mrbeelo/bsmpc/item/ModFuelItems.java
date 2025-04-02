package net.mrbeelo.bsmpc.item;

import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.block.ComposterBlock;
import net.mrbeelo.bsmpc.BsmpC;

public class ModFuelItems {
    public static void registerModFuelItems() {
        BsmpC.LOGGER.info("Registering Mod Fuel Items for " + BsmpC.MOD_ID);

        FuelRegistryEvents.BUILD.register(((builder, context) -> {
            builder.add(ModItems.KOKAINA, 60 * 20);
        }));
    }
}
