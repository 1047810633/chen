package com.chen.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chen.domain.Product;
import com.chen.factory.BasicFactory;
import com.chen.service.ProdService;

public class ProdInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProdService service = BasicFactory.getFactory().getService(ProdService.class);
		//1.获取要查询的商品id
		String id = request.getParameter("id");
		//2.调用Service中的方法查询制定id的商品
		Product prod = service.findProdById(id);
		//3.将查到的商品存入request域后带到页面显示
		if(prod==null){
			throw new RuntimeException("找不到该商品!!!");
		}else{
			request.setAttribute("prod", prod);
			request.getRequestDispatcher("/prodInfo.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
