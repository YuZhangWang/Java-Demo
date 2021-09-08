<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'index.jsp' starting page</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
</head>

<body>
<h1>index3.jsp</h1>
<!-- 在xml的action配置中没有 编写对应的method，使用是动态方法调用，
            语法：action名字!方法名.后缀

  <form action="loginAction2!loginDyMethod.do" method="post">
    -->
<form action="loginAction2.do" method="post">
    用户名：<input type="text" name="admin.adminLoginName"/><br/>
    密码：<input type="text" name="admin.adminPwd"/><br/>
    <input type="submit" value="登录"/>
</form>
<br/>
<a href="regist2.jsp">注册</a>
</body>
</html>
