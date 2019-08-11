package com.zhuguozhu.framework.util;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

/**
 * ���Ϲ�����
 * @author Guozhu Zhu
 * @date 2019/06/29
 * @version 1.0
 *
 */
public final class CollectionUtil {
	
	/**
	 * �жϼ����Ƿ�Ϊ��
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return CollectionUtils.isEmpty(collection);
	}
	
	/**
	 * �жϼ����Ƿ�ǿ�
	 */
	public static boolean isNotEmpty(Collection<?> collection) {
		return !isEmpty(collection);
	}
	
	/**
	 * �ж�Map�Ƿ�Ϊ��
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		return MapUtils.isEmpty(map);
	}
	
	/**
	 * �ж�Map�Ƿ�ǿ�
	 */
	public static boolean isNotEmpty(Map<?, ?> map) {
		return !isEmpty(map);
	}
	
}
