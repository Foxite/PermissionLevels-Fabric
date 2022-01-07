package nl.dirkkok.minecraft.fabric.permissionlevels.mixins;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import nl.dirkkok.minecraft.fabric.permissionlevels.commands.XOpCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CommandManager.class)
public class RedirectVanillaOpCommandRegisterMixin {
	@Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/dedicated/command/OpCommand;register(Lcom/mojang/brigadier/CommandDispatcher;)V"))
	private void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		XOpCommand.register(dispatcher, "op");
	}
}
