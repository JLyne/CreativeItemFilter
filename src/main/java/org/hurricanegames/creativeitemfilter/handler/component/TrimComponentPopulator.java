package org.hurricanegames.creativeitemfilter.handler.component;

import com.destroystokyo.paper.MaterialSetTag;
import io.papermc.paper.datacomponent.DataComponentTypes;
import org.bukkit.inventory.ItemStack;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;
import org.hurricanegames.creativeitemfilter.utils.ItemComponentUtils;
import org.jetbrains.annotations.NotNull;


@SuppressWarnings("UnstableApiUsage")
public class TrimComponentPopulator implements ItemComponentPopulator {
	@Override
	public void populateComponents(@NotNull ItemStack oldItem, @NotNull ItemStack newItem,
								   CreativeItemFilterConfiguration configuration) {
		if(!MaterialSetTag.ITEMS_TRIMMABLE_ARMOR.isTagged(oldItem.getType())) {
			return;
		}

		ItemComponentUtils.copyComponent(oldItem, newItem, DataComponentTypes.TRIM);
	}
}
