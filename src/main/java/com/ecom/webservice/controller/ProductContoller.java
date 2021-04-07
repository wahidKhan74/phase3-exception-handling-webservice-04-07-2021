package com.ecom.webservice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.webservice.entity.Product;

@RestController
public class ProductContoller {

	List<Product> products = new ArrayList<Product>();

	// crud operations for product

	// get one product
	@RequestMapping(value = "/products/{id}", method=RequestMethod.GET)
	public Product getOneProduct(@PathVariable("id") int id) {
		for (Product product : products) {
			if (product.getId() == id) {
				return product;
			}
		}
		return null;
	}

	// get one product by name
	@RequestMapping(value = "/product",method=RequestMethod.GET)
	public Product getOneProduct(@PathParam("name") String name) {
		for (Product product : products) {
			System.out.println(name);
			if (product.getName().equals(name)) {
				return product;
			}
		}
		return null;
	}

	// get all products
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public List<Product> getAllProducts() {
		if (products.isEmpty()) {
			addDefaultProduct();
		}
		return products;
	}

	// create product
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public List<Product> addProduct(@RequestBody Product productObj){
		products.add(productObj);
		return products;
	}
	
	// update product
	@RequestMapping(value = "/products/{id}", method= RequestMethod.PUT)
	public Product updateOneProduct(@PathVariable("id") int id, @RequestBody Product productObj) {
		
		for (int i=0; i<products.size(); i++) {
			if (products.get(i).getId() == id) {
				products.set(i, productObj);
				return products.get(i);
			}
		}
		return null;
	}
	
	// delete product
	@RequestMapping(value = "/products/{id}", method=RequestMethod.DELETE)
	public List<Product> deleteOneProduct(@PathVariable("id") int id) {
		
		for (int i=0; i<products.size(); i++) {
			if (products.get(i).getId() == id) {
				products.remove(i);
				return products;
			}
		}
		return null;
	}
	
	private List<Product> addDefaultProduct() {

		products.add(new Product(1001, "Iphone 11 pro", 70000, "It is a smart phone"));
		products.add(new Product(1002, "Lenovo Laptop XYZ series", 90000, "It is a laptop"));
		products.add(new Product(1003, "Ferari", 900000, "It is a car"));
		products.add(new Product(1004, "Dimond Ring", 2300000, "It is a ring"));

		return products;
	}

}
