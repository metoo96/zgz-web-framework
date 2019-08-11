package com.zhuguozhu.framework.proxy;

/**
 * 定义代理接口
 * @author Guozhu Zhu
 * @date 2019/07/18
 * @version 1.0
 *
 */
public interface Proxy {
    
	Object doProxy(ProxyChain proxyChain) throws Exception, Throwable;
	
}
