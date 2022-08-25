package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;

public class LeatherArmorMetaCopier implements MetaCopier<LeatherArmorMeta> {

	public static final LeatherArmorMetaCopier INSTANCE = new LeatherArmorMetaCopier();

	protected LeatherArmorMetaCopier() {
	}

	@Override
	public void copyValidMeta(CreativeItemFilterConfiguration configuration, LeatherArmorMeta oldMeta, LeatherArmorMeta newMeta) {
		newMeta.setColor(oldMeta.getColor());
	}

	public Class<LeatherArmorMeta> getMetaClass() {
		return LeatherArmorMeta.class;
	}
}
