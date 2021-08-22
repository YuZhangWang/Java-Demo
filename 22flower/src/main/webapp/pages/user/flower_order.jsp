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
    <link href="<%=basePath%>pages/user/css/flower_order.css" rel="stylesheet">
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

<!--订单填写-->
    <div class="main">
        <form class="order" method="post">
        <div class="address">
            <h3>请选择收货地址</h3>
            <ul>
                <li>
                    <span>
                        <e>*</e>收花人姓名:
                    </span>
                    <input id="consigneeName"  type="text" />
                </li>
                <li>
                    <span>
                        <e>*</e>收花人手机:
                    </span>
                    <input id="consigneePhone"  type="text"/>
                </li>
                <li>
                    <span>
                        <e>*</e>收花人地址:
                    </span>
                    <div  id="province">

                    </div>

                </li>
                <li>
                    <span>
                        <e>*</e>详细地址:
                    </span>
                    <input id="address"  type="text" class="longaddress"/>
                </li>
                <li>
                    <span>
                        <e>*</e>订花人姓名:
                    </span>
                    <input id="userName"  type="text"/>
                </li>
                <li>
                    <span>
                        <e>*</e>订花人手机:
                    </span>
                    <input id="userPhone"  type="text"/>
                </li>
            </ul>
        </div>

        <div class="goods">
            <h3>商品清单</h3>
            <table class="goodsTable" id="goodsTable">
                <thead>
                	<th></th>
                    <th>商品</th>
                    <th>单价</th>
                    <th>数量</th>
                    <th>小计</th>

                </thead>
                <tbody>
                <c:if test="${product!=null}">
                 	<tr>               
                    <td id="productID" style="visibility:hidden;">${product.productID }</td>
                    <td>
                        <a href="flower_buy.jsp">
                        <img src="<%=basePath%>pages/user/${product.picture}">
                        <span>${product.productName }</span>
                        </a>
                    </td>
                    <td>
                        <span>
                            <e>￥</e>${product.price }
                        </span>
                    </td>
                    <td>
                        <span id="num">${num}</span>
                    </td>
                    <td>
                        <span class="rmb">
                            <e>￥</e>289.00
                        </span>
                    </td>
                 	</tr>
                 </c:if>
                 <c:if test="${productByShopingCars!=null}">
                 <c:forEach items="${productByShopingCars}" var="productByShopingCar">
                 	<tr>               
                    <td id="productID" style="visibility:hidden;">${productByShopingCar.product.productID }</td>
                    <td>
                        <a href="flower_buy.jsp">
                        <img src="<%=basePath%>pages/user/${productByShopingCar.product.picture}">
                        <span>${productByShopingCar.product.productName }</span>
                        </a>
                    </td>
                    <td>
                        <span>
                            <e>￥</e>${productByShopingCar.product.price }
                        </span>
                    </td>
                    <td>
                        <span id="num">${productByShopingCar.shopingCar.num }</span>
                    </td>
                    <td>
                        <span class="rmb">
                            <e>￥</e>289.00
                        </span>
                    </td>
                 	</tr>
                 	</c:forEach>
                 </c:if>
                 
                </tbody>
            </table>
        </div>
        <div class="ok">
            <div class="fare">
                <span>运费:</span>
                <span>
                    <e>￥</e>0
                </span>
            </div>
            <div class="orderFare">
                <span>订单实付:</span>
                <span class="rmb"><e>￥</e>289.00</span>
            </div>
            <input class="submit" type="button" value="提交订单" onclick="addOrder()" />
        </div>
        </form>
    </div>

<!--订单填写end-->

<!-- 底部footer -->
<%@ include file="foot.jsp" %>	
<!-- 底部footerEnd -->

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="<%=basePath%>/js/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>pages/user/js/jquery.provincesCity.js"></script>
<script src="<%=basePath%>pages/user/js/provincesData.js"></script>
<script src="<%=basePath%>pages/user/js/flower_order.js"></script>
<script type="text/javascript">
    $(function () {
        $(window).scroll(function () {

            if ($(document).scrollTop() >= 200) {
                $(".navbar").addClass("navbar-fixed-top").slideDown();
            } else {
                $(".navbar").removeClass("navbar-fixed-top").scrollTop(0);
            }
        })
        $("#province").ProvinceCity();
    })



</script>
</body>
</html>