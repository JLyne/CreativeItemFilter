package org.hurricanegames.creativeitemfilter;

import java.io.File;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.hurricanegames.commandlib.commands.BukkitCommandExecutor;
import org.hurricanegames.creativeitemfilter.commands.CreativeItemFilterCommandHelper;
import org.hurricanegames.creativeitemfilter.commands.CreativeItemFilterCommands;
import org.hurricanegames.creativeitemfilter.handler.CreativeItemFilterHandler;
import org.hurricanegames.creativeitemfilter.handler.meta.MetaCopierFactory;

public class CreativeItemFilter extends JavaPlugin implements Listener {

	private final CreativeItemFilterConfiguration configuration = new CreativeItemFilterConfiguration(new File(getDataFolder(), "config.yml"));

	private final MetaCopierFactory metaCopierFactory = new MetaCopierFactory();

	@Override
	public void onEnable() {
		configuration.reload();
		getServer().getPluginManager().registerEvents(new CreativeItemFilterHandler(getLogger(), metaCopierFactory, configuration), this);
		getCommand("creativeitemfilter").setExecutor(new BukkitCommandExecutor(new CreativeItemFilterCommands(new CreativeItemFilterCommandHelper(configuration)), "creativeitemfilter.admin"));
	}

	public MetaCopierFactory getMetaCopierFactory() {
		return metaCopierFactory;
	}
}
