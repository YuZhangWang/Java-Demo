<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'file.jsp' starting page</title>
    
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
   <h1>文件上传</h1>
   <form action="upload.do" method="post" enctype="multipart/form-data">
   	鲜花名：<input type="text" name="flowerName" value="玫瑰"/><br />
   	图片：<input type="file" name="flowerPic" onchange="yuLan()" id="fileInput" /><br />
   	预览: <img id="img" src="images/tx_1.jpg" alt="" title="图片预览" width="70" height="70" /><br />
   	<input type="submit" value="提交"/><br />
   </form>
   <script type="text/javascript">
   	 function yuLan(){
        var fileInput = document.getElementById("fileInput");
        var file = fileInput.files[0];
        //创建读取文件的对象
        var reader = new FileReader();         
        //创建文件读取相关的变量
        var imgFile;         
        //为文件读取成功设置事件
        reader.onload=function(e) {
            // alert('文件读取完成');
            imgFile = e.target.result;
            console.log(imgFile);
            var imgshow = document.getElementById("img");
            imgshow.src=imgFile;
        };
 
        //正式读取文件
        reader.readAsDataURL(file);
    }
   </script>
  </body>
</html>
