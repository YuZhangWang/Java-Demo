package com.flower.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.flower.core.po.User;
import com.flower.core.po.Admin;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		//��ȡ�����URL
		String url=request.getRequestURI();
		//URL:���˵�¼�����⣬������URL���������ؿ���
		if(url.indexOf("/login.action")>=0){
			return true;
		}
		if(url.indexOf("/index.action")>=0){
			return true;
		}
		//��ȡsession
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("USER_SESSION");
		Admin admin=(Admin) session.getAttribute("ADMIN_SESSION");
		//�ж�session���Ƿ����û����ݣ�����У��򷵻�true����������ִ��
		if(user!=null||admin!=null){
			return true;
		}
//		if(user==null){
//			//request.setAttribute("msg", "您还没有登录，请登录");
//			request.getRequestDispatcher("/pages/user/login.html").forward(request, response);
//			return false;
//		}
//		if(admin==null){
//			request.setAttribute("msg", "您还没有登录，请登录");
//			request.getRequestDispatcher("/pages/admin/login.jsp").forward(request, response);
//			return false;
//		}
		
		
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception e)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
				
	}

}
