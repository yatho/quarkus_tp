package org.formation.service;

import io.quarkus.oidc.client.reactive.filter.OidcClientRequestReactiveFilter;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.formation.dto.Email;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/notifications")
@RegisterProvider(OidcClientRequestReactiveFilter.class)
@RegisterRestClient(configKey = "notification-api")
public interface NotificationService {
    @POST
    Email send(Email email);
    @POST
    @Path("/reactive")
    Email sendReactive(Email email);
}