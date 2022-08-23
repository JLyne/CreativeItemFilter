package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.inventory.meta.*;
import org.hurricanegames.creativeitemfilter.utils.CachedInstanceOfChain;

public class MetaCopierFactory {

	private final CachedInstanceOfChain<MetaCopier<? extends ItemMeta>> copiers = new CachedInstanceOfChain<>();

	public MetaCopierFactory() {
		copiers.setKnownPath(LeatherArmorMeta.class, LeatherArmorMetaCopier.INSTANCE);
		copiers.setKnownPath(CompassMeta.class, CompassMetaCopier.INSTANCE);
		copiers.setKnownPath(TropicalFishBucketMeta.class, TropicalFishBucketMetaCopier.INSTANCE);
		copiers.setKnownPath(BannerMeta.class, BannerMetaCopier.INSTANCE);
		copiers.setKnownPath(MapMeta.class, MapMetaCopier.INSTANCE);
		copiers.setKnownPath(EnchantmentStorageMeta.class, EnchantmentStorageMetaCopier.INSTANCE);
		copiers.setKnownPath(BookMeta.class, BookMetaCopier.INSTANCE);
		copiers.setKnownPath(KnowledgeBookMeta.class, KnowledgeBookMetaCopier.INSTANCE);
		copiers.setKnownPath(PotionMeta.class, PotionMetaCopier.INSTANCE);
		copiers.setKnownPath(FireworkEffectMeta.class, FireworkEffectMetaCopier.INSTANCE);
		copiers.setKnownPath(FireworkMeta.class, FireworkMetaCopier.INSTANCE);
		copiers.setKnownPath(ItemMeta.class, NoOpMetaCopier.INSTANCE);
	}

	@SuppressWarnings("unchecked")
	public MetaCopier<ItemMeta> getCopier(ItemMeta oldMeta) {
		return (MetaCopier<ItemMeta>) copiers.selectPath(oldMeta.getClass());
	}

}
