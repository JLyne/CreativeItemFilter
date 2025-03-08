package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.inventory.meta.TropicalFishBucketMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;

//TODO: Move to component populator once the bucket entity data api exists
public class TropicalFishBucketMetaCopier implements MetaCopier<TropicalFishBucketMeta> {
	public static final TropicalFishBucketMetaCopier INSTANCE = new TropicalFishBucketMetaCopier();

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
