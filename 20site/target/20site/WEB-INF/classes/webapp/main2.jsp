<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'main.jsp' starting page</title>

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
<h1>管理主页面</h1>
<a href="loginOut.do">退出</a><br/>
<h2>管理员列表</h2>
<table width="100%" border="1">
    <tr>
        <td width="4%">编号</td>
        <td width="11%">登录名</td>
        <td width="19%">密码</td>
        <td width="11%">姓名</td>
        <td width="14%">爱好</td>
        <td width="20%">创建时间</td>
        <td width="6%">错误次数</td>
        <td width="5%">状态</td>
        <td width="10%">操作</td>
    </tr>
    <c:forEach var="adm" items="${list1}" varStatus="s">
        <tr>
            <td width="4%">${adm.adminId }</td>
            <td width="11%">${adm.adminLoginName }</td>
            <td width="19%">${adm.adminPwd }</td>
            <td width="11%"><c:out value="${adm.adminName }"/></td>
            <td width="14%">${adm.adminHobby }</td>
            <td width="20%"><fmt:formatDate value="${adm.adminCreate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td width="6%">${adm.adminError }</td>
            <td width="5%"><fmt:formatNumber value="${adm.adminState }" pattern="0.00"/></td>
            <td width="10%"><a href="javascript:myModify('${adm.adminId }')">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="javascript:myDel('${adm.adminId }')">删除</a></td>
        </tr>
    </c:forEach>
</table>
<script type="text/javascript">
    function myDel(id) {
        //通过主键删除管理员信息
        if (confirm("是否删除管理员信息")) {
            //	要写代码的地方
            window.location.href = "doDeleteAdmin.do?id=" + id;
        } else {
            //什么也不做
        }
    }

    function myModify(id) {
        window.location.href = "modifyAdmin.do?id=" + id;
    }
</script>
</body>
</html>
