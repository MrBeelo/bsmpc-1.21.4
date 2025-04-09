package net.mrbeelo.bsmpc.util;

import net.minecraft.state.property.IntProperty;
import net.mrbeelo.bsmpc.BsmpC;

public class ModProperties {
    public static final IntProperty RELAY_CHARGES = IntProperty.of("relay_charges", 0, 1);

    public static void registerModProperties() {
        BsmpC.LOGGER.info("Registering Mod Properties for " + BsmpC.MOD_ID);
    }
}
