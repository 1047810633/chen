package com.chen.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.chen.dao.OrderDao;
import com.chen.dao.ProdDao;
import com.chen.dao.UserDao;
import com.chen.domain.Order;
import com.chen.domain.OrderItem;
import com.chen.domain.OrderListForm;
import com.chen.domain.Product;
import com.chen.domain.SaleInfo;
import com.chen.domain.User;
import com.chen.factory.BasicFactory;

public class OrderServiceImpl implements OrderService {
	OrderDao orderDao = BasicFactory.getFactory().getDao(OrderDao.class);
	ProdDao prodDao = BasicFactory.getFactory().getDao(ProdDao.class);
	UserDao userDao = BasicFactory.getFactory().getDao(UserDao.class);
	
	public void addOrder(Order order) {
		try{
			//1.���ɶ���
			orderDao.addOrder(order);
			//2.���ɶ�����/�۳���Ʒ����
			for(OrderItem item : order.getList()){
				orderDao.addOrderItem(item);
				prodDao.delPnum(item.getProduct_id(),item.getBuynum());
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<OrderListForm> findOrders(int user_id) {
		try{

			List<OrderListForm> olfList = new ArrayList<OrderListForm>();
			//1.�����û�id��ѯ���id�û������ж���
			List<Order> list = orderDao.findOrderByUserId(user_id);
			//2.�������� ����orderListForm����,����List��
			for(Order order : list){
				//--���ö�����Ϣ
				OrderListForm olf = new OrderListForm();
				BeanUtils.copyProperties(olf, order);
				//--�����û���
				User user = userDao.findUserById(order.getUser_id());
				olf.setUsername(user.getUsername());
				//--������Ʒ��Ϣ
				//----��ѯ��ǰ�������ж�����
				List<OrderItem> itemList = orderDao.findOrderItems(order.getId());
				//----�������ж�����,��ȡ��Ʒid,���Ҷ�Ӧ����Ʒ,����list
				Map<Product,Integer> prodMap = new HashMap<Product,Integer>();
				for(OrderItem item : itemList){
					String prod_id = item.getProduct_id();
					Product prod = prodDao.findProdById(prod_id);
					prodMap.put(prod, item.getBuynum());
				}
				olf.setProdMap(prodMap);
				
				olfList.add(olf);
			}
			
			return olfList;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void delOrderByID(String id) {
		//1.����id��ѯ�����ж�����
		List<OrderItem>list = orderDao.findOrderItems(id);
		//2.����������,����Ӧprod_id����Ʒ�Ŀ��ӻ�ȥ
		for(OrderItem item : list){
			prodDao.addPnum(item.getProduct_id(),item.getBuynum());
		}
		//3.ɾ��������
		orderDao.delOrderItem(id);
		//4.ɾ������
		orderDao.delOrder(id);
	}

	public void changePayState(String order, int i) {
		orderDao.changePayState(order,i);
	}

	public Order findOrderById(String order_id) {
		return orderDao.finOrderById(order_id);
	}

	public List<SaleInfo> saleList() {
		return orderDao.saleList();
	}

}
