window.onload = function () {
    $(".errorClass").each(function () {
        showError($(this));//遍历每个元素，使用每个元素来调用showError方法
    });

    $("#randImage").click(function () {
        changImg();
    });

    /*
     *  输入框得到焦点隐藏错误信息
     */
    $(".inputClass").focus(function () {
        var labelId = $(this).attr("id") + "Error";//通过输入框找到对应的label的id
        $("#" + labelId).text("");//把label的内容清空！
        showError($("#" + labelId));//隐藏没有信息的label
    });

    //输入框失去焦点进行校验
    $(".inputClass").blur(function () {
        var id = $(this).attr("id");//获取当前输入框的id
        var funName = "validate" + id.substring(0, 1).toUpperCase() + id.substring(1) + "()";//得到对应的校验函数名
        eval(funName);//执行函数调用

    });

    //按回车键登录
    function keyProcess(e) {
        var e = e || event;
        var currentKey = e.keyCode || e.which || e.charCode;
        if (currentKey == 13) {
            check();
        }
    }

    document.onkeydown = keyProcess;
    $("#chkAgree").click(function () {
        var chkAgre = $("#chkAgree").prop("checked");
        if (chkAgre) {
            $(".toRegister").show();
        } else {
            $(".toRegister").hide();
        }
    });

}

function check() {

    if (!validatePhone()) {
        return false;
    }
    if (!validateUsercode()) {
        return false;
    }
    if (!validatePassword()) {
        return false;
    }
    if (!validateConfirmPassword()) {
        return false;
    }
    if (!validateVerifyCode()) {
        return false;
    }
    var phone = $("#phone").val();
    var usercode = $("#usercode").val();
    var password = $("#password").val();
    $.ajax({
        type: "post",
        url: "toRegister.action",
        data: {
            "phone": phone,
            "usercode": usercode,
            "password": password,

        },

        dataType: "json",
        success: function (data) {
            if (data == "OK") {
                window.location.href = "index.jsp";
            }
        },
        error: function (error) {
            window.location.href = "register.html";
        }

    });

}

/*
 * 换验证码图片
 */
function changImg() {
    var img = document.getElementById("randImage");
    var d = new Date();
    var time = d.getTime();//如果没有这个
    //下面这一句不会起作用，因为浏览器的缓存技术，图片并不会刷新
    //img.src="/myHelloWeb/servlet/ImageServlet";
    img.src = "valicode.action?" + time;
    //?号后面的东西是通过get方式传递的
}

/*
 * 校验手机号码
 */
function validatePhone() {
    var bool = true;//表示校验通过
    var id = "phone";
    var value = $("#" + id).val();
    var reg = /^[1][3,4,5,7,8][0-9]{9}$/;
    if (!value) {// 非空校验
        $("#" + id + "Error").text("手机号码不能为空");
        showError($("#" + id + "Error"));
        bool = false;
    }
    if (value.length != 11) {//长度校验
        $("#" + id + "Error").text("用户账号长度必须为11位");
        showError($("#" + id + "Error"));
        bool = false;
    }
    if (!reg.test(value)) {
        $("#" + id + "Error").text("手机号不正确");
        showError($("#" + id + "Error"));
        bool = false;
    }
    //手机号码是否注册过
    $.ajax({
        type: "POST",
        dataType: "json",
        async: false,//是否异步请求，如果是异步，那么不会等服务器返回,函数就向下运行了。
        data: {
            phone: value
        },
        url: "validatePhone.action",
        success: function (flag) {
            if (flag) {
                $("#" + id + "Error").text("该手机号已注册过");
                showError($("#" + id + "Error"));
                alert("1false");
                bool = false;
            }
        }
    });
    return bool;
}

/*
 * 校验登录账号
 */
function validateUsercode() {
    var bool = true;//表示校验通过
    var id = "usercode";
    var value = $("#" + id).val();
    if (!value) {// 非空校验
        $("#" + id + "Error").text("账号不能为空");
        showError($("#" + id + "Error"));
        bool = false;
    }
    if (value.length < 3 || value.length > 20) {//长度校验
        $("#" + id + "Error").text("用户账号长度必须在3 ~ 20之间");
        showError($("#" + id + "Error"));
        bool = false;
    }
    if (isNaN(value)) {
        $("#" + id + "Error").text("用户账号必须为数字");
        showError($("#" + id + "Error"));
        bool = false;
    }
    //账号名是否注册过
    $.ajax({
        type: "POST",
        dataType: "json",
        async: false,//是否异步请求，如果是异步，那么不会等服务器返回,函数就向下运行了。
        data: {
            usercode: value
        },
        url: "validateUsercode.action",
        success: function (flag) {
            if (flag) {
                $("#" + id + "Error").text("该帐号已注册过");
                showError($("#" + id + "Error"));
                bool = false;
            }
        }
    });

    return bool;
}

/*  
 * 校验密码  
 */
function validatePassword() {
    var bool = true;//表示校验通过
    var id = "password";
    var value = $("#" + id).val();
    var reg = new RegExp(/^(?![^a-zA-Z]+$)(?!\D+$)/);//密码必须包含数字和字母
    //alert(reg.test(value));
    if (!value) {// 非空校验
        $("#" + id + "Error").text("密码不能为空");
        showError($("#" + id + "Error"));
        bool = false;
    }
    if (value.length < 3 || value.length > 20) {//长度校验
        $("#" + id + "Error").text("密码长度必须在3 ~ 20之间");
        showError($("#" + id + "Error"));
        bool = false;
    }
    if (!reg.test(value)) {
        $("#" + id + "Error").text("密码必须包含数字和字母");
        showError($("#" + id + "Error"));
        bool = false;
    }
    return bool;
}

/*
 * 确认密码校验方法
 */
function validateConfirmPassword() {
    var bool = true;//表示校验通过
    var id = "confirmPassword";
    var value = $("#" + id).val();//获取输入框内容

    if (!value) {
        $("#" + id + "Error").text("确认密码不能为空");
        showError($("#" + id + "Error"));
        bool = false;
    }

    if (value != $("#password").val()) {
        $("#" + id + "Error").text("两次输入不一致");
        showError($("#" + id + "Error"));
        bool = false;
    }
    return bool;
}

/*
 * 校验验证码  
 */
function validateVerifyCode() {
    var bool = true;//表示校验通过
    var id = "verifyCode";
    var value = $("#" + id).val();
    if (!value) {//非空校验
        $("#" + id + "Error").text("验证码不能为空");
        showError($("#" + id + "Error"));
        bool = false;
    }
    if (value.length != 4) {//长度不为4就是错误的
        $("#" + id + "Error").text("验证码长度应为4");
        showError($("#" + id + "Error"));
        bool = false;
    }
    //验证码是否正确
    $.ajax({
        type: "POST",
        dataType: "json",
        async: false,//是否异步请求，如果是异步，那么不会等服务器返回,函数就向下运行了。
        data: {
            verifyCode: value
        },
        url: "verifyCode.action",
        success: function (flag) {
            if (!flag) {
                $("#" + id + "Error").text("错误的验证码");
                showError($("#" + id + "Error"));
                bool = false;
            }
        }
    });

    return bool;
}

/*
 * 判断当前元素是否存在内容，如果存在显示，不页面不显示！
 */
function showError(ele) {
    var text = ele.text();//获取元素的内容
    if (!text) {//如果没有内容
        ele.css("display", "none");//隐藏元素
    } else {//如果有内容
        ele.css("display", "");//显示元素
    }
}



	
	