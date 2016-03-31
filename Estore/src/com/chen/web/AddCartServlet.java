package com.chen.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chen.domain.Product;
import com.chen.factory.BasicFactory;
import com.chen.service.ProdService;

public class AddCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProdService service = BasicFactory.getFactory().getService(ProdService.class);
		//1.根据id查找出要购买的商品
		String id = request.getParameter("id");
		Product prod = service.findProdById(id);
		//2.向cartmap中添加这个商品,如果之前没有这个商品,则添加并将数量设置为1,如果已经有过这个商品,数量+1
		if(prod==null){
			throw new RuntimeException("找不到该商品!");
		}else{
			Map<Product,Integer> cartmap = (Map<Product, Integer>) request.getSession().getAttribute("cartmap");
			cartmap.put(prod, cartmap.containsKey(prod)?cartmap.get(prod)+1 : 1);
		}
		//3.重定向到购物车页面进行展示
		response.sendRedirect("/cart.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
