package net.mrbeelo.bsmpc;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.mrbeelo.bsmpc.block.ModBlocks;
import net.mrbeelo.bsmpc.block.entity.ModBlockEntities;
import net.mrbeelo.bsmpc.command.ModCommands;
import net.mrbeelo.bsmpc.components.ModDataComponentTypes;
import net.mrbeelo.bsmpc.effect.ModEffects;
import net.mrbeelo.bsmpc.enchantment.ModEnchantments;
import net.mrbeelo.bsmpc.entity.ModAttributes;
import net.mrbeelo.bsmpc.entity.ModEntities;
import net.mrbeelo.bsmpc.event.EntityDeathListener;
import net.mrbeelo.bsmpc.fluid.ModFluids;
import net.mrbeelo.bsmpc.item.*;
import net.mrbeelo.bsmpc.particle.ModParticles;
import net.mrbeelo.bsmpc.potion.ModPotionRecipes;
import net.mrbeelo.bsmpc.potion.ModPotions;
import net.mrbeelo.bsmpc.recipe.ModRecipes;
import net.mrbeelo.bsmpc.screen.ModScreenHandlers;
import net.mrbeelo.bsmpc.sound.ModSounds;
import net.mrbeelo.bsmpc.util.ModProperties;
import net.mrbeelo.bsmpc.villager.ModCustomTrades;
import net.mrbeelo.bsmpc.villager.ModVillagers;
import net.mrbeelo.bsmpc.world.ModWorldGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import static net.mrbeelo.bsmpc.effect.custom.HighEffect.playersWithHighSound;

public class BsmpC implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("bsmpc");
	public static final String MOD_ID = "bsmpc";

	@Override
	public void onInitialize() {
		LOGGER.info("Loading your stupid BSMP mod.");

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerModItemGroups();
		ModWorldGenerator.generateModWorldGeneration();
		ModArmorMaterials.registerModArmorMaterials();
		ModSounds.registerModSounds();
		ModVillagers.registerModVillagers();
		ModCustomTrades.registerModCustomTrades();
		ModPotions.registerModPotions();
		ModPotionRecipes.registerModPotionRecipes();
		ModComposterItems.registerModComposterItems();
		ModBlockEntities.registerModBlockEntities();
		ModDataComponentTypes.registerModDataComponentTypes();
		ModEntities.registerModEntities();
		ModAttributes.registerModAttributes();
		ModFluids.registerModFluids();
		ModEffects.registerModEffects();
		ModCommands.registerModCommands();
		ModEnchantments.registerModEnchantments();
		ModParticles.registerModParticles();
		ModScreenHandlers.registerModScreenHandlers();
		ModRecipes.registerModRecipes();
		ModToolMaterials.registerModToolMaterials();
		ModEquipmentAssets.registerModEquipmentAssets();
		ModFuelItems.registerModFuelItems();
		ModProperties.registerModProperties();

		ServerTickEvents.END_WORLD_TICK.register(world -> {
			if (world instanceof ServerWorld) {
				for (PlayerEntity player : world.getPlayers()) {
					if (!player.hasStatusEffect(ModEffects.HIGH) && playersWithHighSound.contains(player)) {
						playersWithHighSound.remove(player);
						if (player instanceof ServerPlayerEntity serverPlayerEntity) {
							serverPlayerEntity.networkHandler.sendPacket(new net.minecraft.network.packet.s2c.play.StopSoundS2CPacket(Identifier.of("bsmpc", "high"), SoundCategory.PLAYERS));
						}
					}
				}
			}});

		ServerLivingEntityEvents.AFTER_DEATH.register(new EntityDeathListener());

		StrippableBlockRegistry.register(ModBlocks.CS_LOG, ModBlocks.STRIPPED_CS_LOG);
		StrippableBlockRegistry.register(ModBlocks.CS_WOOD, ModBlocks.STRIPPED_CS_WOOD);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.CS_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.CS_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_CS_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_CS_WOOD, 5, 5);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.CS_PLANKS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.CS_LEAVES, 30, 60);

		CustomPortalBuilder.beginPortal()
				.frameBlock(ModBlocks.CS_LOG)
				.destDimID(BsmpC.id("cs_dimension"))
				.lightWithFluid(ModFluids.STILL_FROOTOP)
				.tintColor(0x5dfc95)
				.forcedSize(5, 5)
				.registerPortal();
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}

	public static Identifier id(String namespace, String path) {
		return Identifier.of(namespace, path);
	}

	/*public static void serverCommand(ServerWorld world, PlayerEntity user, String command) {
		if (user != null) {
			ServerCommandSource source = new ServerCommandSource(
					user, user.getPos(), user.getRotationClient(), world, 4, user.getName().getString(), user.getDisplayName(), world.getServer(), user).withSilent();
			try {
				world.getServer().getCommandManager().getDispatcher().execute(command, source);
			} catch (CommandSyntaxException e) {
				user.sendMessage(Text.literal("Command failed: " + e.getMessage()), false);
			}
		}
	}*/

	public static BlockPos getBlockInFrontXZ(PlayerEntity player, int distance) {
		// Get the player's yaw
		float yaw = player.getYaw();

		// Convert yaw from degrees to radians for trigonometric functions
		double yawRad = Math.toRadians(yaw);

		// Calculate the x and z offsets using trigonometry, ignoring the pitch (y-axis)
		double xOffset = -Math.sin(yawRad) * distance;
		double zOffset = Math.cos(yawRad) * distance;

		// Get the player's current position
		Vec3d playerPos = player.getPos();

		// Calculate the target position by adding the offsets to the player's position
		double targetX = playerPos.x + xOffset;
		double targetZ = playerPos.z + zOffset;

		// Use the player's current Y position since we are ignoring the Y-axis
		double targetY = playerPos.y;

		// Convert the target position to a block position by casting to integers
		// We round the coordinates to get the nearest block
		int blockX = (int) Math.round(targetX);
		int blockY = (int) Math.round(targetY);
		int blockZ = (int) Math.round(targetZ);

		// Return the block position in front of the player
		return new BlockPos(blockX, blockY, blockZ);
	}

	public static BlockPos getBlockInFrontXYZ(PlayerEntity player, int distance) {
		// Get the player's yaw and pitch
		float yaw = player.getYaw();
		float pitch = player.getPitch();

		// Convert yaw and pitch from degrees to radians for trigonometric functions
		double yawRad = Math.toRadians(yaw);
		double pitchRad = Math.toRadians(pitch);

		// Calculate the x, y, and z offsets using trigonometry
		double xOffset = -Math.sin(yawRad) * Math.cos(pitchRad) * distance;
		double zOffset = Math.cos(yawRad) * Math.cos(pitchRad) * distance;
		double yOffset = -Math.sin(pitchRad) * distance;

		// Get the player's current position
		Vec3d playerPos = player.getPos();

		// Calculate the target position by adding the offsets to the player's position
		double targetX = playerPos.x + xOffset;
		double targetY = playerPos.y + yOffset;
		double targetZ = playerPos.z + zOffset;

		// Convert the target position to a block position by casting to integers
		// We round the coordinates to get the nearest block
		int blockX = (int) Math.round(targetX);
		int blockY = (int) Math.round(targetY);
		int blockZ = (int) Math.round(targetZ);

		// Return the block position in front of the player
		return new BlockPos(blockX, blockY, blockZ);
	}


	public static double getBlockInFrontX(PlayerEntity player, int distance) {
		// Get the player's yaw
		float yaw = player.getYaw();

		// Convert yaw from degrees to radians for trigonometric functions
		double yawRad = Math.toRadians(yaw);

		// Calculate the x offset using trigonometry
		double xOffset = -Math.sin(yawRad) * distance;

		// Get the player's current position
		Vec3d playerPos = player.getPos();

		// Calculate the target X position by adding the offset to the player's X position
		double targetX = playerPos.x + xOffset;

		// Return the target X coordinate
		return targetX;
	}

	public static double getBlockInFrontY(PlayerEntity player, int distance) {
		// Get the player's pitch
		float pitch = player.getPitch();

		// Convert pitch from degrees to radians for trigonometric functions
		double pitchRad = Math.toRadians(pitch);

		// Calculate the Y offset using trigonometry
		double yOffset = -Math.sin(pitchRad) * distance;

		// Get the player's current position
		Vec3d playerPos = player.getPos();

		// Calculate the target Y position by adding the offset to the player's position
		double targetY = playerPos.y + yOffset;

		// Return the target Y coordinate
		return targetY;
	}


	public static double getBlockInFrontZ(PlayerEntity player, int distance) {
		// Get the player's yaw
		float yaw = player.getYaw();

		// Convert yaw from degrees to radians for trigonometric functions
		double yawRad = Math.toRadians(yaw);

		// Calculate the z offset using trigonometry
		double zOffset = Math.cos(yawRad) * distance;

		// Get the player's current position
		Vec3d playerPos = player.getPos();

		// Calculate the target Z position by adding the offset to the player's Z position
		double targetZ = playerPos.z + zOffset;

		// Return the target Z coordinate
		return targetZ;
	}

	public static boolean hasTag(Entity entity, String tag) {
		Set<String> tags = entity.getCommandTags();
		return tags.contains(tag);
	}

	public static void scheduleTicks(PlayerEntity player, int delayTicks, Runnable action) {
		// Use an array to hold the tick count and flag for task completion in a single scope
		final int[] ticksRemaining = {delayTicks}; // Array to store remaining ticks
		final boolean[] isTaskComplete = {false}; // Flag to indicate task completion

		// Register a tick event handler for scheduling
		ServerTickEvents.END_SERVER_TICK.register(server -> {
			if (!isTaskComplete[0]) { // Check if the task is already complete
				if (ticksRemaining[0] > 0) {
					ticksRemaining[0]--; // Decrement the tick counter
				} else {
					action.run(); // Run the action
					isTaskComplete[0] = true; // Mark task as complete
				}
			}
		});

		/*
		scheduleTicks(serverPlayerEntity, 100, () -> {
                player.sendMessage(Text.literal("Message after 100 ticks"), true);
            });
		 */
	}

	public static boolean isMoving(Entity entity) {
        return entity.getVelocity().x != 0 || entity.getVelocity().y != 0 || entity.getVelocity().z != 0;
	}

	public static boolean isMovingHorizontally(Entity entity) {
		return entity.getVelocity().x != 0 || entity.getVelocity().z != 0;
	}

	public static boolean isMovingVertically(Entity entity) {
		return entity.getVelocity().y != 0;
	}

	public boolean isPlayerWalking(PlayerEntity player) {
		return isMovingHorizontally(player) && player.isOnGround() && !isPlayerFlying(player) && !player.isSprinting();
	}

	public boolean isPlayerFlying(PlayerEntity player) {
		return player.getAbilities().flying;
	}
}