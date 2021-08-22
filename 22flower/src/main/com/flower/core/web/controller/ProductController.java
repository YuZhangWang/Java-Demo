package com.flower.core.web.controller;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.flower.core.po.Product;
import com.flower.core.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value="/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	
	@RequestMapping(value="/add.action")
	@ResponseBody
	public String addflower(@RequestParam("productName")String productName,@RequestParam("price") double price,
			@RequestParam("talkTo")String talkTo,@RequestParam("picture") MultipartFile file,HttpServletRequest request) {
		
		System.out.println(productName);
		
	
		String path=request.getSession().getServletContext().getRealPath("/upload")+"\\";
		System.out.println(path);
		System.out.println(file.getContentType());
		//淇濆瓨鏁版嵁搴撶殑璺緞  
		String sqlPath=null;
		
		  //瀹氫箟鏂囦欢淇濆瓨鐨勬湰鍦拌矾寰�?  
	      String localPath=path;  
	      
	      //瀹氫�? 鏂囦欢鍚�?  
	      String filename=null;  
	      
	     if (!file.isEmpty()) {
	    	  //鐢熸垚uuid浣滀负鏂囦欢鍚嶇О    
	          String uuid = UUID.randomUUID().toString().replaceAll("-",""); 
	        //鑾峰緱鏂囦欢绫诲瀷锛堝彲浠ュ垽鏂鏋滀笉鏄浘鐗囷紝绂佹涓婁紶锛�?    
	          String contentType=file.getContentType();  
	          //鑾峰緱鏂囦欢鍚庣紑鍚�?   
	          String suffixName=contentType.substring(contentType.indexOf("/")+1); 
	        //寰楀�? 鏂囦欢鍚�?  
	          filename=uuid+"."+suffixName;   
	          System.out.println(filename);
	          System.out.println(localPath+filename);
	         
	          try {
	        	//鏂囦欢淇濆瓨璺�?  
				file.transferTo(new File(localPath+filename));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Fail";
			}
		}
	     //鎶婂浘鐗囩殑鐩稿璺緞淇濆瓨鑷虫暟鎹�?  
	      sqlPath = "/images/"+filename;  
	      System.out.println(sqlPath);
	      
	      int row=productService.addProduct(productName,price,talkTo,sqlPath);
	      if (row>0) {
	    	  return "OK";
		}else {
			return "FAIL";
		}
	      				
		
	}
	
	@RequestMapping(value="/findProduct.action")
	public String findProduct(Model model,@RequestParam(value="pn",defaultValue="1")Integer pn){
//		System.out.println("findProduct");
		PageHelper.startPage(pn, 5);
		List<Product> products=productService.findProducts();
		PageInfo info=new PageInfo(products,5);
		//System.out.println(info);
		model.addAttribute("pageinfo", info);
		model.addAttribute("products", products);
		return "/pages/admin/main.jsp";
	}
	

	@RequestMapping(value="/delete.action")
	@ResponseBody
	public String delete(Integer id){
		System.out.println("del"+id);
		int row=productService.delProduct(id);
		System.out.println(row);
		if (row>0) {
			return "OK";
		}
		return "FAIL";
		
		
		
	}
	@RequestMapping(value="/getProductById.action")
	@ResponseBody
	public Product getProductById(Integer id){
		Product product=productService.getProductById(id);
		System.out.println(product);
		return product;
		
		
	}
	@RequestMapping(value="toOrder.action")
	public String findProductById(@RequestParam("id")int id,@RequestParam("num")int num,Model model){
		System.out.println("id="+id);
		System.out.println("num="+num);
		Product product=productService.getProductById(id);
				
		if (product!=null) {
			model.addAttribute("num",num);
			model.addAttribute("product",product);
			return "/pages/user/flower_order.jsp";
		}
		return "/pages/user/flower_buy.jsp";
	}
	@RequestMapping(value="/buy.action",method=RequestMethod.GET)
	public String buy(Integer id,Model model){
		System.out.println("id="+id);
		Product product=productService.getProductById(id);
		System.out.println(product);
		model.addAttribute("product",product);
		return "/pages/user/flower_buy.jsp";
		
		
	}

	@RequestMapping(value="/update.action")
	@ResponseBody
	public String updateProductById(@RequestParam("productID")Integer  productID,@RequestParam("productName")String productName,@RequestParam("price") double price,
			@RequestParam("talkTo")String talkTo,@RequestParam("picture") MultipartFile file,HttpServletRequest request){
		Product product=new Product();
		product.setProductID(productID);
		product.setProductName(productName);
		product.setPrice(price);
		product.setTalkTo(talkTo);
		if(file.isEmpty()){
			
			
			int row= productService.updateProduct(product);
			if (row>0) {
				return "OK";
			}else {
				return "FAIL";
			}
		}else {
			//鎶婂師鏉ョ殑鐓х墖鍦ㄦ湰鍦板垹闄�
			Product product2= productService.getProductById(productID);
			String image2=product2.getPicture();
			System.out.println("del"+image2);
			String image3=image2.substring(8);
			String path=request.getSession().getServletContext().getRealPath("/upload")+"\\";
			File f=new File(path+image3);
			
			f.delete();
			//淇濆瓨鏁版嵁搴撶殑璺緞  
			String sqlPath=null;
			
			  //瀹氫箟鏂囦欢淇濆瓨鐨勬湰鍦拌矾寰�?  
		     
		      
		      //瀹氫�? 鏂囦欢鍚�?  
		      String filename=null;  
		    //鐢熸垚uuid浣滀负鏂囦欢鍚嶇О    
	          String uuid = UUID.randomUUID().toString().replaceAll("-",""); 
	        //鑾峰緱鏂囦欢绫诲瀷锛堝彲浠ュ垽鏂鏋滀笉鏄浘鐗囷紝绂佹涓婁紶锛�?    
	          String contentType=file.getContentType();  
	          //鑾峰緱鏂囦欢鍚庣紑鍚�?   
	          String suffixName=contentType.substring(contentType.indexOf("/")+1); 
	        //寰楀�? 鏂囦欢鍚�?  
	          filename=uuid+"."+suffixName;   
	          System.out.println(filename);  
	         
	          try {
	        	//鏂囦欢淇濆瓨璺�?  
				file.transferTo(new File(path+filename));
				 sqlPath = "/images/"+filename;  
			      System.out.println(sqlPath);
			      
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Fail";
			}
	          product.setPicture(sqlPath);
	          int row= productService.updateProduct(product);
				if (row>0) {
					return "OK";
				}else {
					return "FAIL";
				}
	          
	          
	          
		}
		
		
	}
	
	
}
