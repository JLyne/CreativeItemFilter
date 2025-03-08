package org.hurricanegames.creativeitemfilter.handler.component;

import io.papermc.paper.datacomponent.DataComponentTypes;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;
import org.hurricanegames.creativeitemfilter.utils.ItemComponentUtils;
import org.jetbrains.annotations.NotNull;

public final class CompassComponentPopulator implements ItemComponentPopulator {
	@SuppressWarnings("UnstableApiUsage")
	@Override
	public void populateComponents(
			@NotNull ItemStack oldItem, @NotNull ItemStack newItem, CreativeItemFilterConfiguration configuration) {
		if(oldItem.getType() != Material.COMPASS) {
			return;
		}

		ItemComponentUtils.copyComponent(oldItem, newItem, DataComponentTypes.LODESTONE_TRACKER);
	}
}

