package com.flower.core.web.controller;





import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flower.core.po.Product;
import com.flower.core.utils.ProductByShopingCar;
import com.flower.core.po.ShopingCar;
import com.flower.core.po.User;
import com.flower.core.service.ProductService;
import com.flower.core.service.ShopingCarService;



@Controller
@RequestMapping(value="/shopingCar")
public class ShopingCarController {
	@Autowired
	private ShopingCarService shopingCarService;
	@Autowired
	private ProductService productService;

	@RequestMapping(value="toOrder.action")
	public String toOrder(@RequestParam("ids")int[] ids,Model model){
		System.out.println("ids="+ids[0]);
		List<ProductByShopingCar> productByShopingCars=new ArrayList<ProductByShopingCar>();
		List<ShopingCar> shopingCars=new ArrayList<ShopingCar>();
		for(int id:ids){
			shopingCars.add(shopingCarService.findShopingCarById(id));
		}
		if (shopingCars!=null) {
			Product product=new Product();
			for(ShopingCar shopingCar:shopingCars){			
				product=productService.getProductById(shopingCar.getProudctID());
				ProductByShopingCar productByShopingCar=new ProductByShopingCar();
				productByShopingCar.setProduct(product);
				productByShopingCar.setShopingCar(shopingCar);
				productByShopingCars.add(productByShopingCar);
				System.out.println("productName="+product.getProductName());
			}
			
			model.addAttribute("productByShopingCars",productByShopingCars );		
			return "/pages/user/flower_order.jsp";
		}
		return "/pages/user/flower_shoppingcart.jsp";
	}
//	@RequestMapping(value="toOrder.action")
//	@ResponseBody
//	public List<ProductByShopingCar> toOrder(@RequestParam("ids")int[] ids,Model model,HttpSession session){
//		System.out.println("ids="+ids);
//		List<ProductByShopingCar> productByShopingCars=new ArrayList<ProductByShopingCar>();
//		User user=(User) session.getAttribute("USER_SESSION");
//		List<ShopingCar> shopingCars=shopingCarService.findShopingCarByUserId(user.getUserID());
//		if (shopingCars!=null) {
//			Product product=new Product();
//			for(ShopingCar shopingCar:shopingCars){
//				product=productService.getProductById(shopingCar.getProudctID());
//				ProductByShopingCar productByShopingCar=new ProductByShopingCar();
//				productByShopingCar.setProduct(product);
//				productByShopingCar.setShopingCar(shopingCar);
//				productByShopingCars.add(productByShopingCar);
//				System.out.println("productName="+product.getProductName());
//			}
//			
//			model.addAttribute("productByShopingCars",productByShopingCars );		
//			return productByShopingCars;
//		}
//		return productByShopingCars;
//	}
	
	@RequestMapping(value="insert.action",method=RequestMethod.GET)
	public String insert(@RequestParam("num")Integer num,@RequestParam("prouctId")Integer productId,HttpSession session){
		System.out.println("insertShopingCarController="+num);
		User user=(User) session.getAttribute("USER_SESSION");
		int row=shopingCarService.insert(productId,user.getUserID(),num);
		if (row>0) {
			
			return "forward:/shopingCar/findShopingCarByUserId.action";
		}
		return "/pages/user/flower_buy.jsp";
	}
	@RequestMapping(value="findShopingCarByUserId.action")
	public String findShopingCarByUserId(Model model,HttpSession session){
		System.out.println("findShopingCarByUserId");
		User user=(User) session.getAttribute("USER_SESSION");
		List<ShopingCar> shopingCars=shopingCarService.findShopingCarByUserId(user.getUserID());
		if (shopingCars!=null) {
			List<ProductByShopingCar> productByShopingCars=new ArrayList<ProductByShopingCar>();
			Product product=new Product();
			for(ShopingCar shopingCar:shopingCars){
				product=productService.getProductById(shopingCar.getProudctID());
				ProductByShopingCar productByShopingCar=new ProductByShopingCar();
				productByShopingCar.setProduct(product);
				productByShopingCar.setShopingCar(shopingCar);
				productByShopingCars.add(productByShopingCar);
				System.out.println("ProductName="+product.getProductName());
			}
			
			model.addAttribute("productByShopingCars",productByShopingCars );

			return "/pages/user/flower_shoppingcart.jsp";
		}
		return "/pages/user/flower_buy.jsp";
	}
	
	@RequestMapping(value="findAll.action")
	public String findAll(Model model){
		System.out.println("findAll");
		List<ShopingCar> shopingCars=shopingCarService.findAll();
		if (shopingCars!=null) {
			List<ProductByShopingCar> productByShopingCars=new ArrayList<ProductByShopingCar>();
			Product product=new Product();
			for(ShopingCar shopingCar:shopingCars){
				product=productService.getProductById(shopingCar.getProudctID());
				ProductByShopingCar productByShopingCar=new ProductByShopingCar();
				productByShopingCar.setProduct(product);
				productByShopingCar.setShopingCar(shopingCar);
				productByShopingCars.add(productByShopingCar);
				System.out.println("ProductName="+product.getProductName());
			}
			
			model.addAttribute("productByShopingCars",productByShopingCars );

			return "/pages/user/flower_shoppingcart.jsp";
		}
		return "/pages/user/flower_buy.jsp";
	}
	

}
