package net.mrbeelo.bsmpc;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import com.terraformersmc.terraform.sign.api.SpriteIdentifierRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.entity.ArrowEntityRenderer;
import net.minecraft.client.render.entity.EmptyEntityRenderer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.world.biome.FoliageColors;
import net.mrbeelo.bsmpc.block.ModBlocks;
import net.mrbeelo.bsmpc.block.entity.ModBlockEntities;
import net.mrbeelo.bsmpc.block.entity.renderer.PedestalBlockEntityRenderer;
import net.mrbeelo.bsmpc.entity.ModEntities;
import net.mrbeelo.bsmpc.entity.client.ModEntityModelLayers;
import net.mrbeelo.bsmpc.entity.client.custom.model.BlobModel;
import net.mrbeelo.bsmpc.entity.client.custom.model.PyroModel;
import net.mrbeelo.bsmpc.entity.client.custom.model.SnekModel;
import net.mrbeelo.bsmpc.entity.client.custom.renderer.BlobRenderer;
import net.mrbeelo.bsmpc.entity.client.custom.renderer.PyroRenderer;
import net.mrbeelo.bsmpc.entity.client.custom.renderer.BulletProjectileRenderer;
import net.mrbeelo.bsmpc.entity.client.custom.renderer.SnekRenderer;
import net.mrbeelo.bsmpc.fluid.ModFluids;
import net.mrbeelo.bsmpc.particle.ModParticles;
import net.mrbeelo.bsmpc.screen.ModScreenHandlers;
import net.mrbeelo.bsmpc.screen.custom.DeliberatorScreen;
import net.mrbeelo.bsmpc.screen.custom.SafeScreen;

public class BsmpCClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.KOKAINA_CROP, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RUBY_DOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RUBY_TRAPDOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ROSE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_ROSE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CS_SAPLING, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CS_DOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CS_TRAPDOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.N_PLUSHIE, RenderLayer.getCutout());

		ParticleFactoryRegistry.getInstance().register(ModParticles.SPARKLE_PARTICLE, EndRodParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(ModParticles.MOBILIUM_PARTICLE, EndRodParticle.Factory::new);

		BlockEntityRendererFactories.register(ModBlockEntities.PEDESTAL_BE, PedestalBlockEntityRenderer::new);

		EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.SNEK, SnekModel::getTexturedModelData);
		EntityRendererRegistry.register(ModEntities.SNEK, SnekRenderer::new);

		EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.BLOB, BlobModel::getTexturedModelData);
		EntityRendererRegistry.register(ModEntities.BLOB, BlobRenderer::new);

		EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.PYRO, PyroModel::getTexturedModelData);
		EntityRendererRegistry.register(ModEntities.PYRO, PyroRenderer::new);

		EntityRendererRegistry.register(ModEntities.POKE_BALL_PROJECTILE, FlyingItemEntityRenderer::new);
		EntityRendererRegistry.register(ModEntities.BULLET_PROJECTILE, BulletProjectileRenderer::new);
		EntityRendererRegistry.register(ModEntities.PURIFY_BOMB_PROJECTILE, ArrowEntityRenderer::new);
		EntityRendererRegistry.register(ModEntities.LAZER, EmptyEntityRenderer::new);

		FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_FROOTOP, ModFluids.FLOWING_FROOTOP,
				SimpleFluidRenderHandler.coloredWater(0xFFFFA500));
		BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(),
				ModFluids.STILL_FROOTOP, ModFluids.FLOWING_FROOTOP);

		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ?
				BiomeColors.getFoliageColor(world, pos) : ModBlocks.CS_LEAVES.getDefaultMapColor().color, ModBlocks.CS_LEAVES);

		HandledScreens.register(ModScreenHandlers.SAFE_SCREEN_HANDLER, SafeScreen::new);
		HandledScreens.register(ModScreenHandlers.DELIBERATOR_SCREEN_HANDLER, DeliberatorScreen::new);

		TerraformBoatClientHelper.registerModelLayers(BsmpC.id("cs"));
	}
}