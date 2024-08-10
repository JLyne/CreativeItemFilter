package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.inventory.meta.MusicInstrumentMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;

public class InstrumentMetaCopier implements MetaCopier<MusicInstrumentMeta> {

	public static final InstrumentMetaCopier INSTANCE = new InstrumentMetaCopier();

	protected InstrumentMetaCopier() {
	}

	@Override
	public void copyValidMeta(CreativeItemFilterConfiguration configuration, MusicInstrumentMeta oldMeta, MusicInstrumentMeta newMeta) {
		newMeta.setInstrument(oldMeta.getInstrument());
	}

	public Class<MusicInstrumentMeta> getMetaClass() {
		return MusicInstrumentMeta.class;
	}
}
