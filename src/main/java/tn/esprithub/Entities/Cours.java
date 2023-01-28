package tn.esprithub.Entities;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity

public class Cours {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCours")
    private Long idCours;
	 @Column(name = "libelle")
	 private String libelle;


	 @ManyToOne
	 private UE uecours;
	 
	@JsonIgnore
	@OneToMany(mappedBy = "courslien")
		 private List<LienUtile> liens;
	 
	 public Long getIdCours() {
		return idCours;
	}

	public void setIdCours(Long idCours) {
		this.idCours = idCours;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public UE getUecours() {
		return uecours;
	}

	public void setUecours(UE uecours) {
		this.uecours = uecours;
	}

	@JsonIgnore
	public List<LienUtile> getLiens() {
		return liens;
	}

	@JsonIgnore
	public void setLiens(List<LienUtile> liens) {
		this.liens = liens;
	}

	
	

}
