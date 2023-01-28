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
public class Badge {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBadge")
    private Long idBadge;
	@Column(name = "score")
	private int score;
	
	@ManyToOne
	private User userbadges;

}
