package com.zhuguozhu.framework.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zhuguozhu.framework.util.CastUtil;
import com.zhuguozhu.framework.util.CollectionUtil;
import com.zhuguozhu.framework.util.StringUtil;

/**
 * �����������
 * @author Guozhu Zhu
 * @date 2019/07/24
 * @version 1.0
 */
public class Param {
	
	private List<FormParam> formParamList;
	private List<FileParam> fileParamList;
	
	public Param(List<FormParam> formParamList) {
		this.formParamList = formParamList;
	}
	
	public Param(List<FormParam> formParamList, List<FileParam> fileParamList) {
		this.formParamList = formParamList;
		this.fileParamList = fileParamList;
	}
	
	public Map<String, Object> getFieldMap() {
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		if (CollectionUtil.isNotEmpty(formParamList)) {
			for (FormParam formParam : formParamList) {
			    String fieldName = formParam.getFieldName();
		    	Object fieldValue = formParam.getFieldValue();
		    	if (fieldMap.containsKey(fieldName)) {
		    		fieldValue = fieldMap.get(fieldName) + StringUtil.SEPARATOR + fieldValue;
		    	}
		    	fieldMap.put(fieldName, fieldValue);
		    }
		}
	    return fieldMap;
    }
	
	/**
	 * ��ȡ�ϴ��ļ�ӳ��
	 */
	public Map<String, List<FileParam>> getFileMap() {
		Map<String, List<FileParam>> fileMap = new HashMap<String, List<FileParam>>();
		if (CollectionUtil.isNotEmpty(fileParamList)) {
			for (FileParam fileParam : fileParamList) {
				String fieldName = fileParam.getFieldName();
				List<FileParam> fileParamList;
				if (fileMap.containsKey(fieldName)) {
					fileParamList = fileMap.get(fieldName);
				} else {
					fileParamList = new ArrayList<FileParam>();
				}
				fileParamList.add(fileParam);
				fileMap.put(fieldName, fileParamList);
			}
		}
		return fileMap;
	}
	
	/**
	 * ��ȡ�����ϴ��ļ�
	 */
	public List<FileParam> getFileList(String fieldName) {
		return getFileMap().get(fieldName);
	}
	
	/**
	 * ��ȡΨһ�ϴ��ļ�
	 */
	public FileParam getFile(String fieldName) {
		List<FileParam> fileParamList = getFileList(fieldName);
		if (CollectionUtil.isNotEmpty(fileParamList) && fileParamList.size() == 1)  {
			return fileParamList.get(0);
		}
		return null;
	}
	
	/**
	 * ��֤�����Ƿ�Ϊ��
	 */
	public boolean isEmpty() {
	    return CollectionUtil.isEmpty(formParamList) && CollectionUtil.isEmpty(fileParamList);
	}
	
	
	/**
	 * ���ݲ�������ȡString �Ͳ���ֵ
	 */
	public String getString(String name) {
		return CastUtil.castString(getFieldMap().get(name));
	}
	
	/**
	 * ���ݲ�������ȡdouble�Ͳ���ֵ
	 */
	public double getDouble(String name) {
		return CastUtil.castDouble(getFieldMap().get(name));
	}
	
	/**
	 * ���ݲ�������ȡlong�Ͳ���ֵ
	 */
	public long getLong(String name) {
		return CastUtil.castInt(getFieldMap().get(name));
	}
	
	/**
	 * ���ݲ�������ȡboolean�Ͳ���ֵ
	 */
	public boolean getBoolean(String name) {
		return CastUtil.castBoolean(getFieldMap().get(name));
	}
	
}
