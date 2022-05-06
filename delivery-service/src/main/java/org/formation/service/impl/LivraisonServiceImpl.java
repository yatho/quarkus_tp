package org.formation.service.impl;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.oidc.client.Tokens;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.formation.configuration.NotificationPropertiesMapping;
import org.formation.domain.Livraison;
import org.formation.dto.Email;
import org.formation.service.LivraisonService;
import org.formation.service.NotificationService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.net.URI;
import java.util.List;

@ApplicationScoped
public class LivraisonServiceImpl implements LivraisonService {
    @Inject
    @RestClient
    private NotificationService notificationService;

    @Inject
    private NotificationPropertiesMapping notificationPropertiesMapping;

    @Inject
    Tokens tokens;

    @Override
    public Uni<List<Livraison>> findAll() {
        notificationService.sendReactive(new Email());
        return Livraison.findAll().list();
    }

    @Override
    public Uni<Livraison> create(Livraison livraison) {
        return Panache.withTransaction(livraison::persist);
    }

    @Override
    public Uni<Livraison> findById(Long id) {
        return Livraison.findById(id);
    }

    @Override
    public void delete(Long id) {
        Panache.withTransaction(() -> Livraison.deleteById(id));
    }

    @Override
    public Uni<Livraison> update(long id, Livraison livraison) {
        return Panache.withTransaction(() -> Livraison.<Livraison>findById(id)
                        .onItem().ifNotNull().invoke(entity -> {
                            entity.status = livraison.status;
                            entity.livreur = livraison.livreur;
                            entity.noCommande = livraison.noCommande;
                        })
                );
    }
}
