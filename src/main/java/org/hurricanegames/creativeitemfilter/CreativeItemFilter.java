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
import org.hurricanegames.creativeitemfilter.handler.component.ItemComponentPopulatorFactory;
import org.hurricanegames.creativeitemfilter.handler.meta.MetaCopierFactory;
import uk.co.notnull.messageshelper.MessagesHelper;

import java.io.File;

import static io.papermc.paper.command.brigadier.Commands.literal;

@SuppressWarnings("UnstableApiUsage")
public class CreativeItemFilter extends JavaPlugin implements Listener {

	private static CreativeItemFilter instance ;
	private final MetaCopierFactory metaCopierFactory = new MetaCopierFactory();
	private final ItemComponentPopulatorFactory componentPopulatorFactory = new ItemComponentPopulatorFactory();
	private static MessagesHelper messagesHelper;

	public static CreativeItemFilterConfiguration configuration;

	@Override
	public void onEnable() {
		instance = this;
		configuration = new CreativeItemFilterConfiguration(this);
		messagesHelper = MessagesHelper.getInstance(this);

		loadConfig();
		getServer().getPluginManager().registerEvents(
				new CreativeItemFilterHandler(getLogger(), metaCopierFactory, componentPopulatorFactory, configuration),
				this);

		LifecycleEventManager<Plugin> manager = getLifecycleManager();
		manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> registerCommands(event.registrar()));
	}

	private void loadConfig() {
		saveDefaultConfig();
		reloadConfig();
		configuration.reloadValues();
		File messages = new File(getDataFolder(), "messages.yml");

		if(!messages.exists()) {
			saveResource("messages.yml", false);
		}

		messagesHelper.loadMessages(messages);
	}

	private void registerCommands(Commands commands) {
		LiteralCommandNode<CommandSourceStack> reloadCommand = literal("reload")
				.executes(ctx -> {
					loadConfig();
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

	public ItemComponentPopulatorFactory getComponentPopulatorFactory() {
		return componentPopulatorFactory;
	}

	public static CreativeItemFilter getInstance() {
		return instance;
	}

	public static MessagesHelper getMessagesHelper() {
		return messagesHelper;
	}
}
