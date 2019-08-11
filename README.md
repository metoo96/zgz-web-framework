# zgz-web-framework

## 简介

### 1. 一款轻量级 Java Web 框架

- 内置 MVC、IoC、AOP等特性
- 基于 Servlet 3.0 规范
- 使用 Java 注解取代 XML 配置
- 方便、快捷的获取Session、Cookie等请求信息
- 基于AOP实现的简单事务处理机制
- 支持文件上传特性

### 2. 它使应用充分做到“前后端分离”

- 客户端可使用 HTML 或 JSP 作为视图模板
- 客户端通过 AJAX 获取服务端数据并进行界面渲染

## 入门

### 1. 创建一个 Maven Web 工程

整个工程的目录结构如下：

```
zgz-sample/
　　┗ src/
　　　　┗ main/
　　　　　　┗ java/
　　　　　　┗ resources/
　　　　　　┗ webapp/
　　┗ pom.xml
```

在 `java` 目录下，创建以下包名目录结构：

```
com/
　　┗ zhuguozhu/
　　　　┗ sample/
　　　　　　┗ controller/
　　　　　　┗ entity/
　　　　　　┗ service/
```

可见，基础包名为：com.zhuguozhu.sample，下面的配置中会用到它。

### 2. 配置 Maven 依赖

编辑 `pom.xml` 文件，添加 `zgz-framework` 依赖：

```xml
<dependency>
	<groupId>com.zhuguozhu</groupId>
	<artifactId>zgz-framework</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>
```

### 3. 编写 zgz-framework 配置

在 `resources` 目录下，创建一个名为 `zgz.properties` 的文件，内容如下：

```
zgz.framework.jdbc.driver=com.mysql.jdbc.Driver
zgz.framework.jdbc.url=jdbc:mysql://localhost:3306/test
zgz.framework.jdbc.username=root
zgz.framework.jdbc.password=root
zgz.framework.app.base_package=com.zhuguozhu.sample
zgz.framework.app.jsp_path=/WEB-INF/view/
zgz.framework.app.asset_path=/asset/
zgz.framework.app.upload.limit=20

```

> 提示：需根据实际情况修改以上配置。

## 示例列表

### 1. 请求映射

为一个类加上@Controller注解，在方法上加上@Action注解。
```
@Controller
public class HelloController {
    
	@Action("get:/hello")
	public View hello() {
	    return new View("hello.jsp");
	}

	@Action("post:/hello")
	public Data hello(Param param) {
        People people = new People();
        return new Data(people);
    }

}
```
返回值可以是View或Data(Json数据)
如果为void，则什么都不返回。

### 2. 依赖注入
在需要注入的类加上@Service注解。
然后在需要使用的类中加上@Inject注解即可。
```
        @Inject
	private HelloService HelloService;
```	

### 3. 获取请求参数 

```
        @Action("post:/hello")
	public View hello(Param param) {
		Map<String, Object> fieldMap = param.getFieldMap(); // 参数Map,可通过key获取value
		return new View("hello.jsp");
	}
```

### 4. Cookie、Session操作

通过封装的ServletHelper助手类进行操作。
```
        @Action("post:/hello")
	public View hello(Param param) {
		ServletHelper.setSessionAttribute("zhuguozhu", "zhuguozhu-session");
		ServletHelper.setCookie("zhu", "zhu-cookie", -1);
		ServletHelper.getCookie("zhu");
		return new View("hello.jsp");
	}
```	

### 5. 文件上传

通过封装的UploadHelper助手类进行操作, 可支持单文件及多文件上传。
Controller部分:
```
@Controller
public class UploadController {
    
	@Inject
	public UploadService uploadService;
	
	@Action("post:/upload")
	public View createSubmit(Param param) {
		Map<String, Object> fieldMap = param.getFieldMap();
		FileParam fileParam = param.getFile("photo");
		boolean result = uploadService.upload(fieldMap, fileParam);
		return new View("hello.jsp");
	}
	
}
```
Service部分:
```
@Service
public class UploadService {
	
	public boolean upload(Map<String, Object> fieldMap, FileParam fileParam)  {
		UploadHelper.uploadFile("G:tmp/upload/", fileParam);
		return true;
	}

}
```

### 6. 简单的事务控制

添加@Transaction注解即可实现简单的事务控制。
```
@Service
public class HelloService {
	
	@Transaction
	public String getName() {
		......(操作略)
		return "zhuguozhu";
	}

}
```

## TODO
安全控制





