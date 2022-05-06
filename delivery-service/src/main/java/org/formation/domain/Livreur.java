package org.formation.domain;

import com.fasterxml.jackson.annotation.JsonView;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.formation.configuration.Views;

import javax.persistence.*;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Livreur extends PanacheEntityBase {

	@JsonView(Views.Base.class)
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	@JsonView(Views.Complet.class)
	public String nom;
	
	@JsonView(Views.Complet.class)
	public String telephone;

	@JsonView(Views.Complet.class)
	@OneToMany(cascade = CascadeType.ALL)
	public List<Review> reviews;
	
}
