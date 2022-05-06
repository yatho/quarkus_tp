package org.formation.domain;

import com.fasterxml.jackson.annotation.JsonView;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.formation.configuration.Views;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Livraison extends PanacheEntityBase {

	@JsonView(Views.Base.class)
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	@JsonView(Views.Base.class)
	public String noCommande;
	
	@JsonView(Views.Base.class)
	@OneToOne
	public Livreur livreur;
	
	@JsonView(Views.Base.class)
	@NotNull
	public Status status;
	
	@JsonView(Views.Complet.class)
	public Instant creationDate;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livraison other = (Livraison) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	


}
