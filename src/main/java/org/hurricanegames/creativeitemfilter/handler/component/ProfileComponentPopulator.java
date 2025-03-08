package org.hurricanegames.creativeitemfilter.handler.component;

import io.papermc.paper.datacomponent.DataComponentTypes;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;
import org.hurricanegames.creativeitemfilter.utils.ItemComponentUtils;
import org.jetbrains.annotations.NotNull;


@SuppressWarnings("UnstableApiUsage")
public class ProfileComponentPopulator implements ItemComponentPopulator {
	@Override
	public void populateComponents(@NotNull ItemStack oldItem, @NotNull ItemStack newItem,
								   CreativeItemFilterConfiguration configuration) {
		if(oldItem.getType() != Material.PLAYER_HEAD) {
			return;
		}

		ItemComponentUtils.copyComponent(oldItem, newItem, DataComponentTypes.PROFILE);
	}
}
