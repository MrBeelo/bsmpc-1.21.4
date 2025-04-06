package net.mrbeelo.bsmpc.world;

import net.mrbeelo.bsmpc.BsmpC;
import net.mrbeelo.bsmpc.world.gen.*;

public class ModWorldGenerator {
    public static void generateModWorldGeneration() {
        ModGeodeGeneration.generateGeodes();
        ModOreGeneration.generateOres();
        ModTreeGeneration.generateTrees();
        ModFlowerGeneration.generateFlowers();
        ModEntitySpawns.generateEntitySpawns();
        ModBushGeneration.generateBushes();

        BsmpC.LOGGER.info("Generating World Generation for " + BsmpC.MOD_ID);
    }
}
