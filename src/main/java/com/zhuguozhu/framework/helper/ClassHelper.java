package com.zhuguozhu.framework.helper;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import com.zhuguozhu.framework.annotation.Controller;
import com.zhuguozhu.framework.annotation.Service;
import com.zhuguozhu.framework.util.ClassUtil;

/**
 * �����������
 * @author Guozhu Zhu
 * @date 2019/07/03
 * @version 1.0
 *
 */
public final class ClassHelper {
    
	/**
	 * �����༯��
	 */
	private static final Set<Class<?>> CLASS_SET;
	
	static {
		String basePackage = ConfigHelper.getAppBasePackage();
	    CLASS_SET = ClassUtil.getClassSet(basePackage);
	    System.out.println("[info]::CLASS_SET::" + CLASS_SET);   
	}
	
	/**
	 * ��ȡӦ�ð����µ�������
	 */
	public static Set<Class<?>> getClassSet() {
		return CLASS_SET;
	}
	
	/**
	 * ��ȡӦ�ð���������Service��
	 */
	public static Set<Class<?>> getServiceClassSet() {
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for (Class<?> cls : CLASS_SET) {
			if (cls.isAnnotationPresent(Service.class)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	/**
	 * ��ȡӦ�ð���������Controller��
	 */
	public static Set<Class<?>> getControllerClassSet() {
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for (Class<?> cls : CLASS_SET) {
			if (cls.isAnnotationPresent(Controller.class)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	/**
	 * ��ȡӦ�ð��������е�Bean�ࣨ����: Service, Controller��)
	 * ����Controllerע����Serviceע����࣬���Ϊ�ɿ���������Bean.
	 */
	public static Set<Class<?>> getBeanClassSet() {
		Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
		beanClassSet.addAll(getServiceClassSet());
		beanClassSet.addAll(getControllerClassSet());
		return beanClassSet;
	}
	
	// AOP
	/**
	 * ��ȡӦ�ð�����ĳ���ࣨ��ӿڣ����������ࣨ��ʵ���ࣩ
	 */
	public static Set<Class<?>> getClassSetBySuper(Class<?> superClass) {
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for (Class<?> cls : CLASS_SET) {
			if (superClass.isAssignableFrom(cls) && !superClass.equals(cls)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	// AOP
	/**
	 * ��ȡӦ�ð����´���ĳע���������
	 */
	public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass) {
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for (Class<?> cls : CLASS_SET) {
			if (cls.isAnnotationPresent(annotationClass)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
}
