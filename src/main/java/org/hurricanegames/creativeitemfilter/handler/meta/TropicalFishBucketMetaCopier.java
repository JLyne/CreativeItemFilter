package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.inventory.meta.TropicalFishBucketMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;

//TODO: Move to component populator once the bucket entity data api exists
public class TropicalFishBucketMetaCopier implements MetaCopier<TropicalFishBucketMeta> {
	public static final TropicalFishBucketMetaCopier INSTANCE = new TropicalFishBucketMetaCopier();

	@Override
	public void copyValidMeta(CreativeItemFilterConfiguration configuration, TropicalFishBucketMeta oldMeta, TropicalFishBucketMeta newMeta) {
		if (oldMeta.hasPatternColor()) {
			newMeta.setPatternColor(oldMeta.getPatternColor());
		}

		if (oldMeta.hasPattern()) {
			newMeta.setPattern(oldMeta.getPattern());
		}

		if (oldMeta.hasBodyColor()) {
			newMeta.setBodyColor(oldMeta.getBodyColor());
		}
	}

	public Class<TropicalFishBucketMeta> getMetaClass() {
		return TropicalFishBucketMeta.class;
	}
}
