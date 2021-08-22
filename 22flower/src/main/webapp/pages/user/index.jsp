<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet" />
    <link href="<%=basePath%>pages/user/css/index.css" rel="stylesheet">
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
            <a class="navbar-brand" href="#">鲜花在线商城</a>
        </div>

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


<!--导航栏end-->
<!--广告条-->
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img src="<%=basePath%>pages/user/images/banner_1.jpg" alt="1 slide">
            <div class="carousel-caption">
                ...
            </div>
        </div>
        <div class="item">
            <img src="<%=basePath%>pages/user/images/banner_2.jpg" alt="2 slide">
            <div class="carousel-caption">
                ...
            </div>
        </div>
        <div class="item">
            <img src="<%=basePath%>pages/user/images/banner_3.jpg" alt="3 slide">
            <div class="carousel-caption">
                ...
            </div>
        </div>
    </div>

    <!-- Controls -->
    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">上一页</span>
    </a>
    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">下一页</span>
    </a>
</div>
<!--广告条end-->


<!--鲜花列表-->
<div class="goods">
	<!-- 1楼 -->
    <div class="goods_container">
        <div class="up">
            <h4>
            <div class="title">
                <span class="icon">
                    <img src="<%=basePath%>pages/user/images/floor-T.jpg">
                </span>
                <span class="wTitle">
                    1F爱情鲜花
                </span>
            </div>
            </h4>
            <div class="moreLink">
                 <em>
                     <a href="flower_more.html">更多爱情鲜花</a>
                 </em>
            </div>
        </div>
        <div class="down">
            <div class="left">
                <a>
                    <img src="<%=basePath%>pages/user/images/love_flower.png">
                </a>
            </div>
            <div class="right">
                <ul>
                    <li>
                        <span class="pro-img">
                           <a href="<%=basePath%>product/buy.action?id=1">
                                <img src="<%=basePath%>pages/user/images/fl_01.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>美丽的诺言-11枝红玫瑰,小熊</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="pro-img">
                            <a href="<%=basePath%>product/buy.action?id=2">
                                <img src="<%=basePath%>pages/user/images/fl_02.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>钟爱一生 11朵香槟玫瑰</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="pro-img">
                            <a href="<%=basePath%>product/buy.action?id=3">
                                <img src="<%=basePath%>pages/user/images/fl_03.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>天天祝福 33朵红玫瑰花</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="pro-img">
                            <a href="<%=basePath%>product/buy.action?id=4">
                                <img src="<%=basePath%>pages/user/images/fl_04.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>恋你一生 13朵粉玫瑰</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="pro-img">
                            <a href="<%=basePath%>product/buy.action?id=5">
                                <img src="<%=basePath%>pages/user/images/fl_05.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>邂逅的公主－33支粉玫瑰</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li><li>
                        <span class="pro-img">
                           <a href="<%=basePath%>product/buy.action?id=6">
                                <img src="<%=basePath%>pages/user/images/fl_06.jpg">
                            </a>
                        </span>
                    <div class="pro-info">
                        <div class="pro-name">
                            <a>情有独钟----33朵红玫瑰</a>
                        </div>
                        <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                            <i>￥219.00</i>
                        </div>
                    </div>
                </li>
                    <li>
                        <span class="pro-img">
                            <a href="<%=basePath%>product/buy.action?id=7">
                                <img src="<%=basePath%>pages/user/images/fl_07.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>春颜-33枝红玫瑰 搭配相思梅</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="pro-img">
                            <a href="<%=basePath%>product/buy.action?id=8">
                                <img src="<%=basePath%>pages/user/images/fl_08.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>爱你到老-99红色玫瑰</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li>

                </ul>
                
            </div>
        </div>	
    </div>
    <!-- 1楼end -->
    
	<!-- 2楼 -->
	<div class="goods_container">
        <div class="up">
            <h4>
            <div class="title">
                <span class="icon">
                    <img src="<%=basePath%>pages/user/images/floor-T.jpg">
                </span>
                <span class="wTitle">
                    2F生日鲜花
                </span>
            </div>
            </h4>
            <div class="moreLink">
                 <em>
                     <a href="flower_more.html">更多爱情鲜花</a>
                 </em>
            </div>
        </div>
        <div class="down">
            <div class="left">
                <a>
                    <img src="<%=basePath%>pages/user/images/bri_flower.png">
                </a>
            </div>
            <div class="right">
                <ul>
                    <li>
                        <span class="pro-img">
                            <a href="#" onclick="getProduct(1)">
                                <img src="<%=basePath%>pages/user/images/f2_01.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>一生中挚爱 百合2枝，红玫瑰9枝</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="pro-img">
                            <a href="<%=basePath%>product/buy.action?id=2">
                                <img src="<%=basePath%>pages/user/images/f2_02.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>如花似玉 4支白色多头百合</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="pro-img">
                            <a>
                                <img src="<%=basePath%>pages/user/images/f2_03.png">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>梦想启程-6枝向日葵</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="pro-img">
                            <a>
                                <img src="<%=basePath%>pages/user/images/f2_04.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>给你我的温柔 21朵粉百合</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="pro-img">
                            <a>
                                <img src="<%=basePath%>pages/user/images/f2_05.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>幸福花儿 33朵红玫瑰</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li><li>
                        <span class="pro-img">
                            <a>
                                <img src="<%=basePath%>pages/user/images/f2_06.jpg">
                            </a>
                        </span>
                    <div class="pro-info">
                        <div class="pro-name">
                            <a>温柔时光 11朵香槟玫瑰</a>
                        </div>
                        <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                            <i>￥219.00</i>
                        </div>
                    </div>
                </li>
                    <li>
                        <span class="pro-img">
                            <a>
                                <img src="<%=basePath%>pages/user/images/f2_07.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>珍爱一生 3支白色多头百合 玫瑰 桔梗 等搭配</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="pro-img">
                            <a>
                                <img src="<%=basePath%>pages/user/images/f2_08.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>夏日香气 -手提花蓝--</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li>

                </ul>
                
            </div>
        </div>
	</div> 
	<!-- 2楼end -->  
	
	<!-- 3楼 -->
    <div class="goods_container">
        <div class="up">
            <h4>
            <div class="title">
                <span class="icon">
                    <img src="<%=basePath%>pages/user/images/floor-T.jpg">
                </span>
                <span class="wTitle">
                    3F精品礼盒花
                </span>
            </div>
            </h4>
            <div class="moreLink">
                 <em>
                     <a href="flower_more.html">更多爱情鲜花</a>
                 </em>
            </div>
        </div>
        <div class="down">
            <div class="left">
                <a>
                    <img src="<%=basePath%>pages/user/images/lihe_flower.png">
                </a>
            </div>
            <div class="right">
                <ul>
                    <li>
                        <span class="pro-img">
                            <a href="#" onclick="getProduct(1)">
                                <img src="<%=basePath%>pages/user/images/f3_01.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>浪漫满屋-33只混搭玫瑰 礼盒</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="pro-img">
                            <a href="<%=basePath%>product/buy.action?id=2">
                                <img src="<%=basePath%>pages/user/images/f3_02.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>你是我的天使-19朵昆明蓝色妖姬 礼盒</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="pro-img">
                            <a>
                                <img src="<%=basePath%>pages/user/images/f3_03.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>思恋如水 11朵红玫瑰 礼盒</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="pro-img">
                            <a>
                                <img src="<%=basePath%>pages/user/images/f3_04.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>甜美可爱 11枝香槟玫瑰 礼盒</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="pro-img">
                            <a>
                                <img src="<%=basePath%>pages/user/images/f3_05.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>牵手一辈子 16朵粉色玫瑰 礼盒</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li><li>
                        <span class="pro-img">
                            <a>
                                <img src="<%=basePath%>pages/user/images/f3_06.jpg">
                            </a>
                        </span>
                    <div class="pro-info">
                        <div class="pro-name">
                            <a>玫瑰之约 19朵粉白玫瑰混搭 礼盒</a>
                        </div>
                        <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                            <i>￥219.00</i>
                        </div>
                    </div>
                </li>
                    <li>
                        <span class="pro-img">
                            <a>
                                <img src="<%=basePath%>pages/user/images/f3_07.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>红色温暖 19朵红色玫瑰 礼盒</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="pro-img">
                            <a>
                                <img src="<%=basePath%>pages/user/images/f3_08.png">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>最美时光 11朵红色玫瑰 礼盒</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li>

                </ul>
                
            </div>
        </div>
    </div>
    <!--  3楼end -->
    
    <!-- 4楼 -->
    <div class="goods_container">
        <div class="up">
            <h4>
            <div class="title">
                <span class="icon">
                    <img src="<%=basePath%>pages/user/images/floor-T.jpg">
                </span>
                <span class="wTitle">
                    4F永生花
                </span>
            </div>
            </h4>
            <div class="moreLink">
                 <em>
                     <a href="flower_more.html">更多爱情鲜花</a>
                 </em>
            </div>
        </div>
        <div class="down">
            <div class="left">
                <a>
                    <img src="<%=basePath%>pages/user/images/forever.png">
                </a>
            </div>
            <div class="right">
                <ul>
                    <li>
                        <span class="pro-img">
                            <a href="#" onclick="getProduct(1)">
                                <img src="<%=basePath%>pages/user/images/f4_01.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>美丽的诺言-11枝红玫瑰,小熊</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="pro-img">
                            <a href="<%=basePath%>product/buy.action?id=2">
                                <img src="<%=basePath%>pages/user/images/f4_02.png">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>美丽的诺言-11枝红玫瑰</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="pro-img">
                            <a>
                                <img src="<%=basePath%>pages/user/images/f4_03.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>美丽的诺言-11枝红玫瑰,小熊</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="pro-img">
                            <a>
                                <img src="<%=basePath%>pages/user/images/f4_04.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>美丽的诺言-11枝红玫瑰,小熊</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="pro-img">
                            <a>
                                <img src="<%=basePath%>pages/user/images/f4_05.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>美丽的诺言-11枝红玫瑰,小熊</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li><li>
                        <span class="pro-img">
                            <a>
                                <img src="<%=basePath%>pages/user/images/f4_06.jpg">
                            </a>
                        </span>
                    <div class="pro-info">
                        <div class="pro-name">
                            <a>美丽的诺言-11枝红玫瑰,小熊</a>
                        </div>
                        <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                            <i>￥219.00</i>
                        </div>
                    </div>
                </li>
                    <li>
                        <span class="pro-img">
                            <a>
                                <img src="<%=basePath%>pages/user/images/f4_07.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>美丽的诺言-11枝红玫瑰,小熊</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="pro-img">
                            <a>
                                <img src="<%=basePath%>pages/user/images/f4_08.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>美丽的诺言-11枝红玫瑰,小熊</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    159.00
                                </span>
                                <i>￥219.00</i>
                            </div>
                        </div>
                    </li>

                </ul>
                
            </div>
        </div>
    </div>
    <!-- 4楼end -->      
	
	<!--  5楼 -->
    <div class="goods_container">
        <div class="up">
            <h4>
            <div class="title">
                <span class="icon">
                    <img src="<%=basePath%>pages/user/images/floor-T.jpg">
                </span>
                <span class="wTitle">
                    5F绿植
                </span>
            </div>
            </h4>
            <div class="moreLink">
                 <em>
                     <a href="flower_more.html">更多爱情鲜花</a>
                 </em>
            </div>
        </div>
        <div class="down">
            <div class="left">
                <a>
                    <img src="<%=basePath%>pages/user/images/green_flower.png">
                </a>
            </div>
            <div class="right">
                <ul>
                    <li>
                        <span class="pro-img">
                            <a href="#" onclick="getProduct(1)">
                                <img src="<%=basePath%>pages/user/images/f5_01.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>鸿运当头2 致喜,乔迁,祝贺,公司开业,庆典旺铺开张,楼宇奠基</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    549.00
                                </span>
                                <i>￥619.00</i>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="pro-img">
                            <a href="<%=basePath%>product/buy.action?id=2">
                                <img src="<%=basePath%>pages/user/images/f5_02.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>红掌4 又名火鹤花, 致喜,乔迁,祝贺,公司开业,庆典旺铺开张,楼宇奠</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    549.00
                                </span>
                                <i>￥659.00</i>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="pro-img">
                            <a>
                                <img src="<%=basePath%>pages/user/images/f5_03.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>发财树2 致喜,乔迁,祝贺,公司开业,庆典旺铺开张,楼宇奠基</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    589.00
                                </span>
                                <i>￥699.00</i>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="pro-img">
                            <a>
                                <img src="<%=basePath%>pages/user/images/f5_04.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>鸿运当头1 致喜,乔迁,祝贺,公司开业,庆典旺铺开张,楼宇奠基</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    549.00
                                </span>
                                <i>￥659.00</i>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="pro-img">
                            <a>
                                <img src="<%=basePath%>pages/user/images/f5_05.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>八方招财 致喜,乔迁,祝贺,公司开业,庆典旺铺开张,楼宇奠基</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    409.00
                                </span>
                                <i>￥509.00</i>
                            </div>
                        </div>
                    </li><li>
                        <span class="pro-img">
                            <a>
                                <img src="<%=basePath%>pages/user/images/f5_06.jpeg">
                            </a>
                        </span>
                    <div class="pro-info">
                        <div class="pro-name">
                            <a>发财树步步高升1 致喜,乔迁,祝贺,公司开业,庆典旺铺开张,楼宇奠基</a>
                        </div>
                        <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    699.00
                                </span>
                            <i>￥859.00</i>
                        </div>
                    </div>
                </li>
                    <li>
                        <span class="pro-img">
                            <a>
                                <img src="<%=basePath%>pages/user/images/f5_07.jpeg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>大叶绿萝 致喜,乔迁,祝贺,公司开业,庆典旺铺开张,楼宇奠基</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    449.00
                                </span>
                                <i>￥849.00</i>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="pro-img">
                            <a>
                                <img src="<%=basePath%>pages/user/images/f5_08.jpg">
                            </a>
                        </span>
                        <div class="pro-info">
                            <div class="pro-name">
                                <a>蝴蝶兰-幸福美满,心想事成,事业发达,生意兴隆</a>
                            </div>
                            <div class="pro-price">
                                <span>
                                    <i class="rmb">￥</i>
                                    1499.00
                                </span>
                                <i>￥1999.00</i>
                            </div>
                        </div>
                    </li>

                </ul>
                
            </div>
        </div>
    </div>
    <!-- 5楼end --> 
    
    <!-- 承诺图片区 -->
    <div class="cn">
    	<div class="cen">
    		<img src="<%=basePath%>pages/user/images/chengnuo.jpg">
    	</div>
    </div>
    <!-- 承诺图片区end -->
    
	
</div>
<!--鲜花列表结束-->

<!-- 底部footer -->
<%@ include file="foot.jsp" %>	
<!-- 底部footerEnd -->


<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="<%=basePath%>js/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>pages/user/js/index.js"></script>
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