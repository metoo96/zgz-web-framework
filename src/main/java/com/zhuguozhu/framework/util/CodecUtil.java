package com.zhuguozhu.framework.util;

import java.net.URLDecoder;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 编码与解码操作工具类
 * @author Guozhu Zhu
 * @date 2019/07/13
 * @version 1.0
 *
 */
public class CodecUtil {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(CodecUtil.class);
	
	/**
	 * 将URL编码
	 */
	public static String encodeURL(String source) {
		String target;
		try {
			target = URLEncoder.encode(source, "UTF-8");
		} catch (Exception e) {
			LOGGER.error("encode url failure!!!", e);
			throw new RuntimeException(e);
		}
		return target;
	}
	
	/**
	 * 将URL解码
	 */
	public static String decodeURL(String source) {
		String target;
		try {
			target = URLDecoder.decode(source, "UTF-8");
		} catch (Exception e) {
			LOGGER.error("decode url failure!!!", e);
			throw new RuntimeException(e);
		}
		return target;
	}
	
}
