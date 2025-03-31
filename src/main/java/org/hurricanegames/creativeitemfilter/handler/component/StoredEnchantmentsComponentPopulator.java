package org.hurricanegames.creativeitemfilter.handler.component;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemEnchantments;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;
import org.hurricanegames.creativeitemfilter.utils.ItemComponentUtils;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("UnstableApiUsage")
public class StoredEnchantmentsComponentPopulator implements ItemComponentPopulator {
	@Override
	public void populateComponents(@NotNull ItemStack oldItem, @NotNull ItemStack newItem,
								   CreativeItemFilterConfiguration configuration) {
		if(oldItem.getType() != Material.ENCHANTED_BOOK) {
			return;
		}

		ItemComponentUtils.copyComponent(oldItem, newItem, DataComponentTypes.STORED_ENCHANTMENTS,
										 component -> filterEnchantments(component, configuration));
	}

	private static ItemEnchantments filterEnchantments(ItemEnchantments component, CreativeItemFilterConfiguration configuration) {
		if(component == null) {
			return null;
		}

		int enchantmentMaxLevel = configuration.getEnchantmentBookMaxLevel();

		ItemEnchantments.Builder newComponent =  ItemEnchantments.itemEnchantments();

		component.enchantments().entrySet().stream()
				.filter(entry ->
								(entry.getValue() > 0) && (entry.getValue() <= enchantmentMaxLevel))
				.limit(configuration.getEnchantmentBookMaxCount())
				.forEach(entry -> newComponent.add(entry.getKey(), entry.getValue()));

		return newComponent.build();
	}
}
