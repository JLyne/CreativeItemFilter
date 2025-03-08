package org.hurricanegames.creativeitemfilter.handler.component;

import java.util.ArrayList;
import java.util.List;

public final class ItemComponentPopulatorFactory {
	private final List<ItemComponentPopulator> populators = new ArrayList<>();

	public ItemComponentPopulatorFactory() {
		addPopulator(new BasicComponentPopulator());
		addPopulator(new BannerComponentPopulator());
		addPopulator(new CompassComponentPopulator());
		addPopulator(new CustomNameComponentPopulator());
		addPopulator(new DyeableComponentPopulator());
		addPopulator(new EnchantmentsComponentPopulator());
		addPopulator(new FireworkComponentPopulator());
		addPopulator(new InstrumentComponentPopulator());
		addPopulator(new KnowledgeBookComponentPopulator());
		addPopulator(new MapComponentPopulator());
		addPopulator(new OminousBottleComponentPopulator());
		addPopulator(new PotionComponentPopulator());
		addPopulator(new StoredEnchantmentsComponentPopulator());
		addPopulator(new WritableBookComponentPopulator());
		addPopulator(new WrittenBookComponentPopulator());
	}

	public void addPopulator(ItemComponentPopulator populator) {
		populators.add(populator);
	}

	public void removePopulator(ItemComponentPopulator copier) {
		populators.remove(copier);
	}

	public List<ItemComponentPopulator> getPopulators() {
		return populators;
	}
}
