package org.hurricanegames.creativeitemfilter.commands;

import org.hurricanegames.commandlib.commands.CommandRouter;
import org.hurricanegames.commandlib.providers.commands.SimpleConfigurationReloadCommand;

public class CreativeItemFilterCommands extends CommandRouter<CreativeItemFilterCommandHelper> {

	public CreativeItemFilterCommands(CreativeItemFilterCommandHelper helper) {
		super(helper);
		addCommand("reload", new SimpleConfigurationReloadCommand<>(helper, helper.getMainConfiguration(), () -> "main"));
	}

}
