package org.formation.service;

import org.formation.domain.OrderItem;

public class ProductRequest {

	private String reference;
	private int quantity;
	
	public ProductRequest(OrderItem orderItem) {
		this.reference = orderItem.refProduct;
		this.quantity = orderItem.quantity;
	}
}
