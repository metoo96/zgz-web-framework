package com.zhuguozhu.framework.helper;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import com.zhuguozhu.framework.annotation.Controller;
import com.zhuguozhu.framework.annotation.Service;
import com.zhuguozhu.framework.util.ClassUtil;

/**
 * 类操作助手类
 * @author Guozhu Zhu
 * @date 2019/07/03
 * @version 1.0
 *
 */
public final class ClassHelper {
    
	/**
	 * 定义类集合
	 */
	private static final Set<Class<?>> CLASS_SET;
	
	static {
		String basePackage = ConfigHelper.getAppBasePackage();
	    CLASS_SET = ClassUtil.getClassSet(basePackage);
	    System.out.println("[info]::CLASS_SET::" + CLASS_SET);   
	}
	
	/**
	 * 获取应用包名下的所有类
	 */
	public static Set<Class<?>> getClassSet() {
		return CLASS_SET;
	}
	
	/**
	 * 获取应用包名下所有Service类
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
	 * 获取应用包名下所有Controller类
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
	 * 获取应用包名下所有的Bean类（包括: Service, Controller等)
	 * 带有Controller注解与Service注解的类，理解为由框架所管理的Bean.
	 */
	public static Set<Class<?>> getBeanClassSet() {
		Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
		beanClassSet.addAll(getServiceClassSet());
		beanClassSet.addAll(getControllerClassSet());
		return beanClassSet;
	}
	
	// AOP
	/**
	 * 获取应用包名下某父类（或接口）的所有子类（或实现类）
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
	 * 获取应用包名下带有某注解的所有类
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
