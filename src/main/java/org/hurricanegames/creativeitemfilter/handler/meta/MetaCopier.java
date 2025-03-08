package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;


public interface MetaCopier<T extends ItemMeta> {

	default void copyValidMeta(CreativeItemFilterConfiguration configuration, T oldMeta, T newMeta, Material material) {
		copyValidMeta(configuration, oldMeta, newMeta);
	}

	default void copyValidMeta(CreativeItemFilterConfiguration configuration, T oldMeta, T newMeta) {

	}

	Class<T> getMetaClass();
}
