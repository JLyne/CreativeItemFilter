package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.inventory.meta.TropicalFishBucketMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;

public class TropicalFishBucketMetaCopier implements MetaCopier<TropicalFishBucketMeta> {

	public static final TropicalFishBucketMetaCopier INSTANCE = new TropicalFishBucketMetaCopier();

	protected TropicalFishBucketMetaCopier() {
	}

	@Override
	public void copyValidMeta(CreativeItemFilterConfiguration configuration, TropicalFishBucketMeta oldMeta, TropicalFishBucketMeta newMeta) {
		if (oldMeta.hasVariant()) {
			newMeta.setPatternColor(oldMeta.getPatternColor());
			newMeta.setBodyColor(oldMeta.getBodyColor());
			newMeta.setPattern(oldMeta.getPattern());
		}
	}

	public Class<TropicalFishBucketMeta> getMetaClass() {
		return TropicalFishBucketMeta.class;
	}
}
