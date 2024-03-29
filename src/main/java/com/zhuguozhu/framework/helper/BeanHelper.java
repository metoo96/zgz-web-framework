package com.zhuguozhu.framework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.zhuguozhu.framework.util.ReflectionUtil;

/**
 * Bean助手类
 * @author Guozhu Zhu
 * @date 2019/07/08
 * @version 1.0
 *
 */
public final class BeanHelper {
    
	/**
	 * 定义Bean映射（用于存放Bean类与Bean实例的映射关系)
	 */
	private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();
	
	static {
		Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
		for (Class<?> beanClass : beanClassSet) {
			Object obj = ReflectionUtil.newInstance(beanClass);
			BEAN_MAP.put(beanClass, obj);
		}
		System.out.println("[info]::BeanHelper" + BEAN_MAP.size());
	}
	
	/**
	 * 获取Bean映射
	 */
	public static Map<Class<?>, Object> getBeanMap() {
		return BEAN_MAP;
	}
	
	/**
	 * 获取Bean实例
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> cls) {
		if (!BEAN_MAP.containsKey(cls)) {
            throw new RuntimeException("can not get been by class!!!" + cls);
		}
		return (T) BEAN_MAP.get(cls);
	}
	
	/**
	 * 设置Bean实例
	 */
	public static void setBean(Class<?> cls, Object obj) {
		BEAN_MAP.put(cls, obj);
	}
	
}
