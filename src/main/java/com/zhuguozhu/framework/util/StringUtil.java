package com.zhuguozhu.framework.util;

import org.apache.commons.lang3.StringUtils;

/**
 * ×Ö·û´®¹¤¾ßÀà
 * @author Guozhu Zhu
 * @date 2019/06/27
 * @version 1.0
 *
 */
public final class StringUtil {
	
	/**
	 * ×Ö·û´®·Ö¸î·û
	 */
	public static final String SEPARATOR = String.valueOf((char)29);
	
	/**
	 * ÅÐ¶Ï×Ö·û´®ÊÇ·ñÎª¿Õ
	 */
	public static boolean isEmpty(String str) {
		if (str != null) {
			str = str.trim();
		}
		return StringUtils.isEmpty(str);
	}
	
	/**
	 * ÅÐ¶Ï×Ö·û´®ÊÇ·ñ·Ç¿Õ
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

}
