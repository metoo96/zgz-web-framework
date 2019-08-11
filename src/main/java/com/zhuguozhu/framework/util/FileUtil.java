package com.zhuguozhu.framework.util;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * �ļ�����������
 * @author Guozhu Zhu
 * @date 2019/07/01
 * @version 1.0
 *
 */
public final class FileUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
	
	/**
	 * ��ȡ��ʵ�ļ������Զ�ȥ���ļ�·����
	 */
	public static String getRealFileName(String fileName) {
		return FilenameUtils.getName(fileName);
	}
	
	/**
	 * �����ļ�
	 */
	public static File createFile(String filePath) {
		File file;
		try {
			file = new File(filePath);
			File parentDir = file.getParentFile();
			if (!parentDir.exists()) {
				FileUtils.forceMkdir(parentDir);
			}
		} catch (Exception e) {
			LOGGER.error("create file failure!!!", e);
			throw new RuntimeException(e);
		}
		return file;
	}
	
}
