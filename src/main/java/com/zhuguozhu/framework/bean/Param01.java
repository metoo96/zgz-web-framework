package com.zhuguozhu.framework.bean;

import java.util.Map;

import com.zhuguozhu.framework.util.CastUtil;
import com.zhuguozhu.framework.util.CollectionUtil;

/**
 * 第1版的请求参数对象
 * @author Guozhu Zhu
 * @date 2019/07/12
 * @version 1.0
 *
 */
public class Param01 {
    
	private Map<String, Object> paramMap;
	
	public Param01(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
	
	/**
	 * 根据参数名获取long型参数值
	 */
	public long getLong(String name) {
		return CastUtil.castLong(paramMap.get(name));
	}
	
	/**
	 * 获取所有的字段信息
	 */
	public Map<String, Object> getMap() {
		return paramMap;
	}
	
	// 优化部分01
	public boolean isEmpty() {
		return CollectionUtil.isEmpty(paramMap);
	}
	
}
