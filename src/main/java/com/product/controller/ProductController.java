package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import com.product.model.Product;
import com.product.service.ProductService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping("/hello")
	public String helloProduct(@RequestParam(name = "id") String name, @RequestHeader(name = "price") Integer price,
			@RequestBody String category

	) {
		return "Hello Product->" + name + " of price" + price + " the category=>" + category;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAllProducts() {

		List<Product> allProducts = service.getAllProducts();

		return new ResponseEntity(allProducts, HttpStatus.OK);

	}

}
