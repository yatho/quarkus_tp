package org.formation.service;

import org.formation.domain.Order;
import org.formation.domain.OrderRepository;
import org.formation.event.OrderEvent;
import org.formation.web.CreateOrderRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class OrderService {
	
	@Inject
	OrderRepository orderRepository;

	@Transactional
	public Order createOrder(CreateOrderRequest createOrderRequest) {
		Order order = createOrderRequest.createOrder();
		// Save in local DataBase
		orderRepository.persist(order);
		
		return order;
	}
	
	/**
	 * Produce Event to kafka topic "order"
	 * @param orderEvent
	 */
	public void publishEvent(OrderEvent orderEvent) {
		
	}
	
}
