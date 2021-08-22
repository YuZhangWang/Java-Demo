
window.onload =function(){
	$(".img-hook").click(function(){
		changImg();
	});
	//账户输入框失去焦点
	$("#usercode").blur(function(){
		if(validateUsercode()){
			$("#message").text("");
		}
			
	});
	//密码输入框失去焦点
	$("#password").blur(function(){
		if(validatePassword()){
			$("#message").text("");
		}
			
	});
	//验证码输入框失去焦点
	$("#verifyCode").blur(function(){
		if(validateVerifyCode()){
			$("#message").text("");
		}
			
	});
	//按回车键登录
	function keyProcess(e) {
		var e = e || event;
		var currentKey = e.keyCode || e.which || e.charCode;
		if (currentKey == 13 ) {
			login();
		}
	}
	document.onkeydown = keyProcess;

}
//登录
function login() {
	var bool = true;//表示校验通过  
	if(!validateUsercode()){
		bool=false; 
	}
	if(!validatePassword()){
		bool=false;
	}
	if(!validateVerifyCode()){
		bool=false; 
	}
	if(bool){
	var usercode = $("#usercode").val();  
	var password = $("#password").val();  
	
	$.ajax({
        type:"post",
        url:"login.action",
        data : {
        	"usercode" : usercode,
        	"password" : password,
        },
        
        dataType : "json",
        success:function(data) {

        	if(data.notIsUserCode){
        		$("#message").text("账号不存在！");
        	}else{
        		if(data.allIsRight){
        			window.location.href ="index.jsp";
        		}else{
        			$("#message").text("密码有误！");
        		}       	
        	}
        	       	
        },
        error: function(error){      	
        	window.location.href = "login.html";    	
        }       
        
    });
	}
}
/*
 * 换验证码图片
 */
function changImg(){
    var img = document.getElementById("randImage");
    var d = new Date();
    var time = d.getTime();//如果没有这个
    //下面这一句不会起作用，因为浏览器的缓存技术，图片并不会刷新
    //img.src="/myHelloWeb/servlet/ImageServlet";
    img.src="valicode.action?"+time;
    //?号后面的东西是通过get方式传递的
}
/*  
 * 校验登录账号
 */  
function validateUsercode() {   
	var bool = true;//表示校验通过  
    var value = $("#usercode").val();  
    if(!value) {// 非空校验  
		$("#message").text("账号不能为空！");
		bool=false;
    }
    if(value.length < 3 || value.length > 20) {//长度校验          
        $("#message").text("用户账号长度必须在3 ~ 20之间！");
        bool=false; 
    }
    if(isNaN(value)){
    	 $("#message").text("用户账号必须为数字！");
    	 bool=false; 
    }
    return bool;  
}  
/*  
 * 校验密码  
 */  
function validatePassword() {   
	var bool = true;//表示校验通过  
    var value = $("#password").val();  
    var reg = new RegExp(/^(?![^a-zA-Z]+$)(?!\D+$)/);//密码必须包含数字和字母
    //alert(reg.test(value));
    if(!value) {// 非空校验  
    	$("#message").text("密码不能为空！"); 
    	bool=false;  
    } 
    if(value.length < 3 || value.length > 20) {//长度校验  
        $("#message").text("密码长度必须在3 ~ 20之间！"); 
        bool=false;   
    } 
    if(!reg.test(value)){
   	 	$("#message").text("密码必须包含数字和字母！");
   	 	bool=false;
    }
    return bool;  
}

/*  
 * 校验验证码  
 */  
function validateVerifyCode() {
	var bool = true;//表示校验通过
    var id = "verifyCode";
    var value = $("#"+id).val();
    if(!value) {//非空校验  
    	$("#message").text("验证码不能为空");
        bool=false;
    }
    if(value.length != 4) {//长度不为4就是错误的
    	$("#message").text("验证码长度应为4");
        bool=false;
    }
    //验证码是否正确
        $.ajax({      
            type: "POST",  
            dataType: "json",  
            async:false,//是否异步请求，如果是异步，那么不会等服务器返回,函数就向下运行了。 
            data : {
            	verifyCode: value
            },  
            url: "verifyCode.action",  
            success: function(flag) {  
                if(!flag) {
                	$("#message").text("错误的验证码");
                    bool=false;
                }  
            }  
        });  

    return bool;
}


	
	