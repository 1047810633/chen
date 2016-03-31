package com.chen.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chen.factory.BasicFactory;
import com.chen.service.UserService;

public class ActiveServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService service = BasicFactory.getFactory().getService(UserService.class);
		//1.获取激活码
		String activecode = request.getParameter("activecode");
		//2.调用Service激活用户
		service.acitveUser(activecode);
		//3.提示激活成功回到主页
		response.getWriter().write("恭喜您激活成功,3秒后回到主页....");
		response.setHeader("Refresh", "3;url=/index.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
