package com.zhuguozhu.framework.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.zhuguozhu.framework.annotation.Action;
import com.zhuguozhu.framework.bean.Handler;
import com.zhuguozhu.framework.bean.Request;
import com.zhuguozhu.framework.util.ArrayUtil;
import com.zhuguozhu.framework.util.CollectionUtil;

/**
 * ������������
 * @author Guozhu Zhu
 * @date 2019/07/11
 * @version 1.0
 * 
 */
public class ControllerHelper {

	/**
	 * ���ڴ�������봦������ӳ���ϵ
	 */
	private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();
	
	static {
		// ��ȡ���е�Controller��
		Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
		if (CollectionUtil.isNotEmpty(controllerClassSet)) {
			// ������ЩController��
			for (Class<?> controllerClass : controllerClassSet) {
				// ��ȡController���ж���ķ���
				Method[] methods = controllerClass.getDeclaredMethods();
				if (ArrayUtil.isNotEmpty(methods)) {
					// ������ЩController���еķ���
					for (Method method : methods) {
						// �жϵ�ǰ�����Ƿ����Actionע��
						if (method.isAnnotationPresent(Action.class)) {
							// ��Actionע���л�ȡURLӳ�����
							Action action = method.getAnnotation(Action.class);
							String mapping = action.value();
							// ��֤URLӳ�����
							if (mapping.matches("\\w+:/\\w*")) {
								String[] array = mapping.split(":");
								if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
									// ��ȡ���󷽷�������·��
									String requestMethod = array[0];
									String requestPath = array[1];
									Request request = new Request(requestMethod, requestPath);
									Handler handler = new Handler(controllerClass, method);
									// ��ʼ�� Action Map
									ACTION_MAP.put(request, handler);
								}
							}
						}
					}
				}
			}
		}
		System.out.println("[info]::ControllerHelper:" + ACTION_MAP.size());
	}
	
	/**
	 * ��ȡHandler,ͨ�����󷽷�������·�����
	 */
	public static Handler getHandler(String requestMethod, String requestPath) {
		Request request = new Request(requestMethod, requestPath);
		return ACTION_MAP.get(request);
	}
	
}
