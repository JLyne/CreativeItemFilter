package org.hurricanegames.creativeitemfilter.handler.component;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.WritableBookContent;
import io.papermc.paper.text.Filtered;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;
import org.hurricanegames.creativeitemfilter.utils.ItemComponentUtils;
import org.hurricanegames.creativeitemfilter.utils.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

@SuppressWarnings("UnstableApiUsage")
public class WritableBookComponentPopulator implements ItemComponentPopulator {
	@Override
	public void populateComponents(@NotNull ItemStack oldItem, @NotNull ItemStack newItem,
								   CreativeItemFilterConfiguration configuration) {
		if(oldItem.getType() != Material.WRITABLE_BOOK) {
			return;
		}

		ItemComponentUtils.copyComponent(oldItem, newItem, DataComponentTypes.WRITABLE_BOOK_CONTENT,
										 component -> filterWritableBook(component, configuration));
	}

	public static WritableBookContent filterWritableBook(WritableBookContent book, CreativeItemFilterConfiguration configuration) {
		if (book == null) {
			return null;
		}

		return WritableBookContent.writeableBookContent().addFilteredPages(
				book.pages().stream()
						.map(page -> filterPage(page, configuration))
						.limit(configuration.getBookPagesMaxCount())
						.collect(Collectors.toList())).build();
	}

	private static Filtered<String> filterPage(Filtered<String> page, CreativeItemFilterConfiguration configuration) {
		return Filtered.of(StringUtils.clampString(page.raw(), configuration.getBookPagesMaxLength()),
						   page.filtered() != null ?
								   StringUtils.clampString(page.filtered(), configuration.getBookPagesMaxLength()) : null
		);
	}
}
