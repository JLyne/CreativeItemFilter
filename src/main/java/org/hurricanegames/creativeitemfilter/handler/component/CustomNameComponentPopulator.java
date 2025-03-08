package org.hurricanegames.creativeitemfilter.handler.component;

import io.papermc.paper.datacomponent.DataComponentTypes;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;
import org.hurricanegames.creativeitemfilter.utils.ChatComponentUtils;
import org.hurricanegames.creativeitemfilter.utils.ItemComponentUtils;
import org.jetbrains.annotations.NotNull;

public class CustomNameComponentPopulator implements ItemComponentPopulator {
	@SuppressWarnings("UnstableApiUsage")
	@Override
	public void populateComponents(@NotNull ItemStack oldItem, @NotNull ItemStack newItem,
								   CreativeItemFilterConfiguration configuration) {
		ItemComponentUtils.copyComponent(oldItem, newItem, DataComponentTypes.CUSTOM_NAME,
										 component -> filterName(component, configuration));
	}

	private static Component filterName(Component component, CreativeItemFilterConfiguration configuration) {
		if (component != null && ChatComponentUtils.validateComponent(component, configuration.getDisplayNameMaxLength())) {
			return component;
		}

		return null;
	}
}
