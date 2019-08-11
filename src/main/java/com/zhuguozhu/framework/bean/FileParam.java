package com.zhuguozhu.framework.bean;

import java.io.InputStream;

/**
 * ��װ�ϴ��ļ�����
 * @author Guozhu Zhu
 * @date 2019/07/23
 * @version 1.0
 *
 */
public class FileParam {
	
	private String fieldName; // ��ʾ�ļ������ֶ���
	private String fileName; // �ϴ��ļ����ļ���
	private long fileSize; // �ϴ��ļ����ļ���С
	private String contentType; // ��ʾ�ϴ��ļ���Content-Type
	private InputStream inputStream; // ��ʾ�ϴ��ļ����ֽ�������
	
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
