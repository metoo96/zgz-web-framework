package com.zhuguozhu.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.zhuguozhu.framework.bean.Data;
import com.zhuguozhu.framework.bean.Handler;
import com.zhuguozhu.framework.bean.Param;
import com.zhuguozhu.framework.bean.Param01;
import com.zhuguozhu.framework.bean.View;
import com.zhuguozhu.framework.helper.BeanHelper;
import com.zhuguozhu.framework.helper.ConfigHelper;
import com.zhuguozhu.framework.helper.ControllerHelper;
import com.zhuguozhu.framework.helper.RequestHelper;
import com.zhuguozhu.framework.helper.ServletHelper;
import com.zhuguozhu.framework.helper.UploadHelper;
import com.zhuguozhu.framework.util.ArrayUtil;
import com.zhuguozhu.framework.util.CodecUtil;
import com.zhuguozhu.framework.util.JsonUtil;
import com.zhuguozhu.framework.util.ReflectionUtil;
import com.zhuguozhu.framework.util.StreamUtil;
import com.zhuguozhu.framework.util.StringUtil;

/**
 * 请求转发器
 * @author Guozhu Zhu
 * @date 2019/07/15
 * @version 1.0
 *
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet{
	
//	添加上传文件功能之前
//	@Override
//	public void init(ServletConfig servletConfig) {
//		// 初始化相关Helper类
//		HelperLoader.init();
//		// 获取ServletContext对象（用于注册Servlet)
//		ServletContext servletContext = servletConfig.getServletContext();
//		// 注册处理JSP 的 Servleet
//		ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
//		jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
//		// 注册处理静态资源的默认Servlet
//		ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
//		defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
//	}
		
	@Override
	public void init(ServletConfig servletConfig) {
		// 初始化相关Helper类
		HelperLoader.init();
		// 获取ServletContext对象（用于注册Servlet)
		ServletContext servletContext = servletConfig.getServletContext();
		// 注册处理JSP 的 Servleet
		ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
		jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
		// 注册处理静态资源的默认Servlet
		ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
		defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
		// 文件上传相关的初始化操作
	    UploadHelper.init(servletContext);
	}
	
//  未添加上传文件功能的service	
//	@Override
//	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		ServletHelper.init(request, response);
//		try {
//		// 获取请求方法与请求路径
//		String requestMethod = request.getMethod().toLowerCase();
//		String requestPath = request.getPathInfo();
//		// 获取Action处理器
//		Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
//		if (handler != null) {
//			// 获取Controller类及其Bean实例
//			Class<?> controllerClass = handler.getControllerClass();
//			Object controllerBean = BeanHelper.getBean(controllerClass);
//			// 创建请求参数对象
//			Map<String, Object> paramMap = new HashMap<String, Object>();
//			Enumeration<String> paramNames = request.getParameterNames();
//			while (paramNames.hasMoreElements()) {
//				String paramName = paramNames.nextElement();
//				String paramValue = request.getParameter(paramName);
//				paramMap.put(paramName, paramValue);
//			}
//	
//			String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
//			if (StringUtil.isNotEmpty(body)) {
//				String[] params = StringUtils.split(body, "&");
//				if (ArrayUtil.isNotEmpty(params)) {
//					for (String param : params) {
//						String[] array = StringUtils.split(param, "=");
//						if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
//							String paramName = array[0];
//							String paramValue = array[1];
//							paramMap.put(paramName, paramValue);
//						}
//					}
//				}
//			}
////	    Param param = new Param(paramMap);
////	    // 调用Action方法
////		Method actionMethod = handler.getActionMethod();
////		Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
//			Param01 param = new Param01(paramMap);
//			// 调用Action方法
//			Method actionMethod = handler.getActionMethod();
//			Object result;
//			if (param.isEmpty()) {
//			    result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
//			} else {
//				result = ReflectionUtil.invokeMethod(controllerBean, actionMethod);
//			}
////		// 处理Action方法返回值
//			if (result instanceof View) {
//				// 返回JSP页面
//				View view = (View) result;
//				String path = view.getPath();
//				if (StringUtil.isNotEmpty(path)) {
//					if (path.startsWith("/")) {
//						response.sendRedirect(request.getContextPath() + path);
//					} else {
//						Map<String, Object> model = view.getModel();
//						for (Map.Entry<String, Object> entry : model.entrySet()) {
//							request.setAttribute(entry.getKey(), entry.getValue());
//						}
//						request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request, response);
//					}
//				} else if (result instanceof Data) {
//					// 返回Json数据
//					Data data = (Data)result;
//					Object model = data.getModel();
//			        if (model != null) {
//			        	response.setContentType("application/json");
//			        	response.setCharacterEncoding("UTF-8");
//			        	PrintWriter writer = response.getWriter();
//			        	String json = JsonUtil.toJson(model);
//			        	writer.write(json);
//			        	writer.flush();
//			        	writer.close();
//			        }
//				}
//			}
//		}
//		}finally {
//			ServletHelper.destroy();
//		}
//    }
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ServletHelper.init(request, response);
		try {
		// 获取请求方法与请求路径
		String requestMethod = request.getMethod().toLowerCase();
		String requestPath = request.getPathInfo();
		if (requestPath.equals("/favicon.ico")) {
			return;
		}
		// 获取Action处理器
		Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
		if (handler != null) {
			// 获取Controller类及其Bean实例
			Class<?> controllerClass = handler.getControllerClass();
			Object controllerBean = BeanHelper.getBean(controllerClass);
			Param param;
	    	if (UploadHelper.isMultipart(request)) {
				param = UploadHelper.createParam(request);
			} else {
				param = RequestHelper.createParam(request);
			}
			Object result;
			Method actionMethod = handler.getActionMethod();
	
			if (param.isEmpty()) {
				result = ReflectionUtil.invokeMethod(controllerBean, actionMethod);
			} else {
				result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
			}	
     		// 处理Action方法返回值
			if (result instanceof View) {
				handleViewResult((View)result, request, response);
			} else {
				handleDataResult((Data)result, response);
			}
          }
		} finally {
			ServletHelper.destroy();
		}
    }
	
	private void handleViewResult(View view, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String path = view.getPath();
		if (StringUtil.isNotEmpty(path)) {
			if (path.startsWith("/")) {
				response.sendRedirect(request.getContextPath() + path);
			} else {
				Map<String, Object> model = view.getModel();
				for (Map.Entry<String, Object> entry : model.entrySet()) {
					request.setAttribute(entry.getKey(), entry.getValue());
				}
				request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request, response);
			}
		}
	}
	
	private void handleDataResult(Data data, HttpServletResponse response) throws IOException {
		Object model = data.getModel();
		if (model != null) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			String json = JsonUtil.toJson(model);
			writer.write(json);
			writer.flush();
			writer.close();
		}
	}
		
}
