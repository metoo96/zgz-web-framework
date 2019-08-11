package com.zhuguozhu.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json������
 * @author Guozhu Zhu
 * @date 2019/07/14
 * @version 1.0
 *
 */
public class JsonUtil {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
    
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    
    /**
     * ��POJOתΪJson
     */
    public static <T> String toJson(T obj) {
    	System.out.println(obj);
    	String json;
    	try {
    		json = OBJECT_MAPPER.writeValueAsString(obj);
    	} catch (Exception e) {
    		LOGGER.error("conver POJO to Json failure!!!", e);
    		throw new RuntimeException(e);
    	}
    	return json;
    }
    
    /**
     * ��JsonתΪPOJO
     */
    public static <T> T fromJson(String json, Class<T> type) {
    	T pojo;
    	try {
    		pojo = OBJECT_MAPPER.readValue(json, type);
    	} catch (Exception e) {
    		LOGGER.error("covert json to pojo failure!!!", e);
    		throw new RuntimeException(e);
    	}
    	return pojo;
    }
    
}
