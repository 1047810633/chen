package com.chen.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chen.factory.BasicFactory;
import com.chen.service.OrderService;
import com.chen.util.PaymentUtil;

public class Callback extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得回调所有数据
		String p1_MerId = request.getParameter("p1_MerId");
		String r0_Cmd = request.getParameter("r0_Cmd");
		String r1_Code = request.getParameter("r1_Code");
		String r2_TrxId = request.getParameter("r2_TrxId");
		String r3_Amt = request.getParameter("r3_Amt");
		String r4_Cur = request.getParameter("r4_Cur");
		String r5_Pid = request.getParameter("r5_Pid");
		String r6_Order = request.getParameter("r6_Order");
		String r7_Uid = request.getParameter("r7_Uid");
		String r8_MP = request.getParameter("r8_MP");
		String r9_BType = request.getParameter("r9_BType");
		String rb_BankId = request.getParameter("rb_BankId");
		String ro_BankOrderId = request.getParameter("ro_BankOrderId");
		String rp_PayDate = request.getParameter("rp_PayDate");
		String rq_CardNo = request.getParameter("rq_CardNo");
		String ru_Trxtime = request.getParameter("ru_Trxtime");
		// 身份校验 --- 判断是不是支付公司通知你
		String hmac = request.getParameter("hmac");
		
		if(PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType, ResourceBundle.getBundle("merchantInfo").getString("keyValue"))){
			
			if("1".equals(r9_BType)){
				//浏览器重定向访问,不能确信真的支付成功
				response.getWriter().write("支付成功!!!");
				response.setHeader("refresh", "3;url=/index.jsp");
				
				OrderService service = BasicFactory.getFactory().getService(OrderService.class);
				service.changePayState(r6_Order,1);
				
				return;
			}
			if("2".equals(r9_BType)){
				//易宝点对点通信通知支付成功,易宝通知了,说明真的支付成功
				//--修改当前订单的支付状态为已支付
				OrderService service = BasicFactory.getFactory().getService(OrderService.class);
				service.changePayState(r6_Order,1);
				response.getWriter().write("SUCCESS");
			}
			
			
			
		}else{
			throw new RuntimeException("数据被篡改过!!!!!!!");
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
