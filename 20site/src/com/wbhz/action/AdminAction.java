package com.wbhz.action;

import com.wbhz.entity.Admin;
import com.wbhz.entity.MyMessage;
import com.wbhz.service.AdminService;
import com.wbhz.service.impl.AdminServiceImpl;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AdminAction {
    //(1)通过属性获得请求参数,属性名与表单元素的name属性一致，并给出get，set方法组
    private String loginName;
    private String pwd;
    private AdminService adminService = new AdminServiceImpl();

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    //默认执行的方法
    public String execute() {
        return "success";
    }

    public String linkReg() {
        return "success";
    }

    //处理登录的请求的代码
    public String loginAction() throws IOException {
        System.out.println(loginName);
        System.out.println(pwd);
        Admin admin = new Admin();
        admin.setAdminLoginName(loginName);
        admin.setAdminPwd(pwd);
        //调用service
        MyMessage msg = adminService.login(admin);
        //如果登录成功，保存session
        HttpServletRequest request = ServletActionContext.getRequest();//获得request
        HttpServletResponse response = ServletActionContext.getResponse();//获得response
        //设置字符集
        response.setContentType("text/html;charset=utf8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        // 获得输出对象
        PrintWriter out = response.getWriter();
        if (msg.isSuccess()) {
            HttpSession session = request.getSession();
            List<Admin> list = adminService.getAll();
            session.setAttribute("admins", list);
            session.setAttribute("admin", msg.getObj());
            return "success";
        } else {
            return "error";
        }

    }

}
