package org.hurricanegames.creativeitemfilter.handler.component;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.PotionContents;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;
import org.hurricanegames.creativeitemfilter.utils.ItemComponentUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public class PotionComponentPopulator implements ItemComponentPopulator {
	private static final List<Material> potions = List.of(Material.POTION, Material.SPLASH_POTION,
														  Material.LINGERING_POTION, Material.TIPPED_ARROW);

	@Override
	public void populateComponents(@NotNull ItemStack oldItem, @NotNull ItemStack newItem,
								   CreativeItemFilterConfiguration configuration) {
		if(!potions.contains(oldItem.getType())) {
			return;
		}

		ItemComponentUtils.copyComponent(oldItem, newItem, DataComponentTypes.POTION_CONTENTS,
										 component -> filterPotion(component, configuration));
	}

	private static PotionContents filterPotion(PotionContents potion, CreativeItemFilterConfiguration configuration) {
		if(potion == null) {
			return null;
		}

		List<PotionEffect> effects = potion.customEffects().stream()
				.filter(effect -> effect.getAmplifier() < configuration.getPotionEffectsMaxAmplifier())
				.filter(effect -> effect.getDuration() < configuration.getPotionEffectsMaxDuration())
				.limit(configuration.getPotionEffectsMaxCount())
				.toList();

		return PotionContents.potionContents()
				.potion(potion.potion())
				.customColor(potion.customColor())
				.customName(potion.customName())
				.addCustomEffects(effects)
				.build();
	}
}
