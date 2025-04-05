package net.mrbeelo.bsmpc.world.biome;

import net.mrbeelo.bsmpc.BsmpC;
import net.mrbeelo.bsmpc.world.biome.surface.ModMaterialRules;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class ModTerrablenderAPI implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new ModOverworldRegion(BsmpC.id("overworld"), 4));
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, BsmpC.MOD_ID, ModMaterialRules.makeRules());
    }
}
