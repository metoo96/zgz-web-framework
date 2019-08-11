package com.zhuguozhu.framework.util;

/**
 * ����ת������������
 * @author Guozhu Zhu
 * @date 2019/06/27
 * @version 1.0
 *
 */
public final class CastUtil {
	
	/**
	 * תΪString��
	 */
	public static String castString(Object obj) {
		return CastUtil.castString(obj, "");
	}
	
	/**
	 * תΪString��(�ṩĬ��ֵ)
	 */
	public static String castString(Object obj, String defaultValue) {
		return obj != null ? String.valueOf(obj) : defaultValue;
	}
	
	/**
	 * תΪdouble��
	 */
	public static double castDouble(Object obj) {
		return CastUtil.castDouble(obj, 0);
	}
	
	/**
	 * תΪdouble��(�ṩĬ��ֵ)
	 */
	public static double castDouble(Object obj, double defaultValue) {
		double doubleValue = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			if (StringUtil.isNotEmpty(strValue)) {
				try {
					doubleValue = Double.parseDouble(strValue);
				} catch (NumberFormatException e) {
					doubleValue = defaultValue;
				}
			}
		}
		return doubleValue;
	}
	 
	/**
	 * תΪlong��
	 */
	public static long castLong(Object obj) {
		return CastUtil.castLong(obj, 0);
	}
	
	/**
	 * תΪlong�ͣ��ṩĬ��ֵ)
	 */
	public static long castLong(Object obj, long defaultValue) {
		long longValue = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			if (StringUtil.isNotEmpty(strValue)) {
				try {
					longValue = Long.parseLong(strValue);
				} catch (NumberFormatException e) {
					longValue = defaultValue;
				}
			}
		}
		return longValue;
	}
	
	/**
	 * תΪint��
	 */
	public static int castInt(Object obj) {
		return CastUtil.castInt(obj, 0);
	}
	
	/**
	 * תΪint��(�ṩĬ��ֵ)
	 */
	public static int castInt(Object obj, int defaultValue) {
		int intValue = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			if (StringUtil.isNotEmpty(strValue)) {
				try {
					intValue = Integer.parseInt(strValue);
				} catch (NumberFormatException e) {
					intValue = defaultValue;
				}
			}
		}
		return intValue;
	}
	
	/**
	 * תΪboolean��
	 */
	public static boolean castBoolean(Object obj) {
		return CastUtil.castBoolean(obj, false);
	}
	
	/**
	 * תΪboolean��(�ṩĬ��ֵ)
	 */
	public static boolean castBoolean(Object obj, boolean defaultValue) {
		boolean booleanValue = defaultValue;
		if (obj != null) {
			booleanValue = Boolean.parseBoolean(castString(obj));
		}
		return booleanValue;
	}
	
}
