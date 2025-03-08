package org.hurricanegames.creativeitemfilter.utils;

import io.papermc.paper.datacomponent.DataComponentType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class ItemComponentUtils {
	@SuppressWarnings("UnstableApiUsage")
	public static <T> void copyComponent(ItemStack oldItem, ItemStack newItem, DataComponentType.Valued<T> component) {
		if(oldItem.isDataOverridden(component)) {
			T data = oldItem.getData(component);

			if(data == null) {
				newItem.unsetData(component);
			} else {
				newItem.setData(component, data);
			}
		}
	}

	@SuppressWarnings("UnstableApiUsage")
	public static <T> void copyComponent(ItemStack oldItem, ItemStack newItem, DataComponentType.Valued<T> component,
										 Function<@Nullable T, @Nullable T> transformer) {
		if(oldItem.isDataOverridden(component)) {
			T data = transformer.apply(oldItem.getData(component));

			if(data == null) {
				newItem.unsetData(component);
			} else {
				newItem.setData(component, data);
			}
		}
	}
}
