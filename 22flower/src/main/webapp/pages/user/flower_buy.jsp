<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>鲜花在线商城</title>

    <!-- Bootstrap -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet"/>
    <link href="<%=basePath%>pages/user/css/index.css" rel="stylesheet">
    <link href="<%=basePath%>pages/user/css/flower_buy.css" rel="stylesheet">
    <link href="<%=basePath%>pages/user/css/head.css" rel="stylesheet">
    <link href="<%=basePath%>pages/user/css/foot.css" rel="stylesheet">

</head>
<body>
<!--头部-->
<%@ include file="head.jsp" %>
<!--头部end-->
<!--导航栏-->
<nav class="navbar navbar-default ">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">鲜花在线商城</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">活动<span class="sr-only">(current)</span></a></li>
                <li><a href="#">特价</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">Dropdown <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li><a href="#">Separated </a></li>
                        <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>
                <li><a href="#">关于我们</a></li>
            </ul>
            <form class="navbar-form navbar-right hidden-sm hidden-xs">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="我要一朵美丽的花">
                </div>
                <button type="submit" class="btn btn-default">查找</button>
            </form>

        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>


<!--导航栏end-->
<!--购买框-->
<div class="f_buy">
    <div class="f_buyContainer">
        <div class="left">
            <img src="<%=basePath%>pages/user/${product.picture}">
        </div>
        <div class="middle">
            <div class="pro_info">
                <h3>${product.productName}</h3>
                <p>花 材：${product.materials}</p>
                <p>包 装：${product.packaging}</p>
                <p>花 语：${product.talkTo}！</p>

            </div>
            <div class="pro_price">
                <div class="f_price">
                    <span>市场价：</span>
                    <span class="f_rmb"><e>￥</e>219</span>
                </div>
                <div class="t_price">
                    <span>售&nbsp;&nbsp;&nbsp;价：</span>
                    <span class="t_rmb"><e>￥</e>159</span>
                </div>
            </div>
            <div class="pro_num">
                <div class="sales">
                    <span>销量:</span>
                    <e>11689</e>
                </div>
                <div class="assess">
                    <span>评价:</span>
                    <e>1316</e>
                    <span>条</span>
                </div>
            </div>
            <div class="pro_buy">
                <span>数量:</span>
                <input class="reduce" type="button" value="-"/>
                <input class="count-input" id="number" type="text" value="1"/>
                <input class="add" type="button" value="+">
            </div>

            <div class="buy">
                <div class="nowbuy">
                    <a onclick="toOrder()">立即购买</a>
                </div>
                <div class="intoshopping">
                    <img src="images/pro_icon_32.png">
                    <a onclick="addShopingCar()">加入购物车</a>
                </div>
            </div>
        </div>
        <div class="right">
            <div class="title">
                <span>服务支持:</span>
            </div>
            <img src="<%=basePath%>pages/user/images/customerservice.jpg">
        </div>
    </div>


</div>

<!-- 底部footer -->
<%@ include file="foot.jsp" %>
<!-- 底部footerEnd -->

<!--购买框end-->
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="<%=basePath%>js/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>

<script src="<%=basePath%>pages/user/js/flower_buy.js"></script>

<script type="text/javascript">
    $(function () {
        $(window).scroll(function () {

            if ($(document).scrollTop() >= 200) {
                $(".add").addClass("navbar-fixed-top").slideDown();
            } else {
                $(".add").removeClass("navbar-fixed-top").scrollTop(0);
            }
        })
    })
</script>
</body>
</html>