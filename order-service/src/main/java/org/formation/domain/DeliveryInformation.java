package org.formation.domain;

import java.time.LocalDateTime;

import javax.persistence.Embedded;

public class DeliveryInformation {

	public LocalDateTime deliveryTime;
	
	@Embedded
	public Address address;
}
