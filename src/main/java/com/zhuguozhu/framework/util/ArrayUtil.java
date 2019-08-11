package com.zhuguozhu.framework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 数组工具类
 * @author Guozhu Zhu
 * @date 2019/07/09
 * @version 1.0
 *
 */
public class ArrayUtil {
    
	/**
	 * 判断数组是否非空
	 */
	public static boolean isNotEmpty(Object[] array) {
		return !ArrayUtils.isEmpty(array);
	}
	
	/**
	 * 判断数组是否为空
	 */
	public static boolean isEmpty(Object[] array) {
		return ArrayUtils.isEmpty(array);
	}
	
}
