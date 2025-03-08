package org.hurricanegames.creativeitemfilter.handler.component;

import org.bukkit.inventory.ItemStack;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;
import org.jetbrains.annotations.NotNull;

public interface ItemComponentPopulator {
	void populateComponents(
			@NotNull ItemStack oldItem, @NotNull ItemStack newItem, CreativeItemFilterConfiguration configuration);
}
