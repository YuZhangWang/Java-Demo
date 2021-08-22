package com.flower.core.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flower.core.po.Goods;
import com.flower.core.po.Product;
import com.flower.core.service.GoodsService;
import com.flower.core.service.ProductService;



@Controller
@RequestMapping(value="/goods")
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private ProductService productService;
	private List<Goods> goods=new ArrayList<Goods>();


	
	@RequestMapping(value="insert.action",method=RequestMethod.GET)
	@ResponseBody
	public String insert(@RequestParam("orderId") int orderId,@RequestParam("pro_id") int pro_id,@RequestParam("num") int num){
		System.out.println("insert:"+orderId);
		Product product=productService.getProductById(pro_id);
		System.out.println("price="+product.getPrice());
		Goods good=new Goods(orderId,pro_id,num,product.getPrice());
		int row=goodsService.insert(good);	
		if (row>0) {
			return "OK";
		}
		return "FAIL";
	}
	
	@RequestMapping(value="findGoodById.action")
	public String findGoodById(@RequestParam("id")Integer id,Model model){
		System.out.println("findGoodById"+id);
		Goods good=goodsService.findGoodById(id);
		int productId=good.getProductID();
		Product product=productService.getProductById(productId);
		System.out.println(productId);
		System.out.println("goods"+good.getNum());
		
		if (goods!=null) {
			model.addAttribute("good",good);
			model.addAttribute("product",product);
			return "/pages/user/flower_order.jsp";
		}
		return "/pages/user/flower_buy.jsp";
	}
	
}
