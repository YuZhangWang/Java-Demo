/**
 * Created by LENOVO on 2018/9/29.
 */
var number;
var loc=location.href;
var n1=loc.length;
var n2=loc.indexOf('=');
var id=loc.substring(n2+1, n1);
function toOrder(){
	//alert(id);
	number = $("#number").val();
	window.location.href ="http://localhost:8088/flower/product/toOrder.action?id="+id+"&num="+number;
	
}
//加入购物车
function addShopingCar(){
	number = $("#number").val();
	alert("dd"+number);
	window.location.href ="http://localhost:8088/flower/shopingCar/insert.action?prouctId="+id+"&num="+number;
	
}
