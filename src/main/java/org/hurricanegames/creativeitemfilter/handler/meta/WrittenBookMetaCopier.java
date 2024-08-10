package org.hurricanegames.creativeitemfilter.handler.meta;

import java.util.stream.Collectors;

import org.bukkit.inventory.meta.BookMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilter;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;

public class WrittenBookMetaCopier implements MetaCopier<BookMeta> {

	public static final WrittenBookMetaCopier INSTANCE = new WrittenBookMetaCopier();

	protected WrittenBookMetaCopier() {
	}

	@Override
	public void copyValidMeta(CreativeItemFilterConfiguration configuration, BookMeta oldMeta, BookMeta newMeta) {
		if (oldMeta.hasAuthor()
				&& CreativeItemFilter.plain.serialize(oldMeta.author()).length() <= configuration.getBookAuthorMaxLength()) {
			newMeta.author(oldMeta.author());
		}

		if (oldMeta.hasTitle()
				&& CreativeItemFilter.plain.serialize(oldMeta.title()).length() <= configuration.getBookTitleMaxLength()) {
			newMeta.title(oldMeta.title());
		}

		if (oldMeta.hasGeneration()) {
			newMeta.setGeneration(oldMeta.getGeneration());
		}

		if (oldMeta.hasPages()) {
			int bookPagesMaxLength = configuration.getBookPagesMaxLength();
			newMeta.pages(
				oldMeta.pages().stream()
				.filter(page -> CreativeItemFilter.plain.serialize(page).length() <= bookPagesMaxLength)
				.limit(configuration.getBookPagesMaxCount())
				.collect(Collectors.toList())
			);
		}
	}

	public Class<BookMeta> getMetaClass() {
		return BookMeta.class;
	}
}
