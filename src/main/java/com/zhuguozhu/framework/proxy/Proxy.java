package com.zhuguozhu.framework.proxy;

/**
 * �������ӿ�
 * @author Guozhu Zhu
 * @date 2019/07/18
 * @version 1.0
 *
 */
public interface Proxy {
    
	Object doProxy(ProxyChain proxyChain) throws Exception, Throwable;
	
}
