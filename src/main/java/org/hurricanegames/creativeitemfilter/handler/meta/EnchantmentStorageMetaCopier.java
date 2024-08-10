package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;

public class EnchantmentStorageMetaCopier implements MetaCopier<EnchantmentStorageMeta> {
	public static final EnchantmentStorageMetaCopier INSTANCE = new EnchantmentStorageMetaCopier();

	@Override
	public void copyValidMeta(CreativeItemFilterConfiguration configuration, EnchantmentStorageMeta oldMeta, EnchantmentStorageMeta newMeta) {
		if (oldMeta.hasStoredEnchants()) {
			int enchantmentMaxLevel = configuration.getEnchantmentBookMaxLevel();
			oldMeta.getStoredEnchants().entrySet().stream()
			.filter(entry -> (entry.getValue() > 0) && (entry.getValue() <= enchantmentMaxLevel))
			.limit(configuration.getEnchantmentBookMaxCount())
			.forEach(entry -> newMeta.addStoredEnchant(entry.getKey(), entry.getValue(), true));
		}
	}

	public Class<EnchantmentStorageMeta> getMetaClass() {
		return EnchantmentStorageMeta.class;
	}
}
