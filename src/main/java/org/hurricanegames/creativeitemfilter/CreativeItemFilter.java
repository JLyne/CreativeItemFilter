package org.hurricanegames.creativeitemfilter;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.hurricanegames.creativeitemfilter.handler.CreativeItemFilterHandler;
import org.hurricanegames.creativeitemfilter.handler.meta.MetaCopierFactory;
import org.jetbrains.annotations.NotNull;


public class CreativeItemFilter extends JavaPlugin implements Listener {

	private final MetaCopierFactory metaCopierFactory = new MetaCopierFactory();

	@Override
	public void onEnable() {
		CreativeItemFilterConfiguration configuration = new CreativeItemFilterConfiguration(this);

		saveDefaultConfig();
		getServer().getPluginManager().registerEvents(new CreativeItemFilterHandler(getLogger(), metaCopierFactory,
																					configuration), this);
	}

	@Override
	public boolean onCommand(
			@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if(command.equals(getCommand("creativeitemfilter")) && args.length == 1 && args[0].equals("reload")) {
			reloadConfig();
			sender.sendMessage(Component.text("Reloaded config.").color(NamedTextColor.GREEN));
			return true;
		}

		return super.onCommand(sender, command, label, args);
	}

	public MetaCopierFactory getMetaCopierFactory() {
		return metaCopierFactory;
	}
}
