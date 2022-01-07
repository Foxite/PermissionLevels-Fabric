package nl.dirkkok.minecraft.fabric.permissionlevels;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import nl.dirkkok.minecraft.fabric.permissionlevels.commands.CommandRegistrar;
import nl.dirkkok.minecraft.fabric.permissionlevels.commands.XOpCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PermissionLevels implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("modid");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");

		CommandRegistrationCallback.EVENT.register(new CommandRegistrar());
	}
}
