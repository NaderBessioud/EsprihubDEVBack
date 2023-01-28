package tn.esprithub.Entities;



import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.validator.constraints.Length;

@Entity

public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idQuestion")
    private Long idQuestion;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    @Length(min = 3, max = 1000)
    private String content;
    @Column(name = "likes")
    private int likes;
    @Column(name = "dislike")
    private int dislike;
    
    @Column(name = "nbresp")
    private int nbresp;
    @Temporal(TemporalType.DATE)
    @Column(name = "datepub")
    private Date datepub;
    
    @Column(name="closed")
    private boolean closed;
    
   
    
    
    @ManyToMany
    private Set<Tag> tags;
    
    @ManyToOne
    private User userquestions;
    
    @OneToMany(mappedBy = "responses")
    private Set<Response> questionresponse;
    
    @OneToMany(mappedBy = "ressources")
    private Set<Ressource> questionressources;
    
    @ManyToOne
    private UE ue;
    
    @OneToMany(mappedBy = "questionReaction")
    private Set<Reaction> reactions;
    
    

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public Long getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(Long idQuestion) {
		this.idQuestion = idQuestion;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getNbresp() {
		return nbresp;
	}

	public void setNbresp(int nbresp) {
		this.nbresp = nbresp;
	}

	public Date getDatepub() {
		return datepub;
	}

	public void setDatepub(Date datepub) {
		this.datepub = datepub;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public User getUserquestions() {
		return userquestions;
	}

	public void setUserquestions(User userquestions) {
		this.userquestions = userquestions;
	}

	public Set<Response> getQuestionresponse() {
		return questionresponse;
	}

	public void setQuestionresponse(Set<Response> questionresponse) {
		this.questionresponse = questionresponse;
	}

	public Set<Ressource> getQuestionressources() {
		return questionressources;
	}

	public void setQuestionressources(Set<Ressource> questionressources) {
		this.questionressources = questionressources;
	}

	public UE getUe() {
		return ue;
	}

	public void setUe(UE ue) {
		this.ue = ue;
	}
	
	

	public int getDislike() {
		return dislike;
	}

	public void setDislike(int dislike) {
		this.dislike = dislike;
	}

	public Question(Long idQuestion, String title, String content, int likes, int nbresp, Date datepub, Set<Tag> tags,
			User userquestions, Set<Response> questionresponse, Set<Ressource> questionressources, UE ue) {
		super();
		this.idQuestion = idQuestion;
		this.title = title;
		this.content = content;
		this.likes = likes;
		this.nbresp = nbresp;
		this.datepub = datepub;
		this.tags = tags;
		this.userquestions = userquestions;
		this.questionresponse = questionresponse;
		this.questionressources = questionressources;
		this.ue = ue;
	}
	

	public Set<Reaction> getReactions() {
		return reactions;
	}

	public void setReactions(Set<Reaction> reactions) {
		this.reactions = reactions;
	}

	public Question() {
		super();
		
	}

	@Override
	public String toString() {
		return "Question [title=" + title + ", questionresponse=" + questionresponse + "]";
	}
    

    
}
