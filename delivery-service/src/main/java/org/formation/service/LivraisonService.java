package org.formation.service;

import io.smallrye.mutiny.Uni;
import org.formation.domain.Livraison;

import java.util.List;

public interface LivraisonService {
    Uni<List<Livraison>> findAll();

    Uni<Livraison> create(Livraison livraison);

    Uni<Livraison> findById(Long id);

    void delete(Long id);

    Uni<Livraison> update(long id, Livraison livraison);
}
