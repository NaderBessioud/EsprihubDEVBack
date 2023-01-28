package tn.esprithub.Entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
@Entity
@Data
public class Tag {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTag")
    private Long idTag;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "numberOfQuestion")
	private int numberOfQuestion;
	@Column(name = "numberOfFollowers")
	private int numberOfFollowers;
	@Column(name = "numberOfFollowers")	
	
	@ManyToMany(mappedBy = "tags")
	private Set<Question> questiontag;
	
	@ManyToMany
	private Set<Post> poststag;

	
	
	
	
	

}
