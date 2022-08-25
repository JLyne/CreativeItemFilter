package org.hurricanegames.creativeitemfilter.utils;

public class StringUtils {

	public static String clampString(String string, int limit) {
		return string.length() < limit ? string : string.substring(0, limit);
	}

}
