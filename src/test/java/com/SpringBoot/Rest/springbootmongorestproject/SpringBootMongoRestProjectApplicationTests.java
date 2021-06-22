package com.SpringBoot.Rest.springbootmongorestproject;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.SpringBoot.Rest.mongo.rest.project.SpringBootMongoRestProjectApplication;
import com.SpringBoot.Rest.mongo.rest.project.model.Products;
import com.SpringBoot.Rest.mongo.rest.project.repository.ProductsRepository;
import com.SpringBoot.Rest.mongo.rest.project.service.ProductService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= SpringBootMongoRestProjectApplication.class)
public class SpringBootMongoRestProjectApplicationTests {

	@Autowired
	private ProductService service;

	
	@MockBean
	private ProductsRepository repository;
	
	@Test
	public void getAllProductTest() {
		
		Products p1 = new Products();
		p1.setProductId("P1");
		p1.setProductCategory("Commercial");
		p1.setProductName("A320");
		p1.setProductDescriction("Passenger aircraft family");
		p1.setUnits(2);
		
		Products p2 = new Products();
		p2.setProductId("P2");
		p2.setProductCategory("Space");
		p2.setProductName("Sentinel");
		p2.setProductDescriction("Satellite family");
		p2.setUnits(1);
		
		when(repository.findAll()).thenReturn(Stream
				.of(p1, p2).collect(Collectors.toList()));
		assertEquals(2, service.getAllProducts().size());
	}
	
	
	@Test
	public void getProductsByCatagoryTest() {
		
		Products p1 = new Products();
		p1.setProductId("P1");
		p1.setProductCategory("Commercial");
		p1.setProductName("A320");
		p1.setProductDescriction("Passenger aircraft family");
		p1.setUnits(2);
		String category = "Commercial";
		when(repository.findByName(category))
				.thenReturn(Stream.of(p1).collect(Collectors.toList()));
		assertEquals(1, service.getProductsByCatagory(category).size());
	}

	@Test
	public void addNewProductTest() {
		Products p2 = new Products();
		p2.setProductId("P2");
		p2.setProductCategory("Space");
		p2.setProductName("Sentinel");
		p2.setProductDescriction("Satellite family");
		p2.setUnits(1);
		
		when(repository.save(p2)).thenReturn(p2);
		assertEquals(p2, service.addNewProducts(p2));
	}

	/*@Test
	public void deteleProductTest() {
		Products p2 = new Products();
		p2.setProductId("P2");
		p2.setProductCategory("Space");
		p2.setProductName("Sentinel");
		p2.setProductDescriction("Satellite family");
		p2.setUnits(1);
		service.deleteProduct(p2.getProductId());
		verify(repository).deleteById(p2.getProductId());
	}*/

}
