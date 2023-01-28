package tn.esprithub.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;




@Entity

public class LienUtile {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLien")
    private Long idLien;
	 @Column(name = "libelle")
	 private String libelle;
	 @Column(name = "content")
	 private String content;
	 
	 @ManyToOne
	 private Cours courslien;

	public Long getIdLien() {
		return idLien;
	}

	public void setIdLien(Long idLien) {
		this.idLien = idLien;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Cours getCourslien() {
		return courslien;
	}

	public void setCourslien(Cours courslien) {
		this.courslien = courslien;
	}
	
	
	
	
}
