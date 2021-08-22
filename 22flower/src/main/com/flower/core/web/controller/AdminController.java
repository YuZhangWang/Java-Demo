package com.flower.core.web.controller;


import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.flower.core.po.Admin;
import com.flower.core.service.AdminService;


@Controller
@RequestMapping(value="/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	/*
	 * 登录跳转
	 */
	@RequestMapping(value="/login.action",method=RequestMethod.POST)
	public String login(String adminCode,String password,Model model,HttpSession session){
		
		System.out.println("POST:login");
			Admin admin=adminService.findadmin(adminCode, password);
			if (admin!=null) {
				session.setAttribute("ADMIN_SESSION", admin);
				return "redirect:/product/findProduct.action";
			}
			model.addAttribute("msg","登录失败");
				
		
		return "/pages/admin/login.jsp";
	}
	/*
	 * 向用户登录界面跳转
	 */
	@RequestMapping(value="/login.action",method=RequestMethod.GET)
	public String toLogin(){
		System.out.println("login");
		return "/pages/admin/login.jsp";
	}
	/*
	 * 退出登录
	 */
	@RequestMapping(value="/logout.action")
	public String logout(HttpSession session){
		//清除Session
		session.invalidate();
		//重定向到登录界面的跳转方法
		return "redirect:/admin/login.action";
	}
	
}
