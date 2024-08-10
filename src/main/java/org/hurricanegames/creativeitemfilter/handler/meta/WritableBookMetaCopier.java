package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.inventory.meta.WritableBookMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;
import org.hurricanegames.creativeitemfilter.utils.StringUtils;

import java.util.stream.Collectors;

public class WritableBookMetaCopier implements MetaCopier<WritableBookMeta> {

	public static final WritableBookMetaCopier INSTANCE = new WritableBookMetaCopier();

	protected WritableBookMetaCopier() {
	}

	@Override
	public void copyValidMeta(CreativeItemFilterConfiguration configuration, WritableBookMeta oldMeta, WritableBookMeta newMeta) {
		if (oldMeta.hasPages()) {
			int bookPagesMaxLength = configuration.getBookPagesMaxLength();
			newMeta.setPages(
				oldMeta.getPages().stream()
				.map(page -> StringUtils.clampString(page, bookPagesMaxLength))
				.limit(configuration.getBookPagesMaxCount())
				.collect(Collectors.toList())
			);
		}
	}

	public Class<WritableBookMeta> getMetaClass() {
		return WritableBookMeta.class;
	}
}
