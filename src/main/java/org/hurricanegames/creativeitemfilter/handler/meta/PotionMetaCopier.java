package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.inventory.meta.PotionMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;

public class PotionMetaCopier implements MetaCopier<PotionMeta> {

	public static final PotionMetaCopier INSTANCE = new PotionMetaCopier();

	protected PotionMetaCopier() {
	}

	@Override
	public void copyValidMeta(CreativeItemFilterConfiguration configuration, PotionMeta oldMeta, PotionMeta newMeta) {
		newMeta.setColor(oldMeta.getColor());
		newMeta.setBasePotionType(oldMeta.getBasePotionType());
		if (oldMeta.hasCustomEffects()) {
			int maxAmplifier = configuration.getPotionEffectsMaxAmplifier();
			long maxDuration = configuration.getPotionEffectsMaxAmplifier();
			oldMeta.getCustomEffects().stream()
			.filter(effect -> effect.getAmplifier() < maxAmplifier)
			.filter(effect -> effect.getDuration() < maxDuration)
			.limit(configuration.getPotionEffectsMaxCount())
			.forEach(effect -> newMeta.addCustomEffect(effect, true));
		}
	}

	public Class<PotionMeta> getMetaClass() {
		return PotionMeta.class;
	}
}
