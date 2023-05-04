package com.product.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.product.model.Offer;
import com.product.model.Product;
import com.product.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private RestTemplate restTemplate;

	private static final String OFFER_URI = "http://localhost:9099/offers/{startChar}";

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductService service;

	@PostMapping("/product/create")
	public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
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

	@GetMapping("/product/offers")
	public List<Product> getOfferProduct() {

		List<Product> allProducts = service.getAllProducts();
		List<Product> offerProductList = new ArrayList<>();

		for (Product product : allProducts) {
			// http://localhost:9099/offer/A
			// http://localhost:9099/offer/B
			try {

				String productName = product.getProductName().substring(0, 1); // Anish -> A // the value of the path
				// Building RestTemplate ************* // variable
				URI uri = UriComponentsBuilder.fromUriString(OFFER_URI).buildAndExpand(productName).toUri(); // uri

				HttpHeaders headers = new HttpHeaders();
				headers.set("content-type", "application/json"); // headers
				HttpEntity<String> entity = new HttpEntity<>(headers); // entity
				// ***********************
				ResponseEntity<Offer> response = restTemplate.exchange(uri, HttpMethod.GET, entity, Offer.class);
				Offer offerObj = response.getBody();

				product.setProductPrice(
						product.getProductPrice() - (offerObj.getOfferPercentage() * 0.01 * product.getProductPrice()));
				offerProductList.add(product);

			} catch (Exception ex) {

				log.error("Ther err->{}", ex);
			}

		}

		return offerProductList;

	}

	@GetMapping("/product/offerAsync")
	public List<Product> getOfferProductAsync() {

		List<Product> allProducts = service.getAllProducts();

		List<CompletableFuture<Product>> allFutures = allProducts.stream().map(product -> {

			CompletableFuture<Product> futureProduct = CompletableFuture.supplyAsync(() -> {
				String startChar = product.getProductName().substring(0, 1);
				Product callRestAsync = callRestAsync(startChar, product);
				return callRestAsync;
			});
			return futureProduct;
		}).collect(Collectors.toList());
		
		List<Product> productList = allFutures.stream().map(cf -> cf.join()).collect(Collectors.toList());
		return productList;
	}

	private Product callRestAsync(String startChar, Product product) {

		log.info("The Thread name ->{}", Thread.currentThread().getName());
		// Building RestTemplate ************* // variable
		URI uri = UriComponentsBuilder.fromUriString(OFFER_URI).buildAndExpand(startChar).toUri(); // uri

		HttpHeaders headers = new HttpHeaders();
		headers.set("content-type", "application/json"); // headers
		HttpEntity<String> entity = new HttpEntity<>(headers); // entity
		// ***********************
		ResponseEntity<Offer> response = restTemplate.exchange(uri, HttpMethod.GET, entity, Offer.class);
		Offer offerObj = response.getBody();

		product.setProductPrice(
				product.getProductPrice() - (offerObj.getOfferPercentage() * 0.01 * product.getProductPrice()));

		return product;
	}

}
