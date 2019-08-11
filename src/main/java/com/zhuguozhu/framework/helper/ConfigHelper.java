package com.zhuguozhu.framework.helper;

import java.util.Properties;

import com.zhuguozhu.framework.ConfigConstant;
import com.zhuguozhu.framework.util.PropsUtil;

/**
 * �����ļ�������
 * @author Guozhu Zhu
 * @date 2019/06/28
 * @version 1.0
 *
 */
public final class ConfigHelper {
	
	private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);
    
	/**
	 * ��ȡJDBC����
	 */
	public static String getJdbcDriver() {
		return PropsUtil.getString(CONFIG_PROPS,  ConfigConstant.JDBC_DRIVER);
	}
	
	/**
	 * ��ȡ JDBC URL
	 */
	public static String getJdbcUrl() {
	    return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_URL);	
	}
	

	/**
	 * ��ȡJDBC�û���
	 */
	public static String getJdbcUsername() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_USERNAME);
	}
	
	/**
	 * ��ȡJDBC����
	 */
	public static String getJdbcPassword() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
	}
	
	/**
	 * ��ȡӦ�û�������
	 */
	public static String getAppBasePackage() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_BASE_PACKAGE);
	}
	
	/**
	 * ��ȡӦ��JSP·��
	 */
	public static String getAppJspPath() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_JSP_PATH, "/WEB-INF/view/");
	}                   
	
	/**
	 * ��ȡӦ�þ�̬��Դ·��
	 */
	public static String getAppAssetPath() {
		return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_ASSET_PATH, "/asset/");
	}

	/**
	 * ��ȡӦ���ļ��ϴ����� 
	 */
	public static int getAppUploadLimit() {
		return PropsUtil.getInt(CONFIG_PROPS, ConfigConstant.APP_UPLOAD_LIMIT);
	}
	
}
