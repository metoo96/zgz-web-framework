package com.zhuguozhu.framework.bean;

import java.lang.reflect.Method;

/**
 * ��װAction��Ϣ(�������������)
 * @author Guozhu Zhu
 * @date 2019/07/09
 * @version 1.0
 *
 */
public class Handler {
    
	/**
	 * Controller��
	 */
	private Class<?> controllerClass;
	
	/**
	 * Action����
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
