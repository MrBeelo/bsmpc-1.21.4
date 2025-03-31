package net.mrbeelo.bsmpc.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.math.BlockPos;
import net.mrbeelo.bsmpc.BsmpC;
import net.mrbeelo.bsmpc.screen.custom.handler.DeliberatorScreenHandler;
import net.mrbeelo.bsmpc.screen.custom.handler.SafeScreenHandler;

public class ModScreenHandlers {
    public static final ScreenHandlerType<SafeScreenHandler> SAFE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, BsmpC.id("pedestal_screen_handler"),
                    new ExtendedScreenHandlerType<>(SafeScreenHandler::new, BlockPos.PACKET_CODEC));

    public static final ScreenHandlerType<DeliberatorScreenHandler> DELIBERATOR_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, BsmpC.id("deliberator_screen_handler"),
                    new ExtendedScreenHandlerType<>(DeliberatorScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerModScreenHandlers() {
        BsmpC.LOGGER.info("Registering Mod Screen Handlers for " + BsmpC.MOD_ID);
    }
}
