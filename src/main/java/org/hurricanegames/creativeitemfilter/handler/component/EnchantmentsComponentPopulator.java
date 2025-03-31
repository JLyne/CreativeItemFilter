package org.hurricanegames.creativeitemfilter.handler.component;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemEnchantments;
import org.bukkit.inventory.ItemStack;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;
import org.hurricanegames.creativeitemfilter.utils.ItemComponentUtils;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("UnstableApiUsage")
public class EnchantmentsComponentPopulator implements ItemComponentPopulator {
	@Override
	public void populateComponents(@NotNull ItemStack oldItem, @NotNull ItemStack newItem,
								   CreativeItemFilterConfiguration configuration) {
		ItemComponentUtils.copyComponent(oldItem, newItem, DataComponentTypes.ENCHANTMENTS,
										 component -> filterEnchantments(component, newItem, configuration));
	}

	public static ItemEnchantments filterEnchantments(ItemEnchantments component, ItemStack newItem, CreativeItemFilterConfiguration configuration) {
		if(component == null) {
			return null;
		}

		int enchantmentMaxLevel = configuration.getEnchantmentMaxLevel();

		ItemEnchantments.Builder newComponent =  ItemEnchantments.itemEnchantments();

		component.enchantments().entrySet().stream()
				.filter(entry ->
								(entry.getValue() > 0) && (entry.getValue() <= enchantmentMaxLevel))
				.filter(configuration.getEnchantmentRemoveUnapplicableEnabled() ? entry -> entry.getKey().canEnchantItem(newItem) : entry -> true)
				.limit(configuration.getEnchantmentMaxCount())
				.forEach(entry -> newComponent.add(entry.getKey(), entry.getValue()));

		return newComponent.build();
	}
}
