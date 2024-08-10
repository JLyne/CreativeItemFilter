package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.inventory.meta.OminousBottleMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;

public class OminousBottleMetaCopier implements MetaCopier<OminousBottleMeta> {

	public static final OminousBottleMetaCopier INSTANCE = new OminousBottleMetaCopier();

	protected OminousBottleMetaCopier() {
	}

	@Override
	public void copyValidMeta(CreativeItemFilterConfiguration configuration, OminousBottleMeta oldMeta, OminousBottleMeta newMeta) {
		if (oldMeta.hasAmplifier()) {
			newMeta.setAmplifier(oldMeta.getAmplifier());
		}
	}

	public Class<OminousBottleMeta> getMetaClass() {
		return OminousBottleMeta.class;
	}
}
