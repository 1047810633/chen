package com.chen.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.chen.domain.User;
import com.chen.factory.BasicFactory;
import com.chen.service.UserService;
import com.chen.util.MD5Utils;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService service = BasicFactory.getFactory().getService(UserService.class);
		//1.获取用户名密码
		String username = request.getParameter("username");
		String password = MD5Utils.md5(request.getParameter("password"));
		//2.调用Service中根据用户名密码查找用户的方法
		User user = service.getUserByNameAndPsw(username,password);
		if(user == null){
			request.setAttribute("msg", "用户名密码不正确!");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		//3.检查用户激活状态
		if(user.getState() == 0){
			request.setAttribute("msg", "用户尚未激活,请到邮箱中进行激活!");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		//4.登录用户重定向到主页
		request.getSession().setAttribute("user", user);
		
		//--处理记住用户名
		if("true".equals(request.getParameter("remname"))){
			Cookie remnameC = new Cookie("remname",URLEncoder.encode(user.getUsername(),"utf-8"));
			remnameC.setPath("/");
			remnameC.setMaxAge(3600*24*30);
			response.addCookie(remnameC);
		}else{
			Cookie remnameC = new Cookie("remname","");
			remnameC.setPath("/");
			remnameC.setMaxAge(0);
			response.addCookie(remnameC);
		}
		
		//--处理30天内自动登陆
		if("true".equals(request.getParameter("autologin"))){
			Cookie autologinC = new Cookie("autologin",URLEncoder.encode(user.getUsername()+":"+user.getPassword(),"utf-8"));
			autologinC.setPath("/");
			autologinC.setMaxAge(3600*24*30);
			response.addCookie(autologinC);
		}
		
		response.sendRedirect("/index.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
