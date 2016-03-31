package com.chen.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chen.domain.Order;
import com.chen.factory.BasicFactory;
import com.chen.service.OrderService;
import com.chen.util.PaymentUtil;
import com.sun.org.apache.xpath.internal.operations.Or;

public class PayServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 ResourceBundle bundle = ResourceBundle.getBundle("merchantInfo");
		
		String p0_Cmd = "Buy";
		String p1_MerId = bundle.getString("p1_MerId");
		String p2_Order = request.getParameter("id");
		
		OrderService service = BasicFactory.getFactory().getService(OrderService.class);
		Order order= service.findOrderById(p2_Order);
		//String p3_Amt = order.getMoney()+"";
		String p3_Amt = "0.01";
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		String p8_Url = "http://www.estore.com/Callback";
		String p9_SAF = "0";
		String pa_MP  = "";
		String pd_FrpId = request.getParameter("pd_FrpId");
		String pr_NeedResponse = "1";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, bundle.getString("keyValue"));
		
		
		// Éú³Éurl --- url?
		request.setAttribute("pd_FrpId", pd_FrpId);
		request.setAttribute("p0_Cmd", p0_Cmd);
		request.setAttribute("p1_MerId", p1_MerId);
		request.setAttribute("p2_Order", p2_Order);
		request.setAttribute("p3_Amt", p3_Amt);
		request.setAttribute("p4_Cur", p4_Cur);
		request.setAttribute("p5_Pid", p5_Pid);
		request.setAttribute("p6_Pcat", p6_Pcat);
		request.setAttribute("p7_Pdesc", p7_Pdesc);
		request.setAttribute("p8_Url", p8_Url);
		request.setAttribute("p9_SAF", p9_SAF);
		request.setAttribute("pa_MP", pa_MP);
		request.setAttribute("pr_NeedResponse", pr_NeedResponse);
		request.setAttribute("hmac", hmac);

		request.getRequestDispatcher("/confirm.jsp").forward(request, response);
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
