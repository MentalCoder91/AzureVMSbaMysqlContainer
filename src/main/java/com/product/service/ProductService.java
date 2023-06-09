package com.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.exception.ProductNotFound;
import com.product.model.Product;
import com.product.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public Product createProduct(Product product) {
		return repository.save(product);
	}
	
	
	public void saveProducts(List<Product> entities) {
		repository.saveAll(entities);
	}

	public List<Product> getAllProducts() {
		return repository.findAll();
	}

	public Product getProductById(Long id) {
		Optional<Product> product = repository.findById(id);
		if (product.isEmpty()) {
			throw new ProductNotFound("530","The product is empty");
		}
		return product.get();
	}

	public String deleteProductById(Long id) {
		Optional<Product> product = repository.findById(id);
		if (product.isEmpty()) {
			throw new ProductNotFound("530","The product is empty");
		}

		Product productDelete = product.get();
		repository.delete(productDelete);

		return "The id is deleted:" + id;
	}

	public Product updateProduct(Long id, Product product) {

		Optional<Product> product1 = repository.findById(id);
		if (product1.isEmpty()) {
			throw new ProductNotFound("530","The product is empty");
		}
		Product product2 = product1.get(); // get the product.
		BeanUtils.copyProperties(product, product2);
		Product savedProduct = repository.save(product2);
		return savedProduct;
	}

}
