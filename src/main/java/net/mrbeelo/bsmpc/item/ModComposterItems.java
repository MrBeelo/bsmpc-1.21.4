package net.mrbeelo.bsmpc.item;

import net.minecraft.block.ComposterBlock;
import net.mrbeelo.bsmpc.BsmpC;

public class ModComposterItems {
    public static void registerModComposterItems() {
        BsmpC.LOGGER.info("Registering Mod Composter Items for " + BsmpC.MOD_ID);

        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.KOKAINA, 0.5f);
        //ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.KOKAINA_SEED, 0.5f);
    }
}
