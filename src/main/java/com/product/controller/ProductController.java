package com.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.model.Product;
import com.product.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductService service;

	@PostMapping("/product/create")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		return new ResponseEntity<Product>(service.createProduct(product), HttpStatus.CREATED);
	}

	@GetMapping("/hello")
	public String helloProduct(@RequestParam(name = "id") String name, @RequestHeader(name = "price") Integer price,
			@RequestBody String category) {
		return "Hello Product->" + name + " of price" + price + " the category=>" + category;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/all") // get
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> allProducts = service.getAllProducts();
		return new ResponseEntity(allProducts, HttpStatus.OK);
	}

	@SuppressWarnings("static-access")
	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProductById(@PathVariable Long id) {

		Product productById = service.getProductById(id);
		return new ResponseEntity<Product>(productById, HttpStatus.OK);

//			Product productById = null;
//			try {
//				productById = service.getProductById(id);
//				return new ResponseEntity<Product>(productById,HttpStatus.OK);
//			} catch (ErrorResponse e) {
//				log.info("The error=>{}", e.getMessage());
//				return new ResponseEntity<ErrorResponse>(e,HttpStatus.BAD_REQUEST);
//			}catch(Exception e) {
//				return new ResponseEntity<ErrorResponse>(new 
//						ErrorResponse("500",e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
//			}	
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		return new ResponseEntity<String>(service.deleteProductById(id), HttpStatus.NO_CONTENT);
	}

	@PutMapping("/product/update/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
		return new ResponseEntity<Product>(service.updateProduct(id, product), HttpStatus.OK);
	}

}
