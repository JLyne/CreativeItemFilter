package org.hurricanegames.creativeitemfilter.handler.component;

import io.papermc.paper.datacomponent.DataComponentType;
import io.papermc.paper.datacomponent.DataComponentTypes;
import org.bukkit.inventory.ItemStack;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;
import org.hurricanegames.creativeitemfilter.utils.ItemComponentUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public final class BasicComponentPopulator implements ItemComponentPopulator {
	private static final List<DataComponentType.Valued<?>> basicComponents = List.of(
			DataComponentTypes.REPAIR_COST,
			DataComponentTypes.DAMAGE
	);

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void populateComponents(
			@NotNull ItemStack oldItem, @NotNull ItemStack newItem, CreativeItemFilterConfiguration configuration) {
		for (DataComponentType.Valued component : basicComponents) {
			ItemComponentUtils.copyComponent(oldItem, newItem, component);
		}
	}
}
