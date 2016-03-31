package com.chen.service;

import java.util.List;

import com.chen.annotation.Tran;
import com.chen.domain.Order;
import com.chen.domain.OrderListForm;
import com.chen.domain.SaleInfo;

public interface OrderService extends Service{

	/**
	 * ���Ӷ���
	 * @param order ����bean
	 */
	@Tran
	void addOrder(Order order);

	/**
	 * ��ѯָ���û����ж����ķ���
	 * @param user_id
	 * @return ���ҵ�������
	 */
	List<OrderListForm> findOrders(int user_id);

	/**
	 * ���ݶ������ɾ������
	 * @param id
	 */
	@Tran
	void delOrderByID(String id);

	/**
	 * ����id��ѯ����
	 * @param order
	 * @return
	 */
	Order findOrderById(String order);

	/**
	 * �޸�ָ��id������֧��״̬
	 * @param order
	 * @param i
	 */
	void changePayState(String order, int i);

	/**
	 * ��ѯ���۰�
	 * @return
	 */
	List<SaleInfo> saleList();

}
