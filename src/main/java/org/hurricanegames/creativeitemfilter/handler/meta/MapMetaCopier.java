package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.inventory.meta.MapMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;

public class MapMetaCopier implements MetaCopier<MapMeta> {
	public static final MapMetaCopier INSTANCE = new MapMetaCopier();

	@Override
	public void copyValidMeta(CreativeItemFilterConfiguration configuration, MapMeta oldMeta, MapMeta newMeta) {
		if(oldMeta.hasMapView()) { // map_id (and map_decorations?) component
			newMeta.setMapView(oldMeta.getMapView());
		}

		newMeta.setScaling(oldMeta.isScaling());

		if(oldMeta.hasColor()) { // map_color
			newMeta.setColor(oldMeta.getColor());
		}
	}

	public Class<MapMeta> getMetaClass() {
		return MapMeta.class;
	}
}
