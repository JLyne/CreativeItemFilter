package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.inventory.meta.ItemMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;

public class NoOpMetaCopier implements MetaCopier<ItemMeta> {

	public static final NoOpMetaCopier INSTANCE = new NoOpMetaCopier();

	private NoOpMetaCopier() {
	}

	@Override
	public void copyValidMeta(CreativeItemFilterConfiguration configuration, ItemMeta oldMeta, ItemMeta newMeta) {
	}

	public Class<ItemMeta> getMetaClass() {
		return ItemMeta.class;
	}
}
