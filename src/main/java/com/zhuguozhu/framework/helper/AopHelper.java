package com.zhuguozhu.framework.helper;


import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhuguozhu.framework.annotation.Aspect;
import com.zhuguozhu.framework.annotation.Service;
import com.zhuguozhu.framework.proxy.AspectProxy;
import com.zhuguozhu.framework.proxy.Proxy;
import com.zhuguozhu.framework.proxy.ProxyManager;
import com.zhuguozhu.framework.proxy.TransactionProxy;
import com.zhuguozhu.framework.util.StreamUtil;

/**
 * AOP Helper
 * @author Guozhu Zhu
 * @date 2019/07/20
 * @version 1.0
 *
 */
public final class AopHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);
	  
	static {
		try {
			Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
			Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
			for (Map.Entry<Class<?>, List<Proxy>> targetEntry : targetMap.entrySet()) {
				Class<?> targetClass = targetEntry.getKey();
				List<Proxy> proxyList = targetEntry.getValue();
				Object proxy = ProxyManager.createProxy(targetClass, proxyList);
				BeanHelper.setBean(targetClass, proxy);
			}
		} catch (Exception e) {
			LOGGER.error("aop failure!!!", e);
		}
	}
	
	private static Set<Class<?>> createTargetClassSet(Aspect aspect) {
		Set<Class<?>> targetClassSet = new HashSet<Class<?>>();
		Class<? extends Annotation> annotation = aspect.value();
		if (annotation != null && !annotation.equals(Aspect.class)) {
			targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
		}
		return targetClassSet;
	}
	
//	private static Map<Class<?>, Set<Class<?>>> createProxyMap() {
//		Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
//	    Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
//	    for (Class<?> proxyClass : proxyClassSet) {
//	    	if (proxyClass.isAnnotationPresent(Aspect.class)) {
//	    		Aspect aspect = proxyClass.getAnnotation(Aspect.class);
//	    		Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
//	    		proxyMap.put(proxyClass, targetClassSet);
//	    	}
//	    }
//	    return proxyMap;
//	}
	
	// 添加事务代理机制
	private static Map<Class<?>, Set<Class<?>>> createProxyMap() {
		Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
		addAspectProxy(proxyMap);
		addTransactionProxy(proxyMap);
		return proxyMap;
	}
	
	private static void addAspectProxy(Map<Class<?>, Set<Class<?>>> proxyMap) {
		Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
		for (Class<?> proxyClass : proxyClassSet) {
			if (proxyClass.isAnnotationPresent(Aspect.class)) {
				Aspect aspect = proxyClass.getAnnotation(Aspect.class);
				Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
				proxyMap.put(proxyClass, targetClassSet);
			}
		}
	}
	
	private static void addTransactionProxy(Map<Class<?>, Set<Class<?>>> proxyMap) {
		Set<Class<?>> serviceClassSet = ClassHelper.getClassSetByAnnotation(Service.class);
		proxyMap.put(TransactionProxy.class, serviceClassSet);
	}
	
	private static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap) throws InstantiationException, IllegalAccessException {
		Map<Class<?>, List<Proxy>> targetMap = new HashMap<Class<?>, List<Proxy>>();
		for (Map.Entry<Class<?>, Set<Class<?>>> proxyEntry : proxyMap.entrySet()) {
			Class<?> proxyClass = proxyEntry.getKey();
			Set<Class<?>> targetClassSet = proxyEntry.getValue();
			for (Class<?> targetClass : targetClassSet) {
				Proxy proxy = (Proxy) proxyClass.newInstance();
				if (targetMap.containsKey(targetClass)) {
					targetMap.get(targetClass).add(proxy);
				} else {
					List<Proxy> proxyList = new ArrayList<Proxy>();
					proxyList.add(proxy);
					targetMap.put(targetClass, proxyList);
				}
			}
		}
		return targetMap;
	}
	
}
