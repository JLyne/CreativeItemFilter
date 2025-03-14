package org.hurricanegames.creativeitemfilter.handler.component;

import io.papermc.paper.datacomponent.DataComponentTypes;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;
import org.hurricanegames.creativeitemfilter.utils.ItemComponentUtils;
import org.jetbrains.annotations.NotNull;

public class InstrumentComponentPopulator implements ItemComponentPopulator {
	@SuppressWarnings("UnstableApiUsage")
	@Override
	public void populateComponents(@NotNull ItemStack oldItem, @NotNull ItemStack newItem,
								   CreativeItemFilterConfiguration configuration) {
		if(oldItem.getType() == Material.GOAT_HORN) {
			ItemComponentUtils.copyComponent(oldItem, newItem, DataComponentTypes.INSTRUMENT);
		}
	}
}
