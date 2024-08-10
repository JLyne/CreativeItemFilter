package org.hurricanegames.creativeitemfilter;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.hurricanegames.creativeitemfilter.handler.CreativeItemFilterHandler;
import org.hurricanegames.creativeitemfilter.handler.meta.MetaCopierFactory;

import static io.papermc.paper.command.brigadier.Commands.literal;

@SuppressWarnings("UnstableApiUsage")
public class CreativeItemFilter extends JavaPlugin implements Listener {

	private final MetaCopierFactory metaCopierFactory = new MetaCopierFactory();
	public static final PlainTextComponentSerializer plain = PlainTextComponentSerializer.plainText();

	@Override
	public void onEnable() {
		CreativeItemFilterConfiguration configuration = new CreativeItemFilterConfiguration(this);

		saveDefaultConfig();
		getServer().getPluginManager().registerEvents(new CreativeItemFilterHandler(getLogger(), metaCopierFactory,
																					configuration), this);

		LifecycleEventManager<Plugin> manager = getLifecycleManager();
		manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> registerCommands(event.registrar()));
	}

	public void registerCommands(Commands commands) {
		LiteralCommandNode<CommandSourceStack> reloadCommand = literal("reload")
				.requires(source -> source.getSender().hasPermission("creativeitemfilter.reload"))
				.executes(ctx -> {
					reloadConfig();
					ctx.getSource().getSender()
							.sendMessage(Component.text("Reloaded config.").color(NamedTextColor.GREEN));
					return Command.SINGLE_SUCCESS;
				}).build();

		commands.register(literal("creativeitemfilter")
								  .then(reloadCommand).build(), "Main command for CreativeItemFilter");
	}

	public MetaCopierFactory getMetaCopierFactory() {
		return metaCopierFactory;
	}
}
