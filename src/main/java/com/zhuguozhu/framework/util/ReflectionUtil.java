package com.zhuguozhu.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ʵ��Bean����
 * @author Guozhu Zhu
 * @date 2019/07/06
 * @version 1.0
 *
 */
public class ReflectionUtil {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);
	
	/**
	 * ����ʵ��
	 */
	public static Object newInstance(Class<?> cls) {
		Object instance;
		try {
			instance = cls.newInstance();
			System.out.println("[info]::new instance ok!!!");
		} catch (Exception e) {
			LOGGER.error("new instance failure!!!", e);
			throw new RuntimeException(e);
		}
		return instance;
	}
	
	/**
	 * ���÷���
	 */
	public static Object invokeMethod(Object obj, Method method, Object...args) {
		Object result;
		try {
			method.setAccessible(true);
			result = method.invoke(obj, args);
		} catch (Exception e) {
			LOGGER.error("invoke method failure!!!", e);
			throw new RuntimeException(e);
		}
		return result;
	}
	
	/**
	 * ���ó�Ա������ֵ
	 */
	public static void setField(Object obj, Field field, Object value) {
		try {
			field.setAccessible(true);
			field.set(obj, value);
		} catch (Exception e) {
			LOGGER.error("set field failure!!!", e);
			throw new RuntimeException(e);
		}
	}
	
}
