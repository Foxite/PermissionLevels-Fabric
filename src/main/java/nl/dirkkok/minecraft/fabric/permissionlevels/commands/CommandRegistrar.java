package nl.dirkkok.minecraft.fabric.permissionlevels.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;

public class CommandRegistrar implements CommandRegistrationCallback {
	public void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
		if (!dedicated) {
			return;
		}

		XOpCommand.register(dispatcher, "xop");
	}
}
