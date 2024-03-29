package com.zhuguozhu.framework.helper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhuguozhu.framework.bean.FileParam;
import com.zhuguozhu.framework.bean.FormParam;
import com.zhuguozhu.framework.bean.Param;
import com.zhuguozhu.framework.util.CollectionUtil;
import com.zhuguozhu.framework.util.FileUtil;
import com.zhuguozhu.framework.util.StreamUtil;
import com.zhuguozhu.framework.util.StringUtil;

/**
 * 文件上传助手类
 * @author Guozhu Zhu
 * @version 1.0
 *
 */
public class UploadHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadHelper.class);
    
	/**
	 * Apache Commons FileUpload提供的Servlet文件上传对象
	 */
	private static ServletFileUpload servletFileUpload;
	
	/**
	 * 初始化
	 */
	public static void init(ServletContext servletContext) {
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		servletFileUpload = new ServletFileUpload(new DiskFileItemFactory(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD, repository));
		int uploadLimit = ConfigHelper.getAppUploadLimit();
		if (uploadLimit != 0) {
			servletFileUpload.setFileSizeMax(uploadLimit*1024*1024);
		}
		System.out.println("[info]::uploadHelper");
	}
	
	/**
	 * 判断请求是否为multipart类型
	 */
	public static boolean isMultipart(HttpServletRequest request) {
		return ServletFileUpload.isMultipartContent(request);
	}
	
	/**
	 * 创建请求对象
	 */
	public static Param createParam(HttpServletRequest request) throws IOException {
		List<FormParam> formParamList = new ArrayList<FormParam>();
		List<FileParam> fileParamList = new ArrayList<FileParam>();
		try {
			Map<String, List<FileItem>> fileItemListMap = servletFileUpload.parseParameterMap(request);
			if (CollectionUtil.isNotEmpty(fileItemListMap)) {
				for (Map.Entry<String, List<FileItem>> fileItemListEntry : fileItemListMap.entrySet()) {
					String fieldName = fileItemListEntry.getKey();
					List<FileItem> fileItemList = fileItemListEntry.getValue();
					if (CollectionUtil.isNotEmpty(fileItemList)) {
						for (FileItem fileItem : fileItemList) {
							if (fileItem.isFormField()) {
								String fieldValue = fileItem.getString("UTF-8");
								formParamList.add(new FormParam(fieldName, fieldValue));
							} else {
							    String fileName = FileUtil.getRealFileName(new String(fileItem.getName().getBytes(), "UTF-8"));
							    if (StringUtil.isNotEmpty(fileName)) {
							    	long fileSize = fileItem.getSize();
							    	String contentType = fileItem.getContentType();
							    	InputStream inputStream = fileItem.getInputStream();
							    	fileParamList.add(new FileParam(fieldName, fileName, fileSize, contentType, inputStream));
							    }
							}
						}
					}
				}
			}
		} catch (FileUploadException e) {
			LOGGER.error("create param failure!!!", e);
			throw new RuntimeException(e);
		}
		return new Param(formParamList, fileParamList);
	}
	
	/**
	 * 上传文件
	 */
	public static void uploadFile(String basePath, FileParam fileParam) {
		try {
			if (fileParam != null) {
				String filePath = basePath + fileParam.getFileName();
				System.out.println(filePath);
				FileUtil.createFile(filePath);
				InputStream inputStream = new BufferedInputStream(fileParam.getInputStream());
				OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
				StreamUtil.copyStream(inputStream, outputStream);
			}
		} catch (Exception e) {
			LOGGER.error("upload file failure!!!", e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 批量上传文件
	 */
	public static void uploadFile(String basePath, List<FileParam> fileParamList) {
		try {
			if (CollectionUtil.isNotEmpty(fileParamList)) {
				for (FileParam fileParam : fileParamList) {
					uploadFile(basePath, fileParam);
				}
			}
		} catch (Exception e) {
			LOGGER.error("upload file failure!!!", e);
			throw new RuntimeException(e);
		}
	}
	
}
