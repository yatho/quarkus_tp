package org.formation.web;

import com.fasterxml.jackson.annotation.JsonView;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.formation.configuration.Views;
import org.formation.domain.Livraison;
import org.formation.service.LivraisonService;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestPath;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/livraisons")
public class LivraisonController {
    @Inject
    private LivraisonService livraisonService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Base.class)
    @Blocking
    public Uni<List<Livraison>> findAllReactive() {
        return livraisonService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(Views.Complet.class)
    public Uni<Livraison> create(Livraison livraison) {
        return livraisonService.create(livraison);
    }

    @PUT
    @Path("/{id}")
    @ResponseStatus(201)
    public Uni<Livraison> update(@RestPath("id") Long id, @Valid Livraison livraison) {
        return livraisonService.update(id, livraison)
                .onItem().ifNull().failWith(() -> new NotFoundException("Livraison not found"));
    }

    @DELETE
    @Path("/{id}")
    @ResponseStatus(200)
    public void delete(@RestPath("id") Long id) {
        livraisonService.delete(id);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Livraison> findById(@PathParam("id") Long id) {
        return livraisonService.findById(id)
                .onItem().ifNull().failWith(() -> new NotFoundException("Livraison not found"));
    }
}
