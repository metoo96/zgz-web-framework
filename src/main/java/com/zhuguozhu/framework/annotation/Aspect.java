package com.zhuguozhu.framework.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义切面注解
 * @author Guozhu Zhu
 * @date 2019/07/18
 * @version 1.0
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

	/**
	 * 注解
	 */
	Class<? extends Annotation> value();
	
}
