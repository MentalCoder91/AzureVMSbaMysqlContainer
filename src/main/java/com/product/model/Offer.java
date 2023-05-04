package com.product.model;


public class Offer {
	
	private Long id;
	private String startCharacter;
	private Double offerPercentage;
	
	public Offer(Long id, String startCharacter, Double offerPercentage) {
		this.id = id;
		this.startCharacter = startCharacter;
		this.offerPercentage = offerPercentage;
	}

	public Offer() {
		super();
	}




	@Override
	public String toString() {
		return "Offer [id=" + id + ", startCharacter=" + startCharacter + ", offerPercentage=" + offerPercentage + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStartCharacter() {
		return startCharacter;
	}

	public void setStartCharacter(String startCharacter) {
		this.startCharacter = startCharacter;
	}

	public Double getOfferPercentage() {
		return offerPercentage;
	}

	public void setOfferPercentage(Double offerPercentage) {
		this.offerPercentage = offerPercentage;
	}
	
	
	
	
}
