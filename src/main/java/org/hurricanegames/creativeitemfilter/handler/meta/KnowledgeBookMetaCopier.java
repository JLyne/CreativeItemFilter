package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.inventory.meta.KnowledgeBookMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;
import org.hurricanegames.creativeitemfilter.utils.CollectionUtils;

public class KnowledgeBookMetaCopier implements MetaCopier<KnowledgeBookMeta> {

	public static final KnowledgeBookMetaCopier INSTANCE = new KnowledgeBookMetaCopier();

	protected KnowledgeBookMetaCopier() {
	}

	@Override
	public void copyValidMeta(CreativeItemFilterConfiguration configuration, KnowledgeBookMeta oldMeta, KnowledgeBookMeta newMeta) {
		if (oldMeta.hasRecipes()) {
			newMeta.setRecipes(CollectionUtils.clampList(oldMeta.getRecipes(), configuration.getKnowledgeBookMaxRecipes()));
		}
	}

	public Class<KnowledgeBookMeta> getMetaClass() {
		return KnowledgeBookMeta.class;
	}
}
