package org.hurricanegames.creativeitemfilter.handler.meta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.inventory.meta.ItemMeta;

public final class MetaCopierFactory {
	private final List<MetaCopier<? extends ItemMeta>> copiers = new ArrayList<>();
	private final Map<Class<? extends ItemMeta>, List<MetaCopier<ItemMeta>>> cache = new HashMap<>();

	public MetaCopierFactory() {
		addCopier(LeatherArmorMetaCopier.INSTANCE);
		addCopier(CompassMetaCopier.INSTANCE); // lodestone_tracker
		addCopier(TropicalFishBucketMetaCopier.INSTANCE);
		addCopier(BannerMetaCopier.INSTANCE); // banner_patterns
		addCopier(MapMetaCopier.INSTANCE); // map_id, map_decorations, map_color
		addCopier(EnchantmentStorageMetaCopier.INSTANCE); // stored_enchants
		addCopier(WrittenBookMetaCopier.INSTANCE); // written_book_contents
		addCopier(WritableBookMetaCopier.INSTANCE); // writable_book_contents
		addCopier(KnowledgeBookMetaCopier.INSTANCE); // recipes
		addCopier(PotionMetaCopier.INSTANCE); // potion_contents
		addCopier(FireworkEffectMetaCopier.INSTANCE); // firework_explosion
		addCopier(FireworkMetaCopier.INSTANCE); // fireworks
		addCopier(InstrumentMetaCopier.INSTANCE); // instrument
		addCopier(OminousBottleMetaCopier.INSTANCE); // ominous_bottle_amplifier
		addCopier(LightBlockMetaCopier.INSTANCE); // Light block levels
		addCopier(NoOpMetaCopier.INSTANCE);
	}

	@SuppressWarnings("unchecked")
	public <T extends ItemMeta> void addCopier(MetaCopier<T> copier) {
		copiers.add(copier);

		cache.forEach((metaClass, value) -> {
			if(metaClass.isAssignableFrom(copier.getMetaClass())) {
				value.add((MetaCopier<ItemMeta>) copier);
			}
		});
	}

	public <T extends ItemMeta> void removeCopier(MetaCopier<T> copier) {
		copiers.remove(copier);

		cache.forEach((metaClass, value) -> {
			value.remove(copier);
		});
	}

	@SuppressWarnings("unchecked")
	public <T extends ItemMeta> List<MetaCopier<ItemMeta>> getCopiers(T oldMeta) {
		cache.computeIfAbsent(oldMeta.getClass(), (metaClass) -> {
			List<MetaCopier<ItemMeta>> result = new ArrayList<>();

			copiers.forEach((copier) -> {
				if(copier.getMetaClass().isAssignableFrom(oldMeta.getClass())) {
					result.add((MetaCopier<ItemMeta>) copier);
				}
			});

			return result;
		});

		return cache.get(oldMeta.getClass());
	}
}
