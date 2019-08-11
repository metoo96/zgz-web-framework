package com.zhuguozhu.framework.bean;

import java.util.Map;

import com.zhuguozhu.framework.util.CastUtil;
import com.zhuguozhu.framework.util.CollectionUtil;

/**
 * ��1��������������
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
	 * ���ݲ�������ȡlong�Ͳ���ֵ
	 */
	public long getLong(String name) {
		return CastUtil.castLong(paramMap.get(name));
	}
	
	/**
	 * ��ȡ���е��ֶ���Ϣ
	 */
	public Map<String, Object> getMap() {
		return paramMap;
	}
	
	// �Ż�����01
	public boolean isEmpty() {
		return CollectionUtil.isEmpty(paramMap);
	}
	
}
