package com.zhuguozhu.framework.helper;

import java.lang.reflect.Field;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhuguozhu.framework.annotation.Inject;
import com.zhuguozhu.framework.util.ArrayUtil;
import com.zhuguozhu.framework.util.CollectionUtil;
import com.zhuguozhu.framework.util.ReflectionUtil;
import com.zhuguozhu.framework.util.StreamUtil;

/**
 * 依赖注入助手类
 * @author Guozhu Zhu
 * @date 2019/07/09
 * @version 1.0
 *
 */
public final class IocHelper {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(IocHelper.class);
	  
	static {
		System.out.println("[info]::Ioc Helper START!!!");
		// 获取所有的Bean类与bean实例之间的映射关系
		Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
		if (CollectionUtil.isNotEmpty(beanMap)) {
			// 遍历BeanMap
			for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
				// 从BeanMap中获取Bean类与Bean实例
				Class<?> beanClass = beanEntry.getKey();
				Object beanInstance = beanEntry.getValue();
				// 获取Bean类定义的所有成员变量
				Field[] beanFields = beanClass.getDeclaredFields();
				if (ArrayUtil.isNotEmpty(beanFields)) {
					// 遍历BeanField
					for (Field beanField : beanFields) {
						// 判断当前BeanField是否带有Inject注解
						if (beanField.isAnnotationPresent(Inject.class)) {
							// 在BeanMap中获取BeanField对应的实例
						    Class<?> beanFieldClass = beanField.getType();
						    Object beanFieldInstance = beanMap.get(beanFieldClass);
						    if (beanFieldInstance != null) {
							    // 通过反射初始化 BeanField的值
							    ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
						    }
						}
					}
				}
			}
		}
		System.out.println("[info]::Ioc Helper END!!!");		
	}
	
}
