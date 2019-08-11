package com.zhuguozhu.framework.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * ��װ������Ϣ
 * @author Guozhu Zhu
 * @date 2019/07/09
 * @version 1.0
 *
 */
public class Request {
    
	/**
	 * ���󷽷�
	 */
	private String requestMethod;
	
	/**
	 * ����·��
	 */
	private String requestPath;
	
	public Request(String requestMethod, String requestPath) {
		this.requestMethod = requestMethod;
		this.requestPath = requestPath;
	}
	
	public String getRequestMethod() {
		return requestMethod;
	}
	
	public String getRequestPath() {
		return requestPath;
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
}
