package tn.esprithub.Entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Ressource {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRessource")
    private Long idRessource;
	@Column(name = "libelle")
	private String libelle;
	@Column(name = "type")
	private TypeRessource type;
	
	@ManyToOne
	private Question ressources;
	
}
