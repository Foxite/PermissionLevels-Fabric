package nl.dirkkok.minecraft.fabric.permissionlevels.commands;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.argument.GameProfileArgumentType;
import net.minecraft.server.OperatorEntry;
import net.minecraft.server.OperatorList;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import nl.dirkkok.minecraft.fabric.permissionlevels.PermissionLevels;

public class XOpCommand {
	public static void register(CommandDispatcher<ServerCommandSource> dispatcher, String name) {
		LiteralCommandNode<ServerCommandSource> xopNode = CommandManager
			.literal(name) // TODO make unusable by command blocks
			.requires(scs -> scs.hasPermissionLevel(3)) // same as /op, which requires permission level 3, despite granting level 4 by default
			.then(CommandManager.argument("target", net.minecraft.command.argument.GameProfileArgumentType.gameProfile())
				.then(CommandManager.argument("level", com.mojang.brigadier.arguments.IntegerArgumentType.integer(1, 4))
					.then(CommandManager.argument("bypassesPlayerLimit", BoolArgumentType.bool())
						.executes(ctx -> run(ctx, true))
					)
					.executes(ctx -> run(ctx, false))
				)
			)
			.build();

		dispatcher.getRoot().addChild(xopNode);
	}

	public static int run(CommandContext<ServerCommandSource> ctx, boolean optionalParamPresent) throws CommandSyntaxException {
		try {
			GameProfile target = GameProfileArgumentType.getProfileArgument(ctx, "target").stream().findFirst().get();
			int level = IntegerArgumentType.getInteger(ctx, "level");
			boolean bypassesPlayerLimit = true;
			if (optionalParamPresent) {
				bypassesPlayerLimit = BoolArgumentType.getBool(ctx, "bypassesPlayerLimit");
			}

			OperatorList oplist = ctx.getSource().getServer().getPlayerManager().getOpList();
			int userPermissionLevel = oplist.get(ctx.getSource().getPlayer().getGameProfile()).getPermissionLevel();
			if (level <= userPermissionLevel) {
				oplist.add(new OperatorEntry(target, level, bypassesPlayerLimit));
				ctx.getSource().sendFeedback(Text.of("Made %s a level %d op".formatted(target.getName(), level)), true);
			} else {
				ctx.getSource().sendError(Text.of("You cannot promote someone above your own permission level (%d)".formatted(userPermissionLevel)));
			}
			return 1;
		} catch (Throwable t) {
			PermissionLevels.LOGGER.error(t);
			throw t;
		}
	}
}
