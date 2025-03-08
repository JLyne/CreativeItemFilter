package org.hurricanegames.creativeitemfilter.handler.component;

import com.destroystokyo.paper.MaterialSetTag;
import io.papermc.paper.datacomponent.DataComponentTypes;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;
import org.hurricanegames.creativeitemfilter.utils.ItemComponentUtils;
import org.jetbrains.annotations.NotNull;

public final class BannerComponentPopulator implements ItemComponentPopulator {
	@SuppressWarnings("UnstableApiUsage")
	@Override
	public void populateComponents(
			@NotNull ItemStack oldItem, @NotNull ItemStack newItem, CreativeItemFilterConfiguration configuration) {
		if(!MaterialSetTag.BANNERS.isTagged(oldItem.getType()) && oldItem.getType() != Material.SHIELD) {
			return;
		}

		ItemComponentUtils.copyComponent(oldItem, newItem, DataComponentTypes.BANNER_PATTERNS);

		if(oldItem.getType() == Material.SHIELD) {
			ItemComponentUtils.copyComponent(oldItem, newItem, DataComponentTypes.BASE_COLOR);
		}
	}
}
