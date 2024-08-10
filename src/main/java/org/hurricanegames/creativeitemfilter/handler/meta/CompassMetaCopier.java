package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.inventory.meta.CompassMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;

public class CompassMetaCopier implements MetaCopier<CompassMeta> {
	public static final CompassMetaCopier INSTANCE = new CompassMetaCopier();

	@Override
	public void copyValidMeta(CreativeItemFilterConfiguration configuration, CompassMeta oldMeta, CompassMeta newMeta) {
		newMeta.setLodestoneTracked(oldMeta.isLodestoneTracked());
		if (oldMeta.hasLodestone()) {
			newMeta.setLodestone(oldMeta.getLodestone());
		}
	}

	public Class<CompassMeta> getMetaClass() {
		return CompassMeta.class;
	}
}
