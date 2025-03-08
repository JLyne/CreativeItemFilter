package org.hurricanegames.creativeitemfilter.handler.component;

import io.papermc.paper.datacomponent.DataComponentTypes;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;
import org.hurricanegames.creativeitemfilter.utils.ItemComponentUtils;
import org.jetbrains.annotations.NotNull;

public class MapComponentPopulator implements ItemComponentPopulator {
	@SuppressWarnings("UnstableApiUsage")
	@Override
	public void populateComponents(@NotNull ItemStack oldItem, @NotNull ItemStack newItem,
								   CreativeItemFilterConfiguration configuration) {
		if(oldItem.getType() != Material.FILLED_MAP) {
			return;
		}

		ItemComponentUtils.copyComponent(oldItem, newItem, DataComponentTypes.MAP_ID);
		ItemComponentUtils.copyComponent(oldItem, newItem, DataComponentTypes.MAP_COLOR);
		ItemComponentUtils.copyComponent(oldItem, newItem, DataComponentTypes.MAP_DECORATIONS);
	}
}
