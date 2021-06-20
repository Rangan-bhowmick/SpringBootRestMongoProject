package com.SpringBoot.Rest.mongo.rest.project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Document(collection = "products")
public class Products {
	
	@Transient
	public static final String SEQUENCE_NAME = "product_sequence";
	
	@Id
	private String productId;
	private String productCategory;
	private String productName;
	private String productDescriction;
	private int units;
	
	
	public String getProductId() {
		return productId;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public String getProductName() {
		return productName;
	}
	public String getProductDescriction() {
		return productDescriction;
	}
	public int getUnits() {
		return units;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setProductDescriction(String productDescriction) {
		this.productDescriction = productDescriction;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	@Override
	public String toString() {
		return "Products [productId=" + productId + ", productCategory=" + productCategory + ", productName="
				+ productName + ", productDescriction=" + productDescriction + ", units=" + units + "]";
	}
	
	
	
	
}
