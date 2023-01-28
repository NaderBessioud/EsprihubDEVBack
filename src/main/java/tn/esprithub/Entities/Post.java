package tn.esprithub.Entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
@Entity
@Data
public class Post {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPost")
    private Long idPost;
	@Column(name = "title")
	private String title;
	
	@Column(name = "content")
    private String content;
    @Column(name = "likes")
    private int likes;
    
    @ManyToOne
    private User userpoest;
    
    @OneToMany(mappedBy = "postcomment")
    private Set<Comment> comments;

}
