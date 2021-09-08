package com.flower.core.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.flower.core.po.Goods;
import com.flower.core.po.Orders;
import com.flower.core.po.Product;
import com.flower.core.po.User;
import com.flower.core.service.GoodsService;
import com.flower.core.service.OrderService;
import com.flower.core.service.ProductService;


@Controller
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ProductService productService;

    private List<Orders> orders = new ArrayList<Orders>();

    @RequestMapping(value = "/list.action")
    public String showOrder(HttpSession session, Model model) {
        //List<Orders> orders=(List<Orders>) session.getAttribute("ORDERS_SESSION");
        System.out.println("orders:" + orders.size());
        model.addAttribute("orders", orders);
        return "order";
    }


    @RequestMapping(value = "/toOrder.action")
    public String toOrder() {
        System.out.println("toOrder");

        return "/pages/user/flower_order.jsp";
    }

    @RequestMapping(value = "/insert.action", method = RequestMethod.GET)
    @ResponseBody
    public String insertOrder(@RequestParam("pro_id") int[] pro_id, @RequestParam("num") int[] num, @RequestParam("consigneeName") String consigneeName, @RequestParam("consigneePhone") String consigneePhone, @RequestParam("consigneeAddress") String consigneeAddress, @RequestParam("userName") String userName, @RequestParam("userPhone") String userPhone, HttpSession session) {
        System.out.println("fff" + pro_id[0]);
        String ConsigneeName, ConsigneePhone, ConsigneeAddress, UserName, UserPhone;
        try {
            ConsigneeName = new String(consigneeName.getBytes("ISO-8859-1"), "UTF-8");
            ConsigneePhone = new String(consigneePhone.getBytes("ISO-8859-1"), "UTF-8");
            ConsigneeAddress = new String(consigneeAddress.getBytes("ISO-8859-1"), "UTF-8");
            UserName = new String(userName.getBytes("ISO-8859-1"), "UTF-8");
            UserPhone = new String(userPhone.getBytes("ISO-8859-1"), "UTF-8");
            System.out.println("insertProduct:" + ConsigneeAddress);
            User user = (User) session.getAttribute("USER_SESSION");
            if (user != null) {
                System.out.println("uid:" + user.getUserID());
                Integer userId = user.getUserID();
                Orders orders = new Orders(userId, UserName, UserPhone, user.getEmail(), ConsigneeName, ConsigneePhone, ConsigneeAddress, new Date(), "12323", 22.22);
                int row = orderService.insert(orders);
                if (row > 0) {
                    System.out.println("row:" + row + ",orders:" + orders.getOrdersID());
                    for (int i = 0; i < pro_id.length; i++) {
                        Product product = productService.getProductById(pro_id[i]);
                        System.out.println("price=" + product.getPrice());
                        Goods good = new Goods(orders.getOrdersID(), pro_id[i], num[i], product.getPrice());
                        int goodsRow = goodsService.insert(good);
                        if (goodsRow > 0) {
                            return "OK";
                        }
                    }

                }
            } else {
                return "FAIL";
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "OK";
    }


}
