package org.hurricanegames.creativeitemfilter.handler.component;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.WrittenBookContent;
import io.papermc.paper.text.Filtered;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;
import org.hurricanegames.creativeitemfilter.utils.ChatComponentUtils;
import org.hurricanegames.creativeitemfilter.utils.ItemComponentUtils;
import org.hurricanegames.creativeitemfilter.utils.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public class WrittenBookComponentPopulator implements ItemComponentPopulator {
	@Override
	public void populateComponents(@NotNull ItemStack oldItem, @NotNull ItemStack newItem,
								   CreativeItemFilterConfiguration configuration) {
		if(oldItem.getType() != Material.WRITTEN_BOOK) {
			return;
		}

		ItemComponentUtils.copyComponent(oldItem, newItem, DataComponentTypes.WRITTEN_BOOK_CONTENT,
										 component -> filterWrittenBook(component, configuration));
	}

	public static WrittenBookContent filterWrittenBook(WrittenBookContent book, CreativeItemFilterConfiguration configuration) {
		if(book == null) {
			return null;
		}

		Filtered<String> title = Filtered.of(
				StringUtils.clampString(book.title().raw(), configuration.getBookTitleMaxLength()),
				book.title().filtered() != null ?
						StringUtils.clampString(book.title().filtered(), configuration.getBookTitleMaxLength()) : null);
		String author = StringUtils.clampString(book.author(), configuration.getBookAuthorMaxLength());

		List<Component> pages = book.pages().stream()
				.map(Filtered::raw) //FIXME: Handle filtered properly when paper fix the getter type
				.filter(page ->
								ChatComponentUtils.validateComponent(page, configuration.getBookPagesMaxLength()))
				.map(page -> filterPage(page, configuration))
				.limit(configuration.getBookPagesMaxCount())
				.toList();

		return WrittenBookContent.writtenBookContent(title, author)
				.generation(book.generation())
				.addPages(pages)
				.build();
	}

	private static Component filterPage(Component page, CreativeItemFilterConfiguration configuration) {
		return ChatComponentUtils.validateComponent(page, configuration.getBookPagesMaxLength()) ? page : null;
	}
}
