package com.SpringBoot.Rest.mongo.rest.project.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.SpringBoot.Rest.mongo.rest.project.model.Products;

@Repository
public interface ProductsRepository extends MongoRepository<Products, String>{

	@Query("{ 'productCategory': ?0 }")
    List<Products> findByName(final String category);
	
	@Query("{ 'productId': ?0 }")
    Products findByProductId(final String productId);
}
