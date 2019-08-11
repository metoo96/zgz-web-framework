package com.zhuguozhu.framework.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.zhuguozhu.framework.bean.FormParam;
import com.zhuguozhu.framework.bean.Param;
import com.zhuguozhu.framework.util.ArrayUtil;
import com.zhuguozhu.framework.util.CodecUtil;
import com.zhuguozhu.framework.util.StreamUtil;
import com.zhuguozhu.framework.util.StringUtil;

/**
 * 请求助手类
 * @author Guozhu Zhu
 * @date 2019/08/06
 * @version 1.0
 *
 */
public final class RequestHelper {

	/**
	 * 创建请求对象 
	 */
	public static Param createParam(HttpServletRequest request) throws IOException {
		List<FormParam> formParamList = new ArrayList<FormParam>();
		formParamList.addAll(parseParameterNames(request));
		formParamList.addAll(parseInputStream(request));
		return new Param(formParamList);
	}
	
	private static List<FormParam> parseParameterNames(HttpServletRequest request) {
		List<FormParam> formParamList = new ArrayList<FormParam>();
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String fieldName = paramNames.nextElement();
			String[] fieldValues = request.getParameterValues(fieldName);
			if (ArrayUtil.isNotEmpty(fieldValues)) {
				Object fieldValue;
				if (fieldValues.length == 1) {
					fieldValue = fieldValues[0];
				} else {
					StringBuilder sb = new StringBuilder("");
					for (int i = 0; i < fieldValues.length; i++) {
						sb.append(fieldValues[i]);
						if (i != fieldValues.length-1) {
							sb.append(StringUtil.SEPARATOR);
						}
					}
					fieldValue = sb.toString();
				}
				formParamList.add(new FormParam(fieldName, fieldValue));
			}
		}
		return formParamList;
	}
	
	private static List<FormParam> parseInputStream(HttpServletRequest request) throws IOException {
		List<FormParam> formParamList = new ArrayList<FormParam>();
		String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
		if (StringUtil.isNotEmpty(body)) {
			String[] kvs = StringUtils.split(body, "&");
			if (ArrayUtil.isNotEmpty(kvs)) {
				for (String kv : kvs) {
					String[] array = StringUtils.split(kv, "=");
					if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
						String fieldName = array[0];
						String fieldValue = array[1];
						formParamList.add(new FormParam(fieldName, fieldValue));
					}
				}
			}
		}
		return formParamList;
	}
	
}
