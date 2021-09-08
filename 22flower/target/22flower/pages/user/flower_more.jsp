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
    <link href="<%=basePath%>pages/user/css/flower_more.css" rel="stylesheet">

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
    <script src="lib/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="lib/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
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
            <a class="navbar-brand" href="#">鲜花在线商城</a>
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
                        <li><a href="#">Separated link</a></li>
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
<!--moreflower列表-->
<div class="moreFlower">
    <div class="up">
        <ul>
            <li>
                    <span>
                        <a>
                            价格<span class="glyphicon glyphicon-arrow-up"></span>
                        </a>
                    </span>
            </li>
            <li>
                    <span>
                        <a>
                            销量<span class="glyphicon glyphicon-arrow-up"></span>
                        </a>
                    </span>
            </li>
            <li>
                    <span>
                        <a>
                            人气<span class="glyphicon glyphicon-arrow-up"></span>
                        </a>
                    </span>
            </li>
            <li>
                    <span>
                        <a>
                            时间<span class="glyphicon glyphicon-arrow-up"></span>
                        </a>
                    </span>
            </li>
        </ul>
    </div>
    <div class="down">
        <ul>
            <li>
                <div class="pro_img">
                    <a>
                        <img src="<%=basePath%>images/flower_goods.jpg">
                    </a>
                </div>
                <div class="pro_name">
                    <a>
                        美丽的鲜花-11枝红玫瑰，小熊
                    </a>
                </div>
                <div class="pro_price">
                    <p>
                        ￥<strong><span>159</span></strong>
                    </p>
                    <em>
                        ￥<span>219</span>
                    </em>
                    <div>
                        <span>11690人已付款</span>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>


<!--moreflower列表end-->
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="<%=basePath%>/js/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(function () {
        $(window).scroll(function () {

            if ($(document).scrollTop() >= 200) {
                $(".navbar").addClass("navbar-fixed-top").slideDown();
            } else {
                $(".navbar").removeClass("navbar-fixed-top").scrollTop(0);
            }
        })
    })
</script>
</body>
</html>