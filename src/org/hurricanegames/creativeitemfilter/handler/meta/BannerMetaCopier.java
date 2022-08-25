package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.inventory.meta.BannerMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;

public class BannerMetaCopier implements MetaCopier<BannerMeta> {

	public static final BannerMetaCopier INSTANCE = new BannerMetaCopier();

	protected BannerMetaCopier() {
	}

	@Override
	public void copyValidMeta(CreativeItemFilterConfiguration configuration, BannerMeta oldMeta, BannerMeta newMeta) {
		oldMeta.getPatterns().forEach(newMeta::addPattern);
	}

	public Class<BannerMeta> getMetaClass() {
		return BannerMeta.class;
	}
}
