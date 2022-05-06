package org.formation.domain;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;

import java.util.List;

public class Ticket extends ReactivePanacheMongoEntity {
	
	public Long orderId;
	
	public TicketStatus status;
	
	public List<ProductRequest> productRequests;
	
	
}
