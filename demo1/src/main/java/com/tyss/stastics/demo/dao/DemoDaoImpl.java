package com.tyss.stastics.demo.dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;


import com.tyss.stastics.demo.dto.Products_Info;
import com.tyss.stastics.demo.dto.Sales;


@Repository
public class DemoDaoImpl implements DemoDao {

	@PersistenceUnit
	private EntityManagerFactory factory;

	@Override
	public Products_Info addProduct(Products_Info productInfo) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			manager.persist(productInfo);
			transaction.commit();
			return productInfo;
		}catch(Exception e) {
			manager.close();
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Products_Info> getProduct() {
		EntityManager manager = factory.createEntityManager();
		String jpql = "From Products_Info";
		Query query = manager.createNamedQuery(jpql);
		List<Products_Info> product = (List<Products_Info>) query.getResultList();
		if (product== null) {
			return null;
		}
		return product;
	}

	


	@Override
	public boolean addToSales(Sales sales, int productid) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		int remainingQuantity = sales.getProductInfo().getProductQuantity() - sales.getProductQuantitySold();
		sales.setProductInfo(manager.find(Products_Info.class,productid ));
		sales.setProductQuantityUnsold(remainingQuantity);
		manager.persist(sales);
		transaction.commit();
		return true;
	}

	@Override
	public List<Sales> getSales() {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		String jpql = "From Sales";
		Query query = manager.createQuery(jpql);
		List<Sales> list = query.getResultList();
		if (list == null) {
			return null;
		}
		return list;
	}

	@Override
	public List<Products_Info> checkProducts() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	}

	

	

	
	
	

