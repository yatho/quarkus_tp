package org.formation.domain;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_order")
public class Order {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	public Instant date;
	
	public float discount;
	

	@Embedded
	public PaymentInformation paymentInformation;
	
	@Embedded
  	public DeliveryInformation deliveryInformation;
	
	@OneToMany(cascade = CascadeType.ALL)
	public List<OrderItem> orderItems = new ArrayList<>();
	
}
