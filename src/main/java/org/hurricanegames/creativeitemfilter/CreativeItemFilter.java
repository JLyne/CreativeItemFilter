package org.hurricanegames.creativeitemfilter;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.hurricanegames.creativeitemfilter.handler.CreativeItemFilterHandler;
import org.hurricanegames.creativeitemfilter.handler.meta.MetaCopierFactory;

import static io.papermc.paper.command.brigadier.Commands.literal;

@SuppressWarnings("UnstableApiUsage")
public class CreativeItemFilter extends JavaPlugin implements Listener {

	private static CreativeItemFilter instance ;
	private final MetaCopierFactory metaCopierFactory = new MetaCopierFactory();

	public static CreativeItemFilterConfiguration configuration;

	@Override
	public void onEnable() {
		instance = this;
		configuration = new CreativeItemFilterConfiguration(this);

		saveDefaultConfig();
		getServer().getPluginManager().registerEvents(new CreativeItemFilterHandler(getLogger(), metaCopierFactory,
																					configuration), this);

		LifecycleEventManager<Plugin> manager = getLifecycleManager();
		manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> registerCommands(event.registrar()));
	}

	private void registerCommands(Commands commands) {
		LiteralCommandNode<CommandSourceStack> reloadCommand = literal("reload")
				.executes(ctx -> {
					reloadConfig();
					ctx.getSource().getSender()
							.sendMessage(Component.text("Reloaded config.").color(NamedTextColor.GREEN));
					return Command.SINGLE_SUCCESS;
				}).build();

		commands.register(literal("creativeitemfilter")
								  .requires(source -> source.getSender()
										  .hasPermission("creativeitemfilter.reload"))
								  .then(reloadCommand).build(), "Main command for CreativeItemFilter");
	}

	public MetaCopierFactory getMetaCopierFactory() {
		return metaCopierFactory;
	}

	public static CreativeItemFilter getInstance() {
		return instance;
	}
}
