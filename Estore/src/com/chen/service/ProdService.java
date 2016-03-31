package com.chen.service;

import java.util.List;

import com.chen.domain.Product;

public interface ProdService  extends Service{

	/**
	 * �����Ʒ
	 * @param prod ��Ʒ��Ϣbean
	 */
	void addProd(Product prod);

	/**
	 * ��ѯ������Ʒ
	 * @return
	 */
	List<Product> findAllProd();

	/**
	 * ����id������Ʒ��Ϣ
	 * @param id
	 * @return
	 */
	Product findProdById(String id);

}
