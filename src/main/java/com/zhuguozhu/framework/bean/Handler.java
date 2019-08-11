package com.zhuguozhu.framework.bean;

import java.lang.reflect.Method;

/**
 * 封装Action信息(即请求对象处理类)
 * @author Guozhu Zhu
 * @date 2019/07/09
 * @version 1.0
 *
 */
public class Handler {
    
	/**
	 * Controller类
	 */
	private Class<?> controllerClass;
	
	/**
	 * Action方法
	 */
	private Method actionMethod;
	
	public Handler(Class<?> controllerClass, Method actionMethod) {
		this.controllerClass = controllerClass;
		this.actionMethod = actionMethod;
	}
	
	public Class<?> getControllerClass() {
		return controllerClass;
	}
	
	public Method getActionMethod() {
		return actionMethod;
	}
	
}
