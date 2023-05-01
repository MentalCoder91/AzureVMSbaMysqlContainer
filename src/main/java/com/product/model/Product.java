package com.product.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


@Entity // maps a POJO/model to a table in database
@Table(name = "products") 
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;
	
	@NotBlank(message = "Product Name should not be blank,is mandatory")
	private String productName;
	
	@NotBlank(message = "Product Price should not be blank,is mandatory")
	@Min(value=50, message = "Product Price must be atleast 50")
    @Max(value=40000, message = "Product Price should not be greater than 40000.00")
	private Double productPrice;
	
	public Product() {
		super();
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ "]";
	}
	
	
	
	
}
