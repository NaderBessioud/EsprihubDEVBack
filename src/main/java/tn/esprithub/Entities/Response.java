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



@Entity
public class Response {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "idResponse")
	    private Long idResponse;
	 	@Column(name = "contenta")
	    private String content;
	    @Column(name = "likes")
	    private int likes;
	    @Temporal(TemporalType.DATE)
	    @Column(name = "datepub")
	    private Date datepub;
	    @Column(name = "idUser")
	    private Long idUser;
	   
	    @Column(name = "approved")
	    private int approved;
	    
	    
	  


		public int getApproved() {
			return approved;
		}


		public void setApproved(int approved) {
			this.approved = approved;
		}


		@ManyToOne
	    private Question responses;


		public Long getIdResponse() {
			return idResponse;
		}


		public void setIdResponse(Long idResponse) {
			this.idResponse = idResponse;
		}


		public String getContent() {
			return content;
		}


		public void setContent(String contenta) {
			this.content = contenta;
		}


		public int getLikes() {
			return likes;
		}


		public void setLikes(int likes) {
			this.likes = likes;
		}


		public Date getDatepub() {
			return datepub;
		}


		public void setDatepub(Date datepub) {
			this.datepub = datepub;
		}


		public Long getIdUser() {
			return idUser;
		}


		public void setIdUser(Long idUser) {
			this.idUser = idUser;
		}


		public Question getResponses() {
			return responses;
		}


		public void setResponses(Question responses) {
			this.responses = responses;
		}


		public Response(Long idResponse, String contenta, int likes, Date datepub, Long idUser, Question responses) {
			super();
			this.idResponse = idResponse;
			this.content = contenta;
			this.likes = likes;
			this.datepub = datepub;
			this.idUser = idUser;
			this.responses = responses;
		}


		public Response() {
			super();

		}


		@Override
		public String toString() {
			return "Response [idResponse=" + idResponse + ", contenta=" + content + ", likes=" + likes + ", datepub="
					+ datepub + ", idUser=" + idUser +  "]";
		}




}
