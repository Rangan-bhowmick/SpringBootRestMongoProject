package com.SpringBoot.Rest.mongo.rest.project.service;

import java.util.List;
import java.util.Optional;

import com.SpringBoot.Rest.mongo.rest.project.model.Products;

public interface ProductService {
	
	Products findByProductId(String productId);

	public List<Products> getAllProducts();

	public Products createProducts(Products product);

	public List<Products> getProductsByCatagory(String catagory);

	public Products updateProduct(Products product);

	void deleteProduct(String id);
}
