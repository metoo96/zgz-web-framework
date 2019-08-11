package com.zhuguozhu.framework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * ���鹤����
 * @author Guozhu Zhu
 * @date 2019/07/09
 * @version 1.0
 *
 */
public class ArrayUtil {
    
	/**
	 * �ж������Ƿ�ǿ�
	 */
	public static boolean isNotEmpty(Object[] array) {
		return !ArrayUtils.isEmpty(array);
	}
	
	/**
	 * �ж������Ƿ�Ϊ��
	 */
	public static boolean isEmpty(Object[] array) {
		return ArrayUtils.isEmpty(array);
	}
	
}
