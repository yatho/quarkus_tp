package org.formation.domain;

import com.fasterxml.jackson.annotation.JsonView;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.formation.configuration.Views;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review extends PanacheEntityBase {

	@JsonView(Views.Complet.class)
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;

	@JsonView(Views.Complet.class)
	public int note;

	@JsonView(Views.Complet.class)
	public String commentaire;
}
