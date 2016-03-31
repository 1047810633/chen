package com.chen.service;

import java.util.List;
import java.util.UUID;

import com.chen.dao.ProdDao;
import com.chen.domain.Product;
import com.chen.factory.BasicFactory;

public class ProdServiceImpl implements ProdService {
	ProdDao dao = BasicFactory.getFactory().getDao(ProdDao.class);
	public void addProd(Product prod) {
		prod.setId(UUID.randomUUID().toString());
		dao.addProd(prod);
	}
	public List<Product> findAllProd() {
		return dao.findAllProd();
	}
	public Product findProdById(String id) {
		return dao.findProdById(id);
	}

}
