package com.zhuguozhu.framework.bean;

import java.io.InputStream;

/**
 * 封装上传文件参数
 * @author Guozhu Zhu
 * @date 2019/07/23
 * @version 1.0
 *
 */
public class FileParam {
	
	private String fieldName; // 表示文件表单的字段名
	private String fileName; // 上传文件的文件名
	private long fileSize; // 上传文件的文件大小
	private String contentType; // 表示上传文件的Content-Type
	private InputStream inputStream; // 表示上传文件的字节输入流
	
	public FileParam(String fieldName, String fileName, long fileSize, String contentType, InputStream inputStream) {
		this.fieldName = fieldName;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.contentType = contentType;
		this.inputStream = inputStream;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public long getFileSize() {
		return fileSize;
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}

}
