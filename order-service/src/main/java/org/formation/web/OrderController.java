package org.formation.web;

import io.quarkus.runtime.StartupEvent;
import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.formation.domain.Order;
import org.formation.event.OrderEvent;
import org.formation.event.OrderStatus;
import org.formation.service.OrderService;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.ResponseStatus;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletableFuture;

@Path("/orders")
public class OrderController {

	Logger log = Logger.getLogger(OrderController.class);

	@Inject
	OrderService orderService;

	@Inject
	@Channel("order")
	Emitter<OrderEvent> orderEmitter;

	@Inject
	@Channel("order")
	Multi<OrderEvent> orderChannel;

	void onStart(@Observes StartupEvent event) {
		orderChannel.subscribe().with(orderEvent -> {
			log.info("Order event: " + orderEvent.orderStatus);
		});
	}

	@POST
	@ResponseStatus(201)
	@RolesAllowed("ADMIN")
	public Order createOrder(CreateOrderRequest request) {
		Order ret = orderService.createOrder(request);

		OrderEvent orderEvent = new OrderEvent();
		orderEvent.order = ret;
		orderEvent.orderStatus = OrderStatus.CREATED;

		orderEmitter.send(Message.of(orderEvent)
				.withAck(() -> {
					// Called when the message is acked
					log.info("Message acked");
					return CompletableFuture.completedFuture(null);
				})
				.withNack(throwable -> {
					// Called when the message is nacked
					log.info("Message nacked");
					return CompletableFuture.completedFuture(null);
				}));

		log.info("Order created: " + ret.id);
		
		return ret;
		
	}

	@GET
	@Path("/status")
	@Produces(MediaType.SERVER_SENT_EVENTS)
	public Multi<OrderEvent> stream() {
		return orderChannel;
	}
}
