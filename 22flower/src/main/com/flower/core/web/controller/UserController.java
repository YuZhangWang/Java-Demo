package com.flower.core.web.controller;


import java.awt.image.BufferedImage;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flower.core.utils.Data;
import com.flower.core.utils.ImageUtil;
import com.flower.core.utils.MD5;
import com.flower.core.po.User;
import com.flower.core.service.UserService;

@Controller
@RequestMapping(value = "/pages/user")
public class UserController {

    @Autowired
    private UserService userService;


    //首页跳转
    @RequestMapping(value = "/index.action")
    public String index() {

        return "/pages/user/index.html";
    }

    /*
     * 验证码验证
     */
    @RequestMapping(value = "/verifyCode.action", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Boolean validateVerifyCode(@RequestParam("verifyCode") String verifyCode, HttpSession session) {
        System.out.println("verifyCode=" + verifyCode);
        String imgCode = (String) session.getAttribute("imageCode");
        System.out.println("obj=" + imgCode);
        if ((verifyCode.toLowerCase()).equals(imgCode.toLowerCase())) {
            return true;
        }
        return false;
    }

    /*
     * 手机号验证
     */
    @RequestMapping(value = "/validatePhone.action", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Boolean validatePhone(@RequestParam("phone") String phone) {
        System.out.println("phone=" + phone);
        User user = userService.getUserByPhone(phone);
        if (user != null) {
            return true;
        }
        return false;
    }

    /*
     * 帐号验证
     */
    @RequestMapping(value = "/validateUsercode.action", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Boolean validateUsercode(@RequestParam("usercode") String usercode) {
        System.out.println("usercode=" + usercode);
        User user = userService.getUserByUserCode(usercode);
        if (user != null) {
            return true;
        }
        return false;
    }

    /*
     * 登录验证
     */
    @RequestMapping(value = "/login.action", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Data login(@RequestParam("usercode") String usercode, @RequestParam("password") String password, HttpSession session) {
        Data data = new Data();
        String password_mod5 = MD5.md5Password(password);
        System.out.println(password_mod5);
        if (userService.getUserByUserCode(usercode) == null) {
            data.setNotIsUserCode(true);
            return data;
        } else {
            User use = userService.login(usercode, password_mod5);
            if (use != null) {
                session.setAttribute("USER_SESSION", use);
                data.setNotIsUserCode(false);
                data.setAllIsRight(true);

                return data;

            }
        }
        return data;
    }

    //生成验证码图片
    @RequestMapping(value = "/valicode.action")
    public void valicode(HttpServletResponse response, HttpSession session) throws Exception {
        //利用图片工具生成图片
        //第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = ImageUtil.createImage();
        //将验证码存入Session
        session.setAttribute("imageCode", objs[0]);
        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }


    @RequestMapping(value = "/toRegister.action", method = RequestMethod.POST)
    @ResponseBody
    public String register(String phone, String usercode, String password) {
        System.out.println("register");
        String password_mod5 = MD5.md5Password(password);
        System.out.println(password_mod5 + ",len=" + password_mod5.length());
        int row = userService.add(phone, usercode, password_mod5);
        System.out.println(row);
        if (row > 0) {
            System.out.println("row");
            return "OK";
        }
        return "FAIL";
    }


    @RequestMapping(value = "/logout.action")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:login.action";
    }


    @RequestMapping(value = "/login.action", method = RequestMethod.GET)
    public String toLogin() {

        return "/pages/user/login.html";
    }


}
