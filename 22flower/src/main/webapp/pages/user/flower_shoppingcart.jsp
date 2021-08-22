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
    <link href="<%=basePath%>pages/user/css/flower_shoppingcart.css" rel="stylesheet">
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


<!--购物车-->
    <div class="shoppingcart">
        <h3>购物车详情:</h3>
        <table id="cartTable">
            <thead>
                <tr>
                    <th>
                        <label>
                            <input class="check-all check" type="checkbox"/>&nbsp;全选
                        </label>
                    </th>
                    <th>商品</th>
                    <th>单价(元)</th>
                    <th>数量</th>
                    <th>小计</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach items="${productByShopingCars}" var="productByShopingCar">
                <tr>
                    <td class="checkbox">
                        <input class="checkbox-one check" type="checkbox" value="${productByShopingCar.shopingCar.carID }"/>
                    </td>
                    <td class="goods">
                        <a href="flower_buy.html">
                            <img src="<%=basePath%>pages/user/${productByShopingCar.product.picture}">
                            <span>${productByShopingCar.product.productName}</span>
                        </a>
                    </td>
                    <td class="price">
                       ${productByShopingCar.product.price}
                    </td>
                    <td class="count">
                        <span class="reduce"></span>
                        <input class="count-input" type="text" value="${productByShopingCar.shopingCar.num }"/>
                        <span class="add">+</span>
                    </td>
                    <td class="subtotal">298.00</td>
                    <td class="operation">
                       <span class="delete">删除</span>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="foot" id="foot">
            <label class="fl select-all"><input type="checkbox" class="check-all check"/>&nbsp;全选</label>
            <a class="fl delete" id="deleteAll" href="javascript:;">删除</a>
            <input type="button" class="fr closing" id="toOrder" value="结 算"   />
            <div class="fr total">合计：<e>￥</e><span id="priceTotal">0.00</span></div>
            <div class="fr selected" id="selected">已选商品<span id="selectedTotal">0</span>件<span class="arrow up">︽</span>
                <span class="arrow down">︾</span></div>
            <div class="selected-view">
                <div id="selectedViewList" class="clearfix">
                    <div><img src="<%=basePath%>images/flower_goods.jpg"><span>取消选择</span></div>
                </div>
                <span class="arrow">◆<span>◆</span></span>
            </div>
        </div>
    </div>


<!--购物车end-->

<!-- 底部footer -->
<%@ include file="foot.jsp" %>	
<!-- 底部footerEnd -->

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="<%=basePath%>/js/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>pages/user/js/shoppingcart.js" type="text/javascript" charset="utf-8"></script>
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