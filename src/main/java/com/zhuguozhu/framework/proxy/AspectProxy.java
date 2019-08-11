package com.zhuguozhu.framework.proxy;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 切面代理
 * @author Guozhu Zhu
 * @date 2019/07/19
 * @version 1.0
 *
 */
public abstract class AspectProxy implements Proxy{
    
	private static final Logger logger = LoggerFactory.getLogger(AspectProxy.class);
	
	public final Object doProxy(ProxyChain proxyChain) throws Throwable {
		Object result = null;
		Class<?> cls = proxyChain.getTargetClass();
		Method method = proxyChain.getTargetMethod();
		Object[] params = proxyChain.getMethodParams();
		begin();
		try {
			if (intercept(cls, method, params)) {
				before(cls, method, params);
				result = proxyChain.doProxyChain();
				after(cls, method, params, result);
			} else {
				result = proxyChain.doProxyChain();
			}
		} catch (Exception e) {
			logger.error("proxy failure!!!", e);
			error(cls, method, params, e);
			throw e;
		} finally {
			end();
		}
		return result;
	}
	
	public void begin() {
		
	}
	
	public boolean intercept(Class<?> cls, Method method, Object[] params) {
		return true;
	}
	
	public void before(Class<?> cls, Method method, Object[] params) {
		
	}
	
	public void after(Class<?> cls, Method method, Object[] params, Object result) {
		
	}
	
	public void error(Class<?> cls, Method method, Object[] params, Object result) {
		
	}
	
	public void end() {
		
	}
	
}
