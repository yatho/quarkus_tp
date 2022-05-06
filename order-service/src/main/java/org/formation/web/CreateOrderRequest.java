package org.formation.web;

import java.util.List;

import org.formation.domain.Address;
import org.formation.domain.DeliveryInformation;
import org.formation.domain.Order;
import org.formation.domain.OrderItem;
import org.formation.domain.PaymentInformation;

public class CreateOrderRequest {

	  public List<OrderItem> lineItems;
	  public Address deliveryAddress;
	  public PaymentInformation paymentInformation;
	  
	  public Order createOrder() {
		  Order order = new Order();
		  DeliveryInformation df = new DeliveryInformation();
		  df.address = deliveryAddress;
		  order.deliveryInformation = df;
		  order.orderItems = lineItems;
		  order.paymentInformation = paymentInformation;
		  
		  return order;
	  }
}
