package com.maxtrain.capstone.requestline;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.maxtrain.capstone.product.Product;
import com.maxtrain.capstone.request.Request;

@Entity
public class Requestline {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int quantity; // Default value is 1 - Done in the frontend
	
	// RequestId is a Foreign Key
	// Many Requestlines for a Request
	@JsonBackReference
	@ManyToOne(optional=false)
	@JoinColumn(name="requestId")
	private Request request;
	
	// ProductId is a Foreign Key
	// @JsonBackReference
	@ManyToOne(optional=false)
	@JoinColumn(name="productId")
	private Product product;
	
	// Default Constructor
	public Requestline() {}

	// Getters & Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}

// Notes:
// JsonBackReference means doesn't fill in the request but brings back product since would go from
// a Request reading a Requestline to reading the same Request which would read a Requestline

// ManyToOne means that there are many requests for one user. The optional means that can't allow to be null