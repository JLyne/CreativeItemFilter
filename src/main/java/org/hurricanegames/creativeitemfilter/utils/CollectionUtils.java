package org.hurricanegames.creativeitemfilter.utils;

import java.util.List;

public class CollectionUtils {

	public static <T> List<T> clampList(List<T> list, int maxSize) {
		return list.size() <= maxSize ? list : list.subList(0, maxSize);
	}

}
