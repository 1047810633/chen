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

public class ClearCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<Product,Integer> cartmap = (Map<Product, Integer>) request.getSession().getAttribute("cartmap");
		cartmap.clear();
		response.sendRedirect("/cart.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
