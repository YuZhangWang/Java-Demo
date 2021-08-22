<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() 
	                   + ":" + request.getServerPort() + path + "/";
	
%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>鲜花管理</title>
	<!-- 引入css样式文件 -->
	<!-- Bootstrap Core CSS -->
	<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet" />
	<!-- MetisMenu CSS -->
	<link href="<%=basePath%>css/metisMenu.min.css" rel="stylesheet" />
	<!-- DataTables CSS -->
	<link href="<%=basePath%>css/dataTables.bootstrap.css" rel="stylesheet" />
	<!-- Custom CSS -->
	<link href="<%=basePath%>css/sb-admin-2.css" rel="stylesheet" />
	<!-- Custom Fonts -->
	<link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>css/flower.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="wrapper">
  <!-- 导航栏部分 -->
  <nav class="navbar navbar-default navbar-static-top" role="navigation"
		 style="margin-bottom: 0">
	<div class="navbar-header">
		<a class="navbar-brand" href="<%=basePath%>product/findProduct.action">鲜花管理系统 </a>
	</div>
	<!-- 导航栏右侧图标部分 -->
	<ul class="nav navbar-top-links navbar-right">
	    <!-- 邮件通知 start -->
		<li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 
				<i class="fa fa-envelope fa-fw"></i>
				<i class="fa fa-caret-down"></i>
			</a>
			<ul class="dropdown-menu dropdown-messages">
				<li>
				    <a href="#">
						<div>
							<strong>张经理</strong> <span class="pull-right text-muted">
								<em>昨天</em>
							</span>
						</div>
						<div>今天晚上开会，讨论一下下个月工作的事...</div>
				    </a>
				</li>
				<li class="divider"></li>
				<li>
				    <a class="text-center" href="#">
				        <strong>查看全部消息</strong>
						<i class="fa fa-angle-right"></i>
				    </a>
				</li>
			</ul>
		</li>
		<!-- 邮件通知 end -->
		<!-- 任务通知 start -->
		<li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 
			    <i class="fa fa-tasks fa-fw"></i>
				<i class="fa fa-caret-down"></i>
			</a>
			<ul class="dropdown-menu dropdown-tasks">
				<li>
				    <a href="#">
						<div>
							<p>
								<strong>任务 1</strong> 
								<span class="pull-right text-muted">完成40%</span>
							</p>
							<div class="progress progress-striped active">
								<div class="progress-bar progress-bar-success"
									role="progressbar" aria-valuenow="40" aria-valuemin="0"
									aria-valuemax="100" style="width: 40%">
									<span class="sr-only">完成40%</span>
								</div>
							</div>
						</div>
				    </a>
				</li>
				<li class="divider"></li>
				<li>
				    <a href="#">
						<div>
							<p>
								<strong>任务 2</strong> 
								<span class="pull-right text-muted">完成20%</span>
							</p>
							<div class="progress progress-striped active">
								<div class="progress-bar progress-bar-info" role="progressbar"
									aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"
									style="width: 20%">
									<span class="sr-only">完成20%</span>
								</div>
							</div>
						</div>
				    </a>
				</li>
				<li class="divider"></li>
				<li>
				    <a class="text-center" href="#"> 
				        <strong>查看所有任务</strong>
						<i class="fa fa-angle-right"></i>
				    </a>
				</li>
			</ul> 
		</li>
		<!-- 任务通知 end -->
		<!-- 消息通知 start -->
		<li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 
				<i class="fa fa-bell fa-fw"></i>
				<i class="fa fa-caret-down"></i>
			</a>
			<ul class="dropdown-menu dropdown-alerts">
				<li>
				    <a href="#">
						<div>
							<i class="fa fa-comment fa-fw"></i> 新回复 
							<span class="pull-right text-muted small">4分钟之前</span>
						</div>
				    </a>
				</li>
				<li class="divider"></li>
				<li>
				    <a href="#">
						<div>
							<i class="fa fa-envelope fa-fw"></i> 新消息 
							<span class="pull-right text-muted small">4分钟之前</span>
						</div>
				    </a>
				</li>
				<li class="divider"></li>
				<li>
				    <a href="#">
						<div>
							<i class="fa fa-tasks fa-fw"></i> 新任务 
							<span class="pull-right text-muted small">4分钟之前</span>
						</div>
				    </a>
				</li>
				<li class="divider"></li>
				<li>
				    <a href="#">
						<div>
							<i class="fa fa-upload fa-fw"></i> 服务器重启 
							<span class="pull-right text-muted small">4分钟之前</span>
						</div>
				    </a>
				</li>
				<li class="divider"></li>
				<li>
				    <a class="text-center" href="#"> 
				        <strong>查看所有提醒</strong>
						<i class="fa fa-angle-right"></i>
				    </a>
				</li>
			</ul> 
		</li>
		<!-- 消息通知 end -->
		<!-- 用户信息和系统设置 start -->
		<li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 
				<i class="fa fa-user fa-fw"></i>
				<i class="fa fa-caret-down"></i>
			</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="#"><i class="fa fa-user fa-fw"></i>
				               用户：${ADMIN_SESSION.adminName}
				    </a>
				</li>
				<li><a href="#"><i class="fa fa-gear fa-fw"></i> 系统设置</a></li>
				<li class="divider"></li>
				<li>
					<a href="${pageContext.request.contextPath }/admin/logout.action">
					<i class="fa fa-sign-out fa-fw"></i>退出登录
					</a>
				</li>
			</ul> 
		</li>
		<!-- 用户信息和系统设置结束 -->
	</ul>
	<!-- 左侧显示列表部分 start-->
	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">
				<li class="sidebar-search">
					<div class="input-group custom-search-form">
						<input type="text" class="form-control" placeholder="查询内容...">
						<span class="input-group-btn">
							<button class="btn btn-default" type="button">
								<i class="fa fa-search" style="padding: 3px 0 3px 0;"></i>
							</button>
						</span>
					</div> 
				</li>
				<li>
				    <a href="${pageContext.request.contextPath }/product/findProduct.action" class="active">
				      <i class="fa fa-edit fa-fw"></i> 鲜花管理
				    </a>
				</li>
				<li>
				    <a href="${pageContext.request.contextPath }/product/findProduct.action" class="active">
				      <i class="fa fa-dashboard fa-fw" ></i> 用户管理
				    </a>
				</li>
			</ul>
		</div>
	</div>
	<!-- 左侧显示列表部分 end--> 
  </nav>
    <!-- 客户列表查询部分  start-->
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">鲜花管理</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->
		<div class="panel panel-default">
			<div class="panel-body">
				<form class="form-inline" method="POST" 
				      action="${pageContext.request.contextPath }/product/findProduct.action">
			
					<button type="submit" class="btn btn-primary">查询</button>
				</form>
			</div>
		</div>
		<a href="#" class="btn btn-primary" data-toggle="modal" 
		           data-target="#newProductDialog" onclick="clearProduct()">新建</a>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">鲜花信息列表</div>
					<!-- /.panel-heading -->
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
							    <th>图片</th>
								<th>编号</th>
								<th>鲜花名称</th>
								<th>鲜花价格</th>
								<th>鲜花寄语</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${products}" var="product">
								<tr>
								    <td><img src="${basePath}${product.picture}"  class="img-responsive center-block"  style="height:30px; weight:30px;"></td>
									<td>${product.productID}</td>
									<td>${product.productName}</td>
									<td>${product.price}</td>
									<td>${product.talkTo}</td>
									<td>
										<a href="#" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#ProductEditDialog" onclick= "editProduct(${product.productID})">修改</a>
										<a href="#" class="btn btn-danger btn-xs" onclick="deleteProduct(${product.productID})">删除</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
		

					
					<div class="col-md-12 text-right">
					
					</div>
					<!-- /.panel-body -->
				</div>
				<!-- /.panel -->
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- 分页-->
		<div class="row">
		 <!-- 分页文字信息，其中分页信息都封装在pageInfo中 -->   
		          <div class="col-md-6"> 
		  当前第:${pageinfo.pageNum}页,总共：${pageinfo.pages}页,总共${pageinfo.total}条记录
		 </div>
		  <!-- 分页条 -->
            <div class="col-md-6">
               <nav aria-label="Page navigation">
                 <ul class="pagination">
                    <li><a href="${pageContext.request.contextPath }/product/findProduct.action?pn=1">首页</a></li>
                    <c:if test="${pageinfo.hasPreviousPage }">
                       <li>
                         <a href="${pageContext.request.contextPath }/product/findProduct.action?pn=${pageinfo.pageNum-1}" aria-label="Previous">
                                                     <span aria-hidden="true">&laquo;</span>
                       </a>

                       </li>
                    </c:if>
                      
                      <c:forEach items="${pageinfo.navigatepageNums}" var="page_Num">
                         <c:if test="${page_Num == pageinfo.pageNum }">
                         <li class="active"><a href="#">${page_Num}</a></li>
                         </c:if>
                        <c:if test="${page_Num != pageinfo.pageNum }">   
                      <li><a href="${pageContext.request.contextPath}/product/findProduct.action?pn=${page_Num}">${page_Num}</a></li>    
                       </c:if>

 
                       </c:forEach>
                        <c:if test="${pageinfo.hasNextPage }">
                          <li>
                            <a href="${pageContext.request.contextPath}/product/findProduct.action?pn=${pageinfo.pageNum+1}" aria-label="Next">
                              <span aria-hidden="true">&raquo;</span> </a>


                          </li>
                          
                        </c:if>
                        <li><a href="${pageContext.request.contextPath}/product/findProduct.action?pn=${pageinfo.pages}">末页</a></li>
                     
                 </ul>
               </nav>
            </div>
	
</div>				
	
				
	</div>
	<!-- 客户列表查询部分  end-->
</div>


<!-- 创建客户模态框 -->
<div class="modal fade" id="newProductDialog" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">新建鲜花信息</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="new_Product_form" enctype="multipart/form-data">
					<div class="form-group">
						<label for="new_ProductName" class="col-sm-2 control-label">
						    鲜花名称
						</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="new_productName" placeholder="鲜花名称" name="productName" />
						</div>
						
					</div>
					
					
					<div class="form-group">
						<label for="new_linkMan" class="col-sm-2 control-label">鲜花价格</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="new_price" placeholder="鲜花价格" name="price" />
						</div>
						
					</div>
					
					<div class="form-group">
						<label for="new_ProductName" class="col-sm-2 control-label">
						    鲜花寄语
						</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="new_talkTo" placeholder="鲜花名称" name="talkTo" />
						</div>
						
					</div>
					
					<div class="form-group">
						<label for="new_ProductName" class="col-sm-2 control-label">
						    鲜花图片
						</label>
						<div class="col-sm-10">
							<input type="file" class="form-control" id="new_picture" placeholder="鲜花名称" name="picture" />
						</div>
						
					</div>
					</form>
					</div>
					
					
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" onclick="createProduct()">创建客户</button>
			</div>
		</div>
	</div>

</div>

<!-- 修改客户模态框 -->
<div class="modal fade" id="ProductEditDialog" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">修改客户信息</h4>
			</div>
			<div class="modal-body">
			  
				<form class="form-horizontal" id="edit_Product_form" enctype="multipart/form-data">
					<input type="hidden" id="edit_cust_id" name="productID"/>
					<div class="form-group">
						<label for="edit_ProductName" class="col-sm-2 control-label">鲜花名称</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="edit_productName" placeholder="鲜花名称" name="productName" />
						</div>
					</div>
					
					
					
					
					
					<div class="form-group">
						<label for="edit_address" class="col-sm-2 control-label">鲜花价格</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="edit_price" placeholder="鲜花价格" name="price" />
						</div>
					</div>
					
					<div class="form-group">
						<label for="edit_address" class="col-sm-2 control-label">鲜花寄语</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="edit_talkTo" placeholder="鲜花寄语" name="talkTo" />
						</div>
					</div>
					
					
					<div class="form-group">
						<label for="edit_address" class="col-sm-2 control-label">鲜花图片</label>
						<div class="col-sm-10">
							<input type="file" class="form-control" id="edit_picture" placeholder="联系地址" name="picture" />
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" onclick="updateProduct()">保存修改</button>
			</div>
		</div>
	</div>
</div>
<!-- 引入js文件 -->
<!-- jQuery -->
<script src="<%=basePath%>js/jquery-1.11.3.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<!-- Metis Menu Plugin JavaScript -->
<script src="<%=basePath%>js/metisMenu.min.js"></script>
<!-- DataTables JavaScript -->
<script src="<%=basePath%>js/jquery.dataTables.min.js"></script>
<script src="<%=basePath%>js/dataTables.bootstrap.min.js"></script>
<!-- Custom Theme JavaScript -->
<script src="<%=basePath%>js/sb-admin-2.js"></script>
<!-- 编写js代码 -->
<script type="text/javascript">
//清空新建客户窗口中的数据
	function clearProduct() {
	    $("#new_productName").val("");
	    
	    $("#new_price").val("");
	    $("#new_talkTo").val("");
	    $("#new_picture").val("");
	   
	}
	// 创建客户
	function createProduct() {
		var form = document.getElementById("new_Product_form");
		var data= new FormData(form);
		 data.append("picture", document.getElementById("new_picture").files[0]);
		 $.ajax({
		        type:"post",
		        url:"<%=basePath%>product/add.action",
		        cache :false,
	            data : data,
	            processData : false,
	            contentType:false,
		        success:function(data) {
		        	 console.log(data);
		        	  if(data =="OK"){
		                  alert("客户创建成功！");
		                  window.location.reload();
		              }else{
		                  alert("客户创建失败！");
		                  window.location.reload();
		              }
		            
		        }
		    });
		
		  
		
		
	
	
	}
	
	// 通过id获取修改的客户信息
	function editProduct(id) {
	    $.ajax({
	        type:"get",
	        url:"<%=basePath%>product/getProductById.action",
	        data:{"id":id},
	        success:function(data) {
	            $("#edit_cust_id").val(data.productID);
	            $("#edit_productName").val(data.productName);
	           
	            $("#edit_price").val(data.price);
	            $("#edit_talkTo").val(data.talkTo);
	            
	            
	        }
	    });
	}
	
    // 执行修改客户操作
	function updateProduct() {
		var form = document.getElementById("edit_Product_form");
		var data= new FormData(form);
		var f = document.getElementById("edit_picture").value;
		
		if(f){
			console.log("yes");
			data.append("picture", document.getElementById("edit_picture").files[0]);
}
		 
		 $.ajax({
		        type:"post",
		        url:"<%=basePath%>product/update.action",
		        cache :false,
	            data : data,
	            processData : false,
	            contentType:false,
		        success:function(data) {
		        	 console.log(data);
		        	  if(data =="OK"){
		                  alert("客户修改成功！");
		                  window.location.reload();
		              }else{
		                  alert("客户修改失败！");
		                  window.location.reload();
		              }
		            
		        }
		    });
		
		  
	}  
	// 删除客户信息
	function deleteProduct(id) {
	    if(confirm('确实要删除该客户吗?')) {
	$.post("<%=basePath%>product/delete.action",{"id":id},
	function(data){
	            if(data =="OK"){
	                alert("客户删除成功！");
	                window.location.reload();
	            }else{
	                alert("删除客户失败！");
	                window.location.reload();
	            }
	        });
	    }
	}
</script>
</body>
</html>