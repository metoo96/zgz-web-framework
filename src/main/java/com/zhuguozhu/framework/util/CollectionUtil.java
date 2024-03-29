package com.zhuguozhu.framework.util;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

/**
 * 集合工具类
 * @author Guozhu Zhu
 * @date 2019/06/29
 * @version 1.0
 *
 */
public final class CollectionUtil {
	
	/**
	 * 判断集合是否为空
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return CollectionUtils.isEmpty(collection);
	}
	
	/**
	 * 判断集合是否非空
	 */
	public static boolean isNotEmpty(Collection<?> collection) {
		return !isEmpty(collection);
	}
	
	/**
	 * 判断Map是否为空
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		return MapUtils.isEmpty(map);
	}
	
	/**
	 * 判断Map是否非空
	 */
	public static boolean isNotEmpty(Map<?, ?> map) {
		return !isEmpty(map);
	}
	
}
