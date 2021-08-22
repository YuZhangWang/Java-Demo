<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!--  引用struts2的标签库  -->
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
  <!-- 获得基本属性 
  value="id" 访问的值栈中的数据，也就是，处理对应请求的action（AdminManagement）的属性值
  只有登录失败时，才能跳转到这个页面，才可以看到对应的数据
   -->
  <s:property value="id"/><br />
  
    <!-- 获得对象的属性   -->
  <s:property value="admin.getAdminPwd()"/><br />
  <!-- OGNL的范围访问 -->
  <s:property value="#request.reqVali"/><br />
   <!-- OGNL的范围访问 -->
  <s:property value="#session.sessionVali"/><br />
   <!-- OGNL的范围访问 -->
  <s:property value="#application.applicationVali"/><br />
   <!-- OGNL的范围访问 -->
  <s:property value="#attr.name"/><br />
  <hr />
  <s:iterator value="admins" id="ad">
  	<s:property value="#ad['adminName']"/><br />
  </s:iterator>
    <hr />
    <!-- 多条件的筛选 -->
  <s:iterator value="admins.{?#this.adminError >= 10 && #this.adminError <= 20 }" var="ad">
  	<s:property value="#ad.adminName"/><br />
  </s:iterator>
  <hr />
  <h1>index2.jsp</h1>
    <form action="loginAction.do" method="post">
    用户名：<input type="text" name="loginName" /><br />
   密码：<input type="text" name="pwd" /><br />
   <input type="submit" value="登录"/>
    </form><br />
    <a href="regist2.jsp">注册</a>
  </body>
</html>
