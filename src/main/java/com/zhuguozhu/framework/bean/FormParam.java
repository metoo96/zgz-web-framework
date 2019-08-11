package com.zhuguozhu.framework.bean;

/**
 * ��װ������
 * @author Guozhu Zhu
 * @date 2019/07/23
 * @version 1.0
 *
 */
public class FormParam {
    
	private String fieldName;
	private Object fieldValue;
	
	public FormParam(String fieldName, Object fieldValue) {
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	
	public Object getFieldValue() {
		return fieldValue;
	}
	
}

