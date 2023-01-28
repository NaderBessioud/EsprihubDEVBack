package tn.esprithub.Entities;

import java.util.Date;
import java.util.List;

public class UserQuestion {
	private Long id;
	private String nom;
	private String content;
	private Date datepub;
	private String title;
	private int nbranswers;
	private List<String> tags;
	private String role;
	private int nbreNotapproved;
	private TypeBadge badge;
	private boolean closed;
	private String image;
	private String rscr;
	
	
	
	
	
	public String getRscr() {
		return rscr;
	}
	public void setRscr(String rscr) {
		this.rscr = rscr;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public boolean isClosed() {
		return closed;
	}
	public void setClosed(boolean closed) {
		this.closed = closed;
	}
	public TypeBadge getBadge() {
		return badge;
	}
	public void setBadge(TypeBadge badge) {
		this.badge = badge;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDatepub() {
		return datepub;
	}
	public void setDatepub(Date datepub) {
		this.datepub = datepub;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getNbranswers() {
		return nbranswers;
	}
	public void setNbranswers(int nbranswers) {
		this.nbranswers = nbranswers;
	}
	
	
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public UserQuestion() {
		super();

	}
	public UserQuestion(Long idd,String nom, String content, Date datepub, String title, int nbranswers,String image) {
		super();
		this.id=idd;
		this.nom = nom;
		this.content = content;
		this.datepub = datepub;
		this.title = title;
		this.nbranswers = nbranswers;
		this.image=image;
	}
	public UserQuestion(Long idd, String nom, String content, Date datepub, String title, int nbranswers, List<String> tags,String image) {
		super();
		this.id=idd;
		this.nom = nom;
		this.content = content;
		this.datepub = datepub;
		this.title = title;
		this.nbranswers = nbranswers;
		this.tags = tags;
		this.closed=false;
		this.image=image;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public UserQuestion(Long idd,String nom, String content, Date datepub, String title, int nbranswers, List<String> tags,
			String role,TypeBadge badge, boolean closed,String image,String src) {
		super();
		this.id=idd;
		this.nom = nom;
		this.content = content;
		this.datepub = datepub;
		this.title = title;
		this.nbranswers = nbranswers;
		this.tags = tags;
		this.role = role;
		this.badge=badge;
		this.closed=closed;
		this.image=image;
		this.rscr=src;
	}
	public UserQuestion(Long idd,String nom, String content, Date datepub, String title, int nbranswers, String role,TypeBadge badge,String image) {
		super();
		this.id=idd;
		this.nom = nom;
		this.content = content;
		this.datepub = datepub;
		this.title = title;
		this.nbranswers = nbranswers;
		this.role = role;
		this.badge=badge;
		this.image=image;
	}
	public int getNbreNotapproved() {
		return nbreNotapproved;
	}
	public void setNbreNotapproved(int nbreNotapproved) {
		this.nbreNotapproved = nbreNotapproved;
	}
	public UserQuestion(Long idd ,String nom, String content, Date datepub, String title, int nbranswers, List<String> tags,
			String role, int nbreNotapproved,TypeBadge badge,String image,String src) {
		super();
		this.id=idd;
		this.nom = nom;
		this.content = content;
		this.datepub = datepub;
		this.title = title;
		this.nbranswers = nbranswers;
		this.tags = tags;
		this.role = role;
		this.nbreNotapproved = nbreNotapproved;
		this.badge=badge;
		
		this.image=image;
		this.rscr=src;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
	
	

}
