package com.SpringBoot.Rest.mongo.rest.project.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.Rest.mongo.rest.project.model.Products;
import com.SpringBoot.Rest.mongo.rest.project.response.ApiFailureResponse;
import com.SpringBoot.Rest.mongo.rest.project.response.ApiSuccessResponse;
import com.SpringBoot.Rest.mongo.rest.project.responseHandler.ResponseHandler;
import com.SpringBoot.Rest.mongo.rest.project.service.ProductService;
import com.SpringBoot.Rest.mongo.rest.project.service.SequenceGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.experimental.Delegate;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins="*")
public class ProductsController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);;
	
	public static final String SUCCSTATUS = "Success";
	public static final String FAILSTATUS = "Failure";

	@Autowired
	ProductService service;

	@Autowired
	SequenceGeneratorService sequenceService;
	
	@Autowired
	ResponseHandler responseHandler;

	@GetMapping
	public ResponseEntity<?> getAllProducts() {
		ResponseEntity<?> responseEntity;
		logger.debug("-----getAllProducts Request start-----");
		
		try{
			List<Products> result = service.getAllProducts();
			responseEntity = ResponseEntity.of(Optional.of(result));
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Error while getAllProducts request: "+e);
			responseEntity = new ResponseEntity<ApiFailureResponse>(responseHandler.returnFailureResponse(FAILSTATUS, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.debug("responseEntity from getAllProducts:- "+responseEntity);
		return responseEntity;
	}

	@GetMapping("/{catagory}")
	public ResponseEntity<?> getProductsByCatagory(@PathVariable String catagory) {
		
		ResponseEntity<?> responseEntity;
		logger.debug("-----getProductsByCatagory Request start-----Request: "+catagory);
		try{
			List<Products> result = service.getProductsByCatagory(catagory);

			if (result.size() == 0) {
				responseEntity = new ResponseEntity<ApiFailureResponse>(responseHandler.returnFailureResponse(FAILSTATUS, "Product with catagory:"+catagory+" is Not Found"), HttpStatus.NOT_FOUND);
			}else{
				responseEntity = ResponseEntity.of(Optional.of(result));
			}
	
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Error in get Catagory request:-- "+e);
			responseEntity = new ResponseEntity<ApiFailureResponse>(responseHandler.returnFailureResponse(FAILSTATUS, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		logger.debug("responseEntity from getProductsByCatagory: "+responseEntity);
		
		return responseEntity;
		

	}

	@PostMapping
	public ResponseEntity<?> createProduct(@RequestBody Products product) {

		Products createdProduct = null;
		ResponseEntity<?> responseEntity;
		logger.debug("-----createProduct Request start-----Request: "+product.toString());
		try {
			String seq_num = String.valueOf(sequenceService.getSequenceNumber(Products.SEQUENCE_NAME));
			product.setProductId("P" + seq_num);
			createdProduct = service.createProducts(product);
			responseEntity= ResponseEntity.of(Optional.of(createdProduct));
		} catch (Exception e) {
			logger.error("Error in createProduct request:-- "+e);
			
			responseEntity = new ResponseEntity<ApiFailureResponse>(responseHandler.returnFailureResponse(FAILSTATUS, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);

		}
		logger.debug("responseEntity from createProduct: "+responseEntity);
		return responseEntity;
	}

	@PutMapping
	public ResponseEntity<?> updateProduct(@RequestBody Products product) {

		Products updatedProduct = null;
		ResponseEntity<?> responseEntity;
		logger.debug("-----updateProduct Request start-----Request: "+product.toString());
		try {
			
			if(service.findByProductId(product.getProductId()) != null){
				updatedProduct = service.updateProduct(product);
				responseEntity = ResponseEntity.of(Optional.of(updatedProduct));
			}else{
				responseEntity = new ResponseEntity<ApiFailureResponse>(responseHandler.returnFailureResponse(FAILSTATUS, "Product with ID:"+product.getProductId()+" is Not Found"), HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			logger.error("Error in updateProduct request:-- "+e);

			responseEntity = new ResponseEntity<ApiFailureResponse>(responseHandler.returnFailureResponse(FAILSTATUS, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		logger.debug("responseEntity from updateProduct: "+responseEntity);
		
		return responseEntity;
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deteleProduct(@PathVariable String id) {
		
		ResponseEntity<?> responseEntity;
		
		logger.debug("-----deteleProduct Request start-----Request: "+id);
		try{
			if(service.findByProductId(id) != null){
				 service.deleteProduct(id);
				 responseEntity = new ResponseEntity<ApiSuccessResponse>(responseHandler.returnSuccessResponse("Success"), HttpStatus.OK);
			}else{
				responseEntity = new ResponseEntity<ApiFailureResponse>(responseHandler.returnFailureResponse(FAILSTATUS, "Product with ID:"+id+" is Not Found"), HttpStatus.NOT_FOUND);
			}

		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Error in deteleProduct request:-- "+e);
			responseEntity = new ResponseEntity<ApiFailureResponse>(responseHandler.returnFailureResponse(FAILSTATUS, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		logger.debug("responseEntity from deteleProduct: "+responseEntity);
		return responseEntity;
	}

}