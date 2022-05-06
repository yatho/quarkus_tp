package org.formation.event;

import org.formation.domain.ProductRequest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Order {

	public long id;
	
	public Instant date;
	
	public List<ProductRequest> productRequests = new ArrayList<>();
	
}
