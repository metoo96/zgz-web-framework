package com.zhuguozhu.framework.util;

import org.apache.commons.lang3.StringUtils;

/**
 * �ַ���������
 * @author Guozhu Zhu
 * @date 2019/06/27
 * @version 1.0
 *
 */
public final class StringUtil {
	
	/**
	 * �ַ����ָ��
	 */
	public static final String SEPARATOR = String.valueOf((char)29);
	
	/**
	 * �ж��ַ����Ƿ�Ϊ��
	 */
	public static boolean isEmpty(String str) {
		if (str != null) {
			str = str.trim();
		}
		return StringUtils.isEmpty(str);
	}
	
	/**
	 * �ж��ַ����Ƿ�ǿ�
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

}
