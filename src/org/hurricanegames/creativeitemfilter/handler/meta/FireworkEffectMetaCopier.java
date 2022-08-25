package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.FireworkEffect;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;
import org.hurricanegames.creativeitemfilter.utils.CollectionUtils;

public class FireworkEffectMetaCopier implements MetaCopier<FireworkEffectMeta> {

	public static final FireworkEffectMetaCopier INSTANCE = new FireworkEffectMetaCopier();

	protected FireworkEffectMetaCopier() {
	}

	@Override
	public void copyValidMeta(CreativeItemFilterConfiguration configuration, FireworkEffectMeta oldMeta, FireworkEffectMeta newMeta) {
		if (oldMeta.hasEffect()) {
			newMeta.setEffect(copyValidEffect(configuration, oldMeta.getEffect()));
		}
	}

	public static FireworkEffect copyValidEffect(CreativeItemFilterConfiguration configuration, FireworkEffect oldEffect) {
		int maxColors = configuration.getFireworkEffectColorsMaxCount();
		return
			FireworkEffect.builder()
			.flicker(oldEffect.hasFlicker())
			.trail(oldEffect.hasTrail())
			.withColor(CollectionUtils.clampList(oldEffect.getColors(), maxColors))
			.withFade(CollectionUtils.clampList(oldEffect.getFadeColors(), maxColors))
			.with(oldEffect.getType())
			.build();
	}

	public Class<FireworkEffectMeta> getMetaClass() {
		return FireworkEffectMeta.class;
	}
}
