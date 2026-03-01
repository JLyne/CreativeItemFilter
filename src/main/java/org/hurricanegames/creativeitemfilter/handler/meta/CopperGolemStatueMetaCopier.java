package org.hurricanegames.creativeitemfilter.handler.meta;

import com.destroystokyo.paper.MaterialSetTag;
import org.bukkit.Material;
import org.bukkit.block.data.type.CopperGolemStatue;
import org.bukkit.inventory.meta.BlockDataMeta;
import org.hurricanegames.creativeitemfilter.CreativeItemFilterConfiguration;

//TODO: Move to component populator once the block property api exists=
public class CopperGolemStatueMetaCopier implements MetaCopier<BlockDataMeta> {
	public static final CopperGolemStatueMetaCopier INSTANCE = new CopperGolemStatueMetaCopier();

	@Override
	public void copyValidMeta(CreativeItemFilterConfiguration configuration, BlockDataMeta oldMeta, BlockDataMeta newMeta, Material material) {
		if(!MaterialSetTag.COPPER_GOLEM_STATUES.isTagged(material)) {
			return;
		}

		if(oldMeta.hasBlockData()) {
			if(oldMeta.getBlockData(Material.COPPER_GOLEM_STATUE) instanceof CopperGolemStatue statue) {
				CopperGolemStatue.Pose pose = statue.getCopperGolemPose();
				CopperGolemStatue blockData = (CopperGolemStatue) Material.COPPER_GOLEM_STATUE.createBlockData();
				blockData.setCopperGolemPose(pose);
				newMeta.setBlockData(blockData);
			}
		}
	}

	public Class<BlockDataMeta> getMetaClass() {
		return BlockDataMeta.class;
	}
}
