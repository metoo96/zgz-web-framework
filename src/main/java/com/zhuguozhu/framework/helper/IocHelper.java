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
 * ����ע��������
 * @author Guozhu Zhu
 * @date 2019/07/09
 * @version 1.0
 *
 */
public final class IocHelper {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(IocHelper.class);
	  
	static {
		System.out.println("[info]::Ioc Helper START!!!");
		// ��ȡ���е�Bean����beanʵ��֮���ӳ���ϵ
		Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
		if (CollectionUtil.isNotEmpty(beanMap)) {
			// ����BeanMap
			for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
				// ��BeanMap�л�ȡBean����Beanʵ��
				Class<?> beanClass = beanEntry.getKey();
				Object beanInstance = beanEntry.getValue();
				// ��ȡBean�ඨ������г�Ա����
				Field[] beanFields = beanClass.getDeclaredFields();
				if (ArrayUtil.isNotEmpty(beanFields)) {
					// ����BeanField
					for (Field beanField : beanFields) {
						// �жϵ�ǰBeanField�Ƿ����Injectע��
						if (beanField.isAnnotationPresent(Inject.class)) {
							// ��BeanMap�л�ȡBeanField��Ӧ��ʵ��
						    Class<?> beanFieldClass = beanField.getType();
						    Object beanFieldInstance = beanMap.get(beanFieldClass);
						    if (beanFieldInstance != null) {
							    // ͨ�������ʼ�� BeanField��ֵ
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
