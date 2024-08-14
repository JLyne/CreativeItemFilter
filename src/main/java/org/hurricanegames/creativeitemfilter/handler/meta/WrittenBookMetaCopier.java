package org.hurricanegames.creativeitemfilter.handler.meta;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.meta.BookMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;
import org.hurricanegames.creativeitemfilter.utils.ComponentUtils;

public class WrittenBookMetaCopier implements MetaCopier<BookMeta> {
	public static final WrittenBookMetaCopier INSTANCE = new WrittenBookMetaCopier();

	@Override
	public void copyValidMeta(CreativeItemFilterConfiguration configuration, BookMeta oldMeta, BookMeta newMeta) {
		int authorMaxLength = configuration.getBookAuthorMaxLength();
		int titleMaxLength = configuration.getBookTitleMaxLength();
		int pageMaxLength = configuration.getBookPagesMaxLength();

		if (oldMeta.hasAuthor() && ComponentUtils.validateComponent(oldMeta.author(), authorMaxLength)) {
			newMeta.author(oldMeta.author());
		}

		if (oldMeta.hasTitle() && ComponentUtils.validateComponent(oldMeta.title(), titleMaxLength)) {
			newMeta.title(oldMeta.title());
		}

		if (oldMeta.hasGeneration()) {
			newMeta.setGeneration(oldMeta.getGeneration());
		}

		if (oldMeta.hasPages()) {
			newMeta.addPages(oldMeta.pages().stream()
									 .filter(page -> ComponentUtils.validateComponent(page, pageMaxLength))
									 .limit(configuration.getBookPagesMaxCount()).toArray(Component[]::new));
		}
	}

	public Class<BookMeta> getMetaClass() {
		return BookMeta.class;
	}
}
