package org.hurricanegames.creativeitemfilter.commands;

import org.hurricanegames.commandlib.commands.CommandHelper;
import org.hurricanegames.commandlib.commands.CommandMessages;
import org.hurricanegames.commandlib.providers.messages.DefaultMessages;
import org.hurricanegames.commandlib.providers.playerinfo.BukkitPlayerInfo;
import org.hurricanegames.commandlib.providers.playerinfo.BukkitPlayerInfoProvider;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;

public class CreativeItemFilterCommandHelper extends CommandHelper<CommandMessages, BukkitPlayerInfo, BukkitPlayerInfoProvider> {

	private final CreativeItemFilterConfiguration configuration;

	public CreativeItemFilterCommandHelper(CreativeItemFilterConfiguration configuration) {
		super(DefaultMessages.IMMUTABLE, BukkitPlayerInfoProvider.INSTANCE);
		this.configuration = configuration;
	}

	public CreativeItemFilterConfiguration getMainConfiguration() {
		return configuration;
	}

}
