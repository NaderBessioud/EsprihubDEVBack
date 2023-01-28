package tn.esprithub.Entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
public class Reaction {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReaction")
	private Long ideaction;
	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	private Date date;
	@Column(name = "typereaction")
	private TypeReaction typereaction;
	@Column(name = "idUser")
	private Long idUser;
	
	@ManyToOne
	private Question questionReaction;
	

}


