# zgz-web-framework
## 简介

### 1. 一款轻量级 Java Web 框架

- 内置 MVC、IoC、AOP等特性
- 基于 Servlet 3.0 规范
- 使用 Java 注解取代 XML 配置

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

可见，基础包名为：org.zgz.sample，下面的配置中会用到它。