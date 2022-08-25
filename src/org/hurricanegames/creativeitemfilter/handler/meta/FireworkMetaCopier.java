package org.hurricanegames.creativeitemfilter.handler.meta;

import java.util.stream.Collectors;

import org.bukkit.inventory.meta.FireworkMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;

public class FireworkMetaCopier implements MetaCopier<FireworkMeta> {

	public static final FireworkMetaCopier INSTANCE = new FireworkMetaCopier();

	protected FireworkMetaCopier() {
	}

	@Override
	public void copyValidMeta(CreativeItemFilterConfiguration configuration, FireworkMeta oldMeta, FireworkMeta newMeta) {
		int oldPower = oldMeta.getPower();
		if ((oldPower > 0) && (oldPower <= configuration.getFireworkMaxPower())) {
			newMeta.setPower(oldPower);
		}
		if (oldMeta.hasEffects()) {
			newMeta.addEffects(
				oldMeta.getEffects().stream()
				.map(effect -> FireworkEffectMetaCopier.copyValidEffect(configuration, effect))
				.limit(configuration.getFireworkMaxEffects())
				.collect(Collectors.toList())
			);
		}
	}

	public Class<FireworkMeta> getMetaClass() {
		return FireworkMeta.class;
	}
}
