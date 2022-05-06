package org.formation.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderItem {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	public String refProduct;
	
	public float price;
	
	public int quantity;
	
	
}
