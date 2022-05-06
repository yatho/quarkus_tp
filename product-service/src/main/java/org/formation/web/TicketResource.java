package org.formation.web;

import io.quarkus.logging.Log;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.formation.domain.ProductRequest;
import org.formation.domain.Ticket;
import org.formation.domain.TicketStatus;
import org.formation.event.OrderEvent;
import org.formation.event.OrderStatus;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@Path("/tickets")
public class TicketResource {

	@Incoming("order")
	@Outgoing("order-out")
	public Uni<OrderEvent> onOrderEvent(OrderEvent orderEvent) {
		Log.info("Message " + orderEvent);
		if (orderEvent.orderStatus.equals(OrderStatus.CREATED)) {
			return acceptOrder(orderEvent.order.id, orderEvent.order.productRequests)
					.onItem().transform(ticket -> {
						orderEvent.orderStatus = OrderStatus.PENDING;
						return orderEvent;
					});
		}
		return Uni.createFrom().nothing();
	}

	@GET
	public Multi<Ticket> findAll() {
		return Ticket.findAll().stream();
	}
	
	@POST
	public Uni<Ticket> acceptOrder(@RestQuery Long orderId, List<ProductRequest> productsRequest) {
		return Ticket.<Ticket>find("orderId", orderId).firstResult()
				.onItem().ifNull().continueWith(Ticket::new)
				.onItem().call(ticket -> {
					ticket.orderId = orderId;
					ticket.status = TicketStatus.CREATED;
					ticket.productRequests = productsRequest;
					return ticket.persist();
				});
	}
	
	@POST
	@Path("/{orderId}/pickup")
	public Uni<Ticket> noteTicketReadyToPickUp(@RestPath Long orderId) {
		return Ticket.<Ticket>find("orderId", orderId).firstResult()
				.onItem().ifNull().failWith(NotFoundException::new)
				.map(ticket -> {
					ticket.status = TicketStatus.READY_TO_PICK;
					PanacheMongoEntity.update(ticket);
					return ticket;
				});
	}
}
