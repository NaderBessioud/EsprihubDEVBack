package tn.esprithub.Entities;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
public class UE {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUE")
    private Long idUE;
    @Column(name = "libelle")
    private String libelle;
    
    @JsonIgnore
    @OneToMany(mappedBy = "ue")
    private List<Question> uequestions;
    
    @JsonIgnore
    @OneToMany(mappedBy = "uecours")
    private List<Cours> cours;
    
	public Long getIdUE() {
		return idUE;
	}

	public void setIdUE(Long idUE) {
		this.idUE = idUE;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@JsonIgnore
	public List<Question> getUequestions() {
		return uequestions;
	}

	@JsonIgnore
	public void setUequestions(List<Question> uequestions) {
		this.uequestions = uequestions;
	}
	@JsonIgnore
	public List<Cours> getCours() {
		return cours;
	}
	@JsonIgnore
	public void setCours(List<Cours> cours) {
		this.cours = cours;
	}

	
	public UE(String title) {
		this.libelle=title;
	}

	public UE() {
		super();
			}
	
	

}
