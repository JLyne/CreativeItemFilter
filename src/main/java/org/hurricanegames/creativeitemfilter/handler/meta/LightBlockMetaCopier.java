package org.hurricanegames.creativeitemfilter.handler.meta;

import org.bukkit.Material;
import org.bukkit.block.data.type.Light;
import org.bukkit.inventory.meta.BlockDataMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;

public class LightBlockMetaCopier implements MetaCopier<BlockDataMeta> {
	public static final LightBlockMetaCopier INSTANCE = new LightBlockMetaCopier();

	@Override
	public void copyValidMeta(CreativeItemFilterConfiguration configuration, BlockDataMeta oldMeta, BlockDataMeta newMeta, Material material) {
		if(!material.equals(Material.LIGHT)) {
			return;
		}

		if(oldMeta.hasBlockData()) {
			if(oldMeta.getBlockData(Material.LIGHT) instanceof Light light) {
				int level = light.getLevel();
				Light blockData = (Light) Material.LIGHT.createBlockData();
				blockData.setLevel(level);
				newMeta.setBlockData(blockData);
			};
		}
	}

	public Class<BlockDataMeta> getMetaClass() {
		return BlockDataMeta.class;
	}
}
