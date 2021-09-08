package com.wbhz.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.wbhz.entity.Admin;
import com.wbhz.entity.MyMessage;
import com.wbhz.service.AdminService;
import com.wbhz.service.impl.AdminServiceImpl;

public class AdminManagement {
    private static Logger logger = Logger.getLogger("com.wbhz.action.AdminManagement.class");
    //通过写属性的方式，获得表单控件的值，属性名与控件name要一致，并且属性有set，get方法...
    private String loginName;
    private String pwd;
    private int id = 300;
    private Admin admin;
    private List<Admin> admins = new ArrayList<Admin>();
    private String encoding = "utf-8";
    //调用业务逻辑层的登录验证
    private AdminService adminService = new AdminServiceImpl();

    public List<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

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

    //在配置文件中如果不写action的method属性，默认执行的方法就是execute
    public String execute() {
        return "success";
    }

    public String loginOut() {
        logger.info("退出系统");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.removeAttribute("admin");
        session.invalidate();
        return "success";
    }

    public String login() throws IOException {
        logger.info("登录系统");
        System.out.println("loginName:" + loginName);
        System.out.println("pwd:" + pwd);
        System.out.println("encoding:" + encoding);
        System.out.println("这是通过struts进行登录的。");
        //封装数据
        Admin admin = new Admin();
        admin.setAdminLoginName(loginName);
        admin.setAdminPwd(pwd);

        MyMessage msg = adminService.login(admin);
        if (msg.isSuccess()) {
            HttpServletRequest request = ServletActionContext.getRequest();
            //保存登录的状态
            HttpSession session = request.getSession();
            session.setAttribute("admin", msg.getObj());

            return "successShowAdmins";
        } else {
            return "input";
        }
    }

    public String loginDyMethod() throws IOException {
        logger.info("登录系统：loginDyMethod");
        admins.add(new Admin("张三", 5));
        admins.add(new Admin("李四", 15));
        admins.add(new Admin("王五", 25));
        admins.add(new Admin("赵六", 10));
        admins.add(new Admin("牛气", 13));
        admins.add(new Admin("倩儿", 15));
        admins.add(new Admin("孙悟空", 14));
        ActionContext context = ActionContext.getContext();//获得action的上下文
        Map<String, Object> req = (Map<String, Object>) context.get("request");//获得request对象
        Map<String, Object> session1 = context.getSession();//获得session对象
        Map<String, Object> application = context.getApplication();//获得application对象
        Map<String, Object> parameters = context.getParameters();//获得parameters对象
        req.put("reqVali", "request级别的变量");
        session1.put("sessionVali", "session级别的变量");
        application.put("applicationVali", "application级别的变量");

        req.put("name", "request级别的name变量");
        session1.put("name", "session级别的name变量");
        //封装数据
        MyMessage msg = adminService.login(admin);
        if (msg.isSuccess()) {
            HttpServletRequest request = ServletActionContext.getRequest();
            //保存登录的状态
            HttpSession session = request.getSession();
            session.setAttribute("admin", msg.getObj());

            return "successShowAdmins";
        } else {
            return "input";
        }
    }

    //获得所有管理员信息
    public String getAllAdmin() {
        //ActionContext


        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        try {
            request.setCharacterEncoding(encoding);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        response.setCharacterEncoding(encoding);
        response.setContentType("text/html;charset=" + encoding);
        try {
            PrintWriter out = response.getWriter();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //获得所有的管理员信息
        List<Admin> list = adminService.getList();
        //把集合放入请求对象中，request级别的变量，只限于一次请求，如果是多次的请求，数据会丢失
        request.setAttribute("list1", list);
        return "success";
    }

    //删除管理员信息
    public String doDeleteAdmin() {
        System.out.println("id:" + id);
        return "successShowAdmins";
    }

    //打开注册页面
    public String openRegist() {
        logger.info("打开注册页面");
        return "success";
    }
}
