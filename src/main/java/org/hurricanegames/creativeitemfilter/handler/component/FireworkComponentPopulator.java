package org.hurricanegames.creativeitemfilter.handler.component;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.Fireworks;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;
import org.hurricanegames.creativeitemfilter.utils.CollectionUtils;
import org.hurricanegames.creativeitemfilter.utils.ItemComponentUtils;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

@SuppressWarnings("UnstableApiUsage")
public final class FireworkComponentPopulator implements ItemComponentPopulator {
	@Override
	public void populateComponents(
			@NotNull ItemStack oldItem, @NotNull ItemStack newItem, CreativeItemFilterConfiguration configuration) {
		if(oldItem.getType() == Material.FIREWORK_STAR) {
			ItemComponentUtils.copyComponent(oldItem, newItem, DataComponentTypes.FIREWORK_EXPLOSION,
											 value -> filterEffect(configuration, value));
		} else if(oldItem.getType() == Material.FIREWORK_ROCKET) {
			ItemComponentUtils.copyComponent(oldItem, newItem, DataComponentTypes.FIREWORKS,
											 value -> filterFireworks(configuration, value));
		}
	}

	public static Fireworks filterFireworks(CreativeItemFilterConfiguration configuration, Fireworks fireworks) {
		if(fireworks == null) {
			return null;
		}

		Fireworks.Builder builder = Fireworks.fireworks();
		builder.flightDuration(Math.min(fireworks.flightDuration(), configuration.getFireworkMaxPower()));
		builder.addEffects(fireworks.effects().stream()
								   .limit(configuration.getFireworkMaxEffects())
								   .map(effect -> filterEffect(configuration, effect))
								   .collect(Collectors.toList()));
		return builder.build();
	}

	public static FireworkEffect filterEffect(CreativeItemFilterConfiguration configuration, FireworkEffect effect) {
		if(effect == null) {
			return null;
		}

		int maxColors = configuration.getFireworkEffectColorsMaxCount();
		return
			FireworkEffect.builder()
			.flicker(effect.hasFlicker())
			.trail(effect.hasTrail())
			.withColor(CollectionUtils.clampList(effect.getColors(), maxColors))
			.withFade(CollectionUtils.clampList(effect.getFadeColors(), maxColors))
			.with(effect.getType())
			.build();
	}
}
