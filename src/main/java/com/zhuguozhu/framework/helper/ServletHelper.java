package com.zhuguozhu.framework.helper;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhuguozhu.framework.util.StreamUtil;

/**
 * Servlet������
 * @author Guozhu Zhu
 * @date 2019/07/01
 * @version 1.0
 *
 */
public class ServletHelper {
    
	 private static final Logger LOGGER = LoggerFactory.getLogger(ServletHelper.class);
	 
	 /**
	  * ʹÿ���̶߳���ӵ��һ��ServletHelperʵ��
	  */
	 private static final ThreadLocal<ServletHelper> SERVLET_HELPER_HOLDER = new ThreadLocal<ServletHelper>();
	 
	 private HttpServletRequest request;
	 private HttpServletResponse response;
	 
	 private ServletHelper(HttpServletRequest request, HttpServletResponse response) {
		 this.request = request;
		 this.response = response;
	 }
	 
	 /**
	  * ��ʼ��
	  */
	 public static void init(HttpServletRequest request, HttpServletResponse response) {
		 SERVLET_HELPER_HOLDER.set(new ServletHelper(request, response));
	 }
	 
	 /**
	  * ����
	  */
	 public static void destroy() {
		 SERVLET_HELPER_HOLDER.remove();
	 }
	 
	 /**
	  * ��ȡRequest����
	  */
	 private static HttpServletRequest getRequest() {
		 return SERVLET_HELPER_HOLDER.get().request;
	 }
	 
	 /**
	  * ��ȡResponse����
	  */
	 private static HttpServletResponse getResponse() {
		 return SERVLET_HELPER_HOLDER.get().response;
	 }
	 
	 /**
	  * ��ȡSession����
	  */
	 private static HttpSession getSession() {
		 return getRequest().getSession();
	 }
	 
	 /**
	  * ��ȡServletContext����
	  */
	 private static ServletContext getServletContext() {
		 return getRequest().getServletContext();
	 }
	 
	 /**
	  * �����Է���Request��
	  */
	 public static void setRequestAttribute(String key, Object value) {
		 getRequest().setAttribute(key, value);
	 }
	 
	 /**
	  * ��Request�л�ȡ����
	  */
	 @SuppressWarnings("unchecked")
	 public static <T> T getRequestAttribute(String key) {
		 return (T)getRequest().getAttribute(key);
	 }
	 
	 /**
	  * ��Request���Ƴ�����
	  */
	 public static void removeRequestAttribute(String key) {
		 getRequest().removeAttribute(key);
	 }
	 
	 /**
	  * �����ض�����Ӧ
	  */
	 public static void sendRedirect(String location) {
		 try {
			 getResponse().sendRedirect(getRequest().getContextPath() + location);
		 } catch (IOException e) {
			 LOGGER.error("redirect failure!!!", e);
		 }
	 }
	 
	 /**
	  * �����Է���Session��
	  */
	 public static void setSessionAttribute(String key, Object value) {
		 getSession().setAttribute(key, value);
	 }
	 
	 /**
	  * ��Session�л�ȡ����
	  */
	 @SuppressWarnings("unchecked") 
	 public static <T> T getSessionAttribute(String key) {
		 return (T)getRequest().getSession().getAttribute(key);
	 }
	 
	 /**
	  * ��Session���Ƴ�����
	  */
	 public static void removeSessionAttribute(String key) {
		 getRequest().getSession().removeAttribute(key);
	 }
	 
	 /**
	  * ʹSessionʧ��
	  */
	 public static void invalidateSession() {
		 getRequest().getSession().invalidate();
	 }
	 
	 public static void setCookie(String key, String value) {
	        Cookie cookie = new Cookie(key, value);
	        getResponse().addCookie(cookie);
	 }

	 public static void setCookie(String key, String value, int maxAge){
	        Cookie cookie = new Cookie(key, value);
	        cookie.setMaxAge(maxAge);
	        getResponse().addCookie(cookie);
	 }

	 public static String getCookie(String key){
	     Cookie[] cookies = getRequest().getCookies();
	     if (cookies != null) {
	         for (Cookie cookie : cookies){
	             if (cookie.getName().equals(key))
	                 return cookie.getValue();
	         }
	     }
	     System.out.println("[info]::û���ҵ�cookie" + key);
	     return "";
	 }
	 
}
