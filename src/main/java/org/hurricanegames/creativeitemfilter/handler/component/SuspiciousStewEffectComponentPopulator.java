package org.hurricanegames.creativeitemfilter.handler.component;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.SuspiciousStewEffects;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;
import org.hurricanegames.creativeitemfilter.utils.ItemComponentUtils;
import org.jetbrains.annotations.NotNull;


@SuppressWarnings("UnstableApiUsage")
public class SuspiciousStewEffectComponentPopulator implements ItemComponentPopulator {
	@Override
	public void populateComponents(@NotNull ItemStack oldItem, @NotNull ItemStack newItem,
								   CreativeItemFilterConfiguration configuration) {
		if(oldItem.getType() != Material.SUSPICIOUS_STEW) {
			return;
		}

		ItemComponentUtils.copyComponent(oldItem, newItem, DataComponentTypes.SUSPICIOUS_STEW_EFFECTS,
										 component -> filterPotion(component, configuration));
	}

	private static SuspiciousStewEffects filterPotion(SuspiciousStewEffects component, CreativeItemFilterConfiguration configuration) {
		if(component == null) {
			return null;
		}

		return SuspiciousStewEffects.suspiciousStewEffects().addAll(
				component.effects().stream()
						.filter(effect -> effect.duration() < configuration.getPotionEffectsMaxDuration())
						.limit(configuration.getPotionEffectsMaxCount())
						.toList())
				.build();
	}
}
