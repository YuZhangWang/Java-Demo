<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<header class="flower_header hidden-xs hidden-sm">
    <div class="container">
        <div class="col-md-9 logo">
            <span></span>
            <span class="title">鲜花在线商城！欢迎你</span>
        </div>
        <div class="col-md-1 login ">

            <span><a href="login.html"><span class="glyphicon glyphicon-log-in"></span>登录</a></span>
        </div>
        <div class="col-md-1 register ">

            <span><a href="register.html"><span class="glyphicon glyphicon-user"></span>注册</a></span>
        </div>
        <div>
            <span class="username">
            <a href="#">
            	<c:if test="${USER_SESSION==null }">未登陆</c:if>
            	<c:if test="${USER_SESSION!=null }">${USER_SESSION.userName}</c:if>
            </a>
            </span>
        </div>
    </div>

</header>