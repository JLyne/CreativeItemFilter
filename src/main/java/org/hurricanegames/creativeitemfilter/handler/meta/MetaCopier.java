package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.inventory.meta.ItemMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;

public interface MetaCopier<T extends ItemMeta> {

	void copyValidMeta(CreativeItemFilterConfiguration configuration, T oldMeta, T newMeta);

	Class<T> getMetaClass();
}
