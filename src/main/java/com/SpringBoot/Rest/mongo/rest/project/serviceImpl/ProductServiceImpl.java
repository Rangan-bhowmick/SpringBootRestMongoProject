package com.SpringBoot.Rest.mongo.rest.project.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.SpringBoot.Rest.mongo.rest.project.controller.ProductsController;
import com.SpringBoot.Rest.mongo.rest.project.model.Products;
import com.SpringBoot.Rest.mongo.rest.project.repository.ProductsRepository;
import com.SpringBoot.Rest.mongo.rest.project.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductsRepository repository;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	
	//@Cacheable(value="product-cache", sync = true)
	public List<Products> getAllProducts() {
		// TODO Auto-generated method stub
		
		List<Products> resultList = null;
		
		try{
			logger.debug("Fetching from DataBase");
			resultList = repository.findAll();
		}
		catch (Exception e) {
			logger.error("Error in getAllProducts service call:-- "+e);
		}
		return resultList;
	}
	
	@CachePut(value="product-cache", key="#product.productId")
	public Products addNewProducts(Products product) {
		// TODO Auto-generated method stub
		
		Products result = null;
		try{
			result = repository.save(product);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Error in addNewProducts service call:-- "+e);
		}
		return result;
	}
	
	@Cacheable(value="product-cache", key="#catagory")
	public List<Products> getProductsByCatagory(String catagory) {
		// TODO Auto-generated method stub
		List<Products> resultList = null;
		try{
			resultList = repository.findByName(catagory);
		}
		catch (Exception e) {
			logger.error("Error in getProductsByCatagory service call:-- "+e);
		}
		return resultList;
	}
	
	@CachePut(value="product-cache", key="#product.productId")
	public Products updateProduct(Products product) {
		// TODO Auto-generated method stub
		
		Products result = null;
		try{
			result = repository.save(product);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Error in updateProduct service call:-- "+e);
		}
		return result;

	}

	@Cacheable(value="product-cache", key="#productId")
	public Products findByProductId(String productId) {
		// TODO Auto-generated method stub
		
		Products result = null;
		try{
			result = repository.findByProductId(productId);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Error in findByProductId service call:-- "+e);
		}
		return result;
	}
	
	@CacheEvict(value="product-cache", key="#id")
	public void deleteProduct(String id) {
		// TODO Auto-generated method stub
		
		try{
			repository.deleteById(id);
		}
		catch (Exception e) {
			logger.error("Error in deleteProduct service call:-- "+e);
		}
		
		
	}

}
