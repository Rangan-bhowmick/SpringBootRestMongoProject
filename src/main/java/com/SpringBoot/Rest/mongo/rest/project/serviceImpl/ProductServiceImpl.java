package com.SpringBoot.Rest.mongo.rest.project.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.SpringBoot.Rest.mongo.rest.project.model.Products;
import com.SpringBoot.Rest.mongo.rest.project.repository.ProductsRepository;
import com.SpringBoot.Rest.mongo.rest.project.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductsRepository repository;
	
	@Cacheable(cacheNames="products")
	public List<Products> getAllProducts() {
		// TODO Auto-generated method stub
		System.out.println("calling DB");
		return repository.findAll();
	}

	public Products createProducts(Products product) {
		// TODO Auto-generated method stub
		return repository.save(product);
	}

	public List<Products> getProductsByCatagory(String catagory) {
		// TODO Auto-generated method stub
		return repository.findByName(catagory);
	}
	
	@CachePut(cacheNames="products", key="#product.productId")
	public Products updateProduct(Products product) {
		// TODO Auto-generated method stub
		return repository.save(product);
	}

	@Cacheable(cacheNames="products", key="#productId")
	public Products findByProductId(String productId) {
		// TODO Auto-generated method stub
		return repository.findByProductId(productId);
	}
	
	@CacheEvict(cacheNames="products", key="#id")
	public void deleteProduct(String id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
		
	}

}
