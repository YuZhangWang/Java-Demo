<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'regist.jsp' starting page</title>

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
<h1>注册(通过对象的方式把数据填充到给定的对象中)</h1>
<form name="form1" method="post" action="regist.do">
    <table width="618" border="1">
        <tr>
            <td width="89">用户名</td>
            <td width="427"><label for="txtLogin"></label>
                <input type="text" name="admin.adminLoginName" id="txtLogin"></td>
            <td width="80">&nbsp;</td>
        </tr>
        <tr>
            <td>密码</td>
            <td><label for="txtPwd"></label>
                <input type="text" name="admin.adminPwd" id="txtPwd"></td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td>姓名</td>
            <td><label for="txtName"></label>
                <input type="text" name="admin.adminName" id="txtName"></td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td>爱好</td>
            <td>
                <label for="checkbox"><input type="checkbox" name="hobby" value="唱歌"/>唱歌</label>&nbsp;&nbsp;&nbsp;
                <label for="checkbox"><input type="checkbox" name="hobby" value="跳舞"/>跳舞</label>&nbsp;&nbsp;&nbsp;
                <label for="checkbox"><input type="checkbox" name="hobby" value="画画"/>画画</label>&nbsp;&nbsp;&nbsp;
                <label for="checkbox"><input type="checkbox" name="hobby" value="吃饭"/>吃饭</label>&nbsp;&nbsp;&nbsp;
                <label for="checkbox"><input type="checkbox" name="hobby" value="篮球"/>篮球</label>&nbsp;&nbsp;&nbsp;
                <label for="checkbox"><input type="checkbox" name="hobby" value="足球"/>足球</label>&nbsp;&nbsp;&nbsp;
            </td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td><input type="submit" name="button" id="button" value="提交">
                <input type="reset" name="button2" id="button2" value="重置"></td>
            <td>&nbsp;</td>
        </tr>
    </table>
</form>
</body>
</html>
