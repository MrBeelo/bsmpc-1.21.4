package net.mrbeelo.bsmpc.command;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.mrbeelo.bsmpc.BsmpC;
import net.mrbeelo.bsmpc.command.custom.*;

public class ModCommands {
    public static void registerModCommands() {
        BsmpC.LOGGER.info("Registering Mod Commands for " + BsmpC.MOD_ID);

        CommandRegistrationCallback.EVENT.register(GnomeCommand::register);
        CommandRegistrationCallback.EVENT.register(CorrectCommand::register);
        CommandRegistrationCallback.EVENT.register(IncorrectCommand::register);
    }
}
