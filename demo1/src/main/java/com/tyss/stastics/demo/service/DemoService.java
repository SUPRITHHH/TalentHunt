package com.tyss.stastics.demo.service;

import java.util.List;

import com.tyss.stastics.demo.dto.Products_Info;
import com.tyss.stastics.demo.dto.Sales;

public interface DemoService {

	public Products_Info addProduct(Products_Info productInfo);
	
	public List<Products_Info> getProduct();;
	
	public boolean addToSales(Sales sales,int productid);
	
	public List<Sales> getSales();
	
	public List<Products_Info> checkProducts();
	
}
